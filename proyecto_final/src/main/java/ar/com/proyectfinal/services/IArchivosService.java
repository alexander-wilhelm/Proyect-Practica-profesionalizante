package ar.com.proyectfinal.services;

import ar.com.proyectfinal.entities.Archivos;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IArchivosService {
    List<Archivos> getAll();
    Page<Archivos> findAll(Pageable pageable);
    List<Archivos> findByDescrip(String descrip);
    Archivos get(Integer id);
    void save(Archivos entity);
    String delete(Archivos entity);
}
