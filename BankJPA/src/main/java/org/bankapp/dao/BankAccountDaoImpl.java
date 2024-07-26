package org.bankapp.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import org.bankapp.dto.BankAccount;
import org.bankapp.util.EntityUtil;

import java.util.List;

public class BankAccountDaoImpl implements IBankAccountDao{
    EntityManager em = null;
    EntityTransaction et = null;
    @Override
    public void createBankAccount(BankAccount bankAccount) {
        em = EntityUtil.getEntityManager();
        et = em.getTransaction();
        et.begin();
        em.persist(bankAccount);
        et.commit();
        em.close();
    }
    @Override
    public void updateBankAccount(BankAccount bankAccount) {
        em = EntityUtil.getEntityManager();
        et = em.getTransaction();
        et.begin();
        BankAccount bankAccountUpdated = em.find(BankAccount.class,bankAccount.getActNumber());
        bankAccountUpdated.setCustomerName(bankAccount.getCustomerName());
        bankAccountUpdated.setActBalance(bankAccount.getActBalance());
        bankAccountUpdated.setCity(bankAccount.getCity());
        bankAccountUpdated.setBranchCode(bankAccount.getBranchCode());
        et.commit();
        em.close();
    }
    @Override
    public void deleteBankAccount(int actNumber) {
        em = EntityUtil.getEntityManager();
        et = em.getTransaction();
        et.begin();
        BankAccount bankAccount = em.find(BankAccount.class, actNumber);
        em.remove(bankAccount);
        et.commit();
        em.close();
    }
    @Override
    public List<BankAccount> getAllBankAccount() {
        em = EntityUtil.getEntityManager();
        List<BankAccount> allBankAccounts = em.createQuery("SELECT b FROM BankAccount b").getResultList();
        em.close();
        return allBankAccounts;
    }
    @Override
    public BankAccount getBankAccountById(int actNumber) {
        em = EntityUtil.getEntityManager();
        BankAccount bankAccountById = em.find(BankAccount.class, actNumber);
        em.close();
        return bankAccountById;
    }
    @Override
    public List<BankAccount> getBankAccountByCustomerName(String customerName) {
        em = EntityUtil.getEntityManager();
        List<BankAccount> allBankAccountsByCustomerName = em.createQuery("SELECT b FROM BankAccount b WHERE customerName = :customerName").setParameter("customerName", customerName).getResultList();
        em.close();
        return allBankAccountsByCustomerName;
    }
    @Override
    public List<BankAccount> getAllBankAccountByBranchCode(String branchCode) {
        em = EntityUtil.getEntityManager();
        List<BankAccount> allBankAccountsByBranchCode = em.createQuery("SELECT b FROM BankAccount b WHERE branchCode = :branchCode").setParameter("branchCode", branchCode).getResultList();
        em.close();
        return allBankAccountsByBranchCode;
    }
    @Override
    public List<Object[]> getAllBankAccountGroupByCity() {
        em = EntityUtil.getEntityManager();
        List<Object[]> allBankAccountsGroupByCity = em.createQuery("SELECT b.city,b.branchCode FROM BankAccount b GROUP BY b.city,b.branchCode").getResultList();
        em.close();
        return allBankAccountsGroupByCity;
    }
}
