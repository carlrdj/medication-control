/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import dao.DaoSale;
import dao.DaoSaleDetail;
import dao.impl.DaoSaleDetailImpl;
import dao.impl.DaoSaleImpl;
import dto.Client;
import dto.Medication;
import dto.Sale;
import dto.SaleDetail;
import java.sql.Date;
import java.text.DecimalFormat;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import medication.control.MedicationControl;
import static medication.control.MedicationControl.igv;

/**
 *
 * @author SEVEN
 */
public class SaleView {
    
    private Scanner input = null;
    private DaoSale daoSale = null;
    private DaoSaleDetail daoSaleDetail = null;
    DecimalFormat decimalFormat = new DecimalFormat("0.00");
    

    public SaleView() {        
        input = new Scanner(System.in);
        daoSale = new DaoSaleImpl();
        daoSaleDetail = new DaoSaleDetailImpl();
    }
    
    public void saleNew() {
        System.out.println("______________________________________________________________________________________"); 
        System.out.println("------ ---- *- -* *- "+ MedicationControl.menus[MedicationControl.menu_selected - 1] +" -* *- -* ---- ------");
        System.out.println("\t[ " + MedicationControl.cancelOperation +" ] Cancelar\t[ ENTER ] Continuar");
        System.out.println("");
        while (true) {
            String inputData = input.nextLine();
            if (inputData.equals(MedicationControl.cancelOperation)) {
                System.err.println("Se canceló operación.");
                break;
            }else{
                verifyClient();
                break;
            }            
        }
    }
    
    public void verifyClient() {
        System.out.println("Ingresar DNI (*):");
        while (true) {
            String inputData = input.nextLine();
            if (inputData.equals(MedicationControl.cancelOperation)) {
                System.err.println("Se canceló operación.");
                break;
            }
            if(MenuView.clientView.clientExist(inputData)){
                Client getClient = MenuView.clientView.clientGetByDni(inputData);
                saleInsert(getClient);
                break;
            }else{
                System.err.println("Cliente no existe. Vuelva a intentarlo.\t[ " + MedicationControl.cancelOperation +" ] Cancelar");
            }
        }
    }
    
