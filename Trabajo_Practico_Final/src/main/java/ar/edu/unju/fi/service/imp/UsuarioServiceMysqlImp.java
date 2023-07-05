package ar.edu.unju.fi.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unju.fi.entity.Usuario;
import ar.edu.unju.fi.repository.IUsuarioRepository;
import ar.edu.unju.fi.service.IUsuarioService;

/**
* Implementación de la interfaz IUsuarioService que define los métodos de usuario para la entidad Usuario.
* Utiliza el repositorio IUsuarioRepository para acceder y manipular los datos.
*/
@Service
public class UsuarioServiceMysqlImp implements IUsuarioService {

		@Autowired
		private IUsuarioRepository usuarioRepository;
		
		@Autowired
		private Usuario usuario;
		/**
		* Obtiene la lista de todos los usuarios.
		* @return una lista de usuarios activos.
		*/
		@Override
		public List<Usuario> getLista(){
			return usuarioRepository.findByEstado(true);
		}
		/**
		*  Guardo un nuevo usuario.
		*  @param usuario: usuario a guardar.
		*/
		@Override
		public void guardar(Usuario usuario) {
			usuarioRepository.save(usuario);
		}
		/**
		*  Modifica un usuario existente.
		*  @param usuario: usuario a modificar.
		*/
		@Override
		public void modificar(Usuario usuario) {
			usuarioRepository.save(usuario);
		}
		/**
		*  Elimina un usuario existente.
		*  @param usuarioEncontrado: usuario a eliminar.
		*/
		@Override
		public void eliminar(Usuario usuarioEncontrado) {
			usuarioEncontrado.setEstado(false);
			usuarioRepository.save(usuarioEncontrado);
		}
		/**
		    * Encuentra un usuario de acuerdo a su id.
		    * @param id: identificador del usuario a buscar.
		    * @return el usuario que tenga coincidencia.
		    */
		@Override
		public Usuario getBy(Long id) {
			return usuarioRepository.findById(id).get();
		}
		/**
		    * Obtiene un nuevo usuario.
		    * @return un objeto usuario.
		    */
		@Override
		public Usuario getUsuario() {
			return usuario;
		}
}
