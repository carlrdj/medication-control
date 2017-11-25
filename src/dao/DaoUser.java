/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import dto.User;

/**
 *
 * @author SEVEN
 */
public interface DaoUser {
    //Message
    public String message();
    //Gemerate id
    public int userGenerateId();
    //Insert user
    public Boolean userIns(User user);
    //Get user
    public User userGetById(Integer id);
    //Update user
    public Boolean userUpd(User user);
    //Remove user
    public Boolean userDel(Integer id);
    //List user
    public List<User> userList();
    //index
    public int indexList(Integer id);
}
