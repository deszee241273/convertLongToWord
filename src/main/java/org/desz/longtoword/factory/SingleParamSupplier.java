package org.desz.longtoword.factory;

@FunctionalInterface
interface SingleParamSupplier<T, P> {
	T get(P param);
}
