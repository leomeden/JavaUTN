package GestionDeReclamos.Menus;

import GestionDeReclamos.ConexionDB;
import GestionDeReclamos.EspecialidadTecnico;
import GestionDeReclamos.Tecnico;
import GestionDeReclamos.Validaciones;

import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;

import static java.lang.Integer.parseInt;
import static java.lang.Long.parseLong;

public class MenuTecnico {
    public static void menuTecnico(){
        Scanner sn = new Scanner(System.in);

        boolean salir = false;
        int opcion;

        while(!salir){


            System.out.println("\n\n\t\t-- MENU TECNICO --\n\n");
            System.out.println("1.- Agregar especialidad");
            System.out.println("2.- Ver Casos Activos");
            System.out.println("3.- Ver casos Resueltos");
            System.out.println("4.- Volver al menu principal");



            try{

                System.out.println("Seleccione la opcion deseada: ");
                opcion = sn.nextInt();

                switch (opcion){
                    case 1:
                        System.out.println("Opcion Agregar especialidad");
                        submenuAltaEspecialidad();
                        break;
                    case 2:
                        System.out.println("Opcion Ver Casos Activos");
                        break;
                    case 3:
                        System.out.println("Opcion Ver casos Resueltos");
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

    private static void submenuAltaEspecialidad(){
        Validaciones validar = new Validaciones();
        Scanner sn = new Scanner(System.in);

        String opcion ="";

        System.out.println("\n\n\t\t-- AGREGAR ESPECIALIDAD --\n\n");

        try{
            EspecialidadTecnico es = new EspecialidadTecnico();

            es.setIdespecialidad(null);

            //agregar idTecnico ////////////Modificar esto según lo requerido

            //agregar idServicio ////////////Modificar esto según lo requerido

            //agregar tiempo ////////////Modificar esto según lo requerido

            System.out.println(es.toString());
            try {
                ConexionDB.conexionDB();
                ConexionDB.altaEspecialidad(es);
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
