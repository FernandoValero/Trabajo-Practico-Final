package ar.edu.unju.fi.service.imp;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ar.edu.unju.fi.entity.Testimonio;
import ar.edu.unju.fi.repository.ITestimonioRepository;
import ar.edu.unju.fi.service.ITestimonioService;
/**
* Implementación de la interfaz ITestimonioService que define los métodos de servicio para la entidad Producto.
* Utiliza el repositorio IProductoRepository para acceder y manipular los datos.
*/
@Service
public class TestimonioServiceMysqlImp implements ITestimonioService {
	
	@Autowired
	private ITestimonioRepository testimonioRepository;
	
	@Autowired
	private Testimonio testimonio;
	
	/**
	* Obtiene la lista de todos los testimonios.
	* @return una lista de testimonio activos.
	*/
	@Override
	public List<Testimonio> getLista() {
		return testimonioRepository.findByEstado(true);
	}
	/**
	*  Guardo un nuevo testimonio.
	*  @param testimonio: testimonio a guardar.
	*/
	@Override
	public void guardar(Testimonio testimonio) {
		testimonioRepository.save(testimonio);
	}
	/**
	*  Modifica un tesmonio existente.
	*  @param testimonio: testimonio a modificar.
	*/
	@Override
	public void modificar(Testimonio testimonio) {
		testimonioRepository.save(testimonio);
	}
	/**
	*  Elimina un testimonio existente.
	*  @param testimonioEncontrado: testimonio a eliminar.
	*/
	@Override
	public void eliminar(Testimonio testimonioEncontrado) {
		testimonioEncontrado.setEstado(false);
		testimonioRepository.save(testimonioEncontrado);
	}
	/**
    * Encuentra un testimonio de acuerdo a su id.
    * @param id: identificador del testimonio a buscar.
    * @return el testimonio que tenga coincidencia.
    */

	@Override
	public Testimonio getBy(Long id) {
		return testimonioRepository.findById(id).get();
	}
	/**
    * Obtiene un nuevo testimonio.
    * @return un objeto testimonio.
    */
	@Override
	public Testimonio getProducto() {
		return testimonio;
	}
}
