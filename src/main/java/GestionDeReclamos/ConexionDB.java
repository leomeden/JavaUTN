package GestionDeReclamos;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

public class ConexionDB {
    private static Connection conX;
    private static Statement sT;


    public static void inicioDB() {
        conX = null;
        sT = null;
    }


    public static void closeConX() throws SQLException {
        conX.close();
        sT.close();
    }

    public static Connection conexionDB() {

        try {
            inicioDB();
            conX = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbsoporte", "root", "Vicente2016!");
            sT = conX.createStatement();
            return conX;
        } catch (Exception obj) {

            System.out.println("Error en la conexion de la base de datos" + obj);
            System.out.println(obj.fillInStackTrace());
        }
        return null;

    }

    //***********************ALTA CLIENTE
    public static void altaClienteDB(Cliente cli) {
        //validar que el cuit no exista -tambien se podria validar el contrato(codSoporte)
        String consulta = "insert into cliente(idcliente,cuit,razonsocial,idcondiva, direccion, telefono, email, fechaalta, activo, fechabaja)"
                + " values (NULL,?,?,?,?,?,?,?,?,NULL)";

        try {
            PreparedStatement sqlUp = conX.prepareStatement(consulta);


            sqlUp.setLong(1, cli.getCuit());
            sqlUp.setString(2, cli.getRazonSocial());
            sqlUp.setInt(3, cli.getIdCondIva());
            sqlUp.setString(4, cli.getDireccion());
            sqlUp.setLong(5, cli.getTelefono());
            sqlUp.setString(6, cli.getEmail());
            sqlUp.setDate(7, (Date) cli.getFechaAlta());
            sqlUp.setBoolean(8, cli.isActivo());


            sqlUp.executeUpdate();

            System.out.println("La DB/TABLA CLIENTE se actualizo con exito");

        } catch (SQLException obj) {
            System.out.println("Error en el insert de la tabla Cliente"+ obj);
            obj.fillInStackTrace();
        }

    }

    //***********************ALTA TECNICO
    public static void altaTecnicoDB(Tecnico tec) {

        String consulta = "insert into tecnico(idtecnico, dni, cuilcuit, nombreapellido, fechaalta, activo, fechabaja, horasdiarias, telefono, email, idmedionotif)"
                + " values (NULL,?,?,?,?,?,NULL,?,?,?,?)";

        try {
            PreparedStatement sqlUp = conX.prepareStatement(consulta);

            sqlUp.setInt(1, tec.getDni());
            sqlUp.setLong(2, tec.getCuitcuil());
            sqlUp.setString(3, tec.getNombreapellido());
            sqlUp.setDate(4, (Date) tec.getFechaAlta());
            sqlUp.setBoolean(5, tec.isActivo());
            sqlUp.setInt(6, tec.getHorasdiarias());
            sqlUp.setLong(7, tec.getTelefono());
            sqlUp.setString(8, tec.getEmail());
            sqlUp.setInt(9, tec.getIdmedionotif());

            sqlUp.executeUpdate();

            System.out.println("La DB/TABLA TECNICO se actualizo con exito");

        } catch (SQLException obj) {
            System.out.println("Error en el insert de la tabla Tecnico"+ obj);
            obj.fillInStackTrace();
        }

    }

    //***********************ALTA Especialidad técnico
    public static void altaEspecialidad(EspecialidadTecnico esp) {
        String consulta = "insert into especialidadtecnico(idespecialidad, idtecnico, idservicio, tiempoestimadotec)"
                + " values (NULL,?,?,?)";

        try {
            PreparedStatement sqlUp = conX.prepareStatement(consulta);

            sqlUp.setInt(1, esp.getIdtecnico());
            sqlUp.setInt(2, esp.getIdservicio());
            sqlUp.setInt(3, esp.getTiempoestimadotec());


            sqlUp.executeUpdate();

            System.out.println("La DB/TABLA ESPECIALIDAD TECNICO se actualizo con exito");

        } catch (SQLException obj) {
            System.out.println("Error en el insert de la tabla Especialidad Tecnico"+ obj);
            obj.fillInStackTrace();
        }
    }


