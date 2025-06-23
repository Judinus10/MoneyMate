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

    @GetMapping("/activityPage")
    public String getActivityPage(Model model) {
        model.addAttribute("activitiesToday", activityService.getTodayActivities());
        model.addAttribute("activitiesYesterday", activityService.getYesterdayActivities());
        model.addAttribute("activitiesMonth", activityService.getMonthActivities());

        return "activityPage"; // Refers to templates/activityPage.html
    }
}
