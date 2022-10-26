package ar.com.proyectfinal.controller;

import ar.com.proyectfinal.entities.*;
import ar.com.proyectfinal.services.ICategoriasService;
import ar.com.proyectfinal.services.IContenidosService;
import ar.com.proyectfinal.services.IUsuariosService;
import ar.com.proyectfinal.utiles.PageWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Controller
@SessionAttributes("cont")
public class ContenidosController {

    @Autowired
    IContenidosService entityService;

    @Autowired
    IUsuariosService usuariosService;

    @Autowired
    ICategoriasService categoriasService;


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

    @RequestMapping(value = "contenidos/search", method = RequestMethod.POST)
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
        return "../contenidos/edit";
    }

    @RequestMapping("contenidos/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        model.addAttribute("entity", entityService.get(id));
        List<Usuarios> list = usuariosService.getAll();
        model.addAttribute("usuarios", list);
        List<Categorias> listcat = categoriasService.getAll();
        model.addAttribute("categorias", listcat);
        return "../contenidos/edit";
    }

    @RequestMapping(value = "contenidos", method = RequestMethod.POST)
    public String save(Model model, @Validated Contenidos entity) {
        String errores = "";
        if (entity.getDescrip().equals("")) errores += "Ingrese la descripcion";
        if (entity.getTitulo().equals("")) errores += "Ingrese el titulo";
        if (entity.getTag().equals("")) errores += "Ingrese el tag";
        if (entity.getFechaalta() == null) errores += "Ingrese fecha de alta";
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

    @RequestMapping("contenidos/addlink/{id}")
    public String addlink(@PathVariable Integer id, Model model) {
        Contenidos contenido = entityService.get(id);
        Links entity = new Links();

        List<LinksView> lista = new ArrayList<>();
        for (Links l : contenido.getLinks()) {
            LinksView c = new LinksView();
            c.setId(l.getId());
            c.setDescrip(l.getDescrip());
            c.setLink(l.getLink());
            c.setContenidoid(contenido.getId());
            lista.add(c);
            System.out.println(l.getId());
        }
        entity.setLinks(lista);

        entity.setContenidos(contenido);
        model.addAttribute("entity", entity);
        return "../contenidos/links";
    }

    @RequestMapping("contenidos/deletelink/{id}/{contenidoid}")
    public String deletelink(@PathVariable String id, @PathVariable Integer contenidoid, Model model) {

        Contenidos contenido = entityService.get(contenidoid);

        for (Links l : contenido.getLinks()) {
            if (l.getId().equals(id)) {
                contenido.getLinks().remove(l);
                break;
            }
        }

        entityService.save(contenido);

        return "redirect:/contenidos/addlink/" + contenidoid;
    }

    @RequestMapping(value = "links", method = RequestMethod.POST)
    public String savelink(Model model, @Validated Links entity) {
        Contenidos contenido = entityService.get(entity.getContenidos().getId());
        entity.setId(UUID.randomUUID().toString().replace("-", "_"));
        entity.setContenidos(contenido);
        contenido.getLinks().add(entity);
        entityService.save(contenido);
        return "redirect:/contenidos/addlink/" + (entity.getContenidos().getId());
    }


    @RequestMapping("contenidos/addfile/{id}")
    public String addfile(@PathVariable Integer id, Model model) {
        Contenidos contenido = entityService.get(id);
        model.addAttribute("cont", contenido);
        Archivos entity = new Archivos();

        //model.addAttribute("archivos", entityService.get(id).getArchivos());
        return "../contenidos/upload";
    }

    String UPLOADED_FOLDER = "uploads//";

    @PostMapping("/upload") // //new annotation since 4.3
    public String singleFileUpload(@RequestParam("file") MultipartFile file,
                                   @SessionAttribute("cont") Contenidos contenido,
                                   RedirectAttributes redirectAttributes) {

        if (file.isEmpty()) {
            redirectAttributes.addFlashAttribute("message", "Please select a file to upload");
            return "redirect:uploadStatus";
        }

        try {

            // Get the file and save it somewhere
            byte[] bytes = file.getBytes();
            Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
            Files.write(path, bytes);

            redirectAttributes.addFlashAttribute("message",
                    "You successfully uploaded '" + file.getOriginalFilename() + "'");


            Contenidos c = entityService.get(contenido.getId());
            Archivos archivo = new Archivos();
            archivo.setId(UUID.randomUUID().toString().replace("-", "_"));
            archivo.setDescrip(path.toString());
            archivo.setContenidos(c);
            c.getArchivos().add(archivo);
            entityService.save(c);


        } catch (IOException e) {
            e.printStackTrace();
        }

        return "redirect:/contenidos";
    }

    @GetMapping("/uploadStatus")
    public String uploadStatus() {
        return "/contenidos";
    }

    @GetMapping("/getFiles")
    public String GetFiles(Model model, @SessionAttribute("cont") Contenidos contenido) {
        Contenidos c = entityService.get(contenido.getId());

        model.addAttribute("entities", c.getArchivos());
        return "../contenidos/filesList :: filesList";
    }


    @RequestMapping("contenidos/deleteFile/{entity.id}")
    public String deleteFile(@PathVariable String id, @PathVariable Integer contenidoid, Model model) {

        Contenidos contenido = entityService.get(contenidoid);

        for (Archivos l : contenido.getArchivos()) {
            if (l.getId().equals(id)) {
                contenido.getArchivos().remove(l);
                break;
            }
        }
        entityService.save(contenido);

        return "redirect:/contenidos" + contenidoid;
    }

}










