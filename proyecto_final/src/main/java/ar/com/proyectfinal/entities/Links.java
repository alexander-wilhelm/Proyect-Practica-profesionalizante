/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.proyectfinal.entities;

import javax.persistence.*;



@Entity
@Table(name="links")
public class Links {

    @Id

    private String id;
    private String descrip;
    private String link;

    @JoinColumn(name= "fk_contenidos", referencedColumnName = "id",nullable=false)
    @ManyToOne
    private Contenidos contenidos;


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

    public Contenidos getContenidos() {
        return contenidos;
    }

    public void setContenidos(Contenidos contenidos) {
        this.contenidos = contenidos;
    }
}
