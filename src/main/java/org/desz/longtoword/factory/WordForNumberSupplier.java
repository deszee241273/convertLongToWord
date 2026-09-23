package org.desz.longtoword.factory;

import static java.util.Objects.requireNonNull;
import static java.util.stream.Collectors.joining;

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
import org.desz.longtoword.language.WordCacheData.UK;
import org.desz.longtoword.language.WordCacheData.UkUnit;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * Implements Supplier functionality, Singleton, creates single WordCache per
 * ProvLang.
 *
 * @author des
 *
 */
@Slf4j
public final class WordForNumberSupplier implements UniParamSupplier<WordCache, ProvLang> {

	// WordCache instances per ProvLang
	private final Map<ProvLang, WordCache> wordCacheMap;

	private static final AtomicReference<WordForNumberSupplier> wcRef = new AtomicReference<>();

	/**
	 * singleton.
	 */
	private WordForNumberSupplier() {
		this.wordCacheMap = new ConcurrentHashMap<>(ProvLang.values().length);
	}

	/**
	 *
	 * @return singleton WordForNumberSupplier.
	 */
	public static final WordForNumberSupplier wcInstance() {
		while (true) {
			var current = wcRef.get();
			if (current != null) {
				return current;
			}
			var obj = new WordForNumberSupplier();
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

			return builder.quint(UkUnit.QUINTS.unitRec.word()).quadr(UkUnit.QUADS.unitRec.word())
					.trill(UkUnit.TRILLS.unitRec.word()).bill(UkUnit.BILLS.unitRec.word())
					.mill(UkUnit.MILLS.unitRec.word()).thou(UkUnit.THOUS.unitRec.word())
					.hund(UkUnit.HUNS.unitRec.word()).and(UkUnit.AND.unitRec.word()).numWords(UK.mapping()).build();
		}

		case FR: {
			return builder.quint(FrUnit.QUINTS.unitRec.word()).quadr(FrUnit.QUADS.unitRec.word())
					.trill(FrUnit.TRILLS.unitRec.word()).bill(FrUnit.BILLS.unitRec.word())
					.mill(FrUnit.MILLS.unitRec.word()).thou(FrUnit.THOUS.unitRec.word())
					.hund(FrUnit.HUNS.unitRec.word()).and(FrUnit.AND.unitRec.word()).numWords(FR.mapping()).build();
		}
		case DE: {
			return builder.quint(DeUnit.QUINTS.unitRec.word()).quadr(DeUnit.QUADS.unitRec.word())
					.trill(DeUnit.TRILLS.unitRec.word()).bill(DeUnit.BILLS.unitRec.word())
					.mill(DeUnit.MILLS.unitRec.word()).thou(DeUnit.THOUS.unitRec.word())
					.hund(DeUnit.HUNS.unitRec.word()).and(DeUnit.AND.unitRec.word()).numWords(DE.mapping()).build();
		}

		case NL: {
			return builder.quint(NlUnit.QUINTS.unitRec.word()).quadr(NlUnit.QUADS.unitRec.word())
					.trill(NlUnit.TRILLS.unitRec.word()).bill(NlUnit.BILLS.unitRec.word())
					.mill(NlUnit.MILLS.unitRec.word()).thou(NlUnit.THOUS.unitRec.word())
					.hund(NlUnit.HUNS.unitRec.word()).and(NlUnit.AND.unitRec.word()).numWords(NL.mapping()).build();
		}

		}

		return builder.build();
	}

	@Override
	public WordCache get(ProvLang pl) {
		requireNonNull(pl, "ProvLang argument null.");
		var res = wordCacheMap.computeIfAbsent(pl, _ -> cache(pl));
		var pls = wordCacheMap.keySet().stream().map(ProvLang::name).collect(joining(", "));
		log.info(String.format("Cached %s.", pls));
		return res;
	}
}
