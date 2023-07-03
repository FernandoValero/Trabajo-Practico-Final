package ar.edu.unju.fi.repository;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ar.edu.unju.fi.entity.Testimonio;


@Repository
/**
 * Interfaz que define las operaciones de acceso a datos para la entidad Testimonio.
 */
public interface ITestimonioRepository extends CrudRepository<Testimonio, Long> {
	public List<Testimonio> findByEstado(boolean estado);
}
