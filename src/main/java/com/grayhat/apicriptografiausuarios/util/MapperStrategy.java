/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.grayhat.apicriptografiausuarios.util;

/**
 *
 * @author grayhat
 * @param <T>
 * @param <R>
 */
public interface MapperStrategy<T, R> {

    R mapToDto(T entity);

    T mapToModel(R dto);

}
