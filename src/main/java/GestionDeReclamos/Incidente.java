package GestionDeReclamos;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level= AccessLevel.PRIVATE)

public class Incidente {
    Integer idincidente;
    Integer idservicio;
    Date fechaalta;
    boolean resuelto;
    Date fecharesuelto;
    Integer idtecnico;
    Integer idcliente;
    String descrproblema;
    Date fechaestimada;
    String descrresolucion;
}
