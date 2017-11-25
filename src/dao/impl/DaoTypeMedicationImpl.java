/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.impl;

import dao.DaoTypeMedication;
import dto.TypeMedication;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author SEVEN
 */
public class DaoTypeMedicationImpl implements DaoTypeMedication{
    List<TypeMedication> list = null;

    public DaoTypeMedicationImpl() {
        list = new LinkedList<>();
        TypeMedication typeMedication1 = new TypeMedication(1, "Antipirético");
        TypeMedication typeMedication2 = new TypeMedication(2, "Antifúngico");
        TypeMedication typeMedication3 = new TypeMedication(3, "Analgésico");
        TypeMedication typeMedication4 = new TypeMedication(4, "Antibiótico");
        TypeMedication typeMedication5 = new TypeMedication(5, "Vacinas");
        TypeMedication typeMedication6 = new TypeMedication(6, "Antisépticos");
        TypeMedication typeMedication7 = new TypeMedication(7, "Antinflamatorio");
        TypeMedication typeMedication8 = new TypeMedication(8, "Antihistamínico");
        TypeMedication typeMedication9 = new TypeMedication(9, "Anestésico");
        TypeMedication typeMedication10 = new TypeMedication(10, "Antidepresivo");
        TypeMedication typeMedication11 = new TypeMedication(11, "Diurético");
        TypeMedication typeMedication12 = new TypeMedication(12, "Laxante");
        TypeMedication typeMedication13 = new TypeMedication(13, "Broncodilatador");
        
        list.add(typeMedication1);
        list.add(typeMedication2);
        list.add(typeMedication3);
        list.add(typeMedication4);
        list.add(typeMedication5);
        list.add(typeMedication6);
        list.add(typeMedication7);
        list.add(typeMedication8);
        list.add(typeMedication9);
        list.add(typeMedication10);
        list.add(typeMedication11);
        list.add(typeMedication12);
        list.add(typeMedication13);

    }
    
    
    @Override
    public String message() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int typeMedicationGenerateId() {
        int mayor = 0;
        for (int i = 0; i < list.size(); i++) {
            if (mayor < list.get(i).getTyp_med_id()) {
                mayor = list.get(i).getTyp_med_id();           
            }
        }
        return mayor + 1;
    }

    @Override
    public Boolean typeMedicationIns(TypeMedication typeMedication) {
        list.add(typeMedication);
        return true;
    }

    @Override
    public TypeMedication typeMedicationGetById(Integer id) {
        TypeMedication typeMedication = new TypeMedication();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getTyp_med_id()== id) {
                typeMedication.setTyp_med_id(list.get(i).getTyp_med_id());
                typeMedication.setTyp_med_name(list.get(i).getTyp_med_name());            
            }
        }
        return typeMedication;
    }

    @Override
    public Boolean typeMedicationUpd(TypeMedication typeMedication) {
        list.set(indexList(typeMedication.getTyp_med_id()), typeMedication);
        return true;
    }

    @Override
    public Boolean typeMedicationDel(Integer id) {
        list.remove(indexList(id));
        return true;
    }

    @Override
    public List<TypeMedication> typeMedicationList() {
        return list;
    }

    @Override
    public int indexList(Integer id) {
        int index = 0;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getTyp_med_id()== id) {
                index = i;
                break;
            }
        }
        return index;
    }
    
}
