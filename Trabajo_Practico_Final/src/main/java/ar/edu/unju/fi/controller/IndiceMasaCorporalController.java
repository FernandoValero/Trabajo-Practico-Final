package ar.edu.unju.fi.controller;

import java.time.LocalDate;

// import org.apache.commons.logging.Log;
// import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ar.edu.unju.fi.entity.IndiceMasaCorporal;
import ar.edu.unju.fi.entity.Usuario;
import ar.edu.unju.fi.service.IIndiceMasaCorporalService;
import ar.edu.unju.fi.service.IUsuarioService;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/servicio/imc")
public class IndiceMasaCorporalController {
	@Autowired
	// @Qualifier("usuarioServiceMysql")
	private IUsuarioService usuarioService;

	@Autowired
	@Qualifier("imcServiceMysql")
	private IIndiceMasaCorporalService imcService;

	// private static final Log LOGGER =
	// LogFactory.getLog(IndiceMasaCorporalController.class);

	/**
	 * Méotod que renderiza la página de ingreso al servicio IMC.
	 * 
	 * @param model tipo Model que se utiliza para pasar datos entre el controller y
	 *              la vista.
	 * @return String que es el nombre de la vista.
	 */
	@GetMapping("")
	public String getIMCPage(Model model) {
		model.addAttribute("titulo", "Login");
		model.addAttribute("existeUsuario", true);

		return "imc";
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
			IndiceMasaCorporal imc = imcService.getImc();
			imc.setUsuario(usuarioBuscado);
			model.addAttribute("imc", imc);
			model.addAttribute("titulo", "Calculadora IMC");
			return "imc_calculadora";
		} else {
			model.addAttribute("existeUsuario", false);
			model.addAttribute("titulo", "Login");
			return "imc";
		}
	}

	/**
	 * méotodo que calcula el IMC y lo almacena en la base de datos.
	 * 
	 * @param imc    parametro del tipo IndiceMasaCorporal.
	 * @param result del tipo BindigResult que se usa para mostrar los errores de
	 *               validación.
	 * @param model  parametro del tipo model que se usa para pasar los datos entre
	 *               el controller y la vista.
	 * @return un String que si es valido retorna la vista imc_resultado, sino
	 *         retorna la vista imc_calculadora.
	 */
	@PostMapping("/calcular")
	public String guardar(@Valid @ModelAttribute("imc") IndiceMasaCorporal imc, BindingResult result, Model model) {
		if (result.hasErrors()) {
			Usuario usuarioBuscado = usuarioService.getBy(imc.getUsuario().getId());

			imc.setUsuario(usuarioBuscado);
			model.addAttribute("imc", imc);
			model.addAttribute("titulo", "Calculadora IMC");
			return "imc_calculadora";
		}

		Usuario usuarioBuscado = usuarioService.getBy(imc.getUsuario().getId());

		imc.setUsuario(usuarioBuscado);
		imc.setFechaIMC(LocalDate.now());
		model.addAttribute("titulo", "IMC | resultado");
		model.addAttribute("resultado", imc.calcularIMC());
		model.addAttribute("imc", imc);
		imcService.guardarImc(imc);
		return "imc_resultado";
	}

	/**
	 * Método que busca un Id de un usuario y de esta forma busca genera un listado
	 * de objetos imc según el usuario.
	 * 
	 * @param id    parametro de tipo Long que representa el id del usuario.
	 * @param model parametro del tipo Model que se usa para pasar datos entre el
	 *              controller y la vista.
	 * @return un String que es la vista que contiene le listado de IMC.
	 */
	@GetMapping("/listado/{id}")
	public String listadoImc(@PathVariable(value = "id") Long id, Model model) {
		Usuario usuarioBuscado = usuarioService.getBy(id);
		model.addAttribute("usuario", usuarioBuscado);
		model.addAttribute("titulo", "IMC | Listado");
		model.addAttribute("listadoImc", imcService.getListaByUsuarioAndEstado(usuarioBuscado, true));
		return "imc_listado";
	}

	/**
	 * Método que renderiza la página de login de gestión de IMC.
	 * 
	 * @param model del tipo Model que se usa para enviar datos entre la vista y el
	 *              controller.
	 * @return la vista de login de gestion de imc.
	 */
	@GetMapping("/gestion")
	public String getGestioIMC(Model model) {
		model.addAttribute("titulo", "Login Gestion");
		model.addAttribute("existeUsuario", true);
		// model.addAttribute("listadoImc", imcService.getListaImc());
		return "login_gestion_imc";
	}

	/**
	 * Método que elimina un imc según su id.
	 * 
	 * @param id parametro del tipo long que representa el id del imc
	 * @return String que representa la vista de gestionde imc.
	 */
	@GetMapping("/gestion/eliminar/{id}")
	public String eliminarIMC(@PathVariable(value = "id") Long id, Model model) {
		imcService.eliminarById(id);
		model.addAttribute("titulo", "Gestion | IMC");
		model.addAttribute("listadoImc", imcService.getListaImc());
		return "gestion_imc";
	}

	/**
	 * Método que verifica si el usuario con el id es un usuario administrador. si es así se dirige al
	 * la vista de gestion de IMC.
	 * 
	 * @param id parametro de tipo Long que representa el id del usuario.
	 * @param model parametro del tipo Model que se usua para transferir datos entre la vista y el controller.
	 * @return la vista de gestión de IMC.
	 */
	@GetMapping("/validar")
	public String buscarUsuarioAdmin(@RequestParam(value = "id") Long id, Model model) {
		Usuario usuarioBuscado = usuarioService.getByIdAndAdmin(id, true);
		if (usuarioBuscado != null) {
			model.addAttribute("titulo", "Gestion | IMC");
			model.addAttribute("listadoImc", imcService.getListaImc());
			return "gestion_imc";
		} else {
			model.addAttribute("existeUsuario", false);
			model.addAttribute("titulo", "Login Gestión");
			return "login_gestion_imc";
		}
	}

}
