package co.edu.unicauca.asae_t3.capaAccesoADatos.repositories;

import co.edu.unicauca.asae_t3.capaAccesoADatos.models.AsignaturaEntity;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Repository;

@Repository("IDAsignaturaRepository")
public class AsignaturaRepository {

	private Map<Integer, AsignaturaEntity> mapaAsignaturas;

	public AsignaturaRepository() {
		this.mapaAsignaturas = new HashMap<>();
		cargarAsignaturas();
	}

	public Optional<Collection<AsignaturaEntity>> findAll() {
		return mapaAsignaturas.isEmpty() ? Optional.empty() : Optional.of(mapaAsignaturas.values());
	}

	public Optional<AsignaturaEntity> findById(Integer id) {
		return Optional.ofNullable(mapaAsignaturas.get(id));
	}

	public AsignaturaEntity save(AsignaturaEntity asignatura) {
		this.mapaAsignaturas.put(asignatura.getIdAsignatura(), asignatura);
		return asignatura;
	}

	public Optional<AsignaturaEntity> update(Integer id, AsignaturaEntity asignatura) {
		Optional<AsignaturaEntity> respuesta;
		if (this.mapaAsignaturas.containsKey(id)) {
			this.mapaAsignaturas.put(id, asignatura);
			respuesta = Optional.of(asignatura);
		} else {
			respuesta = Optional.empty();
		}
		return respuesta;
	}

	public boolean delete(Integer id) {
		return this.mapaAsignaturas.remove(id) != null;
	}

	private void cargarAsignaturas() {
	this.mapaAsignaturas.put(1, new AsignaturaEntity(1, "Matemáticas", "MAT101", 3, 4, "Asignatura básica de matemáticas"));
	this.mapaAsignaturas.put(2, new AsignaturaEntity(2, "Física", "FIS101", 4, 5, "Asignatura básica de física"));
	this.mapaAsignaturas.put(3, new AsignaturaEntity(3, "Química", "QUI101", 3, 4, "Asignatura básica de química"));
	this.mapaAsignaturas.put(4, new AsignaturaEntity(4, "Programación", "PRO101", 4, 6, "Asignatura básica de programación"));
	this.mapaAsignaturas.put(5, new AsignaturaEntity(5, "Estadística", "EST101", 3, 4, "Asignatura básica de estadística"));
	}
}
	
