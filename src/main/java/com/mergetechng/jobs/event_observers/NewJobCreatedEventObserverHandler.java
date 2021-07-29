package com.mergetechng.jobs.event_observers;

import com.mergetechng.jobs.entities.Job;
import org.springframework.context.ApplicationListener;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

@Component
public class NewJobCreatedEventObserverHandler implements ApplicationListener<GenericSpringApplicationEvent<Job>> {
    @Override
    public void onApplicationEvent(@NonNull GenericSpringApplicationEvent<Job> event) {
        System.out.println("Received spring generic event - " + event.getWhat());
    }
}
