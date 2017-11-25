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
public class Client {
    private int cli_id;
    private String cli_dni;
    private String cli_surname;
    private String cli_name;
    private int cli_bonus_points;

    public Client() {
    }

    public Client(int cli_id, String cli_dni, String cli_surname, String cli_name, int cli_bonus_points) {
        this.cli_id = cli_id;
        this.cli_dni = cli_dni;
        this.cli_surname = cli_surname;
        this.cli_name = cli_name;
        this.cli_bonus_points = cli_bonus_points;
    }

    public int getCli_id() {
        return cli_id;
    }

    public void setCli_id(int cli_id) {
        this.cli_id = cli_id;
    }

    public String getCli_dni() {
        return cli_dni;
    }

    public void setCli_dni(String cli_dni) {
        this.cli_dni = cli_dni;
    }

    public String getCli_surname() {
        return cli_surname;
    }

    public void setCli_surname(String cli_surname) {
        this.cli_surname = cli_surname;
    }

    public String getCli_name() {
        return cli_name;
    }

    public void setCli_name(String cli_name) {
        this.cli_name = cli_name;
    }

    public int getCli_bonus_points() {
        return cli_bonus_points;
    }

    public void setCli_bonus_points(int cli_bonus_points) {
        this.cli_bonus_points = cli_bonus_points;
    }
}
