package com.fcBarcelona.controller;

import com.fcBarcelona.modelo.equipo;
import com.fcBarcelona.modelo.jugador;
import com.fcBarcelona.repository.equipoRepository;
import com.fcBarcelona.repository.jugadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/jugadores")
public class jugadorController {

    @Autowired
    private jugadorRepository jugadorRepository;

    @Autowired
    private equipoRepository equipoRepository;

    @GetMapping("/lista")
    public String listarJugadores(Model model) {
        List<jugador> jugadores = jugadorRepository.findAll();
        model.addAttribute("jugadores", jugadores);
        return "lista_jugadores";
    }

    @GetMapping("/formulario")
    public String mostrarFormulario(Model model) {
        model.addAttribute("jugador", new jugador());
        model.addAttribute("equipos", equipoRepository.findAll());
        return "formulario_jugador";
    }

    @PostMapping("/guardar")
    public String guardarJugador(@ModelAttribute jugador jugador,
                                  @RequestParam Long equipoId) {
        equipo equipo = equipoRepository.findById(equipoId).orElse(null);
        if (equipo != null) {
            jugador.setEquipo(equipo);
        }
        jugadorRepository.save(jugador);
        return "redirect:/jugadores/lista";
    }
    
    @GetMapping("/eliminar/{id}")
    public String eliminarJugador(@PathVariable Long id) {
        jugadorRepository.deleteById(id);
        return "redirect:/jugadores/lista";
    }

}
