/**
 *
 */
package org.desz.longtoword.language;

import java.util.Map;
import java.util.Optional;

import lombok.Builder;

/**
 * @author des
 *
 *         record for units and number-word mappings. WordCacheSupplier creates
 *         and caches instances.
 *
 */

@Builder
public record WordCache(String id, String quint, String quadr, String trill, String bill, String mill,
		String thou, String hund, String and, Map<String, String> numWords) {

	/**
	 * @param num the key.
	 * @return the value.
	 */
	public Optional<String> wordForNbr(final int num) {
		return Optional.ofNullable(numWords.get(String.valueOf(num).toLowerCase()));
	}

}
