package com.kuaishoudan.financer.selenium;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
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
import com.kuaishoudan.financer.util.RandomValue;

public class AppSPUtil {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	// 申请合同**
	public static KSDCase testSQHT(AppiumDriver<WebElement> driver,
			KSDCase ksd) {
		String actualstatue = "";
		driver.manage().timeouts().implicitlyWait(155, TimeUnit.SECONDS);
		driver.findElements(By.className("XCUIElementTypeCell"))
				.get(0).click();// 首页列表
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		 
		driver.manage().timeouts().implicitlyWait(25, TimeUnit.SECONDS);
		driver.findElement(
				By.id("申请合同")).click();// 申请合同
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
 
		driver.findElement(By.xpath("//XCUIElementTypeOther[@name='不安装']"))
				 .click();// 不安装 选择GPS安装方式
	 
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.findElement(By.id("xy_icon_uploadImg"))
				.click();// 添加照片
		upload(driver);
	

		String statue=getActstatue(driver);
		ksd.setStatue(statue);
		List<Integer> list2=ksd.getImgtypes();
		if(ksd.getCartype()==0){
			list2.add(1049);
		}else{
		list2.add(1048);
		}		
		list2.add(1050);
		list2.add(1051); list2.add(1052);
		ksd.setImgtypes(list2);
		return ksd;
	}

