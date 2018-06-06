package com.kuaishoudan.financer.selenium;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidElement;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.kuaishoudan.financer.bean.KSDCase;
import com.kuaishoudan.financer.bean.RequestPayout;
import com.kuaishoudan.financer.dao.UserDaoImpl;
import com.kuaishoudan.financer.util.IdCardGenerator;

public class ZcjjUtil {

	public static KSDCase sqhtZCJJ(AppiumDriver<WebElement> driver, KSDCase ksd) {
		String actualstatue = "";
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		String titletext = driver.findElement(By.xpath("//XCUIElementTypeNavigationBar/XCUIElementTypeStaticText"))
				.getText();// 标题文本

		System.out.println(titletext);
		if ("客户".equals(titletext)) {
			driver.findElements(By.className("XCUIElementTypeCell")).get(0).click();// 首页列表
		}

		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

		driver.findElements(By.className("XCUIElementTypeCell")).get(0).click();// 常规产品列表

		driver.manage().timeouts().implicitlyWait(25, TimeUnit.SECONDS);
		driver.findElement(By.id("申请合同")).click();// 申请合同
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//XCUIElementTypeOther[@name='不安装']")).click();// 不安装
																					// 选择GPS安装方式
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.findElement(By.id("xy_icon_uploadImg")).click();// 添加照片
		AppSPUtil.upload(driver);

		ksd.setStatue(getActstatue(driver));// 状态值
		List<Integer> list2 = ksd.getImgtypes();
		if (ksd.getCartype() == 0) {
			list2.add(1049);
		} else {
			list2.add(1048);
		}
		list2.add(1050);
		list2.add(1051);
		list2.add(1052);
		ksd.setImgtypes(list2);
		return ksd;
	}

