/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.proyectfinal.entities;

import javax.persistence.*;

@Entity
@Table(name="archivos")
public class Archivos {

    @Id

    private String id;
    private String descrip;
    private String formato;

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

    public String getFormato() {
        return formato;
    }

    public void setFormato(String formato) {
        this.formato = formato;
    }

    public Contenidos getContenidos() {
        return contenidos;
    }

    public void setContenidos(Contenidos contenidos) {
        this.contenidos = contenidos;
    }
}
