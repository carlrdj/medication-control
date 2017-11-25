/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.TypeMedication;
import java.util.List;

/**
 *
 * @author SEVEN
 */
public interface DaoTypeMedication {    
    //Message
    public String message();
    //Gemerate id
    public int typeMedicationGenerateId();
    //Insert type medication
    public Boolean typeMedicationIns(TypeMedication typeMedication);
    //Get type medication
    public TypeMedication typeMedicationGetById(Integer id);
    //Update type medication
    public Boolean typeMedicationUpd(TypeMedication typeMedication);
    //Remove type medication
    public Boolean typeMedicationDel(Integer id);
    //List type medication
    public List<TypeMedication> typeMedicationList();
    //index
    public int indexList(Integer id);
}