    public Boolean saleInsert(Client client) {
        System.out.println("");
        System.out.println("\tCliente: " + client.getCli_surname().toUpperCase()+ ", " + client.getCli_name());
        System.out.println("");
        
        Sale sale = new Sale();
        sale.setSal_id(daoSale.saleGenerateId());
        sale.setCli_id(client.getCli_id());
        sale.setUse_id(MedicationControl.user_id);
        
        
        List<SaleDetail> list = new LinkedList<>();
        int sal_det_id = daoSaleDetail.saleDetailGenerateId();
        while (true) {
            if (list.size() > 0) {                
                showPartialSaleDetail(list);
            }
            System.out.println("Buscar medicamento: \t[ t ] Terminar");
            String inputData = input.nextLine();
            if (inputData.equals("t")) {                
                break;
            }
            MenuView.medicationView.medicationSearch(inputData);
            System.out.println("");
            System.out.println("[ s ] Volver a buscar\t[ t ] Terminar\t[ ENTER ] Seleccionar medicamento");
            String inputResponseFistQuestion = input.nextLine();
            if (inputResponseFistQuestion.equals("s")) {
                System.out.println("Volver a buscar:");
            }else if(inputResponseFistQuestion.equals("t")){
                break;
            }else {
                System.out.println("Ingresar Id de medicamento que desea comprar:\t[ " + MedicationControl.cancelOperation +" ] Cancelar");
                while (true) {
                    SaleDetail saleDetail = new SaleDetail();
                    String inputDataMedication = input.nextLine();
                    if (inputDataMedication.equals("")) {
                       System.out.println("Ingrese Id de medicamento que desea comprar:\t[ " + MedicationControl.cancelOperation +" ] Cancelar");
                    }else{
                        if (inputDataMedication.equals(MedicationControl.cancelOperation)) {
                            System.err.println("Se canceló operación.");
                            System.out.println("");
                            System.out.println("Buscar medicamento (*):");
                            break;
                        }
                        try {
                            int parseInputDataMedication = Integer.parseInt(inputDataMedication);                                
                            if (MenuView.medicationView.medicationVerifySelectSearch(parseInputDataMedication, inputData)) {
                                saleDetail.setSal_det_id(sal_det_id);
                                saleDetail.setSal_id(daoSale.saleGenerateId());
                                saleDetail.setMed_id(parseInputDataMedication);
                                
                                System.out.println("");
                                System.out.println("\tMedicamento: " + MenuView.medicationView.medicationGetNameById(parseInputDataMedication));
                                System.out.println("");
                                
                                sal_det_id++;
                                System.out.println("Ingrese Cantidad que desea comprar:\t[ " + MedicationControl.cancelOperation +" ] Cancelar");
                                while (true) {                                        
                                    String inputDataQuantity = input.nextLine();
                                    if (inputDataMedication.equals("")) {
                                        System.out.println("Ingrese Cantidad de medicamento que desea comprar:\t[ " + MedicationControl.cancelOperation +" ] Cancelar");
                                    }else{
                                        if (inputDataQuantity.equals(MedicationControl.cancelOperation)) {                                            
                                            System.err.println("Se canceló operación.");
                                            break;
                                        }else{
                                            try {
                                                int parseInputDataQuantity = Integer.parseInt(inputDataQuantity);
                                                if (MenuView.medicationView.medicationVerifyQuantityById(parseInputDataMedication, parseInputDataQuantity)) {
                                                    saleDetail.setSal_det_quantity(parseInputDataQuantity);
                                                    list.add(saleDetail);//////
                                                    break;
                                                }else{
                                                    System.err.println("No hay stock suficiente. Vuela a intentarlo \t[ " + MedicationControl.cancelOperation +" ] Cancelar");
                                                }
                                            } catch (Exception e) {
                                                System.err.println("Solo se permiten digitos.");
                                            }
                                        }
                                    }
                                }
                                break;
                            }else{
                                System.err.println("Producto caducado o no esta en los resultados de la busqueda, vuelva a intentarlo\t[ " + MedicationControl.cancelOperation +" ] Cancelar");
                            }
                        } catch (Exception e) {
                            System.err.println("Solo se permiten digitos.");
                        }
                    }
                }
                    
                if (sal_det_id == daoSaleDetail.saleDetailGenerateId()) {
                    System.out.println("No medicamentos pedidos");
                    //break;
                }
            }
            updateList(list);
        }
        if (list.size() > 0) {
            Date dateCurrently = new Date(MenuView.medicationView.getDate().getTime());
            showPartialSaleDetail(list);
            System.out.println("\t\t\t\t\tSub total:\tS/. " + decimalFormat.format(calculateTotal(list)));
            System.out.println("\t\t\t\t\tIGV:\t\tS/. " + decimalFormat.format(calculateTotal(list) * igv));
            System.out.println("\t\t\t\t\t\t\t---------");
            System.out.println("\t\t\t\t\tTotal:\t\tS/. " + decimalFormat.format(calculateTotal(list) + (calculateTotal(list) * igv)));
            System.out.println("");
            System.out.println("\tPuntos bono: " + client.getCli_bonus_points());
            
            int soles = client.getCli_bonus_points() / 100;
            int discount = 0;
            if (soles > 0) {
                System.out.println("\tSoles que puede canjear: S/. " + soles);
                System.out.println("");
                System.out.println("¿Desea utilizar sus puntos bonus? \t[ s ] Si\t[ n ] No");
                while (true) {
                    String inputDataQuantity = input.nextLine();
                    if (inputDataQuantity.equals("s")) {
                        if (soles > (calculateTotal(list) + (calculateTotal(list) * igv))) {
                            soles = (int) Math.floor(calculateTotal(list) + (calculateTotal(list) * igv));
                            sale.setSal_discount((double) soles);
                            sale.setSal_total((calculateTotal(list) + (calculateTotal(list) * igv)) - soles);
                            sale.setSal_date(dateCurrently);
                            discount = soles;
                            break;
                        }else{
                            sale.setSal_discount((double) soles);
                            sale.setSal_total((calculateTotal(list) + (calculateTotal(list) * igv)) - soles);
                            sale.setSal_date(dateCurrently);
                            discount = soles;
                            break;                            
                        }
                    }else if (inputDataQuantity.equals("n")){                        
                        sale.setSal_discount(0.00);
                        sale.setSal_total(calculateTotal(list) + (calculateTotal(list) * igv));   
                        sale.setSal_date(dateCurrently);
                        break;               
                    }
                }
            }else{
                sale.setSal_discount(0.00);
                sale.setSal_total(calculateTotal(list));
                sale.setSal_total(calculateTotal(list) + (calculateTotal(list) * igv));   
                sale.setSal_date(dateCurrently);
            }
            
            System.out.println("");
            System.out.println("\tBoleta N°: " + daoSale.saleGenerateId());
            System.out.println("");
            System.out.println("\tAtendido(a) por: " + MenuView.userView.userGetFullNameById(MedicationControl.user_id));
            System.out.println("\tCliente: " + client.getCli_surname().toUpperCase() + ", " + client.getCli_name());
            showTicket(list);
            System.out.println("\t\t\t\t\tSub total:\tS/. " + decimalFormat.format(calculateTotal(list)));
            System.out.println("\t\t\t\t\tIGV:\t\tS/. " + decimalFormat.format(calculateTotal(list) * igv));
            if (discount>0) {
                System.out.println("\t\t\t\t\tDescuento:\tS/. " + decimalFormat.format(discount));
            }
            System.out.println("\t\t\t\t\t\t\t---------");
            if (discount>0) {
                System.out.println("\t\t\t\t\tTotal:\t\tS/. " + decimalFormat.format((calculateTotal(list) + (calculateTotal(list) * igv))- discount)); 
            }else{
                System.out.println("\t\t\t\t\tTotal:\t\tS/. " + decimalFormat.format(calculateTotal(list) + (calculateTotal(list) * igv))); 
            }
            
            System.out.println("");
            System.out.println("Se completo compra con éxito.");
            System.out.println("");
            System.out.println("\t[ ENTER ] Finalizado");
            input.nextLine();
            daoSale.saleIns(sale);
            daoSaleDetail.saleDetailIns(list);
            MenuView.medicationView.medicationUpdateQuantity(list);
            MenuView.clientView.clientUpdateBonusPoints(client, soles);
            return true;
        }else{
            return false;
        }
    }
    
