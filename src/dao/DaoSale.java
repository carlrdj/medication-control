/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.Sale;
import java.util.List;

/**
 *
 * @author SEVEN
 */
public interface DaoSale {
    //Message
    public String message();
    //Gemerate id
    public int saleGenerateId();
    //Insert sale
    public Boolean saleIns(Sale sale);
    //List sale
    public List<Sale> saleList();
}
