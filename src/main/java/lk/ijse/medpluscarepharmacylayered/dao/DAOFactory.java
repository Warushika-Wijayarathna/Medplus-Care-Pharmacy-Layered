package lk.ijse.medpluscarepharmacylayered.dao;

import lk.ijse.medpluscarepharmacylayered.dao.custom.Impl.*;

public class DAOFactory {
    private static DAOFactory daoFactory;

    private DAOFactory(){}

    public static DAOFactory getDAOFactory(){
        return(daoFactory==null)? daoFactory = new DAOFactory() : daoFactory;
    }

    public enum DAOTypes{
        CUSTOMER,
        ITEM,
        EMPLOYEE,
        ITEM_SUPPLIER_DETAIL,
        ORDER,
        ORDER_ITEM_DETAIL,
        ORDER_TEST_DETAIL,
        PRESC_TEST_DETAIL,
        PRESCRIPTION,
        REPORT,
        SUPPLIER,
        TEMPERATURE,
        TEST,
        USER
    }

    public SuperDAO getDAO(DAOTypes types){
        switch (types){
            case CUSTOMER:
                return new CustomerDAOImpl();
            case ITEM:
                return new ItemDAOImpl();
            case EMPLOYEE:
                return new EmployeeDAOImpl();
            case ITEM_SUPPLIER_DETAIL:
                return new ItemSupplierDetailDAOImpl();
            case ORDER:
                return new OrderDAOImpl();
            case ORDER_ITEM_DETAIL:
                return new OrderItemDetailDAOImpl();
            case ORDER_TEST_DETAIL:
                return new OrderTestDetailDAOImpl();
            case PRESC_TEST_DETAIL:
                return new PrescTestDetailDAOImpl();
            case PRESCRIPTION:
                return new PrescriptionDAOImpl();
            case REPORT:
                return new ReportDAOImpl();
            case SUPPLIER:
                return new SupplierDAOImpl();
            case TEMPERATURE:
                return new TemperatureDAOImpl();
            case TEST:
                return new TestDAOImpl();
            case USER:
                return new UserDAOImpl();
            default:
                return null;
        }
    }
}
