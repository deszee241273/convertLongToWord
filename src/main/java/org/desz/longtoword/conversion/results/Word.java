package org.desz.longtoword.conversion.results;

import lombok.Builder;

@Builder(toBuilder = true)
public record Word(String quint, String quadr, String trill, String bill, String mill, String thou, String hund)

{
	@Override
	public String toString() {
		var builder = new StringBuilder();

		if (quint != null) {
			builder.append("quintillion=").append(quint).append(", ");
		}
		if (quadr != null) {
			builder.append("quadrillion=").append(quadr).append(", ");
		}
		if (trill != null) {
			builder.append("trillion=").append(trill).append(", ");
		}
		if (bill != null) {
			builder.append("billion=").append(bill).append(", ");
		}
		if (mill != null) {
			builder.append("million=").append(mill).append(", ");
		}
		if (thou != null) {
			builder.append("thousand=").append(thou).append(", ");
		}
		if (hund != null) {
			builder.append("hundred=").append(hund);
		}

		return builder.toString();
	}

}
