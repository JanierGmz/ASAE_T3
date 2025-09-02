package co.edu.unicauca.asae_t3.capaAccesoADatos.repositories;

import co.edu.unicauca.asae_t3.capaAccesoADatos.models.FranjaHorariaEntity;

import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.time.LocalTime;

@Repository("IDFranjaHorariaRepository")
public class FranjaHorariaRepository {
	private Map<Integer, FranjaHorariaEntity> mapaFranjasHorarias;

	public FranjaHorariaRepository() {
		this.mapaFranjasHorarias = new HashMap<>();
		cargarFranjasHorarias();
	}

	public FranjaHorariaEntity save(FranjaHorariaEntity franja) {
		this.mapaFranjasHorarias.put(franja.getIdFranjaHoraria(), franja);
		return franja;
	}

	public Optional<FranjaHorariaEntity> findById(Integer id) {
		return Optional.ofNullable(this.mapaFranjasHorarias.get(id));
	}

	public Optional<Collection<FranjaHorariaEntity>> findAll() {
		return mapaFranjasHorarias.isEmpty() ? Optional.empty() : Optional.of(mapaFranjasHorarias.values());
	}

	public Optional<FranjaHorariaEntity> update(Integer id, FranjaHorariaEntity franja) {
		Optional<FranjaHorariaEntity> respuesta;
		if (this.mapaFranjasHorarias.containsKey(id)) {
			this.mapaFranjasHorarias.put(id, franja);
			respuesta = Optional.of(franja);
		} else {
			respuesta = Optional.empty();
		}
		return respuesta;
	}

	public boolean delete(Integer id) {
		return this.mapaFranjasHorarias.remove(id) != null;
	}

	private void cargarFranjasHorarias() {
		System.out.println("Cargando franjas horarias de ejemplo...");
		this.mapaFranjasHorarias.put(1, new FranjaHorariaEntity(1, "Lunes", LocalTime.of(9, 0), LocalTime.of(11, 0)));
		this.mapaFranjasHorarias.put(2, new FranjaHorariaEntity(2, "Martes", LocalTime.of(7, 0), LocalTime.of(9, 0)));
		this.mapaFranjasHorarias.put(3, new FranjaHorariaEntity(3, "Miércoles", LocalTime.of(14, 0), LocalTime.of(16, 0)));
		this.mapaFranjasHorarias.put(4, new FranjaHorariaEntity(4, "Jueves", LocalTime.of(16, 0), LocalTime.of(18, 0)));
		this.mapaFranjasHorarias.put(5, new FranjaHorariaEntity(5, "Viernes", LocalTime.of(14, 0), LocalTime.of(16, 0)));
	}
}

