package com.mergetechng.jobs.commons.dto;

import javax.validation.constraints.NotNull;

public class UserResetPasswordDto {

    @NotNull
    private String newPassword ;
    @NotNull
    private String confirmPassword ;

    public UserResetPasswordDto(@NotNull String newPassword, @NotNull String confirmPassword) {
        this.newPassword = newPassword;
        this.confirmPassword = confirmPassword;
    }

    public UserResetPasswordDto(){}

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getPassword() {
        return newPassword;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }
}
