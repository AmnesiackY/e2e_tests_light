package io.testomat.e2e_tests_light.web.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.testomat.e2e_tests_light.utils.LoggerUtil;


import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class CompaniesPage {

    private final SelenideElement headerName = $(".auth-main-container h2");
    private final ElementsCollection infoTable = $$("tbody tr");

    public void isLoaded() {
        LoggerUtil.info("Check that Companies page is open");
        headerName.shouldHave(text("Companies"));
    }

    public ElementsCollection returnRowWithSpecificCompanyName(String specificCompanyName) {
        return infoTable.findBy(Condition.text(specificCompanyName)).$$("td");
    }

    public void checkThatUserAssignedToCompany(String specificCompanyName) {
        LoggerUtil.info(String.format("Check that User assigned to %s company", specificCompanyName));
        var columns = returnRowWithSpecificCompanyName(specificCompanyName);
        columns.get(0).$("a").shouldHave(Condition.exactText(specificCompanyName));
    }

    public void checkThatUserAssignedAs(String roleName) {
        LoggerUtil.info(String.format("Check that User assigned as %s", roleName));
        var columns = returnRowWithSpecificCompanyName(roleName);
        columns.get(2).$("span").shouldHave(Condition.exactText("QA"));
    }
}
