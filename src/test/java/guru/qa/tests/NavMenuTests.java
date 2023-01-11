package guru.qa.tests;

import guru.qa.BaseTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Configuration.baseUrl;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;

public class NavMenuTests extends BaseTest {
    private String navMenuElement = "Solutions",
            pagesTitle = "Build like the best";
    @DisplayName("Проверка заголовка загруженной страницы")
    @Test
    void githubHoverMenuTest() {

        step("навести мышь на пункт меню Solutions", () -> {
            $$(".HeaderMenu-link").filter(visible).findBy(text(navMenuElement)).hover();
        });

        step("в выпадающем меню Solutions кликнуть на Enterprise", () -> {
            $x("//a[.='Enterprise']").click();
        });

        step("проверить заголовок загруженной страницы", () -> {
            $(".col-9-max h1").shouldHave(text(pagesTitle));
        });

    }
}