package ar.edu.unju.fi.controller;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
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
import ar.edu.unju.fi.entity.Testimonio;
import ar.edu.unju.fi.entity.Usuario;
import ar.edu.unju.fi.service.ITestimonioService;
import ar.edu.unju.fi.service.IUsuarioService;
import jakarta.validation.Valid;




@Controller
@RequestMapping("/testimonio")
public class TestimonioController {
	
	@Autowired
	private ITestimonioService testimonioService;
	@Autowired
	private IUsuarioService usuarioService;
	/**
	* Método que obtiene listado de testimonios.
	* @return vista "testimonios".
	*/
	@GetMapping("/listado/{admin}")
	public String getTestimonioPage(@PathVariable(value="accion")String admin,Model model) {
		model.addAttribute("testimonios", testimonioService.getLista());
		return "testimonios";
	}
	@GetMapping("/verificar/{accion}")
	public String getIngresoUsuarioPage(@PathVariable(value="accion")Long accion, Model model) {
		model.addAttribute("accion", accion);
		return "testimonio_accion";
	}
	@PostMapping("/validar")
	public ModelAndView getIngresarFormularioTestimonioPage(@RequestParam(name="clave") Long clave,@RequestParam(name="accion") Long accion) {
		ModelAndView modelAndView = new ModelAndView();
		Usuario usuarioEncontrado = usuarioService.getBy(clave);
		System.out.println("Texto: "+accion);
		if(usuarioEncontrado!=null) {
			if(accion == 0) {
				modelAndView.addObject("id", usuarioEncontrado.getId());
				modelAndView.setViewName("redirect:/testimonio/nuevo/{id}");
			}
			else {
				
				if(accion > 0) {
					Testimonio testimonioEncontrado = testimonioService.getBy(accion);
					if(usuarioEncontrado.getId()==testimonioEncontrado.getUsuario().getId()) {
						modelAndView.addObject("id", accion);
						modelAndView.setViewName("redirect:/testimonio/modificar/{id}");
					}
					else {
						modelAndView.setViewName("redirect:/testimonio/listado");
					}
				}
				else {
					accion = accion * -1;
					Testimonio testimonioEncontrado = testimonioService.getBy(accion);
					if(usuarioEncontrado.getId()==testimonioEncontrado.getUsuario().getId()) {
						modelAndView.addObject("id", accion);
						modelAndView.setViewName("redirect:/testimonio/eliminar/{id}");
					}
					else {
						modelAndView.setViewName("redirect:/testimonio/listado");
					}
				}
			}
		}
		else {
			modelAndView.setViewName("redirect:/testimonio/listado");
		}
		return modelAndView;
	}
	/**
	* Método del formulario para la creación de un nuevo testimonio.
	* @return vista "nuevo_testimonio".
	*/
	@GetMapping("/nuevo/{id}")
	public String getNuevoTestimonioPage(Model model, @PathVariable(value="id")Long id) {
		Usuario usuarioEncontrado = usuarioService.getBy(id);
		Testimonio testimonio = testimonioService.getTestimonio();
		testimonio.setUsuario(usuarioEncontrado);
		model.addAttribute("testimonio", testimonio);
		model.addAttribute("edicion", false);
		return "nuevo_testimonio";
	}
	/**
	* Método para guardar un nuevo testimonio.
	* @return ModelAndView con la vista: "testimonios" o "nuevo_testimonio".
	*/
	@PostMapping("/guardar")
	public ModelAndView guardarTestimonio(@Valid @ModelAttribute("testimonio")Testimonio testimonio, BindingResult result) {
		ModelAndView modelAndView = new ModelAndView("testimonios");
		if(result.hasErrors()) {
			modelAndView.setViewName("nuevo_testimonio");
			modelAndView.addObject("testimonio", testimonio);
			return modelAndView;
		}
		testimonio.setFecha(LocalDate.now());
		testimonio.setEstado(true);
		testimonioService.guardar(testimonio);
		modelAndView.addObject("testimonios",testimonioService.getLista());
		return modelAndView;
	}
	/**
	* Método obtener el formulario de modificación de un testimonio.
	* @return La vista "nuevo_producto".
	*/
	@GetMapping("/modificar/{id}")
	public String getModificarTestimonioPage(Model model, @PathVariable(value="id")Long id) {
		model.addAttribute("testimonio", testimonioService.getBy(id));
		model.addAttribute("edicion", true);
		return "nuevo_testimonio";
	}
	/**
	* Método para modificar un testimonio existente.
	* @return vista "testimonios" mediante la redirección.
	*/
	@PostMapping("/modificar")
	public String modificarTestimonio(@Valid @ModelAttribute("testimonio")Testimonio testimonio, BindingResult result, Model model) {
		if(result.hasErrors()) {
			model.addAttribute("edicion", true);
			return "nuevo_testimonio";
		}
		testimonio.setFecha(LocalDate.now());
		testimonioService.modificar(testimonio);
		return "redirect:/testimonio/listado";
	}
	/**
	* Método para eliminar un testimonio.
	* @return vista "listado" mediante la redirección.
	*/
	@GetMapping("/eliminar/{id}")
	public String eliminarTestimonio(@PathVariable(value="id")Long id) {
		for(Testimonio test : testimonioService.getLista()) {
			if(test.getId()==id) {
				testimonioService.eliminar(test);
				break;
			}
		}
		return "redirect:/testimonio/listado";
	}
}
