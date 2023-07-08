package ar.edu.unju.fi.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import ar.edu.unju.fi.entity.IndiceMasaCorporal;
import ar.edu.unju.fi.entity.Usuario;
import ar.edu.unju.fi.repository.IIndiceMasaCorporalRepository;
import ar.edu.unju.fi.service.IIndiceMasaCorporalService;

public class IndiceMasaCorporalServiceMysqlImp implements IIndiceMasaCorporalService {

    @Autowired
    private IIndiceMasaCorporalRepository imcRespository;
    
    @Autowired
    private IndiceMasaCorporal imc;

    /**
     * Método que retorna una lista de objetod de tipo IndiceMasaCorporal.
     * 
     * @return una lista de objetos del tipo IndiceMasaCorporal.
     */
    @Override
    public List<IndiceMasaCorporal> getListaImc() {
        return imcRespository.findByEstado(true);
    }

    /**
     * Método que genera una lista de IndiceMasaCorporal según su usuario y su estado.
     * 
     * @param usuario Objeto del tipo Usuario.
     * @param estado valor del tipo boolean que representa el estado del imc
     * @return retorna una lista de objetos del tipo IndiceMasaCorporal.
     */
    @Override
    public List<IndiceMasaCorporal> getListaByUsuarioAndEstado(Usuario usuario, boolean estado) {
        return imcRespository.findByUsuarioAndEstado(usuario, estado);
    }

    /**
     * Método que busca un indice de masa corporal según su Id.
     * 
     * @param id del tipo Long 
     * @return retorna un objeto del tipo IndiceMasaCorporal.
     */
    @Override
    public IndiceMasaCorporal getImcById(Long id) {
        return imcRespository.findById(id).get();
    }

    /**
     * Método que guarda un objeto del tipo IndiceMasaCorporal.
     * 
     * @param imc del tipo IndiceMasaCorporal.
     */
    @Override
    public void guardarImc(IndiceMasaCorporal imc) {
        imc.setEstado(true);
        imcRespository.save(imc);
    }

    /**
     * Método que elimina un objeto de tipo IndiceMasaCorporal, poniendo 
     * su estado a false, según su Id.
     * 
     * @param id del tipo Long que representa el Id del objeto a eliminar.
     */
    @Override
    public void eliminarById(Long id) {
        IndiceMasaCorporal imc = getImcById(id);
        imc.setEstado(false);
        imcRespository.save(imc);
    }

    /**
     * Método que retorna un objeto del tipo IndiceMasaCorporal.
     * 
     * @return un objeto del tipo IndiceMasaCorporal.
     */
    @Override
    public IndiceMasaCorporal getImc() {
        return imc;
    }
    
}
