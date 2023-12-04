package GestionDeReclamos;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level= AccessLevel.PRIVATE)
public class Servicio {

        Integer idservicio;
        Integer idtiposervicio;
        Integer nombreservicio;
        Integer tiempomaxres;

}
