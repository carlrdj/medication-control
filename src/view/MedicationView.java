/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import dao.DaoMedication;
import dao.impl.DaoMedicationImpl;
import dto.Medication;
import dto.SaleDetail;
import dto.TypeMedication;
import java.sql.Date;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Scanner;
import medication.control.MedicationControl;
import static medication.control.MedicationControl.menu_selected;
import static medication.control.MedicationControl.menus;
import static medication.control.MedicationControl.sub_menus;

/**
 *
 * @author SEVEN
 */
public class MedicationView {
    private Scanner input = null;
    private DaoMedication daoMedication = null;
    public MedicationView() {        
        input = new Scanner(System.in);
        daoMedication = new DaoMedicationImpl();
    }    
        
    public void medicationShowList() {
        System.out.println("______________________________________________________________________________________");     
        System.out.println("------ ---- *- -* *- "+ MedicationControl.menus[MedicationControl.menu_selected - 1] +" -* *- -* ---- ------");
        System.out.println("");
        List<Medication> list = null;
        list = daoMedication.medicationList();
        System.out.println("\t[Id]"
                + "\t[Tipo]"
                + "\t[Presentación]"
                + "\t[Medicamento]"
                + "\t[Componentes]"
                + "\t[Cantidad disponible]"
                + "\t[S/. Precio unitario]"
                + "\t[Fecha de vencimiento]");
        for (int i = 0; i < list.size(); i++) {
            System.out.println("\t"+list.get(i).getMed_id()+
                    "\t| "+MenuView.typeMedicationView.typeMedicationGetNameById(list.get(i).getTyp_med_id())+
                    "\t| "+MenuView.presentationMedicationView.presentationMedicationGetNameById(list.get(i).getPre_med_id())+
                    "\t| "+list.get(i).getMed_name()+
                    "\t| "+list.get(i).getMed_chemical_compound()+
                    "\t| "+list.get(i).getMed_quantity_available()+
                    "\t| "+list.get(i).getMed_unit_price()+
                    "\t| "+formatDate(list.get(i).getMed_due_date())+
                    "\t" + verifyDueDate(list.get(i).getMed_due_date()));        
        }
    }
    
