package com.kuaishoudan.financer.selenium;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.MobileCapabilityType;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;

import com.kuaishoudan.financer.bean.KSDCase;
import com.kuaishoudan.financer.bean.RequestPayout;
import com.kuaishoudan.financer.dao.UserDaoImpl;
import com.kuaishoudan.financer.util.IdCardGenerator;
import com.kuaishoudan.financer.util.RandomValue;

public class AppUtil {

	public static AppiumDriver<WebElement> getdriver() {
		System.out.println("**");
		// set up appium
		File classpathRoot = new File(System.getProperty("user.dir"));
		File appDir = new File(classpathRoot, "apps");
		File app = new File(appDir, "Zhihuijinfu.ipa");// HelloWorld.ipa
														// Zhihuijinfu.ipa
														// financerfinalVersionjiagusign.apk
		DesiredCapabilities capabilities = new DesiredCapabilities();
		// DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("noReset", true);
		capabilities.setCapability("newCommandTimeout", 240);
		capabilities.setCapability("platformVersion", "9.3");// 8.4
		capabilities.setCapability("platformName", "iOS");// 8.4
		capabilities.setCapability("deviceName", "iPhone");// IPhone 61 iPhone
															// iPhone
		capabilities.setCapability("udid", "a37e172bee9bc595116594adb4af2a5baad59954"); // a37e172bee9bc595116594adb4af2a5baad59954
																						// e15b30e055cf920d7f93aa086846f50b12dc9bb7
		capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "XCUITest");

		// capabilities.setCapability("app", "com.jizhicar.zhihjf");
		// capabilities.setCapability("platformVersion", "8.4");//8.4
		// capabilities.setCapability("deviceName", "iPhone 6");
		capabilities.setCapability("unicodeKeyboard", "True");
		capabilities.setCapability("resetKeyboard", "True");
		capabilities.setCapability("app", app.getAbsolutePath());
		System.out.println("##== p" + app.getAbsolutePath());
		AppiumDriver<WebElement> driver = null;
		try {
			driver = new IOSDriver<WebElement>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return driver;

	}

	// 向上滑动
	public static void swipeToUp(AppiumDriver driver, int during) {
		JavascriptExecutor js = (JavascriptExecutor) driver;

		HashMap<String, String> scrollObject = new HashMap<String, String>();
		scrollObject.put("direction", "down");
		js.executeScript("mobile: scroll", scrollObject);

	}

