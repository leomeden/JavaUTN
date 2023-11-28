package GestionDeReclamos;


import java.sql.Date;


public class Main {
    public static void main(String[] args){
        Menu menu = new Menu();
        menu.menuPrincipal();
/*        try {




            ConexionDB.conexionDB();//

            //convierto la fecha de java.util.Date a java.sql.Date
            java.util.Date date = new java.util.Date();
            java.sql.Date sqlDate = new java.sql.Date(date.getTime());

            Cliente cl = new Cliente(null,20308917100L, "Leonardo Meden", 5, "Solidaridad 837",3416648946L,
                    "leonardomeden@gmail.com", sqlDate, true, null);

            ConexionDB.altaClienteDB(cl);

            ConexionDB.closeConX();



        */

    }
}




