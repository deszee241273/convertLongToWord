package org.desz.longtoword.conversion.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.desz.longtoword.conversion.service.LongToWordService;
import org.desz.longtoword.exceptions.ConversionException;
import org.desz.longtoword.language.ProvLang;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TestLongToWordServiceUk {
	private static final String MAX_INT = "two billion one hundred and forty seven million four hundred and eighty three thousand six hundred and forty seven";

	private LongToWordService longToWordService;

	@BeforeEach
	void init() {
		longToWordService = new LongToWordService();

	}

	@Test
	void test12123113() throws ConversionException {
		assertEquals("twelve million one hundred and twenty three thousand one hundred and thirteen",
				longToWordService.convert(12123113L, ProvLang.UK));
	}

	@Test
	final void test_zero_uk() throws ConversionException {
		assertEquals("zero", longToWordService.convert(0L, ProvLang.UK));
	}

	@Test
	final void test_1_uk() throws ConversionException {
		assertEquals("one", longToWordService.convert(1L, ProvLang.UK));
	}

	@Test
	void test_max_uk() throws ConversionException {
		assertEquals(MAX_INT, longToWordService.convert(2147483647L, ProvLang.UK));

	}

	@Test
	final void test_15_uk() throws ConversionException {

		assertEquals("fifteen", longToWordService.convert(15L, ProvLang.UK));
	}

	@Test
	final void test23() throws ConversionException {

		assertEquals("twenty three", longToWordService.convert(23L, ProvLang.UK));
	}

	@Test
	final void test100() throws ConversionException {

		assertEquals("one hundred", longToWordService.convert(100L, ProvLang.UK));
	}

	@Test
	final void test101() throws ConversionException {

		assertEquals("one hundred and one", longToWordService.convert(101L, ProvLang.UK));
	}

	@Test
	final void test123() throws ConversionException {

		assertEquals("one hundred and twenty three", longToWordService.convert(123L, ProvLang.UK));
	}

	@Test
	final void test123456() throws ConversionException {

		assertEquals("one hundred and twenty three thousand four hundred and fifty six",
				longToWordService.convert(123456L, ProvLang.UK));
	}

	@Test
	final void test1000000() throws ConversionException {

		assertEquals("one million", longToWordService.convert(1000000L, ProvLang.UK));
	}

	@Test
	final void test1000() throws ConversionException {

		assertEquals("one thousand", longToWordService.convert(1000L, ProvLang.UK));
	}

	@Test
	final void test10000() throws ConversionException {

		assertEquals("ten thousand", longToWordService.convert(10000L, ProvLang.UK));
	}

	@Test
	final void test10099() throws ConversionException {

		assertEquals("ten thousand ninety nine", longToWordService.convert(10099L, ProvLang.UK));
	}

	@Test
	final void test10000000() throws ConversionException {

		assertEquals("ten million", longToWordService.convert(10000000L, ProvLang.UK));
	}

	@Test
	final void test10000001() throws ConversionException {

		assertEquals("ten million one", longToWordService.convert(10000001L, ProvLang.UK));
	}

	@Test
	final void test100000() throws ConversionException {

		assertEquals("one hundred thousand", longToWordService.convert(100000L, ProvLang.UK));
	}

	@Test
	final void test100000000() throws ConversionException {

		assertEquals("one hundred million", longToWordService.convert(100000000L, ProvLang.UK));
	}

	@Test
	final void test1100000() throws ConversionException {

		assertEquals("one million one hundred thousand", longToWordService.convert(1100000L, ProvLang.UK));
	}

}
