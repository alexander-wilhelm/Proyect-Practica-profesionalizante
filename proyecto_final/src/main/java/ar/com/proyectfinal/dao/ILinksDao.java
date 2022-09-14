package ar.com.proyectfinal.dao;

import ar.com.proyectfinal.entities.Links;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ILinksDao extends JpaRepository<Links, Integer> {
    @Query("select c from Links c where c.descrip like ?1")
    public List<Links> findByDescrip(String name);
    
    Page<Links> findAll(Pageable pageable);
}
