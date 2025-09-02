package co.edu.unicauca.asae_t3.capaAccesoADatos.repositories;

import co.edu.unicauca.asae_t3.capaAccesoADatos.models.CursoEntity;

import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository("IDCursoRepository")
public class CursoRepository {
	private Map<Integer, CursoEntity> mapaCursos;

	public CursoRepository() {
		this.mapaCursos = new java.util.HashMap<>();
		cargarCursos();
	}

	public CursoEntity save(CursoEntity curso) {
		this.mapaCursos.put(curso.getIdCurso(), curso);
		return curso;
	}

	public Optional<CursoEntity> findById(Integer id) {
		return Optional.ofNullable(this.mapaCursos.get(id));
	}

	public Optional<Collection<CursoEntity>> findAll() {
		return mapaCursos.isEmpty() ? Optional.empty() : Optional.of(mapaCursos.values());
	}

	public Optional<CursoEntity> update(Integer id, CursoEntity curso) {
		Optional<CursoEntity> respuesta;
		if (this.mapaCursos.containsKey(id)) {
			this.mapaCursos.put(id, curso);
			respuesta = Optional.of(curso);
		} else {
			respuesta = Optional.empty();
		}
		return respuesta;
	}

	public boolean delete(Integer id) {
		return this.mapaCursos.remove(id) != null;
	}

	private void cargarCursos() {
		System.out.println("Cargando cursos de ejemplo...");
		this.mapaCursos.put(1, new CursoEntity(1, "CAL01A", "Cálculo I - Grupo A", 30));
		this.mapaCursos.put(2, new CursoEntity(2, "MEC01A", "Mecánica - Grupo A", 25));
		this.mapaCursos.put(3, new CursoEntity(3, "POO01A", "Programación Orientada a Objetos - Grupo A", 30));
		this.mapaCursos.put(4, new CursoEntity(4, "POO01B", "Programación Orientada a Objetos - Grupo B", 35));
		this.mapaCursos.put(5, new CursoEntity(5, "BD01", "Bases de Datos I - Grupo A", 30));
	}
}
