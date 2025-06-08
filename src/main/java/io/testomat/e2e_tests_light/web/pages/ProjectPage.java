package io.testomat.e2e_tests_light.web.pages;

import com.codeborne.selenide.Selectors;
import com.codeborne.selenide.SelenideElement;
import io.testomat.e2e_tests_light.utils.LoggerUtil;


import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;

public class ProjectPage {

    private final SelenideElement header = $(".first h2");
    private final SelenideElement readmeButton = $(".first [href*='/readme']");
    private final SelenideElement editButton = $(Selectors.byLinkText("Edit"));
    private final SelenideElement readMeSidebarDetailViewArea = $("#ember80 ul");


    public ProjectPage isLoaded(String projectName) {
        LoggerUtil.info("Check that project is open");
        header.shouldHave(text(projectName));
        readmeButton.shouldHave(text("Readme"));
        return this;
    }

    public ProjectPage openReadme() {
        LoggerUtil.info("Click on Readme button");
        readmeButton.click();
        return this;
    }

    public ProjectPage clickOnEdit() {
        LoggerUtil.info("Click on Readme button");
        editButton.click();
        return this;
    }

    public ProjectPage checkOnExactTextInReadme(String expectedText) {
        readMeSidebarDetailViewArea.shouldHave(text(expectedText));
        return this;
    }
}
