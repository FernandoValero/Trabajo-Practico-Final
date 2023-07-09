package ar.edu.unju.fi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ar.edu.unju.fi.entity.Usuario;
import ar.edu.unju.fi.service.IUsuarioService;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/registro")
public class RegistroController {

	@Autowired
	// @Qualifier("usuarioServiceMysql")
	private IUsuarioService usuarioService;

	@GetMapping("/formulario")
	public String getPaginaRegistros(Model model, @RequestParam(value = "perfil", required = false) String perfil) {
		Usuario usuario = usuarioService.getUsuario();
		if (usuario != null && perfil != null && perfil.equals("Modificar")) {
			model.addAttribute("edicion", true);
			model.addAttribute("perfil", "Modificar");
		} else {
			model.addAttribute("edicion", false);
			model.addAttribute("perfil", "Registrarme");
		}
		model.addAttribute("usuario", usuario);
		return "registros";
	}

	@PostMapping("/enviar")
	public String enviarUsuario(@Valid @ModelAttribute("usuario") Usuario usuario, BindingResult result, Model model) {
		if (result.hasErrors()) {
			model.addAttribute("edicion", false);
			return "registros";
		}
		usuarioService.guardar(usuario);
		Long idUsuario = usuario.getId(); // Obtener el ID del usuario guardado
		model.addAttribute("guardadoExitoso", true);
		model.addAttribute("idUsuario", idUsuario); // Agregar el ID del usuario al modelo
		return "registros";
	}

	@GetMapping("/login")
	public String getPaginaLogin() {
		return "ingreso";
	}

	@GetMapping("/buscar")
	public String getBuscarUsuarioPage(Model model, @RequestParam(value = "id") Long id) {
		Usuario usuarioBuscado = usuarioService.getBy(id);
		if (usuarioBuscado != null) {
			model.addAttribute("usuario", usuarioBuscado);
			model.addAttribute("perfil", "Modificar");
			return "registros";
		}
		return "ingreso";
	}

	@GetMapping("/editar/{id}")
	public String getEditarSucursalPage(Model model, @ModelAttribute("usuario") Usuario usuario) {
		boolean edicion = true;
		model.addAttribute("edicion", edicion);
		return "registro";
	}

	@PostMapping("/editar/{id}")
	public String editarUsuario(@Valid @ModelAttribute("usuario") Usuario usuario, BindingResult result, Model model) {
		if (result.hasErrors()) {
			model.addAttribute("edicion", true);
			return "registros";
		}
		usuarioService.guardar(usuario);
		model.addAttribute("guardadoExitoso", true);
		return "registros";
	}

}
