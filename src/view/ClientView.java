/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import dao.DaoClient;
import dao.impl.DaoClientImpl;
import dto.Client;
import dto.Sale;
import java.util.List;
import java.util.Scanner;
import medication.control.MedicationControl;
import static medication.control.MedicationControl.menu_selected;
import static medication.control.MedicationControl.menus;
import static medication.control.MedicationControl.sub_menus;

/**
 *
 * @author SEVEN
 */
public class ClientView {
    private Scanner input = null;
    private DaoClient daoClient = null;
    //private MenuView menuView = null;

    public ClientView() {        
        input = new Scanner(System.in);
        daoClient = new DaoClientImpl();
        //menuView = new MenuView();
    }    
    
    public void clientShowList() {
        
        System.out.println("______________________________________________________________________________________");     
        System.out.println("------ ---- *- -* *- "+ MedicationControl.menus[MedicationControl.menu_selected - 1] +" -* *- -* ---- ------");
        System.out.println("");
        List<Client> list = null;
        list = daoClient.clientList();
        System.out.println("\t[Id]"
                + "\t[DNI]"
                + "\t[Nombres]"
                + "\t[Apellidos]"
                + "\t[Puntos bonus]");
        for (int i = 0; i < list.size(); i++) {
            System.out.println("\t"+list.get(i).getCli_id()+
                    "\t| "+list.get(i).getCli_dni()+
                    "\t| "+list.get(i).getCli_name()+
                    "\t| "+list.get(i).getCli_surname()+
                    "\t| "+list.get(i).getCli_bonus_points());
        }
    }
    
    public void clientInsert() {  
        System.out.println("______________________________________________________________________________________"); 
        System.out.println(menus[menu_selected - 1] + " \t>>>>>\t " + sub_menus[menu_selected - 1][0]);
        System.out.println("");
        
        Client client = new Client();
        client.setCli_id(daoClient.clientGenerateId());
        Boolean go = false;
        System.out.println("Ingresar DNI (*):");
        while (true) {
            String inputData = input.nextLine();
            if (inputData.length() == 0) {
                System.err.println("No se permite registrar campo vacío, vuelva a intentarlo.");
            }else if (inputData.length() != 8){
                System.err.println("No se permite registrar DNI (8 caracteres), vuelva a intentarlo.");
            }else if(clientExist(inputData)){
                System.err.println("Cliente con DNI: '" + inputData + "' ya esta registrado.");
                break;
            }else{
                client.setCli_dni(inputData);
                go = true;
                break;
            }
        }
        if(go){
            System.out.println("Ingresar Nombres (*):");
            while (true) {
                String inputData = input.nextLine();
                if (inputData.length() == 0) {
                    System.err.println("No se permite registrar campo vacío, vuelva a intentarlo.");
                }else{
                    client.setCli_name(inputData);
                    break;
                }
            }        
            System.out.println("Ingresar Apellidos (*):");
            while (true) {
                String inputData = input.nextLine();
                if (inputData.length() == 0) {
                    System.err.println("No se permite registrar campo vacío, vuelva a intentarlo.");
                }else{
                    client.setCli_surname(inputData);
                    break;
                }
            }
            client.setCli_bonus_points(0);

            if (daoClient.clientIns(client)) {
                System.out.println("Se registró correctamente.");
            }
        }
        clientShowList();
    }
    
    public Boolean clientUpdate() {
        System.out.println("______________________________________________________________________________________"); 
        System.out.println(MedicationControl.menus[MedicationControl.menu_selected - 1] + " \t>>>>>\t " + MedicationControl.sub_menus[MedicationControl.menu_selected - 1][1]);
        System.out.println("\t[ " + MedicationControl.cancelOperation +" ] Cancelar");
        System.out.println("");
        System.out.println("Ingresar Id de cliente que desea editar: ");
        Client getClient = null;
        Client client = new Client();
        while (true) {
            String inputData = input.nextLine();
            if (inputData.equals(MedicationControl.cancelOperation)) {
                System.err.println("Se canceló operación.");
                clientShowList();
                break;
            }
            try {
                int parseInputData = Integer.parseInt(inputData);
                getClient = daoClient.clientGetById(parseInputData);
                if (getClient.getCli_id()< 1){
                    System.err.println("No existe, vuelva a intentarlo.");
                }else{
                    break;
                }
            } catch (Exception e) {
                System.err.println("Solo se permiten digitos.");
            }
        }
        
            
        System.out.println("------ Editar cliente ------");
        client.setCli_id(getClient.getCli_id());
        Boolean go = false;
        System.out.println("Ingresar DNI (*): " + getClient.getCli_dni());
        while (true) {
            String inputData = input.nextLine();
            if(inputData.equals("")){
                client.setCli_dni(getClient.getCli_dni());
                break;
            }else{
                if (inputData.length() != 8){
                    System.err.println("No se permite registrar DNI (8 caracteres), vuelva a intentarlo.");
                }else if(clientExist(inputData)){
                    System.err.println("Cliente con DNI: '" + inputData + "' ya esta registrado.");
                    break;
                }else{
                    client.setCli_dni(inputData);
                    go = true;
                    break;
                }                    
            }
        }
        if (go) {
            System.out.println("Ingresar Nombres (*): " + getClient.getCli_name());
            while (true) {
                String inputData = input.nextLine();
                if(inputData.equals("")){
                    client.setCli_name(getClient.getCli_name());
                    break;
                }else{
                    client.setCli_name(inputData);
                    break;
                }                    
            }        
            System.out.println("Ingresar Apellidos (*): " + getClient.getCli_surname());
            while (true) {
                String inputData = input.nextLine();
                if(inputData.equals("")){
                    client.setCli_surname(getClient.getCli_surname());
                    break;
                }else{
                    client.setCli_surname(inputData);
                    break;
                }                    
            }           
            client.setCli_bonus_points(getClient.getCli_bonus_points());            
            if (daoClient.clientUpd(client)) {
                System.err.println("Se modificó correctamente.");
            }
        }
            
        clientShowList();     
        return true;   
    }
    
