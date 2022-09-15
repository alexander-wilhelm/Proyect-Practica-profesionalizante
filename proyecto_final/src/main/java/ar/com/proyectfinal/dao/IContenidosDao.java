package ar.com.proyectfinal.dao;

import java.util.List;

import ar.com.proyectfinal.entities.Contenidos;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IContenidosDao extends JpaRepository<Contenidos, Integer> {
    @Query("select c from Contenidos c where c.descrip like ?1")
    public List<Contenidos> findByDescrip(String name);
    
    Page<Contenidos> findAll(Pageable pageable);
}
