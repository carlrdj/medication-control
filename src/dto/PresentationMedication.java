/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

/**
 *
 * @author SEVEN
 */
public class PresentationMedication {
    
    private int pre_med_id;
    private String pre_med_name;

    public PresentationMedication() {
    }

    public PresentationMedication(int pre_med_id, String pre_med_name) {
        this.pre_med_id = pre_med_id;
        this.pre_med_name = pre_med_name;
    }

    public int getPre_med_id() {
        return pre_med_id;
    }

    public void setPre_med_id(int pre_med_id) {
        this.pre_med_id = pre_med_id;
    }

    public String getPre_med_name() {
        return pre_med_name;
    }

    public void setPre_med_name(String pre_med_name) {
        this.pre_med_name = pre_med_name;
    }
    
    
}
