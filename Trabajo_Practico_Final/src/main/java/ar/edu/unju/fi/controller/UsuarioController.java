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
	
	@GetMapping("/usuarios")
	public String getUsuarioPage(Model model) {
		model.addAttribute("titulo", "Login");
		model.addAttribute("existeUsuario", true);
		return "usuarioLogin";
		
	}
	@GetMapping("/validar")
	public String buscarUsuarioAdmin(@RequestParam(value = "id") Long id, Model model) {
		Usuario usuarioBuscado = usuarioService.getByIdAndAdmin(id, true);
		if (usuarioBuscado != null) {
			model.addAttribute("usuario", usuarioService.getLista());
			return "usuarios";
		} else {
			model.addAttribute("existeUsuario", false);
			model.addAttribute("titulo", "Login Gestión");
			return "usuarioLogin";
		}
	}

	@GetMapping("/listado/{admin}")
	public String getUsuarioPage(@PathVariable(value="admin")String admin, Model model) {
		boolean accion = admin.equals("admin");
		model.addAttribute("accion", accion);
		model.addAttribute("usuarios", usuarioService.getLista());
		return "usuarios";
	}
	@GetMapping("/gestion/eliminar/{id}")
	public String EliminarUsuario(@PathVariable(value = "id") Long id) {
		Usuario usuarioEncontrado = usuarioService.getBy(id);
		usuarioService.eliminar(usuarioEncontrado);
		return "redirect:/usuario/usuarios";
	}
}
