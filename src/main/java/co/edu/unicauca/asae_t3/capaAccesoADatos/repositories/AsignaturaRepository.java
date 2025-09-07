package co.edu.unicauca.asae_t3.capaAccesoADatos.repositories;

import co.edu.unicauca.asae_t3.capaAccesoADatos.models.AsignaturaEntity;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Repository;

@Repository("IDAsignaturaRepository")
public class AsignaturaRepository implements IAsignaturaRepository{

	private Map<Integer, AsignaturaEntity> mapaAsignaturas;

	public AsignaturaRepository() {
		this.mapaAsignaturas = new HashMap<>();
		cargarAsignaturas();
	}

	@Override
	public Optional<Collection<AsignaturaEntity>> findAll() {
		return mapaAsignaturas.isEmpty() ? Optional.empty() : Optional.of(mapaAsignaturas.values());
	}

	@Override
	public Optional<AsignaturaEntity> findById(Integer id) {
		return Optional.ofNullable(mapaAsignaturas.get(id));
	}

	@Override
	public AsignaturaEntity save(AsignaturaEntity asignatura) {
		this.mapaAsignaturas.put(asignatura.getIdAsignatura(), asignatura);
		return asignatura;
	}

	@Override
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

	@Override
	public boolean delete(Integer id) {
		return this.mapaAsignaturas.remove(id) != null;
	}

	private void cargarAsignaturas() {
		this.mapaAsignaturas.put(1, new AsignaturaEntity(1, "Cálculo I", "CAL01", 3, 4, "Cálculo diferencial."));
		this.mapaAsignaturas.put(2, new AsignaturaEntity(2, "Mecánica", "MEC01", 4, 5, "Principios de mecánica clásica."));
		this.mapaAsignaturas.put(3, new AsignaturaEntity(3, "Programación Orientada a Objetos", "POO01", 3, 4, "Diseño y desarrollo de software orientado a objetos."));
		this.mapaAsignaturas.put(4, new AsignaturaEntity(4, "Bases de datos I", "BD01", 4, 6, "Administración y diseño de bases de datos."));
		this.mapaAsignaturas.put(5, new AsignaturaEntity(5, "Estadística", "EST01", 3, 4, "Principios de estadística."));
	}
}
	
