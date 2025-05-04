package io.testomat.e2e_tests_light;

import com.codeborne.selenide.Condition;
import java.util.logging.Logger;
import org.junit.jupiter.api.Test;


import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

public class ProjectPageTests {

    Logger logger;

    @Test
    public void firstTest() {
        open("https://app.testomat.io/");

        logger.info("Login user");
        $("#content-desktop #user_email").setValue("hitsac2711@gmail.com");
        $("#content-desktop #user_password").setValue("KguJn8Tq!F7y6md");
        $("#content-desktop #user_remember_me").click();
        $("#content-desktop [name=\"commit\"]").click();
        $(".common-flash-success").shouldBe(Condition.visible);

        logger.info("Choose workspace");
        $("#content-desktop #company_id").selectOptionContainingText("QA Club Lviv");

        //search project
        logger.info("Search project ");
        $("#content-desktop #search").setValue("manufacture light");

        //select project
        $(byText("Manufacture light")).click();


        $("h2").shouldHave(Condition.text("Manufacture light"));
    }

}
