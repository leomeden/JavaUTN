package GestionDeReclamos.Menus;

import GestionDeReclamos.ConexionDB;
import GestionDeReclamos.EspecialidadTecnico;
import GestionDeReclamos.Incidente;
import GestionDeReclamos.Validaciones;

import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;

import static GestionDeReclamos.ConexionDB.verificarEspTec;
import static java.lang.Integer.parseInt;

public class MenuMesaDeAyuda {

        public static void menuMesaDeAyuda(){
            Scanner sn = new Scanner(System.in);

            boolean salir = false;
            int opcion;

            while(!salir){


                System.out.println("\n\n\t\t-- MENU MESA DE AYUDA --\n\n");
                System.out.println("1.- Alta de Incidente");
                System.out.println("2.- Listar Incidentes");
                System.out.println("3.- Eliminar Incidente");
                System.out.println("4.- Volver al menu principal");



                try{

                    System.out.println("Seleccione la opcion deseada: ");
                    opcion = sn.nextInt();

                    switch (opcion){
                        case 1:
                            System.out.println("Opcion Alta de Incidente");
                            submenuAltaIncidente();
                            break;
                        case 2:
                            System.out.println("Opcion Listar Incidentes");
                            break;
                        case 3:
                            System.out.println("Opcion Eliminar Incidentes");
                            break;
                        case 4:
                            salir=true;
                            break;
                        default:
                            System.out.println("Opción incorrecta - Seleccione una opcion entre 1 y 4");

                    }
                }
                catch(InputMismatchException e){
                    System.out.println("Debes seleccionar un numero de opcion");
                    sn.next();
                }
            }
        }

    private static void submenuAltaIncidente(){
        Validaciones validar = new Validaciones();
        Scanner sn = new Scanner(System.in);

        String opcion ="";

        System.out.println("\n\n\t\t-- AGREGAR INCIDENTE --\n\n");

        try{
            Incidente in = new Incidente();

            in.setIdincidente(null);

            //agregar idCliente ////////////Modificar esto según lo requerido

            boolean ser = true;
            while(ser){
                System.out.println("Elija cliente a agregar incidente: ");
                Map<Integer, String> clientes = new HashMap<>();
                clientes = ConexionDB.traerClientes();
                System.out.println("***************************");
                clientes.forEach((key, value) -> {
                    System.out.println(key + "\t" + value);
                });
                System.out.println("***************************");
                //System.out.println(medioNotif.size());
                System.out.println("\nIngrese una opcion: ");
                opcion = sn.nextLine();

                if(validar.validateNotVoid(opcion) && validar.isNumeric(opcion)){
                    if(validar.validateRango(parseInt(opcion), clientes.size())){
                        ser = false;
                    } else {
                        System.out.println("\nIngrese una opción valida!!!!\n");
                    }
                } else {
                    System.out.println("\nIngrese una opción valida!!!!\n");
                }
            }
            in.setIdcliente(parseInt(opcion));

            //agregar idServicio ////////////Modificar esto según lo requerido

            ser = true;
            while(ser){
                System.out.println("Elija el servicio de los contratados: ");
                Map<Integer, String> servicios = new HashMap<>();
                servicios = ConexionDB.traerServiciosContratados(in.getIdcliente());
                System.out.println("***************************");
                servicios.forEach((key, value) -> {
                    System.out.println(key + "\t" + value);
                });
                System.out.println("***************************");
                //System.out.println(medioNotif.size());
                System.out.println("\nIngrese una opcion: ");
                opcion = sn.nextLine();

                if(validar.validateNotVoid(opcion) && validar.isNumeric(opcion)){
                    //if(validar.validateRango(parseInt(opcion), servicios.size())){
                    if(servicios.containsKey(parseInt(opcion))){
                        ser = false;
                    } else {
                        System.out.println("\nIngrese una opción valida!!!!\n");
                    }
                } else {
                    System.out.println("\nIngrese una opción valida!!!!\n");
                }
            }
            in.setIdservicio(parseInt(opcion));

            //agregar Fecha Alta y setear Activo y Fecha Baja
            java.util.Date date = new java.util.Date();
            java.sql.Date sqlDate = new java.sql.Date(date.getTime());
            in.setFechaalta(sqlDate);
            in.setResuelto(false);
            in.setFecharesuelto(sqlDate);
            in.setFechaestimada(sqlDate);

            //agregar idTecnico ////////////Modificar esto según lo requerido

            ser = true;
            while(ser){
                System.out.println("Elija el tecnico para ese servicio: ");
                Map<Integer, String> servicios = new HashMap<>();
                servicios = ConexionDB.traerNomServTec(in.getIdservicio());
                System.out.println("***************************");
                servicios.forEach((key, value) -> {
                    System.out.println(key + "\t" + value);
                });
                System.out.println("***************************");
                //System.out.println(medioNotif.size());
                System.out.println("\nIngrese una opcion: ");
                opcion = sn.nextLine();

                if(validar.validateNotVoid(opcion) && validar.isNumeric(opcion)){
                    //if(validar.validateRango(parseInt(opcion), servicios.size())){
                    if(servicios.containsKey(parseInt(opcion))){
                        ser = false;
                    } else {
                        System.out.println("\nIngrese una opción valida!!!!\n");
                    }
                } else {
                    System.out.println("\nIngrese una opción valida!!!!\n");
                }
            }
            in.setIdtecnico(parseInt(opcion));

            //agregar descripcion ////////////Modificar esto según lo requerido
            ser = true;
            while(ser){
                System.out.println("Realice una descripcion del problema: ");

                opcion = sn.nextLine();

                if(validar.validateNotVoid(opcion)){
                        ser = false;
                } else {
                    System.out.println("\nNo puede quedar vacia la descripcion\n");
                }
            }
            in.setDescrproblema(opcion);

            //Imprimo objeto para debug
            System.out.println(in.toString());
            try {
                ConexionDB.conexionDB();
                ConexionDB.altaIncidente(in);
                ConexionDB.closeConX();
            }
            catch(Exception obj) {

                System.out.println("No se pudo agregar incidente");
                System.out.println(obj.fillInStackTrace());
            }
        }
        catch(InputMismatchException e){
            System.out.println("Debes seleccionar un numero de opcion");
            sn.next();
        }
    }

}
