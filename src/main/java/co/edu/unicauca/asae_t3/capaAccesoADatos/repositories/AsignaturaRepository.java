package co.edu.unicauca.asae_t3.capaAccesoADatos.repositories;

import co.edu.unicauca.asae_t3.capaAccesoADatos.models.AsignaturaEntity;

import java.util.List;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Collection;


import org.springframework.stereotype.Repository;

@Repository("IDAsignaturaRepository")
public class AsignaturaRepository {
	private List<AsignaturaEntity> listaAsignaturas;

	public AsignaturaRepository() {
		this.listaAsignaturas = new ArrayList<>();
		cargarAsignaturas();
	}

	public AsignaturaEntity save(AsignaturaEntity asignatura) {
		this.listaAsignaturas.add(asignatura);
		return asignatura;
	}

	public Optional<AsignaturaEntity> findById(Integer id) {
		return Optional.ofNullable(this.listaAsignaturas.stream()
				.filter(asignatura -> asignatura.getIdAsignatura().equals(id))
				.findFirst()
				.orElse(null));
	}

	public Optional<Collection<AsignaturaEntity>> findAll() {
		return listaAsignaturas.isEmpty() ? Optional.empty() : Optional.of(listaAsignaturas);
	}

	public void deleteById(Integer id) {
		this.listaAsignaturas.removeIf(asignatura -> asignatura.getIdAsignatura().equals(id));
	}

	public AsignaturaEntity update(AsignaturaEntity asignatura) {
		Optional<AsignaturaEntity> asignaturaOpt = findById(asignatura.getIdAsignatura());
		if (asignaturaOpt.isPresent()) {
			AsignaturaEntity asignaturaToUpdate = asignaturaOpt.get();
			asignaturaToUpdate.setNombre(asignatura.getNombre());
			asignaturaToUpdate.setCodigo(asignatura.getCodigo());
			asignaturaToUpdate.setNumeroCreditos(asignatura.getNumeroCreditos());
            asignaturaToUpdate.setHorasSemanales(asignatura.getHorasSemanales());
            asignaturaToUpdate.setDescripcion(asignatura.getDescripcion());
			asignaturaToUpdate.setEstado(asignatura.getEstado());
			return asignaturaToUpdate;
		}
		return null;
	}

	private void cargarAsignaturas() {
		System.out.println("Cargando asignaturas de ejemplo...");
		this.listaAsignaturas.add(new AsignaturaEntity(1, "Matemáticas", "MAT101", 3, 4, "Introducción a las matemáticas", true));
        this.listaAsignaturas.add(new AsignaturaEntity(2, "Física", "FIS101", 4, 4, "Fundamentos de física", true));
        this.listaAsignaturas.add(new AsignaturaEntity(3, "Química", "QUI101", 3, 4, "Conceptos básicos de química", true));
        this.listaAsignaturas.add(new AsignaturaEntity(4, "Programación", "PRO101", 4, 4, "Introducción a la programación", true));
        this.listaAsignaturas.add(new AsignaturaEntity(5, "Estadística", "EST101", 3, 4, "Fundamentos de estadística", true));
	}
}
