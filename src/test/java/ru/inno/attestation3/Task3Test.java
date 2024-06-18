package ru.inno.attestation3;

import com.codeborne.selenide.Browsers;
import com.codeborne.selenide.Configuration;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import jdk.jfr.Description;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.inno.attestation3.api.DemoqaClient;
import ru.inno.attestation3.pagesAndElements.LoginPage;
import ru.inno.attestation3.pagesAndElements.ProfilePage;

import java.util.List;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static io.qameta.allure.Allure.step;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Task3Test {
    DemoqaClient client = new DemoqaClient();

    @Test
    @DisplayName("Добавление 2 книг")
    @Description("Проверяем добавление 6 книг в коллекцию")
    @Severity(SeverityLevel.CRITICAL)
    public void iCanAddBooks() {
        Configuration.browser = Browsers.CHROME;
        open();
        getWebDriver().manage().window().maximize();
        LoginPage loginPage = new LoginPage();
        ProfilePage profilePage = new ProfilePage();
        step("Открыть страницу авторизации", () -> loginPage.openLoginPage());
        step("Ввести имя пользователя", () -> loginPage.setUserName());
        step("Ввести пароль", () -> loginPage.setPassword());
        step("Нажать кнопку Логин и перейти на страницу Профиль", () -> loginPage.logIn());
        step("Нажать на кнопку Books Store в левом меню", () -> profilePage.getMenu().openBooksPage());

        List<String> listOfBooks = step("Получить список книг", () -> client.getBooksList(2));
        step("Добавить список книг в корзину", () -> client.addBooks(listOfBooks));

        Configuration.browser = Browsers.CHROME;
        open();
        getWebDriver().manage().window().maximize();
        step("Открыть страницу авторизации", () -> loginPage.openLoginPage());
        step("Ввести имя пользователя", () -> loginPage.setUserName());
        step("Ввести пароль", () -> loginPage.setPassword());
        step("Нажать кнопку Логин и перейти на страницу Профиль", () -> loginPage.logIn());

        step("Проверить, что на первой странице отображается 2 книги",
                () -> profilePage.checkFilledTable(2));
        step("Удалить все книги из корзины", () -> profilePage.deleteAllBooks());
        String emptyTableText = step("Проверить, что есть сообщение о пустой таблице",
                () -> profilePage.checkEmptyTable());
        String textToBe = "No rows found";
        step("Проверить текст сообщения", () -> assertEquals(textToBe, emptyTableText));
        step("Проверить, что все строки таблицы пустые", () -> profilePage.checkFilledTable(0));
        step("Разлогиниться", () ->profilePage.logOut());
    }
}