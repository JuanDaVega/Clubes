package com.fcBarcelona.controller;

import com.fcBarcelona.modelo.equipo;
import com.fcBarcelona.modelo.jugador;
import com.fcBarcelona.repository.equipoRepository;
import com.fcBarcelona.repository.entrenadorRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PrincipalControlador {

    @Autowired
    private equipoRepository equipoRepository;

    @Autowired
    private entrenadorRepository entrenadorRepository;

    @GetMapping("/formulario")
    public String mostrarFormulario(Model model) {
        model.addAttribute("jugador", new jugador());
        model.addAttribute("equipo", new equipo());

        model.addAttribute("equipos", equipoRepository.findAll());
        model.addAttribute("entrenadores", entrenadorRepository.findAll());

        return "form";
    }

    @GetMapping("/")
    public String index() {
        return "index";
    }
}
