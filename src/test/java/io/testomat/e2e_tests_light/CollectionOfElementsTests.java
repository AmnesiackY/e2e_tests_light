package io.testomat.e2e_tests_light;

import com.codeborne.selenide.ClipboardConditions;
import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.LocalStorageConditions;
import com.codeborne.selenide.Selectors;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import static com.codeborne.selenide.Selenide.$$;

public class CollectionOfElementsTests extends BaseTest {

    @Test
    @DisplayName("find all product experiments")
    void findAllProductExperiments() {

        var labelCountOfTests = $$("ul li p")
                .shouldHave(CollectionCondition.sizeGreaterThan(0));
        for (SelenideElement labelCountOfTest : labelCountOfTests) {
            labelCountOfTest.shouldHave(Condition.text("14 tests").or(Condition.text("0 tests").or(Condition.text("2 tests"))));
        }

        $$(Selectors.byText("2 tests")).shouldHave(CollectionCondition.size(1));
        $$(Selectors.byText("0 tests")).shouldHave(CollectionCondition.size(102));

        labelCountOfTests.filter(Condition.text("2 tests")).shouldHave(CollectionCondition.size(1));
        labelCountOfTests.filter(Condition.text("0 tests")).shouldHave(CollectionCondition.size(102));
    }

    @Test
    @DisplayName("clipboard")
    void clipboard() {
        Selenide.clipboard().setText("olo");
        Selenide.clipboard().shouldHave(ClipboardConditions.content("olo"));

        Selenide.localStorage().shouldHave(LocalStorageConditions.itemWithValue("name", "target name"));

    }

    public static void main(String[] args) {
        List<String> olo1 = Arrays.asList("Olo1", "Olo2", "Olo3");
        System.out.println("olo1 = " + olo1);
        olo1.forEach(System.out::println);

        Integer[] sakdfa = {1, 2, 3, 4, 5};

        System.out.println("sakdfa = " + sakdfa.toString());
        System.out.println("sakdfa = " + Arrays.toString(sakdfa));

        for (int i = 0; i < olo1.size(); i++) {
            System.out.println("sakdfa[i] = " + sakdfa[i]);
        }

        for (int i : sakdfa) {
            System.out.println("sakdfa[i] = " + i);
        }

        String[] olo = {"Olo1", "Olo2", "Olo3"};

        System.out.println("olo = " + olo);

        System.out.println("Arrays.toString(olo) = " + Arrays.toString(olo));
    }
}
