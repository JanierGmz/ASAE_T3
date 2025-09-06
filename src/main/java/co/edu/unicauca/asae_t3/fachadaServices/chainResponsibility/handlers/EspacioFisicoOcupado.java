
package co.edu.unicauca.asae_t3.fachadaServices.chainResponsibility.handlers;

import co.edu.unicauca.asae_t3.fachadaServices.DTO.FranjaHorariaDTOPeticion;
import co.edu.unicauca.asae_t3.fachadaServices.chainResponsibility.chain.SolicitudFranjaHoraria;

import co.edu.unicauca.asae_t3.fachadaServices.exceptions.FormatoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import co.edu.unicauca.asae_t3.capaAccesoADatos.repositories.EspacioFisicoRepository;
import co.edu.unicauca.asae_t3.capaAccesoADatos.repositories.FranjaHorariaRepository;

@Component
public class EspacioFisicoOcupado extends SolicitudFranjaHoraria{

    @Autowired
    @Qualifier("IDEspacioFisicoRepository")
    private EspacioFisicoRepository espacioFisicoRepository;

    @Autowired
    @Qualifier("IDFranjaHorariaRepository")
    private FranjaHorariaRepository franjaHorariaRepository;


    @Override
    public boolean procesarSolicitud(FranjaHorariaDTOPeticion solicitudFranjaHoraria) {
        Integer idEspacioFisico = solicitudFranjaHoraria.getIdEspacioFisico();
        if (idEspacioFisico != null) {
            // Verificar que el espacio físico existe y está activo
            var espacioOpt = espacioFisicoRepository.findById(idEspacioFisico);
            if (espacioOpt.isPresent()) {
                var espacio = espacioOpt.get();
                if (espacio.getEstado() != null && !espacio.getEstado()) {
                    // Si está inactivo, no se permite la asignación
                    throw new FormatoException("El espacio físico está inactivo.");
                }

                // Verificar que el espacio físico no esté ocupado en el mismo día y horario
                var franjasOcupadas = franjaHorariaRepository.findByEspacioFisicoAndDiaAndHorario(
                        idEspacioFisico,
                        solicitudFranjaHoraria.getDia(),
                        solicitudFranjaHoraria.getHoraInicio(),
                        solicitudFranjaHoraria.getHoraFin());

                if (!franjasOcupadas.isEmpty()) {
                    throw new FormatoException("El espacio físico ya está ocupado en el día " +
                            solicitudFranjaHoraria.getDia() + " entre las " +
                            solicitudFranjaHoraria.getHoraInicio() + " y " +
                            solicitudFranjaHoraria.getHoraFin() + ".");
                }
            } else {
                throw new FormatoException("El espacio físico con ID " + idEspacioFisico + " no existe.");
            }
        }
        // Si está activo y disponible, sigue con el siguiente manejador en la cadena
        if (this.getSiguiente() != null) {
            return this.getSiguiente().procesarSolicitud(solicitudFranjaHoraria);
        }
        return true;
    }

}
