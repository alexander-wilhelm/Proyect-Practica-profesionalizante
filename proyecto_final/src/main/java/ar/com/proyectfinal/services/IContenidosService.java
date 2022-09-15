package ar.com.proyectfinal.services;

import ar.com.proyectfinal.entities.Contenidos;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IContenidosService {
    List<Contenidos> getAll();
    Page<Contenidos> findAll(Pageable pageable);
    List<Contenidos> findByDescrip(String descrip);
    Contenidos get(Integer id);
    void save(Contenidos entity);
    String delete(Contenidos entity);
}
