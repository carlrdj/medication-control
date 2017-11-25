/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import dto.Client;
import dto.Medication;
import dto.Sale;
import dto.SaleDetail;
import dto.User;
import dto.UserRanking;
import java.sql.Date;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import medication.control.MedicationControl;
import static medication.control.MedicationControl.user_id;

/**
 *
 * @author SEVEN
 */
public class ReportView {
    private Scanner input = null;
    DecimalFormat decimalFormat = new DecimalFormat("0.00");

    public ReportView() {
        input = new Scanner(System.in);
    }
    
    public void reportSalesbyDate(){
        System.out.println("______________________________________________________________________________________"); 
        System.out.println("------ ---- *- -* *- "+ MedicationControl.menus[MedicationControl.menu_selected - 1] +" -* *- -* ---- ------");
        System.out.println("");
        System.out.println("Ingresar Fecha (*) dd-mm-yyyy:\t[ " + MedicationControl.cancelOperation +" ] Cancelar");
        while (true) {
            String inputData = input.nextLine();
            if (inputData.equals(MedicationControl.cancelOperation)) {
                System.err.println("Se canceló operación.");
                break;
            }
            
            String vector[] = inputData.split("-");
            if (vector.length != 3) {
                System.err.println("Solo se permiten fechas con el siguiete formato dd-mm-yyyy.");                
            }else{
                if (vector[2].length() != 4 || vector[1].length() != 2 || vector[0].length() != 2) {
                    System.err.println("Solo se permiten fechas con el siguiete formato dd-mm-yyyy.");
                }else{
                    try {
                        int parseInputData1 = Integer.parseInt(vector[2]);
                        int parseInputData2 = Integer.parseInt(vector[1]);
                        int parseInputData3 = Integer.parseInt(vector[0]);
                        if (parseInputData2 < 13 && parseInputData3 < 32) {
                            inputData = vector[2] + "-" + vector[1] +"-" + vector[0];
                            Date date=Date.valueOf(inputData);
                            System.out.println("");
                            System.out.println("\tReporte de ventas : " + inputData);
                            System.out.println("\tUsuario : " + MenuView.userView.userGetFullNameById(user_id));
                            List<User> listUser = MenuView.userView.userList();
                            for (int i = 0; i < listUser.size(); i++) {
                                System.out.println("");
                                System.out.println("\t" + listUser.get(i).getUse_surname().toUpperCase() + ", " + listUser.get(i).getUse_name());
                                System.out.println("\t----------------------------------------------------");
                                List<Medication> listMedication = MenuView.medicationView.medicationList();
                                for (int j = 0; j < listMedication.size(); j++) {
                                    //Double amountCollected = MenuView.saleView.saleAmountCollected(listUser.get(i).getUse_id(), listMedication.get(j).getMed_id(), date);
                                    MenuView.saleView.saleAmountCollected(listUser.get(i).getUse_id(), listMedication.get(j).getMed_id(), listMedication.get(j).getMed_name(), date);
                                    //System.out.println("\t" + listMedication.get(j).getMed_name()+ "\t\tS/. " + decimalFormat.format(amountCollected));
                                    //System.out.println(result);
                                }
                            }
                            System.out.println("");
                            System.out.println("Ingresar Fecha (*) dd-mm-yyyy:\t[ " + MedicationControl.cancelOperation +" ] Cancelar");
                        }else{
                            System.err.println("Solo se permiten fechas con el siguiete formato dd-mm-yyyy.");
                        }                        
                    } catch (Exception e) {
                        System.err.println("Solo se permiten fechas con el siguiete formato dd-mm-yyyy.");
                    }
                }                
            }            
        }
    }
    
