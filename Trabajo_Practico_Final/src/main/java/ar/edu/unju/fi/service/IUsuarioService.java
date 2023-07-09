package ar.edu.unju.fi.service;

import java.util.List;
import ar.edu.unju.fi.entity.Usuario;

/**
* Esta interfaz define los métodos para administrar los usuarios.
*/
public interface IUsuarioService {
	
	/**
	* Obtiene la lista de todos los usuarios.
	* @return una lista de usuarios.
	*/
	List<Usuario> getLista();
	
	/**
	*  Guarda un nuevo usuario.
	*  @param usuario: usuario a guardar.
	*/
	void guardar (Usuario usuario);
	
	/**
	*  Modifica un usuario existente.
	*  @param usuario: usuario a modificar.
	*/
	void modificar (Usuario usuario);
	
	/**
	*  Elimina un usuario existente.
	*  @param usuarioEncontrado: usuario a eliminar.
	*/
	void eliminar (Usuario usuarioEncontrado);
	
	/**
	    * Encuentra un usuario de acuerdo a su id.
	    * @param id: identificador del usuario a buscar.
	    * @return el usuario que tenga coincidencia.
	    */
	Usuario getBy(Long id);
	
	/**
	    * Obtiene un nuevo usuario.
	    * @return un objeto usuario.
	    */
	Usuario getUsuario();

	/**
	 	* Obtiene un usuario según su id y su tipo de usuario.
	 	* @return un objeto usuario
	 */
	Usuario getByIdAndAdmin(Long id, boolean admin);
	
}
