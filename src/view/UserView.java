/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import dao.DaoUser;
import dao.impl.DaoUserImpl;
import dto.Sale;
import dto.User;
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
public class UserView{
    
    private Scanner input = null;
    private DaoUser daoUser = null;
    //private MenuView menuView = null;

    public UserView() {        
        input = new Scanner(System.in);
        daoUser = new DaoUserImpl();
        //menuView = new MenuView();
    }    
    
    public void userShowList() {
        System.out.println("______________________________________________________________________________________");     
        System.out.println("------ ---- *- -* *- "+ MedicationControl.menus[MedicationControl.menu_selected - 1] +" -* *- -* ---- ------");
        System.out.println("");
        List<User> list = null;
        list = daoUser.userList();
        System.out.println("\t[Id]"
                + "\t[DNI]"
                + "\t[Nombres]"
                + "\t[Apellidos]"
                + "\t[Usuario]"
                + "\t[Contraseña]");
        for (int i = 0; i < list.size(); i++) {
            System.out.println("\t"+list.get(i).getUse_id()+
                    "\t| "+list.get(i).getUse_dni()+
                    "\t| "+list.get(i).getUse_name()+
                    "\t| "+list.get(i).getUse_surname()+
                    "\t| "+list.get(i).getUse_user()+
                    "\t| **********");
        }
    }
    
    public void userInsert() {  
        System.out.println("______________________________________________________________________________________"); 
        System.out.println(menus[menu_selected - 1] + " \t>>>>>\t " + sub_menus[menu_selected - 1][0]);
        System.out.println("");
        
        User user = new User();
        user.setUse_id(daoUser.userGenerateId());
        Boolean go = false;
        System.out.println("Ingresar DNI (*):");
        while (true) {
            String inputData = input.nextLine();
            if (inputData.length() == 0) {
                System.err.println("No se permite registrar campo vacío, vuelva a intentarlo.");
            }else if (inputData.length() != 8){
                System.err.println("No se permite registrar DNI (8 caracteres), vuelva a intentarlo.");
            }else if(userExist(inputData)){
                System.err.println("Cliente con DNI: '" + inputData + "' ya esta registrado.");
                break;
            }else{
                user.setUse_dni(inputData);
                go = true;
                break;
            }
        }
        if (go) {
            System.out.println("Ingresar Nombres (*):");
            while (true) {
                String inputData = input.nextLine();
                if (inputData.length() == 0) {
                    System.err.println("No se permite registrar campo vacío, vuelva a intentarlo.");
                }else{
                    user.setUse_name(inputData);
                    break;
                }
            }
            System.out.println("Ingresar Apellidos (*):");
            while (true) {
                String inputData = input.nextLine();
                if (inputData.length() == 0) {
                    System.err.println("No se permite registrar campo vacío, vuelva a intentarlo.");
                }else{
                    user.setUse_surname(inputData);
                    break;
                }
            }
            System.out.println("Ingresar Usuario (*):");
            while (true) {
                String inputData = input.nextLine();
                if (inputData.length() == 0) {
                    System.err.println("No se permite registrar campo vacío, vuelva a intentarlo.");
                }else{
                    user.setUse_user(inputData);
                    break;
                }
            }
            System.out.println("Ingresar Contraseña (*):");
            while (true) {
                String inputData = input.nextLine();
                if (inputData.length() == 0) {
                    System.err.println("No se permite registrar campo vacío, vuelva a intentarlo.");
                }else{
                    user.setUse_password(inputData);
                    break;
                }
            }
            if (daoUser.userIns(user)) {
                System.out.println("Se registró correctamente.");
            }
        } 
        userShowList();
    }
    
    public Boolean userUpdate() {
        System.out.println("______________________________________________________________________________________"); 
        System.out.println(MedicationControl.menus[MedicationControl.menu_selected - 1] + " \t>>>>>\t " + MedicationControl.sub_menus[MedicationControl.menu_selected - 1][1]);
        System.out.println("");
        System.out.println("Ingresar Id de usuario que desea editar: ");
        User getUser = null;
        User user = new User();
        while (true) {
            String inputData = input.nextLine();
            try {
                int parseInputData = Integer.parseInt(inputData);
                getUser = daoUser.userGetById(parseInputData);
                if (getUser.getUse_id() < 1){
                    System.err.println("No existe, vuelva a intentarlo.");
                }else{
                    break;
                }
            } catch (Exception e) {
                System.err.println("Solo se permiten digitos.");
            }
        }

        System.out.println("------ Editar usuario ------");
        user.setUse_id(getUser.getUse_id());
        Boolean go = false;
        System.out.println("Ingresar DNI (*): " + getUser.getUse_dni());
        while (true) {
            String inputData = input.nextLine();
            if(inputData.equals("")){
                user.setUse_dni(getUser.getUse_dni());
                go = true;
                break;
            }else{
                if (inputData.length() != 8){
                    System.err.println("No se permite registrar DNI (8 caracteres), vuelva a intentarlo.");
                }else if(userExist(inputData)){
                    System.err.println("Cliente con DNI: '" + inputData + "' ya esta registrado.");
                    break;
                }else{
                    user.setUse_dni(inputData);  
                    go = true;
                    break;
                }                    
            }
        }
        if (go) {
            System.out.println("Ingresar Nombres (*): " + getUser.getUse_name());
            while (true) {
                String inputData = input.nextLine();
                if(inputData.equals("")){
                    user.setUse_name(getUser.getUse_name());
                    break;
                }else{
                    user.setUse_name(inputData);
                    break;
                }                    
            }
            System.out.println("Ingresar Apellidos (*): " + getUser.getUse_surname());
            while (true) {
                String inputData = input.nextLine();
                if(inputData.equals("")){
                    user.setUse_surname(getUser.getUse_surname());
                    break;
                }else{
                    user.setUse_surname(inputData);
                    break;
                }                    
            }
            System.out.println("Ingresar Usuario (*): " + getUser.getUse_user());
            while (true) {
                String inputData = input.nextLine();
                if(inputData.equals("")){
                    user.setUse_user(getUser.getUse_user());
                    break;
                }else{
                    user.setUse_user(inputData);
                    break;
                }                    
            }
            System.out.println("Ingresar Contraseña (*): ********");
            while (true) {
                String inputData = input.nextLine();
                if(inputData.equals("")){
                    user.setUse_password(getUser.getUse_password());
                    break;
                }else{
                    user.setUse_password(inputData);
                    break;
                }                    
            }
            
            if (daoUser.userUpd(user)) {
                System.err.println("Se modificó correctamente.");
            }   
        }     
        userShowList();   
          
        return true;
        
        
    }