	// (申请合同)-申请请款
	public static KSDCase testHTSQQK(AppiumDriver<WebElement> driver,WebDriver webdriver,
			KSDCase ksd,String devicename) {
		String actualstatue = "";
		driver.manage().timeouts().implicitlyWait(55, TimeUnit.SECONDS);

		WebUtil.login(webdriver, ksd );// 登录
		List<Integer> list = WebOrgan.getImge2(webdriver, ksd);
		List<Integer> list2=	ksd.getImgtypes();

		WebUtil.logout(webdriver);
		int aa=0,countImg=0;
		 Collections.sort(list); 
		for(int i=0;i<list.size();i++){
			if(list.get(i)<9){
				List<Integer> list3	=UserDaoImpl.getImgType(list.get(i)+7,list);
				list2.addAll(list3);
				aa=list3.size();
				countImg=aa+countImg;
			}
		}
 
		if(countImg==0){
			for(Integer type:list){
				if(type>99){
					list2.add(type);break;
				}
			}
		}
		ksd.setImgtypes(list2);
		System.out.println(list2.size()+"$$$"+countImg);
		ksd.setImgcount(countImg);
		driver.findElements(By.className("XCUIElementTypeCell"))
		.get(0).click();// 首页列表
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.findElement(
				By.id("申请请款")).click();// 申请请款
 
		driver.manage().timeouts().implicitlyWait(25, TimeUnit.SECONDS);
 
		driver.findElement(By.xpath("//XCUIElementTypeApplication[@name='快收单']/XCUIElementTypeWindow[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeTable/XCUIElementTypeCell/XCUIElementTypeStaticText[5]")).click();
		 // zhanghu列表
		driver.manage().timeouts().implicitlyWait(155, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//XCUIElementTypeButton[@name='wd phyout more'][1]"))
				.click();// xia
		driver.manage().timeouts().implicitlyWait(55, TimeUnit.SECONDS);
		driver.findElements(
				By.className("XCUIElementTypeCell")).get(4).click();// 上牌抵押地
		driver.manage().timeouts().implicitlyWait(55, TimeUnit.SECONDS);
		

		driver.manage().timeouts().implicitlyWait(55, TimeUnit.SECONDS);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		HashMap tapObject = new HashMap();
		tapObject.put("x", 380);
		tapObject.put("y", 520);
		tapObject.put("duration", 1000);
		js.executeScript("mobile: tap", tapObject);
 //

 
		driver.manage().timeouts().implicitlyWait(45, TimeUnit.SECONDS);
		driver.findElements(
				By.className("XCUIElementTypeCell")).get(5).click();// 上牌方
		driver.manage().timeouts().implicitlyWait(45, TimeUnit.SECONDS);
		driver.findElement(By.id("经销商"))
				 .click();
		driver.manage().timeouts().implicitlyWait(45, TimeUnit.SECONDS);
		driver.findElements(
				By.className("XCUIElementTypeCell")).get(6).click();// 抵押方
		driver.manage().timeouts().implicitlyWait(55, TimeUnit.SECONDS);
		driver.findElement(By.id("我司"))
				 .click();
		driver.manage().timeouts().implicitlyWait(45, TimeUnit.SECONDS);
		
		try {
 
			Thread.sleep(500);
//			driver.findElement(By.xpath("//XCUIElementTypeApplication[@name='快收单']/XCUIElementTypeWindow[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeTable/XCUIElementTypeCell[9]/XCUIElementTypeTextField/XCUIElementTypeTextField")
//					).sendKeys(""+ksd.getDeduction());;//扣除款项
//
//			Thread.sleep(500);
			AppUtil.swipeToUp(driver, 1000);// 向上滑动
			Thread.sleep(1000);
		} catch ( InterruptedException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}

		driver.manage().timeouts().implicitlyWait(58, TimeUnit.SECONDS);
		 
 
		WebElement  upload=driver.findElement(By.xpath("//XCUIElementTypeApplication[@name='快收单']/XCUIElementTypeWindow[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeTable/XCUIElementTypeCell[12]"));
		int y=upload.getLocation().getY()-75;
		System.out.println(upload.getLocation().getY()+","+y);

 
		HashMap tapObject2 = new HashMap();
		tapObject2.put("x", 100);
	 
		tapObject2.put("y",y);  
		tapObject2.put("duration", 1000);
		js.executeScript("mobile: tap", tapObject2);
		//上传照片

		
		AppUtil.uploadQk(driver,ksd.getImgcount());
		
		RequestPayout requestPyout = ksd.getRequestpayout();
		
	
		//	AppUtil.testFd(driver, devicename,requestPyout);
	
		//	AppUtil.testDy(driver,devicename, requestPyout);

	//		AppUtil.testZx(driver,devicename, requestPyout);
		
		
		driver.manage().timeouts().implicitlyWait(58, TimeUnit.SECONDS);
		
		driver.findElement(
				By.id("确认"))
				.click();// 确定
		driver.manage().timeouts().implicitlyWait(65, TimeUnit.SECONDS);
		//
		driver.findElement(By.id("确认"))
				.click();// 申请请款确定

		try {
			Thread.sleep(7000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.manage().timeouts().implicitlyWait(35, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//XCUIElementTypeButton[@name='确认']"))
				.click();// 信息确认按钮
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

	// 不出合同申请请款
	public static KSDCase testBCSQQK(AppiumDriver<WebElement> driver,WebDriver webdriver,
			KSDCase ksd,String devicename) {
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		WebUtil.login(webdriver, ksd );// 登录
		List<Integer> list = WebOrgan.getImge2(webdriver, ksd);
		WebUtil.logout(webdriver);
		List<Integer> list2=ksd.getImgtypes(); 
		int aa=0,countImg=0;
		 Collections.sort(list);  
		for(int i=0;i<list.size();i++){
			if(list.get(i)<9){
				List<Integer> list3=UserDaoImpl.getImgType(list.get(i)+7,list);
				list2.addAll(list3);
				aa=list3.size();
				countImg=aa+countImg;
			}
		}
		if(countImg==0){
			for(Integer type:list){
				if(type>99){
					list2.add(type);break;
				}
			}
		}
		ksd.setImgtypes(list2);
		System.out.println("$$$"+countImg);
		ksd.setImgcount(countImg);
		driver.manage().timeouts().implicitlyWait(45, TimeUnit.SECONDS);
		driver.findElements(By.id("com.kuaishoudan.financer:id/text_name"))
				.get(0).click();// 首页列表
		driver.manage().timeouts().implicitlyWait(45, TimeUnit.SECONDS);

		driver.findElement(
				By.id("com.kuaishoudan.financer:id/tv_not_apply_compact"))
				.click();// 不出合同
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		int gxs = driver.findElements(
				By.id("com.kuaishoudan.financer:id/check_group")).size();// 勾选数
		System.out.println("gxs" + gxs);
		driver.findElements(By.id("com.kuaishoudan.financer:id/check_group"))
				.get(gxs - 1).click();// 不安装 选择GPS安装方式
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.findElement(By.id("com.kuaishoudan.financer:id/toolbar_submit"))
				.click();// 提交
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.findElement(
				By.id("com.kuaishoudan.financer:id/dialog_custom_confirm"))
				.click();// 是按钮

		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

		/*
		 * driver.findElement(
		 * By.id("com.kuaishoudan.financer:id/btn_apply_request")).click();//
		 * 申请请款445整数进位
		 */
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.findElement(
				By.id("com.kuaishoudan.financer:id/text_request_pay_name"))
				.click();// dian账号名
		driver.findElement(By.id("com.kuaishoudan.financer:id/iv_is_show"))
				.click();// xia下标
		driver.findElement(
				By.id("com.kuaishoudan.financer:id/et_chekuan_chejia"))
				.sendKeys(ksd.getVin());// 车架号
		driver.findElement(
				By.id("com.kuaishoudan.financer:id/ll_chekuan_shangpaidiya"))
				.click();// 上牌抵押地
		driver.findElement(By.id("com.kuaishoudan.financer:id/options3"))
				.click();// 城市
		int width = driver.manage().window().getSize().width;
		int height = driver.manage().window().getSize().height;
		driver.swipe(width * 2 / 3, height - 80, width * 2 / 3, height - 280,
				800);
		driver.findElement(By.id("com.kuaishoudan.financer:id/btnSubmit"))
				.click();// 城市确定
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.findElement(
				By.id("com.kuaishoudan.financer:id/tv_chekuan_shangpaifang"))
				.click();// 上牌方
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.findElements(By.id("com.kuaishoudan.financer:id/text_select"))
				.get(ksd.getRegisttype() - 1).click();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.findElement(
				By.id("com.kuaishoudan.financer:id/tv_chekuan_diyafang"))
				.click();// 抵押方
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.findElements(By.id("com.kuaishoudan.financer:id/text_select"))
				.get(ksd.getPledge() - 1).click();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		try {
			Runtime.getRuntime().exec(
					"adb -s " + devicename + " shell input text "
							+ ksd.getDeduction());
			Thread.sleep(500);
			driver.findElement(By.id("com.kuaishoudan.financer:id/tv_chekuan_kouchuxiang")).click();//扣除款项
		} catch (IOException | InterruptedException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
	

		AppUtil.swipeToUp(driver, 1000);// 向上滑动		
		AppUtil.uploadQk(driver,ksd.getImgcount());
	
		RequestPayout requestPyout = ksd.getRequestpayout();
		try {
	
			AppUtil.testFd(driver, devicename,requestPyout);
		//	AppUtil.testDy(driver,devicename, requestPyout);
		//	AppUtil.testZx(driver,devicename, requestPyout);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
		//	e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
		//	e1.printStackTrace();
		}
		
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.findElement(
				By.id("com.kuaishoudan.financer:id/tv_toolbar_confirm"))
				.click();// 确定
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		//
		driver.findElement(By.id("com.kuaishoudan.financer:id/tv_confirm"))
				.click();// 申请请款确定

		try {
			Thread.sleep(7000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.findElement(By.id("com.kuaishoudan.financer:id/tv_countdown"))
				.click();// 倒计时确认

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.findElement(By.id("com.kuaishoudan.financer:id/toolbar_back"))
				.click();// 返回
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.findElement(By.id("com.kuaishoudan.financer:id/toolbar_back"))
				.click();// 返回
		String actualstatue = getActstatue(driver);
		ksd.setStatue(actualstatue);
		return ksd;
	}

	/**
	 * 上传照片 申请合同
	 * 
	 * @param driver
	 * @return
	 */
	public static String upload(AppiumDriver<WebElement> driver) {
		String acstatue = "";
		try {
			 
			driver.findElement(
					By.id("从相册选取"))
					.click();// 从相册选择
		
			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
			List<WebElement> cs=	driver.findElements(By.className("XCUIElementTypeCell"));
		 
			cs.get(4).click();
			cs.get(5).click();
			cs.get(5).click();
			cs.get(5).click();
			cs.get(6).click();
			cs.get(6).click();
			cs.get(7).click();
			cs.get(7).click();
			Thread.sleep(2000);
			driver.findElement(By.xpath("//XCUIElementTypeApplication[@name='快收单']/XCUIElementTypeWindow[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeToolbar/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeButton"))
			.click();// 两种证上传——确定按钮
	//		AppUtil.swipeToUp(driver, 1000);// 向上滑动
			Thread.sleep(15000);
			driver.manage().timeouts().implicitlyWait(18, TimeUnit.SECONDS);
			driver.findElement(
					By.id("提交"))
					.click();// 上传完照片-确认按钮
			driver.manage().timeouts().implicitlyWait(18, TimeUnit.SECONDS);
			Thread.sleep(1000);
			driver.findElement(
					By.id("确定"))
					.click();// 提醒确定是
			Thread.sleep(1000);
  

		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			 e.printStackTrace();
		} catch (org.openqa.selenium.WebDriverException e) {
			e.printStackTrace();
			
		}

		try {

			driver.findElement(
					By.id("icon back")).click();// 返回按钮
	
		} catch (org.openqa.selenium.WebDriverException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
		}
		
		return acstatue;
	}

	// BD经理登录审批
	public static boolean loginBD(AppiumDriver<WebElement> driver,
			String username) {
		boolean flag = false;
		try {
			Thread.sleep(500);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
		driver.findElement(By.id("icon back"))
				.click();// 返回
		try {
			Thread.sleep(500);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		driver.findElement(By.id("icon back"))
				.click();// 返回
		driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
		try {
			Thread.sleep(500);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String titletext = driver.findElement(
				By.xpath("//XCUIElementTypeNavigationBar/XCUIElementTypeStaticText")).getText();// 标题文本

		if ("贷款详情".equals(titletext)) {
			driver.findElement(
					By.id("icon back")).click();// 返回
		}
		AppUtil.logout(driver);// 退出登录
		WebElement username2=driver.findElement(By.className("XCUIElementTypeTextField"));
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		username2.clear();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		username2.sendKeys(username);;
		driver.findElement(By.xpath("//XCUIElementTypeApplication/XCUIElementTypeWindow[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeSecureTextField"))
		.sendKeys("@123456");;
 
		driver.findElement(By.id("登 录"))
				.click();

		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.manage().timeouts().implicitlyWait(38, TimeUnit.SECONDS);
		driver.findElement(By.id("icon baidian"))
				.click();// 菜单
		driver.manage().timeouts().implicitlyWait(38, TimeUnit.SECONDS);
		driver.findElement(
				By.id("消息"))
				.click();// 消息
		driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
		driver.findElements(By.className("XCUIElementTypeCell"))
				.get(0).click();// 点进同意贷款详情
		driver.manage().timeouts().implicitlyWait(38, TimeUnit.SECONDS);

	driver.findElements(By.className("XCUIElementTypeButton")).get(2).click();		
		// 同意按钮
		driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//XCUIElementTypeApplication/XCUIElementTypeWindow[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeCollectionView/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeTextView")).sendKeys("同意");
		driver.manage().timeouts().implicitlyWait(18, TimeUnit.SECONDS);
		driver.findElement(By.id("确定"))
				.click();// 确定按钮
		driver.manage().timeouts().implicitlyWait(18, TimeUnit.SECONDS);
		driver.findElement(
				By.id("是"))
				.click();// 提醒----确定按钮

		AppUtil.logout(driver);// 退出登录
		flag = true;
		return flag;

	}

	public static Map<String, String> getSPname(
			AppiumDriver<WebElement> driver,KSDCase ksd) throws InterruptedException,
			IOException {
		Map<String, String> map = new HashMap<String, String>();
		;
		driver.manage().timeouts().implicitlyWait(125, TimeUnit.SECONDS);
		String spname = "";
		String title = driver.findElement(
				By.xpath("//XCUIElementTypeNavigationBar/XCUIElementTypeStaticText")).getText();
		if ("查看状态".equals(title)) {
			AppUtil.swipeToDown(driver, 1000);// 向下滑动
		//	System.out.println("下-----------" + title);

		} else {

			driver.findElements(By.className("XCUIElementTypeCell"))
			.get(0).click();// 首页列表

			driver.manage().timeouts().implicitlyWait(45, TimeUnit.SECONDS);
			driver.findElement(
					By.id("icon businessMore"))
					.click();// 状态审批流程
			driver.manage().timeouts().implicitlyWait(45, TimeUnit.SECONDS);
			driver.findElement(
					By.id("查看进度"))
					.click();// 查看进度
		//	AppUtil.swipeToDown(driver, 1000);// 向下滑动
		}
		Thread.sleep(2500);
		List<WebElement> statueitems = driver.findElements(By
				.className("XCUIElementTypeCell"));
		System.out.println("@@" + statueitems.get(0)
				.findElement(
						By.xpath("XCUIElementTypeStaticText[1]"))
				.getText()+statueitems.get(0)
				.findElement(
						By.xpath("XCUIElementTypeStaticText[2]"))
				.getText()+statueitems.get(0)
				.findElement(
						By.xpath("XCUIElementTypeStaticText[3]"))
				.getText());
		for (int i = 0; i < statueitems.size(); i++) {
			String statue = statueitems
					.get(i)
					.findElement(
							By.xpath("XCUIElementTypeStaticText[2]"))
					.getText();
		
			if ("正在处理".equals(statue)) {
				String name = statueitems.get(i)
						.findElement(
								By.xpath("XCUIElementTypeStaticText[3]"))
						.getText();
		//		System.out.println("!!!!"+name);
 
				String[] strs = name.split("-");
				if (strs[0].contains("BD")) {
					// ///////////////////////////////

					spname = strs[1];
					System.out.println("BD经理处理" + spname);
					map.put("name", spname);
					break;
				} else {
					String prename = statueitems
							.get(i + 1)
							.findElement(
									By.xpath("XCUIElementTypeStaticText[3]"))
							.getText();
					String[] strspre = prename.split("-");
					spname = strs[1] + "," + strspre[1];
		 			System.out.println("正在处理" + spname);
					map.put("name", strs[1]);
					map.put("prename", strspre[1]);
					break;
				}
			} else if ("放款审批/已放款".equals(statue)) {
				String name = statueitems
						.get(i)
						.findElement(
								By.xpath("XCUIElementTypeStaticText[3]"))
						.getText();
				String[] strs = name.split("-");
				spname = strs[1] + "," + ksd.getLoginname();//	spname = strs[1] + "," + "刘浩亮";
				map.put("name", strs[1]);
				map.put("prename", ksd.getLoginname());
			} else if ("回款结果/已回款".equals(statue)) {
				String name = statueitems
						.get(i)
						.findElement(
								By.xpath("XCUIElementTypeStaticText[3]"))
						.getText();
				String[] strs = name.split("-");
				spname = strs[1] + "," + ksd.getLoginname();
				map.put("name", strs[1]);
				map.put("prename", ksd.getLoginname());
			}
		}
		// return spname;
		return map;
	}

	// 状态实际值
	public static String getActstatue(AppiumDriver<WebElement> driver) {
//		try {
//			Thread.sleep(500);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		AppUtil.swipeToDown(driver, 1000);
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String actu =driver.findElement(By.xpath("//XCUIElementTypeCell/XCUIElementTypeStaticText")).getText().trim();
		return actu;

	}
	public static void sp6App(AppiumDriver<WebElement> driver,KSDCase ksd){
		driver.findElements(By.className("XCUIElementTypeCell"))
		.get(0).click();// 首页列表
		driver.manage().timeouts().implicitlyWait(13, TimeUnit.SECONDS);
		driver.findElement(By.id("归档")).click();//归档
		driver.manage().timeouts().implicitlyWait(13, TimeUnit.SECONDS);

		driver.findElement(By.id("仅图像材料")).click();//材料类型
		driver.manage().timeouts().implicitlyWait(33, TimeUnit.SECONDS);
		driver.findElement(By.id("实物材料+图像材料")).click();//
		driver.manage().timeouts().implicitlyWait(33, TimeUnit.SECONDS);
		driver.findElement(By.id("xy icon unselected")).click();
	/*	for(int i=0;i<ksd.getImgcount();i++){
			driver.findElements(By.id("com.kuaishoudan.financer:id/cb_check")).get(i).click();
		}*/
	//			
		AppUtil.swipeToUp(driver, 1000);
		try {
			Thread.sleep(500);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		driver.manage().timeouts().implicitlyWait(13, TimeUnit.SECONDS);
		driver.findElement(
				By.id("xy_icon_uploadImg"))
				.click();// 上传照片
		AppUtil.uploadQk(driver,ksd.getImgcount());
		driver.manage().timeouts().implicitlyWait(13, TimeUnit.SECONDS);
		driver.findElement(By.id("提交")).click();//提交
		driver.manage().timeouts().implicitlyWait(13, TimeUnit.SECONDS);	//	driver.findElement(By.id("com.kuaishoudan.financer:id/dialog_custom_confirm")).click();//是
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
		// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.findElement(By.id("icon back")).click();//返回
	}

}
