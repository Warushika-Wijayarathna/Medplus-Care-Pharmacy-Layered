package lk.ijse.medpluscarepharmacylayered.bo.custom.impl;

import lk.ijse.medpluscarepharmacylayered.bo.SuperBO;
import lk.ijse.medpluscarepharmacylayered.bo.custom.UserBO;
import lk.ijse.medpluscarepharmacylayered.dao.DAOFactory;
import lk.ijse.medpluscarepharmacylayered.dao.custom.QueryDAO;
import lk.ijse.medpluscarepharmacylayered.dao.custom.UserDAO;

import java.sql.SQLException;

public class UserBOImpl implements UserBO {
    QueryDAO queryDAO = (QueryDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.QUERY);
   @Override
    public boolean checkUser(String userName,String password) throws SQLException, ClassNotFoundException {
       System.out.println();
       return queryDAO.auth(userName,password);
   }
}
