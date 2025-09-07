package co.edu.unicauca.asae_t3.capaAccesoADatos.repositories;

import co.edu.unicauca.asae_t3.capaAccesoADatos.models.FranjaHorariaEntity;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.time.LocalTime;

@Repository("IDFranjaHorariaRepository")
public class FranjaHorariaRepository implements IFranjaHorariaRepository{
	private Map<Integer, FranjaHorariaEntity> mapaFranjasHorarias;

	public FranjaHorariaRepository() {
		this.mapaFranjasHorarias = new HashMap<>();
		cargarFranjasHorarias();
	}

	@Override
	public FranjaHorariaEntity save(FranjaHorariaEntity franja) {
		// Solo generar nuevo ID si la entidad no tiene ID (es una nueva entidad)
		if (franja.getIdFranjaHoraria() == null) {
			franja.setIdFranjaHoraria(generateNewId());
		}
		this.mapaFranjasHorarias.put(franja.getIdFranjaHoraria(), franja);
		return franja;
	}

	@Override
	public Optional<FranjaHorariaEntity> findById(Integer id) {
		return Optional.ofNullable(this.mapaFranjasHorarias.get(id));
	}

	@Override
	public Optional<Collection<FranjaHorariaEntity>> findAll() {
		return mapaFranjasHorarias.isEmpty() ? Optional.empty() : Optional.of(mapaFranjasHorarias.values());
	}

	@Override
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

	@Override
	public boolean delete(Integer id) {
		return this.mapaFranjasHorarias.remove(id) != null;
	}

	@Override
	public Collection<FranjaHorariaEntity> findByEspacioFisicoAndDiaAndHorario(
			Integer idEspacioFisico, String dia, LocalTime horaInicio, LocalTime horaFin) {
		return this.mapaFranjasHorarias.values().stream()
				.filter(franja -> franja.getEspacioFisico() != null 
					&& franja.getEspacioFisico().getIdEspacioFisico().equals(idEspacioFisico)
					&& franja.getDia().equalsIgnoreCase(dia)
					&& franja.getEstado() != null && franja.getEstado()
					&& (horaInicio.isBefore(franja.getHoraFin()) && horaFin.isAfter(franja.getHoraInicio())))
				.collect(java.util.stream.Collectors.toList());
	}

	@Override
	public Collection<FranjaHorariaEntity> findByCurso(Integer idCurso) {
		return this.mapaFranjasHorarias.values().stream()
				.filter(franja -> franja.getCurso() != null 
					&& franja.getCurso().getIdCurso().equals(idCurso)
					&& franja.getEstado() != null && franja.getEstado())
				.collect(java.util.stream.Collectors.toList());
	}

	private Integer generateNewId() {
		return this.mapaFranjasHorarias.keySet().stream()
				.mapToInt(Integer::intValue)
				.max()
				.orElse(0) + 1;
	}

	private void cargarFranjasHorarias() {
		this.mapaFranjasHorarias.put(1, new FranjaHorariaEntity(1, "lunes", LocalTime.of(9, 0), LocalTime.of(11, 0)));
		this.mapaFranjasHorarias.put(2, new FranjaHorariaEntity(2, "martes", LocalTime.of(9, 0), LocalTime.of(11, 0)));
		this.mapaFranjasHorarias.put(3, new FranjaHorariaEntity(3, "lunes", LocalTime.of(11, 0), LocalTime.of(13, 0)));
	}
}

