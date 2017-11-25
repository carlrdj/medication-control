/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package medication.control;

import java.util.Scanner;
import view.MenuView;

/**
 *
 * @author SEVEN
 */
public class MedicationControl {

   private MenuView menuView = new MenuView();
   
   public static int user_id = 0;
   
   public static int optionColum = 5;
   public static Double igv = 0.18;
   public static String cancelOperation = "c";
   public static int menu_selected;
   public static int submenu_selected;
   public static String[] menus = {
            "Administrar Usuarios del sistema",
            "Administrar tipos de medicamentos",
            "Administrar formas de presentación de medicamentos",
            "Administrar medicamentos.",
            "Administrar datos de cliente.",
            "Operaciones de venta",
            "Reporte: Caja",
            "Reporte: Clientes",
            "Reporte: Compras",
            "Reporte: Venta de usuario",
            "Reporte: Ranking de ventas.",
            "Salir"
        };
    public static String[][] sub_menus = {
            {"Registrar usuario", "Actualizar usuario", "Eliminar usuario", "Regresar a menu"},
            {"Registrar tipo de medicamentos", "Actualizar tipo de medicamentos", "Eliminar tipo de medicamentos", "Regresar a menu"},
            {"Registrar formas de presentación de medicamentos", "Actualizar formas de presentación de medicamentos", "Eliminar formas de presentación de medicamentos", "Regresar a menu"},
            {"Registrar medicamento", "Actualizar medicamento", "Eliminar medicamento", "Regresar a menu"},
            {"Registrar cliente", "Actualizar cliente", "Actualizar puntos bono", "Eliminar cliente", "Regresar a menu"}
        };
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        MedicationControl medicationControl = new MedicationControl();
        medicationControl.runMenu();        
    }
    public void runMenu() {
        //Welcome message
        System.out.println("------ ---- *- -* *-                  -* *- -* ---- ------");      
        System.out.println("------ ---- *- -* *-   BIENVENIDOS    -* *- -* ---- ------");      
        System.out.println("------ ---- *- -* *-                  -* *- -* ---- ------");       
        
        
        menuView.authentication();
        //Show options menu
        //menuView.showMenu();        
    }
    
    
    
    

    

    

    

}
