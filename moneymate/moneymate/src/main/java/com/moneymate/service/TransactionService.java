package com.moneymate.service;

import com.moneymate.model.Transaction;
import com.moneymate.repository.TransactionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;

    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    // Add new transaction
    public Transaction addTransaction(Transaction transaction) {
        return transactionRepository.save(transaction);
    }

    // Edit existing transaction
    public Transaction updateTransaction(Long id, Transaction updated) {
        Optional<Transaction> existingOpt = transactionRepository.findById(id);
        if (existingOpt.isPresent()) {
            Transaction existing = existingOpt.get();
            existing.setAmount(updated.getAmount());
            existing.setCategory(updated.getCategory());
            existing.setDateTime(updated.getDateTime());
            existing.setDescription(updated.getDescription());
            existing.setType(updated.getType());
            return transactionRepository.save(existing);
        } else {
            throw new RuntimeException("Transaction not found with id: " + id);
        }
    }

    // Delete transaction
    public void deleteTransaction(Long id) {
        transactionRepository.deleteById(id);
    }

    // Get all transactions for a user
    public List<Transaction> getTransactionsByUserId(Long userId) {
    List<Transaction> list = transactionRepository.findByUserId(userId);
    System.out.println("Inside service - transactions count: " + list.size());
    return list;
}


    // Get one transaction
    public Optional<Transaction> getTransactionById(Long id) {
        return transactionRepository.findById(id);
    }
}
