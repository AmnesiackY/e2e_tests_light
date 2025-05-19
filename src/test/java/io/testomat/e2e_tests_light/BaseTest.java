package io.testomat.e2e_tests_light;

import com.codeborne.selenide.Configuration;
import io.github.cdimascio.dotenv.Dotenv;
import io.testomat.e2e_tests_light.web.pages.ProjectsPage;
import io.testomat.e2e_tests_light.web.pages.SignInPage;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.platform.commons.logging.Logger;
import org.junit.platform.commons.logging.LoggerFactory;

public class BaseTest {

    static Dotenv env = Dotenv.load();

    static {
        Configuration.baseUrl = env.get("BASE_URL");
    }

    protected final Logger logger = LoggerFactory.getLogger(getClass());

    private static final String baseUrl = env.get("BASE_URL");
    private static final String userEmail = env.get("USERNAME");
    private static final String userPassword = env.get("PASSWORD");
    private static final SignInPage signInPage = new SignInPage();
    private static final ProjectsPage projectsPage = new ProjectsPage();

    @BeforeAll
    public static void openTestomatAndLogin() {
        signInPage.open();
        signInPage.loginUser(userEmail, userPassword);
        projectsPage.signInSuccess();
    }

    @BeforeEach
    public void openProjectsPage() {
        projectsPage.open();
        projectsPage.isLoaded();
    }
}
