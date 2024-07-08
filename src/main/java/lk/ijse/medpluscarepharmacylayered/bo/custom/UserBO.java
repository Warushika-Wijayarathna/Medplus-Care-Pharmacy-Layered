package lk.ijse.medpluscarepharmacylayered.bo.custom;

import lk.ijse.medpluscarepharmacylayered.bo.SuperBO;
import lk.ijse.medpluscarepharmacylayered.dto.UserDTO;

import java.sql.SQLException;
import java.util.List;

public interface UserBO extends SuperBO {

    List<UserDTO> getAllUsers() throws SQLException, ClassNotFoundException;

    void saveUser(UserDTO userDTO) throws SQLException, ClassNotFoundException;

    String getIdByUsername(String username);

    void updateUser(UserDTO user) throws SQLException, ClassNotFoundException;
}
