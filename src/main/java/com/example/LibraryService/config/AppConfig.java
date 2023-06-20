package com.example.LibraryService.config;

import com.example.LibraryService.service.RentalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.support.CronTrigger;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

@Configuration
@EnableScheduling
public class AppConfig implements SchedulingConfigurer {

    @Autowired
    private RentalService rentalService;

    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        taskRegistrar.setScheduler(taskExecutor());
        taskRegistrar.addTriggerTask(
                () -> rentalService.processRentals(),
                triggerContext -> {
                    // Здесь можно настроить периодичность выполнения задачи
                    CronTrigger trigger = new CronTrigger("0 0 0 * * *");
                    return trigger.nextExecutionTime(triggerContext);
                }
        );
    }

    @Bean(destroyMethod = "shutdown")
    public Executor taskExecutor() {
        return Executors.newScheduledThreadPool(10);
    }
}
