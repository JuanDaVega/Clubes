package com.fcBarcelona.modelo;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class equipo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    private String ciudad;

    @OneToOne
    @JoinColumn(name = "entrenador_id")
    private entrenador entrenador;

    @OneToMany(mappedBy = "equipo", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<jugador> jugadores;

    // Getters y Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public entrenador getEntrenador() {
        return entrenador;
    }

    public void setEntrenador(entrenador entrenador) {
        this.entrenador = entrenador;
    }

    public List<jugador> getJugadores() {
        return jugadores;
    }

    public void setJugadores(List<jugador> jugadores) {
        this.jugadores = jugadores;
    }
}
