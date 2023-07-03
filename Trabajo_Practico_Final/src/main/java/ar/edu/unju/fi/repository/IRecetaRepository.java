package ar.edu.unju.fi.repository;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ar.edu.unju.fi.entity.Receta;


@Repository
public interface IRecetaRepository extends CrudRepository<Receta, Long> {
	
	/*
	 * Método para devolver recetas de acuerdo a su estado.
	 */
	public List<Receta> findByEstado(boolean estado);
}