	public static KSDCase zcjjHTSQQK(AppiumDriver<WebElement> driver, WebDriver webdriver, KSDCase ksd,
			String devicename) {
		String actualstatue = "";
		WebUtil.login(webdriver, ksd);// 登录
		List<Integer> list = WebOrgan.getImge2(webdriver, ksd);
		WebUtil.logout(webdriver);
		List<Integer> list2 = ksd.getImgtypes();
		int aa = 0, countImg = 0;
		Collections.sort(list);
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i) < 9) {
				List<Integer> list3 = UserDaoImpl.getImgType(list.get(i) + 7, list);
				list2.addAll(list3);
				aa = list3.size();
				countImg = aa + countImg;
			}
		}

		if (countImg == 0) {
			for (Integer type : list) {
				if (type > 99) {
					list2.add(type);
					break;
				}
			}
		}
		ksd.setImgtypes(list2);
		System.out.println("$$$" + countImg);
		ksd.setImgcount(countImg);

		driver.manage().timeouts().implicitlyWait(115, TimeUnit.SECONDS);

		String titletext = driver.findElements(By.className("XCUIElementTypeStaticText")).get(1).getText();

		System.out.println(titletext);
		if ("客户".equals(titletext)) {
			driver.findElements(By.id("com.kuaishoudan.financer:id/text_name")).get(0).click();// 首页列表
		}
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.findElements(By.id("com.kuaishoudan.financer:id/text_product")).get(0).click();// 常规产品列表

		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.findElement(By.id("申请请款")).click();// 申请请款
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.findElement(By
				.xpath("//XCUIElementTypeApplication/XCUIElementTypeWindow[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeTable/XCUIElementTypeCell/XCUIElementTypeStaticText[5]"))
				.click();
		// zhanghu列表
		try {
			Thread.sleep(300);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//XCUIElementTypeButton[@name='wd phyout more'][1]")).click();// xia
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.findElements(By.className("XCUIElementTypeCell")).get(4).click();// 上牌抵押地

		driver.manage().timeouts().implicitlyWait(55, TimeUnit.SECONDS);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		HashMap tapObject = new HashMap();
		tapObject.put("x", 380);
		tapObject.put("y", 520);
		tapObject.put("duration", 1000);
		js.executeScript("mobile: tap", tapObject);
		//

		driver.manage().timeouts().implicitlyWait(45, TimeUnit.SECONDS);
		driver.findElements(By.className("XCUIElementTypeCell")).get(5).click();// 上牌方
		driver.manage().timeouts().implicitlyWait(45, TimeUnit.SECONDS);
		driver.findElement(By.id("经销商")).click();
		driver.manage().timeouts().implicitlyWait(45, TimeUnit.SECONDS);
		driver.findElements(By.className("XCUIElementTypeCell")).get(6).click();// 抵押方
		driver.manage().timeouts().implicitlyWait(55, TimeUnit.SECONDS);
		driver.findElement(By.id("我司")).click();
		driver.manage().timeouts().implicitlyWait(45, TimeUnit.SECONDS);

		try {

			Thread.sleep(500);
			// driver.findElement(By.xpath("//XCUIElementTypeApplication[@name='快收单']/XCUIElementTypeWindow[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeTable/XCUIElementTypeCell[9]/XCUIElementTypeTextField/XCUIElementTypeTextField")
			// ).sendKeys(""+ksd.getDeduction());;//扣除款项
			//
			// Thread.sleep(500);
			AppUtil.swipeToUp(driver, 1000);// 向上滑动
			Thread.sleep(1000);
		} catch (InterruptedException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}

		driver.manage().timeouts().implicitlyWait(58, TimeUnit.SECONDS);

		WebElement upload = driver.findElement(By.xpath(
				"//XCUIElementTypeApplication[@name='快收单']/XCUIElementTypeWindow[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeTable/XCUIElementTypeCell[12]"));
		int y = upload.getLocation().getY() - 75;
		System.out.println(upload.getLocation().getY() + "," + y);

		HashMap tapObject2 = new HashMap();
		tapObject2.put("x", 100);

		tapObject2.put("y", y);
		tapObject2.put("duration", 1000);
		js.executeScript("mobile: tap", tapObject2);
		// 上传照片

		AppUtil.uploadQk(driver, ksd.getImgcount());

		RequestPayout requestPyout = ksd.getRequestpayout();

		// AppUtil.testFd(driver, devicename,requestPyout);

		// AppUtil.testDy(driver,devicename, requestPyout);

		// AppUtil.testZx(driver,devicename, requestPyout);
		try {
			Thread.sleep(300);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		driver.manage().timeouts().implicitlyWait(58, TimeUnit.SECONDS);

		driver.findElement(By.id("确认")).click();// 确定
		driver.manage().timeouts().implicitlyWait(65, TimeUnit.SECONDS);
		//
		driver.findElement(By.id("确认")).click();// 申请请款确定

		try {
			Thread.sleep(7000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.manage().timeouts().implicitlyWait(35, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//XCUIElementTypeButton[@name='确认']")).click();// 信息确认按钮
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		actualstatue = AppUtil.getStatue(driver);
		ksd.setStatue(actualstatue);
		return ksd;
	}

	public static Map<String, String> getSPname(AppiumDriver<WebElement> driver, KSDCase ksd)
			throws InterruptedException, IOException {
		Map<String, String> map = new HashMap<String, String>();
		;
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		String spname = "";
		String title = driver.findElement(By.xpath("//XCUIElementTypeNavigationBar/XCUIElementTypeStaticText"))
				.getText();
		if ("查看状态".equals(title)) {
			AppUtil.swipeToDown(driver, 1000);// 向下滑动
			System.out.println("下-----------" + title);

		} else {
			String titletext = driver.findElements(By.className("XCUIElementTypeStaticText")).get(1).getText();

			System.out.println(titletext);
			if ("客户".equals(titletext)) {
				driver.findElements(By.className("XCUIElementTypeCell")).get(0).click();// 首页列表
			}
			Thread.sleep(500);
			driver.findElements(By.className("XCUIElementTypeCell")).get(0).click();// //
																					// 常规产品列表

			driver.manage().timeouts().implicitlyWait(55, TimeUnit.SECONDS);
			driver.findElement(By.id("icon operation his")).click();// 状态审批流程

		}
		Thread.sleep(2000);
		List<WebElement> statueitems = driver.findElements(By.className("XCUIElementTypeCell"));
		for (int i = 0; i < statueitems.size(); i++) {
			String statue = statueitems.get(i).findElement(By.xpath("XCUIElementTypeStaticText[2]")).getText();
			System.out.println("@@" + statueitems.size());
			if ("正在处理".equals(statue)) {
				String name = statueitems.get(i).findElement(By.xpath("XCUIElementTypeStaticText[3]")).getText();
				// System.out.println(i+"!!!!"+name);

				String[] strs = name.split("-");
				if (strs[0].contains("BD")) {
					// ///////////////////////////////

					spname = strs[1];
					System.out.println("BD经理处理" + spname);
					map.put("name", spname);
					break;
				} else {
					String prename = statueitems.get(i + 1).findElement(By.xpath("XCUIElementTypeStaticText[3]"))
							.getText();
					String[] strspre = prename.split("-");
					spname = strs[1] + "," + strspre[1];
					System.out.println("正在处理" + spname);
					map.put("name", strs[1]);
					map.put("prename", strspre[1]);
					break;
				}
			} else if ("放款审批/已放款".equals(statue)) {
				String name = statueitems.get(i).findElement(By.xpath("XCUIElementTypeStaticText[3]")).getText();
				String[] strs = name.split("-");
				spname = strs[1] + "," + ksd.getLoginname();
				map.put("name", strs[1]);
				map.put("prename", ksd.getLoginname());
			} else if ("回款结果/已回款".equals(statue)) {
				String name = statueitems.get(i).findElement(By.xpath("XCUIElementTypeStaticText[3]")).getText();
				String[] strs = name.split("-");
				spname = strs[1] + "," + ksd.getLoginname();
				map.put("name", strs[1]);
				map.put("prename", ksd.getLoginname());
			}
		}
		return map;
	}

	// 不出合同申请请款
	public static KSDCase testBCSQQK(AppiumDriver<WebElement> driver, WebDriver webdriver, KSDCase ksd,
			String devicename) {
		WebUtil.login(webdriver, ksd);// 登录
		List<Integer> list = WebOrgan.getImge2(webdriver, ksd);
		WebUtil.logout(webdriver);
		List<Integer> list2 = ksd.getImgtypes();
		int aa = 0, countImg = 0;
		Collections.sort(list);
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i) < 9) {
				List<Integer> list3 = UserDaoImpl.getImgType(list.get(i) + 7, list);
				list2.addAll(list3);
				aa = list3.size();
				countImg = aa + countImg;
			}
		}
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

		driver.manage().timeouts().implicitlyWait(115, TimeUnit.SECONDS);

		String titletext = driver.findElements(By.className("XCUIElementTypeStaticText")).get(1).getText();
		// System.out.println(titletext);
		if ("客户".equals(titletext)) {
			driver.findElements(By.className("XCUIElementTypeCell")).get(0).click();// 首页列表
		}
		driver.manage().timeouts().implicitlyWait(35, TimeUnit.SECONDS);
		try {
			Thread.sleep(500);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		driver.findElements(By.className("XCUIElementTypeCell")).get(0).click();// changgui列表
		driver.manage().timeouts().implicitlyWait(35, TimeUnit.SECONDS);

		driver.findElement(By.id("不出合同")).click();// "不出合同"
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//XCUIElementTypeOther[@name='不安装']")).click();// 不安装
																					// 选择GPS安装方式
		driver.manage().timeouts().implicitlyWait(35, TimeUnit.SECONDS);
		driver.findElement(By.id("提交")).click();// "提交"
		driver.manage().timeouts().implicitlyWait(35, TimeUnit.SECONDS);
		driver.findElement(By.id("是")).click();// ""
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

		driver.manage().timeouts().implicitlyWait(55, TimeUnit.SECONDS);
		driver.findElement(By
				.xpath("//XCUIElementTypeApplication/XCUIElementTypeWindow[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeTable/XCUIElementTypeCell/XCUIElementTypeStaticText[5]"))
				.click();
		// zhanghu列表
		try {
			Thread.sleep(300);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		driver.manage().timeouts().implicitlyWait(55, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//XCUIElementTypeButton[@name='wd phyout more'][1]")).click();// xia
		driver.manage().timeouts().implicitlyWait(55, TimeUnit.SECONDS);
		driver.findElements(By.className("XCUIElementTypeCell")).get(1).sendKeys(ksd.getVin());
		;
		driver.manage().timeouts().implicitlyWait(25, TimeUnit.SECONDS);
		driver.findElements(By.className("XCUIElementTypeCell")).get(0).click();
		;

		driver.manage().timeouts().implicitlyWait(25, TimeUnit.SECONDS);
		driver.findElements(By.className("XCUIElementTypeCell")).get(5).click();// 上牌抵押地
		driver.manage().timeouts().implicitlyWait(55, TimeUnit.SECONDS);

		JavascriptExecutor js = (JavascriptExecutor) driver;
		HashMap tapObject = new HashMap();
		tapObject.put("x", 380);
		tapObject.put("y", 520);
		tapObject.put("duration", 1000);
		js.executeScript("mobile: tap", tapObject);
		//

		driver.manage().timeouts().implicitlyWait(45, TimeUnit.SECONDS);
		driver.findElements(By.className("XCUIElementTypeCell")).get(6).click();// 上牌方
		driver.manage().timeouts().implicitlyWait(45, TimeUnit.SECONDS);
		driver.findElement(By.id("经销商")).click();
		driver.manage().timeouts().implicitlyWait(45, TimeUnit.SECONDS);
		driver.findElements(By.className("XCUIElementTypeCell")).get(7).click();// 抵押方
		driver.manage().timeouts().implicitlyWait(55, TimeUnit.SECONDS);
		driver.findElement(By.id("我司")).click();
		driver.manage().timeouts().implicitlyWait(45, TimeUnit.SECONDS);

		try {

			Thread.sleep(500);
			// driver.findElement(By.xpath("//XCUIElementTypeApplication[@name='快收单']/XCUIElementTypeWindow[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeTable/XCUIElementTypeCell[9]/XCUIElementTypeTextField/XCUIElementTypeTextField")
			// ).sendKeys(""+ksd.getDeduction());;//扣除款项
			//
			// Thread.sleep(500);
			AppUtil.swipeToUp(driver, 1000);// 向上滑动
			Thread.sleep(1000);
		} catch (InterruptedException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}

		driver.manage().timeouts().implicitlyWait(58, TimeUnit.SECONDS);

		WebElement upload = driver.findElement(By.xpath(
				"//XCUIElementTypeApplication[@name='快收单']/XCUIElementTypeWindow[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeTable/XCUIElementTypeCell[13]"));
		int y = upload.getLocation().getY() - 75;
		System.out.println(upload.getLocation().getY() + "," + y);

		HashMap tapObject2 = new HashMap();
		tapObject2.put("x", 100);

		tapObject2.put("y", y);
		tapObject2.put("duration", 1000);
		js.executeScript("mobile: tap", tapObject2);
		// 上传照片

		AppUtil.uploadQk(driver, ksd.getImgcount());

		// RequestPayout requestPyout = ksd.getRequestpayout();

		// AppUtil.testFd(driver, devicename,requestPyout);

		// AppUtil.testDy(driver,devicename, requestPyout);

		// AppUtil.testZx(driver,devicename, requestPyout);
		try {
			Thread.sleep(300);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		driver.manage().timeouts().implicitlyWait(58, TimeUnit.SECONDS);

		driver.findElement(By.id("确认")).click();// 确定

		driver.manage().timeouts().implicitlyWait(565, TimeUnit.SECONDS);
		//
		driver.findElement(By.id("确认")).click();// 申请请款确定

		try {
			Thread.sleep(7000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.manage().timeouts().implicitlyWait(35, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//XCUIElementTypeButton[@name='确认']")).click();// 信息确认按钮
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String actualstatue = AppUtil.getStatue(driver);
		ksd.setStatue(actualstatue);
		return ksd;
	}

	// BD经理登录审批
	public static boolean loginBD(AppiumDriver<WebElement> driver, String username) {
		boolean flag = false;
		try {
			Thread.sleep(500);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
		driver.findElement(By.id("icon back")).click();// 返回
		try {
			Thread.sleep(500);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		driver.findElement(By.id("icon back")).click();// 返回

		try {
			Thread.sleep(500);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		// String titletext = driver.findElement(
		// By.xpath("//XCUIElementTypeNavigationBar/XCUIElementTypeStaticText")).getText();//
		// 标题文本

		driver.findElement(By.id("icon back")).click();// 返回

		try {
			Thread.sleep(500);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		AppUtil.logout(driver);// 退出登录
		WebElement username2 = driver.findElement(By.className("XCUIElementTypeTextField"));
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		username2.clear();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		username2.sendKeys(username);
		;
		driver.findElement(By
				.xpath("//XCUIElementTypeApplication/XCUIElementTypeWindow[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeSecureTextField"))
				.sendKeys("@123456");
		;

		driver.findElement(By.id("登 录")).click();

		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.manage().timeouts().implicitlyWait(38, TimeUnit.SECONDS);
		driver.findElement(By.id("icon baidian")).click();// 菜单
		driver.manage().timeouts().implicitlyWait(38, TimeUnit.SECONDS);
		driver.findElement(By.id("消息")).click();// 消息
		driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
		driver.findElements(By.className("XCUIElementTypeCell")).get(0).click();// 点进同意贷款详情
		driver.manage().timeouts().implicitlyWait(38, TimeUnit.SECONDS);

		driver.findElements(By.className("XCUIElementTypeButton")).get(2).click();
		// 同意按钮
		driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
		driver.findElement(By
				.xpath("//XCUIElementTypeApplication/XCUIElementTypeWindow[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeCollectionView/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeTextView"))
				.sendKeys("同意");
		driver.manage().timeouts().implicitlyWait(18, TimeUnit.SECONDS);
		driver.findElement(By.id("确定")).click();// 确定按钮
		driver.manage().timeouts().implicitlyWait(18, TimeUnit.SECONDS);
		driver.findElement(By.id("是")).click();// 提醒----确定按钮

		AppUtil.logout(driver);// 退出登录
		flag = true;
		return flag;

	}

	// 状态实际值
	public static String getActstatue(AppiumDriver<WebElement> driver) {
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		AppUtil.swipeToDown(driver, 1000);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.manage().timeouts().implicitlyWait(18, TimeUnit.SECONDS);
		String actu = driver.findElements(By.id("com.kuaishoudan.financer:id/text_status")).get(0).getText().trim();
		return actu;

	}

	/**
	 * 已请款-----返回查看状态
	 * 
	 * @param driver
	 */
	public static String getStatue(AppiumDriver<WebElement> driver) {
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.findElement(By.id("com.kuaishoudan.financer:id/toolbar_back")).click();// 返回
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
		driver.findElement(By.id("com.kuaishoudan.financer:id/toolbar_back")).click();// 返回
		String statue = ZcjjUtil.getActstatue(driver);// 状态值
		return statue;

	}

	public static void sp6App(AppiumDriver<WebElement> driver, KSDCase ksd) {
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

		driver.findElements(By.id("com.kuaishoudan.financer:id/text_product")).get(0).click();// 常规产品列表
		driver.manage().timeouts().implicitlyWait(13, TimeUnit.SECONDS);
		driver.findElement(By.id("com.kuaishoudan.financer:id/btn_archive")).click();// 归档
		driver.manage().timeouts().implicitlyWait(13, TimeUnit.SECONDS);

		// driver.findElements(By.id("com.kuaishoudan.financer:id/check_group")).get(0).click();//当面交付
		driver.findElement(By.id("com.kuaishoudan.financer:id/tv_select_type")).click();// 材料类型
		driver.manage().timeouts().implicitlyWait(13, TimeUnit.SECONDS);
		driver.findElements(By.id("com.kuaishoudan.financer:id/tv_title")).get(2).click();//
		driver.manage().timeouts().implicitlyWait(13, TimeUnit.SECONDS);
		driver.findElement(By.id("com.kuaishoudan.financer:id/cb_all")).click();
		/*
		 * for(int i=0;i<ksd.getImgcount();i++){
		 * driver.findElements(By.id("com.kuaishoudan.financer:id/cb_check")).
		 * get(i).click(); }
		 */
		//
		AppUtil.swipeToUp(driver, 1000);
		try {
			Thread.sleep(1500);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		driver.manage().timeouts().implicitlyWait(13, TimeUnit.SECONDS);
		AppUtil.uploadQk(driver, ksd.getImgcount());
		driver.manage().timeouts().implicitlyWait(13, TimeUnit.SECONDS);
		driver.findElement(By.id("com.kuaishoudan.financer:id/tv_toolbar_confirm")).click();// 提交
		driver.manage().timeouts().implicitlyWait(13, TimeUnit.SECONDS);
		// driver.findElement(By.id("com.kuaishoudan.financer:id/dialog_custom_confirm")).click();//是
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.findElement(By.id("com.kuaishoudan.financer:id/toolbar_back")).click();// 返回
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.findElement(By.id("com.kuaishoudan.financer:id/toolbar_back")).click();// 返回

	}
}
