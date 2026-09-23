package org.desz.longtoword.conversion.results;

import static java.util.Objects.isNull;
import static org.apache.commons.lang3.StringUtils.SPACE;
import static org.apache.commons.lang3.StringUtils.normalizeSpace;

import lombok.Builder;

@Builder(toBuilder = true)
public record Word(String quint, String quadr, String trill, String bill, String mill, String thou, String hund)

{
	@Override
	public String toString() {
		var sb = new StringBuilder();
		sb = !isNull(quint) ? sb.append(quint + SPACE) : sb;
		sb = !isNull(quadr) ? sb.append(quadr + SPACE) : sb;
		sb = !isNull(trill) ? sb.append(trill + SPACE) : sb;
		sb = !isNull(bill) ? sb.append(bill + SPACE) : sb;
		sb = !isNull(mill) ? sb.append(mill + SPACE) : sb;
		sb = !isNull(thou) ? sb.append(thou + SPACE) : sb;
		sb = !isNull(hund) ? sb.append(hund) : sb;

		return normalizeSpace(sb.toString());

	}
}
