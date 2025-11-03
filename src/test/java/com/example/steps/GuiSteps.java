package com.example.steps;

import com.example.pages.StampDutyPage;
import com.example.util.DriverFactory;
import io.cucumber.java.en.*;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@Slf4j
public class GuiSteps {
    private StampDutyPage page;

    @Given("I am on the stamp duty calculator page")
    public void open_page() {
        WebDriver driver = DriverFactory.getDriver();
        page = new StampDutyPage(driver);
        page.openMainPage();
    }

    @When("I click on the \"Check online\" button")
    public void click_check_online() throws InterruptedException {
        log.info("Click Check Online.....");
        page.clickCheckOnline();
        Thread.sleep(3000);
    }

    @When("I select \"{word}\" for a new vehicle")
    public void select_new_vehicle(String option) {
        log.info("Select vehicle registration as {}", option);
        page.selectNewVehicle(option.equalsIgnoreCase("Yes"));
    }

    @When("I enter {int} as vehicle amount")
    public void enter_amount(int amount) {
        log.info("Enter Vehicle Amount {}", amount);
        page.enterVehicleValue(String.valueOf(amount));
    }

    @When("I click on the \"Calculate\" button")
    public void click_calculate() {
        log.info("Click on Calculate");
        page.clickCalculate();
    }

    @Then("I verify the calculation popup displays the expected vehicle amount {double} and registration {string}")
    public void verify_popup(double expectedPurchasePrice, String expectedRegistrationStatus) throws InterruptedException {
        log.info("Navigating to Popup......");
        Thread.sleep(3000);
        String expectedDutyPayable = String.format("%.2f", getDutyPayableAmount(expectedPurchasePrice));
        log.info("Expected Duty Payable {}", expectedDutyPayable);
        String actualRegistrationStatus = page.getRegistrationStatus();
        String actualPurchasePrice = page.getPurchasePrice();
        String actualDutyPayable = page.getDutyPayable();
        assertThat(actualRegistrationStatus, is(expectedRegistrationStatus));
        assertThat(actualPurchasePrice.replace(",", ""), is("$" + String.format("%.2f", expectedPurchasePrice)));
        assertThat(actualDutyPayable.replace(",", ""), is("$" + expectedDutyPayable));
    }

    private double getDutyPayableAmount(double purchasePrice) {
        if (purchasePrice <= 45000) {
            return purchasePrice * 0.03;
        } else if (purchasePrice <= 60000) {
            return 1350 + (purchasePrice - 45000) * 0.05;
        } else {
            return 2100 + (purchasePrice - 60000) * 0.05;
        }
    }

}
