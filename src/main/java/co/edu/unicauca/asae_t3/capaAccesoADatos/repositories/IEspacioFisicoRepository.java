package co.edu.unicauca.asae_t3.capaAccesoADatos.repositories;

import co.edu.unicauca.asae_t3.capaAccesoADatos.models.EspacioFisicoEntity;

import java.util.Collection;
import java.util.Optional;

public interface IEspacioFisicoRepository {

    public EspacioFisicoEntity save(EspacioFisicoEntity espacioFisico);

    public Optional<EspacioFisicoEntity> findById(Integer id);

    public Optional<Collection<EspacioFisicoEntity>> findAll();

    public Optional<EspacioFisicoEntity> update(Integer id, EspacioFisicoEntity espacioFisico);

    public boolean delete(Integer id);

}
