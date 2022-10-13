package ar.com.proyectfinal.entities;

public class LinksView {
    private String id;
    private String descrip;
    private String link;
    private Integer contenidoid;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescrip() {
        return descrip;
    }

    public void setDescrip(String descrip) {
        this.descrip = descrip;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Integer getContenidoid() {
        return contenidoid;
    }

    public void setContenidoid(Integer contenidoid) {
        this.contenidoid = contenidoid;
    }
}
