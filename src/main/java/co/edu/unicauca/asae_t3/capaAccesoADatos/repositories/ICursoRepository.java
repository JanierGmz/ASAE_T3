package co.edu.unicauca.asae_t3.capaAccesoADatos.repositories;

import co.edu.unicauca.asae_t3.capaAccesoADatos.models.CursoEntity;

import java.util.Optional;

import java.util.Collection;

public interface ICursoRepository {

    public CursoEntity save(CursoEntity curso);

    public Optional<CursoEntity> findById(Integer id);

    public Optional<Collection<CursoEntity>> findAll();

    public Optional<CursoEntity> update(Integer id, CursoEntity curso);

    public boolean delete(Integer id);
}
