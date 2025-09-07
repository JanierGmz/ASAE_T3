package co.edu.unicauca.asae_t3.fachadaServices.chainResponsibility.handlers;

import co.edu.unicauca.asae_t3.fachadaServices.DTO.FranjaHorariaDTOPeticion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import jakarta.annotation.PostConstruct;

@Component
public class ValidacionChain {

    @Autowired
    private DatosNoNulos datosNoNulosHandler;
    @Autowired
    private Horario horarioHandler;
    @Autowired
    private EspacioFisicoInactivo espacioInactivoHandler;
    @Autowired
    private EspacioFisicoOcupado espacioOcupadoHandler;
    @Autowired
    private DocenteOcupado docenteOcupadoHandler;

    @PostConstruct
    public void configurarCadena() {
        datosNoNulosHandler.setSiguiente(horarioHandler);
        horarioHandler.setSiguiente(espacioInactivoHandler);
        espacioInactivoHandler.setSiguiente(espacioOcupadoHandler);
        espacioOcupadoHandler.setSiguiente(docenteOcupadoHandler);
    }

    public boolean validar(FranjaHorariaDTOPeticion solicitudFranjaHoraria) {
        return this.datosNoNulosHandler.procesarSolicitud(solicitudFranjaHoraria);
    }

}
