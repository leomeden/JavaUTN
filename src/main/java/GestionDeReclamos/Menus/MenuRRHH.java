package GestionDeReclamos.Menus;

import GestionDeReclamos.Cliente;
import GestionDeReclamos.ConexionDB;
import GestionDeReclamos.Tecnico;
import GestionDeReclamos.Validaciones;

import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;

import static java.lang.Integer.parseInt;
import static java.lang.Long.parseLong;

public class MenuRRHH {
    public static void menuRRHH(){
        Scanner sn = new Scanner(System.in);

        boolean salir = false;
        int opcion;

        while(!salir){


            System.out.println("\n\n\t\t-- MENU RRHH --\n\n");
            System.out.println("1.- Alta de tecnico");
            System.out.println("2.- Modificacion de tecnico");
            System.out.println("3.- Baja de tecnico");
            System.out.println("4.- Volver al menu principal");



            try{

                System.out.println("Seleccione la opcion deseada: ");
                opcion = sn.nextInt();

                switch (opcion){
                    case 1:
                        System.out.println("Opcion Alta de tecnico");
                        submenuAltaTecnico();
                        break;
                    case 2:
                        System.out.println("Opcion Modificacion de tecnico");
                        break;
                    case 3:
                        System.out.println("Opcion Baja de tecnico");
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

    private static void submenuAltaTecnico(){
        Validaciones validar = new Validaciones();
        Scanner sn = new Scanner(System.in);

        String opcion ="";

        System.out.println("\n\n\t\t-- NUEVO TECNICO --\n\n");

        try{
            Tecnico te = new Tecnico();

            te.setIdtecnico(null);

            //agregar DNI a Tecnico
            boolean tec = true;
            while(tec){
                System.out.println("Ingrese DNI: ");
                opcion = sn.nextLine();
                if(!validar.validateDNI(opcion)){
                    System.out.println("DNI incorrecto! - Debe contener entre 7 y 8 digitos numericos");
                } else {
                    tec = false;
                }
            }
            te.setDni(parseInt(opcion));

            //agregar CUIT/CUIL a Tecnico
            tec = true;
            while(tec){
                System.out.println("Ingrese CUITCUIL: ");
                opcion = sn.nextLine();
                if(validar.validateCUIT(opcion)){
                    tec = ConexionDB.traerTecnico(parseLong(opcion));
                    if(tec){
                        System.out.println("Ese CUIT ya esta registrado");
                    }
                } else {
                    System.out.println("CUIT incorrecto!");
                }
            }
            te.setCuitcuil(parseLong(opcion));

            //agregar Nombre y Apellido a Tecnico
            tec = true;
            while(tec) {
                System.out.println("Ingrese Nombre y Apellido del Tecnico: ");
                opcion = sn.nextLine();
                if(!validar.validateNotVoid(opcion)){
                    System.out.println("No puede dejar el campo vacio\n");
                } else {
                    tec = false;
                }
            }
            te.setNombreapellido(opcion);

            //agregar Fecha Alta y setear Activo y Fecha Baja
            java.util.Date date = new java.util.Date();
            java.sql.Date sqlDate = new java.sql.Date(date.getTime());
            te.setFechaAlta(sqlDate);
            te.setActivo(true);
            te.setFechaBaja(null);

            //agregar Horas Diarias
            tec = true;
            while(tec) {
                System.out.println("Ingrese Horas diarias disponibles: ");
                opcion = sn.nextLine();
                if(!validar.validateNotVoid(opcion) || !validar.isNumeric(opcion)){
                    System.out.println("Debe ingresar una cantidad de horas valida\n");
                } else {
                    tec = false;
                }
            }
            te.setHorasdiarias(parseInt(opcion));

            //agregar Telefono
            tec = true;
            while(tec) {
                System.out.println("Ingrese Telefono: ");
                opcion = sn.nextLine();
                if(!validar.validateNotVoid(opcion) || !validar.isNumeric(opcion)){
                    System.out.println("Debe ingresar telefono valido\n");
                } else {
                    tec = false;
                }
            }
            te.setTelefono(parseLong(opcion));

            //agregar Email
            tec = true;
            while(tec) {
                System.out.println("Ingrese EMAIL: ");
                opcion = sn.nextLine();
                if(!validar.validateNotVoid(opcion)){
                    System.out.println("No puede dejar el campo vacio\n");
                } else {
                    tec = false;
                }
            }
            te.setEmail(opcion);

            //agregar Medio de Notificacion
            tec = true;
            while(tec){
                System.out.println("Ingrese medio de notificacion elegido: ");
                Map<Integer, String> medioNotif = new HashMap<>();
                medioNotif = ConexionDB.traerMedioNotif();
                System.out.println("***************************");
                medioNotif.forEach((key, value) -> {
                    System.out.println(key + "\t" + value);
                });
                System.out.println("***************************");
                //System.out.println(medioNotif.size());
                System.out.println("\nIngrese una opcion: ");
                opcion = sn.nextLine();

                if(validar.validateNotVoid(opcion) && validar.isNumeric(opcion)){
                    if(validar.validateRango(parseInt(opcion), medioNotif.size())){
                        tec = false;
                    } else {
                        System.out.println("\nIngrese una opción valida!!!!\n");
                    }
                } else {
                    System.out.println("\nIngrese una opción valida!!!!\n");
                }
            }
            te.setIdmedionotif(parseInt(opcion));

            System.out.println(te.toString());
            try {
                ConexionDB.conexionDB();
                ConexionDB.altaTecnicoDB(te);
                ConexionDB.closeConX();
            }
            catch(Exception obj) {

                System.out.println("No se pudo agregar el tecnico");
                System.out.println(obj.fillInStackTrace());
            }



        }
        catch(InputMismatchException e){
            System.out.println("Debes seleccionar un numero de opcion");
            sn.next();
        }
    }

}
