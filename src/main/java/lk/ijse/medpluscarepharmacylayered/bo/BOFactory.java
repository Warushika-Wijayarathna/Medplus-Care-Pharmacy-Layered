package lk.ijse.medpluscarepharmacylayered.bo;

import lk.ijse.medpluscarepharmacylayered.bo.custom.impl.*;

public class BOFactory {
    private static BOFactory boFactory;
    private BOFactory(){
    }
    public static BOFactory getBoFactory(){
        return (boFactory==null)? boFactory=new BOFactory() : boFactory;
    }

    public enum BOTypes{
        CUSTOMER,ITEM,EMPLOYEE,PRESCRIPTION,ORDER,ORDER_ITEM_DETAIL,USER,ITEM_SUPPLIER_DETAIL,ORDER_TEST_DETAIL,PAYMENT,PRES_TEST_DETAIL,REPORT,SUPPLIER,QUERY,TEST
    }

    public SuperBO getBO(BOTypes boTypes){
        switch (boTypes){
            case CUSTOMER:
                return new CustomerBOImpl();
            case ITEM:
                return new ItemBOImpl();
            case EMPLOYEE:
                return new EmployeeBOImpl();
            case PRESCRIPTION:
                return new PrescriptionBOImpl();
            case ORDER:
                return new OrderBOImpl();
            case ORDER_ITEM_DETAIL:
                return new OrderItemDetailBOImpl();
            case USER:
                return new UserBOImpl();
            case ITEM_SUPPLIER_DETAIL:
                return new ItemSupplierDetailBOImpl();
            case ORDER_TEST_DETAIL:
                return new OrderTestDetailBOImpl();
            case PRES_TEST_DETAIL:
                return new PrescriptionTestDetailBOImpl();
            case REPORT:
                return new ReportBOImpl();
            case SUPPLIER:
                return new SupplierBOImpl();
            case QUERY:
                return new QueryBOImpl();
            case TEST:
                return new TestBOImpl();
            default:
                return null;
        }
    }
}
