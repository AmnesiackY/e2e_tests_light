package io.testomat.e2e_tests_light;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.testomat.e2e_tests_light.utils.StringParsers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;


import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static io.testomat.e2e_tests_light.utils.StringParsers.parseIntegerFromString;

public class ProjectPageTests extends BaseTest {

    private final String workspaceName = "QA Club Lviv";
    private final String targetProjectName = "Manufacture light";

    @Test()
    public void userCanFindAndOpenProjectWithTests() {
        chooseWorkspace(workspaceName);

        searchProject(targetProjectName);

        selectProject(targetProjectName);

        waitForProjectPageIsLoaded(targetProjectName);
    }

    @Test()
    @Disabled("Cannot be executed without workspace administrator rights")
    public void exampleTestWithTotalCases() {
        var expectedAmountOfProjects = 1;
        var expectedAmountOfTestCases = 0;
        var expectedTotalAmountOfTestCases = 100;
        searchProject(targetProjectName);

        SelenideElement targetProject = countOfProjectsShouldBeEqualTo(expectedAmountOfProjects).first();

        countOfTestCasesShouldBeEqualTo(targetProject, expectedAmountOfTestCases);

        totalCountOfTestCasesGreaterThan(expectedTotalAmountOfTestCases);
    }

    @Test()
    public void userCantCreateNewProjectIfLimitOfProjectsReached() {
        var projectTitle = "Test project";

        clickCreateProject();

        enterProjectTitle(projectTitle);

        clickCreateButton();

        checkThatLimitBannerAlertIsVisible();
    }

    @Test
    public void userCanCheckHisRoleAndCompany() {
        var companyName = "QA Club Lviv";
        var roleName = "QA";

        openCompanies();

        checkThatUserAssignedToCompany(workspaceName);

        checkThatUserAssignedAs(roleName);
    }

    @Test
    public void exampleParseDouble() {
        var text = "15.5 coverage";
        double actualDouble = StringParsers.parseDoubleFromString(text);

        Assertions.assertEquals(15.5, actualDouble);
    }

    @Test
    public void exampleParseInteger() {
        var text = "0 tests";
        int actualInteger = parseIntegerFromString(text);

        Assertions.assertEquals(0, actualInteger);
    }

    @Test
    public void exampleParseBoolean() {
        var text = "true";
        Boolean actualBoolean = Boolean.parseBoolean(text);

        Assertions.assertEquals(true, actualBoolean);
    }

    private void checkThatUserAssignedToCompany(String specificCompanyName) {
        logger.info(() -> String.format("Check that User assigned to %s company", specificCompanyName));
        var columns = parseCompaniesTable(specificCompanyName);

        var actualValue = columns.get(0).$("a").shouldHave(Condition.exactText(specificCompanyName)).getText();

        Assertions.assertEquals(actualValue, specificCompanyName);
    }

    private void checkThatUserAssignedAs(String roleName) {
        logger.info(() -> String.format("Check that User assigned as %s", roleName));
        var columns = parseCompaniesTable(roleName);

        var actualValue = columns.get(2).$("span").shouldHave(Condition.exactText("QA")).getText();

        Assertions.assertEquals(actualValue, roleName);
    }

    private ElementsCollection parseCompaniesTable(String specificCompanyName) {
        SelenideElement specificRow = $$("tbody tr").findBy(Condition.text(specificCompanyName));
        return specificRow.$$("td");
    }

    private void openCompanies() {
        logger.info(() -> "Open Companies page");
        $("[href='/companies']").shouldHave(text("Companies")).click();
        logger.info(() -> "Check that Companies page is open");
        $(".auth-main-container h2").shouldHave(text("Companies"));
    }


    private void totalCountOfTestCasesGreaterThan(int expectedTotalCount) {
        String totalProjects = $("#container kbd").getText();
        Integer actualCountOfTotalTests = parseIntegerFromString(totalProjects);
        Assertions.assertTrue(actualCountOfTotalTests > expectedTotalCount);
    }

    private void countOfTestCasesShouldBeEqualTo(SelenideElement targetProject, int expectedCount) {
        String countOfTests = targetProject.getText();
        Integer actualCountOfTests = parseIntegerFromString(countOfTests);
        Assertions.assertEquals(expectedCount, actualCountOfTests);
    }

    private ElementsCollection countOfProjectsShouldBeEqualTo(int expectedSize) {
        return $$("grid ul li").filter(visible).shouldHave(CollectionCondition.size(expectedSize));
    }

    //TODO Create awaiters for PreloadderSpinner

    private void waitForProjectPageIsLoaded(String projectName) {
        logger.info(() -> "Check that project is open");
        $(".first h2").shouldHave(text(projectName));
        $(".first [href*='/readme']").shouldHave(text("Readme"));
    }

    private void selectProject(String projectName) {
        logger.info(() -> "Select project");
        $(byText(projectName)).click();
    }

    private void searchProject(String projectName) {
        logger.info(() -> "Search project ");
        $("#content-desktop #search").setValue(projectName);
    }

    private void clickCreateProject() {
        logger.info(() -> "Click Create project ");
        $("#container  [href*='/projects/new']").shouldBe(visible).click();
        logger.info(() -> "Check that New project page is open");
        $(".common-page-header-left h2").shouldHave(text("New project"));
    }

    public void enterProjectTitle(String projectTitle) {
        logger.info(() -> "Enter project title");
        $("#project_title").setValue(projectTitle);
    }

    public void clickCreateButton() {
        logger.info(() -> "Click Create button");
        $("#project-create-btn [type='submit']").click();
    }

    public void checkThatLimitBannerAlertIsVisible() {
        logger.info(() -> "Limit banner alert is visible");
        var isDisplayedLimitBanner = $(".auth-main-container .common-flash-alert-right ").shouldBe(visible).isDisplayed();
        Assertions.assertTrue(isDisplayedLimitBanner);
    }


    private void chooseWorkspace(String workspaceName) {
        logger.info(() -> "Choose workspace");
        $("#content-desktop #company_id").selectOptionContainingText(workspaceName);
    }

}
