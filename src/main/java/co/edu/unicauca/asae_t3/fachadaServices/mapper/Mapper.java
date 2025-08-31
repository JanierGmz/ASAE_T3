package co.edu.unicauca.asae_t3.fachadaServices.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Mapper {
    @Bean
    public ModelMapper crearBeanMapper(){
        return new ModelMapper();
    }
}
