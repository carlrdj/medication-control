/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.impl;

import dao.DaoClient;
import dto.Client;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author SEVEN
 */
public class DaoClientImpl implements DaoClient{
    List<Client> list = null;

    public DaoClientImpl() {
        list = new LinkedList<>();
        Client client1 = new Client(1, "47801273", "Rueda", "Carlos", 96500);
        Client client2 = new Client(2, "01234567", "Rojas", "Cesar", 1566);
        
        list.add(client1);
        list.add(client2);
    }
    
    @Override
    public String message() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int clientGenerateId() {         
        int mayor = 0;
        for (int i = 0; i < list.size(); i++) {
            if (mayor < list.get(i).getCli_id()) {
                mayor = list.get(i).getCli_id();           
            }
        }
        return mayor + 1;
    }

    @Override
    public Boolean clientIns(Client client) {        
        list.add(client);
        return true;
    }

    @Override
    public Client clientGetById(Integer id) {
        Client client = new Client();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getCli_id()== id) {
                client.setCli_id(list.get(i).getCli_id());
                client.setCli_dni(list.get(i).getCli_dni());
                client.setCli_surname(list.get(i).getCli_surname());
                client.setCli_name(list.get(i).getCli_name());
                client.setCli_bonus_points(list.get(i).getCli_bonus_points());    
            }
        }
        return client;
    }

    @Override
    public Boolean clientUpd(Client client) {
        list.set(indexList(client.getCli_id()), client);
        return true;
    }

    @Override
    public Boolean clientDel(Integer id) {
        list.remove(indexList(id));
        return true;
    }

    @Override
    public List<Client> clientList() {
        return list;
    }

    @Override
    public int indexList(Integer id) {
        int index = 0;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getCli_id()== id) {
                index = i;
                break;
            }
        }
        return index;
    }
    
}
