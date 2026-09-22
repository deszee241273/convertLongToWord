/**
 *
 */
package org.desz.longtoword.language;

import static org.apache.commons.lang3.StringUtils.SPACE;

import java.util.Map;
import static java.util.stream.Collectors.toUnmodifiableMap;
import java.util.stream.Stream;

/**
 * @author des
 *
 *         Enums for language, int -> word - number mappings and Units
 *
 */
public final class WordCacheData {

	public record NumWord(String num, String word) {
	}

	public enum NL {
		ZERO(new NumWord("0", "Nul")), ONE(new NumWord("1", "Een")), TWO(new NumWord("2", "Twee")),
		THREE(new NumWord("3", "Drei")), FOUR(new NumWord("4", "Vier")), FIVE(new NumWord("5", "Vijf")),
		SIX(new NumWord("6", "Zes")), SEVEN(new NumWord("7", "Zeven")), EIGHT(new NumWord("8", "Acht")),
		NINE(new NumWord("9", "Negen")), TEN(new NumWord("10", "Tien")), ELEVEN(new NumWord("11", "Elf")),
		TWELVE(new NumWord("12", "Twaalf")), THIRTEEN(new NumWord("13", "Dertein")),
		FOURTEEN(new NumWord("14", "Veertien")), FIFTEEN(new NumWord("15", "Vijftien")),
		SIXTEEN(new NumWord("16", "Zestien")), SEVENTEEN(new NumWord("17", "Zeventien")),
		EIGHTEEN(new NumWord("18", "Achttien")), NINETEEN(new NumWord("19", "Negentien")),
		TWENTY(new NumWord("20", "Twintig")), TWENTYONE(new NumWord("21", "Eenentwintig")),
		TWENTYTWO(new NumWord("22", "Tweeëntwintig")), TWENTYTHREE(new NumWord("23", "Dreieëntwintig")),
		TWENTYFOUR(new NumWord("24", "Vierentwintig")), TWENTYFIVE(new NumWord("25", "Vijfentwintig")),
		TWENTYSIX(new NumWord("26", "Zesentwintig")), TWENTYSEVEN(new NumWord("27", "Zevenentwintig")),
		TWENTYEIGHT(new NumWord("28", "Achtentwintig")), TWENTYNINE(new NumWord("29", "Negenentwintig")),
		THIRTY(new NumWord("30", "Dertig")), FORTY(new NumWord("40", "Veertig")), FIFTY(new NumWord("50", "Vijftig")),
		SIXTY(new NumWord("60", "Zestig")), SEVENTY(new NumWord("70", "Zeventig")),
		EIGHTY(new NumWord("80", "Tachtig")), NINETY(new NumWord("90", "Negentig"));

		public final NumWord numWord;

		NL(NumWord nlrec) {
			this.numWord = nlrec;
		}

		public static Map<String, String> mapping() {
			return Stream.of(NL.values()).map(o -> o.numWord).collect(toUnmodifiableMap(NumWord::num, NumWord::word));

		}

	}

	public enum DE {
		ZERO(new NumWord("0", "Null")), ONE(new NumWord("1", "ein")), TWO(new NumWord("2", "Zwei")),
		THREE(new NumWord("3", "Drei")),

		FOUR(new NumWord("4", "Vier")), FIVE(new NumWord("5", "Fünf")),

		SIX(new NumWord("6", "Sechs")), SEVEN(new NumWord("7", "Sieben")), EIGHT(new NumWord("8", "Acht")),
		NINE(new NumWord("9", "Neun")), TEN(new NumWord("10", "Zehn")), ELEVEN(new NumWord("11", "Elf")),
		TWELVE(new NumWord("12", "Zwölf")), THIRTEEN(new NumWord("13", "Dreizehn")),
		FOURTEEN(new NumWord("14", "Vierzehn")), FIFTEEN(new NumWord("15", "Fünfzehn")),
		SIXTEEN(new NumWord("16", "Sechzehn")), SEVENTEEN(new NumWord("17", "Siebzehn")),
		EIGHTEEN(new NumWord("18", "Achtzehn")), NINETEEN(new NumWord("19", "Neunzehn")),
		TWENTY(new NumWord("20", "Zwanzig")), THIRTY(new NumWord("30", "Dreißig")), FORTY(new NumWord("40", "Vierzig")),
		FIFTY(new NumWord("50", "Fünfzig")), SIXTY(new NumWord("60", "Sechzig")), SEVENTY(new NumWord("70", "Siebzig")),
		EIGHTY(new NumWord("80", "Achtzig")), NINETY(new NumWord("90", "Neunzig"));

		public final NumWord numWord;

		DE(NumWord derec) {
			this.numWord = derec;
		}
		public static Map<String, String> mapping() {
			return Stream.of(DE.values()).map(o -> o.numWord).collect(toUnmodifiableMap(NumWord::num, NumWord::word));
		}
	
	}

	/**
	 * French integer to word
	 *
	 * @author des
	 *
	 */

