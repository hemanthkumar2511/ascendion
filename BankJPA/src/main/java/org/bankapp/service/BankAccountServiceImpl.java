package org.bankapp.service;

import org.bankapp.dao.BankAccountDaoImpl;
import org.bankapp.dao.IBankAccountDao;
import org.bankapp.dto.BankAccount;

import java.util.List;

public class BankAccountServiceImpl implements IBankAccountService{
    IBankAccountDao bankAccountDao = new BankAccountDaoImpl();
    @Override
    public void createBankAccount(BankAccount bankAccount) {
        bankAccountDao.createBankAccount(bankAccount);
    }

    @Override
    public void updateBankAccount(BankAccount bankAccount) {
        bankAccountDao.updateBankAccount(bankAccount);
    }

    @Override
    public void deleteBankAccount(int actNumber) {
        bankAccountDao.deleteBankAccount(actNumber);
    }

    @Override
    public List<BankAccount> getAllBankAccount() {
        return bankAccountDao.getAllBankAccount();
    }

    @Override
    public List<Object[]> getAllBankAccountGroupByCity() {
        return bankAccountDao.getAllBankAccountGroupByCity();
    }

    @Override
    public List<BankAccount> getAllBankAccountByBranchCode(String branchCode) {
        return bankAccountDao.getAllBankAccountByBranchCode(branchCode);
    }

    @Override
    public BankAccount getBankAccountById(int actNumber) {
        return bankAccountDao.getBankAccountById(actNumber);
    }

    @Override
    public List<BankAccount> getBankAccountByCustomerName(String customerName) {
        return bankAccountDao.getBankAccountByCustomerName(customerName);
    }
}
