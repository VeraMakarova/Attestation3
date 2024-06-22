package ru.inno.attestation3.api;

import io.restassured.http.ContentType;
import ru.inno.attestation3.helper.ConfigHelper;

import java.util.List;

import static io.restassured.RestAssured.given;

public class DemoqaClient {
   // private String userId = ConfigHelper.getUserId();
    private String userName = ConfigHelper.getUserName();
    private String password = ConfigHelper.getPassword();
    String addBooksUrl = ConfigHelper.getURLAddBooks();

    public String getToken() {
        String creds = "{\"userName\": \"" + userName + "\",\"password\": \"" + password + "\"}";
        String token = given().log().all()
                .body(creds)
                .contentType(ContentType.JSON)
                .post("https://demoqa.com/Account/v1/GenerateToken")
                .then().log().all()
                .extract().path("token");
        return token;
    }


    public List<String> getBooksList(int numberOfBooks) {
        List<String> booksList = given().log().all()
                .get("https://demoqa.com/BookStore/v1/Books")
                .then().log().all()
                .contentType(ContentType.JSON)
                .extract().path("books.isbn");
        List<String> shortBooksList = booksList.stream().limit(numberOfBooks).toList();
        return shortBooksList;

    }

    public void addBook(String userId, String token, String isbn) {
        String body = "{\n" +
                "  \"userId\": \"" + userId + "\",\n" +
                "  \"collectionOfIsbns\": [\n" +
                "    {\n" +
                "      \"isbn\": \"" + isbn + "\"\n" +
                "    }\n" +
                "  ]\n" +
                "}";
        given().log().all()
                .body(body)
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + token)
                .when().post(addBooksUrl)
                .then()
                .statusCode(201);
    }

    public void addBooks(List<String> listOfBooks) {

        String[] isbn = new String[listOfBooks.size()];
        for (int i = 0; i < listOfBooks.size(); i++) {
            isbn[i] = listOfBooks.get(i);
        }
        String userId = ConfigHelper.getUserId();
        String token = getToken();
        for (int i = 0; i < isbn.length; i++) {
            addBook(userId, token, isbn[i]);
        }
    }
}