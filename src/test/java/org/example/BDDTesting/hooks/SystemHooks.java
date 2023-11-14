package org.example.BDDTesting.hooks;

import org.junit.After;
import org.junit.Before;

import io.cucumber.java.Scenario;


public class SystemHooks {
	@Before
	public void BeforeDisplayMessage(Scenario sc) {
		System.out.println("Before" + sc.getName());
	}

	@After
	public void AfterDisplayMessage(Scenario sc) {
		System.out.println("After" + sc.getName());
	}
}
