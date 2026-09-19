package org.desz.longtoword.conversion.results;

import lombok.Builder;

@Builder(toBuilder = true)
public record Word(String quint, String quadr, String trill, String bill, String mill, String thou, String hund)

{
	}
