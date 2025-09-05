package co.edu.unicauca.asae_t3.fachadaServices.DTO;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DocenteDTOPeticion {
    private String nombres;
    private String apellidos;
    private String correoInstitucional;
    private String categoria;

    //private List<FranjaHorariaDTOPeticion> franjasHorarias;
}
