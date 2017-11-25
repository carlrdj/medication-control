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
public class Sale {    
    private int sal_id;
    private int use_id;
    private int cli_id;
    private Double sal_discount;
    private Double sal_total;
    private Date sal_date;

    public Sale() {
    }

    public Sale(int sal_id, int use_id, int cli_id, Double sal_discount, Double sal_total, Date sal_date) {
        this.sal_id = sal_id;
        this.use_id = use_id;
        this.cli_id = cli_id;
        this.sal_discount = sal_discount;
        this.sal_total = sal_total;
        this.sal_date = sal_date;
    }

    public int getSal_id() {
        return sal_id;
    }

    public void setSal_id(int sal_id) {
        this.sal_id = sal_id;
    }

    public int getUse_id() {
        return use_id;
    }

    public void setUse_id(int use_id) {
        this.use_id = use_id;
    }

    public int getCli_id() {
        return cli_id;
    }

    public void setCli_id(int cli_id) {
        this.cli_id = cli_id;
    }

    public Double getSal_discount() {
        return sal_discount;
    }

    public void setSal_discount(Double sal_discount) {
        this.sal_discount = sal_discount;
    }

    public Double getSal_total() {
        return sal_total;
    }

    public void setSal_total(Double sal_total) {
        this.sal_total = sal_total;
    }

    public Date getSal_date() {
        return sal_date;
    }

    public void setSal_date(Date sal_date) {
        this.sal_date = sal_date;
    }
    
    
}
