package com.mergetechng.jobs.commons.dto;

import javax.validation.constraints.NotNull;

public class UserResetPasswordDto {

    @NotNull
    private String newPassword ;
    @NotNull
    private String confirmPassword ;
    @NotNull
    private String username ;


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

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
