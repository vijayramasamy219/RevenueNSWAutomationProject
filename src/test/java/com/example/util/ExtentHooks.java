package com.example.util;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import io.cucumber.java.*;

public class ExtentHooks {
    private static ExtentReports extent = ExtentManager.getInstance();
    private static ThreadLocal<ExtentTest> scenarioTest = new ThreadLocal<>();

    @Before
    public void beforeScenario(Scenario scenario) {
        ExtentTest test = extent.createTest(scenario.getName());
        scenarioTest.set(test);
    }

    @After
    public void afterScenario(Scenario scenario) {
        if (scenario.isFailed()) {
            String screenshot = DriverFactory.takeScreenshot("failure_") ;
            if (screenshot != null) {
                scenarioTest.get().fail("Scenario failed", MediaEntityBuilder.createScreenCaptureFromPath(screenshot).build());
            } else {
                scenarioTest.get().fail("Scenario failed (no screenshot)");    
            }
        } else {
            scenarioTest.get().pass("Scenario passed");
        }
        extent.flush();
        DriverFactory.quitDriver();
    }
}
