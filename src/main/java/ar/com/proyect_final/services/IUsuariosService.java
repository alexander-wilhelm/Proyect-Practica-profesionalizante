package ar.com.proyect_final.services;

import ar.com.proyect_final.entities.Usuarios;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IUsuariosService {
    List<Usuarios> getAll();
    Usuarios get(Integer id);
    void save(Usuarios entity);
    String delete(Usuarios entity);
    Page<Usuarios> findAll(Pageable pageable);
    List<Usuarios> findByDescrip(String descrip);

    Usuarios findUsuario(String usuario, String pass);
}
