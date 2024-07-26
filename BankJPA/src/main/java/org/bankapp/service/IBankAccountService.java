package org.bankapp.service;

import org.bankapp.dto.BankAccount;

import java.util.List;

public interface IBankAccountService {
    public void createBankAccount(BankAccount bankAccount);
    public void updateBankAccount(BankAccount bankAccount);
    public void deleteBankAccount(int actNumber);
    public List<BankAccount> getAllBankAccount();
    public BankAccount getBankAccountById(int actNumber);
    public List<BankAccount> getBankAccountByCustomerName(String customerName);
    public List<BankAccount> getAllBankAccountByBranchCode(String branchCode);
    public List<Object[]> getAllBankAccountGroupByCity();
}
