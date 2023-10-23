package com.aninfo.service;

import com.aninfo.exceptions.InvalidTransactionTypeException;
import com.aninfo.model.Transaction;
import com.aninfo.model.Deposit;
import com.aninfo.model.Withdraw;
import com.aninfo.model.Account;
import com.aninfo.repository.TransactionRepository;
import com.aninfo.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    public Transaction createTransaction(Transaction transaction) {
        return transactionRepository.save(transaction);
    }

    public Double applyPromo(Double amount){
        if (amount >= 2000){
            double plus = amount / 10;
            if (plus > 500){
                plus = (double) 500;
            }
            return amount + plus;
        }
        return amount;
    }

    public Deposit createDeposit(Long cbu, Double amount, AccountService accountService){
        Optional<Account> accountOptional = accountService.findById(cbu);
        if (accountOptional.isEmpty()){
            throw new InvalidTransactionTypeException("Cbu not found");
        }
        accountService.deposit(cbu, amount);
        amount = applyPromo(amount); 
        Deposit deposit = new Deposit(cbu, amount);
        return (Deposit) createTransaction(deposit);
    }

    public Withdraw createWithdraw(Long cbu, Double amount, AccountService accountService){
        Optional<Account> accountOptional = accountService.findById(cbu);
        if (accountOptional.isEmpty()){
            throw new InvalidTransactionTypeException("Cbu not found");
        }
        if (amount <= 0){
            throw new InvalidTransactionTypeException("Cannot withdraw a negative amount");
        }
        accountService.withdraw(cbu, amount);
        Withdraw withdraw = new Withdraw(cbu, amount);
        return (Withdraw) createTransaction(withdraw);
    }

     public void save(Transaction transaction) {
         transactionRepository.save(transaction);
     }

    public Collection<Transaction> getTransactions() {
        return transactionRepository.findAll();
    }

    public Optional<Transaction> findById(Long id) {
        return transactionRepository.findById(id);
    }

    public Collection<Transaction> findByCbu(Long cbu){
        
        List<Transaction> transactions = transactionRepository.findAll();
        List<Transaction> transactionsWithCbu = new ArrayList<>();

        for (Transaction transaction : transactions) {
            if (transaction.getAssociatedCbu().equals(cbu)) {
                transactionsWithCbu.add(transaction);
            }
        }

        return transactionsWithCbu;
    }

    public void deleteById(Long id) {
        Optional<Transaction> transactionOptional = transactionRepository.findById(id);
        if (transactionOptional.isEmpty()){
            throw new InvalidTransactionTypeException("Id not found");
        }
        transactionRepository.deleteById(id);
    }
}