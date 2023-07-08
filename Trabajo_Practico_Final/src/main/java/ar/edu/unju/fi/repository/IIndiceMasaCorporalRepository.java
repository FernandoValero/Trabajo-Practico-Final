package ar.edu.unju.fi.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ar.edu.unju.fi.entity.IndiceMasaCorporal;
import java.util.List;
import ar.edu.unju.fi.entity.Usuario;


@Repository
public interface IIndiceMasaCorporalRepository extends CrudRepository<IndiceMasaCorporal, Long> {
    
    /**
     * Método que define la busqueda de una entidad IMC a 
     * partir de su estado.
     */
    List<IndiceMasaCorporal> findByEstado(boolean estado);

    /**
     * Métod que define la busqueda de una entidad IMC a 
     * partir de usuario y estado.
     */
    List<IndiceMasaCorporal> findByUsuarioAndEstado(Usuario usuario, boolean estado);
}