	public enum FR {
		ZERO(new NumWord("0", "Zéro")), ONE(new NumWord("1", "Un")), TWO(new NumWord("2", "Deux")),
		THREE(new NumWord("3", "Trois")), FOUR(new NumWord("4", "Quatre")), FIVE(new NumWord("5", "Cinq")),
		SIX(new NumWord("6", "Six")), SEVEN(new NumWord("7", "Sept")), EIGHT(new NumWord("8", "Huit")),
		NINE(new NumWord("9", "Neuf")), TEN(new NumWord("10", "Dix")), ELEVEN(new NumWord("11", "Onze")),
		TWELVE(new NumWord("12", "Douze")), THIRTEEN(new NumWord("13", "Treize")),
		FOURTEEN(new NumWord("14", "Quatorze")), FIFTEEN(new NumWord("15", "Quinze")),
		SIXTEEN(new NumWord("16", "Seize")), SEVENTEEN(new NumWord("17", "Dix-sept")),
		EIGHTEEN(new NumWord("18", "Dix-huit")), NINETEEN(new NumWord("19", "Dix-neuf")),
		TWENTY(new NumWord("20", "Vingt")), THIRTY(new NumWord("30", "Trente")), FORTY(new NumWord("40", "Quarante")),
		FIFTY(new NumWord("50", "Cinquante")), SIXTY(new NumWord("60", "Soixante")),
		SEVENTY(new NumWord("70", "Soixante-dix")), EIGHTY(new NumWord("80", "Soixante-vingt")),
		NINETY(new NumWord("90", "Quarante-vingt-dix"));

		public final NumWord numWord;

		FR(NumWord frrec) {
			this.numWord = frrec;
		}

		public static Map<String, String> mapping() {
			return Stream.of(FR.values()).map(o -> o.numWord).collect(toUnmodifiableMap(NumWord::num, NumWord::word));

		}
	}

	public enum UK {
		ZERO(new NumWord("0", "Zero")), ONE(new NumWord("1", "One")),

		TWO(new NumWord("2", "Two")), THREE(new NumWord("3", "Three")), FOUR(new NumWord("4", "Four")),
		FIVE(new NumWord("5", "Five")), SIX(new NumWord("6", "Six")), SEVEN(new NumWord("7", "Seven")),
		EIGHT(new NumWord("8", "Eight")), NINE(new NumWord("9", "Nine")), TEN(new NumWord("10", "Ten")),
		ELEVEN(new NumWord("11", "Eleven")), TWELVE(new NumWord("12", "Twelve")),
		THIRTEEN(new NumWord("13", "Thirteen")), FOURTEEN(new NumWord("14", "Fourteen")),
		FIFTEEN(new NumWord("15", "Fifteen")), SIXTEEN(new NumWord("16", "Sixteen")),
		SEVENTEEN(new NumWord("17", "Seventeen")), EIGHTEEN(new NumWord("18", "Eighteen")),
		NINETEEN(new NumWord("19", "Nineteen")), TWENTY(new NumWord("20", "Twenty")),
		THIRTY(new NumWord("30", "Thirty")), FORTY(new NumWord("40", "Forty")), FIFTY(new NumWord("50", "Fifty")),
		SIXTY(new NumWord("60", "Sixty")), SEVENTY(new NumWord("70", "Seventy")), EIGHTY(new NumWord("80", "Eighty")),
		NINETY(new NumWord("90", "Ninety"));

		public final NumWord numWord;

		UK(NumWord ukrec) {
			this.numWord = ukrec;

		}

		public static Map<String, String> mapping() {
			return Stream.of(UK.values()).map(o -> o.numWord).collect(toUnmodifiableMap(NumWord::num, NumWord::word));

		}

	}

	public record UnitRec(String word) {
	}

	/**
	 * French. https://en.wikipedia.org/wiki/Names_of_large_numbers
	 *
	 */
	public enum FrUnit {
		AND(new UnitRec("et" + SPACE)), QUINTS(new UnitRec(SPACE + "quintillion")),
		QUADS(new UnitRec(SPACE + "quadrillion")), TRILLS(new UnitRec(SPACE + "trillion")),
		BILLS(new UnitRec(SPACE + "milliard")), MILLS(new UnitRec(SPACE + "million")),
		THOUS(new UnitRec(SPACE + "mille")), HUNS(new UnitRec(SPACE + "cent"));

		public final UnitRec unitRec;

		FrUnit(UnitRec frUnit) {
			this.unitRec = frUnit;
		}

	}

	/**
	 * German.
	 *
	 * @author des
	 *
	 */

	public enum DeUnit {

		AND(new UnitRec("und")), QUINTS(new UnitRec(SPACE + "Trillion")), QUADS(new UnitRec(SPACE + "Billiarde")),
		TRILLS(new UnitRec(SPACE + "Billion")), BILLS(new UnitRec(SPACE + "Milliarde")),
		MILLS(new UnitRec(SPACE + "Million")), THOUS(new UnitRec(SPACE + "Tausend")), HUNS(new UnitRec("hundert"));

		public final UnitRec unitRec;

		DeUnit(UnitRec deUnit) {
			this.unitRec = deUnit;
		}

	}

	public enum NlUnit {

		AND(new UnitRec("en")), QUINTS(new UnitRec(SPACE + "triljoen")), QUADS(new UnitRec(SPACE + "biljard")),
		TRILLS(new UnitRec(SPACE + "biljoen")), BILLS(new UnitRec(SPACE + "Milliarde")),
		MILLS(new UnitRec(SPACE + "miljeon")), THOUS(new UnitRec(SPACE + "duizend")), HUNS(new UnitRec("honderd"));

		public final UnitRec unitRec;

		NlUnit(UnitRec nlUnit) {
			this.unitRec = nlUnit;
		}
	}

	public enum UkUnit {

		AND(new UnitRec("and" + SPACE)), QUINTS(new UnitRec(SPACE + "quintillion")),
		QUADS(new UnitRec(SPACE + "quadrillion")), TRILLS(new UnitRec(SPACE + "trillion")),
		BILLS(new UnitRec(SPACE + "billion")), MILLS(new UnitRec(SPACE + "million")),
		THOUS(new UnitRec(SPACE + "thousand")), HUNS(new UnitRec(SPACE + "hundred"));

		public final UnitRec unitRec;

		UkUnit(UnitRec ukUnit) {
			this.unitRec = ukUnit;
		}

	}

}
