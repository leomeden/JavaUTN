package GestionDeReclamos.Menus;

import GestionDeReclamos.Cliente;
import GestionDeReclamos.ConexionDB;
import GestionDeReclamos.Validaciones;

import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;

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
            System.out.println("2.- Modificacion de cliente");
            System.out.println("3.- Baja de cliente");
            System.out.println("4.- Volver al menu principal");



            try{

                System.out.println("Seleccione la opcion deseada: ");
                opcion = sn.nextInt();

                switch (opcion){
                    case 1:
                        System.out.println("Opcion Alta de cliente");
                        submenuAltaCliente();
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


    private static void submenuAltaCliente(){
        Validaciones validar = new Validaciones();
        Scanner sn = new Scanner(System.in);

        String opcion ="";

        System.out.println("\n\n\t\t-- NUEVO CLIENTE --\n\n");

        try{
            Cliente cl = new Cliente();

            cl.setIdcliente(null);
            boolean cli = true;

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

            System.out.println("Ingrese RAZON SOCIAL: ");
            opcion = sn.nextLine();
            cl.setRazonSocial(opcion);

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
            cl.setIdCondIva(parseInt(opcion));

            System.out.println("Ingrese DIRECCION: ");
            opcion = sn.nextLine();
            cl.setDireccion(opcion);

            System.out.println("Ingrese TELEFONO: ");
            opcion = sn.nextLine();
            cl.setTelefono(parseLong(opcion));

            System.out.println("Ingrese EMAIL: ");
            opcion = sn.nextLine();
            cl.setEmail(opcion);

            java.util.Date date = new java.util.Date();
            java.sql.Date sqlDate = new java.sql.Date(date.getTime());
            cl.setFechaAlta(sqlDate);
            cl.setActivo(true);
            cl.setFechaBaja(null);

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
}
