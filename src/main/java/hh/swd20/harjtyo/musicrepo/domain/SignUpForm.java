package hh.swd20.harjtyo.musicrepo.domain;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class SignUpForm {

    @NotNull
    @NotEmpty
    @Size(min=5, max=30)
    private String username;

    @NotNull
    @NotEmpty
    @Size(min=5, max=30)
    private String password;

    @NotNull
    @NotEmpty
    @Size(min=5, max=30)
    private String passwordCheck;

    @NotEmpty
    private String role = "USER";

    public SignUpForm() {

    }

    public SignUpForm(String username, String password, String passwordCheck, String role) {
        this.username = username;
        this.password = password;
        this.passwordCheck = passwordCheck;
        this.password = this.passwordCheck;
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordCheck() {
        return passwordCheck;
    }

    public void setPasswordCheck(String passwordCheck) {
        this.passwordCheck = passwordCheck;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
