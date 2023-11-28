package GestionDeReclamos;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level= AccessLevel.PRIVATE)

public class Cliente {
    Integer idcliente;
    long cuit;
    String razonSocial;
    int idCondIva;
    String direccion;
    long telefono;
    String email;
    Date fechaAlta;
    boolean activo;
    Date fechaBaja;
}
