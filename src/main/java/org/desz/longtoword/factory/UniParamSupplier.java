package org.desz.longtoword.factory;

@FunctionalInterface
interface UniParamSupplier<T, P> {
	T get(P param);
}
