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
public class SaleDetail {
    private int sal_det_id;
    private int sal_id;
    private int med_id;
    private int sal_det_quantity;

    public SaleDetail() {
    }

    public SaleDetail(int sal_det_id, int sal_id, int med_id, int sal_det_quantity) {
        this.sal_det_id = sal_det_id;
        this.sal_id = sal_id;
        this.med_id = med_id;
        this.sal_det_quantity = sal_det_quantity;
    }

    public int getSal_det_id() {
        return sal_det_id;
    }

    public void setSal_det_id(int sal_det_id) {
        this.sal_det_id = sal_det_id;
    }

    public int getSal_id() {
        return sal_id;
    }

    public void setSal_id(int sal_id) {
        this.sal_id = sal_id;
    }

    public int getMed_id() {
        return med_id;
    }

    public void setMed_id(int med_id) {
        this.med_id = med_id;
    }

    public int getSal_det_quantity() {
        return sal_det_quantity;
    }

    public void setSal_det_quantity(int sal_det_quantity) {
        this.sal_det_quantity = sal_det_quantity;
    }

   
}