    public void reportSalesbyClientDni(){
        System.out.println("______________________________________________________________________________________"); 
        System.out.println("------ ---- *- -* *- "+ MedicationControl.menus[MedicationControl.menu_selected - 1] +" -* *- -* ---- ------");
        System.out.println("");
        System.out.println("Ingresar DNI de cliente (*):\t[ " + MedicationControl.cancelOperation +" ] Cancelar");
        while (true) {
            String inputData = input.nextLine();
            if (inputData.equals(MedicationControl.cancelOperation)) {
                System.err.println("Se canceló operación.");
                break;
            }
            if (inputData.length() == 0) {
                System.err.println("No se permite registrar campo vacío, vuelva a intentarlo.");
            }else if (inputData.length() != 8){
                System.err.println("No se permite registrar DNI (8 caracteres), vuelva a intentarlo.");
            }else if(MenuView.clientView.clientExist(inputData)){
                System.out.println("");
                System.out.println("\tReporte de compras");
                Client client = MenuView.clientView.clientGetByDni(inputData);
                System.out.println("\tCliente : " + client.getCli_surname().toUpperCase() + ", " + client.getCli_name());
                System.out.println("\tUsuario : " + MenuView.userView.userGetFullNameById(user_id));
                int soles = client.getCli_bonus_points() / 100;
                System.out.println("\tPuntos bono : " +  client.getCli_bonus_points() + " Equivalen a S/. " + soles);
                System.out.println("");
                List<Sale> listSale = MenuView.saleView.saleList();
                System.out.println("\tMedicamento" + "\tCantidad" + "\tMonto recaudado" + "\tFecha");
                System.out.println("\t----------------------------------------------------------");
                
                for (int i = 0; i < listSale.size(); i++) {
                    if (listSale.get(i).getCli_id() == client.getCli_id()) {
                        List<SaleDetail> listSaleDetail = MenuView.saleView.saleDetailListBySalId(listSale.get(i).getSal_id());
                        System.out.println("\tBoleta N°: " + listSale.get(i).getSal_id() + "\t------------------------------------------");
                        for (int j = 0; j < listSaleDetail.size(); j++) {
                            System.out.println("\t" + MenuView.medicationView.medicationGetNameById(listSaleDetail.get(j).getMed_id()) + 
                                "\t" + listSaleDetail.get(j).getSal_det_quantity() + 
                                "\t\tS/. " + decimalFormat.format(
                                        (listSaleDetail.get(j).getSal_det_quantity() * MenuView.medicationView.medicationGetUnitPriceById(listSaleDetail.get(j).getMed_id()))+ (listSaleDetail.get(j).getSal_det_quantity() * MenuView.medicationView.medicationGetUnitPriceById(listSaleDetail.get(j).getMed_id()) * MedicationControl.igv)
                                ) + 
                                "\t" + listSale.get(i).getSal_date());
                        }
                    }
                }
                System.out.println("");
                System.out.println("Ingresar DNI de cliente (*):\t[ " + MedicationControl.cancelOperation +" ] Cancelar");
                
            }else{
                System.err.println("Cliente no esta registrado. Vuelva a intentarlo.");
            }
        }
    }
    
