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
    private IUsuarioService usuarioService;

    /**
     * Maneja la solicitud GET para mostrar el formulario de registro.
     * Si se proporciona el parámetro "perfil" con el valor "Modificar", se cargan los datos del usuario para su edición.
     * 
     * @param model  el modelo utilizado para pasar datos a la vista
     * @param perfil el perfil del usuario ("Modificar" para edición, o null para registro nuevo)
     * @return la vista "registros" para el formulario de registro/edición
     */
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

    /**
     * Maneja la solicitud POST para enviar los datos del formulario de registro/edición.
     * Si los datos son válidos, se guarda el usuario en la base de datos.
     * 
     * @param usuario el usuario obtenido del formulario
     * @param result  el resultado del proceso de validación del formulario
     * @param model   el modelo utilizado para pasar datos a la vista
     * @return la vista "registros" si hay errores de validación, o la misma vista con un mensaje de guardado exitoso
     */
    @PostMapping("/enviar")
    public String enviarUsuario(@Valid @ModelAttribute("usuario") Usuario usuario, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("edicion", false);
            return "registros";
        }
        usuarioService.guardar(usuario);
        Long idUsuario = usuario.getId();
        model.addAttribute("guardadoExitoso", true);
        model.addAttribute("idUsuario", idUsuario);
        return "registros";
    }

    /**
     * Maneja la solicitud GET para mostrar la página de inicio de sesión.
     * 
     * @return la vista "ingreso" para el inicio de sesión
     */
    @GetMapping("/login")
    public String getPaginaLogin() {
        return "ingreso";
    }

    /**
     * Maneja la solicitud GET para buscar un usuario por su ID.
     * Si se encuentra el usuario, se cargan sus datos en el formulario de registro para su edición.
     * 
     * @param model el modelo utilizado para pasar datos a la vista
     * @param id    el ID del usuario a buscar
     * @return la vista "registros" con los datos del usuario si se encuentra, o la vista "ingreso" si no se encuentra
     */
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

    /**
     * Maneja la solicitud GET para mostrar la página de edición de un usuario.
     * 
     * @param model   el modelo utilizado para pasar datos a la vista
     * @param usuario el usuario a editar (pasado como un objeto ModelAttribute)
     * @return la vista "registro" para la edición del usuario
     */
    @GetMapping("/editar/{id}")
    public String getEditarSucursalPage(Model model, @ModelAttribute("usuario") Usuario usuario) {
        boolean edicion = true;
        model.addAttribute("edicion", edicion);
        return "registro";
    }

    /**
     * Maneja la solicitud POST para guardar los cambios realizados en la edición de un usuario.
     * Si los datos son válidos, se actualiza el usuario en la base de datos.
     * 
     * @param usuario el usuario obtenido del formulario de edición
     * @param result  el resultado del proceso de validación del formulario
     * @param model   el modelo utilizado para pasar datos a la vista
     * @return la vista "registros" si hay errores de validación, o la misma vista con un mensaje de guardado exitoso
     */
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

