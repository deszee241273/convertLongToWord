/**
 *
 */
package org.desz.longtoword.conversion.decorators;

import static java.util.Arrays.asList;
import static java.util.Objects.nonNull;
import static java.util.Objects.requireNonNull;
import static org.apache.commons.lang3.StringUtils.EMPTY;
import static org.apache.commons.lang3.StringUtils.SPACE;
import static org.desz.longtoword.language.ProvLang.DE;

import org.desz.longtoword.conversion.results.Word;
import org.desz.longtoword.conversion.results.Word.WordBuilder;
import org.desz.longtoword.conversion.service.LongToWordService;
import org.desz.longtoword.exceptions.DecoratorException;
import org.desz.longtoword.factory.WordCacheSupplier;
import org.desz.longtoword.language.WordCache;

/**
 * @author des
 *
 */
public final class DeDecorator implements IWordDecorator<Word> {

	private final WordCache wordCache;

	private final Word word;

	/**
	 *
	 * @param word the DE Word.
	 */
	public DeDecorator(final Word word) {
		this.word = requireNonNull(word);
		this.wordCache = LongToWordService.WC_CTX.orElse(WordCacheSupplier.wcInstance().get(DE));

	}

	private String pluralise(String unitWord) {

		var num = asList(unitWord.split(SPACE));
		var ein = wordCache.wordForNbr(1).orElseThrow(DecoratorException::new);
		var plur = unitWord.endsWith("e") ? unitWord + "n" : unitWord + "en";
		return !(num.getFirst().equals(ein)) ? plur : unitWord;
	}

	@Override
	public Word pluraliseUnit() {

		WordBuilder builder = word.toBuilder();
		builder = nonNull(word.quint()) ?

				builder.quint(pluralise(word.quint())) : builder;

		builder = nonNull(word.quadr()) ? builder.quadr(pluralise(word.quadr())) : builder;

		builder = nonNull(word.trill()) ? builder.trill(pluralise(word.trill())) : builder;

		builder = nonNull(word.bill()) ?

				builder.bill(pluralise(word.bill())) : builder;

		builder = nonNull(word.mill()) ?

				builder.mill(pluralise(word.mill())) : builder;

		builder = nonNull(word.thou()) ? builder.thou(word.thou()) : builder;

		builder = nonNull(word.hund()) ? builder.hund(word.hund()) : builder;

		return builder.build();

	}

	@Override
	public Word pluraliseEin() {
		WordBuilder builder = word.toBuilder();
		return nonNull(word.hund()) ? builder.hund(word.hund() + "s").build() : builder.hund(word.hund()).build();

	}

	@Override
	public Word concatThouHund() {

		WordBuilder builder = word.toBuilder();
		var bword = nonNull(word.thou()) ? builder.thou(word.thou().replaceAll(SPACE, EMPTY).toLowerCase()).build()
				: word;

		// concat thou and hund. set hund empty.
		return nonNull(word.hund()) ? bword.toBuilder().thou(bword.thou() + bword.hund()).hund(EMPTY).build() : bword;

	}

}