    public void reportMedicationToBuy(){
        System.out.println("______________________________________________________________________________________"); 
        System.out.println("------ ---- *- -* *- "+ MedicationControl.menus[MedicationControl.menu_selected - 1] +" -* *- -* ---- ------");
        System.out.println("");
        System.out.println("\tReporte de compras");
        System.out.println("\tUsuario : " + MenuView.userView.userGetFullNameById(user_id));
        System.out.println("");
        List<Medication> list = MenuView.medicationView.medicationList();
        System.out.println("\tMedicamento\tCantidad actual\t\tFecha de vencimiento");
        System.out.println("\t------------------------------------------------------------");
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getMed_quantity_available() < 50 || 
                    MenuView.medicationView.verifyDueDate(list.get(i).getMed_due_date()).equals("Producto Caducado") ||
                    MenuView.medicationView.medicationToBuy(list.get(i).getMed_due_date()).equals("Producto por comprar")) {                
                System.out.println("\t" + list.get(i).getMed_name() + 
                        "\t" + list.get(i).getMed_quantity_available() + 
                        "\t\t\t" + MenuView.medicationView.formatDate(list.get(i).getMed_due_date()) + 
                        "\t\t" + MenuView.medicationView.verifyDueDate(list.get(i).getMed_due_date()));
            }
        }
        System.out.println("");
        System.out.println("\t[ ENTER ] Terminar");
        input.nextLine();
    }
    
    public void reportSalesByUseDni(){
        System.out.println("______________________________________________________________________________________"); 
        System.out.println("------ ---- *- -* *- "+ MedicationControl.menus[MedicationControl.menu_selected - 1] +" -* *- -* ---- ------");
        System.out.println("");
        System.out.println("Ingresar DNI de usuario (*):\t[ " + MedicationControl.cancelOperation +" ] Cancelar");
        while (true) {
            String inputData = input.nextLine();
            if (inputData.equals(MedicationControl.cancelOperation)) {
                System.err.println("Se canceló operación.");
                break;
            }
            if (inputData.length() == 0) {
                System.err.println("No se permite registrar campo vacío, vuelva a intentarlo.");
            }else if (inputData.length() != 8){
                System.err.println("No se permite registrar DNI (8 caracteres), vuelva a intentarlo.");
            }else if(MenuView.userView.userExist(inputData)){
                System.out.println("");
                User user = MenuView.userView.userGetByDni(inputData);
                System.out.println("\tReporte de ventas de usuario: " + user.getUse_surname().toUpperCase() + ", " + user.getUse_name());
                System.out.println("");
                System.out.println("\tMedicamento" + "\tMonto de venta" + "\tAño");                
                System.out.println("\t--------------------------------------");
                List<Sale> listSale = MenuView.saleView.saleList();
                Locale locale = new Locale("es","MX");
                String month = "";
                String year = "";
                String monthRoute = "";
                Double amountCollected = 0.0;
                
                for (int i = 0; i < listSale.size(); i++) {
                    month = new SimpleDateFormat("MMMM", locale).format(listSale.get(i).getSal_date());
                    year = new SimpleDateFormat("yyyy").format(listSale.get(i).getSal_date());
                    
                    
                    if (!month.equals(monthRoute) && listSale.get(i).getUse_id() == user.getUse_id()) {
                        System.out.print("\t" + month.toUpperCase());
                        for (int j = 0; j < listSale.size(); j++) {
                            monthRoute = new SimpleDateFormat("MMMM", locale).format(listSale.get(j).getSal_date());
                            if (month.equals(monthRoute) && listSale.get(j).getUse_id() == user.getUse_id()) {
                                amountCollected += listSale.get(j).getSal_total();
                            }
                        }
                        System.out.print("\t\tS/. " + decimalFormat.format(amountCollected));
                        System.out.print("\t\t" + year + "\n");
                        amountCollected = 0.0;
                        
                    }
                    monthRoute = month;
                }
                System.out.println("");
                System.out.println("Ingresar DNI de usuario (*):\t[ " + MedicationControl.cancelOperation +" ] Cancelar");
                
            }else{
                System.err.println("Usuario no esta registrado. Vuelva a intentarlo.");
            }
        }
    }
    
    
    public void reportRankingUserForSale(){
        System.out.println("______________________________________________________________________________________"); 
        System.out.println("------ ---- *- -* *- "+ MedicationControl.menus[MedicationControl.menu_selected - 1] +" -* *- -* ---- ------");
        System.out.println("");
        System.out.println("\tReporte: ranking de ventas");
        System.out.println("\tUsuario : " + MenuView.userView.userGetFullNameById(user_id));
        System.out.println("");
        List<User> listUser = MenuView.userView.userList();
        System.out.println("\tPuesto\tUsuario\t\tMonto de venta");
        System.out.println("\t--------------------------------------");        
        List<UserRanking> listUserRanking = null;
        listUserRanking = new LinkedList<>();
        for (int i = 0; i < listUser.size(); i++) {
            Double amountCollected = 0.0;
            List<Sale> listSale = MenuView.saleView.saleList();
            for (int j = 0; j < listSale.size(); j++) {
                if (listSale.get(j).getUse_id() == listUser.get(i).getUse_id()){
                    amountCollected += listSale.get(j).getSal_total();
                }
            }
            UserRanking userRanking = new UserRanking();
            userRanking.setUse_id( listUser.get(i).getUse_id());
            userRanking.setUse_ran_fullname(listUser.get(i).getUse_surname().toUpperCase()+ ", " + listUser.get(i).getUse_name());
            userRanking.setUse_ran_amount_collected(amountCollected);
            listUserRanking.add(userRanking);
        }
        
        Collections.sort(listUserRanking, new Comparator<UserRanking>() {
            @Override
            public int compare(UserRanking o1, UserRanking o2) {
                return Double.compare(o1.getUse_ran_amount_collected(), o2.getUse_ran_amount_collected());
            }
        });
        Collections.reverse(listUserRanking);
        for (int i = 0; i < listUserRanking.size(); i++) {
            System.out.println("\t" + (i + 1) + "\t"+ listUserRanking.get(i).getUse_ran_fullname() + "\tS/. " + decimalFormat.format(listUserRanking.get(i).getUse_ran_amount_collected()));
        }
        System.out.println("");
        System.out.println("\t[ ENTER ] Terminar");
        input.nextLine();
    }
}
