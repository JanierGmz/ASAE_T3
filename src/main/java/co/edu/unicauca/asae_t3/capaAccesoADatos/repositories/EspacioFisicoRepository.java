package co.edu.unicauca.asae_t3.capaAccesoADatos.repositories;

import org.springframework.stereotype.Repository;
import co.edu.unicauca.asae_t3.capaAccesoADatos.models.EspacioFisicoEntity;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Repository("IDEspacioFisicoRepository")
public class EspacioFisicoRepository {
	private Map<Integer, EspacioFisicoEntity> mapaEspaciosFisicos;

	public EspacioFisicoRepository() {
		this.mapaEspaciosFisicos = new HashMap<>();
		cargarEspaciosFisicos();
	}

	public EspacioFisicoEntity save(EspacioFisicoEntity espacioFisico) {
		this.mapaEspaciosFisicos.put(espacioFisico.getIdEspacioFisico(), espacioFisico);
		return espacioFisico;
	}

	public Optional<EspacioFisicoEntity> findById(Integer id) {
		return Optional.ofNullable(this.mapaEspaciosFisicos.get(id));
	}

	public Optional<Collection<EspacioFisicoEntity>> findAll() {
		return mapaEspaciosFisicos.isEmpty() ? Optional.empty() : Optional.of(mapaEspaciosFisicos.values());
	}

	public Optional<EspacioFisicoEntity> update(Integer id, EspacioFisicoEntity espacioFisico) {
		Optional<EspacioFisicoEntity> respuesta;
		if (this.mapaEspaciosFisicos.containsKey(id)) {
			this.mapaEspaciosFisicos.put(id, espacioFisico);
			respuesta = Optional.of(espacioFisico);
		} else {
			respuesta = Optional.empty();
		}
		return respuesta;
	}

	public boolean delete(Integer id) {
		return this.mapaEspaciosFisicos.remove(id) != null;
	}

	private void cargarEspaciosFisicos() {
		System.out.println("Cargando espacios físicos de ejemplo...");
		this.mapaEspaciosFisicos.put(1, new EspacioFisicoEntity(1, "Aula 101", "Aula", 30, "Edificio A"));
		this.mapaEspaciosFisicos.put(2, new EspacioFisicoEntity(2, "Laboratorio de Computación", "Laboratorio", 20, "Edificio B"));
		this.mapaEspaciosFisicos.put(3, new EspacioFisicoEntity(3, "Auditorio Principal", "Auditorio", 100, "Edificio C"));
		this.mapaEspaciosFisicos.put(4, new EspacioFisicoEntity(4, "Aula 202", "Aula", 25, "Edificio A"));
		this.mapaEspaciosFisicos.put(5, new EspacioFisicoEntity(5, "Laboratorio de Física", "Laboratorio", 15, "Edificio D"));
	}
}
