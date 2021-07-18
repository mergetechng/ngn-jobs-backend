package com.mergetechng.jobs.commons.dto;

import javax.validation.constraints.NotNull;


public class UserUpdatePasswordDto {

    @NotNull
    private String oldPassword ;
    @NotNull
    private String newPassword;
    @NotNull
    private String confirmPassword;

    public UserUpdatePasswordDto(String newPassword, String confirmPassword , String oldPassword) {
        this.newPassword = newPassword;
        this.confirmPassword = confirmPassword;
        this.oldPassword = oldPassword ;
    }

    public UserUpdatePasswordDto() {
    }

    public String getNewPassword() {
        return newPassword;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }
}
