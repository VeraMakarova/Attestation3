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

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static io.qameta.allure.Allure.step;

public class Task2Test {

    DemoqaClient client = new DemoqaClient();

    @Test
    @DisplayName("Добавление 6 книг")
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

        List<String> listOfBooks = step("Получить список книг", () -> client.getBooksList(6));
        step("Добавить список книг в корзину", () -> client.addBooks(listOfBooks));

        Configuration.browser = Browsers.CHROME;
        open();
        getWebDriver().manage().window().maximize();
        step("Открыть страницу авторизации", () -> loginPage.openLoginPage());
        step("Ввести имя пользователя", () -> loginPage.setUserName());
        step("Ввести пароль", () -> loginPage.setPassword());
        step("Нажать кнопку Логин и перейти на страницу Профиль", () -> loginPage.logIn());
        step("Проверить, что на первой странице отображается 5 книг", () -> profilePage.checkFilledTable(5));
        step("Открыть следующую страницу списка книг", () -> profilePage.openNextPage());
        step("Проверить, что на второй странице отображается одна книга", () -> profilePage.checkFilledTable(1));
        step("Удалить все книги из корзины", () -> profilePage.deleteAllBooks());
        step("Разлогиниться", () -> profilePage.logOut());
    }
}