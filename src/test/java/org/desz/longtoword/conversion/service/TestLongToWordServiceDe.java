package org.desz.longtoword.conversion.service;

import static org.desz.longtoword.language.ProvLang.DE;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.desz.longtoword.exceptions.ConversionException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TestLongToWordServiceDe {

	private static final long MAX_INT = 2147483647L;
	private LongToWordService longToWordService;

	@BeforeEach
	void init() {
		longToWordService = new LongToWordService();
	}

	@Test
	void test_long_max() throws ConversionException {

		assertEquals(
				"neun Trillionen zweihundertdreiundzwanzig Billiarden dreihundertzweiundsiebzig Billionen sechsunddreißig Milliarden achthundertvierundfünfzig Millionen siebenhundertfünfundsiebzigtausendachthundertsieben",
				longToWordService.convert(Long.MAX_VALUE, DE));
	}

	@Test
	final void test_max_int() throws ConversionException {

		assertEquals(
				"zwei Milliarden einhundertsiebenundvierzig Millionen vierhundertdreiundachtzigtausendsechshundertsiebenundvierzig",
				longToWordService.convert(MAX_INT, DE));
	}

	@Test
	final void test3147483647() throws ConversionException {

		assertEquals(
				"drei Milliarden einhundertsiebenundvierzig Millionen vierhundertdreiundachtzigtausendsechshundertsiebenundvierzig",
				longToWordService.convert(3147483647L, DE));
	}

	@Test
	final void test23873636() throws ConversionException {

		assertEquals("dreiundzwanzig Millionen achthundertdreiundsiebzigtausendsechshundertsechsunddreißig",
				longToWordService.convert(23873636L, DE));
	}

	@Test
	final void test1000() throws ConversionException {

		assertEquals("eintausend", longToWordService.convert(1000L, DE));
	}

	@Test
	final void test1020() throws ConversionException {

		assertEquals("eintausendzwanzig", longToWordService.convert(1020L, DE));
	}

	@Test
	final void test807() throws ConversionException {

		assertEquals("achthundertsieben", longToWordService.convert(807L, DE));
	}

	@Test
	final void test817() throws ConversionException {

		assertEquals("achthundertsiebzehn", longToWordService.convert(817L, DE));
	}

	@Test
	final void test827() throws ConversionException {

		assertEquals("achthundertsiebenundzwanzig", longToWordService.convert(827L, DE));
	}

	@Test
	final void test10000() throws ConversionException {

		assertEquals("zehntausend", longToWordService.convert(10000L, DE));
	}

	@Test
	final void test100001() throws ConversionException {

		assertEquals("einhunderttausendeins", longToWordService.convert(100001L, DE));
	}

	@Test
	final void test1000001() throws ConversionException {

		assertEquals("ein Million eins", longToWordService.convert(1000001L, DE));
	}

	@Test
	final void test100000007() throws ConversionException {

		assertEquals("einhundert Millionen sieben", longToWordService.convert(100000007, DE));
	}

	@Test
	final void test100000017() throws ConversionException {

		assertEquals("einhundert Millionen siebzehn", longToWordService.convert(100000017L, DE));
	}

	@Test
	final void test100000031() throws ConversionException {

		assertEquals("einhundert Millionen einunddreißig", longToWordService.convert(100000031L, DE));
	}

	@Test
	final void test2387() throws ConversionException {

		assertEquals("zweitausenddreihundertsiebenundachtzig", longToWordService.convert(2387L, DE));
	}

	@Test
	final void test238() throws ConversionException {

		assertEquals("zweihundertachtunddreißig", longToWordService.convert(238L, DE));
	}

	@Test
	final void test99() throws ConversionException {

		assertEquals("neunundneunzig", longToWordService.convert(99L, DE));
	}

	@Test
	final void test19() throws ConversionException {

		assertEquals("neunzehn", longToWordService.convert(19L, DE));
	}

	@Test
	final void test9() throws ConversionException {

		assertEquals("neun", longToWordService.convert(9L, DE));
	}

	@Test
	final void test0() throws ConversionException {

		assertEquals("null", longToWordService.convert(0L, DE));
	}

}
