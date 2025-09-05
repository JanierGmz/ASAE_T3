package co.edu.unicauca.asae_t3.fachadaServices.chainResponsibility.handlers;

import co.edu.unicauca.asae_t3.fachadaServices.DTO.FranjaHorariaDTOPeticion;
import co.edu.unicauca.asae_t3.fachadaServices.chainResponsibility.chain.SolicitudFranjaHoraria;

import java.util.List;

import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.time.LocalTime;

@Component
public class Horario extends SolicitudFranjaHoraria {
    @Override
    public boolean procesarSolicitud(FranjaHorariaDTOPeticion solicitudFranjaHoraria) {
        // Validar hora: solo entre 6 am y 10 pm
        LocalTime horaInicio = solicitudFranjaHoraria.getHoraInicio();
        LocalTime horaFin = solicitudFranjaHoraria.getHoraFin();
        LocalTime minHora = LocalTime.of(6, 0);
        LocalTime maxHora = LocalTime.of(22, 0);

        if (horaInicio.isBefore(minHora) || horaFin.isAfter(maxHora)) {
            return false;
        }

        // Validar día: solo de lunes a sábado
        String dia = solicitudFranjaHoraria.getDia().toLowerCase().trim();
        List<String> diasValidos = Arrays.asList("lunes", "martes", "miércoles", "miercoles","jueves", "viernes", "sábado", "sabado");
        if (!diasValidos.contains(dia)) {
            return false;
        }

        // Si pasa la validación, sigue con el siguiente manejador
        if (this.getSiguiente() != null) {
            return this.getSiguiente().procesarSolicitud(solicitudFranjaHoraria);
        }
        return true;
    }

}
