package com.moneymate.service;

import com.moneymate.model.Transaction;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.moneymate.model.Transaction;

@Service
public class ActivityService {

    public List<Transaction> getTodayActivities() {
        // Fetch from DB or mock data
        return List.of(
            new Transaction(1, 100.0, "EXPENSE", "Food", LocalDateTime.now(), "Lunch"),
            new Transaction(2, 50.0, "INCOME", "Salary", LocalDateTime.now(), "Bonus")
        );
    }

    public List<Transaction> getYesterdayActivities() {
        // Sample placeholder
        return List.of(
            new Transaction(3, 40.0, "EXPENSE", "Transport", LocalDateTime.now().minusDays(1), "Bus fare")
        );
    }

    public List<Transaction> getMonthActivities() {
        // Sample placeholder
        return List.of(
            new Transaction(4, 1000.0, "INCOME", "Salary", LocalDateTime.now().minusDays(10), "Monthly salary")
        );
    }
}
