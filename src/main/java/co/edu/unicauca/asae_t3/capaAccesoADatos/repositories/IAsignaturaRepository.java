package co.edu.unicauca.asae_t3.capaAccesoADatos.repositories;

import co.edu.unicauca.asae_t3.capaAccesoADatos.models.AsignaturaEntity;

import java.util.Collection;
import java.util.Optional;

public interface IAsignaturaRepository {

    public Optional<Collection<AsignaturaEntity>> findAll();

    public Optional<AsignaturaEntity> findById(Integer id);

    public AsignaturaEntity save(AsignaturaEntity asignatura);

    public Optional<AsignaturaEntity> update(Integer id, AsignaturaEntity asignatura);

    public boolean delete(Integer id);

}
