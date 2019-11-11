package com.smt.example.cron;

import com.smt.example.constant.Constants;
import com.smt.example.service.utilities.HelperService;
import com.smt.example.utilities.LogUtilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Cron Job @author Turuu
 */

@Component
public class CronJob implements Constants {

    private HelperService helperService;

    public CronJob(HelperService helperService) {
        this.helperService = helperService;
    }

    @Scheduled(cron = "*/10 * * * * *")
    public void doRunCron() {
        LogUtilities.info(this.getClass().getName(), "[cron.job][ini][" + helperService.getLocalDateTime() + "]");
        System.out.println("hello world");
        LogUtilities.info(this.getClass().getName(), "[cron.job][end][" + helperService.getLocalDateTime() + "]");
    }

}
