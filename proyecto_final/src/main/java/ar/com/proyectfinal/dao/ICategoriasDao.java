package ar.com.proyectfinal.dao;

import ar.com.proyectfinal.entities.Categorias;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICategoriasDao extends JpaRepository<Categorias, Integer> {
    @Query("select c from Categorias c where c.descrip like ?1")
    public List<Categorias> findByDescrip(String name);
    
    Page<Categorias> findAll(Pageable pageable);
}
