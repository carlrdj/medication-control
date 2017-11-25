/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import dto.User;
import java.util.Scanner;
import medication.control.MedicationControl;
import static medication.control.MedicationControl.menu_selected;
import static medication.control.MedicationControl.menus;
import static medication.control.MedicationControl.sub_menus;

/**
 *
 * @author SEVEN
 */
public class MenuView {    
    private Scanner input = null;
    public static UserView userView = null;
    public static TypeMedicationView typeMedicationView = null;
    public static PresentationMedicationView presentationMedicationView = null;
    public static MedicationView medicationView = null;
    public static ClientView clientView = null;
    public static SaleView saleView = null;
    private ReportView reportView = null;

    public MenuView() {
        input = new Scanner(System.in);
        userView = new UserView();
        typeMedicationView = new TypeMedicationView();
        presentationMedicationView = new PresentationMedicationView();
        medicationView = new MedicationView();
        clientView = new ClientView();
        saleView = new SaleView();
        reportView = new ReportView();
    }
    
    public void authentication(){
        while (true) {            
            User userLogin = userView.userAuthentication();
            if (userLogin.getUse_id() < 1) {
                System.err.println("Usuario y/o contraseña incorrectos.");
            }else{
                MedicationControl.user_id = userLogin.getUse_id();
                System.err.println("");
                System.out.println("Bienvenido : " + userLogin.getUse_surname().toUpperCase() + ", " + userLogin.getUse_name());
                showMenu();
            }
        }
    }    
    
    public void showMenu() {
        System.out.println("______________________________________________________________________________________");
        for (int i = 0; i < menus.length; i++) {
           System.out.println("[ "+ (i + 1) +" ] " + menus[i] + " ");
        }
        while (true) {
            System.out.println("Seleccione menu:");
            String input_menu_selected = input.nextLine();   
            try {
                menu_selected = Integer.parseInt(input_menu_selected);
                if (menu_selected > 0 && menu_selected < 13) {
                    selectionMenu(menu_selected);
                }else{
                    System.err.println("Seleccione opcion valida.");
                }
            } catch (Exception e) {
                System.err.println("Solo se permiten digitos.");
            }
        }
    }    
    
    private void selectionMenu(int menu_selected) {
        switch(menu_selected){
            case(1):
                userView.userShowList();                
                showSubMenu(menu_selected);
                break;
            case(2):
                typeMedicationView.typeMedicationShowList();
                showSubMenu(menu_selected);                  
                break;
            case(3):
                presentationMedicationView.presentationMedicationShowList();
                showSubMenu(menu_selected);                
                break;
            case(4):
                medicationView.medicationShowList();
                showSubMenu(menu_selected);      
                break;
            case(5):
                clientView.clientShowList();
                showSubMenu(menu_selected);
                break;
            case(6):
                saleView.saleNew();
                showMenu();
                break;
            case(7):
                reportView.reportSalesbyDate();
                showMenu();
                break;
            case(8):
                reportView.reportSalesbyClientDni();
                showMenu();
                break;
            case(9):
                reportView.reportMedicationToBuy();
                showMenu();
                break;
            case(10):
                reportView.reportSalesByUseDni();
                showMenu();
                break;
            case(11):
                reportView.reportRankingUserForSale();
                showMenu();
                break;
            case(12):
                System.out.println("");
                System.out.println("\tSessión finalizada");
                authentication();
                break;
            default:
                break;
        }
    }
    
    public void showSubMenu(int menu_selected) {
        System.out.println("______________________________________________________________________________________");
        for (int i = 0; i < sub_menus[menu_selected - 1].length; i++) {
            System.out.println("[ " + (i + 1) + " ] " + sub_menus[menu_selected - 1][i] + " ");
        }
        while (true) {
            System.out.println("Seleccione submenu:");        
            String input_submenu_selected = input.nextLine();
            try {
                MedicationControl.submenu_selected = Integer.parseInt(input_submenu_selected);
                try {
                    if (sub_menus[menu_selected - 1][(MedicationControl.submenu_selected - 1)].equals("Regresar a menu")) {
                        menu_selected = 0;
                        MedicationControl.submenu_selected = 0;
                        showMenu();                        
                    }else{
                        selectionSubMenu(MedicationControl.menu_selected, MedicationControl.submenu_selected);
                    }                
                    break;
                } catch (Exception e) {
                    System.err.println("Seleccione opcion valida:");
                }
            } catch (Exception e) {
                System.err.println("Solo se permiten digitos");
            }
        }
    }
    
    private void selectionSubMenu(int menu_selected, int submenu_selected) {
        switch(submenu_selected){
            case(1):
                switch(menu_selected){
                    case(1):
                        userView.userInsert();
                        break;
                    case(2):
                        typeMedicationView.typeMedicationInsert();
                        break;
                    case(3):
                        presentationMedicationView.presentationMedicationInsert();
                        break;
                    case(4):
                        medicationView.medicationInsert();
                        break;
                    case(5):
                        clientView.clientInsert();
                        break;
                }
                showSubMenu(menu_selected);
                break;
            case(2): 
                switch(menu_selected){
                    case(1):
                        userView.userUpdate();
                        break;
                    case(2):
                        typeMedicationView.typeMedicationUpdate();
                        break;
                    case(3):
                        presentationMedicationView.presentationMedicationUpdate();
                        break;
                    case(4):
                        medicationView.medicationUpdate();
                        break; 
                    case(5):
                        clientView.clientUpdate();
                        break;
                }
                showSubMenu(menu_selected);
                break;
            case(3):
                switch(menu_selected){
                    case(1):
                        userView.userDelete();
                        break;
                    case(2):
                        typeMedicationView.typeMedicationDelete();
                        break;
                    case(3):
                        presentationMedicationView.presentationMedicationDelete();
                        break;
                    case(4):
                        medicationView.medicationDelete();
                        break; 
                    case(5):
                        clientView.clientUpdateBonusPoint();
                        break;
                }
                showSubMenu(menu_selected);
                break;
            case(4):
                switch(menu_selected){
                    case(5):
                        clientView.clientDelete();
                        break;
                }
                showSubMenu(menu_selected);
                break;
            case(5):
                break;
            default:
                break;
        }
    }

}
