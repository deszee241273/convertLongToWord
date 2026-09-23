package org.desz.longtoword.conversion.service;

import java.util.List;

import org.desz.longtoword.conversion.results.Word;
import org.desz.longtoword.language.WordCache;

public record ScopeParams(Word.WordBuilder builder, WordCache wordCache, List<String> numbers) {

}
