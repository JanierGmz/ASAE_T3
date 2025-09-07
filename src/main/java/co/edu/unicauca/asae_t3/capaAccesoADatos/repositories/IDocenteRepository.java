package co.edu.unicauca.asae_t3.capaAccesoADatos.repositories;

import co.edu.unicauca.asae_t3.capaAccesoADatos.models.DocenteEntity;

import java.util.Collection;
import java.util.Optional;

public interface IDocenteRepository {

    public DocenteEntity save(DocenteEntity docente);

    public Optional<DocenteEntity> findById(Integer id);

    public Optional<Collection<DocenteEntity>> findAll();

    public Optional<DocenteEntity> update(Integer id, DocenteEntity docente);

    public boolean delete(Integer id);

}
