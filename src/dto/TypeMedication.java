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
public class TypeMedication {
    private int typ_med_id;
    private String typ_med_name;    

    public TypeMedication() {
    }

    public TypeMedication(int typ_med_id, String typ_med_name) {
        this.typ_med_id = typ_med_id;
        this.typ_med_name = typ_med_name;
    }

    public int getTyp_med_id() {
        return typ_med_id;
    }

    public void setTyp_med_id(int typ_med_id) {
        this.typ_med_id = typ_med_id;
    }

    public String getTyp_med_name() {
        return typ_med_name;
    }

    public void setTyp_med_name(String typ_med_name) {
        this.typ_med_name = typ_med_name;
    }
    
}
