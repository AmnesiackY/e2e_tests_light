package io.testomat.e2e_tests_light.web.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.testomat.e2e_tests_light.utils.LoggerUtil;


import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class CompaniesPage {

    public void clickCompanies() {
        LoggerUtil.info("Open Companies page");
        $("[href='/companies']").shouldHave(text("Companies")).click();
        LoggerUtil.info("Check that Companies page is open");
        $(".auth-main-container h2").shouldHave(text("Companies"));
    }

    public ElementsCollection parseCompaniesTable(String specificCompanyName) {
        SelenideElement specificRow = $$("tbody tr").findBy(Condition.text(specificCompanyName));
        return specificRow.$$("td");
    }

    public void checkThatUserAssignedToCompany(String specificCompanyName) {
        LoggerUtil.info(String.format("Check that User assigned to %s company", specificCompanyName));
        var columns = parseCompaniesTable(specificCompanyName);

        columns.get(0).$("a").shouldHave(Condition.exactText(specificCompanyName));
    }

    public void checkThatUserAssignedAs(String roleName) {
        LoggerUtil.info(String.format("Check that User assigned as %s", roleName));
        var columns = parseCompaniesTable(roleName);

        columns.get(2).$("span").shouldHave(Condition.exactText("QA"));
    }
}
