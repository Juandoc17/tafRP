package org.example;
 
import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
 
public class CRUDAPITests extends BaseTests{
 
	private String baseUrl = "https://fakerestapi.azurewebsites.net/api/v1/Activities";

	@Test
	public void testCreateNewPetJustApi() {
		String title = "string";
		String dueDate = "2023-11-29T17:52:03.204Z";
		String body = "{ \"id\": 0, \"title\": \"" + title + "\", \"dueDate\": \"" + dueDate + "\", \"completed\": true }";
		Response response = given()
			.contentType("application/json")
			.body(body)
			.when()
			.post(baseUrl);
		response.then().statusCode(200);
		response.then().body("title", equalTo(title));
		response.then().body("dueDate", equalTo(dueDate));
	}
	
	@Test
	public void testCreateNewPet() {
		driver.get(baseUrl);
		WebElement bodyInput = driver.findElement(By.id("bodyInput")); 
		String title = "string";
		String dueDate = "2023-11-29T17:52:03.204Z";
		String body = "{ \"id\": 0, \"title\": \"" + title + "\", \"dueDate\": \"" + dueDate + "\", \"completed\": true }";
		bodyInput.sendKeys(body);
		WebElement submitButton = driver.findElement(By.id("submitButton")); 
		submitButton.click();
		wait.until(ExpectedConditions.urlContains("/Activities"));
		WebElement titleElement = driver.findElement(By.xpath("//div[@class='title']"));
		Assert.assertEquals(title, titleElement.getText());
		WebElement dueDateElement = driver.findElement(By.xpath("//div[@class='dueDate']"));
		Assert.assertEquals(dueDate, dueDateElement.getText());
	}
	
	@Test
	public void testReadNotNullContent() {
		driver.get(baseUrl);
		WebElement resultElement = driver.findElement(By.id("result"));
		Assert.assertNotNull("Result is null", resultElement.getText());
	}
	

    @Test
    public void testReadAll() {
        Response response = given()
            .contentType("application/json")
            .when()
            .get(baseUrl);
        response.then().statusCode(200);
    }

    @Test
    public void testReadById() {
        int id = 1; 
        Response response = given()
            .contentType("application/json")
            .when()
            .get(baseUrl + "/" + id);
        response.then().statusCode(200);
    }
 
    @Test
    public void testUpdate() {
        int id = 1;
        String body = "{ \"id\": 0, \"title\": \"new title\", \"dueDate\": \"2023-11-29T17:52:03.204Z\", \"completed\": false }";
        Response response = given()
            .contentType("application/json")
            .body(body)
            .when()
            .put(baseUrl + "/" + id);
        response.then().statusCode(200);
    }

    @Test
    public void testDelete() {
        int id = 1;
        Response response = given()
            .contentType("application/json")
            .when()
            .delete(baseUrl + "/" + id);
        response.then().statusCode(200);
    }
}