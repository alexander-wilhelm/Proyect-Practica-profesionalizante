package ar.com.proyectfinal.services;

import ar.com.proyectfinal.entities.Roles;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IRolesService {
    List<Roles> getAll();
    Page<Roles> findAll(Pageable pageable);
    List<Roles> findByDescrip(String descrip);
    Roles get(Integer id);
    void save(Roles entity);
    String delete(Roles entity);
}
