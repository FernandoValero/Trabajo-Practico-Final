package ar.edu.unju.fi.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ar.edu.unju.fi.entity.Usuario;
import ar.edu.unju.fi.service.IUsuarioService;




@Controller
@RequestMapping("/usuario")
public class UsuarioController {

	@Autowired
	private IUsuarioService usuarioService;
	/**
	* Método que obtiene una vista para que el usuario ingrese su id
	* para poder ingresar a la parte de gestion de datos de Usuario.
	* @return vista "usuarioLogin".
	*/
	@GetMapping("/usuarios")
	public String getUsuarioPage(Model model) {
		model.addAttribute("titulo", "Login");
		model.addAttribute("existeUsuario", true);
		return "usuarioLogin";
		
	}
	
	/**
	* Método que obtiene el listado de los usuarios, solamente para los admin.
	* y lista solamente los usarios que no son admin.
	* @return vista "usuarios".
	* Metodo que reenvia al usuario si no es admin.
	* @return vista "usuarioLogin".
	*/
	@GetMapping("/validar")
	public String buscarUsuarioAdmin(@RequestParam(value = "id") Long id, Model model) {
		Usuario usuarioBuscado = usuarioService.getByIdAndAdmin(id, true);
		if (usuarioBuscado != null) {
			model.addAttribute("listado", usuarioService.getByEstadoAndAdmin(true, false));
			return "usuarios";
		} else {
			model.addAttribute("existeUsuario", false);
			model.addAttribute("titulo", "Login Gestión");
			return "usuarioLogin";
		}
	
	}
	
	/**
	* Método que elimina un usuario comun, por su {id}.
	* @return vista "usuarios".
	*/
	@GetMapping("/eliminar/{id}")
	public String EliminarUsuario(@PathVariable(value = "id") Long id, Model model) {
		Usuario usuarioEncontrado = usuarioService.getBy(id);
		usuarioService.eliminar(usuarioEncontrado);
		model.addAttribute("listado", usuarioService.getByEstadoAndAdmin(true, false));
		return "usuarios";
	}
}
