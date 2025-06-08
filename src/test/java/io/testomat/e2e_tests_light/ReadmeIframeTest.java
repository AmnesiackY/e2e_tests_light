package io.testomat.e2e_tests_light;

import com.codeborne.selenide.junit5.TextReportExtension;
import io.testomat.e2e_tests_light.utils.HugeReadmeDescription;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith({TextReportExtension.class})
public class ReadmeIframeTest extends BaseTest {

    @Test
    @DisplayName("Update readme text in iframe")
    void updateReadmeTextInIframe() {
        app.projectsPage.isLoaded()
                .searchForProject(targetProjectName)
                .selectProject(targetProjectName);

        app.projectPage.openReadme()
                .clickOnEdit();

        app.readmePage.isLoaded()
                .clickOnEditReadme()
                .editFirstLineInEditor("tomato")
                .clickOnUpdate();

        app.projectPage.openReadme().checkOnExactTextInReadme("tomato");

        //post-condition
        app.projectPage.clickOnEdit();
        app.readmePage.clickOnEditReadme()
                .revertToDefault(HugeReadmeDescription.readMeDescription)
                .clickOnUpdate();
    }

}
