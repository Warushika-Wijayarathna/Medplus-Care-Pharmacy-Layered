package lk.ijse.medpluscarepharmacylayered.bo.custom.impl;

import lk.ijse.medpluscarepharmacylayered.bo.custom.QueryBO;
import lk.ijse.medpluscarepharmacylayered.dao.DAOFactory;
import lk.ijse.medpluscarepharmacylayered.dao.custom.QueryDAO;

import java.sql.SQLException;

public class QueryBOImpl implements QueryBO {
    QueryDAO queryDAO = (QueryDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.QUERY);
    @Override
    public boolean checkUser(String username, String password) throws SQLException, ClassNotFoundException {
        return queryDAO.auth(username, password);
    }
}
