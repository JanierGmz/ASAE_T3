package co.edu.unicauca.asae_t3.capaControladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
