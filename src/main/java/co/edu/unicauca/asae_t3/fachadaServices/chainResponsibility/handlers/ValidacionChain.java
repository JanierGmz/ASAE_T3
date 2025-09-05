package co.edu.unicauca.asae_t3.fachadaServices.chainResponsibility.handlers;

import co.edu.unicauca.asae_t3.fachadaServices.DTO.FranjaHorariaDTOPeticion;
import co.edu.unicauca.asae_t3.fachadaServices.chainResponsibility.chain.SolicitudFranjaHoraria;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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

    public void configurarCadena() {
        datosNoNulosHandler.setSiguiente(horarioHandler);
        horarioHandler.setSiguiente(espacioInactivoHandler);
        espacioInactivoHandler.setSiguiente(espacioOcupadoHandler);
        espacioOcupadoHandler.setSiguiente(docenteOcupadoHandler);
        System.out.println("Cadena de responsabilidad configurada correctamente.");
    }

    public boolean validar(FranjaHorariaDTOPeticion solicitudFranjaHoraria) {
        return this.datosNoNulosHandler.procesarSolicitud(solicitudFranjaHoraria);
    }

}
