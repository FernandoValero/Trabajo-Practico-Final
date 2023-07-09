package ar.edu.unju.fi.repository;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ar.edu.unju.fi.entity.Ingrediente;


@Repository
public interface IIngredienteRepository extends CrudRepository<Ingrediente, Long> {
	
	/*
	 * Método para devolver ingredientes de acuerdo a su estado.
	 */
	public List<Ingrediente> findByEstado(boolean estado);
}
