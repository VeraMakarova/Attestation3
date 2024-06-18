package ru.inno.attestation3;

import ru.inno.attestation3.helper.ConfigHelper;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class LoginPage {

    private LeftMenuElement menu;

    public LeftMenuElement getMenu() {
        this.menu = new LeftMenuElement();
        return menu;
    }

    public void openLoginPage() {
        String loginPageURL = ConfigHelper.getLoginPageUrl();
        open(loginPageURL);
    }

    public void setUserName() {
        String userName = ConfigHelper.getUserName();
        $("#userName").setValue(userName);
    }

    public void setPassword() {
        String password = ConfigHelper.getPassword();
        $("#password").setValue(password);
    }

    public void logIn() {
        $("#login").pressEnter();
    }
}