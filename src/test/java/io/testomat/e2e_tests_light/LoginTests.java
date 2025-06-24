package io.testomat.e2e_tests_light;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import static io.testomat.e2e_tests_light.BaseTest.app;

public class LoginTests {

    private final String invalidLogin = "invalidUser@mail.com";
    private final String invalidPassword = "invalidPassword";

    @Test
    @DisplayName("Login with valid data")
    void loginWithValidData() {
        app.signInPage.open();
        app.signInPage.loginUser(BaseTest.username, BaseTest.password);
        app.projectsPage.isLoaded().signInSuccess();
    }

    @Test
    @DisplayName("Login with invalid data")
    void loginWithInvalidData() {
        app.signInPage.open();
        app.signInPage.checkLoginValidation(invalidLogin, invalidPassword);
    }
}
