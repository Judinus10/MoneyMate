package com.moneymate.controller;

import com.moneymate.service.ActivityService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;


@Controller
public class ActivityController {

    private final ActivityService activityService;

    @Autowired
    public ActivityController(ActivityService activityService) {
        this.activityService = activityService;
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
        model.addAttribute("activitiesToday", activityService.getTodayActivities());
        model.addAttribute("activitiesYesterday", activityService.getYesterdayActivities());
        model.addAttribute("activitiesMonth", activityService.getMonthActivities());

        return "activityPage"; // Refers to templates/activityPage.html
    }
}
