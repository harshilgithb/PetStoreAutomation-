package api.utilities;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.awt.Desktop;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentManager {

	public static ExtentReports getExtentReport() {
		ExtentReports report = new ExtentReports();
		Date d = new Date();
		String filename = d.toString().replace(":", "_").replace(" ", "_");
		
		File filePath = new File(".//reports//extentreports"+filename+ ".html");
		System.out.println("Report generated at: " + filePath.getAbsolutePath());

		ExtentSparkReporter sparkReporter = new ExtentSparkReporter(filePath);
		report.attachReporter(sparkReporter);
		sparkReporter.config().setReportName("APITestAutomationReport");
		sparkReporter.config().setTheme(Theme.DARK);
		sparkReporter.config().setTimeStampFormat("dd-mm-yyyy");
		report.setSystemInfo("Author", "Sneha");
		report.setSystemInfo("Website", "Petstore");
		report.setSystemInfo("ProjectName", "RestAssuredAutomation");
		report.setSystemInfo("OS", System.getProperty("os"));
		report.setSystemInfo("Language", "Java");
		
		return report;
	

	}
	
	// Method to open the HTML report in the default browser
	// Enhanced method to open the HTML report
	private static void openReportInBrowser(File reportFile) {
	    try {
	        if (!reportFile.exists()) {
	            System.out.println("Report file does not exist: " + reportFile.getAbsolutePath());
	            return;
	        }

	        if (Desktop.isDesktopSupported()) {
	            Desktop desktop = Desktop.getDesktop();
	            if (desktop.isSupported(Desktop.Action.BROWSE)) {
	                desktop.browse(reportFile.toURI());  // Open in default browser
	            } else {
	                System.out.println("Desktop browsing is not supported on this system.");
	            }
	        } else {
	            System.out.println("Desktop is not supported on this system. Please open the report manually.");
	            System.out.println("Report location: " + reportFile.getAbsolutePath());
	        }
	    } catch (Exception e) {
	        System.out.println("An error occurred while trying to open the report:");
	        e.printStackTrace();
	        System.out.println("Please open the report manually from the following location: " + reportFile.getAbsolutePath());
	    }
	}
}
