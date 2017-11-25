/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import dao.DaoTypeMedication;
import dao.impl.DaoTypeMedicationImpl;
import dto.Medication;
import dto.TypeMedication;
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
public class TypeMedicationView {
    private Scanner input = null;
    private DaoTypeMedication daoTypeMedication = null;

    public TypeMedicationView() {        
        input = new Scanner(System.in);
        daoTypeMedication = new DaoTypeMedicationImpl();
    }
    
    public String typeMedicationGetNameById(int id){
        TypeMedication getTypeMedication = daoTypeMedication.typeMedicationGetById(id);
        return getTypeMedication.getTyp_med_name();
    }
    
    public void typeMedicationShowOptionList(){
        String optionList = "";
        List<TypeMedication> list = null;
        list = daoTypeMedication.typeMedicationList();
        
        for (int i = 0; i < list.size(); i++) {
            optionList += " [ " + list.get(i).getTyp_med_id()+ " ] " + list.get(i).getTyp_med_name();
            if ( ((i % MedicationControl.optionColum) == 0) && i != 0 ) {
                optionList += "\n";
            }
        }
        System.out.println(optionList);        
    }
    
    public void typeMedicationShowList() {
        System.out.println("______________________________________________________________________________________");     
        System.out.println("------ ---- *- -* *- "+ MedicationControl.menus[menu_selected - 1] +" -* *- -* ---- ------");
        System.out.println("");
        List<TypeMedication> list = null;
        list = daoTypeMedication.typeMedicationList();
        System.out.println("\t[Id]"
                + "\t[Tipo de medicamento]");
        for (int i = 0; i < list.size(); i++) {
            System.out.println("\t"+list.get(i).getTyp_med_id()+
                    "\t| "+list.get(i).getTyp_med_name());
        }
    }
    
    public void typeMedicationInsert() {  
        System.out.println("______________________________________________________________________________________"); 
        System.out.println(menus[menu_selected - 1] + " \t>>>>>\t " + sub_menus[menu_selected - 1][0]);
        System.out.println("");
        
        TypeMedication typeMedication = new TypeMedication();
        typeMedication.setTyp_med_id(daoTypeMedication.typeMedicationGenerateId());
        System.out.println("Ingresar Tipo de medicamento (*):");
        while (true) {
            String inputData = input.nextLine();
            if (inputData.length() == 0) {
                System.err.println("No se permite registrar campo vacío, vuelva a intentarlo.");
            }else{
                typeMedication.setTyp_med_name(inputData);
                break;
            }
        }
        if (daoTypeMedication.typeMedicationIns(typeMedication)) {
            System.out.println("Se registró correctamente.");
        }        
        typeMedicationShowList();
        //menuView.showSubMenu(menu_selected);
    }
    
    public Boolean typeMedicationUpdate() {
        System.out.println("______________________________________________________________________________________"); 
        System.out.println(MedicationControl.menus[MedicationControl.menu_selected - 1] + " \t>>>>>\t " + MedicationControl.sub_menus[MedicationControl.menu_selected - 1][1]);
        System.out.println("");
        System.out.println("Ingresar Id de tipo de medicamento que desea editar: ");
        TypeMedication getTypeMedication = null;
        TypeMedication typeMedication = new TypeMedication();
        while (true) {
            String inputData = input.nextLine();
            try {
                int parseInputData = Integer.parseInt(inputData);
                getTypeMedication = daoTypeMedication.typeMedicationGetById(parseInputData);
                if (getTypeMedication.getTyp_med_id()< 1){
                    System.err.println("No existe, vuelva a intentarlo.");
                }else{
                    break;
                }
            } catch (Exception e) {
                System.err.println("Solo se permiten digitos.");
            }
        }
        
        System.out.println("------ Editar usuario ------");
        typeMedication.setTyp_med_id(getTypeMedication.getTyp_med_id());
        System.out.println("Ingresar Tipo de medicamento (*): " + getTypeMedication.getTyp_med_name());
        while (true) {
            String inputData = input.nextLine();
            if (inputData.equals("")) {
                typeMedication.setTyp_med_name(getTypeMedication.getTyp_med_name());
                break;
            }else{
                typeMedication.setTyp_med_name(inputData);
                break;                 
            }
        }

        if (daoTypeMedication.typeMedicationUpd(typeMedication)) {
            System.err.println("Se modificó correctamente.");
        }        
        typeMedicationShowList();   
        return true;
        
        
    }

    public void typeMedicationDelete() {
        System.out.println("______________________________________________________________________________________"); 
        System.out.println(MedicationControl.menus[MedicationControl.menu_selected - 1] + " \t>>>>>\t " + MedicationControl.sub_menus[MedicationControl.menu_selected - 1][2]);
        System.out.println("\t[ " + MedicationControl.cancelOperation +" ] Cancelar");
        System.out.println("");
        System.out.println("Ingresar Id de tipo de medicamento que desea eliminar: ");
        
        
        while (true) {
            String inputData = input.nextLine();
            if (inputData.equals(MedicationControl.cancelOperation)) {
                System.err.println("Se canceló operación.");
                typeMedicationShowList();
                break;
            }
            try {
                int parseInputData = Integer.parseInt(inputData);
                TypeMedication getTypeMedication = daoTypeMedication.typeMedicationGetById(parseInputData);
                if (getTypeMedication.getTyp_med_id()< 1) {
                    System.err.println("No existe, vuelva a intentarlo.");
                }else{
                    if (typeMedicationProtectedDelete(getTypeMedication.getTyp_med_id())) {
                        System.err.println("No se puede eliminar tipo de medicamento, este tipo de medicamento está registrado en medicamento(s).");
                    }else{                        
                        if (daoTypeMedication.typeMedicationDel(getTypeMedication.getTyp_med_id())) {
                            System.err.println("Se eliminó correctamente.");
                        }  
                    }
                                
                    typeMedicationShowList();
                    break;
                }  
            } catch (Exception e) {
                System.err.println("Solo se permiten digitos.");
            }
        }
    }
    
    public Boolean typeMedicationProtectedDelete(int id){        
        List<Medication> listMedication = null;
        listMedication = MenuView.medicationView.medicationList();
        for (int i = 0; i < listMedication.size(); i++) {
            if (listMedication.get(i).getTyp_med_id()== id) {
                return true;
            }
        }
        return false;
    }
}
