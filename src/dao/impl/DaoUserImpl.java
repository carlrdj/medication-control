/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.impl;

import dao.DaoUser;
import dto.User;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author SEVEN
 */
public class DaoUserImpl implements DaoUser{
    List<User> list = null;
   

    public DaoUserImpl() {
        list = new LinkedList<>();
        User user1 = new User(1, "47801273", "Rueda", "Carlos", "carl", "carl");
        User user2 = new User(2, "01234567", "Rojas", "Cesar", "zero", "zero");
        User user3 = new User(3, "69696969", "Sayagin", "Goku", "SUPERUSUARIO", "SUPERUSUARIO");
        list.add(user1);
        list.add(user2);
        list.add(user3);        
    }
    
    @Override
    public String message() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int userGenerateId() {
        int mayor = 0;
        for (int i = 0; i < list.size(); i++) {
            if (mayor < list.get(i).getUse_id()) {
                mayor = list.get(i).getUse_id();           
            }
        }
        return mayor + 1;
    }

    @Override
    public Boolean userIns(User user) {        
        list.add(user);
        return true;
    }

    @Override
    public User userGetById(Integer id) {
        User user = new User();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getUse_id() == id) {
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

    @Override
    public Boolean userUpd(User user) {
        list.set(indexList(user.getUse_id()), user);
        return true;
    }

    @Override
    public Boolean userDel(Integer id) {
        list.remove(indexList(id));
        return true;
    }

    @Override
    public List<User> userList() {
        return list;
    }

    @Override
    public int indexList(Integer id) {
        int index = 0;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getUse_id() == id) {
                index = i;
                break;
            }
        }
        return index;
    }
    
}
