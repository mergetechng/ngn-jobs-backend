package com.mergetechng.jobs.commons.enums;

public enum RoleEnum {
    ADMIN_DASHBOARD("This role allows an admin to have access to the dashboard"),
    USER_MANAGEMENT("This role allows an admin user to manage all other users (drivers or riders"),
    PUBLIC_API("The role for all user in the system to access endpoints with security"),
    SERVER_MANAGEMENT("This role allows a technical user to major endpoints"),
    ROLES_MANAGEMENT("This role will allow a user to manage roles in the system"),
    GROUPS_MANAGEMENT("This role will allow a user to manage groups in the system"),
    PRIVILEGE_MANAGEMENT("This role will allow an admin user to manage privileges"),
    ADMIN_MANAGEMENT("This role will allow a super admin to manage other admins"),
    RECEIPT_MANAGEMENT("Role that allows an admin user to manage all receipt in the system "),
    INVOICE_MANAGEMENT("Role that allows an admin user to manage all invoices in the system "),
    SERVER_MONITORING("This role allows an admin user to check the health status of the server and every other activity on the server, logs included"),
    PAYMENT_MANAGEMENT("This role will allow an admin user to manage all payments int the system"),
    JOB_EMPLOYER_USER("A role that allows job employer to create jobs for applicant"),
    JOB_APPLICANT_USER("The Job applicant user role"),
    ADMIN_USER(""),
    ACCOUNT_VERIFICATION(""),
    TRIP_SURCHARGE(""),
    VEHICLE_VERIFICATION(""),
    ADDRESS_VERIFICATION(""),
    IDENTITY_VERIFICATION(""),
    USER_DASHBOARD(""),
    WALLET_MANAGEMENT(""),
    API_MANAGEMENT(""),
    LOG_REQUEST_AUDIT("The role that allows a user to view log requests"),
    PAYMENT("Payment role, managing anything concerning payment"),
    USER_PAYMENT(""),
    FINANCE(""),
    VERIFICATION(""),
    REGISTRATION(""),
    BANK_DETAILS(""),
    SYSTEM_ADMIN(""),
    PAYMENT_WORKFLOW(""),
    VEHICLE_REGISTRATION("");
    public String description ;

    RoleEnum(String description) {
        this.description = description;
    }
}