    //***********************ALTA Servicio Contratado
    public static void altaServicioContratado(ServicioCliente ser) {
        String consulta = "insert into serviciocontratado(idservcontr, idcliente, idservicio, fechaaltaserv, servactivo, fechafincontrato)"
                + " values (NULL,?,?,?,?, NULL)";

        try {
            PreparedStatement sqlUp = conX.prepareStatement(consulta);

            sqlUp.setInt(1, ser.getIdcliente());
            sqlUp.setInt(2, ser.getIdservicio());
            sqlUp.setDate(3, ser.getFechaaltaserv());
            sqlUp.setBoolean(4, ser.isServactivo());


            sqlUp.executeUpdate();

            System.out.println("La DB/TABLA SERVICIO CONTRATADO se actualizo con exito");

        } catch (SQLException obj) {
            System.out.println("Error en el insert de la tabla SERVICIO CONTRATADO"+ obj);
            obj.fillInStackTrace();
        }
    }

    //***********************ALTA Incidente
    public static void altaIncidente(Incidente inc) {
        String consulta = "insert into incidente(idincidente, idservicio, fechaalta, resuelto, fecharesuelto, idtecnico, idcliente, descrproblema, fechaestimada, descrresolucion)"
                + " values (NULL,?,?,?,?,?,?,?,?,NULL)";

        try {
            PreparedStatement sqlUp = conX.prepareStatement(consulta);

            sqlUp.setInt(1, inc.getIdservicio());
            sqlUp.setDate(2, inc.getFechaalta());
            sqlUp.setBoolean(3, inc.isResuelto());
            sqlUp.setDate(4, inc.getFecharesuelto());
            sqlUp.setInt(5, inc.getIdtecnico());
            sqlUp.setInt(6, inc.getIdcliente());
            sqlUp.setString(7, inc.getDescrproblema());
            sqlUp.setDate(8, inc.getFechaestimada());

            sqlUp.executeUpdate();

            System.out.println("La DB/TABLA INCIDENTE se actualizo con exito");

        } catch (SQLException obj) {
            System.out.println("Error en el insert de la tabla INCIDENTE"+ obj);
            obj.fillInStackTrace();
        }
    }

        public static Map<Integer, String> traerCondIva() {

            Map<Integer, String> condicionIva = new HashMap<>();

            String consulta = "SELECT * FROM condiva";

            ResultSet sql;
            try {
                conexionDB();
                sql = sT.executeQuery(consulta);
                System.out.println("");
                while (sql.next()) {
                    condicionIva.put(sql.getInt(1), sql.getString(2));
                    //System.out.println(sql.getInt(1)+"\t"+sql.getString(2));

                }
            closeConX();
            } catch (SQLException e) {
                System.out.println("Error en el select de la tabla EMPLEADO"+ e);
                e.printStackTrace();
            }

            return condicionIva;
    }
    public static boolean traerCliente(long cuit) {

        boolean resultado = false;
        String consulta = "SELECT * FROM cliente WHERE cuit = " + cuit;

        ResultSet sql;
        try {

            conexionDB();
            sql = sT.executeQuery(consulta);
            if(sql.next()){
                resultado = true;
            }
            closeConX();
            return resultado;

        } catch (SQLException e) {
            System.out.println("Error en el select de la tabla cliente"+ e);
            e.printStackTrace();
        }


    return resultado;
    }

    public static boolean traerTecnico(long cuit) {

        boolean resultado = false;
        String consulta = "SELECT * FROM tecnico WHERE cuilcuit = " + cuit;

        ResultSet sql;
        try {

            conexionDB();
            sql = sT.executeQuery(consulta);
            if(sql.next()){
                resultado = true;
            }
            closeConX();
            return resultado;

        } catch (SQLException e) {
            System.out.println("Error en el select de la tabla tecnico"+ e);
            e.printStackTrace();
        }


        return resultado;
    }

    public static Map<Integer, String> traerTecnicos() {

        Map<Integer, String> resultado = new HashMap<>();
        String consulta = "SELECT * FROM tecnico";

        ResultSet sql;
        try {

            conexionDB();
            sql = sT.executeQuery(consulta);
            System.out.println("");
            while (sql.next()) {
                resultado.put(sql.getInt(1), sql.getString(4));
                //System.out.println(sql.getInt(1)+"\t"+sql.getString(2));
            }
            closeConX();
            return resultado;
        } catch (SQLException e) {
            System.out.println("Error en el select de la tabla tecnico"+ e);
            e.printStackTrace();
        }
        return resultado;
    }

