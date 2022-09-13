package ar.com.proyectfinal.controller;

import ar.com.proyectfinal.entities.Archivos;
import ar.com.proyectfinal.services.IArchivosService;
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

@Controller
public class ArchivosController {

    @Autowired
    IArchivosService entityService;

    @RequestMapping(value = "/archivos", method = RequestMethod.GET)
    public String list(Model model, Pageable pageable) {        
        Page<Archivos> centroPage = entityService.findAll(pageable);
        PageWrapper<Archivos> page = new PageWrapper<Archivos>(centroPage, "/archivos");
        model.addAttribute("entities", page.getContent());
        model.addAttribute("page", page);
        model.addAttribute("entity", new Archivos());
        return "../archivos/index";
    }

    @RequestMapping("archivos/refresh")
    public String refresh() {
        return "redirect:/archivos";
    }

    @RequestMapping(value = "archivos/search", method = RequestMethod.POST)
    public String search(Model model, Archivos entity) {
        if (entity.getDescrip().equals("")) {
            return refresh();
        }
        model.addAttribute("entities", entityService.findByDescrip(entity.getDescrip()));
        model.addAttribute("entity", new Archivos());
        model.addAttribute("page", null);
        return "../archivos/index";
    }

    @RequestMapping("archivos/create/{id}")
    public String create(@PathVariable Integer id, Model model) {
        model.addAttribute("entity", new Archivos());
        return "../archivos/edit";
    }

    @RequestMapping("archivos/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        model.addAttribute("entity", entityService.get(id));
        return "../archivos/edit";
    }

    @RequestMapping(value = "archivos", method = RequestMethod.POST)
    public String save(Model model, @Validated Archivos entity) {
        if (entity.getDescrip().equals("")) {
            model.addAttribute("message", "Descripción Incorrecta");
            model.addAttribute("entity", entity);
            return "../archivos/edit";
        }

        entityService.save(entity);
        return "redirect:/archivos";
    }

    @RequestMapping("archivos/delete/{id}")
    public String delete(@PathVariable Integer id, Model model, Pageable pageable) {
        try {
            Archivos entity = entityService.get(id);
            entityService.delete(entity);
            return "redirect:/archivos";
        } catch (Exception e) {
            model.addAttribute("message", e.getMessage().toString());
            Page<Archivos> centroPage = entityService.findAll(pageable);
            PageWrapper<Archivos> page = new PageWrapper<Archivos>(centroPage, "/archivos");
            model.addAttribute("entities", page.getContent());
            model.addAttribute("page", page);
            model.addAttribute("entity", new Archivos());
            return "../archivos/index";
        }
    }
    
}