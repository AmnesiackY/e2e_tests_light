package io.testomat.e2e_tests_light.web.pages;

import com.codeborne.selenide.SelenideElement;
import io.testomat.e2e_tests_light.utils.ClipboardHelper;
import io.testomat.e2e_tests_light.utils.HugeReadmeDescription;
import org.openqa.selenium.Keys;


import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.switchTo;

public class ReadmePage {

    //Readme page elements
    private final SelenideElement header = $("h2");
    private final SelenideElement readMeButton = $(byText("Edit Readme"));

    //Pop-up elements
    private final SelenideElement modalPopUp = $("#modal-overlays iframe[src='/ember-monaco/frame.html']");
    private final SelenideElement passiveTextAreaOfModalPopUp = $(".view-lines div");
    private final SelenideElement activeTextAreaOfModalPopUp = $("textarea");
    private final SelenideElement updateButtonOfModalPopUp = $(byText("Update"));
    private final SelenideElement cancelButtonOfModalPopUp = $(byText("Cancel"));

    public ReadmePage clickOnEditReadme() {
        readMeButton.click();
        switchTo().frame(modalPopUp);
        return this;
    }

    public ReadmePage editFirstLineInEditor(String targetText) {
        passiveTextAreaOfModalPopUp.click();
        activeTextAreaOfModalPopUp.setValue(targetText);
        return this;
    }

    public ReadmePage clickOnUpdate() {
        switchTo().defaultContent();
        updateButtonOfModalPopUp.click();
        return this;
    }

    public ReadmePage clickOnCancel() {
        switchTo().defaultContent();
        cancelButtonOfModalPopUp.click();
        return this;
    }

    public ReadmePage clickCloseButton() {
        switchTo().defaultContent();
        $("button.close").click();
        return this;
    }

    public ReadmePage isLoaded() {
        header.shouldHave(text("Readme"));
        return this;
    }

    public ReadmePage revertToDefault(String expectedDefaultText) {
        passiveTextAreaOfModalPopUp.click();
        activeTextAreaOfModalPopUp.sendKeys(Keys.COMMAND, "a"); // Select all text
        ClipboardHelper.copyToClipboard(HugeReadmeDescription.readMeDescription);
        activeTextAreaOfModalPopUp.sendKeys(Keys.COMMAND, "v"); // Send text
        return this;
    }
}
