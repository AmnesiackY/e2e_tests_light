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

    private final SelenideElement successBanner = $("#container .common-flash-success");
    private final SelenideElement searchInput = $("#search");
    private final SelenideElement projectsCounter = $("#container kbd");
    private final SelenideElement companiesTab = $("[href='/companies']");
    private final SelenideElement workspaceDropDown = $("#content-desktop #company_id");
    private final SelenideElement createButton = $("#container  [href*='/projects/new']");
    private final ElementsCollection projectsGrid = $$("grid ul li");


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
        successBanner.shouldBe(visible);
    }

    public ElementsCollection countOfProjectsShouldBeEqualTo(int expectedSize) {
        LoggerUtil.info("Check that count of projects is equal to " + expectedSize);
        return projectsGrid.filter(visible).shouldHave(CollectionCondition.size(expectedSize));
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
        createButton.shouldBe(visible).click();
    }

    public void chooseWorkspace(String workspaceName) {
        LoggerUtil.info("Choose workspace");
        workspaceDropDown.selectOptionContainingText(workspaceName);
    }

    public void clickCompanies() {
        LoggerUtil.info("Open Companies page");
        companiesTab.shouldHave(text("Companies")).click();
    }
}
