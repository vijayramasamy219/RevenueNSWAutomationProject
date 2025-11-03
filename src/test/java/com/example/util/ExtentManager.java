package com.example.util;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentManager {
    private static ExtentReports extent;

    public static ExtentReports getInstance() {
        if (extent == null) {
            ExtentSparkReporter reporter = new ExtentSparkReporter("target/extent-reports/ExtentReport.html");
            reporter.config().setDocumentTitle("Service NSW Automation Report");
            reporter.config().setReportName("Service NSW Automation Results");
            extent = new ExtentReports();
            extent.attachReporter(reporter);
        }
        return extent;
    }
}
