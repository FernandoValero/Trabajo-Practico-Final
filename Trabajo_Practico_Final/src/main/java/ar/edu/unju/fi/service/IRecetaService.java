package ar.edu.unju.fi.service;

import java.util.List;

import ar.edu.unju.fi.entity.Receta;
import jakarta.validation.Valid;

public interface IRecetaService {

	/**
	 *Devuelve la lista de Recetas
	 */
	List<Receta> getListaReceta();
	
	/**
	 *Devuelve la lista de Recetas
	 */
	List<Receta> getListaRecetaCategoria(String categoria);
	
	/**
	 *Metodo que permite guardar una receta
	 *@param receta, valor a guardar
	 */
	void guardar (@Valid Receta receta);
	
	/**
	 *Metodo para buscar una receta que coincida con el id por paramtero
	 *@param id, receta a buscar
	 */
	Receta getBy(Long id);
	
	/**
	 *Metodo que permite modificar los datos de una receta
	 *@param receta, receta a modificar
	 */
	void editar (Receta receta);
	
	/**
	 *Metodo que permite eliminar una receta
	 * *@param recetaEncontrada, receta a eliminar
	 */
	void eliminar (Receta recetaEncontrada);
	
	/**
	 *Devuelve la instancia de una receta
	 */
	Receta getReceta();

}
