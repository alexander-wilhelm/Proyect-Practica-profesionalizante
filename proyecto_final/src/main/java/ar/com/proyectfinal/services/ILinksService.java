package ar.com.proyectfinal.services;

import ar.com.proyectfinal.entities.Links;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ILinksService {
    List<Links> getAll();
    Page<Links> findAll(Pageable pageable);
    List<Links> findByDescrip(String descrip);
    Links get(Integer id);
    void save(Links entity);
    String delete(Links entity);
}
