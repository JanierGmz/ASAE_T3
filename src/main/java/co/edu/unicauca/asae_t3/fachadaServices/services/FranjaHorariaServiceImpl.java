package co.edu.unicauca.asae_t3.fachadaServices.services;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import co.edu.unicauca.asae_t3.capaAccesoADatos.models.FranjaHorariaEntity;
import co.edu.unicauca.asae_t3.capaAccesoADatos.repositories.FranjaHorariaRepository;
import co.edu.unicauca.asae_t3.capaAccesoADatos.repositories.CursoRepository;
import co.edu.unicauca.asae_t3.capaAccesoADatos.repositories.EspacioFisicoRepository;
import co.edu.unicauca.asae_t3.capaAccesoADatos.repositories.DocenteRepository;
import co.edu.unicauca.asae_t3.capaAccesoADatos.models.CursoEntity;
import co.edu.unicauca.asae_t3.capaAccesoADatos.models.EspacioFisicoEntity;
import co.edu.unicauca.asae_t3.capaAccesoADatos.models.DocenteEntity;
import co.edu.unicauca.asae_t3.fachadaServices.chainResponsibility.handlers.ValidacionChain;
import co.edu.unicauca.asae_t3.fachadaServices.DTO.FranjaHorariaDTOPeticion;
import co.edu.unicauca.asae_t3.fachadaServices.DTO.FranjaHorariaDTORespuesta;
import co.edu.unicauca.asae_t3.fachadaServices.exceptions.ResourceNotFoundException;

@Service("IDFranjaHorariaService")
public class FranjaHorariaServiceImpl implements IFranjaHorariaService {

    @Autowired
    @Qualifier("IDFranjaHorariaRepository")
    private FranjaHorariaRepository franjaHorariaRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private EspacioFisicoRepository espacioFisicoRepository;

    @Autowired
    private DocenteRepository docenteRepository;

    @Autowired
    private ValidacionChain validacionChain;

    @Override
    public List<FranjaHorariaDTORespuesta> findAll() {
        Optional<Collection<FranjaHorariaEntity>> franjasEntityOpt = this.franjaHorariaRepository.findAll();
        if (franjasEntityOpt.isEmpty()) {
            return List.of();
        }
        Collection<FranjaHorariaEntity> franjasEntity = franjasEntityOpt.get();
        return this.modelMapper.map(franjasEntity, new TypeToken<List<FranjaHorariaDTORespuesta>>() {
        }.getType());
    }

    @Override
    public FranjaHorariaDTORespuesta findById(Integer id) {
        Optional<FranjaHorariaEntity> entityOpt = franjaHorariaRepository.findById(id);
        if (entityOpt.isEmpty()) {
            throw new ResourceNotFoundException("Franja horaria", "id", id);
        }
        return modelMapper.map(entityOpt.get(), FranjaHorariaDTORespuesta.class);
    }

    @Override
    public FranjaHorariaDTORespuesta save(FranjaHorariaDTOPeticion franjaHoraria) {
        if (!validacionChain.validar(franjaHoraria)) {
            throw new IllegalArgumentException("Datos de franja horaria no válidos.");
        }

        // Buscar las entidades relacionadas
        CursoEntity curso = cursoRepository.findById(franjaHoraria.getIdCurso())
                .orElseThrow(() -> new IllegalArgumentException("Curso no encontrado"));
        EspacioFisicoEntity espacioFisico = espacioFisicoRepository.findById(franjaHoraria.getIdEspacioFisico())
                .orElseThrow(() -> new IllegalArgumentException("Espacio físico no encontrado"));
        List<DocenteEntity> docentes = franjaHoraria.getIdDocentes().stream()
                .map(id -> docenteRepository.findById(id)
                        .orElseThrow(() -> new IllegalArgumentException("Docente con id " + id + " no encontrado")))
                .toList();

        // Mapear DTO a Entity
        FranjaHorariaEntity franjaHorariaEntity = this.modelMapper.map(franjaHoraria, FranjaHorariaEntity.class);
        
        // Forzar ID a null para nuevas franjas (evita problema del ModelMapper)
        franjaHorariaEntity.setIdFranjaHoraria(null);

        // Asignar propiedades adicionales
        franjaHorariaEntity.setEstado(true);
        franjaHorariaEntity.setCurso(curso);
        franjaHorariaEntity.setEspacioFisico(espacioFisico);
        franjaHorariaEntity.setDocentes(docentes);

        // Guardar la entidad
        FranjaHorariaEntity saved = franjaHorariaRepository.save(franjaHorariaEntity);
        return this.modelMapper.map(saved, FranjaHorariaDTORespuesta.class);
    }

    @Override
    public FranjaHorariaDTORespuesta update(Integer id, FranjaHorariaDTOPeticion franjaHoraria) {
        if (!validacionChain.validar(franjaHoraria)) {
            throw new IllegalArgumentException("Datos de franja horaria no válidos");
        }

        FranjaHorariaEntity franjaActualizada = null;
        Optional<FranjaHorariaEntity> franjaEntityOpt = this.franjaHorariaRepository.findById(id);

        if (franjaEntityOpt.isPresent()) {
            FranjaHorariaEntity franjaDatosNuevos = franjaEntityOpt.get();

            // Buscar las entidades relacionadas
            CursoEntity curso = cursoRepository.findById(franjaHoraria.getIdCurso())
                    .orElseThrow(() -> new IllegalArgumentException("Curso no encontrado"));
            EspacioFisicoEntity espacioFisico = espacioFisicoRepository.findById(franjaHoraria.getIdEspacioFisico())
                    .orElseThrow(() -> new IllegalArgumentException("Espacio físico no encontrado"));
            List<DocenteEntity> docentes = franjaHoraria.getIdDocentes().stream()
                    .map(idDocente -> docenteRepository.findById(idDocente)
                            .orElseThrow(
                                    () -> new IllegalArgumentException(
                                            "Docente con id " + idDocente + " no encontrado")))
                    .toList();

            franjaDatosNuevos.setDia(franjaHoraria.getDia());
            franjaDatosNuevos.setHoraInicio(franjaHoraria.getHoraInicio());
            franjaDatosNuevos.setHoraFin(franjaHoraria.getHoraFin());
            franjaDatosNuevos.setEstado(true);
            franjaDatosNuevos.setCurso(curso);
            franjaDatosNuevos.setEspacioFisico(espacioFisico);
            franjaDatosNuevos.setDocentes(docentes);

            Optional<FranjaHorariaEntity> optionalFranja = franjaHorariaRepository.update(id, franjaDatosNuevos);
            franjaActualizada = optionalFranja.get();
        }

        return modelMapper.map(franjaActualizada, FranjaHorariaDTORespuesta.class);

    }


    @Override
    public boolean deleteById(Integer id) {
        Optional<FranjaHorariaEntity> entityOpt = franjaHorariaRepository.findById(id);
        if (entityOpt.isEmpty()) {
            return false;
        }
        franjaHorariaRepository.delete(id);
        return true;
    }

    @Override
    public List<FranjaHorariaDTORespuesta> findByCurso(Integer idCurso) {
        Collection<FranjaHorariaEntity> franjasEntity = this.franjaHorariaRepository.findByCurso(idCurso);
        if (franjasEntity.isEmpty()) {
            return List.of();
        }
        return this.modelMapper.map(franjasEntity, new TypeToken<List<FranjaHorariaDTORespuesta>>() {
        }.getType());
    }

}
