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

public class ServicioCliente {
    Integer idservcontr;
    Integer idcliente;
    Integer idservicio;
    Date fechaaltaserv;
    boolean servactivo;
    Date fechafincontrato;
}
