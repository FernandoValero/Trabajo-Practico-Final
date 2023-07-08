package ar.edu.unju.fi.service;

import java.util.List;
import ar.edu.unju.fi.entity.Testimonio;

/**
* Esta interfaz define los métodos para administrar los testimonios hechos por los usuarios.
*/

public interface ITestimonioService {
	/**
	* Obtiene la lista de todos los testimonios.
	* @return una lista de testimonios.
	*/
	List<Testimonio> getLista();
	/**
	*  Guardo un nuevo testimonio.
	*  @param testimonio: testimonio a guardar.
	*/
	void guardar(Testimonio testimonio);
	/**
	*  Modifica un tesmonio existente.
	*  @param testimonio: testimonio a modificar.
	*/
	void modificar(Testimonio testimonio);
	/**
	*  Elimina un testimonio existente.
	*  @param testimonioEncontrado: testimonio a eliminar.
	*/
	void eliminar(Testimonio testimonioEncontrado);
	/**
    * Encuentra un testimonio de acuerdo a su id.
    * @param id: identificador del testimonio a buscar.
    * @return el testimonio que tenga coincidencia.
    */
	Testimonio getBy(Long id);
	/**
    * Obtiene un nuevo testimonio.
    * @return un objeto testimonio.
    */
	Testimonio getTestimonio();
}
