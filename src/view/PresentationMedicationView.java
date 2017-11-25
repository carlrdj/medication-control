/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import dao.DaoPresentationMedication;
import dao.impl.DaoPresentationMedicationImpl;
import dto.Medication;
import dto.PresentationMedication;
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
public class PresentationMedicationView {
    private Scanner input = null;
    private DaoPresentationMedication daoPresentationMedication = null;
    //private MenuView menuView = null;

    public PresentationMedicationView() {        
        input = new Scanner(System.in);
        daoPresentationMedication = new DaoPresentationMedicationImpl();
        //menuView = new MenuView();
    }    
    public void presentationMedicationShowOptionList(){
        String optionList = "";
        List<PresentationMedication> list = null;
        list = daoPresentationMedication.presentationMedicationList();
        
        for (int i = 0; i < list.size(); i++) {
            optionList += " [ " + list.get(i).getPre_med_id()+ " ] " + list.get(i).getPre_med_name();
            if ( ((i % MedicationControl.optionColum) == 0) && i != 0 ) {
                optionList += "\n";
            }
        }
        System.out.println(optionList);
    }
    
    public String presentationMedicationGetNameById(int id){
        PresentationMedication getPresentationMedication = daoPresentationMedication.presentationMedicationGetById(id);
        return getPresentationMedication.getPre_med_name();
    }
    
    public void presentationMedicationShowList() {
        System.out.println("______________________________________________________________________________________");     
        System.out.println("------ ---- *- -* *- "+ MedicationControl.menus[menu_selected - 1] +" -* *- -* ---- ------");
        System.out.println("");
        List<PresentationMedication> list = null;
        list = daoPresentationMedication.presentationMedicationList();
        System.out.println("\t[Id]"
                + "\t[Presentación de medicamento]");
        for (int i = 0; i < list.size(); i++) {
            System.out.println("\t"+list.get(i).getPre_med_id()+
                    "\t| "+list.get(i).getPre_med_name());
        }
    }
    
    public void presentationMedicationInsert() {  
        System.out.println("______________________________________________________________________________________"); 
        System.out.println(menus[menu_selected - 1] + " \t>>>>>\t " + sub_menus[menu_selected - 1][0]);
        System.out.println("");
        
        PresentationMedication presentationMedication = new PresentationMedication();
        presentationMedication.setPre_med_id(daoPresentationMedication.presentationMedicationGenerateId());
        System.out.println("Ingresar Presentación de medicamento (*):");
        while (true) {
            String inputData = input.nextLine();
            if (inputData.length() == 0) {
                System.err.println("No se permite registrar campo vacío, vuelva a intentarlo.");
            }else{
                presentationMedication.setPre_med_name(inputData);
                break;
            }
        }
        
        if (daoPresentationMedication.presentationMedicationIns(presentationMedication)) {
            System.out.println("Se registró correctamente.");
        }        
        presentationMedicationShowList();
        //menuView.showSubMenu(menu_selected);
    }
    
    public Boolean presentationMedicationUpdate() {
        System.out.println("______________________________________________________________________________________"); 
        System.out.println(MedicationControl.menus[MedicationControl.menu_selected - 1] + " \t>>>>>\t " + MedicationControl.sub_menus[MedicationControl.menu_selected - 1][1]);
        System.out.println("");
        System.out.println("Ingresar Id de presentación de medicamento que desea editar: ");
        PresentationMedication getPresentationMedication = null;
        PresentationMedication presentationMedication = new PresentationMedication();
        
        while (true) {
            String inputData = input.nextLine();
            try {
                int parseInputData = Integer.parseInt(inputData);
                getPresentationMedication = daoPresentationMedication.presentationMedicationGetById(parseInputData);
                if (getPresentationMedication.getPre_med_id()< 1){
                    System.err.println("No existe, vuelva a intentarlo.");
                }else{
                    break;
                }
            } catch (Exception e) {
                System.err.println("Solo se permiten digitos.");
            }
        }
        
        System.out.println("------ Editar presentación de medicamento ------");
        presentationMedication.setPre_med_id(getPresentationMedication.getPre_med_id());
        System.out.println("Ingresar Presentación de medicamento (*): " + getPresentationMedication.getPre_med_name());
        while (true) {
            String inputData = input.nextLine();
            if (inputData.equals("")) {
                presentationMedication.setPre_med_name(getPresentationMedication.getPre_med_name());
                break;
            }else{
                presentationMedication.setPre_med_name(inputData); 
                break;              
            }
        }
                   
        if (daoPresentationMedication.presentationMedicationUpd(presentationMedication)) {
            System.err.println("Se modificó correctamente.");
        }        
        presentationMedicationShowList();   
        return true;            
        
    }

    public void presentationMedicationDelete() {
        System.out.println("______________________________________________________________________________________"); 
        System.out.println(MedicationControl.menus[MedicationControl.menu_selected - 1] + " \t>>>>>\t " + MedicationControl.sub_menus[MedicationControl.menu_selected - 1][2]);
        System.out.println("\t[ " + MedicationControl.cancelOperation +" ] Cancelar");    
        System.out.println("");
        System.out.println("Ingresar Id de presentación de medicamento que desea eliminar: ");
        
        while (true) {
            String inputData = input.nextLine();
            if (inputData.equals(MedicationControl.cancelOperation)) {
                System.err.println("Se canceló operación.");
                presentationMedicationShowList();
                break;
            }
            try {
                int parseInputData = Integer.parseInt(inputData);
                PresentationMedication getPresentationMedication = daoPresentationMedication.presentationMedicationGetById(parseInputData);
                if (getPresentationMedication.getPre_med_id() < 1) {
                    System.err.println("No existe, vuelva a intentarlo.");
                }else{                     
                    if (presentatiobMedicationProtectedDelete(getPresentationMedication.getPre_med_id())) {
                        System.err.println("No se puede eliminar presentación de medicamento, esta presentación de medicamento está registrada en medicamento(s).");
                    }else{     
                        if (daoPresentationMedication.presentationMedicationDel(getPresentationMedication.getPre_med_id())) {
                            System.err.println("Se eliminó correctamente.");
                        } 
                    }           
                    presentationMedicationShowList();
                    break;
                }  
            } catch (Exception e) {
                System.err.println("Solo se permiten digitos.");
            }
        }
    }
    
    public Boolean presentatiobMedicationProtectedDelete(int id){        
        List<Medication> listMedication = null;
        listMedication = MenuView.medicationView.medicationList();
        for (int i = 0; i < listMedication.size(); i++) {
            if (listMedication.get(i).getPre_med_id()== id) {
                return true;
            }
        }
        return false;
    }
}
