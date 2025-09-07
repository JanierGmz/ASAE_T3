package co.edu.unicauca.asae_t3.capaAccesoADatos.repositories;

import co.edu.unicauca.asae_t3.capaAccesoADatos.models.FranjaHorariaEntity;

import java.util.Collection;
import java.util.Optional;
import java.time.LocalTime;

public interface IFranjaHorariaRepository {

    public FranjaHorariaEntity save(FranjaHorariaEntity franja);

    public Optional<FranjaHorariaEntity> findById(Integer id);

    public Optional<Collection<FranjaHorariaEntity>> findAll();

    public Optional<FranjaHorariaEntity> update(Integer id, FranjaHorariaEntity franja);

    public boolean delete(Integer id);

    public Collection<FranjaHorariaEntity> findByEspacioFisicoAndDiaAndHorario(
            Integer idEspacioFisico, String dia, LocalTime horaInicio, LocalTime horaFin);

    public Collection<FranjaHorariaEntity> findByCurso(Integer idCurso);

}
