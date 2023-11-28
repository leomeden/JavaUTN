package GestionDeReclamos;
import java.util.Date;
import java.util.Scanner;
import java.util.InputMismatchException;

import static java.lang.Integer.parseInt;
import static java.lang.Long.parseLong;

public class Menu {
    public void menuPrincipal() {
        Scanner sn = new Scanner(System.in);

        boolean salir = false;
        int opcion;

        while(!salir){


            System.out.println("\n\t\t-- MENU INICIAL --");
            System.out.println("-- Indique con que tipo de usuario quiere ingresar --\n");

            System.out.println("1.- RRHH");
            System.out.println("2.- MESA DE AYUDA");
            System.out.println("3.- COMERCIAL");
            System.out.println("4.- TECNICO");
            System.out.println("5.- SALIR");


            try{

                System.out.println("Seleccione la opcion deseada: ");
                opcion = sn.nextInt();

                switch (opcion){
                    case 1:
                        System.out.println("Opcion RRHH");
                        menuRRHH();
                        break;
                    case 2:
                        System.out.println("Opcion MESA DE AYUDA");
                        break;
                    case 3:
                        System.out.println("Opcion COMERCIAL");
                        menuComercial();
                        break;
                    case 4:
                        System.out.println("Opcion TECNICO");
                        break;
                    case 5:
                        salir=true;
                        break;
                    default:
                        System.out.println("Opción incorrecta - Seleccione una opcion entre 1 y 5");

                }
            }
            catch(InputMismatchException e){
                System.out.println("Debes seleccionar un numero de opcion");
                sn.next();
            }
        }

        System.out.println("Gracias por utilizar el sistema, hasta luego... ");
    }
    private void menuRRHH(){
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

    private void menuComercial(){
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
    private void submenuAltaCliente(){
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

                    cli = ConexionDB.traerCliente(parseLong(opcion));
                    if(cli){
                        System.out.println("Ese CUIT ya esta registrado");
                    }

                }

                cl.setCuit(parseLong(opcion));

                System.out.println("Ingrese RAZON SOCIAL: ");
                opcion = sn.nextLine();
                cl.setRazonSocial(opcion);

                System.out.println("Seleccione CONDICION IVA: ");
                ConexionDB.traerCondIva();
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