    public Boolean clientUpdateBonusPoint(){
        System.out.println("______________________________________________________________________________________"); 
        System.out.println(MedicationControl.menus[MedicationControl.menu_selected - 1] + " \t>>>>>\t " + MedicationControl.sub_menus[MedicationControl.menu_selected - 1][1]);
        //System.out.println("\t[ " + MedicationControl.cancelOperation +" ] Cancelar");
        System.out.println("");
        System.out.println("Ingresar Id de cliente que desea editar sus puntos bono: ");
        Client getClient = null;
        Client client = new Client();
        Boolean go = false;
        while (true) {
            String inputData = input.nextLine();
            try {
                int parseInputData = Integer.parseInt(inputData);
                getClient = daoClient.clientGetById(parseInputData);
                if (getClient.getCli_id()< 1){
                    System.err.println("No existe, vuelva a intentarlo.");
                }else{
                    go = true;
                    break;
                }
            } catch (Exception e) {
                System.err.println("Solo se permiten digitos.");
            }
        }        
        if (go) {
            System.out.println("------ Editar puntos bono de cliente ------");
            System.out.println("Cliente: " + getClient.getCli_surname().toLowerCase() + ", " + getClient.getCli_name());        
            client.setCli_id(getClient.getCli_id());
            client.setCli_dni(getClient.getCli_dni());
            client.setCli_name(getClient.getCli_name());
            client.setCli_surname(getClient.getCli_surname());

            System.out.println("Ingresar puntos bono : " + getClient.getCli_bonus_points());
            while (true) {
                String inputData = input.nextLine();
                if(inputData.equals("")){
                    client.setCli_bonus_points(getClient.getCli_bonus_points());
                    break;
                }else{
                    try {
                        int parseInputData = Integer.parseInt(inputData);
                        client.setCli_bonus_points(parseInputData);
                        break;
                    } catch (Exception e) {
                        System.err.println("Solo se permiten digitos.");
                    }
                }                    
            }           

            if (daoClient.clientUpd(client)) {
                System.err.println("Se modificó correctamente.");
            }                
        }
        clientShowList();
        return true;
    }

    public void clientDelete() {
        System.out.println("______________________________________________________________________________________");
        System.out.println(MedicationControl.menus[MedicationControl.menu_selected - 1] + " \t>>>>>\t " + MedicationControl.sub_menus[MedicationControl.menu_selected - 1][2]);
        System.out.println("\t[ " + MedicationControl.cancelOperation +" ] Cancelar");
        System.out.println("");
        System.out.println("Ingresar Id de cliente que desea eliminar: ");
        
        
        while (true) {
            String inputData = input.nextLine();
            if (inputData.equals(MedicationControl.cancelOperation)) {
                System.err.println("Se canceló operación.");
                clientShowList();
                break;
            }
            try {
                int parseInputData = Integer.parseInt(inputData);
                Client getClient = daoClient.clientGetById(parseInputData);
                if (getClient.getCli_id() < 1) {
                    System.err.println("No existe, vuelva a intentarlo");
                }else{    
                    if (clientProtectedDelete(getClient.getCli_id())) {
                        System.err.println("No se puede eliminar cliente, este cliente está registrado en ventas.");
                    }else{   
                        if (daoClient.clientDel(getClient.getCli_id())) {
                            System.err.println("Se eliminó correctamente.");
                        } 
                    }           
                    clientShowList();
                    break;
                }  
            } catch (Exception e) {
                System.err.println("Solo se permiten digitos.");
            }
        }
    }
    
    public Boolean clientExist(String dni){
        List<Client> list = null;
        list = daoClient.clientList();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getCli_dni().equals(dni)) {
                return true;
            }
        }
        return false;
    }
    
    public Client clientGetByDni(String dni){
        Client client = new Client();
        List<Client> list = null;
        list = daoClient.clientList();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getCli_dni().equals(dni)) {
                client.setCli_id(list.get(i).getCli_id());
                client.setCli_dni(list.get(i).getCli_dni());
                client.setCli_name(list.get(i).getCli_name());
                client.setCli_surname(list.get(i).getCli_surname());
                client.setCli_bonus_points(list.get(i).getCli_bonus_points());
            }
        }
        return client;
    }
    
    public Boolean clientUpdateBonusPoints(Client client, int soles){
        client.setCli_bonus_points(client.getCli_bonus_points() - (soles * 100));
        daoClient.clientUpd(client);
        return true;
    }
    
    public Boolean clientProtectedDelete(int id){        
        List<Sale> listSale = null;
        listSale = MenuView.saleView.saleList();
        for (int i = 0; i < listSale.size(); i++) {
            if (listSale.get(i).getCli_id()== id) {
                return true;
            }
        }
        return false;
    }
}
