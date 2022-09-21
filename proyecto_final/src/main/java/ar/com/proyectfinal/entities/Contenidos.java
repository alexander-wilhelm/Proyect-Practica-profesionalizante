/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.proyectfinal.entities;

import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.*;


@Entity
@Table(name="contenidos")
public class Contenidos implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String descrip;
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private Date fechacreacion;
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private Date fechaalta;
    private String titulo;
    private String tag;
    private String tipo;
    private Boolean habilitado;
    private Boolean baja;

    @JoinColumn(name= "fk_usuarios", referencedColumnName = "id",nullable=false)
    @ManyToOne
    private Usuarios usuarios;

    @JoinColumn(name= "fk_categorias", referencedColumnName = "id",nullable=false)
    @ManyToOne
    private Categorias categorias;

    @OneToMany (mappedBy = "contenidos", cascade = CascadeType.ALL, orphanRemoval=true)
    private List<Links> links;

    @OneToMany (mappedBy = "contenidos", cascade = CascadeType.ALL, orphanRemoval=true)
    private List<Archivos> archivos;

    /* Constructor para cuando hay colecciones

     */
    public Contenidos() {
        links = new ArrayList<Links>();
        archivos = new ArrayList<Archivos>();
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescrip() {
        return descrip;
    }

    public void setDescrip(String descrip) {
        this.descrip = descrip;
    }

    public Date getFechacreacion() {
        return fechacreacion;
    }

    public void setFechacreacion(Date fechacreacion) {
        this.fechacreacion = fechacreacion;
    }

    public Date getFechaalta() {
        return fechaalta;
    }

    public void setFechaalta(Date fechaalta) {
        this.fechaalta = fechaalta;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Boolean getHabilitado() {
        return habilitado;
    }

    public void setHabilitado(Boolean habilitado) {
        this.habilitado = habilitado;
    }

    public Boolean getBaja() {
        return baja;
    }

    public void setBaja(Boolean baja) {
        this.baja = baja;
    }

    public Usuarios getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(Usuarios usuarios) {
        this.usuarios = usuarios;
    }

    public Categorias getCategorias() {
        return categorias;
    }

    public void setCategorias(Categorias categorias) {
        this.categorias = categorias;
    }
}
