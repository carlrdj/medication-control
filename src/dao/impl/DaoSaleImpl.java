/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.impl;

import dao.DaoSale;
import dto.Sale;
import java.sql.Date;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author SEVEN
 */
public class DaoSaleImpl implements DaoSale{
    List<Sale> list = null;

    public DaoSaleImpl() {
        list = new LinkedList<>();
        String str1="2017-10-24";  
        Date date1=Date.valueOf(str1);
        String str2="2017-11-24";  
        Date date2=Date.valueOf(str2);
        String str3="2017-11-24";  
        Date date3=Date.valueOf(str3);
        Sale sale1 = new Sale(1, 1, 1, 0.0, 20.0, date1);
        Sale sale2 = new Sale(2, 1, 2, 0.0, 23.5, date2);
        Sale sale3 = new Sale(3, 2, 2, 0.0, 23.5, date3);
        list.add(sale1);
        list.add(sale2);
        list.add(sale3);
    }
    
    @Override
    public String message() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int saleGenerateId() {
        int mayor = 0;
        for (int i = 0; i < list.size(); i++) {
            if (mayor < list.get(i).getSal_id()) {
                mayor = list.get(i).getSal_id();           
            }
        }
        return mayor + 1;
    }

    @Override
    public Boolean saleIns(Sale sale) {
        list.add(sale);
        return true;
    }

    @Override
    public List<Sale> saleList() {
        return list;
    }
    
}
