package co.edu.unicauca.asae_t3.capaAccesoADatos.repositories;

import co.edu.unicauca.asae_t3.capaAccesoADatos.models.DocenteEntity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

@Repository("IDDocenteRepository")
public class DocenteRepository {

    private List<DocenteEntity> listaDocentes;

    public DocenteRepository() {
        this.listaDocentes = new ArrayList<>();
        cargarDocentes();
    }

    public DocenteEntity save(DocenteEntity docente) {
        this.listaDocentes.add(docente);
        return docente;
    }

    public Optional<DocenteEntity> findById(Integer id) {
        return Optional.ofNullable(this.listaDocentes.stream()
                .filter(docente -> docente.getIdDocente().equals(id))
                .findFirst()
                .orElse(null));
    }

    public Optional<Collection<DocenteEntity>> findAll() {
        return listaDocentes.isEmpty() ? Optional.empty() : Optional.of(listaDocentes);
    }

    public void deleteById(Integer id) {
        this.listaDocentes.removeIf(docente -> docente.getIdDocente().equals(id));
    }

    public DocenteEntity update(DocenteEntity docente) {
        Optional<DocenteEntity> docenteOpt = findById(docente.getIdDocente());
        if (docenteOpt.isPresent()) {
            DocenteEntity docenteToUpdate = docenteOpt.get();
            docenteToUpdate.setNombres(docente.getNombres());
            docenteToUpdate.setApellidos(docente.getApellidos());
            docenteToUpdate.setCorreoInstitucional(docente.getCorreoInstitucional());
            docenteToUpdate.setCategoria(docente.getCategoria());
            docenteToUpdate.setEstado(docente.getEstado());
            return docenteToUpdate;
        }
        return null;
    }

    private void cargarDocentes() {
        System.out.println("Cargando docentes de ejemplo...");
        this.listaDocentes.add(new DocenteEntity(1, "Juan", "Pérez", "juah@example.com", "planta", true));
        this.listaDocentes.add(new DocenteEntity(2, "Pedro", "Gómez", "pedro@example.com", "planta", true));
        this.listaDocentes.add(new DocenteEntity(3, "Ana", "Mora", "ana@example.com", "catedratico", true));
        this.listaDocentes.add(new DocenteEntity(4, "Luis", "García", "luis@example.com", "ocasional", true));
        this.listaDocentes.add(new DocenteEntity(5, "María", "Rodríguez", "maria@example.com", "planta", true));
    }
}
