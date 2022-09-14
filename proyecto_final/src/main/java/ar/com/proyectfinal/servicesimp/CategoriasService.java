package ar.com.proyectfinal.servicesimp;

import ar.com.proyectfinal.dao.ICategoriasDao;
import ar.com.proyectfinal.entities.Categorias;
import ar.com.proyectfinal.services.ICategoriasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class CategoriasService implements ICategoriasService {

    @Autowired
    private ICategoriasDao entityDao;

    public List<Categorias> getAll() {
        return entityDao.findAll(Sort.by(Sort.Direction.ASC, "descrip"));
    }

    public Page<Categorias> findAll(Pageable pageable) {
        return entityDao.findAll(PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by(Sort.Direction.ASC, "descrip")));
    }

    public List<Categorias> findByDescrip(String descrip) {
        return entityDao.findByDescrip("%" + descrip + "%");
    }

    public Categorias get(Integer id) {
        return entityDao.findById(id).orElse(null);
    }

    @Transactional
    public void save(Categorias entity) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        entityDao.save(entity);
    }

    @Transactional
    public String delete(Categorias entity) {
        try {
            entityDao.delete(entity);
            return null;
        } catch (Exception e) {
            return e.getMessage().toString();
        }
    }
}
