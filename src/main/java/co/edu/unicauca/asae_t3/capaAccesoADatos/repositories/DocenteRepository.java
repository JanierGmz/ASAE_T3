package co.edu.unicauca.asae_t3.capaAccesoADatos.repositories;

import co.edu.unicauca.asae_t3.capaAccesoADatos.models.DocenteEntity;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Repository;

@Repository("IDDocenteRepository")
public class DocenteRepository implements IDocenteRepository {

    private Map<Integer, DocenteEntity> mapaDocentes;

    public DocenteRepository() {
        this.mapaDocentes = new HashMap<>();
        cargarDocentes();
    }

    @Override
    public DocenteEntity save(DocenteEntity docente) {
        this.mapaDocentes.put(docente.getIdDocente(), docente);
        return docente;
    }

    @Override
    public Optional<DocenteEntity> findById(Integer id) {
        return Optional.ofNullable(this.mapaDocentes.get(id));
    }

    @Override
    public Optional<Collection<DocenteEntity>> findAll() {
        return mapaDocentes.isEmpty() ? Optional.empty() : Optional.of(mapaDocentes.values());
    }

    @Override
    public Optional<DocenteEntity> update(Integer id, DocenteEntity docente) {
        Optional<DocenteEntity> respuesta;
        if (this.mapaDocentes.containsKey(id)) {
            this.mapaDocentes.put(id, docente);
            respuesta = Optional.of(docente);
        } else {
            respuesta = Optional.empty();
        }
        return respuesta;
    }

    @Override
    public boolean delete(Integer id) {
        return this.mapaDocentes.remove(id) != null;
    }

    private void cargarDocentes() {
        this.mapaDocentes.put(1, new DocenteEntity(1, "Hernán", "Cortes", "hcortes@unicauca.edu.co", "planta"));
        this.mapaDocentes.put(2, new DocenteEntity(2, "Henry", "Laniado", "hlaniado@unicauca.edu.co", "planta"));
        this.mapaDocentes.put(3, new DocenteEntity(3, "Carlos", "Orozco", "carlosorozco@unicauca.edu.co", "catedratico"));
        this.mapaDocentes.put(4, new DocenteEntity(4, "Jimena", "Timaná", "jtimana@unicauca.edu.co", "ocasional"));
        this.mapaDocentes.put(5, new DocenteEntity(5, "Fabio", "Realpe", "frealpe@unicauca.edu.co", "planta"));
    }
}
