package org.desz.longtoword.factory;

import static java.util.Objects.requireNonNull;
import static java.util.stream.Collectors.toUnmodifiableMap;
import static java.util.stream.Stream.of;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicReference;

import org.desz.longtoword.language.ProvLang;
import org.desz.longtoword.language.WordCache;
import org.desz.longtoword.language.WordCacheData.DE;
import org.desz.longtoword.language.WordCacheData.DeUnit;
import org.desz.longtoword.language.WordCacheData.FR;
import org.desz.longtoword.language.WordCacheData.FrUnit;
import org.desz.longtoword.language.WordCacheData.NL;
import org.desz.longtoword.language.WordCacheData.NlUnit;
import org.desz.longtoword.language.WordCacheData.NumWord;
import org.desz.longtoword.language.WordCacheData.UK;
import org.desz.longtoword.language.WordCacheData.UkUnit;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * Implements Supplier functionality, uses Atomicity to ensure Singleton
 * WordCache. creates WordCache, .
 *
 * @author des
 *
 */
@Slf4j
public final class WordCacheSupplier implements SingleParamSupplier<WordCache, ProvLang> {

	// caches WordCache instances per ProvLang
	private final Map<ProvLang, WordCache> wordCacheMap;

	private static final AtomicReference<WordCacheSupplier> wcRef = new AtomicReference<>();

	/**
	 * singleton.
	 */
	private WordCacheSupplier() {
		this.wordCacheMap = new ConcurrentHashMap<>(ProvLang.values().length);
	}

	/**
	 *
	 * @return singleton instance.
	 */
	public static final WordCacheSupplier wcInstance() {
		while (true) {
			var current = wcRef.get();
			if (current != null) {
				return current;
			}
			var obj = new WordCacheSupplier();
			if (wcRef.compareAndSet(null, obj)) {
				return obj;
			}
		}

	}

	private WordCache cache(final ProvLang pl) {

		var builder = WordCache.builder();
		builder.id(pl.name());

		switch (pl) {

		case UK: {

			return builder.quintn(UkUnit.QUINTS.unitRec.word()).quadrn(UkUnit.QUADS.unitRec.word())
					.trilln(UkUnit.TRILLS.unitRec.word()).billn(UkUnit.BILLS.unitRec.word())
					.milln(UkUnit.MILLS.unitRec.word()).thoud(UkUnit.THOUS.unitRec.word())
					.hund(UkUnit.HUNS.unitRec.word()).and(UkUnit.AND.unitRec.word())
					.numWords(
							of(UK.values()).map(o -> o.numWord).collect(toUnmodifiableMap(NumWord::num, NumWord::word)))
					.build();
		}

		case FR: {
			return builder.quintn(FrUnit.QUINTS.unitRec.word()).quadrn(FrUnit.QUADS.unitRec.word())
					.trilln(FrUnit.TRILLS.unitRec.word()).billn(FrUnit.BILLS.unitRec.word())
					.milln(FrUnit.MILLS.unitRec.word()).thoud(FrUnit.THOUS.unitRec.word())
					.hund(FrUnit.HUNS.unitRec.word()).and(FrUnit.AND.unitRec.word())
					.numWords(
							of(FR.values()).map(o -> o.numWord).collect(toUnmodifiableMap(NumWord::num, NumWord::word)))
					.build();
		}
		case DE: {
			return builder.quintn(DeUnit.QUINTS.unitRec.word()).quadrn(DeUnit.QUADS.unitRec.word())
					.trilln(DeUnit.TRILLS.unitRec.word()).billn(DeUnit.BILLS.unitRec.word())
					.milln(DeUnit.MILLS.unitRec.word()).thoud(DeUnit.THOUS.unitRec.word())
					.hund(DeUnit.HUNS.unitRec.word()).and(DeUnit.AND.unitRec.word())
					.numWords(
							of(DE.values()).map(o -> o.numWord).collect(toUnmodifiableMap(NumWord::num, NumWord::word)))
					.build();
		}

		case NL: {
			return builder.quintn(NlUnit.QUINTS.unitRec.word()).quadrn(NlUnit.QUADS.unitRec.word())
					.trilln(NlUnit.TRILLS.unitRec.word()).billn(NlUnit.BILLS.unitRec.word())
					.milln(NlUnit.MILLS.unitRec.word()).thoud(NlUnit.THOUS.unitRec.word())
					.hund(NlUnit.HUNS.unitRec.word()).and(NlUnit.AND.unitRec.word())
					.numWords(
							of(NL.values()).map(o -> o.numWord).collect(toUnmodifiableMap(NumWord::num, NumWord::word)))
					.build();
		}

		}

		return builder.build();
	}

	@Override
	public WordCache get(ProvLang pl) {
		requireNonNull(pl, "ProvLang argument null.");
		var res = wordCacheMap.computeIfAbsent(pl, _ -> cache(pl));
		log.info(String.format("Cached %s. Cache size %d", pl.name(), this.wordCacheMap.size()));
		return res;
	}
}
