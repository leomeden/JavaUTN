package GestionDeReclamos;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Date;

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

        public static void traerCondIva() {


        String consulta = "SELECT * FROM condiva";

            ResultSet sql;
            try {
                conexionDB();
                sql = sT.executeQuery(consulta);
                System.out.println("");
                while (sql.next()) {

                    System.out.println(sql.getInt(1)+"\t"+sql.getString(2));

                }
        closeConX();
            } catch (SQLException e) {
                System.out.println("Error en el select de la tabla EMPLEADO"+ e);
                e.printStackTrace();
            }



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
}