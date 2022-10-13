package ar.com.proyectfinal.servicesimp;

import java.util.List;
import javax.transaction.Transactional;

import ar.com.proyectfinal.dao.IContenidosDao;
import ar.com.proyectfinal.entities.Contenidos;
import ar.com.proyectfinal.services.IContenidosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class ContenidosService implements IContenidosService {

    @Autowired
    private IContenidosDao entityDao;

    public List<Contenidos> getAll() {
        return entityDao.findAll(Sort.by(Sort.Direction.ASC, "descrip"));
    }

    public Page<Contenidos> findAll(Pageable pageable) {
        return entityDao.findAll(PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by(Sort.Direction.ASC, "descrip")));
    }

    public List<Contenidos> findByDescrip(String descrip) {
        return entityDao.findByDescrip("%" + descrip + "%");
    }

    public Contenidos get(Integer id) {
        return entityDao.findById(id).orElse(null);
    }

    @Transactional
    public void save(Contenidos entity) {
        entityDao.save(entity);
    }


    @Transactional
    public String delete(Contenidos entity) {
        try {
            entityDao.delete(entity);
            return null;
        } catch (Exception e) {
            return e.getMessage().toString();
        }


    }
}
