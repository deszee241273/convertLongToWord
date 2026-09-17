package org.desz.longtoword.language;

/**
 * ProvLang for language.
 *
 */

public enum ProvLang {

	UK(new LangRec("UK", "UK-English", true)), FR(new LangRec("FR", "Français", true)),
	DE(new LangRec("DE", "Deutsch", true)), NL(new LangRec("NL", "Nederlandse", true));

	public record LangRec(String code, String desc, boolean isValid) {
	}

	public final LangRec langRec;

	ProvLang(LangRec langRec) {
		this.langRec = langRec;
	}

}