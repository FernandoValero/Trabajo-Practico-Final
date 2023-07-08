package ar.edu.unju.fi.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

@Controller
@RequestMapping("/servicio/imc")
public class IndiceMasaCorporalController {
    @Autowired
	// @Qualifier("usuarioServiceMysql")
	private IUsuarioService usuarioService;
	
	@Autowired
	@Qualifier("imcServiceMysql")
	private IIndiceMasaCorporalService imcService;
	
	private static final Log LOGGER = LogFactory.getLog(IndiceMasaCorporalController.class); 

    @GetMapping("")
	public String getIMCPage(Model model) {	
		model.addAttribute("existeUsuario", true);

		return "imc";
	}
	
	@GetMapping("/buscar")
	public String buscarUsuario(@RequestParam(value = "id")Long id, Model model) {
		LOGGER.info("valor del id:------------------>"+id);
		Usuario usuarioBuscado = usuarioService.getBy(id);
		LOGGER.info("usuario:---------------"+usuarioBuscado);
		if (usuarioBuscado != null) {
			
//			model.addAttribute("usuario", usuarioBuscado);
			IndiceMasaCorporal imc =  imcService.getImc();
			imc.setUsuario(usuarioBuscado);
			LOGGER.info("id del usuario:--------------->"+imc.getUsuario().getId());
			LOGGER.info("estatura del usuario--------------->"+imc.getUsuario().getEstatura());
			LOGGER.info("peso del usuario:------------>"+imc.getPeso());
			imc.setUsuario(usuarioBuscado);
			model.addAttribute("imc", imc);
			return "imc_calculadora";
		} else {
			model.addAttribute("existeUsuario", false);
			return "imc";
		}		
	}
	
	@PostMapping("/calcular")
	public String guardar(@ModelAttribute("imc")IndiceMasaCorporal imc, Model model) {
		LOGGER.info("el id de usuario:-------------------->"+imc.getUsuario().getId());
		LOGGER.info("valor de usuario:---->"+imc.getUsuario());
		Usuario usuarioBuscado = usuarioService.getBy(imc.getUsuario().getId());
		
		imc.setUsuario(usuarioBuscado);

		LOGGER.info("id del usuario:--------------->"+imc.getUsuario().getId());
		LOGGER.info("estatura del usuario--------------->"+imc.getUsuario().getEstatura());
		LOGGER.info("peso del usuario:------------>"+imc.getPeso());
		model.addAttribute("resultado", imc.calcularIMC());		
		model.addAttribute("imc", imc);
		imcService.guardarImc(imc);
		return "imc_resultado";
	}
		
	
	@GetMapping("/listado/{id}")
	public String listadoImc(@PathVariable(value = "id")Long id, Model model) {
		Usuario usuarioBuscado = usuarioService.getBy(id);
		model.addAttribute("usuario", usuarioBuscado);
		
		model.addAttribute("listadoImc", imcService.getListaByUsuarioAndEstado(usuarioBuscado, true));
		return "imc_listado";
	}
}

