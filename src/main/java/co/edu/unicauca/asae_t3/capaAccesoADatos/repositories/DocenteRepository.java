package co.edu.unicauca.asae_t3.capaAccesoADatos.repositories;

import co.edu.unicauca.asae_t3.capaAccesoADatos.models.DocenteEntity;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Repository;

@Repository("IDDocenteRepository")
public class DocenteRepository {

    private Map<Integer, DocenteEntity> mapaDocentes;

    public DocenteRepository() {
        this.mapaDocentes = new HashMap<>();
        cargarDocentes();
    }

    public DocenteEntity save(DocenteEntity docente) {
        this.mapaDocentes.put(docente.getIdDocente(), docente);
        return docente;
    }

    public Optional<DocenteEntity> findById(Integer id) {
        return Optional.ofNullable(this.mapaDocentes.get(id));
    }

    public Optional<Collection<DocenteEntity>> findAll() {
        return mapaDocentes.isEmpty() ? Optional.empty() : Optional.of(mapaDocentes.values());
    }

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

    public boolean delete(Integer id) {
        return this.mapaDocentes.remove(id) != null;
    }

    private void cargarDocentes() {
        System.out.println("Cargando docentes de ejemplo...");
        this.mapaDocentes.put(1, new DocenteEntity(1, "Juan", "Pérez", "juah@example.com", "planta"));
        this.mapaDocentes.put(2, new DocenteEntity(2, "Pedro", "Gómez", "pedro@example.com", "planta"));
        this.mapaDocentes.put(3, new DocenteEntity(3, "Ana", "Mora", "ana@example.com", "catedratico"));
        this.mapaDocentes.put(4, new DocenteEntity(4, "Luis", "García", "luis@example.com", "ocasional"));
        this.mapaDocentes.put(5, new DocenteEntity(5, "María", "Rodríguez", "maria@example.com", "planta"));
    }
}
