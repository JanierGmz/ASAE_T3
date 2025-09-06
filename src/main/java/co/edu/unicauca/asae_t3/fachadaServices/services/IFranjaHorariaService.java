package co.edu.unicauca.asae_t3.fachadaServices.services;

import java.util.List;

import co.edu.unicauca.asae_t3.fachadaServices.DTO.FranjaHorariaDTOPeticion;
import co.edu.unicauca.asae_t3.fachadaServices.DTO.FranjaHorariaDTORespuesta;

public interface IFranjaHorariaService {

    public List<FranjaHorariaDTORespuesta> findAll();

    public FranjaHorariaDTORespuesta findById(Integer id);

    public FranjaHorariaDTORespuesta save(FranjaHorariaDTOPeticion franjaHoraria);

    public FranjaHorariaDTORespuesta update(Integer id, FranjaHorariaDTOPeticion franjaHoraria);

    public boolean deleteById(Integer id);

    public List<FranjaHorariaDTORespuesta> findByCurso(Integer idCurso);

}
