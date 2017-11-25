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
public class UserRanking {
    private int use_id;
    private String use_ran_fullname;
    private Double use_ran_amount_collected;

    public UserRanking() {
    }

    public UserRanking(int use_id, String use_ran_fullname, Double use_ran_amount_collected) {
        this.use_id = use_id;
        this.use_ran_fullname = use_ran_fullname;
        this.use_ran_amount_collected = use_ran_amount_collected;
    }

    public int getUse_id() {
        return use_id;
    }

    public void setUse_id(int use_id) {
        this.use_id = use_id;
    }

    public String getUse_ran_fullname() {
        return use_ran_fullname;
    }

    public void setUse_ran_fullname(String use_ran_fullname) {
        this.use_ran_fullname = use_ran_fullname;
    }

    public Double getUse_ran_amount_collected() {
        return use_ran_amount_collected;
    }

    public void setUse_ran_amount_collected(Double use_ran_amount_collected) {
        this.use_ran_amount_collected = use_ran_amount_collected;
    }
    
}
