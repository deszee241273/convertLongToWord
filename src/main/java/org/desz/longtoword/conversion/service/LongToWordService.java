package org.desz.longtoword.conversion.service;

import static java.util.Arrays.asList;
import static java.util.Objects.nonNull;
import static java.util.Objects.requireNonNull;
import static org.desz.longtoword.factory.WordForNumberSupplier.wcInstance;

import java.text.NumberFormat;
import java.util.Locale;
import java.util.OptionalInt;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Supplier;

import org.desz.longtoword.conversion.decorators.DeDecorator;
import org.desz.longtoword.conversion.results.Word;
import org.desz.longtoword.exceptions.BuildWordException;
import org.desz.longtoword.exceptions.ConversionException;
import org.desz.longtoword.language.ProvLang;
import org.desz.longtoword.language.WordCache;

import lombok.extern.slf4j.Slf4j;

/**
 * @author des Converts number to word in target language.
 *
 */
@Slf4j
public final class LongToWordService {
	private static final NumberFormat FORMATTER = NumberFormat.getIntegerInstance(Locale.UK);

	private final Supplier<Word> wordSupplier = new WordSupplier();

	public static final ScopedValue<WordCache> WC_CTX = ScopedValue.newInstance();

	public static final ScopedValue<ScopeParams> CTX = ScopedValue.newInstance();

	/**
	 *
	 * @param num      the long.
	 * @param provLang the ProvLang.
	 * @return the word.
	 * @throws ConversionException the ConversionException.
	 */

	public String convert(final long num, final ProvLang provLang) throws ConversionException {

		var pvl = requireNonNull(provLang);

		var wordCache = wcInstance().get(pvl);

		if (num == 0) {
			return wordCache.wordForNbr(0).orElseThrow(ConversionException::new).toLowerCase();
		}

		// return if cache contains num word mapping.
		var hun = wordCache.wordForNbr((int) num);

		if (hun.isPresent()) {
			return hun.get().toLowerCase();
		}
		// compute the word.
		var wordRef = new AtomicReference<Word>();
		// list num elements.
		var numbers = asList(FORMATTER.format(num).split(","));
		var scopedValues = new ScopeParams(Word.builder(), wordCache, numbers);
		try {
			var word = ScopedValue.where(CTX, scopedValues).call(wordSupplier::get);
			wordRef.set(word);

			// decorate DE word.
			if (pvl.equals(ProvLang.DE)) {
				var lastElem = OptionalInt.of(Integer.parseUnsignedInt(numbers.getLast(), 10))
						.orElseThrow(ConversionException::new);
				var lastIsEin = lastElem % 100 == 1;
				ScopedValue.where(WC_CTX, wordCache).run(() -> {
					var deWord = new DeDecorator(wordRef.get()).pluraliseUnit();
					deWord = lastIsEin ? new DeDecorator(deWord).pluraliseEin() : deWord;
					deWord = nonNull(deWord.thou()) ? new DeDecorator(deWord).concatThouHund() : deWord;
					wordRef.set(deWord.toBuilder().build());
				});

			}

		} catch (BuildWordException _ex) {
			throw new ConversionException(_ex.getMessage());
		}

		var result = wordRef.get().toString();

		log.info(String.format("%s converted to %s", num, result));
		return result;

	}

}
