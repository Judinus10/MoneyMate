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

    /**
     * Handle submission of the add transaction form.
     */
    // @PostMapping("/transactions/add")
    // public String addTransaction(
    // @RequestParam Long userId,
    // @RequestParam double amount,
    // @RequestParam String type,
    // @RequestParam String category,
    // @RequestParam String date,
    // @RequestParam String time,
    // @RequestParam(required = false) String description,
    // RedirectAttributes redirectAttributes) {

    // LocalDate localDate = LocalDate.parse(date);
    // LocalTime localTime = LocalTime.parse(time);
    // var dateTime = localDate.atTime(localTime);

    // Transaction transaction = new Transaction(
    // userId,
    // amount,
    // type,
    // category,
    // dateTime,
    // description
    // );

    // transactionService.addTransaction(transaction);
    // redirectAttributes.addFlashAttribute("message", "Transaction added
    // successfully!");

    // return "redirect:/transactions/list?userId=" + userId;
    // }
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

    /**
     * Display a list of all transactions for the specified user.
     */
    @GetMapping("/transactions/list")
    public String showTransactionList(
            Model model,
            @RequestParam(value = "userId", required = false, defaultValue = "1") Long userId) {

        List<Transaction> transactions = transactionService.getTransactionsByUserId(userId);
        System.out.println("Fetched transactions for userId: " + userId);
        System.out.println("Transaction count: " + transactions.size());

        // Check for empty or null list (debug purpose)
        if (transactions == null || transactions.isEmpty()) {
            System.out.println("⚠️ No transactions found for userId " + userId);
        }

        model.addAttribute("transactions", transactions);
        model.addAttribute("userId", userId);

        return "transactions-list"; // Make sure the file is located at templates/transactions-list.html
    }

    /**
     * Handle deletion of a transaction.
     */
    @PostMapping("/transactions/delete/{id}")
    public String deleteTransaction(
            @PathVariable Long id,
            @RequestParam(value = "userId", required = false, defaultValue = "1") Long userId,
            RedirectAttributes redirectAttributes) {

        transactionService.deleteTransaction(id);
        redirectAttributes.addFlashAttribute("message", "Transaction deleted successfully!");

        return "redirect:/transactions/list?userId=" + userId;
    }

    // Example data
    @GetMapping("/dashboard")
    public String showDashboard(Model model) {
        List<Integer> monthlyData = List.of(200, 450, 700, 1200, 1600, 2100);
        List<Integer> yearlyData = List.of(1500, 2000, 1700, 2200, 2500, 2700);
        model.addAttribute("monthlyData", monthlyData);
        model.addAttribute("yearlyData", yearlyData);
        return "dashboard";
    }

    // @GetMapping("/activity")
    // public String showActivityPage(Model model, @RequestParam Long userId) {
    //     List<Transaction> all = transactionService.getTransactionsByUserId(userId);

    //     LocalDate today = LocalDate.now();
    //     LocalDate yesterday = today.minusDays(1);
    //     LocalDate firstDay = today.withDayOfMonth(1);

    //     List<Transaction> todayTx = new ArrayList<>();
    //     List<Transaction> yesterdayTx = new ArrayList<>();
    //     List<Transaction> monthTx = new ArrayList<>();

    //     for (Transaction tx : all) {
    //         LocalDate txDate = tx.getDate().toLocalDate();
    //         if (txDate.isEqual(today)) {
    //             todayTx.add(tx);
    //         } else if (txDate.isEqual(yesterday)) {
    //             yesterdayTx.add(tx);
    //         } else if (!txDate.isBefore(firstDay)) {
    //             monthTx.add(tx);
    //         }
    //     }

    //     model.addAttribute("userId", userId);
    //     model.addAttribute("todayTransactions", todayTx);
    //     model.addAttribute("yesterdayTransactions", yesterdayTx);
    //     model.addAttribute("monthTransactions", monthTx);

    //     return "ActivityPage"; // ActivityPage.html
    // }

    @GetMapping("/activityPage")
    public String getActivityPage(Model model) {
        // Add data like today's, yesterday's, monthly activities to the model
        // Example placeholder:
        model.addAttribute("userId", 1); // Replace with actual user logic
        model.addAttribute("activitiesToday", yourService.getTodayActivities());
        model.addAttribute("activitiesYesterday", yourService.getYesterdayActivities());
        model.addAttribute("activitiesMonth", yourService.getMonthActivities());

        return "activityPage"; // Refers to templates/activityPage.html
    }

}
