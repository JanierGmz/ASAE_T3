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
import co.edu.unicauca.asae_t3.fachadaServices.DTO.FranjaHorariaDTOPeticion;
import co.edu.unicauca.asae_t3.fachadaServices.DTO.FranjaHorariaDTORespuesta;

@Service("IDFranjaHorariaService")
public class FranjaHorariaServiceImpl implements IFranjaHorariaService {

    @Autowired
    @Qualifier("IDFranjaHorariaRepository")
    private FranjaHorariaRepository franjaHorariaRepository;

    private ModelMapper modelMapper;

    public FranjaHorariaServiceImpl(FranjaHorariaRepository franjaHorariaRepository, ModelMapper modelMapper) {
        this.franjaHorariaRepository = franjaHorariaRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<FranjaHorariaDTORespuesta> findAll() {
        List<FranjaHorariaDTORespuesta> listaRetornar;
        Optional<Collection<FranjaHorariaEntity>> franjasEntityOpt = this.franjaHorariaRepository.findAll();
        
        // Si el Optional está vacío, devolvemos una lista vacía
        if (franjasEntityOpt.isEmpty()) {
            listaRetornar = List.of();
        }

        // Convertimos la colección a una lista y la mapeamos a FranjaHorariaDTO
        Collection<FranjaHorariaEntity> clientesEntity = franjasEntityOpt.get();
        listaRetornar = this.modelMapper.map(clientesEntity, new TypeToken<List<FranjaHorariaDTORespuesta>>() {
        }.getType());
        return listaRetornar;
    }

    @Override
    public FranjaHorariaDTORespuesta findById(Integer id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findById'");
    }

    @Override
    public FranjaHorariaDTORespuesta save(FranjaHorariaDTOPeticion franjaHoraria) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'save'");
    }

    @Override
    public FranjaHorariaDTORespuesta update(Integer id, FranjaHorariaDTOPeticion franjaHoraria) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public boolean deleteById(Integer id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteById'");
    }

}
