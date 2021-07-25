package com.mergetechng.jobs.commons.enums;

public enum CVEnum {
    DOWNLOAD("download"),
    UPLOAD("upload"),
    DELETE("enum"),
    ATTACH_JOB_CV("attach_job_cv");
    String val;

    CVEnum(String val) {
        this.val = val;
    }
}
