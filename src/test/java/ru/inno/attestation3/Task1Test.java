package ru.inno.attestation3;

import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import jdk.jfr.Description;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.inno.attestation3.pagesAndElements.LoginPage;
import ru.inno.attestation3.pagesAndElements.ProfilePage;

import static io.qameta.allure.Allure.step;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Task1Test {

    @Test
    @DisplayName("Успешная авторизация")
    @Description("Проверяем успешную авторизацию")
    @Severity(SeverityLevel.BLOCKER)
    public void iCanLogin() {
        LoginPage loginPage = new LoginPage();
        ProfilePage profilePage = new ProfilePage();
        step("Открыть страницу авторизации", () -> loginPage.openLoginPage());
        step("Ввести имя пользователя", () -> loginPage.setUserName());
        step("Ввести пароль", () -> loginPage.setPassword());
        step("Нажать кнопку Логин и перейти на страницу Профиль", () -> loginPage.logIn());

        String emptyTableText = step("Проверить, что есть сообщение о пустой таблице",
                () -> profilePage.checkEmptyTable());
        String textToBe = "No rows found";
        step("Проверить текст сообщения", () -> assertEquals(textToBe, emptyTableText));
        step("Проверить, что все строки таблицы пустые", () -> profilePage.checkFilledTable(0));
        step("Разлогиниться", () -> profilePage.logOut());
    }

}