package com.fcBarcelona.controller;

import com.fcBarcelona.modelo.entrenador;
import com.fcBarcelona.modelo.equipo;
import com.fcBarcelona.repository.entrenadorRepository;
import com.fcBarcelona.repository.equipoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/entrenadores")
public class entrenadorController {

    @Autowired
    private entrenadorRepository entrenadorRepository;

    @Autowired
    private equipoRepository equipoRepository;

    @GetMapping("/lista")
    public String listarEntrenadores(Model model) {
        List<entrenador> entrenadores = entrenadorRepository.findAll();
        model.addAttribute("entrenadores", entrenadores);
        return "lista_entrenadores"; 
    }

    @GetMapping("/formulario")
    public String mostrarFormulario(Model model) {
        model.addAttribute("entrenador", new entrenador());
        model.addAttribute("equipos", equipoRepository.findAll()); 
        return "formulario_entrenador";
    }

    @PostMapping("/guardar")
    public String guardarEntrenador(@ModelAttribute entrenador entrenador,
                                    @RequestParam Long equipoId) {
        equipo equipo = equipoRepository.findById(equipoId).orElse(null);
        if (equipo != null) {
            entrenador.setEquipo(equipo);
            equipo.setEntrenador(entrenador); 
        }
        entrenadorRepository.save(entrenador);
        equipoRepository.save(equipo);
        return "redirect:/entrenadores/lista";
    }
    
    @GetMapping("/eliminar/{id}")
    public String eliminarEntrenador(@PathVariable Long id) {
        entrenador entrenador = entrenadorRepository.findById(id).orElse(null);
        if (entrenador != null && entrenador.getEquipo() != null) {
            equipo equipo = entrenador.getEquipo();
            equipo.setEntrenador(null);
            equipoRepository.save(equipo);
        }
        entrenadorRepository.deleteById(id);
        return "redirect:/entrenadores/lista";
    }

}
