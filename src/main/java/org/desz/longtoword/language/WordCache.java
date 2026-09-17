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
public record WordCache(String id, String quintn, String quadrn, String trilln, String billn, String milln,
		String thoud, String hund, String and, Map<String, String> numWords) {

	/**
	 * @param num the key.
	 * @return the value.
	 */
	public Optional<String> wordForNbr(final int num) {
		return Optional.ofNullable(numWords.get(String.valueOf(num).toLowerCase()));
	}

}
