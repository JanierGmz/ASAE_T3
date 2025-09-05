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
        Collection<FranjaHorariaEntity> clientesEntity = franjasEntityOpt.get();
        return this.modelMapper.map(clientesEntity, new TypeToken<List<FranjaHorariaDTORespuesta>>() {
        }.getType());
    }

    @Override
    public FranjaHorariaDTORespuesta findById(Integer id) {
        Optional<FranjaHorariaEntity> entityOpt = franjaHorariaRepository.findById(id);
        if (entityOpt.isEmpty()) {
            return null;
        }
        return modelMapper.map(entityOpt.get(), FranjaHorariaDTORespuesta.class);
    }

    @Override

    public FranjaHorariaDTORespuesta save(FranjaHorariaDTOPeticion franjaHoraria) {
    if (!validacionChain.validar(franjaHoraria)) {
        throw new IllegalArgumentException("Datos de franja horaria no válidos");
    }
    CursoEntity curso = cursoRepository.findById(franjaHoraria.getIdCurso())
        .orElseThrow(() -> new IllegalArgumentException("Curso no encontrado"));
    EspacioFisicoEntity espacioFisico = espacioFisicoRepository.findById(franjaHoraria.getIdEspacioFisico())
        .orElseThrow(() -> new IllegalArgumentException("Espacio físico no encontrado"));
    List<DocenteEntity> docentes = franjaHoraria.getIdDocentes().stream()
        .map(id -> docenteRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Docente con id " + id + " no encontrado")))
        .toList();
    FranjaHorariaEntity franjaHorariaEntity = this.modelMapper.map(franjaHoraria, FranjaHorariaEntity.class);
    franjaHorariaEntity.setEstado(true);
    franjaHorariaEntity.setCurso(curso);
    franjaHorariaEntity.setEspacioFisico(espacioFisico);
    franjaHorariaEntity.setDocentes(docentes);
    FranjaHorariaEntity saved = franjaHorariaRepository.save(franjaHorariaEntity);
    return this.modelMapper.map(saved, FranjaHorariaDTORespuesta.class);
    }

    @Override

    public FranjaHorariaDTORespuesta update(Integer id, FranjaHorariaDTOPeticion franjaHoraria) {
    if (!validacionChain.validar(franjaHoraria)) {
        throw new IllegalArgumentException("Datos de franja horaria no válidos");
    }
    FranjaHorariaEntity entity = franjaHorariaRepository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("Franja horaria no encontrada"));
    CursoEntity curso = cursoRepository.findById(franjaHoraria.getIdCurso())
        .orElseThrow(() -> new IllegalArgumentException("Curso no encontrado"));
    EspacioFisicoEntity espacioFisico = espacioFisicoRepository.findById(franjaHoraria.getIdEspacioFisico())
        .orElseThrow(() -> new IllegalArgumentException("Espacio físico no encontrado"));
    List<DocenteEntity> docentes = franjaHoraria.getIdDocentes().stream()
        .map(idDoc -> docenteRepository.findById(idDoc)
            .orElseThrow(() -> new IllegalArgumentException("Docente con id " + idDoc + " no encontrado.")))
        .toList();
    entity.setDia(franjaHoraria.getDia());
    entity.setHoraInicio(franjaHoraria.getHoraInicio());
    entity.setHoraFin(franjaHoraria.getHoraFin());
    entity.setCurso(curso);
    entity.setEspacioFisico(espacioFisico);
    entity.setDocentes(docentes);
    FranjaHorariaEntity updated = franjaHorariaRepository.save(entity);
    return modelMapper.map(updated, FranjaHorariaDTORespuesta.class);
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

}
