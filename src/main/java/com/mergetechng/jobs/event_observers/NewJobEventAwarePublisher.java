package com.mergetechng.jobs.event_observers;

import com.mergetechng.jobs.entities.Job;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.lang.NonNullApi;
import org.springframework.stereotype.Component;

@Component
public class NewJobEventAwarePublisher implements ApplicationEventPublisherAware {

    private ApplicationEventPublisher applicationEventPublisher;
    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    public void publisherNewJobEvent(Job newJob){
        this.applicationEventPublisher.publishEvent(newJob);
    }
}
