/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.PresentationMedication;
import java.util.List;

/**
 *
 * @author SEVEN
 */
public interface DaoPresentationMedication {    
    //Message
    public String message();
    //Gemerate id
    public int presentationMedicationGenerateId();
    //Insert presentation medication
    public Boolean presentationMedicationIns(PresentationMedication presentationMedication);
    //Get presentation medication
    public PresentationMedication presentationMedicationGetById(Integer id);
    //Update presentation medication
    public Boolean presentationMedicationUpd(PresentationMedication presentationMedication);
    //Remove presentation medication
    public Boolean presentationMedicationDel(Integer id);
    //List presentation medication
    public List<PresentationMedication> presentationMedicationList();
    //index
    public int indexList(Integer id);
}
