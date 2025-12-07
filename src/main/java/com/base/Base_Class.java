package com.base;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class Base_Class { // cannot create object for this class. Other classes can extend this class

	public static WebDriver driver; // Selenium interface for browser automation. Static - same instance(driver) is
									// used across the class
	//Launch browser
	protected static WebDriver launchBrowser(String browserName) {
		try {
			if (browserName.equalsIgnoreCase("chrome")) {
				driver = new ChromeDriver();
			} else if (browserName.equalsIgnoreCase("firefox")) {
				driver = new FirefoxDriver();
			} else if (browserName.equalsIgnoreCase("edge")) {
				driver = new EdgeDriver();
			}
		} catch (Exception e) {
			Assert.fail("Error: Occurred during browser launch"); // Assert - from JUnit to intentionally fail execution
																	// if something goes wrong
		}

		driver.manage().window().maximize();
		return driver;

	}
//URL launch
	protected static void launchUrl(String url) {
		try {
			driver.get(url);
		} catch (Exception e) {
			Assert.fail("Error: Occurred during Url launch");
		}
	}

//Close Browser	
	protected static void closeBrowser() {
		try {
			driver.close();
		} catch (Exception e) {
			Assert.fail("Error: Occurred while closing the browser");
		}
	}
//Quit Browser
	protected static void quitBrowser() {
		try {
			driver.quit();
		} catch (Exception e) {
			Assert.fail();
		}
	}
//Click
	protected static void clickElement(WebElement element) {
		try {
			element.click();
		} catch (Exception e) {
			Assert.fail("Error: Occurred during element click");
		}
	}
//Value Input
	
	protected static void passInput(WebElement element, String input) {
		try {
			element.sendKeys(input);
		} catch (Exception e) {
			Assert.fail("Error: Occurred during passInput: " + e.getMessage());
		}
	}

	//Navigation
	protected static void navigationPage(String action, String url) {
		try {
			if (action.equalsIgnoreCase("to")) {
				driver.navigate().to(url);
			} else if (action.equalsIgnoreCase("back")) {
				driver.navigate().back();
			} else if (action.equalsIgnoreCase("forward")) {
				driver.navigate().forward();
			} else if (action.equalsIgnoreCase("refresh")) {
				driver.navigate().refresh();
			} else {
				Assert.fail("Invalid navigation action: " + action);
			}
		} catch (Exception e) {
			Assert.fail("Error: Occurred while performing navigation" + action);
		}

	}
			
//WindowHandling
	protected static void windowHandling(int num) {
		try {
			List<String> allWindow = new ArrayList<>(driver.getWindowHandles());
			driver.switchTo().window(allWindow.get(num));
		} catch (Exception e) {
			Assert.fail("Error: Occurred during Window Handling");
		}
	}
//Get Title
	protected static void getTitle() {
		try {
			String pagetitle = driver.getTitle();
		} catch (Exception e) {
			Assert.fail("Error: Occurred while getting the title");
		}
	}
//Get current URL
	protected static void getCurrentUrl() {
		try {
			String currUrl = driver.getCurrentUrl();
		} catch (Exception e) {
			Assert.fail("Error: Occurred while getting the current URL");
		}
	}
//Get Text
	protected static void getText(WebElement element) {
		try {
			String text = element.getText();
		} catch (Exception e) {
			Assert.fail("Error: Occurred while getting text of the element");
		}
	}

	protected static void getAttribute(WebElement element, String attributeName) {
		try {
			String attribute = element.getAttribute(attributeName);
		} catch (Exception e) {
			Assert.fail("Error: Occurred while getting the attribute");
		}
	}

	protected static void isEnabled(WebElement element) {
		try {
			boolean elemEnabled = element.isEnabled();
			System.out.println("Element enabled is: " + elemEnabled);
		} catch (Exception e) {
			Assert.fail("Error: Occurred while getting isEnabled status");
		}
	}

	protected static void isDisplayed(WebElement element) {
		try {
			boolean elemDisplayed = element.isDisplayed();
			System.out.println("Element displayed is: " + elemDisplayed);
		} catch (Exception e) {
			Assert.fail("Error: Occurred while getting isDisplayed status");
		}

	}
	
	protected static void isSelected(WebElement element) {
		try {
			boolean elemSelected = element.isSelected();
			System.out.println("Element displayed is: " + elemSelected);
		} catch (Exception e) {
			Assert.fail("Error: Occurred while getting isSelected status");
		}

	}
	
	//Alert action
	protected static void alertAction(String action) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	        Alert alert = wait.until(ExpectedConditions.alertIsPresent());

	        if (action.equalsIgnoreCase("accept")) {
	            alert.accept();

	        } else if (action.equalsIgnoreCase("dismiss")) {
	            alert.dismiss();

	        } else if (action.equalsIgnoreCase("getText")) {
	            System.out.println("Alert Text: " + alert.getText());
	            alert.accept();   // always accept after reading text

	        } else {
	            Assert.fail("Invalid alert action: " + action);
	        }

	    } catch (Exception e) {
	        Assert.fail("Error handling alert: " + e.getMessage());
	    }
	}
	

	//Select Options
	protected static void selectOptions(WebElement element, String type, String data) {
		try {
			Select sel = new Select(element);
			boolean multiSelect = sel.isMultiple();
			System.out.println("Multiple select is: " + multiSelect);
			if (type.equalsIgnoreCase("text")) {
				sel.selectByVisibleText(data);
			} else if (type.equalsIgnoreCase("index")) {
				sel.selectByIndex(Integer.parseInt(data));
			} else if (type.equalsIgnoreCase("value")) {
				sel.selectByValue(data);
			}
		} catch (Exception e) {
			Assert.fail("Error occurred while performing Select action: " + type);
		}
	}
	
	//DeSelect Options
	protected static void deselectOptions(WebElement element, String type, String data) {
		try {
			Select desel = new Select(element);
			if (type.equalsIgnoreCase("text")) {
				desel.deselectByVisibleText(data);
			}
			if (type.equalsIgnoreCase("index")) {
				desel.deselectByIndex(Integer.parseInt(data));
			}
			if (type.equalsIgnoreCase("value")) {
				desel.deselectByValue(data);
			}
		} catch (Exception e) {
			Assert.fail("Error occurred while performing deSelect action: " + type);
		}

	}
	
	//GetOptions of Dropdown
	protected static List<String> getOptions(WebElement element) {

		try {
			Select selGetOptions = new Select(element);
			List<WebElement> allOptions = selGetOptions.getOptions();

			for (WebElement option : allOptions) {
				System.out.println("Option: " + option.getText());
			}

		} catch (Exception e) {
			Assert.fail("Error occurred while performing getOptions of Dropdown");
		}
		return null;
	}
	
	//Actions
	protected static void actions(WebElement element, String actionType) {
		Actions action = new Actions(driver);
		try {
			if (actionType.equalsIgnoreCase("click")) {
				action.click(element).perform();
			} else if (actionType.equalsIgnoreCase("doubleclick")) {
				action.doubleClick(element).perform();
			} else if (actionType.equalsIgnoreCase("rightclick")) {
				action.contextClick(element).perform();
			} else if (actionType.equalsIgnoreCase("mousehover")) {
				action.moveToElement(element).perform();
			}
		} catch (Exception e) {
			Assert.fail("Error occurred while performing Actions: " + actionType);
		}

	}
	
	
	//Implicit Wait
	protected static void implicitWait(int time) {
		try {
			if (driver != null) {
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(time));
				System.out.println("Implicit wait applied for " + time + "seconds");
			} else {
				Assert.fail("Driver is not initialized. Please launch the browser first");

			}
		} catch (Exception e) {
			Assert.fail("Error occurred while applying Implicit Wait" + e.getMessage());
		}
	}
	
	//Explicit Wait
	protected static void explicitWait(By locator, int time, String condition) {
		try {
			if (driver == null) {
				Assert.fail("Driver is not initialized. Please launch the browser first");
			}
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(time));

			if (condition.equalsIgnoreCase("present")) {
				wait.until(ExpectedConditions.presenceOfElementLocated(locator));
			} else if (condition.equalsIgnoreCase("visible")) {
				wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
			} else if (condition.equalsIgnoreCase("clickable")) {
				wait.until(ExpectedConditions.elementToBeClickable(locator));
			} else {
				Assert.fail("Invalid wait condition: " + condition);
			}

		} catch (Exception e) {
			Assert.fail("Error occurred while waiting for the element (" + condition + "): " + locator + " | "
					+ e.getMessage());
		}
	}
	
	//Robot class - Keyboard Actions
	
	protected static void robotPressAndRelease(String action, Robot robot) {
		try {
			if (action.equalsIgnoreCase("down")) {
				robot.keyPress(KeyEvent.VK_DOWN);
				robot.keyRelease(KeyEvent.VK_DOWN);
			}else if(action.equalsIgnoreCase("up")){
				robot.keyPress(KeyEvent.VK_UP);
				robot.keyRelease(KeyEvent.VK_UP);
			}else if(action.equalsIgnoreCase("enter")) {
				robot.keyPress(KeyEvent.VK_ENTER);
				robot.keyRelease(KeyEvent.VK_ENTER);
			}else {
				Assert.fail("Invalid Robot condition: " + action);
			}
			}catch(Exception e) {
				Assert.fail("Error occurred during Robot action("+ action + "): " + e.getMessage());
		
		}
	}
	
	//JavaScriptExecutor
	protected static void javaScriptExecutor(String action, int x, int y, String data, WebElement element) {
		try {
			JavascriptExecutor js = (JavascriptExecutor) driver;

			// Click
			if (action.equalsIgnoreCase("click")) {
				js.executeScript("arguments[0].click();", element);
			}

			// SendKeys
			else if (action.equalsIgnoreCase("sendkeys")) {
				js.executeScript("arguments[0].value=arguments[1];", element, data);
			}
			// ScrollToElement
			else if (action.equalsIgnoreCase("srcollIntoView")) {
				js.executeScript("arguments[0].scrollIntoView(true);", element);
			}

			// ScrollByPixels
			else if (action.equalsIgnoreCase("scrollBy")) {
				js.executeScript("window.scrollBy(arguments[0],arguments[1];", x, y);
			}
		} catch (Exception e) {
			Assert.fail("Error occurred while performing JSE action(" + action + "): " + e.getMessage());
		}
	}
	
	//Frames by Id
	protected static void frameId(String id) {
		try {
			driver.switchTo().frame(id);

		} catch (Exception e) {
			Assert.fail("Error occurred while switching to frame by Id");
		}
	}


//Frames by Index
	protected static void frameIndex(String indexvalue) {
		try {
			int index = Integer.parseInt(indexvalue);
			driver.switchTo().frame(indexvalue);

		} catch (Exception e) {
			Assert.fail("Error occurred while switching to frame by Index");
		}
	}
	
	//TakesScreenshot

	
	protected static void takeSnapWithDate() {

		try {
			// Create Screenshots folder inside your project
			String folderPath = System.getProperty("user.dir") + "/Screenshots";

			File folder = new File(folderPath);
			if (!folder.exists()) {
				folder.mkdirs();
			}

			// Timestamp file name
			Date todayDate = new Date();
			String currentDate = todayDate.toString().replace(" ", "_").replace(":", "-");

			TakesScreenshot ts = (TakesScreenshot) driver;
			File source = ts.getScreenshotAs(OutputType.FILE);

			// Final destination path
			File destination = new File(folderPath + "/" + currentDate + ".png");

			FileHandler.copy(source, destination);

			System.out.println("Screenshot saved at: " + destination.getAbsolutePath());

		} catch (Exception e) {
			Assert.fail("Error Occurred while taking snaps: " + e.getMessage());
		}
	}

}

