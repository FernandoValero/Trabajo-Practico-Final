package ar.edu.unju.fi.controller;

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
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unju.fi.entity.Ingrediente;
import ar.edu.unju.fi.entity.Usuario;
import ar.edu.unju.fi.service.IIngredienteService;
import ar.edu.unju.fi.service.IUsuarioService;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/ingrediente")
public class IngredienteController {
	
	@Autowired
	private IUsuarioService usuarioService;
	@Autowired
	@Qualifier("ingredienteServiceMysql")
	private IIngredienteService ingredienteService;
	
	
	/**
	 * Método para Validar un usuario segun su id.
	 * En caso de que no se encuentre el usuario o de que no sea admin regresa a la pagina de login_receta_ingrediente
	 * Si encuentra el usuario devuelve la página ingredientes para poder modificarla.
	 */
	@GetMapping("/validacion")
	public String ValidacionDeUsuarioPage(@RequestParam(value = "id") Long id, Model model) {
		Usuario usuarioBuscado = usuarioService.getByIdAndAdmin(id, true);
		boolean entidad = true;
		model.addAttribute("entidad", entidad);
		if (usuarioBuscado != null) {
			model.addAttribute("ingredientes", ingredienteService.getListaIngrediente());
			return "ingredientes";
		} else {
			model.addAttribute("existeUsuario", false);
			return "login_receta_ingrediente";
		}
	}
	
	
	@GetMapping("/gestion")
	public String getListaAlRecetasPage(Model model) {
		boolean entidad = true;
		model.addAttribute("entidad", entidad);
		model.addAttribute("existeUsuario", true);
		return "login_receta_ingrediente";
	}
	
	
	/**
	 * Método para obtener los ingredientes de la BD y agregarlas al modelo.
	 * Devuelve la página "ingredientes.html".
	 */
	@GetMapping("/ingredientes")
	public String getListaIngredientesPage(Model model) {
		model.addAttribute("ingredientes",ingredienteService.getListaIngrediente());
		return "ingredientes";
	}	
	
	
	/* 
	 * Método para obtener la página de creación de un nuevo ingrediente.
	 * Devuelve la página "nuevo_ingrediente".
	 */
	@GetMapping("/nuevo")
	public String getNuevoIngredientePage(Model model) {
		boolean edicion=false;
		model.addAttribute("ingrediente", ingredienteService.getIngrediente());
		model.addAttribute("edicion", edicion);
		return "nuevo_ingrediente";
	}
	
	
	/*
	 * Método para guardar un nuevo ingrediente. 
	 * Verifica si hay un error, en caso afirmativo se retorna a la página "nuevo_ingrediente" y muestra los errores
	 * Agrega el nuevo ingrediente a la BD de ingredientes usando el método guardar.
	 * Actualiza el modelo con la BD actualizada de ingredientes.
	 * Devuelve el objeto ModelAndView.
	 */
	@PostMapping("/guardar")
	public ModelAndView getGuardarIngredientePage(@Valid @ModelAttribute("ingrediente")Ingrediente ingrediente, BindingResult result) {
		ModelAndView modelView = new ModelAndView("ingredientes");
		if(result.hasErrors()) {
			modelView.setViewName("nuevo_ingrediente") ;
			modelView.addObject("ingrediente", ingrediente);
			return modelView;
		}
		ingredienteService.guardar(ingrediente);
		modelView.addObject("ingredientes", ingredienteService.getListaIngrediente());
		return modelView;
	}

	/*
	 * Método para eliminar un ingrediente.
	 * Busca el ingrediente con el nombre proporcionado en la BD de ingredientes usando el método getBy.
	 * Remueve el ingrediente de la lista de ingredientes usando el método eliminar.
	 * Redirecciona a la página de /ingrediente/ingredientes.
	 */
	@GetMapping("/eliminar/{id}")
	public String EliminarIngrediente(@PathVariable(value="id") Long id) {
		Ingrediente ingredienteEncontrado= ingredienteService.getBy(id);
		ingredienteService.eliminar(ingredienteEncontrado);
		return "redirect:/ingrediente/ingredientes";
	}
}
