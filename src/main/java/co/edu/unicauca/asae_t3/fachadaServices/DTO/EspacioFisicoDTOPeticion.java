package co.edu.unicauca.asae_t3.fachadaServices.DTO;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EspacioFisicoDTOPeticion {
    private String nombre;
    private String tipo;
    private Integer capacidad;
    private String ubicacion;

    List<FranjaHorariaDTOPeticion> franjasHorarias;
}
