package io.testomat.e2e_tests_light;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.logging.Logger;
import org.junit.platform.commons.logging.LoggerFactory;


import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

public class ProjectPageTests {

    private static final Logger logger = LoggerFactory.getLogger(ProjectPageTests.class);

    @Test()
    public void userCanFindAndOpenProjectWithTests() {
        final String userEmail = "hitsac2711@gmail.com";
        final String userPassword = "KguJn8Tq!F7y6md";
        final String workspaceName = "QA Club Lviv";
        final String projectName = "Manufacture light";

        logger.info(() -> "Open Testomat");
        open("https://app.testomat.io/");

        logger.info(() -> "Login user");
        $("#content-desktop #user_email").setValue(userEmail);
        $("#content-desktop #user_password").setValue(userPassword);
        $("#content-desktop #user_remember_me").click();
        $("#content-desktop [name=\"commit\"]").click();
        $(".common-flash-success").shouldBe(visible);

        logger.info(() -> "Choose workspace");
        $("#content-desktop #company_id").selectOptionContainingText(workspaceName);

        logger.info(() -> "Search project ");
        $("#content-desktop #search").setValue("Manufacture light");

        logger.info(() -> "Select project");
        $(byText(projectName)).click();

        logger.info(() -> "Check that project is open");
        $("h2").shouldHave(text(projectName));
    }
}
