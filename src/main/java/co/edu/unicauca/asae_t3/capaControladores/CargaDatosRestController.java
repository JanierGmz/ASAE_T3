package co.edu.unicauca.asae_t3.capaControladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.unicauca.asae_t3.fachadaServices.services.ICargaDatos;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.http.ResponseEntity;



@RestController
@RequestMapping("/api/datos")
public class CargaDatosRestController {

    @Autowired
    @Qualifier("IDCargaDatosService")
    private ICargaDatos cargaDatosService;

   @GetMapping()
    public ResponseEntity<String> cargarDatos() {
        String mensaje = cargaDatosService.cargarDatos();
        return ResponseEntity.ok(mensaje);
    }
}
