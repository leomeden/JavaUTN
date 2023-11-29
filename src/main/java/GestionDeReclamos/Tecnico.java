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

    public class Tecnico {
        Integer idtecnico;
        Integer dni;
        long cuitcuil;
        String nombreapellido;
        Date fechaAlta;
        boolean activo;
        Date fechaBaja;
        Integer horasdiarias;
        long telefono;
        String email;
        int idmedionotif;
    }

