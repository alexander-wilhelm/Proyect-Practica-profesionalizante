package ar.com.proyect_final.dao;

import java.util.List;

import ar.com.proyect_final.entities.Registros;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IRegistrosDao extends JpaRepository<Registros, Integer> {
    @Query("select c from Registros c where c.descrip like ?1")
    public List<Registros> findByDescrip(String name);
    
    Page<Registros> findAll(Pageable pageable);
}
