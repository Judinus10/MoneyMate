package com.moneymate.controller;

import com.moneymate.model.Transaction;
import com.moneymate.service.TransactionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping
    public Transaction create(@RequestBody Transaction transaction) {
        return transactionService.addTransaction(transaction);
    }

    @PutMapping("/{id}")
    public Transaction update(@PathVariable Long id, @RequestBody Transaction transaction) {
        return transactionService.updateTransaction(id, transaction);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        transactionService.deleteTransaction(id);
    }

    @GetMapping("/user/{userId}")
    public List<Transaction> getByUser(@PathVariable Long userId) {
        return transactionService.getTransactionsByUserId(userId);
    }

    @GetMapping("/{id}")
    public Transaction getOne(@PathVariable Long id) {
        return transactionService.getTransactionById(id)
                .orElseThrow(() -> new RuntimeException("Transaction not found"));
    }
}
