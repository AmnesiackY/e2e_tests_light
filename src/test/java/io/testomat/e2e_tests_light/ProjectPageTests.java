package io.testomat.e2e_tests_light;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;


import static io.testomat.e2e_tests_light.utils.StringParsers.parseIntegerFromString;

public class ProjectPageTests extends BaseTest {

    @Test()
    public void userCanFindAndOpenProjectWithTests() {
        app.projectsPage.chooseWorkspace(workspaceName);

        app.projectsPage.searchForProject(targetProjectName);

        app.projectsPage.selectProject(targetProjectName);

        app.projectPage.isLoaded(targetProjectName);
    }

    @Test()
    @Disabled("Cannot be executed without workspace administrator rights")
    public void exampleTestWithTotalCases() {
        var expectedAmountOfProjects = 1;
        var expectedAmountOfTestCases = 0;
        var expectedTotalAmountOfTestCases = 100;

        app.projectsPage.searchForProject(targetProjectName);

        var targetProject = app.projectsPage.countOfProjectsShouldBeEqualTo(expectedAmountOfProjects).first();

        app.projectsPage.countOfTestCasesShouldBeEqualTo(targetProject, expectedAmountOfTestCases);

        app.projectsPage.totalCountOfProjectsIsVisible();

        var totalTestCases = app.projectsPage.getTotalCountOfTestCases();
        var actualTotalOfTotalTestCases = parseIntegerFromString(totalTestCases);
        Assertions.assertTrue(actualTotalOfTotalTestCases > expectedTotalAmountOfTestCases);
    }

    @Test()
    public void userCantCreateNewProjectIfLimitOfProjectsReached() {
        var projectTitle = "Test project";

        app.projectsPage.clickCreateProject();

        app.createProjectPage.enterProjectTitle(projectTitle);

        app.createProjectPage.clickCreateButton();

        app.createProjectPage.checkThatLimitBannerAlertIsVisible();
    }

    @Test
    public void userCanCheckHisRoleAndCompany() {
        var roleName = "QA";

        app.projectsPage.clickCompanies();

        app.companiesPage.isLoaded();

        app.companiesPage.checkThatUserAssignedToCompany(workspaceName);

        app.companiesPage.checkThatUserAssignedAs(roleName);
    }
}
