package com.mergetechng.jobs.event_observers;

import com.mergetechng.jobs.commons.enums.NgJobsEventTypeAndStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class NgJobFileUploadEventHandler implements ApplicationListener<GenericSpringApplicationEvent<Map<String, NgJobsEventTypeAndStatus>>> {
    private static final Logger LOGGER = LoggerFactory.getLogger(NgJobFileUploadEventHandler.class);
    @Override
    public void onApplicationEvent(GenericSpringApplicationEvent<Map<String, NgJobsEventTypeAndStatus>> mapGenericSpringApplicationEvent) {
        if (mapGenericSpringApplicationEvent.getWhat().get("EVENT_TYPE") == NgJobsEventTypeAndStatus.UPLOAD) {
            if (mapGenericSpringApplicationEvent.getWhat().get("STATUS").name().equals("UPLOAD_FAILED")){
                LOGGER.error("Failed Upload Event not handled....");
            }
        }
        System.out.println(mapGenericSpringApplicationEvent.success);
    }
}
