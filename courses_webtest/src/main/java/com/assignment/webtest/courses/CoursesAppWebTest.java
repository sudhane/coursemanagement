package com.assignment.webtest.courses;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;

/**
 * Chrome Headless - Course Management Web Application testing
 *
 */
public class CoursesAppWebTest {

	private static WebDriver driver;

	static {
		// Creating a new instance of the HTML unit driver
		// declare the chrome driver from the local machine location
		System.setProperty("webdriver.chrome.driver", "D:\\Softwares\\chromedriver_win32\\chromedriver.exe");

		// create object of chrome options
		ChromeOptions options = new ChromeOptions();

		// add the headless argument
		options.addArguments("headless");

		// pass the options parameter in the Chrome driver declaration
		driver = new ChromeDriver(options);

		driver.get("http://localhost:3000/");

		System.out.println(" Driver launched with the Course Application " + driver);
	}

	public static void main(String[] args) throws Exception {

		CoursesAppWebTest webTest = new CoursesAppWebTest();

		webTest.testList();

		webTest.testCreateCourse();

		webTest.testList();

		webTest.testCourseDelete();

		webTest.closeDriver();

	}


	public void testList() {

		driver.get("http://localhost:3000/");

		// ================ Delete & List Search ================

		try {
			Thread.currentThread().sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		List<WebElement> listOfElements = driver.findElements(By.id("course_details"));
		System.out.println(" Total listOfElements " + listOfElements.size());

		try {
			Thread.currentThread().sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		List<WebElement> listOfViewElements = driver.findElements(By.id("viewCourseButton"));
		System.out.println(" Total listOfViewElements " + listOfViewElements.size());

	}

	/**
	 * 
	 */
	public void testCreateCourse() throws Exception {
		System.out.println(" Add Course Button " + driver.findElement(By.id("addCourseButton")));

		WebElement addCourseElement = driver.findElement(By.id("addCourseButton"));
		addCourseElement.click();

		WebElement addCourseScreenTitleElement = driver.findElement(By.id("addCourseHeader"));
		System.out.println(" Add course title " + addCourseScreenTitleElement.getText());

		WebElement courseTitleElement = driver.findElement(By.id("course_title"));
		courseTitleElement.sendKeys("Be fit !!!");

		WebElement courseAuthorIdElement = driver.findElement(By.id("course_authorId"));
		Select authorNames = new Select(courseAuthorIdElement);
		authorNames.selectByIndex(1);

		WebElement courseCategoryElement = driver.findElement(By.id("course_category"));
		courseCategoryElement.sendKeys("Fitness");

		WebElement submitCourseElement = driver.findElement(By.id("submitCourseButton"));
		System.out.println(" submitCourseElement " + submitCourseElement);
		submitCourseElement.click();

	}

	public void testCourseDelete() {

		List<WebElement> listOfDeleteElements = driver.findElements(By.id("deleteCourseButton"));

		System.out.println(" Total listOfDeleteElements " + listOfDeleteElements.size());

		if (listOfDeleteElements != null && listOfDeleteElements.size() > 0) {
			try {
				WebElement elementToBeDeleted = listOfDeleteElements.get(0);
				elementToBeDeleted.click();
				Thread.currentThread().sleep(10000);
				List<WebElement> listOfElementsAfterDelete = driver.findElements(By.id("course_details"));

				System.out.println(" Total listOfElementsAfterDelete " + listOfElementsAfterDelete.size());
			} catch (Exception exception) {
				System.out.println(exception.getMessage());
			}

		}
	}

	public void closeDriver() {
		// Close the driver
		driver.close();
	}

}
