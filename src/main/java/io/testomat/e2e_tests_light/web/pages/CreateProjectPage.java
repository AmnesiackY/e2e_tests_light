package io.testomat.e2e_tests_light.web.pages;

import io.testomat.e2e_tests_light.utils.LoggerUtil;


import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class CreateProjectPage {

    public void enterProjectTitle(String projectTitle) {
        LoggerUtil.info("Enter project title");
        $("#project_title").setValue(projectTitle);
    }

    public void clickCreateButton() {
        LoggerUtil.info("Click Create button");
        $("#project-create-btn [type='submit']").click();
    }

    public void checkThatLimitBannerAlertIsVisible() {
        LoggerUtil.info("Limit banner alert is visible");
        $(".auth-main-container .common-flash-alert-right ").shouldBe(visible);
    }
}
