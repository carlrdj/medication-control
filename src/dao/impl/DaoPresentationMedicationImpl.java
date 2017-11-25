/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.impl;

import dao.DaoPresentationMedication;
import dto.PresentationMedication;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author SEVEN
 */
public class DaoPresentationMedicationImpl implements DaoPresentationMedication{
    List<PresentationMedication> list = null;
    
    public DaoPresentationMedicationImpl() {
        list = new LinkedList<>();
        PresentationMedication presentationMedication1 = new PresentationMedication(1, "Pastilla");
        PresentationMedication presentationMedication2 = new PresentationMedication(2, "Capsula");
        PresentationMedication presentationMedication3 = new PresentationMedication(3, "Polvo");
        PresentationMedication presentationMedication4 = new PresentationMedication(4, "Granulado");
        PresentationMedication presentationMedication5 = new PresentationMedication(5, "Trocisco");
        PresentationMedication presentationMedication6 = new PresentationMedication(6, "Preparados liberación prolongada");
        PresentationMedication presentationMedication7 = new PresentationMedication(7, "Crema");
        PresentationMedication presentationMedication8 = new PresentationMedication(8, "Emplasto");
        PresentationMedication presentationMedication9 = new PresentationMedication(9, "Linimentos");
        PresentationMedication presentationMedication10 = new PresentationMedication(10, "Suspensión");
        PresentationMedication presentationMedication11 = new PresentationMedication(11, "Soluciones");
        PresentationMedication presentationMedication12 = new PresentationMedication(12, "Lociones");
        PresentationMedication presentationMedication13 = new PresentationMedication(13, "Inhalaciones");
        PresentationMedication presentationMedication14 = new PresentationMedication(14, "Jarabes");
        PresentationMedication presentationMedication15 = new PresentationMedication(15, "Ampolla");
        PresentationMedication presentationMedication16 = new PresentationMedication(16, "Tintura");
        PresentationMedication presentationMedication17 = new PresentationMedication(17, "Mucílago");
        
        list.add(presentationMedication1);
        list.add(presentationMedication2);
        list.add(presentationMedication3);
        list.add(presentationMedication4);
        list.add(presentationMedication5);
        list.add(presentationMedication6);
        list.add(presentationMedication7);
        list.add(presentationMedication8);
        list.add(presentationMedication9);
        list.add(presentationMedication10);
        list.add(presentationMedication11);
        list.add(presentationMedication12);
        list.add(presentationMedication13);
        list.add(presentationMedication14);
        list.add(presentationMedication15);
        list.add(presentationMedication16);
        list.add(presentationMedication17);
    }
    
    @Override
    public String message() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int presentationMedicationGenerateId() {        
        int mayor = 0;
        for (int i = 0; i < list.size(); i++) {
            if (mayor < list.get(i).getPre_med_id()) {
                mayor = list.get(i).getPre_med_id();           
            }
        }
        return mayor + 1;
    }

    @Override
    public Boolean presentationMedicationIns(PresentationMedication presentationMedication) {
        list.add(presentationMedication);
        return true;
    }

    @Override
    public PresentationMedication presentationMedicationGetById(Integer id) {
        PresentationMedication presentationMedication = new PresentationMedication();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getPre_med_id()== id) {
                presentationMedication.setPre_med_id(list.get(i).getPre_med_id());
                presentationMedication.setPre_med_name(list.get(i).getPre_med_name());              
            }
        }
        return presentationMedication;
    }

    @Override
    public Boolean presentationMedicationUpd(PresentationMedication presentationMedication) {
        list.set(indexList(presentationMedication.getPre_med_id()), presentationMedication);
        return true;
    }

    @Override
    public Boolean presentationMedicationDel(Integer id) {
        list.remove(indexList(id));
        return true;
    }

    @Override
    public List<PresentationMedication> presentationMedicationList() {
        return list;
    }

    @Override
    public int indexList(Integer id) {
        int index = 0;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getPre_med_id()== id) {
                index = i;
                break;
            }
        }
        return index;
    }
    
}