    public void medicationSearch(String search) {
        System.out.println("");        
        System.out.println("\tResultados de busqueda\t--------------------------------------------------------------------------------------------------------------");
        System.out.println("");
        List<Medication> list = null;
        list = daoMedication.medicationList();
        System.out.println("\t[Id]"
                + "\t[Medicamento]"
                + "\t[Componentes]"
                + "\t[Tipo]"
                + "\t[Presentación]"
                + "\t[Cantidad disponible]"
                + "\t[S/. Precio unitario]"
                + "\t[Fecha de vencimiento]");
        Boolean result = true;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getMed_name().indexOf(search) != -1 || search.equals("") == true) {
                System.out.println("\t[ "+list.get(i).getMed_id()+" ]"+
                    "\t| "+list.get(i).getMed_name()+
                    "\t| "+list.get(i).getMed_chemical_compound()+
                    "\t| "+MenuView.typeMedicationView.typeMedicationGetNameById(list.get(i).getTyp_med_id())+
                    "\t| "+MenuView.presentationMedicationView.presentationMedicationGetNameById(list.get(i).getPre_med_id())+
                    "\t| "+list.get(i).getMed_quantity_available()+
                    "\t| "+list.get(i).getMed_unit_price()+
                    "\t| "+formatDate(list.get(i).getMed_due_date())+
                    "\t" + verifyDueDate(list.get(i).getMed_due_date()));
                result = false;
            }
        }
        if (result) {
            System.out.println("No se encontraron resultados.");
        }
    }
    
    public static java.sql.Date getDate() {
        java.util.Date today = new java.util.Date();
        return new java.sql.Date(today.getTime());
    }
    
    public String verifyDueDate(Date date){
        Date dateCurrently = new Date(getDate().getTime());
        
        if (dateCurrently.after(date)){
            return "Producto Caducado";
        }
        return "";
    }   
    
    public String medicationToBuy(Date date){
        Date dateCurrently = new Date(getDate().getTime());   
        Date dateAfterMonth = new Date(date.getTime() - 31l*24l*60l*60l*1000l);
        
        if (dateCurrently.after(dateAfterMonth)){
            return "Producto por comprar";
        }
        return "";
    }    
    
    public String formatDate(Date date) {
        DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        return df.format(date);
    }
    
    public void medicationInsert() {           
        System.out.println("______________________________________________________________________________________"); 
        System.out.println(menus[menu_selected - 1] + " \t>>>>>\t " + sub_menus[menu_selected - 1][0]);
        System.out.println("");        
        Medication medication = new Medication();
        medication.setMed_id(daoMedication.medicationGenerateId());
        System.out.println("Ingresar Tipo de medicamento (*):");
        MenuView.typeMedicationView.typeMedicationShowOptionList();
        while (true) {
            String inputData = input.nextLine();
            try {
                int parseInputData = Integer.parseInt(inputData);
                if (MenuView.typeMedicationView.typeMedicationGetNameById(parseInputData) == null){
                    System.err.println("No existe, vuelva a intentarlo.");
                }else{
                    medication.setTyp_med_id(parseInputData);
                    break;
                }
            } catch (Exception e) {
                System.err.println("Solo se permiten digitos.");
            }
        }
        System.out.println("Ingresar Presentación de medicamento (*):");
        MenuView.presentationMedicationView.presentationMedicationShowOptionList();
        while (true) {
            String inputData = input.nextLine();
            try {
                int parseInputData = Integer.parseInt(inputData);
                if (MenuView.presentationMedicationView.presentationMedicationGetNameById(parseInputData) == null){
                    System.err.println("No existe, vuelva a intentarlo.");
                }else{
                    medication.setPre_med_id(parseInputData);
                    break;
                }
            } catch (Exception e) {
                System.err.println("Solo se permiten digitos.");
            }
        }
        System.out.println("Ingresar Medicamento (*):");
        while (true) {
            String inputData = input.nextLine();
            if (inputData.length() == 0) {
                System.err.println("No se permite registrar campo vacío, vuelva a intentarlo.");
            }else{
                medication.setMed_name(inputData);
                break;
            }
        }        
        System.out.println("Ingresar Componentes químicos (*):");
        while (true) {
            String inputData = input.nextLine();
            if (inputData.length() == 0) {
                System.err.println("No se permite registrar campo vacío, vuelva a intentarlo.");
            }else{
                medication.setMed_chemical_compound(inputData);
                break;
            }
        }
        System.out.println("Ingresar Cantidad disponible (*):");
        while (true) {
            String inputData = input.nextLine();
            try {
                int parseInputData = Integer.parseInt(inputData);
                medication.setMed_quantity_available(parseInputData);
                break;
            } catch (Exception e) {
                System.err.println("Solo se permiten digitos.");
            }
        }
        System.out.println("Ingresar Precio unitario (*):");
        while (true) {
            String inputData = input.nextLine();
            try {
                Double parseInputData = Double.parseDouble(inputData);
                medication.setMed_unit_price(parseInputData);
                break;
            } catch (Exception e) {
                System.err.println("Solo se permiten decimales.");
            }
        }
        System.out.println("Ingresar Fecha de vencimiento (*) dd-mm-yyyy:");
        while (true) {
            String inputData = input.nextLine();
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
                            medication.setMed_due_date(date);
                            break;      
                        }else{
                            System.err.println("Solo se permiten fechas con el siguiete formato dd-mm-yyyy.");
                        }                        
                    } catch (Exception e) {
                        System.err.println("Solo se permiten fechas con el siguiete formato dd-mm-yyyy.");
                    }
                }
            }
        }
        
        if (daoMedication.medicationIns(medication)) {
            System.out.println("Se registró correctamente.");
        }        
        medicationShowList();
    }
    
    public Boolean medicationUpdate() {
        System.out.println("______________________________________________________________________________________"); 
        System.out.println(MedicationControl.menus[MedicationControl.menu_selected - 1] + " \t>>>>>\t " + MedicationControl.sub_menus[MedicationControl.menu_selected - 1][1]);
        System.out.println("");
        System.out.println("Ingresar Id de medicamento que desea editar: ");
        Medication getMedication = null;
        Medication medication = new Medication();
        while (true) {
            String inputData = input.nextLine();
            try {
                int parseInputData = Integer.parseInt(inputData);
                getMedication = daoMedication.medicationGetById(parseInputData);
                if (getMedication.getMed_id() < 1){
                    System.err.println("No existe, vuelva a intentarlo.");
                }else{
                    break;
                }
            } catch (Exception e) {
                System.err.println("Solo se permiten digitos.");
            }
        }
        
        System.out.println("------ Editar medicamento ------");
        medication.setMed_id(getMedication.getMed_id());
        System.out.println("Ingresar Tipo de medicamento (*): [ "  + getMedication.getTyp_med_id() + " ] " + MenuView.typeMedicationView.typeMedicationGetNameById(getMedication.getTyp_med_id()));
        MenuView.presentationMedicationView.presentationMedicationShowOptionList();
        while (true) {
            String inputData = input.nextLine();
            if (inputData.equals("")) {
                medication.setTyp_med_id(getMedication.getTyp_med_id());
                break;
            }else{
                try {
                    int parseInputData = Integer.parseInt(inputData);
                    if (MenuView.presentationMedicationView.presentationMedicationGetNameById(parseInputData) == null){
                        System.err.println("No existe, vuelva a intentarlo.");
                    }else{
                        medication.setTyp_med_id(parseInputData);
                        break;
                    }
                } catch (Exception e) {
                    System.err.println("Solo se permiten digitos.");
                }
            }
        }
        System.out.println("Ingresar Presentación de medicamento (*): [ "  + getMedication.getPre_med_id()+ " ] " + MenuView.presentationMedicationView.presentationMedicationGetNameById(getMedication.getPre_med_id()));
        MenuView.presentationMedicationView.presentationMedicationShowOptionList();
        while (true) {
            String inputData = input.nextLine();
            if (inputData.equals("")) {
                medication.setPre_med_id(getMedication.getPre_med_id());
                break;
            }else{
                try {
                    int parseInputData = Integer.parseInt(inputData);
                    if (MenuView.presentationMedicationView.presentationMedicationGetNameById(parseInputData) == null){
                        System.err.println("No existe, vuelva a intentarlo.");
                    }else{
                        medication.setPre_med_id(parseInputData);
                        break;
                    }
                } catch (Exception e) {
                    System.err.println("Solo se permiten digitos.");
                }
            }
        }
        System.out.println("Ingresar Medicamento (*): " + getMedication.getMed_name());
        while (true) {
            String inputData = input.nextLine();
            if (inputData.equals("")) {
                medication.setMed_name(getMedication.getMed_name());
                break;
            }else{
                medication.setMed_name(inputData);
                break;
            }
        }        
        System.out.println("Ingresar Componentes químicos (*): " + getMedication.getMed_chemical_compound());
        while (true) {
            String inputData = input.nextLine();
            if (inputData.equals("")) {
                medication.setMed_chemical_compound(getMedication.getMed_chemical_compound());
                break;
            }else{
                medication.setMed_chemical_compound(inputData);
                break;
            }
        }
        System.out.println("Ingresar Cantidad disponible (*): " + getMedication.getMed_quantity_available());
        while (true) {
            String inputData = input.nextLine();
            if (inputData.equals("")) {
                medication.setMed_quantity_available(getMedication.getMed_quantity_available());
                break;
            }else{
                try {
                    int parseInputData = Integer.parseInt(inputData);
                    medication.setMed_quantity_available(parseInputData);
                    break;
                } catch (Exception e) {
                    System.err.println("Solo se permiten digitos.");
                }
            }
        }
        System.out.println("Ingresar Precio unitario (*): " + getMedication.getMed_unit_price());
        while (true) {
            String inputData = input.nextLine();
            if (inputData.equals("")) {
                medication.setMed_unit_price(getMedication.getMed_unit_price());
                break;
            }else{
                try {
                    Double parseInputData = Double.parseDouble(inputData);
                    medication.setMed_unit_price(parseInputData);
                    break;
                } catch (Exception e) {
                    System.err.println("Solo se permiten decimales.");
                }
            }
        }
        
        System.out.println("Ingresar Fecha de vencimiento (*) dd-mm-yyyy: " + formatDate(getMedication.getMed_due_date()));
        while (true) {
            String inputData = input.nextLine();
            if (inputData.equals("")) {
                medication.setMed_due_date(getMedication.getMed_due_date());
                break;
            }else{
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
                                medication.setMed_due_date(date);
                                break;                                
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

        if (daoMedication.medicationUpd(medication)) {
            System.err.println("Se modificó correctamente.");
        }        
        medicationShowList();
        return true;       
        
    }

    public void medicationDelete() {
        System.out.println("______________________________________________________________________________________"); 
        System.out.println(MedicationControl.menus[MedicationControl.menu_selected - 1] + " \t>>>>>\t " + MedicationControl.sub_menus[MedicationControl.menu_selected - 1][2]);
        System.out.println("\t[ " + MedicationControl.cancelOperation +" ] Cancelar");
        System.out.println("");
        System.out.println("Ingresar Id de medicamento que desea eliminar: ");
        while (true) {
            String inputData = input.nextLine();
            if (inputData.equals(MedicationControl.cancelOperation)) {
                System.err.println("Se canceló operación.");
                medicationShowList();
                break;
            }
            try {
                int parseInputData = Integer.parseInt(inputData);
                Medication getMedication = daoMedication.medicationGetById(parseInputData);
                if (getMedication.getMed_id()< 1) {
                    System.err.println("No existe, vuelva a intentarlo.");
                }else{  
                    if (medicationProtectedDelete(getMedication.getMed_id())) {
                        System.err.println("No se puede eliminar medicamento, este medicamento está registrado en ventas.");
                    }else{                        
                        if (daoMedication.medicationDel(getMedication.getMed_id())) {
                            System.err.println("Se eliminó correctamente.");
                        }   
                    }          
                    
                               
                    medicationShowList();
                    break;
                }  
            } catch (Exception e) {
                System.err.println("Solo se permiten digitos.");
            }
        }
    }
    
    public Boolean medicationVerifySelectSearch(int id, String search){
        List<Medication> list = null;
        list = daoMedication.medicationList();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getMed_name().indexOf(search) != -1 || search.equals("")) {
                if (list.get(i).getMed_id() == id) {                    
                    if(verifyDueDate(list.get(i).getMed_due_date()).equals("")){
                        return true;
                    }
                }
            }
        }
        return false;
    }
    
    public Boolean medicationVerifyQuantityById(int id, int quantity){
        List<Medication> list = null;
        list = daoMedication.medicationList();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getMed_id() == id) {
                if (list.get(i).getMed_quantity_available() >= quantity) {
                    return true;
                }
            }
        }
        return false;
    }
    
    public String medicationGetNameById(int id){
        List<Medication> list = null;
        list = daoMedication.medicationList();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getMed_id() == id) {
                return list.get(i).getMed_name();
            }
        }
        return "";
    }
    
    public Double medicationGetUnitPriceById(int id){
        List<Medication> list = null;
        list = daoMedication.medicationList();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getMed_id() == id) {
                return list.get(i).getMed_unit_price();
            }
        }
        return 0.0;
    }
    public Double medicationGetSubTotalByIdAndQuantity(int id, int quantity){
        List<Medication> list = null;
        list = daoMedication.medicationList();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getMed_id() == id) {
                return list.get(i).getMed_unit_price() * quantity;
            }
        }
        return 0.0;
    }
    
    public Boolean medicationUpdateQuantity(List<SaleDetail> listSaleDetail){
        List<Medication> list = null;
        list = daoMedication.medicationList();
        for (int i = 0; i < list.size(); i++) {
            for (int j = 0; j < listSaleDetail.size(); j++) {
                if (list.get(i).getMed_id() == listSaleDetail.get(j).getMed_id()) {
                    Medication medication = new Medication();
                    medication.setMed_id(list.get(i).getMed_id());
                    medication.setPre_med_id(list.get(i).getPre_med_id());
                    medication.setTyp_med_id(list.get(i).getTyp_med_id());
                    medication.setMed_name(list.get(i).getMed_name());
                    medication.setMed_chemical_compound(list.get(i).getMed_chemical_compound());
                    medication.setMed_unit_price(list.get(i).getMed_unit_price());
                    medication.setMed_quantity_available(list.get(i).getMed_quantity_available() - listSaleDetail.get(j).getSal_det_quantity());
                    medication.setMed_due_date(list.get(i).getMed_due_date());
                    daoMedication.medicationUpd(medication);
                }
            }
        }
        return true;
    }
    
    public List<Medication> medicationList(){
        return daoMedication.medicationList();
    }
    
    public Boolean medicationProtectedDelete(int id){        
        List<SaleDetail> listSaleDetail = null;
        listSaleDetail = MenuView.saleView.saleDetailList();
        for (int i = 0; i < listSaleDetail.size(); i++) {
            if (listSaleDetail.get(i).getMed_id()== id) {
                return true;
            }
        }
        return false;
    }
}
