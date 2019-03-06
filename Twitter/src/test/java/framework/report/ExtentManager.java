package framework.report;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import framework.Log;
import framework.utils.PropertyReader;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ExtentManager {
    private final static String DEFAULT_DIRECTORY = "target/";
    private final static String REPORT_DIRECTORY = PropertyReader.getPropertyOrDefault("extentReportDirectory", DEFAULT_DIRECTORY);
    private final static String REPORT_NAME = new SimpleDateFormat("yyyy-MM-dd hh-mm-ss-ms").format(new Date()) + ".html";
    private static ExtentReports extentReports = null;

    private static ExtentReports createReporter(String filePath) {
        ExtentHtmlReporter extentHtmlReporter = new ExtentHtmlReporter(filePath);
        extentHtmlReporter.config().setTheme(Theme.DARK);
        extentHtmlReporter.config().setReportName(REPORT_NAME);
        extentHtmlReporter.config().setChartVisibilityOnOpen(true);
        extentReports = new ExtentReports();
        extentReports.attachReporter(extentHtmlReporter);
        return extentReports;
    }

    public static ExtentReports getInstance() {
        if (extentReports == null) {
            extentReports = ExtentManager.createReporter(String.format("%s%s", REPORT_DIRECTORY, REPORT_NAME));
        }
        return extentReports;
    }

    public static void saveReporter() {
        try {
            if (extentReports != null) {
                extentReports.flush();
            }
        } catch (Exception e) {
            Log.getInstance().error("loc.err.save.report");
        }
    }

    public static ExtentTest createTest(String testName) {
        return extentReports.createTest(testName);
    }
}

