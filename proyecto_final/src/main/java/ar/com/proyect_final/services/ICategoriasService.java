package ar.com.proyect_final.services;

import ar.com.proyect_final.entities.Categorias;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ICategoriasService {
    List<Categorias> getAll();
    Page<Categorias> findAll(Pageable pageable);
    List<Categorias> findByDescrip(String descrip);
    Categorias get(Integer id);
    void save(Categorias entity);
    String delete(Categorias entity);
}
