package lk.ijse.medpluscarepharmacylayered.dao;

import lk.ijse.medpluscarepharmacylayered.db.DbConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SQLUtil {
    public static <T>T execute(String sql, Object... args) throws SQLException, ClassNotFoundException {
        Connection connection = DbConnection.getDbConnection().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        for (int i = 0; i < args.length; i++) {
            pstm.setObject((i+1),args[i]);
        }
        if (sql.startsWith("SELECT")){
            return (T) pstm.executeQuery();
        }else{
            return (T) (Boolean) (pstm.executeUpdate()>0);
        }
    }

    public static <T>T execute(Connection connection, String sql, Object... parameters) throws SQLException {
        PreparedStatement stmt = connection.prepareStatement(sql);
        for (int i = 0; i < parameters.length; i++) {
            stmt.setObject(i + 1, parameters[i]);
        }
        if (sql.startsWith("SELECT")){
            return (T) stmt.executeQuery();
        }else{
            return (T) (Boolean) (stmt.executeUpdate()>0);
        }
    }
}
