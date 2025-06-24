package io.testomat.e2e_tests_light;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.junit5.TextReportExtension;
import io.github.cdimascio.dotenv.Dotenv;
import io.testomat.e2e_tests_light.common.Application;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.commons.logging.Logger;
import org.junit.platform.commons.logging.LoggerFactory;

@ExtendWith({TextReportExtension.class})
public class BaseTest {

    protected static Dotenv env = Dotenv.load();
    protected static final String baseUrl = env.get("BASE_URL");
    protected static final String username = env.get("USERNAME");
    protected static final String password = env.get("PASSWORD");
    protected final String workspaceName = "QA Club Lviv";
    protected final String targetProjectName = "manufacture light";
    protected final Logger logger = LoggerFactory.getLogger(getClass());

    protected static Application app = new Application();

    @BeforeAll
    public static void openTestomatAndLogin() {
        app.signInPage.open();
        app.signInPage.loginUser(username, password);
        app.projectsPage.signInSuccess();
    }

    @BeforeEach
    public void openProjectsPage() {
        app.projectsPage.open();
        app.projectsPage.isLoaded();
    }

    static {
        Configuration.baseUrl = baseUrl;
        Configuration.headless = true;
        Configuration.timeout = 10000;
    }
}
