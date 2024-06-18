package ru.inno.attestation3.pagesAndElements;

import org.openqa.selenium.WebElement;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class LeftMenuElement {
    private final WebElement menu;

    public LeftMenuElement() {
        this.menu = $(".accordion");
    }

    public void openBooksPage() {
        $(".element-group:last-child #item-2").scrollIntoView(true).shouldBe(visible).click();

    }

    public void openProfilePage() {
        $(".element-group:last-child #item-3").scrollIntoView(true).shouldBe(visible).click();

    }
}