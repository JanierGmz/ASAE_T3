package co.edu.unicauca.asae_t3.fachadaServices.chainResponsibility.handlers;

import co.edu.unicauca.asae_t3.fachadaServices.DTO.FranjaHorariaDTOPeticion;
import co.edu.unicauca.asae_t3.fachadaServices.chainResponsibility.chain.SolicitudFranjaHoraria;

import co.edu.unicauca.asae_t3.fachadaServices.exceptions.FormatoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import co.edu.unicauca.asae_t3.capaAccesoADatos.repositories.EspacioFisicoRepository;

@Component
public class EspacioFisicoInactivo extends SolicitudFranjaHoraria {

    @Autowired
    @Qualifier("IDEspacioFisicoRepository")
    private EspacioFisicoRepository espacioFisicoRepository;

    @Override
    public boolean procesarSolicitud(FranjaHorariaDTOPeticion solicitudFranjaHoraria) {
        Integer idEspacioFisico = solicitudFranjaHoraria.getIdEspacioFisico();
        if (idEspacioFisico != null) {
            var espacioOpt = espacioFisicoRepository.findById(idEspacioFisico);
            if (espacioOpt.isPresent()) {
                var espacio = espacioOpt.get();
                if (espacio.getEstado() != null && !espacio.getEstado()) {
                    // Si está inactivo, no se permite la asignación
                    throw new FormatoException("El espacio físico está inactivo.");
                }
            }
        }
        // Si está activo, sigue con el siguiente manejador en la cadena
        if (this.getSiguiente() != null) {
            return this.getSiguiente().procesarSolicitud(solicitudFranjaHoraria);
        }
        return true;
    }

}
