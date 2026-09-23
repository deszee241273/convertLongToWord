package org.desz.longtoword.conversion.decorators;

public interface IDeWordDecorator<T> {

	T pluraliseEin();

	T pluraliseUnit();

	T concatThouHund();

}
