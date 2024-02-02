package Service;

import Model.Account;
import DAO.AccountDAO;

public class AccountService {
    private AccountDAO accountDAO;

    public AccountService() 
    {
        accountDAO = new AccountDAO();
    }

    public Account newAccount(Account newAcc)
    {
        return accountDAO.newAccount(newAcc);
    }

    public Account verifyAccount(Account verAcc)
    {
        return accountDAO.verifyAccount(verAcc);
    }
    
}
