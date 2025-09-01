package co.edu.unicauca.asae_t3.capaAccesoADatos.repositories;

import co.edu.unicauca.asae_t3.capaAccesoADatos.models.AsignaturaEntity;
import co.edu.unicauca.asae_t3.capaAccesoADatos.models.CursoEntity;

import java.util.List;
import java.util.ArrayList;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository("IDCursoRepository")
public class CursoRepository {
	private List<CursoEntity> listaCursos;

	public CursoRepository() {
		this.listaCursos = new ArrayList<>();
		cargarCursos();
	}

	public CursoEntity save(CursoEntity curso) {
		this.listaCursos.add(curso);
		return curso;
	}

	public Optional<CursoEntity> findById(Integer id) {
		return Optional.ofNullable(this.listaCursos.stream()
				.filter(curso -> curso.getIdCurso().equals(id))
				.findFirst()
				.orElse(null));
	}

	public Optional<Collection<CursoEntity>> findAll() {
		return listaCursos.isEmpty() ? Optional.empty() : Optional.of(listaCursos);
	}

	public void deleteById(Integer id) {
		this.listaCursos.removeIf(curso -> curso.getIdCurso().equals(id));
	}

	public CursoEntity update(CursoEntity curso) {
		Optional<CursoEntity> cursoOpt = findById(curso.getIdCurso());
		if (cursoOpt.isPresent()) {
			CursoEntity cursoToUpdate = cursoOpt.get();
			cursoToUpdate.setNombre(curso.getNombre());
			cursoToUpdate.setCodigo(curso.getCodigo());
			cursoToUpdate.setAsignatura(curso.getAsignatura());
			return cursoToUpdate;
		}
		return null;
	}

	private void cargarCursos() {
		System.out.println("Cargando cursos de ejemplo...");
		AsignaturaRepository asignaturaRepo = new AsignaturaRepository();
		List<AsignaturaEntity> asignaturas = new ArrayList<>(asignaturaRepo.findAll().orElse(new ArrayList<>()));
		if (asignaturas.size() >= 5) {
			this.listaCursos.add(new CursoEntity(1, "Curso Ing. de Sistemas", "CUR101", asignaturas.get(0)));
			this.listaCursos.add(new CursoEntity(2, "Curso de Física", "CUR102", asignaturas.get(1)));
			this.listaCursos.add(new CursoEntity(3, "Curso de Química", "CUR103", asignaturas.get(2)));
			this.listaCursos.add(new CursoEntity(4, "Curso de Arquitectura", "CUR104", asignaturas.get(3)));
			this.listaCursos.add(new CursoEntity(5, "Curso de Contaduría", "CUR105", asignaturas.get(4)));
		}
	}
}
