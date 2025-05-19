package io.testomat.e2e_tests_light.web.pages;

import com.codeborne.selenide.Selenide;
import io.testomat.e2e_tests_light.utils.LoggerUtil;


import static com.codeborne.selenide.Condition.hidden;
import static com.codeborne.selenide.Selenide.$;

public class SignInPage {

    public void open() {
        LoggerUtil.info("Open Sign In page");
        Selenide.open("/users/sign_in");
    }

    public void loginUser(String userEmail, String userPassword) {
        LoggerUtil.info("Fill user email");
        $("#content-desktop #user_email").setValue(userEmail);
        LoggerUtil.info("Fill user password");
        $("#content-desktop #user_password").setValue(userPassword);
        LoggerUtil.info("Tick Remember me button");
        $("#content-desktop #user_remember_me").click();
        LoggerUtil.info("Click Commit button");
        $("#content-desktop [name='commit']").click();
        LoggerUtil.info("Check that Sign In page is disappeared");
        $("#content-desktop #user_email").shouldBe(hidden);
        $("#content-desktop #user_password").shouldBe(hidden);
    }
}
