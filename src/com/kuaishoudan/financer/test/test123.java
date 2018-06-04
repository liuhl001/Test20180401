package com.kuaishoudan.financer.test;

import java.net.MalformedURLException;

import org.openqa.selenium.WebDriver;

import com.kuaishoudan.financer.selenium.WebUtil;

public class test123 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		test123.ff();
	}
	public static void ff(){
		try {
			WebDriver driver = WebUtil.getdriver();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