    public List<SaleDetail> updateList(List<SaleDetail> list){
        List<SaleDetail> newList = list;
        for (int i = 0; i < list.size(); i++) {
            for (int j = 0; j < list.size(); j++) {
                if (list.get(i).getMed_id() == list.get(j).getMed_id() && i != j){
                    newList.remove(list.get(i));
                }
            }
        }
        return newList;
    }
    
    public void showPartialSaleDetail(List<SaleDetail> list){
        for (int i = 0; i < list.size(); i++) {
            list.get(i).getMed_id();
        }
        System.out.println("");
        System.out.println("\tCarrito de compras\t------------------------------------------------------------"); 
        System.out.println("");
        System.out.println("\t[Medicamento]"
                + "\t[Cantidad]"
                + "\t[S/. Precio unitario]"
                + "\t[S/. Precio por medicamento]");
        Boolean result = true;
        for (int i = 0; i < list.size(); i++) {
            System.out.println("\t" + MenuView.medicationView.medicationGetNameById(list.get(i).getMed_id()) +
                    "\t| "+ list.get(i).getSal_det_quantity() +
                    "\t\t| "+ MenuView.medicationView.medicationGetUnitPriceById(list.get(i).getMed_id()) +
                    "\t\t\t| "+ decimalFormat.format(MenuView.medicationView.medicationGetSubTotalByIdAndQuantity(list.get(i).getMed_id(), list.get(i).getSal_det_quantity())));
                result = false;
        }
        System.out.println("");
                                
    }
    
