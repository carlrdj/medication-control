/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.impl;

import dao.DaoSaleDetail;
import dto.SaleDetail;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author SEVEN
 */
public class DaoSaleDetailImpl implements DaoSaleDetail{
    List<SaleDetail> list = null;

    public DaoSaleDetailImpl() {
        list = new LinkedList<>();
        
        SaleDetail saleDetail = new SaleDetail(1, 1, 1, 5);
        SaleDetail saleDetai2 = new SaleDetail(2, 1, 3, 4);
        SaleDetail saleDetai3 = new SaleDetail(3, 2, 1, 5);
        SaleDetail saleDetai4 = new SaleDetail(4, 3, 1, 2);
        SaleDetail saleDetai5 = new SaleDetail(5, 3, 3, 5);
        list.add(saleDetail);
        list.add(saleDetai2);
        list.add(saleDetai3);
        list.add(saleDetai4);
        list.add(saleDetai5);
    }
    
    @Override
    public String message() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int saleDetailGenerateId() {
        int mayor = 0;
        for (int i = 0; i < list.size(); i++) {
            if (mayor < list.get(i).getSal_det_id()) {
                mayor = list.get(i).getSal_det_id();           
            }
        }
        return mayor + 1;
    }

    @Override
    public Boolean saleDetailIns(List<SaleDetail> listSaleDetail) {
        for (int i = 0; i < listSaleDetail.size(); i++) {
            list.add(listSaleDetail.get(i));
        }
        return true;
    }

    @Override
    public List<SaleDetail> saleDetailList() {
        return list;
    }

    
}
