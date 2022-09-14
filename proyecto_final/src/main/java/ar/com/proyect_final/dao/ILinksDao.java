package ar.com.proyect_final.dao;

import ar.com.proyect_final.entities.Links;
import ar.com.proyect_final.entities.Roles;
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
