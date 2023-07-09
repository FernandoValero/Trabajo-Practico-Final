package ar.edu.unju.fi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ar.edu.unju.fi.entity.Usuario;
import ar.edu.unju.fi.service.IUsuarioService;

@Controller
@RequestMapping("/servicio/pesoIdeal")
public class PesoIdealController {

    @Autowired	
	private IUsuarioService usuarioService;

    /**
	 * Méotod que renderiza la página de login .
	 * 
	 * @param model tipo Model que se utiliza para pasar datos entre el controller y
	 *              la vista.
	 * @return String que es el nombre de la vista.
	 */
    @GetMapping("")
	public String getLoginUsuario(Model model) {
		model.addAttribute("titulo", "Login");
		model.addAttribute("existeUsuario", true);

		return "login_peso_ideal";
	}

    /**
	 * Méotodo que busca un usuario según su Id. si lo encuentra, se dirige a la
	 * vista donde calcula el IMC.
	 * Si no lo encuentra retorna la misma página con un mensaje que el usuairo no
	 * existe.
	 * 
	 * @param id    parametro de tipo Long que es pasado por el requesParam.
	 * @param model parametor del tipo Model que se usa para pasar datos desde el
	 *              controller a la vista.
	 * @return String que es nombre d ela vista si el usuario es distinto de null,
	 *         otro caso retorna la misma vista.
	 */
	@GetMapping("/buscar")
	public String buscarUsuario(@RequestParam(value = "id") Long id, Model model) {
		Usuario usuarioBuscado = usuarioService.getBy(id);
		if (usuarioBuscado != null) {
			model.addAttribute("usuario", usuarioBuscado);
			model.addAttribute("titulo", "Peso Ideal");
			return "peso_ideal";
		} else {
			model.addAttribute("existeUsuario", false);
			model.addAttribute("titulo", "Login");
			return "login_peso_ideal";
		}
	}


    
}
