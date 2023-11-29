package org.example;
import org.junit.*;
import org.openqa.selenium.*;

public class CRUDAPITests extends BaseTests{

	//Swagger: https://fakerestapi.azurewebsites.net/index.html
	private String baseUrl = "https://fakerestapi.azurewebsites.net/api/v1/Activities";

	@Test
	public void testCreateNewPet() {
		driver.get(baseUrl + "/create");
		// Add your test code here
	}

	@Test
	public void testRead() {
		driver.get(baseUrl + "/read");
		// Add your test code here
	}

	@Test
	public void testUpdate() {
		driver.get(baseUrl + "/update");
		// Add your test code here
	}

	@Test
	public void testDelete() {
		driver.get(baseUrl + "/delete");
		// Add your test code here
	}

	@After
	public void tearDown() {
		driver.quit();
	}
}
