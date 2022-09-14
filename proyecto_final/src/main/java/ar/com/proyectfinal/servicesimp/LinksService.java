package ar.com.proyectfinal.servicesimp;

import ar.com.proyectfinal.dao.ILinksDao;
import ar.com.proyectfinal.entities.Links;
import ar.com.proyectfinal.services.ILinksService;
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
public class LinksService implements ILinksService {

    @Autowired
    private ILinksDao entityDao;

    public List<Links> getAll() {
        return entityDao.findAll(Sort.by(Sort.Direction.ASC, "descrip"));
    }

    public Page<Links> findAll(Pageable pageable) {
        return entityDao.findAll(PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by(Sort.Direction.ASC, "descrip")));
    }

    public List<Links> findByDescrip(String descrip) {
        return entityDao.findByDescrip("%" + descrip + "%");
    }

    public Links get(Integer id) {
        return entityDao.findById(id).orElse(null);
    }

    @Transactional
    public void save(Links entity) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        entityDao.save(entity);
    }

    @Transactional
    public String delete(Links entity) {
        try {
            entityDao.delete(entity);
            return null;
        } catch (Exception e) {
            return e.getMessage().toString();
        }
    }
}
