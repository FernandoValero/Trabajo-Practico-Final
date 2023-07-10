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
	* Método que obtiene listado de testimonios según los privilegios.
	* @return vista "testimonios".
	*/
	@GetMapping("/listado/{vista}")
	public ModelAndView getTestimonioPage(@PathVariable(value="vista")int vista) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("existeUsuario",true);
		if (vista==1) 
			modelAndView.addObject("vista",true);
		else
			modelAndView.addObject("vista", false);
		modelAndView.addObject("testimonios", testimonioService.getLista());
		modelAndView.setViewName("testimonios");
		return modelAndView;
	}
	/**
	* Método que obtiene formulario de validación de usuario administrador.
	* @return vista "testimonios_accion".
	*/
	@GetMapping("/verificar")
	public String getIngresoUsuarioAdminPage(Model model) {
		model.addAttribute("user", true);
		model.addAttribute("accion", 1);
		return "login_testimonio";
	}
	/**
	* Método que obtiene formulario de validación de usuario común.
	* @return vista "testimonios_accion".
	*/
	@GetMapping("/verificar/{accion}")
	public String getIngresoUsuarioPage(@PathVariable(value="accion")Long accion, Model model) {
		model.addAttribute("accion", accion);
		model.addAttribute("user", false);
		return "login_testimonio";
	}
	/**
	* Método valida al usuario a realizar acciones.
	* @return ModelAndView con la vista: según el acción y usuario.
	*/
	@PostMapping("/validar")
	public ModelAndView getIngresarFormularioTestimonioPage(Model model,@RequestParam(name="clave") Long clave,@RequestParam(name="accion") Long accion, @RequestParam(name="user") boolean user) {
		//accion: determina la acción según el id - negativo =borrar;positivo=modificar;cero=guardar
		ModelAndView modelAndView = new ModelAndView();
		Usuario usuarioEncontrado = usuarioService.getBy(clave);
		if(usuarioEncontrado!=null) {
			if(user) {
				if(usuarioEncontrado.isAdmin())
					modelAndView.setViewName("redirect:/testimonio/listado/1");
				else {
					modelAndView.addObject("existeUsuario", false);
					modelAndView.setViewName("testimonios");
				}
			}
			else {
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
							modelAndView.addObject("existeUsuario", false);
							modelAndView.setViewName("testimonios");
						}
					}
					else {
						Testimonio testimonioEncontrado = testimonioService.getBy(accion*-1);
						if(usuarioEncontrado.isAdmin() || usuarioEncontrado.getId()==testimonioEncontrado.getUsuario().getId()) {
							modelAndView.addObject("id", accion);
							modelAndView.setViewName("redirect:/testimonio/eliminar/{id}");
						}
						else {
							modelAndView.addObject("existeUsuario", false);
							modelAndView.setViewName("testimonios");
						}
					}
				}
			}
		}
		else {
			modelAndView.addObject("existeUsuario", false);
			modelAndView.setViewName("testimonios");
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
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("redirect:/testimonio/listado/0");
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
		return "redirect:/testimonio/listado/0";
	}
	/**
	* Método para eliminar un testimonio.
	* @return vista "listado" mediante la redirección.
	*/
	@GetMapping("/eliminar/{id}")
	public ModelAndView eliminarTestimonio(@PathVariable(value="id")Long id) {
		ModelAndView modelAndView = new ModelAndView("testimonios");
		modelAndView.setViewName("redirect:/testimonio/listado/1");
		if(id<0) {
			id=id*-1;
			modelAndView.setViewName("redirect:/testimonio/listado/0");
		}
		for(Testimonio test : testimonioService.getLista()) {
			if(test.getId()==id) {
				testimonioService.eliminar(test);
				break;
			}
		}
		return modelAndView;
	}
}