    public void userDelete() {
        System.out.println("______________________________________________________________________________________"); 
        System.out.println(MedicationControl.menus[MedicationControl.menu_selected - 1] + " \t>>>>>\t " + MedicationControl.sub_menus[MedicationControl.menu_selected - 1][2]);
        System.out.println("\t[ " + MedicationControl.cancelOperation +" ] Cancelar");
        System.out.println("");
        System.out.println("Ingresar Id de usuario que desea eliminar: ");
        
        while (true) {
            String inputData = input.nextLine();
            if (inputData.equals(MedicationControl.cancelOperation)) {
                System.err.println("Se canceló operación.");
                userShowList();
                break;
            }
            try {
                int parseInputData = Integer.parseInt(inputData);
                User getUser = daoUser.userGetById(parseInputData);
                if (getUser.getUse_id() < 1) {
                    System.err.println("No existe, vuelva a intentarlo.");
                }else{
                    if (userProtectedDelete(getUser.getUse_id())) {
                        System.err.println("No se puede eliminar usuario, este usuario a registrado ventas.");
                    }else{                        
                        if (daoUser.userDel(getUser.getUse_id())) {
                            System.err.println("Se eliminó correctamente.");
                        }  
                    }
                    userShowList();
                    break;
                }  
            } catch (Exception e) {
                System.err.println("Solo se permiten digitos.");
            }
        }
    }
    
    public Boolean userExist(String dni){
        List<User> list = null;
        list = daoUser.userList();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getUse_dni().equals(dni)) {
                return true;
            }
        }
        return false;
    }
    
    public String userGetFullNameById(int id){
        List<User> list = null;
        list = daoUser.userList();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getUse_id() == id) {
                return list.get(i).getUse_surname().toUpperCase() + ", " + list.get(i).getUse_name();
            }
        }
        return "";
    }
    
    public List<User> userList(){
        return daoUser.userList();
    }
    
    
    public User userGetByDni(String dni){
        User user = new User();
        List<User> list = null;
        list = daoUser.userList();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getUse_dni().equals(dni)) {
                user.setUse_id(list.get(i).getUse_id());
                user.setUse_dni(list.get(i).getUse_dni());
                user.setUse_name(list.get(i).getUse_name());
                user.setUse_surname(list.get(i).getUse_surname());
                user.setUse_user(list.get(i).getUse_user());
                user.setUse_password(list.get(i).getUse_password());
            }
        }
        return user;
    }
    
    
    public User userAuthentication(){        
        System.out.println("");
        String inputUser = "";
        String inputPassword = "";
        
        System.out.println("LOGIN\t--------------------------------------------------");
        System.out.println("Ingrese usuario:");
        while (true) {
            String inputData = input.nextLine();
            if (inputData.length() == 0) {
                System.err.println("No se permite campo vacío, vuelva a intentarlo.");
            }else{
                inputUser = inputData;
                break;
            }
        }
        
        System.out.println("Ingrese contraseña:");
        while (true) {
            String inputData = input.nextLine();
            if (inputData.length() == 0) {
                System.err.println("No se permite campo vacío, vuelva a intentarlo.");
            }else{
                inputPassword = inputData;
                break;
            }
        }
        
        List<User> list = null;
        list = daoUser.userList();
        User user = new User();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getUse_user().equals(inputUser) && list.get(i).getUse_password().equals(inputPassword)) {
                user.setUse_id(list.get(i).getUse_id());
                user.setUse_dni(list.get(i).getUse_dni());
                user.setUse_name(list.get(i).getUse_name());
                user.setUse_surname(list.get(i).getUse_surname());
                user.setUse_user(list.get(i).getUse_user());
                user.setUse_password(list.get(i).getUse_password());
            }
        }
        return user;
    }
    
    public Boolean userProtectedDelete(int id){        
        List<Sale> listSale = null;
        listSale = MenuView.saleView.saleList();
        for (int i = 0; i < listSale.size(); i++) {
            if (listSale.get(i).getUse_id() == id) {
                return true;
            }
        }
        return false;
    }
}
