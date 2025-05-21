package io.testomat.e2e_tests_light.web.pages;

import com.codeborne.selenide.SelenideElement;
import io.testomat.e2e_tests_light.utils.LoggerUtil;


import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class CreateProjectPage {

    private final SelenideElement headerText = $(".common-page-header-left h2");
    private final SelenideElement projectTitleInput = $("#project_title");
    private final SelenideElement createButton = $("#project-create-btn [type='submit']");
    private final SelenideElement warningBanner = $(".auth-main-container .common-flash-alert-right ");

    public void enterProjectTitle(String projectTitle) {
        LoggerUtil.info("Enter project title");
        projectTitleInput.setValue(projectTitle);
    }

    public void clickCreateButton() {
        LoggerUtil.info("Click Create button");
        createButton.click();
    }

    public void checkThatLimitBannerAlertIsVisible() {
        LoggerUtil.info("Limit banner alert is visible");
        warningBanner.shouldBe(visible);
    }

    public void isLoaded() {
        LoggerUtil.info("Check that Create project page is open");
        headerText.shouldHave(text("New project"));
    }
}
