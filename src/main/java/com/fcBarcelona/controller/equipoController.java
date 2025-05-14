package com.fcBarcelona.controller;

import com.fcBarcelona.modelo.equipo;
import com.fcBarcelona.modelo.entrenador;

import com.fcBarcelona.repository.equipoRepository;
import com.fcBarcelona.repository.entrenadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/equipos")
public class equipoController {

    @Autowired
    private equipoRepository equipoRepository;

    @Autowired
    private entrenadorRepository entrenadorRepository;

    @GetMapping("/formulario")
    public String mostrarFormularioEquipo(Model model) {
        model.addAttribute("equipo", new equipo());
        model.addAttribute("entrenadores", entrenadorRepository.findAll());
        return "formulario_equipo"; 
    }

    @PostMapping("/guardar")
    public String guardarEquipo(@ModelAttribute equipo equipo) {
        equipoRepository.save(equipo);
        return "redirect:/equipos/lista";
    }

    @GetMapping("/lista")
    public String listarEquipos(Model model) {
        List<equipo> equipos = equipoRepository.findAll();
        model.addAttribute("equipos", equipos);
        return "lista_equipos";
    }
    
    @GetMapping("/eliminar/{id}")
    public String eliminarEquipo(@PathVariable Long id) {
        equipo equipo = equipoRepository.findById(id).orElse(null);
        if (equipo != null) {
            if (equipo.getEntrenador() != null) {
                entrenador entrenador = equipo.getEntrenador();
                entrenador.setEquipo(null);
                entrenadorRepository.save(entrenador);
            }
            equipoRepository.delete(equipo);
        }
        return "redirect:/equipos/lista";
    }
    
    @GetMapping("/detalle/{id}")
    public String detalleEquipo(@PathVariable Long id, Model model) {
        equipo equipo = equipoRepository.findById(id).orElse(null);
        if (equipo != null) {
            model.addAttribute("equipo", equipo);
            model.addAttribute("entrenador", equipo.getEntrenador());
            model.addAttribute("jugadores", equipo.getJugadores());
        }
        return "detalle_equipo";
    }


}
