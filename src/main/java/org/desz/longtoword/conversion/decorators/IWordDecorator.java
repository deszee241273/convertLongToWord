package org.desz.longtoword.conversion.decorators;

public interface IWordDecorator<T> {

	T pluraliseEin();

	T pluraliseUnit();

	T concatThouHund();

}
