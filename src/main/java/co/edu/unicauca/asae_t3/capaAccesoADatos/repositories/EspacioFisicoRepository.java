package co.edu.unicauca.asae_t3.capaAccesoADatos.repositories;

import org.springframework.stereotype.Repository;
import co.edu.unicauca.asae_t3.capaAccesoADatos.models.EspacioFisicoEntity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository("IDEspacioFisicoRepository")
public class EspacioFisicoRepository {
	private List<EspacioFisicoEntity> listaEspaciosFisicos;

	public EspacioFisicoRepository() {
		this.listaEspaciosFisicos = new ArrayList<>();
		cargarEspaciosFisicos();
	}

	public EspacioFisicoEntity save(EspacioFisicoEntity espacioFisico) {
		this.listaEspaciosFisicos.add(espacioFisico);
		return espacioFisico;
	}

	public Optional<EspacioFisicoEntity> findById(Integer id) {
		return Optional.ofNullable(this.listaEspaciosFisicos.stream()
				.filter(espacio -> espacio.getIdEspacioFisico().equals(id))
				.findFirst()
				.orElse(null));
	}

	public Optional<Collection<EspacioFisicoEntity>> findAll() {
		return listaEspaciosFisicos.isEmpty() ? Optional.empty() : Optional.of(listaEspaciosFisicos);
	}

	public void deleteById(Integer id) {
		this.listaEspaciosFisicos.removeIf(espacio -> espacio.getIdEspacioFisico().equals(id));
	}

	public EspacioFisicoEntity update(EspacioFisicoEntity espacioFisico) {
		Optional<EspacioFisicoEntity> espacioOpt = findById(espacioFisico.getIdEspacioFisico());
		if (espacioOpt.isPresent()) {
			EspacioFisicoEntity espacioToUpdate = espacioOpt.get();
			espacioToUpdate.setNombre(espacioFisico.getNombre());
			espacioToUpdate.setUbicacion(espacioFisico.getUbicacion());
			espacioToUpdate.setCapacidad(espacioFisico.getCapacidad());
			espacioToUpdate.setEstado(espacioFisico.getEstado());
			return espacioToUpdate;
		}
		return null;
	}

	private void cargarEspaciosFisicos() {
		this.listaEspaciosFisicos.add(new EspacioFisicoEntity(1, "Aula 101", "Aula", 30, "Edificio A", true));
        this.listaEspaciosFisicos.add(new EspacioFisicoEntity(2, "Laboratorio de Computación", "Laboratorio", 20, "Edificio B", true));
        this.listaEspaciosFisicos.add(new EspacioFisicoEntity(3, "Auditorio Principal", "Auditorio", 100, "Edificio C", true));
        this.listaEspaciosFisicos.add(new EspacioFisicoEntity(4, "Aula 202", "Aula", 25, "Edificio A", true));
        this.listaEspaciosFisicos.add(new EspacioFisicoEntity(5, "Laboratorio de Física", "Laboratorio", 15, "Edificio D", true));
	}
}
