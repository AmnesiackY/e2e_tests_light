package io.testomat.e2e_tests_light.web.pages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.testomat.e2e_tests_light.utils.LoggerUtil;


import static com.codeborne.selenide.Condition.hidden;
import static com.codeborne.selenide.Selenide.$;

public class SignInPage {

    private final SelenideElement emailInput = $("#content-desktop #user_email");
    private final SelenideElement passwordInput = $("#content-desktop #user_password");
    private final SelenideElement rememberMeCheckbox = $("#content-desktop #user_remember_me");
    private final SelenideElement signInButton = $("#content-desktop [name='commit']");

    public void open() {
        LoggerUtil.info("Open Sign In page");
        Selenide.open("/users/sign_in");
    }

    public void loginUser(String userEmail, String userPassword) {
        LoggerUtil.info("Fill user email");
        emailInput.setValue(userEmail);
        LoggerUtil.info("Fill user password");
        passwordInput.setValue(userPassword);
        LoggerUtil.info("Tick Remember me button");
        rememberMeCheckbox.click();
        LoggerUtil.info("Click Commit button");
        signInButton.click();
        LoggerUtil.info("Check that Sign In page is disappeared");
        emailInput.shouldBe(hidden);
        passwordInput.shouldBe(hidden);
    }
}
