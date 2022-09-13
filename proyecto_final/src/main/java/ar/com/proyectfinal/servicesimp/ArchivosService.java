package ar.com.proyectfinal.servicesimp;

import ar.com.proyectfinal.dao.IArchivosDao;
import ar.com.proyectfinal.entities.Archivos;
import ar.com.proyectfinal.services.IArchivosService;
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
public class ArchivosService implements IArchivosService {

    @Autowired
    private IArchivosDao entityDao;

    public List<Archivos> getAll() {
        return entityDao.findAll(Sort.by(Sort.Direction.ASC, "descrip"));
    }

    public Page<Archivos> findAll(Pageable pageable) {
        return entityDao.findAll(PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by(Sort.Direction.ASC, "descrip")));
    }

    public List<Archivos> findByDescrip(String descrip) {
        return entityDao.findByDescrip("%" + descrip + "%");
    }

    public Archivos get(Integer id) {
        return entityDao.findById(id).orElse(null);
    }

    @Transactional
    public void save(Archivos entity) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        entityDao.save(entity);
    }

    @Transactional
    public String delete(Archivos entity) {
        try {
            entityDao.delete(entity);
            return null;
        } catch (Exception e) {
            return e.getMessage().toString();
        }
    }
}
