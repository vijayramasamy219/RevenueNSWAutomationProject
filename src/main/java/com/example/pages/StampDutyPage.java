package com.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class StampDutyPage {
    WebDriver driver;

    By checkOnlineLink = By.xpath("//a[contains(@class,'button--primary') and normalize-space(text())='Check online']");
    By newVehicleYesRadio = By.xpath("//div[@id='passenger']//label[normalize-space(text())='Yes']");
    By newVehicleNoRadio = By.xpath("//input[@id='passenger_N']");
    By vehicleValueInput = By.id("purchasePrice");
    By calculateButton = By.xpath("//button[normalize-space(text())='Calculate']");
    By popupContent = By.xpath("//div[contains(@class,'confirm-modal') and contains(@class,'show')]");
    By registrationStatus = By.xpath(".//td[normalize-space(text())='Is this registration for a passenger vehicle?']/following-sibling::td[1]");
    By purchasePrice = By.xpath(".//td[normalize-space(text())='Purchase price or value']/following-sibling::td[1]");
    By dutyPayable = By.xpath(".//td[normalize-space(text())='Duty payable']/following-sibling::td[1]");

    public StampDutyPage(WebDriver driver) {
        this.driver = driver;
    }

    public void openMainPage() {
        driver.get("https://www.service.nsw.gov.au/transaction/check-motor-vehicle-stamp-duty");
    }

    public void clickCheckOnline() {
        driver.findElement(checkOnlineLink).click();
    }

    public void selectNewVehicle(boolean isNew) {
        if (isNew) driver.findElement(newVehicleYesRadio).click();
        else driver.findElement(newVehicleNoRadio).click();
    }

    public void enterVehicleValue(String value) {
        WebElement input = driver.findElement(vehicleValueInput);
        input.clear();
        input.sendKeys(value);
    }

    public void clickCalculate() {
        driver.findElement(calculateButton).click();
    }

    public String getPopupText() {
        return driver.findElement(popupContent).getText();
    }

    public String getRegistrationStatus() {
        return driver.findElement(registrationStatus).getText();
    }

    public String getPurchasePrice() {
        return driver.findElement(purchasePrice).getText();
    }

    public String getDutyPayable() {
        return driver.findElement(dutyPayable).getText();
    }
}
