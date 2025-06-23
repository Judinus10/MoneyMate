package com.moneymate.service;

import com.moneymate.model.Transaction;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ActivityService {

    public List<Transaction> getTodayActivities() {
        return List.of(
            new Transaction(1L, 100.0, "EXPENSE", "Food", LocalDateTime.now(), "Lunch"),
            new Transaction(1L, 50.0, "INCOME", "Salary", LocalDateTime.now(), "Bonus")
        );
    }

    public List<Transaction> getYesterdayActivities() {
        return List.of(
            new Transaction(1L, 40.0, "EXPENSE", "Transport", LocalDateTime.now().minusDays(1), "Bus fare")
        );
    }

    public List<Transaction> getMonthActivities() {
        return List.of(
            new Transaction(1L, 1000.0, "INCOME", "Salary", LocalDateTime.now().minusDays(10), "Monthly salary")
        );
    }
}
