package com.moneymate.controller;

import com.moneymate.model.Transaction;
import com.moneymate.service.TransactionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Controller
public class TransactionWebController {

    private final TransactionService transactionService;

    public TransactionWebController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    /**
     * Redirect root ("/") to the home page (transactions form).
     */
    @GetMapping({ "/", "/home" })
    public String homePage(Model model) {
        model.addAttribute("userName", "Sarah");
        model.addAttribute("balance", "$12,450.00");
        model.addAttribute("income", "$8,000");
        model.addAttribute("expenses", "$3,200");
        return "home";
    }

    /**
     * Show the form to add a new transaction.
     */
    @GetMapping("/transactions")
    public String showForm(
            Model model,
            @RequestParam(value = "userId", required = false, defaultValue = "1") Long userId) {

        model.addAttribute("userId", userId);
        model.addAttribute("nowDate", LocalDate.now());
        model.addAttribute("nowTime", LocalTime.now().withSecond(0).withNano(0));

        return "transactions"; // => templates/transactions.html
    }

    @PostMapping("/transactions/add")
    public String addTransaction(
            @RequestParam Long userId,
            @RequestParam double amount,
            @RequestParam String type,
            @RequestParam String category,
            @RequestParam String date,
            @RequestParam String time,
            @RequestParam(required = false) String description,
            RedirectAttributes redirectAttributes) {

        try {
            LocalDate localDate = LocalDate.parse(date);
            LocalTime localTime = LocalTime.parse(time);
            var dateTime = localDate.atTime(localTime);

            Transaction transaction = new Transaction(
                    userId,
                    amount,
                    type,
                    category,
                    dateTime,
                    description);

            transactionService.addTransaction(transaction);
            redirectAttributes.addFlashAttribute("message", "Transaction added successfully!");

        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Failed to add transaction: " + e.getMessage());
            return "redirect:/transactions";
        }

        return "redirect:/transactions?userId=" + userId;
    }

    @GetMapping("/dashboard")
    public String showDashboard(Model model) {
        List<Integer> monthlyData = List.of(200, 450, 700, 1200, 1600, 2100);
        List<Integer> yearlyData = List.of(1500, 2000, 1700, 2200, 2500, 2700);
        model.addAttribute("monthlyData", monthlyData);
        model.addAttribute("yearlyData", yearlyData);
        return "dashboard";
    }

}
