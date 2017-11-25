/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import java.sql.Date;

/**
 *
 * @author SEVEN
 */
public class Medication {
    private int med_id;
    private int typ_med_id;
    private int pre_med_id;
    private String med_name;
    private String med_chemical_compound;
    private int med_quantity_available;
    private Double med_unit_price;
    private Date med_due_date;

    public Medication() {
    }

    public Medication(int med_id, int typ_med_id, int pre_med_id, String med_name, String med_chemical_compound, int med_quantity_available, Double med_unit_price, Date med_due_date) {
        this.med_id = med_id;
        this.typ_med_id = typ_med_id;
        this.pre_med_id = pre_med_id;
        this.med_name = med_name;
        this.med_chemical_compound = med_chemical_compound;
        this.med_quantity_available = med_quantity_available;
        this.med_unit_price = med_unit_price;
        this.med_due_date = med_due_date;
    }

    public int getMed_id() {
        return med_id;
    }

    public void setMed_id(int med_id) {
        this.med_id = med_id;
    }

    public int getTyp_med_id() {
        return typ_med_id;
    }

    public void setTyp_med_id(int typ_med_id) {
        this.typ_med_id = typ_med_id;
    }

    public int getPre_med_id() {
        return pre_med_id;
    }

    public void setPre_med_id(int pre_med_id) {
        this.pre_med_id = pre_med_id;
    }

    public String getMed_name() {
        return med_name;
    }

    public void setMed_name(String med_name) {
        this.med_name = med_name;
    }

    public String getMed_chemical_compound() {
        return med_chemical_compound;
    }

    public void setMed_chemical_compound(String med_chemical_compound) {
        this.med_chemical_compound = med_chemical_compound;
    }

    public int getMed_quantity_available() {
        return med_quantity_available;
    }

    public void setMed_quantity_available(int med_quantity_available) {
        this.med_quantity_available = med_quantity_available;
    }

    public Double getMed_unit_price() {
        return med_unit_price;
    }

    public void setMed_unit_price(Double med_unit_price) {
        this.med_unit_price = med_unit_price;
    }

    public Date getMed_due_date() {
        return med_due_date;
    }

    public void setMed_due_date(Date med_due_date) {
        this.med_due_date = med_due_date;
    }

    
}
