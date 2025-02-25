package com.grayhat.apicriptografiausuarios.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

/**
 *
 * @author grayhat
 * @param <T> Parametro de la clase que se va a implementar con los métodos crud
 * @param <ID> Parametro del id para utilizar en los metodos crud
 */
@NoRepositoryBean
public interface IGenericRepo<T, ID> extends JpaRepository<T, Long> {

}
