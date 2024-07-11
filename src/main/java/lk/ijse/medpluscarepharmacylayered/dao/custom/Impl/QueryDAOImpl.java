package lk.ijse.medpluscarepharmacylayered.dao.custom.Impl;

import lk.ijse.medpluscarepharmacylayered.dao.SQLUtil;
import lk.ijse.medpluscarepharmacylayered.dao.custom.QueryDAO;
import lk.ijse.medpluscarepharmacylayered.entity.Item;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

public class QueryDAOImpl implements QueryDAO {
    @Override
    public List<Item> getAll() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute(null, "SELECT test_id AS id, description, price, discount FROM Test " +
                "UNION ALL " +
                "SELECT item_id AS id, description, retail_price AS price, discount FROM Item");

        List<Item> itemList = new ArrayList<>();
        while (resultSet.next()) {
            String id = resultSet.getString("id");
            String desc = resultSet.getString("description");
            double price = resultSet.getDouble("price");
            double discount = resultSet.getDouble("discount");

            Item item = new Item(id, desc, price, discount);
            itemList.add(item);
        }
        return itemList;
    }

}
