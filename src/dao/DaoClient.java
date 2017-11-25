/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.Client;
import java.util.List;

/**
 *
 * @author SEVEN
 */
public interface DaoClient {    
    //Message
    public String message();
    //Gemerate id
    public int clientGenerateId();
    //Insert client
    public Boolean clientIns(Client client);
    //Get client
    public Client clientGetById(Integer id);
    //Update client
    public Boolean clientUpd(Client client);
    //Remove client
    public Boolean clientDel(Integer id);
    //List client
    public List<Client> clientList();
    //index
    public int indexList(Integer id);
}
