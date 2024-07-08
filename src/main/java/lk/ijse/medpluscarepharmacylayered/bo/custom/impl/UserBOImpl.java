package lk.ijse.medpluscarepharmacylayered.bo.custom.impl;

import lk.ijse.medpluscarepharmacylayered.bo.SuperBO;
import lk.ijse.medpluscarepharmacylayered.bo.custom.UserBO;
import lk.ijse.medpluscarepharmacylayered.dao.DAOFactory;
import lk.ijse.medpluscarepharmacylayered.dao.custom.QueryDAO;
import lk.ijse.medpluscarepharmacylayered.dao.custom.UserDAO;
import lk.ijse.medpluscarepharmacylayered.dto.UserDTO;
import lk.ijse.medpluscarepharmacylayered.entity.User;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserBOImpl implements UserBO {
    UserDAO userDAO = (UserDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.USER);

    @Override
    public List<UserDTO> getAllUsers() throws SQLException, ClassNotFoundException {
        List<UserDTO> allUsers = new ArrayList<>();
        List<User> users = userDAO.getAll();
        for (User user : users) {
            allUsers.add(new UserDTO(user.getUserId(), user.getUserName(), user.getPassword()));
        }
        return allUsers;
    }

    @Override
    public void saveUser(UserDTO userDTO) throws SQLException, ClassNotFoundException {
        userDAO.add(new User(userDTO.getUserName(), userDTO.getPassword()));
    }

    @Override
    public String getIdByUsername(String username) {
        return null;
    }

    @Override
    public void updateUser(UserDTO user) throws SQLException, ClassNotFoundException {
        userDAO.update(new User(user.getUserId(), user.getUserName(), user.getPassword()));
    }
}
