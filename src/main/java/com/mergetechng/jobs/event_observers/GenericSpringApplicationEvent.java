package com.mergetechng.jobs.event_observers;

import org.springframework.context.ApplicationEvent;

public class GenericSpringApplicationEvent<T> extends ApplicationEvent {
    private T what;
    protected boolean success;

    public GenericSpringApplicationEvent(Object source){
        super(source);
    }

    public GenericSpringApplicationEvent(Object source , T what, boolean success) {
        super(source);
        this.what = what;
        this.success = success;
    }

    public T getWhat() {
        return what;
    }

    public void setWhat(T what) {
        this.what = what;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
