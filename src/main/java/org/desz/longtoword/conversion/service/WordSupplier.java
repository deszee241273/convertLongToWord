package org.desz.longtoword.conversion.service;

import static java.util.Arrays.asList;
import static java.util.Objects.isNull;
import static java.util.OptionalInt.of;
import static org.apache.commons.lang3.StringUtils.EMPTY;
import static org.apache.commons.lang3.StringUtils.SPACE;
import static org.desz.longtoword.language.ProvLang.DE;

import java.util.List;
import java.util.function.Supplier;

import org.desz.longtoword.conversion.results.Word;
import org.desz.longtoword.conversion.results.Word.WordBuilder;
import org.desz.longtoword.exceptions.BuildWordException;
import org.desz.longtoword.exceptions.HundredthConversionException;
import org.desz.longtoword.language.WordCache;

final class WordSupplier implements Supplier<Word> {

	private WordBuilder wordBuilder;
	private WordCache wordCache;
	private List<String> numbers;

	/**
	 * rearrangement of DE hundredth.
	 *
	 * @param s      the DE word to process.
	 * @param addAnd whether to add und.
	 * @return the word.
	 */
	private String processDeHun(final String s, final boolean addAnd) {

		var l = asList(s.split(SPACE));
		var sz = l.size();

		if (sz == 1) {
			return s;
		}

		switch (sz) {

		case 2 -> {
			return addAnd ? l.get(1) + wordCache.and() + l.get(0) : l.get(0) + l.get(1).substring(3);
		}

		case 3 -> {
			return l.get(0) + l.get(2) + l.get(1);
		}
		default -> throw new BuildWordException();

		}

	}

	/**
	 * Applies tail recursion methodology to convert numbers into word.
	 * 
	 * @return the Word.
	 */
	private Word buildWord() throws BuildWordException {

		if (isNull(this.wordCache)) {
			assert (LongToWordService.WC_CTX.isBound());
			this.wordCache = LongToWordService.WC_CTX.orElseThrow(BuildWordException::new);

			assert (LongToWordService.NUMS_CTX.isBound());
			this.numbers = LongToWordService.NUMS_CTX.orElseThrow(BuildWordException::new);

			assert (LongToWordService.WB_CTX.isBound());
			this.wordBuilder = LongToWordService.WB_CTX.orElseThrow(BuildWordException::new);

		}

		var num = of(Integer.parseUnsignedInt(numbers.getFirst(), 10)).orElseThrow(BuildWordException::new);
		var sz = numbers.size();
		if (num != 0) {

			var hun = convertHundredth(num);

			if (wordCache.id().equals(DE.name())) {
				hun = processDeHun(hun, num % 100 > 20);

			}

			switch (sz) {
			case 7 -> wordBuilder.quint(hun + wordCache.quintn());

			case 6 -> wordBuilder.quadr(hun + wordCache.quadrn());

			case 5 -> wordBuilder.trill(hun + wordCache.trilln());

			case 4 -> wordBuilder.bill(hun + wordCache.billn());

			case 3 -> wordBuilder.mill(hun + wordCache.milln());

			case 2 -> wordBuilder.thou(hun + wordCache.thoud());

			case 1 -> wordBuilder.hund(hun);

			default -> throw new BuildWordException();

			}

		}
		this.numbers = numbers.subList(1, sz);
		return sz == 1 ? wordBuilder.build() : buildWord();

	}

	/**
	 * 
	 *
	 * @param num the integer to convert.
	 * @return the word.
	 *
	 * @throws HundredthConversionException
	 */
	private String convertHundredth(final int num) throws HundredthConversionException {

		var numWord = wordCache.wordForNbr(num);
		if (numWord.isPresent()) {
			return numWord.get().toLowerCase();
		}

		var hun = (wordCache.wordForNbr(num / 100).orElseGet(() -> EMPTY) + wordCache.hund()).toLowerCase();

		var mod = num % 100;
		if (mod == 0) { // hun = 100, 200..900
			return hun;
		}

		hun = num < 100 ? EMPTY : hun + SPACE + wordCache.and();

		var dec = wordCache.wordForNbr(mod);

		if (dec.isPresent()) {
			return hun + dec.get().toLowerCase();
		}
		// calculate decimal part.

		int k = mod;// e.g., mod = 23
		mod %= 10;
		k -= mod;

		return hun + wordCache.wordForNbr(k).orElseGet(() -> EMPTY).toLowerCase() + SPACE
				+ wordCache.wordForNbr(mod).orElseGet(() -> EMPTY).toLowerCase();
	}

	@Override
	public Word get() {
		return this.buildWord();
	}

}
