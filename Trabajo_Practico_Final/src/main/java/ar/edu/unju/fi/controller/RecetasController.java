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
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unju.fi.entity.Receta;
import ar.edu.unju.fi.service.IIngredienteService;
import ar.edu.unju.fi.service.IRecetaService;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/receta")
public class RecetasController {
	
	@Autowired
	@Qualifier("recetaServiceMysql")
	private IRecetaService recetaService;
	@Autowired
	private IIngredienteService ingredienteService;
	
	
	/**
	 * Método para obtener las recetas de la BD segun una categoria y agregarlas al modelo.
	 * Devuelve la página "recetas.html".
	 */
	@GetMapping("/validacion")
	public String getValidacionRecetasPage() {
		
		return "";
	}
	
	
	/**
	 * Método para obtener las recetas de la BD segun una categoria y agregarlas al modelo.
	 * Devuelve la página "recetas.html".
	 */
	@GetMapping("/recetas/{categoria}")
	public String getListaRecetasPage(Model model, @PathVariable(value="categoria" ) String categoria) {
		model.addAttribute("recetas",recetaService.getListaRecetaCategoria(categoria));
		return "recetas";
	}
	
	/**
	 * Método para obtener las recetas de la BD segun una categoria y agregarlas al modelo.
	 * Devuelve la página "recetas.html".
	 */
	@GetMapping("/gestion")
	public String getListaAllRecetasPage(Model model) {
		boolean gestion=true;
		model.addAttribute("recetas",recetaService.getListaReceta());
		model.addAttribute("gestion", gestion);
		return "recetas";
	}
	
	
	/**
	 * Método para obtener las recetas de la BD y agregarlas al modelo.
	 * Devuelve la página "recetas.html".
	 */
	@GetMapping("/categorias")
	public String getListaRecetasCategoriaPage(Model model) {
		model.addAttribute("recetas",recetaService.getListaReceta());
		return "recetas_categoria";
	}
	
	
	
	/* 
	 * Método para obtener la página de creación de una nueva receta.
	 * Devuelve la página "nueva_receta".
     */
	@GetMapping("/nuevo")
	public String getNuevaRecetaPage(Model model) {
		boolean edicion=false;
		model.addAttribute("ingredientes",ingredienteService.getListaIngrediente());
		model.addAttribute("receta", recetaService.getReceta());
		model.addAttribute("edicion", edicion);
		return "nueva_receta";
	}
	
	
	/*
	 * Método para guardar una nueva receta. 
	 * Verifica si hay un error, en caso afirmativo se retorna a la página "nueva_receta" y muestra los errores
	 * Agrega la nueva receta a la BD de recetas usando el método guardar.
	 * Actualiza el modelo con la BD actualizada de recetas.
	 * Devuelve el objeto ModelAndView.
	 */
	@PostMapping("/guardar")
	public ModelAndView getGuardarRecetaPage(@Valid @ModelAttribute("receta") Receta receta, BindingResult result) {
		ModelAndView modelView = new ModelAndView("recetas");
		if(result.hasErrors()) {
			 modelView.addObject("ingredientes",ingredienteService.getListaIngrediente());
			modelView.setViewName("nueva_receta");
			return modelView;
		}
		recetaService.guardar(receta);
		modelView.addObject("recetas", recetaService.getListaReceta());
		return modelView;
	}

	
	/*
	 * Método para obtener la página de edición de una receta existente.
	 * Busca la receta con el nombre proporcionado en la BD de recetas usando el método getBy.
	 * Establece la receta encontrada y el indicador de "edicion" en el modelo.
	 * Devuelve la página "nueva_receta".
	 */
	@GetMapping("/editar/{id}")
	public String getEditarRecetaPage(Model model, @PathVariable(value="id") Long id) {
		boolean edicion=true;
		Receta recetaEncontrada = recetaService.getBy(id);
		model.addAttribute("ingredientes",ingredienteService.getListaIngrediente());
		model.addAttribute("receta", recetaEncontrada);
		model.addAttribute("edicion", edicion);
		return "nueva_receta";
	}
	
	
	/*
	 * Método para editar una receta existente.
	 * Verifica si hay un error, en caso afirmativo se retorna a la página "nueva_receta" y muestra los errores
	 * Actualiza los atributos de la receta encontrada con los valores proporcionados usando el método editar.
	 * Redirecciona a la página de /receta/recetas.
	 */		
	@PostMapping("/editar")
	public String editarReceta(@Valid @ModelAttribute("receta")Receta receta, BindingResult result,Model model) {
		if(result.hasErrors()) {
			model.addAttribute("edicion", true);
			model.addAttribute("ingredientes",ingredienteService.getListaIngrediente());
			return "nueva_receta";
		}
		recetaService.editar(receta);
		return "redirect:/receta/gestion";
	}

	
	/*
	 * Método para eliminar una receta.
	 * Busca la receta con el nombre proporcionado en la BD de recetas usando el método getBy.
	 * Remueve la receta de la lista de recetas usando el método eliminar.
	 * Redirecciona a la página de /receta/recetas.
	 */
	@GetMapping("/eliminar/{id}")
	public String EliminarReceta(@PathVariable(value="id") Long id) {
		Receta recetaEncontrada= recetaService.getBy(id);
		recetaService.eliminar(recetaEncontrada);
		return "redirect:/receta/gestion";
	}
}
