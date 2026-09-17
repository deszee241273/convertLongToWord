package org.desz.longtoword.conversion.decorators;

import static org.desz.longtoword.language.WordCacheData.DE.ONE;
import static org.desz.longtoword.language.WordCacheData.DeUnit.QUINTS;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.desz.longtoword.conversion.results.Word;
import org.desz.longtoword.factory.WordCacheSupplier;
import org.desz.longtoword.language.ProvLang;
import org.desz.longtoword.language.WordCache;
import org.junit.jupiter.api.Test;

class TestDeDecorator {

	static WordCache wordCache = WordCacheSupplier.wcInstance().get(ProvLang.DE);

	@Test
	void test_pluralise_unit_rule() {

		var exp = ONE.numWord.word() + QUINTS.unitRec.word().toLowerCase();
		var actual = new DeDecorator(Word.builder().quint(exp).build()).pluraliseUnit().quint();

		assertEquals(exp, actual, "Expected ein trillion");

	}

	@Test
	void test_pluralise_unit_zwei() {
		var input = "zwei trillion";
		var act = new DeDecorator(Word.builder().quint(input).build()).pluraliseUnit();
		assertEquals(input + "en", act.quint().toLowerCase(), "Unit should be pluralised.");

	}

	@Test
	void test_pluralise_hundreth() {

		assertEquals(ONE.numWord.word() + "s",
				new DeDecorator(Word.builder().hund("ein").build()).pluraliseEin().hund(), "expected eins");

	}

	@Test
	void test_combine_thou_and_hund() {

		assertEquals("neunhundertneunundneunzigtausendneunhundertneunundneunzig", new DeDecorator(
				Word.builder().thou("neunhundertneunundneunzigtausend").hund("neunhundertneunundneunzig").build())
				.concatThouHund().thou());

	}

}
