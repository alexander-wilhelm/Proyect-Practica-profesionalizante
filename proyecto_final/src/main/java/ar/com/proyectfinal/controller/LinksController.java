package ar.com.proyectfinal.controller;

import ar.com.proyectfinal.entities.Links;
import ar.com.proyectfinal.services.ILinksService;
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
public class LinksController {

    @Autowired
    ILinksService entityService;

    @RequestMapping(value = "/links", method = RequestMethod.GET)
    public String list(Model model, Pageable pageable) {        
        Page<Links> centroPage = entityService.findAll(pageable);
        PageWrapper<Links> page = new PageWrapper<Links>(centroPage, "/links");
        model.addAttribute("entities", page.getContent());
        model.addAttribute("page", page);
        model.addAttribute("entity", new Links());
        return "../links/index";
    }

    @RequestMapping("links/refresh")
    public String refresh() {
        return "redirect:/links";
    }

    @RequestMapping(value = "links/search", method = RequestMethod.POST)
    public String search(Model model, Links entity) {
        if (entity.getDescrip().equals("")) {
            return refresh();
        }
        model.addAttribute("entities", entityService.findByDescrip(entity.getDescrip()));
        model.addAttribute("entity", new Links());
        model.addAttribute("page", null);
        return "../links/index";
    }

    @RequestMapping("links/create/{id}")
    public String create(@PathVariable Integer id, Model model) {
        model.addAttribute("entity", new Links());
        return "../links/edit";
    }

    @RequestMapping("links/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        model.addAttribute("entity", entityService.get(id));
        return "../links/edit";
    }

    @RequestMapping(value = "links", method = RequestMethod.POST)
    public String save(Model model, @Validated Links entity) {
        if (entity.getDescrip().equals("")) {
            model.addAttribute("message", "Descripción Incorrecta");
            model.addAttribute("entity", entity);
            return "../links/edit";
        }

        entityService.save(entity);
        return "redirect:/links";
    }

    @RequestMapping("links/delete/{id}")
    public String delete(@PathVariable Integer id, Model model, Pageable pageable) {
        try {
            Links entity = entityService.get(id);
            entityService.delete(entity);
            return "redirect:/links";
        } catch (Exception e) {
            model.addAttribute("message", e.getMessage().toString());
            Page<Links> centroPage = entityService.findAll(pageable);
            PageWrapper<Links> page = new PageWrapper<Links>(centroPage, "/links");
            model.addAttribute("entities", page.getContent());
            model.addAttribute("page", page);
            model.addAttribute("entity", new Links());
            return "../links/index";
        }
    }
    
}