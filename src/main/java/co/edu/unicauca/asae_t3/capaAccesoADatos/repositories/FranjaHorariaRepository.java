package co.edu.unicauca.asae_t3.capaAccesoADatos.repositories;

import co.edu.unicauca.asae_t3.capaAccesoADatos.models.*;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Collection;

@Repository("IDFranjaHorariaRepository")
public class FranjaHorariaRepository {
	private List<FranjaHorariaEntity> listaFranjasHorarias;

	public FranjaHorariaRepository() {
		this.listaFranjasHorarias = new ArrayList<>();
		cargarFranjasHorarias();
	}

	public FranjaHorariaEntity save(FranjaHorariaEntity franja) {
		this.listaFranjasHorarias.add(franja);
		return franja;
	}

	public Optional<FranjaHorariaEntity> findById(Integer id) {
		return Optional.ofNullable(this.listaFranjasHorarias.stream()
				.filter(franja -> franja.getIdFranjaHoraria().equals(id))
				.findFirst()
				.orElse(null));
	}

	public Optional<Collection<FranjaHorariaEntity>> findAll() {
		return listaFranjasHorarias.isEmpty() ? Optional.empty() : Optional.of(listaFranjasHorarias);
	}

	public void deleteById(Integer id) {
		this.listaFranjasHorarias.removeIf(franja -> franja.getIdFranjaHoraria().equals(id));
	}

	public FranjaHorariaEntity update(FranjaHorariaEntity franja) {
		Optional<FranjaHorariaEntity> franjaOpt = findById(franja.getIdFranjaHoraria());
		if (franjaOpt.isPresent()) {
			FranjaHorariaEntity franjaToUpdate = franjaOpt.get();
			franjaToUpdate.setHoraInicio(franja.getHoraInicio());
			franjaToUpdate.setHoraFin(franja.getHoraFin());
			franjaToUpdate.setDia(franja.getDia());
			franjaToUpdate.setEstado(franja.getEstado());
			return franjaToUpdate;
		}
		return null;
	}

	private void cargarFranjasHorarias() {
		CursoRepository cursoRepo = new CursoRepository();
		EspacioFisicoRepository espacioRepo = new EspacioFisicoRepository();
		DocenteRepository docenteRepo = new DocenteRepository();

		List<CursoEntity> cursos = new ArrayList<>(cursoRepo.findAll().orElse(new ArrayList<>())) ;
		List<EspacioFisicoEntity> espacios = new ArrayList<>(espacioRepo.findAll().orElse(new ArrayList<>())) ;
		List<DocenteEntity> docentes = new ArrayList<>(docenteRepo.findAll().orElse(new ArrayList<>())) ;

        System.out.println("Cargando franjas horarias de ejemplo...");

		if (cursos.size() >= 5 && espacios.size() >= 5 && docentes.size() >= 5) {
			this.listaFranjasHorarias.add(new FranjaHorariaEntity(1, "08:00", "10:00", "Lunes", true, cursos.get(0), espacios.get(0), docentes.get(0)));
			this.listaFranjasHorarias.add(new FranjaHorariaEntity(2, "10:00", "12:00", "Martes", true, cursos.get(1), espacios.get(1), docentes.get(1)));
			this.listaFranjasHorarias.add(new FranjaHorariaEntity(3, "14:00", "16:00", "Miércoles", true, cursos.get(2), espacios.get(2), docentes.get(2)));
			this.listaFranjasHorarias.add(new FranjaHorariaEntity(4, "16:00", "18:00", "Jueves", true, cursos.get(3), espacios.get(3), docentes.get(3)));
			this.listaFranjasHorarias.add(new FranjaHorariaEntity(5, "18:00", "20:00", "Viernes", true, cursos.get(4), espacios.get(4), docentes.get(4)));
		}
	}
	}

