/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.SaleDetail;
import java.util.List;

/**
 *
 * @author SEVEN
 */
public interface DaoSaleDetail {
    //Message
    public String message();
    //Gemerate id
    public int saleDetailGenerateId();
    //Insert sale detail
    public Boolean saleDetailIns(List<SaleDetail> list);
    //List sale detail
    public List<SaleDetail> saleDetailList();
}
