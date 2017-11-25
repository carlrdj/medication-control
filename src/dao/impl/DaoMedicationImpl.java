/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.impl;

import dao.DaoMedication;
import dto.Medication;
import java.sql.Date;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author SEVEN
 */
public class DaoMedicationImpl implements DaoMedication{
    List<Medication> list = null;

    public DaoMedicationImpl() {        
        list = new LinkedList<>();
        String str1="2017-12-16";  
        Date date1=Date.valueOf(str1);
        String str2="2017-03-31";  
        Date date2=Date.valueOf(str2);
        String str3="2018-11-09";  
        Date date3=Date.valueOf(str3);
    
        Medication medication1 = new Medication(1, 1, 2, "Losartan", "Besatestazona 0.066 g, Clotindesenon 0.142 g", 94, 5.99, date1);
        Medication medication2 = new Medication(2, 1, 2, "Vitapirena", "Ibuprofeno 0.014 g, etc", 9996, 6.19, date2);
        Medication medication3 = new Medication(3, 1, 2, "Vitapirena plus", "Ibuprofeno 0.014 k, etc", 9996, 6.19, date3);
        
        list.add(medication1);
        list.add(medication2);
        list.add(medication3);
    }
    
    @Override
    public String message() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int medicationGenerateId() {        
        int mayor = 0;
        for (int i = 0; i < list.size(); i++) {
            if (mayor < list.get(i).getMed_id()) {
                mayor = list.get(i).getMed_id();           
            }
        }
        return mayor + 1;
    }

    @Override
    public Boolean medicationIns(Medication medication) {
        list.add(medication);
        return true;
    }

    @Override
    public Medication medicationGetById(Integer id) {
        Medication medication = new Medication();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getMed_id()== id) {
                medication.setMed_id(list.get(i).getMed_id());
                medication.setTyp_med_id(list.get(i).getTyp_med_id());
                medication.setPre_med_id(list.get(i).getPre_med_id());
                medication.setMed_name(list.get(i).getMed_name());
                medication.setMed_chemical_compound(list.get(i).getMed_chemical_compound());
                medication.setMed_quantity_available(list.get(i).getMed_quantity_available());    
                medication.setMed_unit_price(list.get(i).getMed_unit_price());   
                medication.setMed_due_date(list.get(i).getMed_due_date());
            }
        }
        return medication;
    }

    @Override
    public Boolean medicationUpd(Medication medication) {
        list.set(indexList(medication.getMed_id()), medication);
        return true;
    }

    @Override
    public Boolean medicationDel(Integer id) {
        list.remove(indexList(id));
        return true;
    }

    @Override
    public List<Medication> medicationList() {
        return list;
    }

    @Override
    public int indexList(Integer id) {
        int index = 0;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getMed_id()== id) {
                index = i;
                break;
            }
        }
        return index;
    }
    
}
