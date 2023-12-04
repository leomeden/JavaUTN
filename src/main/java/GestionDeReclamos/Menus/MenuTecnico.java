package GestionDeReclamos.Menus;

import GestionDeReclamos.ConexionDB;
import GestionDeReclamos.EspecialidadTecnico;
import GestionDeReclamos.Tecnico;
import GestionDeReclamos.Validaciones;

import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;

import static GestionDeReclamos.ConexionDB.traerTiempoServicio;
import static GestionDeReclamos.ConexionDB.verificarEspTec;
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

            boolean esp = true;
            while(esp){
                System.out.println("Elija técnico a agregar especialidad: ");
                Map<Integer, String> tecEsp = new HashMap<>();
                tecEsp = ConexionDB.traerTecnicos();
                System.out.println("***************************");
                tecEsp.forEach((key, value) -> {
                    System.out.println(key + "\t" + value);
                });
                System.out.println("***************************");
                //System.out.println(medioNotif.size());
                System.out.println("\nIngrese una opcion: ");
                opcion = sn.nextLine();

                if(validar.validateNotVoid(opcion) && validar.isNumeric(opcion)){
                    if(validar.validateRango(parseInt(opcion), tecEsp.size())){
                        esp = false;
                    } else {
                        System.out.println("\nIngrese una opción valida!!!!\n");
                    }
                } else {
                    System.out.println("\nIngrese una opción valida!!!!\n");
                }
            }
            es.setIdtecnico(parseInt(opcion));

            //agregar idServicio ////////////Modificar esto según lo requerido
            esp = true;
            while(esp){
                System.out.println("Elija servicio para agregar a especialidades del tecnico: ");
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
                          if(!verificarEspTec(es.getIdtecnico(),parseInt(opcion))){
                              esp = false;
                          } else {
                              System.out.println("\nEl tecnico ya tiene esa especialidad!!!!\n");
                          }
                    } else {
                        System.out.println("\nIngrese una opción valida!!!!\n");
                    }
                } else {
                    System.out.println("\nIngrese una opción valida!!!!\n");
                }
            }
            es.setIdservicio(parseInt(opcion));

            //agregar tiempo ////////////Modificar esto según lo requerido
            esp = true;
            int tiempoServ = ConexionDB.traerTiempoServicio(parseInt(opcion));
            while(esp){
                System.out.println("Elija tiempo de servicio (Maximo = " + tiempoServ + ")");
                opcion = sn.nextLine();
                if(validar.validateNotVoid(opcion) && validar.isNumeric(opcion)){
                    if (parseInt(opcion) <= tiempoServ && parseInt(opcion)>0){
                        esp = false;

                    } else {
                        System.out.println("\nIngrese un valor correcto!!!!\n");
                    }
                } else {
                    System.out.println("\nIngrese un valor correcto!!!!\n");
                }
                es.setTiempoestimadotec(parseInt(opcion));
            }



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
