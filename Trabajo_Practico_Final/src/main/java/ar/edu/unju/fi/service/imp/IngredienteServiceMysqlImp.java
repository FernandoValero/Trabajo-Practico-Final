package ar.edu.unju.fi.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unju.fi.entity.Ingrediente;
import ar.edu.unju.fi.repository.IIngredienteRepository;
import ar.edu.unju.fi.service.IIngredienteService;
import jakarta.validation.Valid;

@Service("ingredienteServiceMysql")
public class IngredienteServiceMysqlImp implements IIngredienteService {

	@Autowired
	private IIngredienteRepository ingredienteRepository;
	
	@Autowired
	private Ingrediente ingrediente;
	
	
	/**
	 *Método para obtener la lista de Ingredientes.
	 *Devuelve los ingredientes.
	 */
	@Override
	public List<Ingrediente> getListaIngrediente() {
		
		return ingredienteRepository.findByEstado(true);
	}

	
	/*
	 * Método para guardar un nuevo ingrediente.
	 * Agrega un nuevo ingrediente a la lista de ingredientes.
	 */
	@Override
	public void guardar(@Valid Ingrediente ingrediente) {
		ingrediente.setEstado(true);
		ingredienteRepository.save(ingrediente);

	}

	
	/*
	 * Método para buscar un ingrediente.
	 * Busca el ingrediente con el nombre proporcionado en la BD de ingredientes.
	 * Devuelve el ingrediente encontrado.
	 */
	@Override
	public Ingrediente getBy(Long id) {
		return ingredienteRepository.findById(id).get();
	}

	
	/*
	 * Método para eliminar un ingrediente.
	 * Remueve el ingrediente de la lista de ingredientes.
	 */
	@Override
	public void eliminar(Ingrediente ingredienteEncontrado) {
		ingredienteEncontrado.setEstado(false);
		ingredienteRepository.save(ingredienteEncontrado);
	}

	
	/*
	 * Método para devolver un ingrediente.
	 * Devuelve un ingrediente.
	 */
	@Override
	public Ingrediente getIngrediente() {
		return ingrediente;
	}
}