package com.moneymate.controller;

import com.moneymate.model.Transaction;
import com.moneymate.service.TransactionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Controller
@RequestMapping("/transactions")
public class TransactionWebController {

    private final TransactionService transactionService;

    public TransactionWebController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    // Show the form page for adding transactions
    @GetMapping
    public String showForm(Model model, @RequestParam(value = "userId", required = false, defaultValue = "1") Long userId) {
        model.addAttribute("userId", userId);
        model.addAttribute("nowDate", LocalDate.now());
        // Set time without seconds and nanos for input[type=time]
        model.addAttribute("nowTime", LocalTime.now().withSecond(0).withNano(0));
        return "transactions"; // Thymeleaf template: transactions.html (form page)
    }

    // Handle POST request to add a transaction
    @PostMapping("/add")
    public String addTransaction(
            @RequestParam Long userId,
            @RequestParam double amount,
            @RequestParam String type,
            @RequestParam String category,
            @RequestParam String date,
            @RequestParam String time,
            @RequestParam(required = false) String description,
            RedirectAttributes redirectAttributes) {

        // Parse date and time
        LocalDate localDate = LocalDate.parse(date);
        LocalTime localTime = LocalTime.parse(time);
        var dateTime = localDate.atTime(localTime);

        // Create transaction object
        Transaction transaction = new Transaction(
                userId,
                amount,
                type,
                category,
                dateTime,
                description
        );

        // Save transaction
        transactionService.addTransaction(transaction);

        // Add success message (optional)
        redirectAttributes.addFlashAttribute("message", "Transaction added successfully!");

        // Redirect to list page showing all transactions for the user
        return "redirect:/transactions/list?userId=" + userId;
    }

    // Show the list of transactions
    @GetMapping("/list")
    public String showTransactionList(Model model, @RequestParam(value = "userId", required = false, defaultValue = "1") Long userId) {
        List<Transaction> transactions = transactionService.getTransactionsByUserId(userId);
        model.addAttribute("transactions", transactions);
        model.addAttribute("userId", userId);
        return "transactions-list"; // Thymeleaf template: transactions-list.html (list page)
    }

    // Handle deletion of a transaction
    @PostMapping("/delete/{id}")
    public String deleteTransaction(@PathVariable Long id, @RequestParam(value = "userId", required = false, defaultValue = "1") Long userId,
                                    RedirectAttributes redirectAttributes) {
        transactionService.deleteTransaction(id);
        redirectAttributes.addFlashAttribute("message", "Transaction deleted successfully!");
        return "redirect:/transactions/list?userId=" + userId;
    }
}