    public Double calculateTotal(List<SaleDetail> list){
        Double total = 0.0;
        for (int i = 0; i < list.size(); i++) {
            total+=MenuView.medicationView.medicationGetSubTotalByIdAndQuantity(list.get(i).getMed_id(), list.get(i).getSal_det_quantity());
        }
        return total;                        
    }
        
    public void showTicket(List<SaleDetail> list){                                            
        System.out.println("\t[Medicamento]"
                + "\t[Cantidad]"
                + "\t[S/. Precio unitario]"
                + "\t[S/. Precio por medicamento]");
        Boolean result = true;
        for (int i = 0; i < list.size(); i++) {
            System.out.println("\t" + MenuView.medicationView.medicationGetNameById(list.get(i).getMed_id()) +
                    "\t| "+ list.get(i).getSal_det_quantity() +
                    "\t\t| "+ MenuView.medicationView.medicationGetUnitPriceById(list.get(i).getMed_id()) +
                    "\t\t\t| "+ decimalFormat.format(MenuView.medicationView.medicationGetSubTotalByIdAndQuantity(list.get(i).getMed_id(), list.get(i).getSal_det_quantity())));
                result = false;
        }                                
    }
    
    /*
    public Double saleAmountCollected(int use_id, int med_id, Date date){
        List<Sale> listSale = daoSale.saleList();
        Double amountCollected = 0.0;
        for (int i = 0; i < listSale.size(); i++) {
            
            //System.err.println(" " + listSale.get(i).getUse_id() + " " + use_id + " / " + listSale.get(i).getSal_date() + " " + date);
            if (listSale.get(i).getUse_id() == use_id && listSale.get(i).getSal_date().equals(date)) {
                List<SaleDetail> listSaleDetail = daoSaleDetail.saleDetailList();
                //System.err.println(" " + listSale.get(i).getSal_date() + " //// " + date);
                for (int j = 0; j < listSaleDetail.size(); j++) {
                    if (listSaleDetail.get(j).getMed_id() == med_id) {                        
                        amountCollected += listSaleDetail.get(j).getSal_det_quantity() * MenuView.medicationView.medicationGetUnitPriceById(med_id) * igv;
                    }
                }
                
            }
        }
        return amountCollected;
    }
    */
    
    public void saleAmountCollected(int use_id, int med_id, String med_name, String date){
        List<Sale> listSale = daoSale.saleList();
        
        for (int i = 0; i < listSale.size(); i++) {
            List<SaleDetail> listSaleDetail = daoSaleDetail.saleDetailList();
            Double amountCollected = 0.0;
            for (int j = 0; j < listSaleDetail.size(); j++) {
                if (listSale.get(i).getSal_date().toString().equals(date) && 
                        listSale.get(i).getUse_id() == use_id &&
                        listSaleDetail.get(j).getMed_id() == med_id &&
                        listSaleDetail.get(j).getSal_id() == listSale.get(i).getSal_id()) {
                    //Entre en la fecha indicada
                    amountCollected += (listSaleDetail.get(j).getSal_det_quantity() * MenuView.medicationView.medicationGetUnitPriceById(med_id)) + (igv * listSaleDetail.get(j).getSal_det_quantity() * MenuView.medicationView.medicationGetUnitPriceById(med_id));
                }
            }
            if (amountCollected > 0.0) {
                System.out.println("\t" + med_name + "\t" + decimalFormat.format(amountCollected));
            }
        }
    }
    
    public List<Sale> saleList(){
        return daoSale.saleList();
    }
    
    public List<SaleDetail> saleDetailList(){
        return daoSaleDetail.saleDetailList();
    }
    
    public List<SaleDetail> saleDetailListBySalId(int sal_id){
        List<SaleDetail> list = daoSaleDetail.saleDetailList();
        List<SaleDetail> newlist = new LinkedList<>();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getSal_id() == sal_id) {
                newlist.add(list.get(i));
            }
        }
        return newlist;
    }
    
}
