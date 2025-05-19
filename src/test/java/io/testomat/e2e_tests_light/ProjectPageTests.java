package io.testomat.e2e_tests_light;

import io.testomat.e2e_tests_light.web.pages.CompaniesPage;
import io.testomat.e2e_tests_light.web.pages.CreateProjectPage;
import io.testomat.e2e_tests_light.web.pages.ProjectPage;
import io.testomat.e2e_tests_light.web.pages.ProjectsPage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;


import static io.testomat.e2e_tests_light.utils.StringParsers.parseIntegerFromString;

public class ProjectPageTests extends BaseTest {

    private final String workspaceName = "QA Club Lviv";
    private final String targetProjectName = "Manufacture light";
    private final ProjectsPage projectsPage = new ProjectsPage();
    private final ProjectPage projectPage = new ProjectPage();
    private final CompaniesPage companiesPage = new CompaniesPage();
    private final CreateProjectPage createProjectPage = new CreateProjectPage();

    @Test()
    public void userCanFindAndOpenProjectWithTests() {
        projectsPage.chooseWorkspace(workspaceName);

        projectsPage.searchForProject(targetProjectName);

        projectsPage.selectProject(targetProjectName);

        projectPage.isLoaded(targetProjectName);
    }

    @Test()
    @Disabled("Cannot be executed without workspace administrator rights")
    public void exampleTestWithTotalCases() {
        var expectedAmountOfProjects = 1;
        var expectedAmountOfTestCases = 0;
        var expectedTotalAmountOfTestCases = 100;

        projectsPage.searchForProject(targetProjectName);

        var targetProject = projectsPage.countOfProjectsShouldBeEqualTo(expectedAmountOfProjects).first();

        projectsPage.countOfTestCasesShouldBeEqualTo(targetProject, expectedAmountOfTestCases);

        projectsPage.totalCountOfProjectsIsVisible();

        var totalTestCases = projectsPage.getTotalCountOfTestCases();
        var actualTotalOfTotalTestCases = parseIntegerFromString(totalTestCases);
        Assertions.assertTrue(actualTotalOfTotalTestCases > expectedTotalAmountOfTestCases);
    }

    @Test()
    public void userCantCreateNewProjectIfLimitOfProjectsReached() {
        var projectTitle = "Test project";

        projectsPage.clickCreateProject();

        createProjectPage.enterProjectTitle(projectTitle);

        createProjectPage.clickCreateButton();

        createProjectPage.checkThatLimitBannerAlertIsVisible();
    }

    @Test
    public void userCanCheckHisRoleAndCompany() {
        var roleName = "QA";

        companiesPage.clickCompanies();

        companiesPage.checkThatUserAssignedToCompany(workspaceName);

        companiesPage.checkThatUserAssignedAs(roleName);
    }
}
