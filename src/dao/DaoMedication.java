/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.Medication;
import java.util.List;

/**
 *
 * @author SEVEN
 */
public interface DaoMedication {    
    //Message
    public String message();
    //Gemerate id
    public int medicationGenerateId();
    //Insert medication
    public Boolean medicationIns(Medication medication);
    //Get medication
    public Medication medicationGetById(Integer id);
    //Update medication
    public Boolean medicationUpd(Medication medication);
    //Remove medication
    public Boolean medicationDel(Integer id);
    //List medication
    public List<Medication> medicationList();
    //index
    public int indexList(Integer id);
}
