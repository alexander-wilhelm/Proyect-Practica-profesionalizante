package ar.com.proyectfinal.dao;

import ar.com.proyectfinal.entities.Archivos;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IArchivosDao extends JpaRepository<Archivos, Integer> {
    @Query("select c from Archivos c where c.descrip like ?1")
    public List<Archivos> findByDescrip(String name);
    
    Page<Archivos> findAll(Pageable pageable);
}
