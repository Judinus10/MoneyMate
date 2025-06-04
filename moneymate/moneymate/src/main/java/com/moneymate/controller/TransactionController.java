package com.moneymate.controller;

import com.moneymate.model.Transaction;
import com.moneymate.service.TransactionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/transactions")
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    // 🌐 Web: Show all transactions for user
    @GetMapping
    public String showTransactions(Model model) {
        Long userId = 1L; // Replace with dynamic userId when auth is ready
        List<Transaction> transactions = transactionService.getTransactionsByUserId(userId);
        model.addAttribute("transactions", transactions);
        model.addAttribute("userId", userId);
        return "transactions";
    }

    // 🌐 Web: Add transaction via form
    @PostMapping("/add")
    public String addTransaction(@RequestParam Long userId,
                                 @RequestParam double amount,
                                 @RequestParam String type,
                                 @RequestParam String category,
                                 @RequestParam String date,
                                 @RequestParam String description) {

        Transaction transaction = new Transaction(
                userId,
                amount,
                type,
                category,
                LocalDate.parse(date),
                description
        );
        transactionService.addTransaction(transaction);
        return "redirect:/transactions";
    }

    // 🌐 Web: Delete transaction
    @PostMapping("/delete/{id}")
    public String deleteTransaction(@PathVariable Long id) {
        transactionService.deleteTransaction(id);
        return "redirect:/transactions";
    }

    // ✅ REST: Create transaction
    @PostMapping("/api")
    @ResponseBody
    public Transaction create(@RequestBody Transaction transaction) {
        return transactionService.addTransaction(transaction);
    }

    // ✅ REST: Update transaction
    @PutMapping("/api/{id}")
    @ResponseBody
    public Transaction update(@PathVariable Long id, @RequestBody Transaction transaction) {
        return transactionService.updateTransaction(id, transaction);
    }

    // ✅ REST: Delete transaction
    @DeleteMapping("/api/{id}")
    @ResponseBody
    public void delete(@PathVariable Long id) {
        transactionService.deleteTransaction(id);
    }

    // ✅ REST: Get transaction by ID
    @GetMapping("/api/{id}")
    @ResponseBody
    public Transaction getOne(@PathVariable Long id) {
        return transactionService.getTransactionById(id)
                .orElseThrow(() -> new RuntimeException("Transaction not found"));
    }

    // ✅ REST: Get transactions by user
    @GetMapping("/api/user/{userId}")
    @ResponseBody
    public List<Transaction> getByUser(@PathVariable Long userId) {
        return transactionService.getTransactionsByUserId(userId);
    }
}
