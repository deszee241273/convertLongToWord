package org.desz.longtoword.factory;

import org.desz.longtoword.language.ProvLang;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class TestWordCacheSupplier {

	@Test
	void test_singleton_semantics() {

		var pvl = WordForNumberSupplier.wcInstance();

		Assertions.assertSame(pvl, WordForNumberSupplier.wcInstance());
	}

	@Test
	void test_functional_semantics() {

		Assertions.assertEquals(WordForNumberSupplier.wcInstance().get(ProvLang.DE).id(), ProvLang.DE.name());
	}

}
