package com.moneymate.controller;

import com.moneymate.model.Transaction;
import com.moneymate.service.TransactionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Controller
@RequestMapping("/transactions")
public class TransactionWebController {

    private final TransactionService transactionService;

    public TransactionWebController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    // Show transactions for a hardcoded user for now (userId = 1)
    @GetMapping
    public String showTransactions(Model model) {
        Long userId = 1L; // Replace with dynamic userId when auth is ready
        model.addAttribute("transactions", transactionService.getTransactionsByUserId(userId));
        model.addAttribute("userId", userId);
        model.addAttribute("today", LocalDate.now()); 
        model.addAttribute("now", LocalDateTime.now());
        return "transactions";
    }

    @PostMapping("/add")
    public String addTransaction(@RequestParam Long userId,
            @RequestParam double amount,
            @RequestParam String type,
            @RequestParam String category,
            @RequestParam String date,
            @RequestParam String time,
            @RequestParam String description) {

        // Combine date and time into LocalDateTime
        LocalDate localDate = LocalDate.parse(date);
        LocalTime localTime = LocalTime.parse(time);
        LocalDateTime dateTime = LocalDateTime.of(localDate, localTime);

        Transaction transaction = new Transaction(
                userId,
                amount,
                type,
                category,
                dateTime, // ✅ Correct type now
                description);

        transactionService.addTransaction(transaction);
        return "redirect:/transactions";
    }

    // Delete transaction
    @PostMapping("/delete/{id}")
    public String deleteTransaction(@PathVariable Long id) {
        transactionService.deleteTransaction(id);
        return "redirect:/transactions";
    }
}
