package com.mergetechng.jobs.commons.enums;

import io.swagger.v3.oas.annotations.Parameter;

public enum RequestActionEnum {
    /*
     *Download CV or Resume attached to a Job application
     */
    JOB_CV_RESUME_DOWNLOAD,

    /*Upload CV or Resume and attach to a Job Application*/
    JOB_CV_RESUME_UPLOAD,

    /*Delete CV or Resume attached to a Job application*/
    JOB_CV_RESUME_DELETE,

    /*Attach CV or Resume to a Job application*/
    JOB_CV_RESUME_ATTACH,

    /*Attach CV or Resume to a Job application*/
    JOB_CV_RESUME_DEFAULT,

    GET_JOB ,

    DELETE_JOB,

    CREATE_JOB;
}
