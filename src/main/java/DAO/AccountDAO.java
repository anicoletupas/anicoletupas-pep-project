package DAO;

import Model.Account;
import Util.ConnectionUtil;

import java.util.*;
import java.sql.*;

public class AccountDAO {

    public Account newAccount(Account newAcc)
    {
        Connection connection = ConnectionUtil.getConnection();
        try {
            String sql = "INSERT INTO account (username, password) VALUES (?, ?) ";
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setString(1, newAcc.getUsername());
            preparedStatement.setString(2, newAcc.getPassword());

            preparedStatement.executeUpdate();
            ResultSet pkeyResultSet = preparedStatement.getGeneratedKeys();
            if(pkeyResultSet.next()){
                int generated_account_id = (int) pkeyResultSet.getLong(1);
                return new Account(generated_account_id, newAcc.getUsername(), newAcc.getPassword());
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public Account verifyAccount(Account account)
    {
        Connection connection = ConnectionUtil.getConnection();
        try {
            String sql = "SELECT * FROM account WHERE username = ? AND password = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, account.getUsername());
            preparedStatement.setString(2, account.getPassword());

            ResultSet pkeyResultSet = preparedStatement.executeQuery();
            if(pkeyResultSet.next()){
                Account verifiedAcc = new Account(pkeyResultSet.getInt("account_id"), account.getUsername(), account.getPassword());
                return verifiedAcc;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
    
}
