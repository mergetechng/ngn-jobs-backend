package com.mergetechng.jobs.event_observers;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ApplicationEventMulticaster;
import org.springframework.context.event.SimpleApplicationEventMulticaster;
import org.springframework.core.task.SimpleAsyncTaskExecutor;

@Configuration(value = "asynchronousEventConfiguration")
public class AsynchronousEventConfiguration {
    //creates new thread for every new event published to
    // make it asynchronous
    @Bean(name = "applicationEventMulticasting")
    public ApplicationEventMulticaster simpleApplicationEventMulticasting() {
        SimpleApplicationEventMulticaster eventMulticasting =
                new SimpleApplicationEventMulticaster();
        eventMulticasting.setTaskExecutor(new SimpleAsyncTaskExecutor());
        return eventMulticasting;
    }
}
