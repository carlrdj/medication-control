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
public class User {
    private int use_id;
    private String use_dni;
    private String use_surname;
    private String use_name;
    private String use_user;
    private String use_password;

    public User() {
    }

    public User(int use_id, String use_dni, String use_surname, String use_name, String use_user, String use_password) {
        this.use_id = use_id;
        this.use_dni = use_dni;
        this.use_surname = use_surname;
        this.use_name = use_name;
        this.use_user = use_user;
        this.use_password = use_password;
    }

    public int getUse_id() {
        return use_id;
    }

    public void setUse_id(int use_id) {
        this.use_id = use_id;
    }

    public String getUse_dni() {
        return use_dni;
    }

    public void setUse_dni(String use_dni) {
        this.use_dni = use_dni;
    }

    public String getUse_surname() {
        return use_surname;
    }

    public void setUse_surname(String use_surname) {
        this.use_surname = use_surname;
    }

    public String getUse_name() {
        return use_name;
    }

    public void setUse_name(String use_name) {
        this.use_name = use_name;
    }

    public String getUse_user() {
        return use_user;
    }

    public void setUse_user(String use_user) {
        this.use_user = use_user;
    }

    public String getUse_password() {
        return use_password;
    }

    public void setUse_password(String use_password) {
        this.use_password = use_password;
    }
    
    
}
