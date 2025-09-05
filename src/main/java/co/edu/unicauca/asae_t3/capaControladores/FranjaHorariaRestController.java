package co.edu.unicauca.asae_t3.capaControladores;


import java.util.List;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.unicauca.asae_t3.fachadaServices.DTO.FranjaHorariaDTOPeticion;
import co.edu.unicauca.asae_t3.fachadaServices.DTO.FranjaHorariaDTORespuesta;
import co.edu.unicauca.asae_t3.fachadaServices.services.IFranjaHorariaService;

@RestController
@RequestMapping("/api/franjas")
public class FranjaHorariaRestController {

    @Autowired
    @Qualifier("IDFranjaHorariaService")
    private IFranjaHorariaService franjaHorariaService;


    @GetMapping
    public List<FranjaHorariaDTORespuesta> findAll() {
        return franjaHorariaService.findAll();
    }

    // Consultar una franja horaria por id (usando path variable)
    @GetMapping("/{id}")
    public FranjaHorariaDTORespuesta findById(@PathVariable Integer id) {
        return franjaHorariaService.findById(id);
    }

    // Crear un cuadre de franja horaria
    @PostMapping
    public FranjaHorariaDTORespuesta create(@RequestBody FranjaHorariaDTOPeticion franjaHoraria) {
        return franjaHorariaService.save(franjaHoraria);
    }

    // Actualizar información de una franja horaria
    @PutMapping("/{id}")
    public FranjaHorariaDTORespuesta update(@PathVariable Integer id, @RequestBody FranjaHorariaDTOPeticion franjaHoraria) {
        return franjaHorariaService.update(id, franjaHoraria);
    }

    // Eliminar una franja de cuadre horaria (usando request param)
    @DeleteMapping
    public boolean delete(@RequestParam Integer id) {
        return franjaHorariaService.deleteById(id);
    }
}
