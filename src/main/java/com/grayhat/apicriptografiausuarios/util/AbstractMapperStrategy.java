package com.grayhat.apicriptografiausuarios.util;

import java.util.function.Function;

/**
 *
 * @author grayhat
 * @param <T> Parametro de la clase origen desde la cual estamos mapeando los
 * datos
 * @param <R> Parametro de la clase de destino a la cual estamos mapeando los
 * datos
 */
public abstract class AbstractMapperStrategy<T, R> implements MapperStrategy<T, R> {

    private final Function<T, R> mapToDtoFunction;
    private final Function<R, T> mapToModelFunction;

    public AbstractMapperStrategy(Function<T, R> mapToDtoFunction, Function<R, T> mapToModelFunction) {
        this.mapToDtoFunction = mapToDtoFunction;
        this.mapToModelFunction = mapToModelFunction;
    }

    @Override
    public R mapToDto(T entity) {
        return mapToDtoFunction.apply(entity);
    }

    @Override
    public T mapToModel(R dto) {
        return mapToModelFunction.apply(dto);
    }
}
