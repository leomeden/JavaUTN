package GestionDeReclamos.Menus;

import GestionDeReclamos.*;

import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;

import static GestionDeReclamos.ConexionDB.verificarEspTec;
import static java.lang.Integer.parseInt;
import static java.lang.Long.parseLong;

public class MenuComercial {
    public static void menuComercial(){
        Scanner sn = new Scanner(System.in);

        boolean salir = false;
        int opcion;

        while(!salir){


            System.out.println("\n\n\t\t-- MENU COMERCIAL --\n\n");

            System.out.println("1.- Alta de cliente");
            System.out.println("2.- Alta de Servicio de cliente");
            System.out.println("3.- Modificacion de cliente");
            System.out.println("4.- Baja de cliente");
            System.out.println("5.- Volver al menu principal");



            try{

                System.out.println("Seleccione la opcion deseada: ");
                opcion = sn.nextInt();

                switch (opcion){
                    case 1:
                        System.out.println("Opcion Alta de cliente");
                        submenuAltaCliente();
                        break;
                    case 2:
                        System.out.println("Opcion Alta Servicio de cliente");
                        submenuAltaServCli();
                        break;
                    case 3:
                        System.out.println("Opcion Modificacion de cliente");
                        break;
                    case 4:
                        System.out.println("Opcion Baja de cliente");
                        break;
                    case 5:
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


    private static void submenuAltaCliente(){
        Validaciones validar = new Validaciones();
        Scanner sn = new Scanner(System.in);

        String opcion ="";

        System.out.println("\n\n\t\t-- NUEVO CLIENTE --\n\n");

        try{
            Cliente cl = new Cliente();

            cl.setIdcliente(null);
            boolean cli = true;

            //agregar CUIT a Cliente
            while(cli){
                System.out.println("Ingrese CUIT: ");
                opcion = sn.nextLine();
                if(validar.validateCUIT(opcion)){
                    cli = ConexionDB.traerCliente(parseLong(opcion));
                    if(cli){
                        System.out.println("Ese CUIT ya esta registrado");
                    }
                } else {
                    System.out.println("CUIT incorrecto!");
                }
            }
            cl.setCuit(parseLong(opcion));

            //agregar Razon Social a Cliente
            cli = true;
            while(cli) {
                System.out.println("Ingrese RAZON SOCIAL: ");
                opcion = sn.nextLine();
                if(!validar.validateNotVoid(opcion)){
                    System.out.println("No puede dejar el campo vacio\n");
                } else {
                    cli = false;
                }
            }
            cl.setRazonSocial(opcion);

            //agregar Condicion de IVA
            cli = true;
            while(cli){
                System.out.println("Seleccione CONDICION IVA: ");
                Map<Integer, String> condicionIva = new HashMap<>();
                condicionIva = ConexionDB.traerCondIva();
                System.out.println("***************************");
                condicionIva.forEach((key, value) -> {
                    System.out.println(key + "\t" + value);
                });
                System.out.println("***************************");
                //System.out.println(condicionIva.size());
                System.out.println("\nIngrese una opcion: ");
                opcion = sn.nextLine();

                if(validar.validateNotVoid(opcion) && validar.isNumeric(opcion)){
                    if(validar.validateRango(parseInt(opcion), condicionIva.size())){
                        cli = false;
                    } else {
                        System.out.println("\nIngrese una opción valida!!!!\n");
                    }
                } else {
                    System.out.println("\nIngrese una opción valida!!!!\n");
                }
            }
            cl.setIdCondIva(parseInt(opcion));

            //agregar Direccion
            cli = true;
            while(cli) {
                System.out.println("Ingrese DIRECCION: ");
                opcion = sn.nextLine();
                if(!validar.validateNotVoid(opcion)){
                    System.out.println("No puede dejar el campo vacio\n");
                } else {
                    cli = false;
                }
            }
            cl.setDireccion(opcion);

            //agregar Telefono
            cli = true;
            while(cli) {
                System.out.println("Ingrese TELEFONO: ");
                opcion = sn.nextLine();
                if(!validar.validateNotVoid(opcion) || !validar.isNumeric(opcion)){
                    System.out.println("Debe ingresar telefono valido\n");
                } else {
                    cli = false;
                }
            }
            cl.setTelefono(parseLong(opcion));

            //agregar Email
            cli = true;
            while(cli) {
                System.out.println("Ingrese EMAIL: ");
                opcion = sn.nextLine();
                if(!validar.validateNotVoid(opcion)){
                    System.out.println("No puede dejar el campo vacio\n");
                } else {
                    cli = false;
                }
            }
            cl.setEmail(opcion);

            //agregar Fecha Alta y setear Activo y Fecha Baja
            java.util.Date date = new java.util.Date();
            java.sql.Date sqlDate = new java.sql.Date(date.getTime());
            cl.setFechaAlta(sqlDate);
            cl.setActivo(true);
            cl.setFechaBaja(null);

            //imprimo objeto para testeo
            System.out.println(cl.toString());
            try {
                ConexionDB.conexionDB();
                ConexionDB.altaClienteDB(cl);
                ConexionDB.closeConX();
            }
            catch(Exception obj) {

                System.out.println("No se pudo agregar el cliente");
                System.out.println(obj.fillInStackTrace());
            }



        }
        catch(InputMismatchException e){
            System.out.println("Debes seleccionar un numero de opcion");
            sn.next();
        }

    }

    private static void submenuAltaServCli(){
        Validaciones validar = new Validaciones();
        Scanner sn = new Scanner(System.in);

        String opcion ="";

        System.out.println("\n\n\t\t-- AGREGAR SERVICIO EN CLIENTE --\n\n");

        try{
            ServicioCliente se = new ServicioCliente();

            se.setIdservcontr(null);

            //agregar idCliente ////////////Modificar esto según lo requerido

            boolean ser = true;
            while(ser){
                System.out.println("Elija cliente a agregar servicio: ");
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
            se.setIdcliente(parseInt(opcion));

            //agregar idServicio ////////////Modificar esto según lo requerido

            ser = true;
            while(ser){
                System.out.println("Elija el tipo de servicio del incidente: ");
                Map<Integer, String> servicios = new HashMap<>();
                servicios = ConexionDB.traerServicios();
                System.out.println("***************************");
                servicios.forEach((key, value) -> {
                    System.out.println(key + "\t" + value);
                });
                System.out.println("***************************");
                //System.out.println(medioNotif.size());
                System.out.println("\nIngrese una opcion: ");
                opcion = sn.nextLine();

                if(validar.validateNotVoid(opcion) && validar.isNumeric(opcion)){
                    if(validar.validateRango(parseInt(opcion), servicios.size())){
                        ser = false;
                    } else {
                        System.out.println("\nIngrese una opción valida!!!!\n");
                    }
                } else {
                    System.out.println("\nIngrese una opción valida!!!!\n");
                }
            }
            se.setIdservicio(parseInt(opcion));

            //agregar Fecha Alta y setear Activo y Fecha Baja
            java.util.Date date = new java.util.Date();
            java.sql.Date sqlDate = new java.sql.Date(date.getTime());
            se.setFechaaltaserv(sqlDate);
            se.setServactivo(true);
            se.setFechafincontrato(null);

            //imprimo objeto para testeo
            System.out.println(se.toString());
            try {
                ConexionDB.conexionDB();
                ConexionDB.altaServicioContratado(se);
                ConexionDB.closeConX();
            }
            catch(Exception obj) {

                System.out.println("No se pudo agregar especialidad");
                System.out.println(obj.fillInStackTrace());
            }
        }
        catch(InputMismatchException e){
            System.out.println("Debes seleccionar un numero de opcion");
            sn.next();
        }
    }
}
