package co.edu.unicauca.asae_t3.fachadaServices.chainResponsibility.handlers;

import co.edu.unicauca.asae_t3.fachadaServices.DTO.FranjaHorariaDTOPeticion;
import co.edu.unicauca.asae_t3.fachadaServices.chainResponsibility.chain.SolicitudFranjaHoraria;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import co.edu.unicauca.asae_t3.capaAccesoADatos.repositories.DocenteRepository;

@Component
public class DocenteOcupado extends SolicitudFranjaHoraria {

    @Qualifier("IDDocenteRepository")
    private DocenteRepository docenteRepository;

    @Override
    public boolean procesarSolicitud(FranjaHorariaDTOPeticion solicitudFranjaHoraria) {
        var idDocentes = solicitudFranjaHoraria.getIdDocentes();
        if (idDocentes != null) {
            for (var idDocente : idDocentes) {
                var docenteOpt = docenteRepository.findById(idDocente);
                if (docenteOpt.isPresent()) {
                    var docente = docenteOpt.get();
                    if (docente.getFranjasHorarias() != null) {
                        for (var franja : docente.getFranjasHorarias()) {
                            if (franja.getDia().equals(solicitudFranjaHoraria.getDia())) {
                                var inicioNueva = solicitudFranjaHoraria.getHoraInicio();
                                var finNueva = solicitudFranjaHoraria.getHoraFin();
                                var inicioExistente = franja.getHoraInicio();
                                var finExistente = franja.getHoraFin();
                                if (!(finNueva.isBefore(inicioExistente) || inicioNueva.isAfter(finExistente))) {
                                    // Docente ocupado
                                    return false;
                                }
                            }
                        }
                    }
                }
            }
        }
        if (this.getSiguiente() != null) {
            return this.getSiguiente().procesarSolicitud(solicitudFranjaHoraria);
        }
        return true;
    }

}
