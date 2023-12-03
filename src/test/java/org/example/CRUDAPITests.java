package org.example;

import org.junit.*;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.http.ContentType;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import org.example.models.*;

public class CRUDAPITests extends BaseTests {

    private String baseUrl = "https://fakerestapi.azurewebsites.net/api/v1/Activities";

    @Before
    public void setUp() {
        RestAssured.baseURI = baseUrl;
    }

    @Test
    public void testCreateNewActivityApi() {
        String title = "string";
        String dueDate = "2023-11-29T17:52:03.204Z";

        Activity activity = new Activity();
        activity.setId(0);
        activity.setTitle(title);
        activity.setDueDate(dueDate);
        activity.setCompleted(true);

        Response response = given()
                .contentType(ContentType.JSON)
                .body(activity)
                .when()
                .post(baseUrl);

        response.then().assertThat().statusCode(200)
                .and().body("title", equalTo(title))
                .and().body("dueDate", equalTo(dueDate));
    }

    @Test
    public void testReadAll() {
        int expectedNumberOfRecords = 30;

        Response response = given()
                .contentType(ContentType.JSON)
                .when()
                .get(baseUrl);

        response.then().assertThat().statusCode(200)
                .and().body("size()", equalTo(expectedNumberOfRecords));
    }

    @Test
    public void testReadById() {
        int id = 1;
        Response response = given()
                .contentType(ContentType.JSON)
                .pathParam("id", id)
                .when()
                .get(baseUrl + "/{id}");
        response.then().assertThat().statusCode(200);
    }

    @Test
    public void testUpdate() {
        int id = 1;
        String title = "new title";
        String dueDate = "2023-11-29T17:52:03.204Z";
        boolean completed = false;

        Activity activity = new Activity();
        activity.setId(id);
        activity.setTitle(title);
        activity.setDueDate(dueDate);
        activity.setCompleted(completed);

        Response response = given()
                .contentType(ContentType.JSON)
                .body(activity)
                .pathParam("id", id)
                .when()
                .put(baseUrl + "/{id}");

        response.then().assertThat().statusCode(200)
                .and().body("title", equalTo(title))
                .and().body("dueDate", equalTo(dueDate))
                .and().body("completed", equalTo(completed));
    }

    @Test
    public void testDelete() {
        int id = 1;
        Response response = given()
                .contentType(ContentType.JSON)
                .pathParam("id", id)
                .when()
                .delete(baseUrl + "/{id}");
        response.then().assertThat().statusCode(200);
        response = given()
                .contentType(ContentType.JSON)
                .pathParam("id", id)
                .when()
                .get(baseUrl + "/{id}");
        response.then().assertThat().statusCode(404);
    }

    @Test
    public void testCreateNewActivityApi_Negative() {
        String title = "string";
        String dueDate = "2023-11-29T17:52:03.204Z";

        Activity activity = new Activity();
        activity.setId(0);
        activity.setTitle(title);
        activity.setDueDate(dueDate);
        activity.setCompleted(true);

        Response response = given()
                .contentType(ContentType.JSON)
                .body(activity)
                .when()
                .post(baseUrl + "/invalid");

        response.then().assertThat().statusCode(404);
    }

    @Test
    public void testReadAll_Negative() {
        Response response = given()
                .contentType(ContentType.JSON)
                .when()
                .get(baseUrl + "/invalid");

        response.then().assertThat().statusCode(404);
    }

    @Test
    public void testReadById_Negative() {
        int id = 9999;
        Response response = given()
                .contentType(ContentType.JSON)
                .pathParam("id", id)
                .when()
                .get(baseUrl + "/{id}");
        response.then().assertThat().statusCode(404);
    }

    @Test
    public void testUpdate_Negative() {
        int id = 9999;
        String title = "new title";
        String dueDate = "2023-11-29T17:52:03.204Z";
        boolean completed = false;

        Activity activity = new Activity();
        activity.setId(id);
        activity.setTitle(title);
        activity.setDueDate(dueDate);
        activity.setCompleted(completed);

        Response response = given()
                .contentType(ContentType.JSON)
                .body(activity)
                .pathParam("id", id)
                .when()
                .put(baseUrl + "/{id}");

        response.then().assertThat().statusCode(404);
    }

}
