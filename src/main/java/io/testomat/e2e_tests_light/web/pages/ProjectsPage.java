package io.testomat.e2e_tests_light.web.pages;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.testomat.e2e_tests_light.utils.LoggerUtil;


import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class ProjectsPage {

    private final SelenideElement searchInput = $("#search");
    private final SelenideElement projectsCounter = $("#container kbd");
    private final SelenideElement companiesTab = $("[href='/companies']");


    public void open() {
        LoggerUtil.info("Open Projects page");
        Selenide.open("");
    }

    public void isLoaded() {
        searchInput.shouldBe(visible);
    }

    public void searchForProject(String targetProjectName) {
        LoggerUtil.info("Search project ");
        searchInput.setValue(targetProjectName);
    }

    public void selectProject(String targetProjectName) {
        LoggerUtil.info("Select project " + targetProjectName);
        $(byText(targetProjectName)).click();
    }

    public void signInSuccess() {
        LoggerUtil.info("Check that Projects page is loaded");
        $("#container .common-flash-success").shouldBe(visible);
    }

    public ElementsCollection countOfProjectsShouldBeEqualTo(int expectedSize) {
        LoggerUtil.info("Check that count of projects is equal to " + expectedSize);
        return $$("grid ul li").filter(visible).shouldHave(CollectionCondition.size(expectedSize));
    }

    public void countOfTestCasesShouldBeEqualTo(SelenideElement targetProject, int expectedCount) {
        targetProject.shouldHave(text(expectedCount + " tests"));
    }

    public void totalCountOfProjectsIsVisible() {
        projectsCounter.shouldBe(visible);
    }

    public String getTotalCountOfTestCases() {
        return projectsCounter.getText();
    }

    public void clickCreateProject() {
        LoggerUtil.info("Click Create project ");
        $("#container  [href*='/projects/new']").shouldBe(visible).click();
        LoggerUtil.info("Check that New project page is open");
        $(".common-page-header-left h2").shouldHave(text("New project"));
    }

    public void chooseWorkspace(String workspaceName) {
        LoggerUtil.info("Choose workspace");
        $("#content-desktop #company_id").selectOptionContainingText(workspaceName);
    }

    public void clickCompanies() {
        LoggerUtil.info("Open Companies page");
        companiesTab.shouldHave(text("Companies")).click();
    }
}
