package org.desz.longtoword.conversion.service;

import static java.util.Arrays.asList;
import static java.util.OptionalInt.of;
import static org.apache.commons.lang3.StringUtils.EMPTY;
import static org.apache.commons.lang3.StringUtils.SPACE;
import static org.desz.longtoword.language.ProvLang.DE;

import java.util.List;
import java.util.Objects;
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

	private String processDeHun(final int num) {

		var hun = this.convertHundredth(num);

		var l = asList(hun.split(SPACE));
		var sz = l.size();

		if (sz == 1) {
			return hun;
		}

		switch (sz) {

		case 2 -> {
			return num % 100 > 20 ? l.get(1) + wordCache.and() + l.get(0) : l.get(0) + l.get(1).substring(3);
		}

		case 3 -> {
			return l.get(0) + l.get(2) + l.get(1);
		}
		default -> throw new BuildWordException();

		}

	}

	/**
	 * Recursive method uses Tail Call Optimisation to build word.
	 * 
	 * @return the Word.
	 */
	private Word buildWord() throws BuildWordException {

		if (numbers.isEmpty())
			return wordBuilder.build();
		var num = of(Integer.parseUnsignedInt(numbers.getFirst(), 10)).orElseThrow(BuildWordException::new);

		var sz = numbers.size();

		if (num != 0) {

			var hun = wordCache.id().equals(DE.name()) ? processDeHun(num) : convertHundredth(num);

			switch (sz) {
			case 7 -> wordBuilder.quint(hun + wordCache.quint());

			case 6 -> wordBuilder.quadr(hun + wordCache.quadr());

			case 5 -> wordBuilder.trill(hun + wordCache.trill());

			case 4 -> wordBuilder.bill(hun + wordCache.bill());

			case 3 -> wordBuilder.mill(hun + wordCache.mill());

			case 2 -> wordBuilder.thou(hun + wordCache.thou());

			case 1 -> wordBuilder.hund(hun);

			default -> throw new BuildWordException();

			}

		}
		this.numbers = numbers.subList(1, sz);
		return buildWord();

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
		if (Objects.isNull(this.wordCache)) {
			var sc = LongToWordService.CTX.get();
			this.wordCache = sc.wordCache();

			this.numbers = sc.numbers();

			this.wordBuilder = sc.builder();
		}

		return this.buildWord();
	}

}