	// 向上滑动
	public static void swipeToUp2(AppiumDriver<WebElement> driver, int during) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		for (int i = 0; i < 2; i++) {

			HashMap<String, String> scrollObject = new HashMap<String, String>();
			scrollObject.put("direction", "down");
			js.executeScript("mobile: scroll", scrollObject);
		}

	}

	// 向上滑动
	public static void swipeToUp3(AppiumDriver driver, int during) {
		int width = driver.manage().window().getSize().width;
		int height = driver.manage().window().getSize().height;
		// System.out.print(width+"@"+height);
		for (int i = 0; i < 5; i++)
			driver.swipe(width / 2, height * 3 / 4, width / 2, height / 4, during);

	}

	// 向上滑动
	public static void swipeToUpQk(AppiumDriver driver, int during, int count) {
		int width = driver.manage().window().getSize().width;
		int height = driver.manage().window().getSize().height;
		// System.out.print(width+"@"+height);
		for (int i = 0; i < (count / 2); i++)
			driver.swipe(width / 2, height * 3 / 4, width / 2, height / 4, during);

	}

	// 向下滑动
	public static void swipeToDown(AppiumDriver driver, int during) {
		JavascriptExecutor js = (JavascriptExecutor) driver;

		HashMap<String, String> scrollObject = new HashMap<String, String>();
		scrollObject.put("direction", "up");
		js.executeScript("mobile: scroll", scrollObject);

	}

	/**
	 * 创建用户
	 * 
	 * @param driver
	 * @param devicename
	 * @param k
	 */
	public static boolean createUser(AppiumDriver<WebElement> driver, String devicename, int k, KSDCase ksd) {
		boolean flag = false;
		// try {
		driver.manage().timeouts().implicitlyWait(258, TimeUnit.SECONDS);

		driver.findElement(By.id("client add")).click();// +号
		driver.manage().timeouts().implicitlyWait(38, TimeUnit.SECONDS);
		driver.findElement(By.id("手动输入")).click();// 手动输入

		driver.manage().timeouts().implicitlyWait(38, TimeUnit.SECONDS);
		System.out.println(driver.findElements(By.className("XCUIElementTypeTextField")).size());
		driver.findElements(By.className("XCUIElementTypeTextField")).get(0).sendKeys(ksd.getUsername());
		driver.findElements(By.className("XCUIElementTypeTextField")).get(1).sendKeys(ksd.getPhone());

		driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
		driver.findElement(By.id("身份证")).click();// 点击身份证
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		if (ksd.getIdentitytype() == 1) {
			driver.findElement(By.name("身份证")).click();// 点击身份证
			driver.manage().timeouts().implicitlyWait(55, TimeUnit.SECONDS);
			driver.findElements(By.className("XCUIElementTypeTextField")).get(2).sendKeys(ksd.getIdentitynum());// 证件号码
																												// *****

		} else if (ksd.getIdentitytype() == 2) {
			driver.findElement(By.name("军官证")).click();// 点击军官证
			driver.manage().timeouts().implicitlyWait(55, TimeUnit.SECONDS);
			driver.findElements(By.className("XCUIElementTypeTextField")).get(2).sendKeys(ksd.getJgid());

		}

		driver.findElement(By.id("请输入户籍地址")).sendKeys(ksd.getAddress());
		;// 地址
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.findElement(By.id("确认")).click();// 确认
		driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
		driver.findElement(By.id("马上进件")).click();// 马上进件
		flag = true;

		return true;
	}

	/**
	 * 个人贷款
	 * 
	 * @param driver
	 * @param devicename
	 * @param k
	 */
	public static KSDCase addGr(AppiumDriver<WebElement> driver, WebDriver webdriver, String devicename, int k,
			KSDCase ksd) {

		String actualstatue = "";

		boolean flag = false;
		boolean cx = false;

		try {

			driver.manage().timeouts().implicitlyWait(28, TimeUnit.SECONDS);

			driver.findElement(By.xpath("//XCUIElementTypeButton[@name='去申请'][1]")).click();// 去申请
		} catch (org.openqa.selenium.NoSuchElementException ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
			flag = true;

		}
		if (!flag) {
			try {

				Thread.sleep(2000);
				List<WebElement> buttons = driver.findElements(By.className("XCUIElementTypeButton"));
				// for(int i=0;i<sd.size();i++){
				// System.out.println( sd.get(i).getAttribute("name"));
				// }
				driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
				int ran = ksd.getCartype();
				// ------------
				if (ran == 1) {
					buttons.get(2).click();// 二手车
				} else {
					buttons.get(3).click();// 新车
				}
				// ___________

				driver.findElements(By.className("XCUIElementTypeCell")).get(2).click();// 所属商户
				try {
					List<WebElement> supplier = driver.findElements(By.className("XCUIElementTypeStaticText"));

					ksd.setSssh(supplier.get(2).getText());

					supplier.get(2).click();// 所属商户列表

				} catch (java.lang.IndexOutOfBoundsException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();

				}
				driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
				driver.findElement(By
						.xpath("//XCUIElementTypeApplication/XCUIElementTypeWindow[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeTable/XCUIElementTypeCell[4]"))
						.click();// 品牌车系
				driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
				try {

					// driver.findElement(By.name(ksd.getCarbrand())).click();
					driver.findElements(By.className("XCUIElementTypeCell")).get(0).click();
					cx = true;
				} catch (java.lang.IndexOutOfBoundsException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();

				}
				try {
					if (cx) {

						// driver.findElement(By.id(ksd.getCarseries())).click();
						driver.findElements(By.className("XCUIElementTypeCell")).get(0).click();

					}
				} catch (java.lang.IndexOutOfBoundsException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();

				}

				driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

				driver.findElements(By.className("XCUIElementTypeTextField")).get(0).sendKeys("" + ksd.getCarprice()); // 车辆价格
				driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
				driver.findElements(By.className("XCUIElementTypeTextField")).get(1).sendKeys("" + ksd.getSqdk());
				;// 申请贷款
				driver.manage().timeouts().implicitlyWait(35, TimeUnit.SECONDS);
				driver.findElement(By.name("新建贷款")).click();
				;
				Thread.sleep(500);
				driver.findElements(By.className("XCUIElementTypeCell")).get(6).click();
				;// 还款期数 / 融资期限
				driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
				driver.findElement(By.id("" + (ksd.getHkqs() * 6))).click();// 还款期数周期
																			// /融资期限
				driver.manage().timeouts().implicitlyWait(18, TimeUnit.SECONDS);
				driver.findElements(By.className("XCUIElementTypeCell")).get(7).click();
				;// 金融产品
				Thread.sleep(500);
				driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
				try {
					Thread.sleep(500);
					List<WebElement> producs = driver.findElements(By.className("XCUIElementTypeCell"));

					for (WebElement produc1 : producs) {
						WebElement product = produc1.findElement(By.xpath("XCUIElementTypeStaticText[10]"));

						if (!product.getText().contains("平安租赁")) {
							product.click();// 第一个产品
							break;
						}
					}

					Thread.sleep(300);
					ksd.setProduct(driver
							.findElement(By
									.xpath("//XCUIElementTypeApplication/XCUIElementTypeWindow[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeTable/XCUIElementTypeCell[8]/XCUIElementTypeStaticText[2]"))
							.getText().trim());

				} catch (java.lang.IndexOutOfBoundsException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();

				}

				driver.findElements(By.className("XCUIElementTypeCell")).get(8).click();
				;// 费率
				driver.manage().timeouts().implicitlyWait(25, TimeUnit.SECONDS);
				try {

					driver.findElement(By
							.xpath("//XCUIElementTypeApplication/XCUIElementTypeWindow[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeTable/XCUIElementTypeCell[1]"))
							.click();
					;
					driver.manage().timeouts().implicitlyWait(25, TimeUnit.SECONDS);
					String rate = driver
							.findElement(By
									.xpath("//XCUIElementTypeApplication/XCUIElementTypeWindow[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeTable/XCUIElementTypeCell[9]/XCUIElementTypeStaticText[2]"))
							.getText();
					ksd.setRate(rate);
					driver.manage().timeouts().implicitlyWait(25, TimeUnit.SECONDS);

				} catch (java.lang.IndexOutOfBoundsException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();

				}

				driver.findElement(By.id("请输入备注内容")).sendKeys(ksd.getRemark());// 备注
				driver.manage().timeouts().implicitlyWait(25, TimeUnit.SECONDS);

				WebUtil.login(webdriver, ksd);// 登录
				List<Integer> list = WebOrgan.getImge1(webdriver, ksd);

				WebUtil.logout(webdriver);

				driver.manage().timeouts().implicitlyWait(155, TimeUnit.SECONDS);
				driver.findElement(By.name("下一步")).click();

				driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

				// if (ran == 1) { // 二手车
				int havesystem = UserDaoImpl.gethave_system(ksd.getProduct().trim().split("-")[0]);// 产品名称查是否有常规甩单
				System.out.println(ksd.getProduct().trim().split("-")[0] + "," + havesystem);
				if (havesystem == 0) {
					driver.findElement(By.id("常规")).click();// 订单常规
					//// XCUIElementTypeApplication[@name="快收单"]/XCUIElementTypeWindow[4]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeButton
				}
				// }
				Thread.sleep(1000);
				driver.manage().timeouts().implicitlyWait(55, TimeUnit.SECONDS);
				int aa = 0, countImg = 0;

				List<Integer> list2 = new ArrayList<Integer>();
				for (int i = 0; i < list.size(); i++) {
					if (list.get(i) < 9) {
						List<Integer> list3 = UserDaoImpl.getImgType(list.get(i) + 1, list);
						list2.addAll(list3);
						aa = list3.size();
						countImg = aa + countImg;
					}
				}
				Collections.sort(list);
				if (countImg == 0) {
					for (Integer type : list) {
						if (type > 99) {
							list2.add(type);
							break;
						}
					}
				}

				ksd.setImgtypes(list2);
				System.out.println(list2.size() + "$$$" + countImg);

				ksd.setImgcount(countImg);

				driver.findElement(By.id("xy_icon_uploadImg")).click();// 上传照片
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (org.openqa.selenium.NoSuchElementException ex) {
				ex.printStackTrace();

			}

			actualstatue = upload(driver, ksd.getImgcount());

		}
		return ksd;
	}

	/**
	 * 企业贷款
	 * 
	 * @param driver
	 * @param devicename
	 * @param k
	 */
	public static KSDCase addQy(AppiumDriver<WebElement> driver, WebDriver webdriver, String devicename, int k,
			KSDCase ksd) {

		String actualstatue = "";

		boolean flag = false;
		boolean cx = false;

		try {

			Thread.sleep(1500);

			driver.manage().timeouts().implicitlyWait(28, TimeUnit.SECONDS);

			driver.findElements(By.className("XCUIElementTypeButton")).get(3).click();// 去申请
		} catch (org.openqa.selenium.NoSuchElementException ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
			flag = true;

		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (!flag) {
			try {

				Thread.sleep(1000);

				driver.findElements(By.className("XCUIElementTypeTextField")).get(0).sendKeys("qiyemc"); // qiye
				driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
				driver.findElements(By.className("XCUIElementTypeTextField")).get(1).sendKeys("yinyezhizhao"); // qiye
				driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
				driver.findElement(By.name("新建贷款")).click();
				;
				driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
				List<WebElement> buttons = driver.findElements(By.className("XCUIElementTypeButton"));
				// for(int i=0;i<buttons.size();i++){
				// System.out.println(buttons.get(i).getText());
				// }

				int ran = ksd.getCartype();
				Thread.sleep(1000);
				// ------------
				if (ran == 1) {
					buttons.get(2).click();// 二手车
				} else {
					buttons.get(3).click();// 新车
				}
				// ___________
				driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
				driver.findElements(By.className("XCUIElementTypeCell")).get(5).click();// 所属商户
				try {
					driver.manage().timeouts().implicitlyWait(28, TimeUnit.SECONDS);
					List<WebElement> supplier = driver.findElements(By.className("XCUIElementTypeStaticText"));

					ksd.setSssh(supplier.get(2).getText());

					supplier.get(2).click();// 所属商户列表

				} catch (java.lang.IndexOutOfBoundsException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();

				}
				driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
				driver.findElement(By
						.xpath("//XCUIElementTypeApplication/XCUIElementTypeWindow[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeTable/XCUIElementTypeCell[7]"))
						.click();// 品牌车系
				driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
				try {

					// driver.findElement(By.name(ksd.getCarbrand())).click();
					driver.findElements(By.className("XCUIElementTypeCell")).get(0).click();

					cx = true;
				} catch (java.lang.IndexOutOfBoundsException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();

				}
				try {
					if (cx) {
						Thread.sleep(200);
						// driver.findElement(By.id(ksd.getCarseries())).click();
						driver.findElements(By.className("XCUIElementTypeCell")).get(0).click();

					}
				} catch (java.lang.IndexOutOfBoundsException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();

				}

				driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

				driver.findElements(By.className("XCUIElementTypeTextField")).get(2).sendKeys("" + ksd.getCarprice()); // 车辆价格
				driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
				driver.findElement(By.name("新建贷款")).click();
				;
				driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
				driver.findElements(By.className("XCUIElementTypeTextField")).get(3).sendKeys("" + ksd.getSqdk());
				;// 申请贷款
				driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
				driver.findElement(By.name("新建贷款")).click();
				;
				driver.findElements(By.className("XCUIElementTypeCell")).get(9).click();
				;// 还款期数 / 融资期限
				driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
				driver.findElement(By.id("" + (ksd.getHkqs() * 6))).click();// 还款期数周期
																			// /融资期限
				driver.manage().timeouts().implicitlyWait(18, TimeUnit.SECONDS);
				driver.findElements(By.className("XCUIElementTypeCell")).get(10).click();
				;// 金融产品
				Thread.sleep(500);
				driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
				try {
					Thread.sleep(500);
					List<WebElement> producs = driver.findElements(By.className("XCUIElementTypeCell"));

					for (WebElement produc1 : producs) {
						WebElement product = produc1.findElement(By.xpath("XCUIElementTypeStaticText[10]"));

						if (!product.getText().contains("平安租赁")) {
							product.click();// 第一个产品
							break;
						}
					}

					Thread.sleep(300);
					ksd.setProduct(driver
							.findElement(By
									.xpath("//XCUIElementTypeApplication/XCUIElementTypeWindow[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeTable/XCUIElementTypeCell[11]/XCUIElementTypeStaticText[2]"))
							.getText().trim());

				} catch (java.lang.IndexOutOfBoundsException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();

				}

				driver.manage().timeouts().implicitlyWait(25, TimeUnit.SECONDS);
				driver.findElements(By.className("XCUIElementTypeCell")).get(11).click();
				;// 费率
				driver.manage().timeouts().implicitlyWait(25, TimeUnit.SECONDS);
				try {

					driver.findElement(By
							.xpath("//XCUIElementTypeApplication/XCUIElementTypeWindow[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeTable/XCUIElementTypeCell[1]"))
							.click();
					;
					driver.manage().timeouts().implicitlyWait(25, TimeUnit.SECONDS);
					String rate = driver
							.findElement(By
									.xpath("//XCUIElementTypeApplication/XCUIElementTypeWindow[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeTable/XCUIElementTypeCell[12]/XCUIElementTypeStaticText[2]"))
							.getText();
					ksd.setRate(rate);
					driver.manage().timeouts().implicitlyWait(25, TimeUnit.SECONDS);

				} catch (java.lang.IndexOutOfBoundsException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();

				}

				driver.findElement(By.id("请输入备注内容")).sendKeys(ksd.getRemark());// 备注
				driver.manage().timeouts().implicitlyWait(25, TimeUnit.SECONDS);

				WebUtil.login(webdriver, ksd);// 登录
				List<Integer> list = WebOrgan.getImge1(webdriver, ksd);

				WebUtil.logout(webdriver);

				driver.manage().timeouts().implicitlyWait(355, TimeUnit.SECONDS);
				driver.findElement(By.name("下一步")).click();

				driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

				// if (ran == 1) { // 二手车
				int havesystem = UserDaoImpl.gethave_system(ksd.getProduct().trim().split("-")[0]);// 产品名称查是否有常规甩单
				System.out.println(ksd.getProduct().trim().split("-")[0] + "," + havesystem);
				if (havesystem == 0) {
					driver.findElement(By.id("常规")).click();// 订单常规
					//// XCUIElementTypeApplication[@name="快收单"]/XCUIElementTypeWindow[4]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeButton
				}
				// }
				Thread.sleep(1000);
				driver.manage().timeouts().implicitlyWait(55, TimeUnit.SECONDS);
				int aa = 0, countImg = 0;

				List<Integer> list2 = new ArrayList<Integer>();
				for (int i = 0; i < list.size(); i++) {
					if (list.get(i) < 9) {
						List<Integer> list3 = UserDaoImpl.getImgType(list.get(i) + 1, list);
						list2.addAll(list3);
						aa = list3.size();
						countImg = aa + countImg;
					}
				}
				Collections.sort(list);
				if (countImg == 0) {
					for (Integer type : list) {
						if (type > 99) {
							list2.add(type);
							break;
						}
					}
				}

				ksd.setImgtypes(list2);
				System.out.println(list2.size() + "$$$" + countImg);

				ksd.setImgcount(countImg);

				driver.findElement(By.id("xy_icon_uploadImg")).click();// 上传照片
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (org.openqa.selenium.NoSuchElementException ex) {
				ex.printStackTrace();

			}

			actualstatue = upload(driver, ksd.getImgcount());

		}
		return ksd;

	}

	public static KSDCase addTest(AppiumDriver<WebElement> driver, WebDriver webdriver, String devicename, int i) {

		KSDCase ksd = RandomValue.getRandom();
		System.out.println("名称" + ksd.getUsername() + "手机" + ksd.getPhone() + "身份证号" + ksd.getIdentitynum() + "身份类型"
				+ ksd.getIdentitytype() + "军官" + ksd.getJgid() + "企业个人" + ksd.getQygr() + "车类型" + ksd.getCartype()
				+ "车品牌" + ksd.getCarbrand() + "车系" + ksd.getCarseries() + "车价格" + ksd.getCarprice() + "贷款价格"
				+ ksd.getSqdk() + "融资期限" + ksd.getHkqs() + "\n  " + ksd.getPurchase_tax() + "\n " + ksd.getInsurance()
				+ " \n" + ksd.getGps_charge() + "\n " + ksd.getService_charge() + "," + ksd.getRegisttype() + ","
				+ ksd.getPledge());
		int gq = ksd.getQygr();
		boolean flag = createUser(driver, devicename, i, ksd);
		// boolean flag=true;
		gq = 2;
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (flag) {
			if (gq == 2) {// 企业贷款
				// driver.findElements(By.className("XCUIElementTypeCell"))
				// .get(0).click();// 首页列表
				// driver.manage().timeouts().implicitlyWait(5,
				// TimeUnit.SECONDS);
				// driver.findElement(By.id("进件")).click();
				// driver.manage().timeouts().implicitlyWait(5,
				// TimeUnit.SECONDS);
				ksd = addQy(driver, webdriver, devicename, i, ksd);
			} else {// 个人贷款
				// driver.findElements(By.className("XCUIElementTypeCell"))
				// .get(0).click();// 首页列表
				// driver.manage().timeouts().implicitlyWait(5,
				// TimeUnit.SECONDS);
				// driver.findElement(By.id("进件")).click();
				// driver.manage().timeouts().implicitlyWait(5,
				// TimeUnit.SECONDS);
				ksd = addGr(driver, webdriver, devicename, i, ksd);
				//
			}
		}
		return ksd;

	}

	// 再次进件
	public static void zcjj(AppiumDriver<WebElement> driver) {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.findElements(By.className("XCUIElementTypeCell")).get(0).click();// 首页列表

		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

		String titletext = driver.findElements(By.className("XCUIElementTypeStaticText")).get(1).getText();
		// System.out.println(titletext);
		if ("贷款详情".equals(titletext)) {
			driver.manage().timeouts().implicitlyWait(45, TimeUnit.SECONDS);
			driver.findElement(By.id("icon businessMore")).click();// 状态审批流程
			driver.manage().timeouts().implicitlyWait(45, TimeUnit.SECONDS);
			driver.findElement(By.id("再次进件")).click();// 查看进度
		} else {
			driver.findElement(By.id("进件")).click();// 第3次进件3
		}
	}

	/**
	 * 上传照片
	 * 
	 * @param driver
	 * @return
	 */
	public static String upload(AppiumDriver<WebElement> driver, int imgcount) {
		String acstatue = "";
		// imgcount=27;
		int count1 = imgcount / 9;
		int count2 = imgcount % 9;
		try {

			for (int j = 0; j < count1; j++) {// 9

				if (j > 0) {
					driver.findElement(By.id("xy_icon_uploadImg")).click();// 上传照片
				}
				driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
				driver.findElement(By.id("从相册选取")).click();// 从相册选择
				List<WebElement> cs = driver.findElements(By.className("XCUIElementTypeCell"));
				for (int i = 4; i < 13; i++) {
					if (i == 4 && j > 0) {

						//if (j == 1) {
							for (int k = 0; k < (j * 10); k++)

								cs.get(i).click();
//						} else {
//							for (int k = 0; k < (j * 9); k++)
//
//								cs.get(i).click();
//						}

					} else if (i == 5) {

						cs.get(i).click();
					} else {

						cs.get(i).click();
					}

					cs.get(i).click();// 添加图片（驾驶证）
				}

				driver.findElement(By
						.xpath("//XCUIElementTypeApplication[@name='快收单']/XCUIElementTypeWindow[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeToolbar/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeButton"))
						.click();// 两种证上传——确定按钮
				Thread.sleep(1000);

				AppUtil.swipeToUp2(driver, 1000);// 向上滑动
				Thread.sleep(8500);

			}

			if (count2 == 0 && count1 > 0) {

			} else {

				driver.manage().timeouts().implicitlyWait(548, TimeUnit.SECONDS);
				if (count1 != 0) {
					driver.findElement(By.id("xy_icon_uploadImg")).click();// 上传照片
				}

				driver.manage().timeouts().implicitlyWait(45, TimeUnit.SECONDS);
				driver.findElement(By.id("从相册选取")).click();// 从相册选择
				// System.out.println("###"+driver.findElements(By.className("XCUIElementTypeCell")).size());
				List<WebElement> cs = driver.findElements(By.className("XCUIElementTypeCell"));

				if (count2 == 0 && count1 == 0) {

					cs.get(4).click();// 添加图片（驾驶证）
				} else if (count2 > 0 && count2 < 9) {

					for (int i = 4; i < (count2 + 4); i++) {
						if (i == 4) {

							for (int k = 0; k < (count1 * 10); k++) {

								cs.get(i).click();

							}

						} else if (i == 5 && count1 == 0) {
							for (int k = 0; k < 2; k++) {
								cs.get(i).click();// Thread.sleep(1000);
							}
						} else {

							cs.get(i).click();
						}

						cs.get(i).click();// 添加图片（驾驶证）

					}
				}

				driver.manage().timeouts().implicitlyWait(18, TimeUnit.SECONDS);
				//
				driver.findElement(By
						.xpath("//XCUIElementTypeApplication/XCUIElementTypeWindow[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeToolbar/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeButton"))
						.click();// 两种证上传——确定按钮

				Thread.sleep(7000 + count2 * 2000);
			}

			driver.manage().timeouts().implicitlyWait(258, TimeUnit.SECONDS);
			driver.findElement(By.id("提交")).click();// 上传完照片-确认按钮
			driver.manage().timeouts().implicitlyWait(158, TimeUnit.SECONDS);
			driver.findElement(By.id("确定")).click();// 提醒确定是

			driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);

		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (org.openqa.selenium.WebDriverException e) {
			e.printStackTrace();
		}

		try {
			Thread.sleep(1000);
			driver.findElement(By.id("icon back")).click();// 返回按钮

			// acstatue = AppSPUtil.getActstatue(driver);// 状态值
		} catch (org.openqa.selenium.WebDriverException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return acstatue;
	}

	public static KSDCase addZjjtest(AppiumDriver<WebElement> driver, WebDriver webdriver, String devicename, int i) {

		KSDCase ksd = RandomValue.getRandom(driver);
		System.out.println("名称" + ksd.getUsername() + "手机" + ksd.getPhone() + "身份证号" + ksd.getIdentitynum() + "身份类型"
				+ ksd.getIdentitytype() + "军官" + ksd.getJgid() + "企业个人" + ksd.getQygr() + "车类型" + ksd.getCartype()
				+ "车品牌" + ksd.getCarbrand() + "车系" + ksd.getCarseries() + "车价格" + ksd.getCarprice() + "贷款价格"
				+ ksd.getSqdk() + "融资期限" + ksd.getHkqs());
		int gq = ksd.getQygr();
		// ksd.setProduct("汇通租赁-2323");
		// List<Integer>ff=new ArrayList<Integer>();ff.add(100);
		// ksd.setImgtypes(ff);
		AppUtil.zcjj(driver);
		if (gq == 2) {// 企业贷款
			ksd = addQy(driver, webdriver, devicename, i, ksd);
		} else {// 个人贷款
			ksd = addGr(driver, webdriver, devicename, i, ksd);
		}
		// driver.findElement(By.id("icon back")).click();
		return ksd;
	}

	public static void login(AppiumDriver<WebElement> driver, String devicename, KSDCase ksd) {

		try {
			driver.manage().timeouts().implicitlyWait(18, TimeUnit.SECONDS);

			WebElement username = driver.findElement(By.className("XCUIElementTypeTextField"));

			driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
			username.clear();
			driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
			username.sendKeys(ksd.getLoginemail());
			;
			driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
			driver.findElement(By
					.xpath("//XCUIElementTypeApplication/XCUIElementTypeWindow[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeSecureTextField"))
					.sendKeys(ksd.getPwd());
			;
			Thread.sleep(1000);
			driver.findElement(By.id("登 录")).click();
			Thread.sleep(2000);

		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// driver.findElement(By.id("com.kuaishoudan.financer:id/edit_account")).sendKeys(username);
		// driver.findElement(By.id("com.kuaishoudan.financer:id/edit_password")).sendKeys("@123456");

	}

	// 登出
	public static void logout(AppiumDriver<WebElement> driver) {
		// System.out.println("logout");
		driver.manage().timeouts().implicitlyWait(58, TimeUnit.SECONDS);
		driver.findElement(By.id("icon baidian")).click();// 菜单
		driver.manage().timeouts().implicitlyWait(158, TimeUnit.SECONDS);
		driver.findElement(By.id("defaultIcon")).click();// 头像
		driver.manage().timeouts().implicitlyWait(58, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//XCUIElementTypeButton[@name='退出登录']")).click();// 退出登录
		driver.manage().timeouts().implicitlyWait(48, TimeUnit.SECONDS);
		driver.findElement(By.id("是")).click();// 确定)
	}

	public static boolean ElementExist(AppiumDriver<AndroidElement> driver, By locator) {
		try {
			driver.findElement(locator);
			return true;
		} catch (org.openqa.selenium.NoSuchElementException ex) {
			return false;
		}

	}

	/**
	 * 已请款-----返回查看状态
	 * 
	 * @param driver
	 */
	public static String getStatue(AppiumDriver<WebElement> driver) {
		try {
			Thread.sleep(300);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.findElement(By.id("icon back")).click();// 返回
		try {
			Thread.sleep(300);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
		driver.findElement(By.id("icon back")).click();// 返回
		String statue = AppSPUtil.getActstatue(driver);// 状态值
		return statue;

	}

	/**
	 * (未使用) 查看进度
	 * 
	 * @param driver
	 */
	public static void look_status(AppiumDriver<AndroidElement> driver) {
		driver.manage().timeouts().implicitlyWait(18, TimeUnit.SECONDS);
		// driver.findElement(By.id("com.kuaishoudan.financer:id/toolbar_back")).click();//返回按钮
		/*
		 * driver.findElements(By.id("com.kuaishoudan.financer:id/text_name"))
		 * .get(0).click();// 第一个客户
		 * driver.manage().timeouts().implicitlyWait(28, TimeUnit.SECONDS);
		 * driver.findElement(
		 * By.id("com.kuaishoudan.financer:id/toolbar_loan_status")) .click();//
		 * 流程。。。 driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
		 * driver.findElement(
		 * By.id("com.kuaishoudan.financer:id/text_customer_look_status"))
		 * .click();// 查看进度
		 */

	}

	/**
	 * 两次返回
	 * 
	 * @param driver
	 */
	public static void goBack1(AppiumDriver<WebElement> driver) {
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.findElement(By.id("icon back")).click();// 返回按钮
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.findElement(By.id("icon back")).click();// 返回按钮
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 一次返回
	 * 
	 * @param driver
	 */
	public static void goBack0(AppiumDriver<AndroidElement> driver) {
		driver.manage().timeouts().implicitlyWait(18, TimeUnit.SECONDS);
		driver.findElement(By.id("icon back")).click();// 返回按钮
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static String getIndexname(AppiumDriver<WebElement> driver) {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String name2 = driver.findElement(By.xpath("//XCUIElementTypeCell[1]/XCUIElementTypeStaticText[2]")).getText();

		return name2;
	}

	/**
	 * 上传照片
	 * 
	 * @param driver
	 * @return
	 */
	public static String uploadQk(AppiumDriver<WebElement> driver, int imgcount) {

		String acstatue = "";

		int count1 = imgcount / 9;
		int count2 = imgcount % 9;
		try {

			for (int j = 0; j < count1; j++) {// 9

				if (j > 0) {
					driver.findElement(By.id("xy_icon_uploadImg")).click();// 上传照片
				}
				driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
				driver.findElement(By.id("从相册选取")).click();// 从相册选择
				List<WebElement> cs = driver.findElements(By.className("XCUIElementTypeCell"));
				for (int i = 4; i < 13; i++) {
					if (i == 4 && j > 0) {

						if (j == 1) {
							for (int k = 0; k < (j * 10); k++)

								cs.get(i).click();
						} else {
							for (int k = 0; k < (j * 9); k++)

								cs.get(i).click();
						}

					} else if (i == 5) {

						cs.get(i).click();
					} else {

						cs.get(i).click();
					}

					cs.get(i).click();// 添加图片（驾驶证）
				}

				driver.findElement(By
						.xpath("//XCUIElementTypeApplication[@name='快收单']/XCUIElementTypeWindow[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeToolbar/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeButton"))
						.click();// 两种证上传——确定按钮
				Thread.sleep(1000);

				AppUtil.swipeToUp2(driver, 1000);// 向上滑动
				Thread.sleep(8500);

			}

			if (count2 == 0 && count1 > 0) {

			} else {

				driver.manage().timeouts().implicitlyWait(48, TimeUnit.SECONDS);
				if (count1 != 0) {
					driver.findElement(By.id("xy_icon_uploadImg")).click();// 上传照片
				}

				driver.manage().timeouts().implicitlyWait(45, TimeUnit.SECONDS);
				driver.findElement(By.id("从相册选取")).click();// 从相册选择
				// System.out.println("###"+driver.findElements(By.className("XCUIElementTypeCell")).size());
				List<WebElement> cs = driver.findElements(By.className("XCUIElementTypeCell"));

				if (count2 == 0 && count1 == 0) {

					cs.get(4).click();// 添加图片（驾驶证）
				} else if (count2 > 0 && count2 < 9) {

					for (int i = 4; i < (count2 + 4); i++) {
						if (i == 4) {

							for (int k = 0; k < (count1 * 10); k++) {

								cs.get(i).click();

							}

						} else if (i == 5 && count1 == 0) {
							for (int k = 0; k < 2; k++) {
								cs.get(i).click();// Thread.sleep(1000);
							}
						} else {

							cs.get(i).click();
						}

						cs.get(i).click();// 添加图片（驾驶证）

					}
				}

				driver.manage().timeouts().implicitlyWait(18, TimeUnit.SECONDS);
				//
				driver.findElement(By
						.xpath("//XCUIElementTypeApplication[@name='快收单']/XCUIElementTypeWindow[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeToolbar/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeButton"))
						.click();// 两种证上传——确定按钮

				Thread.sleep(5000 + count2 * 2000);
			}

		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (org.openqa.selenium.WebDriverException e) {
			e.printStackTrace();

		}

		return acstatue;

	}

	// 返点费用支出
	public static void testFd(AppiumDriver<WebElement> driver, String devicename, RequestPayout RequestPyout)
			throws InterruptedException, IOException {

		AppUtil.swipeToUp3(driver, 1000);// 向上滑动
		Thread.sleep(1000);
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.findElements(By.id("com.kuaishoudan.financer:id/iv_is_show")).get(0).click();
		AppUtil.swipeToUp(driver, 1000);// 向上滑动
		// 车款融资额返点

		Runtime.getRuntime()
				.exec("adb -s " + devicename + " shell input text " + RequestPyout.getFinancing_back_point());
		Thread.sleep(500);
		driver.findElements(By.id("com.kuaishoudan.financer:id/text_content")).get(0).click();
		Thread.sleep(1500);
		// GPS返点
		Runtime.getRuntime().exec("adb -s " + devicename + " shell input text " + RequestPyout.getGps_back_point());
		Thread.sleep(500);
		driver.findElements(By.id("com.kuaishoudan.financer:id/text_content")).get(1).click();
		Thread.sleep(1500);
		// 保险返点
		Runtime.getRuntime()
				.exec("adb -s " + devicename + " shell input text " + RequestPyout.getInsurance_back_point());
		Thread.sleep(500);
		driver.findElements(By.id("com.kuaishoudan.financer:id/text_content")).get(2).click();
		Thread.sleep(1500);
		// 服务费返点
		Runtime.getRuntime().exec("adb -s " + devicename + " shell input text " + RequestPyout.getService_back_point());
		Thread.sleep(500);
		driver.findElements(By.id("com.kuaishoudan.financer:id/text_content")).get(3).click();
		Thread.sleep(1000);
		driver.findElements(By.id("com.kuaishoudan.financer:id/iv_is_show")).get(0).click();
	}

	// 新车抵押费用支出

	public static void testDy(AppiumDriver<AndroidElement> driver, String devicename, RequestPayout RequestPyout)
			throws InterruptedException, IOException {

		AppUtil.swipeToUp3(driver, 1000);// 向上滑动
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.findElements(By.id("com.kuaishoudan.financer:id/iv_is_show")).get(1).click();
		AppUtil.swipeToUp(driver, 1000);// 向上滑动
		Thread.sleep(2000);

		// 抵押费
		Runtime.getRuntime().exec("adb -s " + devicename + " shell input text " + RequestPyout.getMortgage_free());
		Thread.sleep(500);
		driver.findElements(By.id("com.kuaishoudan.financer:id/text_content")).get(0).click();
		Thread.sleep(1500);
		// 解押费
		Runtime.getRuntime().exec("adb -s " + devicename + " shell input text " + RequestPyout.getSign_free());
		Thread.sleep(500);
		driver.findElements(By.id("com.kuaishoudan.financer:id/text_content")).get(1).click();
		Thread.sleep(1000);
		// 上牌抵押地
		driver.findElement(By.id("com.kuaishoudan.financer:id/tv_chekuan_shangpaidiya")).click();
		driver.findElement(By.id("com.kuaishoudan.financer:id/options3")).click();// 城市
		int width = driver.manage().window().getSize().width;
		int height = driver.manage().window().getSize().height;
		driver.swipe(width * 2 / 3, height - 80, width * 2 / 3, height - 280, 800);
		driver.findElement(By.id("com.kuaishoudan.financer:id/btnSubmit")).click();// 城市确定
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

		// 上牌方
		driver.findElement(By.id("com.kuaishoudan.financer:id/tv_chekuan_shangpaifang")).click();
		driver.findElements(By.id("com.kuaishoudan.financer:id/text_select")).get(RequestPyout.getRegistration_party())
				.click();

		// 抵押方
		driver.findElement(By.id("com.kuaishoudan.financer:id/tv_chekuan_diyafang")).click();
		driver.findElements(By.id("com.kuaishoudan.financer:id/text_select")).get(RequestPyout.getRegistration_party())
				.click();

		driver.findElements(By.id("com.kuaishoudan.financer:id/iv_is_show")).get(1).click();

	}

	// 新车杂项费用支出

	public static void testZx(AppiumDriver<AndroidElement> driver, String devicename, RequestPayout RequestPyout)
			throws InterruptedException, IOException {

		AppUtil.swipeToUp3(driver, 1000);// 向上滑动
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.findElements(By.id("com.kuaishoudan.financer:id/iv_is_show")).get(2).click();
		AppUtil.swipeToUp(driver, 1000);// 向上滑动
		// GPS安装费
		Runtime.getRuntime().exec("adb -s " + devicename + " shell input text " + RequestPyout.getGps_installation());
		Thread.sleep(500);
		driver.findElements(By.id("com.kuaishoudan.financer:id/text_content")).get(0).click();
		Thread.sleep(1500);
		// 前置利息
		Runtime.getRuntime().exec("adb -s " + devicename + " shell input text " + RequestPyout.getInterest_on_pre());
		Thread.sleep(500);
		driver.findElements(By.id("com.kuaishoudan.financer:id/text_content")).get(1).click();
		Thread.sleep(1500);
		// 退款
		Runtime.getRuntime().exec("adb -s " + devicename + " shell input text " + RequestPyout.getRefund());
		Thread.sleep(500);
		driver.findElements(By.id("com.kuaishoudan.financer:id/text_content")).get(2).click();
		Thread.sleep(1500);
		// 车价贷款(返款)
		Runtime.getRuntime().exec("adb -s " + devicename + " shell input text " + RequestPyout.getThe_car_loan());
		Thread.sleep(500);
		driver.findElements(By.id("com.kuaishoudan.financer:id/text_content")).get(3).click();
		Thread.sleep(1500);
		driver.findElements(By.id("com.kuaishoudan.financer:id/iv_is_show")).get(2).click();

	}

}
