package com.crud.tasks.scheduler;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.domain.Mail;
import com.crud.tasks.repository.TaskRepository;
import com.crud.tasks.service.SimpleEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class EmailScheduler {

    private static final String SUBJECT = "Tasks: Once a day email";

    @Autowired
    private SimpleEmailService simpleEmailService;

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private AdminConfig adminConfig;

    //@Scheduled(cron = "0 0 10 * * *")
    @Scheduled(fixedDelay = 100000)
    private void sendInformationEmail() {
        long size = taskRepository.count();
            simpleEmailService.send(new Mail(
                    adminConfig.getAdminMail(),
                    SUBJECT,
                    generateMailBody(size), null));
    }

    private String generateMailBody(long size) {
        return "Currently in database you got " + size + (size > 1 ? " tasks" : " task");

    }

    @Scheduled(cron = "0 0 10 * * *")
    private void sendDailyEmail() {
        long size = taskRepository.count();
        simpleEmailService.sendDailyEmail(new Mail(
                adminConfig.getAdminMail(),
                SUBJECT,
                generateMailBody(size), null));
    }
}
