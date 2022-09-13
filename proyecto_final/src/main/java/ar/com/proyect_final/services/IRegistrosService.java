package ar.com.proyect_final.services;

import ar.com.proyect_final.entities.Registros;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IRegistrosService {
    List<Registros> getAll();
    Page<Registros> findAll(Pageable pageable);
    List<Registros> findByDescrip(String descrip);
    Registros get(Integer id);
    void save(Registros entity);
    String delete(Registros entity);
}
