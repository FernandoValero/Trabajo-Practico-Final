package ar.edu.unju.fi.repository;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ar.edu.unju.fi.entity.Usuario;

@Repository
public interface IUsuarioRepository extends CrudRepository<Usuario,Long>{
	/*
	 * Método para devolver usuarios de acuerdo a su estado.
	 */
	public List<Usuario> findByEstado(boolean estado);


	
	/**
	 * Método que busca un usuario según su Id y su estado de Admin
	 * @param id parametro de tipo long que representa el id del usuario.
	 * @param admin paramero del tipo boolean que representa el valor del tipo de usuario.
	 * @return una lista de objetos del tipo usuario.
	 */
	List<Usuario> findByIdAndAdmin(Long id, boolean admin);
}
