package ar.com.proyect_final.servicesimp;

import java.util.List;
import javax.transaction.Transactional;

import ar.com.proyect_final.dao.IRegistrosDao;
import ar.com.proyect_final.entities.Registros;
import ar.com.proyect_final.services.IRegistrosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class RegistrosService implements IRegistrosService {

    @Autowired
    private IRegistrosDao entityDao;

    public List<Registros> getAll() {
        return entityDao.findAll(Sort.by(Sort.Direction.ASC, "descrip"));
    }

    public Page<Registros> findAll(Pageable pageable) {
        return entityDao.findAll(PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by(Sort.Direction.ASC, "descrip")));
    }

    public List<Registros> findByDescrip(String descrip) {
        return entityDao.findByDescrip("%" + descrip + "%");
    }

    public Registros get(Integer id) {
        return entityDao.findById(id).orElse(null);
    }

    @Transactional
    public void save(Registros entity) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        entityDao.save(entity);
    }

    @Transactional
    public String delete(Registros entity) {
        try {
            entityDao.delete(entity);
            return null;
        } catch (Exception e) {
            return e.getMessage().toString();
        }
    }
}
