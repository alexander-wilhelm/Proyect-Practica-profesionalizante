package ar.com.proyectfinal.controller;

import ar.com.proyectfinal.entities.Categorias;
import ar.com.proyectfinal.entities.Contenidos;
import ar.com.proyectfinal.entities.Links;
import ar.com.proyectfinal.entities.Usuarios;
import ar.com.proyectfinal.services.ICategoriasService;
import ar.com.proyectfinal.services.IContenidosService;
import ar.com.proyectfinal.services.ILinksService;
import ar.com.proyectfinal.services.IUsuariosService;
import ar.com.proyectfinal.utiles.PageWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class ContenidosController {

    @Autowired
    IContenidosService entityService;

    @Autowired
    IUsuariosService usuariosService;

    @Autowired
    ICategoriasService categoriasService;

    @Autowired
    ILinksService linksService;

    @RequestMapping(value = "/contenidos", method = RequestMethod.GET)
    public String list(Model model, Pageable pageable) {        
        Page<Contenidos> centroPage = entityService.findAll(pageable);
        PageWrapper<Contenidos> page = new PageWrapper<Contenidos>(centroPage, "/contenidos");
        model.addAttribute("entities", page.getContent());
        model.addAttribute("page", page);
        model.addAttribute("entity", new Contenidos());
        return "../contenidos/index";
    }

    @RequestMapping("contenidos/refresh")
    public String refresh() {
        return "redirect:/contenidos";
    }

    @RequestMapping(value = "registros/search", method = RequestMethod.POST)
    public String search(Model model, Contenidos entity) {
        if (entity.getDescrip().equals("")) {
            return refresh();
        }
        model.addAttribute("entities", entityService.findByDescrip(entity.getDescrip()));
        model.addAttribute("entity", new Contenidos());
        model.addAttribute("page", null);
        return "../contenidos/index";
    }

    @RequestMapping("contenidos/create/{id}")
    public String create(@PathVariable Integer id, Model model) {
        model.addAttribute("entity", new Contenidos());
        List<Usuarios> list = usuariosService.getAll();
        model.addAttribute("usuarios", list);
        List<Categorias> listcat = categoriasService.getAll();
        model.addAttribute("categorias", listcat);
        List<Links> listlink = linksService.getAll();
        model.addAttribute("links", listlink);
        return "../contenidos/edit";
    }

    @RequestMapping("contenidos/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        model.addAttribute("entity", entityService.get(id));
        List<Usuarios> list = usuariosService.getAll();
        model.addAttribute("usuarios", list);
        List<Categorias> listcat = categoriasService.getAll();
        model.addAttribute("categorias", listcat);
        List<Links> listlink = linksService.getAll();
        model.addAttribute("links", listlink);
        return "../contenidos/edit";
    }

    @RequestMapping(value = "contenidos", method = RequestMethod.POST)
    public String save(Model model, @Validated Contenidos entity) {
        String errores = "";
        if (entity.getDescrip().equals("")) errores += "Ingrese la descripcion";
        if (entity.getTitulo().equals("")) errores += "Ingrese el titulo";
        if (entity.getTag().equals("")) errores += "Ingrese el tag";
        if (entity.getFechaalta() == null) errores += "Ingrese fecha de alta";
        if (entity.getFechacracion() == null) errores += "Ingrese fecha de creacion";
        if (entity.getHabilitado().equals("")) errores += "Ingrese el estado";
        if (entity.getTipo().equals("")) errores += "Ingrese tipo";
        if (entity.getBaja().equals("")) errores += "Ingrese baja";

        if (!errores.equals("")) {

            model.addAttribute("message", errores);
            model.addAttribute("entity", entity);
            List<Usuarios> list = usuariosService.getAll();
            model.addAttribute("usuarios", list);
            List<Categorias> listcat = categoriasService.getAll();
            model.addAttribute("categorias", listcat);
            List<Links> listlink = linksService.getAll();
            model.addAttribute("links", listlink);
            return "../contenidos/edit";
        }
        entityService.save(entity);
        return "redirect:/contenidos";
    }

    @RequestMapping("contenidos/delete/{id}")
    public String delete(@PathVariable Integer id, Model model, Pageable pageable) {
        try {
            Contenidos entity = entityService.get(id);
            entityService.delete(entity);
            return "redirect:/contenidos";
        } catch (Exception e) {
            model.addAttribute("message", e.getMessage().toString());
            Page<Contenidos> centroPage = entityService.findAll(pageable);
            PageWrapper<Contenidos> page = new PageWrapper<Contenidos>(centroPage, "/contenidos");
            model.addAttribute("entities", page.getContent());
            model.addAttribute("page", page);
            model.addAttribute("entity", new Contenidos());
            return "../contenidos/index";
        }
    }
    
}