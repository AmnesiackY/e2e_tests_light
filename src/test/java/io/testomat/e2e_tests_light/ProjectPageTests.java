package io.testomat.e2e_tests_light;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.testomat.e2e_tests_light.utils.StringParsers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.logging.Logger;
import org.junit.platform.commons.logging.LoggerFactory;


import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.open;
import static io.testomat.e2e_tests_light.utils.StringParsers.parseIntegerFromString;

public class ProjectPageTests extends BaseTest{

    private static final Logger logger = LoggerFactory.getLogger(ProjectPageTests.class);

    private final String baseUrl = env.get("BASE_URL");
    private final String userEmail = env.get("USERNAME");
    private final String userPassword = env.get("PASSWORD");
    private  final String workspaceName = "QA Club Lviv";
    private final String targetProjectName = "Manufacture light";

    @Test()
    public void userCanFindAndOpenProjectWithTests() {
        open(baseUrl);

        loginUser(userEmail, userPassword);

        chooseWorkspace(workspaceName);

        searchProject(targetProjectName);

        selectProject(targetProjectName);

        waitForProjectPageIsLoaded(targetProjectName);
    }

    @Test
    public void anotherTest() {
        open(baseUrl);

        loginUser(userEmail, userPassword);

        searchProject(targetProjectName);

        SelenideElement targetProject = countOfProjectsShouldBeEqualTo(1).first();

        countOfTestCasesShouldBeEqualTo(targetProject, 0);

        totalCountOfTestCasesGreaterThan(100);
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

    //TODO Create loader-await class
    private void waitForProjectPageIsLoaded(String projectName) {
        logger.info(() -> "Check that project is open");
        $(".first h2").shouldHave(text(projectName));
        $(".first [href*='/readme']").shouldHave(text("Readme"));
    }

    //TODO Navigation classes??
    private void selectProject(String projectName) {
        logger.info(() -> "Select project");
        $(byText(projectName)).click();
    }

    private void searchProject(String projectName) {
        logger.info(() -> "Search project ");
        $("#content-desktop #search").setValue(projectName);
    }

    private void chooseWorkspace(String workspaceName) {
        logger.info(() -> "Choose workspace");
        $("#content-desktop #company_id").selectOptionContainingText(workspaceName);
    }

    //TODO Move to BaseTest
    private void loginUser(String userEmail, String userPassword) {
        logger.info(() -> "Login user");
        $("#content-desktop #user_email").setValue(userEmail);
        $("#content-desktop #user_password").setValue(userPassword);
        $("#content-desktop #user_remember_me").click();
        $("#content-desktop [name='commit']").click();
        $(".common-flash-success").shouldBe(visible);
    }

}