    public static Map<Integer, String> traerClientes() {

        Map<Integer, String> resultado = new HashMap<>();
        String consulta = "SELECT * FROM cliente";

        ResultSet sql;
        try {

            conexionDB();
            sql = sT.executeQuery(consulta);
            System.out.println("");
            while (sql.next()) {
                resultado.put(sql.getInt(1), sql.getString(3));
                //System.out.println(sql.getInt(1)+"\t"+sql.getString(2));
            }
            closeConX();
            return resultado;
        } catch (SQLException e) {
            System.out.println("Error en el select de la tabla cliente"+ e);
            e.printStackTrace();
        }
        return resultado;
    }

    public static Map<Integer, String> traerServicios() {

        Map<Integer, String> resultado = new HashMap<>();
        String consulta = "SELECT * FROM servicio";


        ResultSet sql;
        try {

            conexionDB();
            sql = sT.executeQuery(consulta);
            System.out.println("");
            while (sql.next()) {
                resultado.put(sql.getInt(1), sql.getString(3));
                //System.out.println(sql.getInt(1)+"\t"+sql.getString(2));
            }
            closeConX();
            return resultado;
        } catch (SQLException e) {
            System.out.println("Error en el select de la tabla servicio"+ e);
            e.printStackTrace();
        }
        return resultado;
    }

    public static Map<Integer, String> traerNomServTec(int idServ) {

        Map<Integer, String> resultado = new HashMap<>();
        String consulta = "SELECT * FROM serviciostecnicosnombres WHERE idservicio = " + idServ;

        ResultSet sql;
        try {
            conexionDB();
            sql = sT.executeQuery(consulta);
            System.out.println("");
            while (sql.next()) {
                resultado.put(sql.getInt(1), sql.getString(3));
                //System.out.println(sql.getInt(1)+"\t"+sql.getString(2));
            }
            closeConX();
            return resultado;
        } catch (SQLException e) {
            System.out.println("Error en el select de la tabla Especialidad Tecnico"+ e);
            e.printStackTrace();
        }
        return resultado;
    }


    public static Map<Integer, String> traerServiciosContratados(int idCli) {

        Map<Integer, String> resultado = new HashMap<>();
        String consulta = "SELECT * FROM serviciosclientes WHERE idcliente = " + idCli;


        ResultSet sql;
        try {

            conexionDB();
            sql = sT.executeQuery(consulta);
            System.out.println("");
            while (sql.next()) {
                resultado.put(sql.getInt(2), sql.getString(3));
                //System.out.println(sql.getInt(1)+"\t"+sql.getString(2));
            }
            closeConX();
            return resultado;
        } catch (SQLException e) {
            System.out.println("Error en el select de la tabla servicio"+ e);
            e.printStackTrace();
        }
        return resultado;
    }

    public static boolean verificarEspTec(int idTec, int idServ) {

        boolean resultado=false;
        String consulta = "SELECT * FROM especialidadtecnico WHERE idtecnico = "+ idTec + " AND idservicio = "+ idServ;

        ResultSet sql;
        try {

            conexionDB();
            sql = sT.executeQuery(consulta);
            System.out.println("");
            if (sql.next()) {
                resultado= true;
                //System.out.println(sql.getInt(1)+"\t"+sql.getString(2));
            }
            closeConX();
            return resultado;
        } catch (SQLException e) {
            System.out.println("Error en el select de la tabla servicio"+ e);
            e.printStackTrace();
        }
        return resultado;
    }





    public static int traerTiempoServicio(int idServ) {

       Integer resultado = 0;
        String consulta = "SELECT * FROM servicio WHERE idServicio ="  + idServ;

        ResultSet sql;
        try {
            conexionDB();
            sql = sT.executeQuery(consulta);
            System.out.println("");
            if (sql.next()) {
                resultado = sql.getInt(4);
            }
            closeConX();
            return resultado;
        } catch (SQLException e) {
            System.out.println("Error en el select de la tabla servicio"+ e);
            e.printStackTrace();
        }
        return resultado;
    }

    public static Map<Integer, String> traerMedioNotif() {
        Map<Integer, String> medioNotif = new HashMap<>();

        String consulta = "SELECT * FROM medionotif";

        ResultSet sql;
        try {
            conexionDB();
            sql = sT.executeQuery(consulta);
            System.out.println("");
            while (sql.next()) {
                medioNotif.put(sql.getInt(1), sql.getString(2));
                //System.out.println(sql.getInt(1)+"\t"+sql.getString(2));

            }
            closeConX();
        } catch (SQLException e) {
            System.out.println("Error en el select de la tabla MEDIONOTIF" + e);
            e.printStackTrace();
        }

        return medioNotif;
    }


}

