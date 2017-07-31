package app.gamestore.dto.bindingDtos.user;

import app.gamestore.annotations.PasswordMatching;

import javax.validation.constraints.Pattern;

@PasswordMatching
public class RegisterUser {
    @Pattern(regexp = "^\\w+@\\w+\\..\\w+$", message = "Invalid email")
    private String email;
    @Pattern(regexp = "(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9]).{6,50}", message = "Invalid password")
    private String password;
    private String confirmPassword;
    private String fullName;

    public RegisterUser() {
    }

    public RegisterUser(String email, String password, String confirmPassword, String fullName) {
        this.email = email;
        this.password = password;
        this.confirmPassword = confirmPassword;
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

   public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}
