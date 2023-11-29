package GestionDeReclamos.Menus;
import GestionDeReclamos.Cliente;
import GestionDeReclamos.ConexionDB;
import GestionDeReclamos.Validaciones;

import java.util.*;

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
                        MenuRRHH.menuRRHH();
                        break;
                    case 2:
                        System.out.println("Opcion MESA DE AYUDA");
                        break;
                    case 3:
                        System.out.println("Opcion COMERCIAL");
                        MenuComercial.menuComercial();
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



}
