package Service;

import Model.Account;
import DAO.AccountDAO;

public class AccountService {
    private AccountDAO accountDAO;

    public AccountService() 
    {
        accountDAO = new AccountDAO();
    }
    
}
