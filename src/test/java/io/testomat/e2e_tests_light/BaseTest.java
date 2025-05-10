package io.testomat.e2e_tests_light;

import io.github.cdimascio.dotenv.Dotenv;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.platform.commons.logging.Logger;
import org.junit.platform.commons.logging.LoggerFactory;


import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.open;

public class BaseTest {

    static Dotenv env = Dotenv.load();

    protected final Logger logger = LoggerFactory.getLogger(getClass());

    private static final String baseUrl = env.get("BASE_URL");
    private static final String userEmail = env.get("USERNAME");
    private static final String userPassword = env.get("PASSWORD");

    @BeforeEach
    public void openHomepage() {
        open(baseUrl);
        loginUser(userEmail, userPassword);
    }

    @AfterEach
    public void closeTestDriver() {
        closeWebDriver();
    }

    private void loginUser(String userEmail, String userPassword) {
        logger.info(() -> "Fill user email");
        $("#content-desktop #user_email").setValue(userEmail);
        logger.info(() -> "Fill user password");
        $("#content-desktop #user_password").setValue(userPassword);
        logger.info(() -> "Tick Remember me button");
        $("#content-desktop #user_remember_me").click();
        logger.info(() -> "Click Commit button");
        $("#content-desktop [name='commit']").click();
        $(".common-flash-success").shouldBe(visible);
    }
}
