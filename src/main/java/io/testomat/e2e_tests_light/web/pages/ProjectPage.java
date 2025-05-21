package io.testomat.e2e_tests_light.web.pages;

import io.testomat.e2e_tests_light.utils.LoggerUtil;


import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;

public class ProjectPage {

    public void isLoaded(String projectName) {
        LoggerUtil.info("Check that project is open");
        $(".first h2").shouldHave(text(projectName));
        $(".first [href*='/readme']").shouldHave(text("Readme"));
    }
}
