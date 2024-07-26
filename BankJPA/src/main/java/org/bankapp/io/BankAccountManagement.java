package org.bankapp.io;

import org.bankapp.dto.BankAccount;
import org.bankapp.exception.BankAccountNotFoundException;
import org.bankapp.service.BankAccountServiceImpl;
import org.bankapp.service.IBankAccountService;

import java.util.List;
import java.util.Scanner;

public class BankAccountManagement {
    public static void main(String[] args){
        IBankAccountService bankAccountService = new BankAccountServiceImpl();
            Scanner scanner = new Scanner(System.in);
            boolean exit = false;
            while (!exit) {
                System.out.println("Choose an operation:");
                System.out.println("1. Add a bank account");
                System.out.println("2. Update a bank account");
                System.out.println("3. Delete a bank account");
                System.out.println("4. Get all bank accounts");
                System.out.println("5. Get a bank account by Account Number");
                System.out.println("6. Get bank accounts by Customer Name");
                System.out.println("7. Get all bank accounts by Branch Code");
                System.out.println("8. Get bank accounts group by city");
                System.out.println("9. Exit");

                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 9:
                        exit = true;
                        System.out.println("Exiting...");
                        break;
                    case 1:

                        System.out.println("Enter bank account details:");
                        System.out.print("Account Number: ");
                        int actNumber = scanner.nextInt();
                        scanner.nextLine();
                        System.out.print("Customer Name: ");
                        String customerName = scanner.nextLine();
                        System.out.print("Account Balance: ");
                        int actBalance = scanner.nextInt();
                        scanner.nextLine();
                        System.out.print("City: ");
                        String city = scanner.nextLine();
                        System.out.print("Branch Code: ");
                        int branchCode = scanner.nextInt();

                        BankAccount newBankAccount = new BankAccount(actNumber,customerName,actBalance,city,branchCode);
                        bankAccountService.createBankAccount(newBankAccount);
                        System.out.println("Bank Account added successfully.");
                        break;
                    case 2:

                        System.out.print("Enter bank account number to update: ");
                        int updateBankAccountNo = scanner.nextInt();
                        scanner.nextLine();

                        BankAccount bankAccountToBeUpdated = bankAccountService.getBankAccountById(updateBankAccountNo);
                        if (bankAccountToBeUpdated != null) {
                            System.out.println("Enter update details:");
                            System.out.print("Customer Name: ");
                            bankAccountToBeUpdated.setCustomerName(scanner.nextLine());
                            System.out.print("Account Balance: ");
                            bankAccountToBeUpdated.setActBalance(scanner.nextInt());
                            scanner.nextLine();
                            System.out.print("City: ");
                            bankAccountToBeUpdated.setCity(scanner.nextLine());
                            System.out.print("Branch Code: ");
                            bankAccountToBeUpdated.setBranchCode(scanner.nextInt());

                            bankAccountService.updateBankAccount(bankAccountToBeUpdated);
                            System.out.println("Bank account updated successfully.");
                        } else {
                            try {
                                throw new BankAccountNotFoundException("Bank Account Not Found !!");
                            } catch (BankAccountNotFoundException e) {
                                throw new RuntimeException(e);
                            }
                        }
                        break;
                    case 3:

                        System.out.print("Enter bank account number to delete: ");
                        int deleteAccNo = scanner.nextInt();
                        scanner.nextLine();

                        bankAccountService.deleteBankAccount(deleteAccNo);
                        System.out.println("Bank account deleted successfully.");
                        break;
                    case 4:

                        List<BankAccount> allBankAccounts = bankAccountService.getAllBankAccount();
                        if(allBankAccounts.size()==0){
                            try {
                                throw new BankAccountNotFoundException("Bank Account Not Found !!");
                            } catch (BankAccountNotFoundException e) {
                                throw new RuntimeException(e);
                            }
                        }
                        System.out.println("All Bank Accounts:");
                        allBankAccounts.forEach(System.out::println);
                        break;
                    case 5:

                        System.out.print("Enter bank account number to retrieve: ");
                        int retrieveBankAccNo = scanner.nextInt();
                        scanner.nextLine();

                        BankAccount retrievedBankAcc = bankAccountService.getBankAccountById(retrieveBankAccNo);
                        if (retrievedBankAcc != null) {
                            System.out.println("Bank Account found: " + retrievedBankAcc);
                        } else {
                            try {
                                throw new BankAccountNotFoundException("Bank Account Not Found !!");
                            } catch (BankAccountNotFoundException e) {
                                throw new RuntimeException(e);
                            }
                        }
                        break;

                    case 6:
                        System.out.print("Enter customer name to retrieve: ");
                        String retrieveCustomerName = scanner.nextLine();

                        List<BankAccount> retrievedBankAccByName = bankAccountService.getBankAccountByCustomerName(retrieveCustomerName);
                        if (retrievedBankAccByName != null) {
                            retrievedBankAccByName.forEach(System.out::println);
                        } else {
                            try {
                                throw new BankAccountNotFoundException("Bank Account Not Found !!");
                            } catch (BankAccountNotFoundException e) {
                                throw new RuntimeException(e);
                            }
                        }
                        break;
                    case 7:
                        System.out.print("Enter branch code to retrieve: ");
                        String retrieveBranchCode = scanner.nextLine();

                        List<BankAccount> allBankAccountsByBranchCode = bankAccountService.getAllBankAccountByBranchCode(retrieveBranchCode);
                        if(allBankAccountsByBranchCode.size()==0){
                            try {
                                throw new BankAccountNotFoundException("Bank Account Not Found !!");
                            } catch (BankAccountNotFoundException e) {
                                throw new RuntimeException(e);
                            }
                        }
                        System.out.println("All Bank Accounts By Branch Code:");
                        allBankAccountsByBranchCode.forEach(System.out::println);
                        break;
                    case 8:
                        List<Object[]> bankAccGroupByCity = bankAccountService.getAllBankAccountGroupByCity();
                        System.out.println("Bank Account group by:");
                        for (Object[] obj : bankAccGroupByCity) {
                            System.out.println("City: " + obj[0] +
                                    ", Branch Code: " + obj[1]);
                        }
                        break;
                    default:
                        System.out.println("Invalid choice. Choose again.");
                }
            }
            scanner.close();
        }
    }
