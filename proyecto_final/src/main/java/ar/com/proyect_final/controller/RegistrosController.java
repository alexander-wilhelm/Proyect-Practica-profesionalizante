package ar.com.proyect_final.controller;

import ar.com.proyect_final.entities.Registros;
import ar.com.proyect_final.entities.Usuarios;
import ar.com.proyect_final.services.IRegistrosService;
import ar.com.proyect_final.services.IUsuariosService;
import ar.com.proyect_final.utiles.PageWrapper;
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
public class RegistrosController {

    @Autowired
    IRegistrosService entityService;
    @Autowired
    IUsuariosService usuariosService;

    @RequestMapping(value = "/registros", method = RequestMethod.GET)
    public String list(Model model, Pageable pageable) {        
        Page<Registros> centroPage = entityService.findAll(pageable);
        PageWrapper<Registros> page = new PageWrapper<Registros>(centroPage, "/registros");
        model.addAttribute("entities", page.getContent());
        model.addAttribute("page", page);
        model.addAttribute("entity", new Registros());
        return "../registros/index";
    }

    @RequestMapping("registros/refresh")
    public String refresh() {
        return "redirect:/registros";
    }

    @RequestMapping(value = "registros/search", method = RequestMethod.POST)
    public String search(Model model, Registros entity) {
        if (entity.getDescrip().equals("")) {
            return refresh();
        }
        model.addAttribute("entities", entityService.findByDescrip(entity.getDescrip()));
        model.addAttribute("entity", new Registros());
        model.addAttribute("page", null);
        return "../registros/index";
    }

    @RequestMapping("registros/create/{id}")
    public String create(@PathVariable Integer id, Model model) {
        model.addAttribute("entity", new Registros());
        List<Usuarios> list = usuariosService.getAll();
        model.addAttribute("usuarios", list);

        return "../registros/edit";
    }

    @RequestMapping("registros/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        List<Usuarios> list = usuariosService.getAll();
        model.addAttribute("usuarios", list);
        model.addAttribute("entity", entityService.get(id));
        return "../registros/edit";
    }

    @RequestMapping(value = "registros", method = RequestMethod.POST)
    public String save(Model model, @Validated Registros entity) {
        String errores = "";
        if (entity.getDescrip().equals("")) errores += "Ingrese la descripcion";
        if (entity.getApenom().equals("")) errores += "Ingrese nombre y apellido";
        if (entity.getDirec().equals("")) errores += "Ingrese la direccion";
        if (entity.getTel() == null) errores += "Ingrese numero de telefono";
        if (entity.getFechanac() == null) errores += "Ingrese la fecha de nacimiento";
        if (entity.getEstado().equals("")) errores += "Ingrese el estado";

        if (!errores.equals("")) {

            model.addAttribute("message", errores);
            model.addAttribute("entity", entity);
            List<Usuarios> list = usuariosService.getAll();
            model.addAttribute("usuarios", list);
            return "../registros/edit";
        }

        entityService.save(entity);
        return "redirect:/registros";
    }

    @RequestMapping("registros/delete/{id}")
    public String delete(@PathVariable Integer id, Model model, Pageable pageable) {
        try {
            Registros entity = entityService.get(id);
            entityService.delete(entity);
            return "redirect:/registros";
        } catch (Exception e) {
            model.addAttribute("message", e.getMessage().toString());
            Page<Registros> centroPage = entityService.findAll(pageable);
            PageWrapper<Registros> page = new PageWrapper<Registros>(centroPage, "/registros");
            model.addAttribute("entities", page.getContent());
            model.addAttribute("page", page);
            model.addAttribute("entity", new Registros());
            return "../registros/index";
        }
    }
    
}