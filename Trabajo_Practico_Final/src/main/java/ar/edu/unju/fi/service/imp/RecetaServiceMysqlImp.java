package ar.edu.unju.fi.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unju.fi.entity.Receta;
import ar.edu.unju.fi.repository.IRecetaRepository;
import ar.edu.unju.fi.service.IRecetaService;
import jakarta.validation.Valid;

@Service("recetaServiceMysql")
public class RecetaServiceMysqlImp implements IRecetaService {

	@Autowired
	private IRecetaRepository recetaRepository;
	
	@Autowired
	private Receta receta;
	
	
	/**
	 *Método para obtener la lista de Recetas.
	 *Devuelve las recetas.
	 */
	@Override
	public List<Receta> getListaReceta() {
		
		return recetaRepository.findByEstado(true);
	}
	
	/**
	 *Método para obtener la lista de Recetas.
	 *Devuelve las recetas.
	 */
	@Override
	public List<Receta> getListaRecetaCategoria(String categoria) {
	     return recetaRepository.findByEstadoAndCategoria(true, categoria);
	}

	
	/*
	 * Método para guardar una nueva receta.
	 * Agrega la nueva receta a la lista de recetas.
	 */
	@Override
	public void guardar(@Valid Receta receta) {
		receta.setEstado(true);
		recetaRepository.save(receta);

	}

	
	/*
	 * Método para buscar una receta.
	 * Busca la receta con el nombre proporcionado en la BD de recetas.
	 * Devuelve la receta encontrada.
	 */
	@Override
	public Receta getBy(Long id) {
		return recetaRepository.findById(id).get();
	}

	
	/*
	 * Método para editar una receta existente.
	 * Actualiza los atributos de la receta encontrada con los valores proporcionados.
	 */	
	@Override
	public void editar(Receta receta) {
		recetaRepository.save(receta);
	}

	
	/*
	 * Método para eliminar una receta.
	 * Remueve la receta de la lista de recetas.
	 */
	@Override
	public void eliminar(Receta recetaEncontrada) {
		recetaEncontrada.setEstado(false);
		recetaRepository.save(recetaEncontrada);
	}

	
	/*
	 * Método para devolver un receta.
	 * Devuelve una receta.
	 */
	@Override
	public Receta getReceta() {
		return receta;
	}
}