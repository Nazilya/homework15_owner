package guru.qa.tests;

import com.codeborne.selenide.Condition;
import guru.qa.BaseTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Configuration.baseUrl;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;

public class SelenideTests extends BaseTest {

    private String selenideUrl = "/selenide/selenide",
            searchRepo = "selenide",
            section = "Wikis",
            page = "SoftAssertions",
            codeExamplesTitle = "Using JUnit5 extend test class:";

    @DisplayName("Проверка наличия примера кода для JUnit5 - вариант 1")
    @Test
    public void searchSoftAssertionsCodeExampleInSelenideRepoTest1() {

        step("сделать поиск в github: " + searchRepo, () -> {
            $("[data-test-selector=nav-search-input]").setValue(searchRepo).pressEnter();
        });

        step("перейти в раздел: " + section, () -> {
            $$("nav a").filter(visible).findBy(text(section)).click();
            //$x("//nav[@class='menu border d-none d-md-block']//a[text()='Wikis']").click();
        });

        step("проверить, что присутствует страница: " + page, () -> {
            $$("#wiki_search_results a").filter(visible).findBy(text(page)).shouldHave(text(page));
        });

        step("перейти на страницу: " + page, () -> {
            $$("#wiki_search_results a").filter(visible).findBy(text(page)).click();
            //$x("//div[@id='wiki_search_results']//a[.='SoftAssertions']").click();
        });

        step("проверить наличие примера кода для JUnit5", () -> {
            $$("ol li").filter(visible)
                    .findBy(text("JUnit5 extension")).should(Condition.exist);
            $$("ol li").filter(visible)
                    .findBy(text(codeExamplesTitle)).should(Condition.exist);
        });
    }

    @DisplayName("Проверка наличия примера кода для JUnit5 - вариант 2")
    @Test
    public void searchSoftAssertionsCodeExampleInSelenideRepoTest2() {
        step("открыть страницу: " + baseUrl + selenideUrl, () -> {
            open(selenideUrl);
        });

        step("перейти в раздел: " + section, () -> {
            $("#wiki-tab").click();
        });

        step("проверить, что присутствует страница: " + page, () -> {
            $$("#wiki-body a").filter(visible).findBy(text("Soft assertions")).shouldHave(visible);
        });

        step("перейти на страницу: " + page, () -> {
            $(".wiki-more-pages-link button").click();
            $$("#wiki-pages-box li").filter(visible).findBy(text(page)).click();
            //$x("//div[@id='wiki-pages-box']//a[.='SoftAssertions']").click();
        });

        step("проверить наличие примера кода для JUnit5", () -> {
            $$("ol li").filter(visible)
                    .findBy(text("JUnit5 extension")).should(Condition.exist);
            $$(".markdown-body h4").filter(visible)
                    .findBy(text(codeExamplesTitle)).should(Condition.exist);
        });
    }

}

