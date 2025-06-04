package com.moneymate.controller;

import com.moneymate.model.Transaction;
import com.moneymate.service.TransactionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/")
public class TransactionViewController {

    private final TransactionService transactionService;

    public TransactionViewController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping("/user/{userId}")
    public String listTransactions(@PathVariable Long userId, Model model) {
        List<Transaction> transactions = transactionService.getTransactionsByUserId(userId);
        model.addAttribute("transactions", transactions);
        model.addAttribute("newTransaction", new Transaction());
        model.addAttribute("userId", userId);
        return "transactions";
    }

    @PostMapping("/add")
    public String addTransaction(@ModelAttribute Transaction newTransaction) {
        transactionService.addTransaction(newTransaction);
        return "redirect:/transactions/user/" + newTransaction.getUserId();
    }

    @GetMapping("/delete/{id}/{userId}")
    public String deleteTransaction(@PathVariable Long id, @PathVariable Long userId) {
        transactionService.deleteTransaction(id);
        return "redirect:/transactions/user/" + userId;
    }
}
