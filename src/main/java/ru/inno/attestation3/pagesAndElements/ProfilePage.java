package ru.inno.attestation3.pagesAndElements;

import com.codeborne.selenide.ElementsCollection;

import static com.codeborne.selenide.Condition.empty;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class ProfilePage {

    private LeftMenuElement menu;

    public LeftMenuElement getMenu() {
        this.menu = new LeftMenuElement();
        return menu;
    }

    public String checkEmptyTable() {
        return $(".rt-noData").getText();
    }

    public void openNextPage() {
        $(".-next").scrollIntoView(true).shouldBe(visible).click();
    }

    public void checkFilledTable(int rowsShouldBeFilled) {
        ElementsCollection rows = $$(".rt-tr-group");
        for (int i = 0; i < rowsShouldBeFilled; i++) {
            rows.get(i).shouldNotBe(empty);
        }
        for (int i = rowsShouldBeFilled; i < rows.size(); i++) {
            rows.get(i).shouldBe(empty);
        }
    }

    public void deleteAllBooks() {
        $(".text-right.button.di [type=button]").scrollIntoView(true).shouldBe(visible).click();
        $("#closeSmallModal-ok").click();
        switchTo().alert().accept();
    }

    public void logOut() {
        $(".text-right.col-md-5.col-sm-12 #submit").click();
    }
}