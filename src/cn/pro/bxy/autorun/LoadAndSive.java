package cn.pro.bxy.autorun;

import java.io.IOException;
import java.io.InputStream;
import java.rmi.RemoteException;
import java.text.ParseException;
import java.util.Properties;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import cn.pro.bxy.*;
import cn.pro.bxy.common.*;

public class LoadAndSive {

	// public static void main(String[] args) throws ParseException {
	// // TODO Auto-generated method stub
	//
	// }
	private String properFile;

	public String getProperFile() {
		return properFile;
	}

	public void setProperFile(String properFile) {
		this.properFile = properFile;
	}

	// 历史表定时操作
	public int doLoadAndSive2() throws IOException {
		System.out.println("doLoadAndSive2-properFile:" + properFile);
		Properties prop = new SystemUtils().getProperties(properFile);

		String webserviceUser = prop.getProperty("webserviceUser");
		String webservicePassword = prop.getProperty("webservicePassword");
		int pageNumber = Integer.parseInt(prop
				.getProperty("oneTimeLoadPageSize"));
		String wsdlAddress = prop.getProperty("webserviceAddress");
		int iNetType = Integer.parseInt(prop.getProperty("isLAN"));
		int mmCount = 0;
		int pageIndex = 1;
		int IsLastPage = 1;// 0 means its the end of page
		int returnNum = 0;
		JsonUtils ju = new JsonUtils(wsdlAddress);
		ju.setProperFile(this.properFile);

		System.out.println(this.properFile + "," + wsdlAddress);
		String token = ju.login2(webserviceUser, webservicePassword);

		while (IsLastPage != 0) {

			String deviceInfo = ju.loadJson2(token, pageIndex, pageNumber);

			JSONObject jobj = ju.str2json(deviceInfo);
			IsLastPage = jobj.getInt("IsLastPage");
			pageIndex++; // =pageIndex+pageIndex*pageNumber;
			String result = jobj.get("result").toString();
			// result=ju.gbk2utf8(result);
			result = result.substring(1, result.length() - 1);
			JSONArray jArray = ju.str2JsonArry(result);
			MysqTools mt = new MysqTools();
			try {
				mmCount += jArray.size();
				String netType = "";
				if (iNetType == 1)
					netType = netType + "内网";
				else
					netType = netType + "外网";
				// 更新历史表
				returnNum = mt.siveActiveDeviceInfo2History(jArray, netType);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		MysqTools mt1 = new MysqTools();
		try {
			mt1.SetLoginfo("设备历史信息定时更新操作成功!");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("mmCount:" + mmCount);
		System.out.println(" All works is cover ");

		return returnNum;//
	}

	// 历史表定时操作
	public int doLoadAndSive2ByPerson() throws IOException {
		System.out.println("doLoadAndSive2-properFile:" + properFile);
		Properties prop = new SystemUtils().getProperties(properFile);

		String webserviceUser = prop.getProperty("webserviceUser");
		String webservicePassword = prop.getProperty("webservicePassword");
		int pageNumber = Integer.parseInt(prop
				.getProperty("oneTimeLoadPageSize"));
		String wsdlAddress = prop.getProperty("webserviceAddress");
		int iNetType = Integer.parseInt(prop.getProperty("isLAN"));
		int mmCount = 0;
		int pageIndex = 1;
		int IsLastPage = 1;// 0 means its the end of page
		int returnNum = 0;
		JsonUtils ju = new JsonUtils(wsdlAddress);
		ju.setProperFile(this.properFile);

		System.out.println(this.properFile + "," + wsdlAddress);
		String token = ju.login2(webserviceUser, webservicePassword);

		while (IsLastPage != 0) {

			String deviceInfo = ju.loadJson2(token, pageIndex, pageNumber);

			JSONObject jobj = ju.str2json(deviceInfo);
			IsLastPage = jobj.getInt("IsLastPage");
			pageIndex++; // =pageIndex+pageIndex*pageNumber;
			String result = jobj.get("result").toString();
			// result=ju.gbk2utf8(result);
			// result = result.substring(1, result.length() - 1);
			JSONArray jArray = ju.str2JsonArry(result);
			MysqTools mt = new MysqTools();
			try {
				mmCount += jArray.size();
				String netType = "";
				if (iNetType == 1)
					netType = netType + "内网";
				else
					netType = netType + "外网";
				// 首先更新基本表，然后更新历史表
				returnNum = mt.siveActiveDeviceInfo2History(jArray, netType);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		MysqTools mt1 = new MysqTools();
		try {
			mt1.SetLoginfo("设备历史信息定时更新操作成功!");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("mmCount:" + mmCount);
		System.out.println(" All works is cover ");

		return returnNum;//
	}

	// ClientUser定时操作
	public int doLoadAndSive3() throws IOException {
		System.out.println("doLoadAndSive3-properFile:" + properFile);
		Properties prop = new SystemUtils().getProperties(properFile);

		String webserviceUser = prop.getProperty("webserviceUser");
		String webservicePassword = prop.getProperty("webservicePassword");
		int pageNumber = Integer.parseInt(prop
				.getProperty("oneTimeLoadPageSize"));
		String wsdlAddress = prop.getProperty("webserviceAddress");
		int mmCount = 0;
		int pageIndex = 1;
		int IsLastPage = 1;// 0 means its the end of page
		int returnNum = 0;
		JsonUtils ju = new JsonUtils(wsdlAddress);
		ju.setProperFile(this.properFile);
		String token = ju.login2(webserviceUser, webservicePassword);

		while (IsLastPage != 0) {

			String deviceInfo = ju.loadJson3(token, pageIndex, pageNumber);

			JSONObject jobj = ju.str2json(deviceInfo);
			IsLastPage = jobj.getInt("IsLastPage");
			pageIndex++; // =pageIndex+pageIndex*pageNumber;
			String result = jobj.get("result").toString();
			// result=ju.gbk2utf8(result);
			result = result.substring(1, result.length() - 1);
			JSONArray jArray = ju.str2JsonArry(result);
			MysqTools mt = new MysqTools();
			try {
				mmCount += jArray.size();
				returnNum = mt.siveClientUser(jArray);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		MysqTools mt1 = new MysqTools();
		try {
			mt1.SetLoginfo("用户信息定时更新操作成功!");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("mmCount:" + mmCount);
		System.out.println(" All works is cover ");

		return returnNum;//
	}

	// ClientGroup定时操作
	public int doLoadAndSive4() throws IOException {
		System.out.println("doLoadAndSive4-properFile:" + properFile);
		Properties prop = new SystemUtils().getProperties(properFile);

		String webserviceUser = prop.getProperty("webserviceUser");
		String webservicePassword = prop.getProperty("webservicePassword");
		int pageNumber = Integer.parseInt(prop
				.getProperty("oneTimeLoadPageSize"));
		String wsdlAddress = prop.getProperty("webserviceAddress");
		int mmCount = 0;
		int pageIndex = 1;
		int IsLastPage = 1;// 0 means its the end of page
		int returnNum = 0;
		JsonUtils ju = new JsonUtils(wsdlAddress);
		ju.setProperFile(this.properFile);
		String token = ju.login2(webserviceUser, webservicePassword);
		while (IsLastPage != 0) {

			String deviceInfo = ju.loadJson4(token, pageIndex, pageNumber);

			JSONObject jobj = ju.str2json(deviceInfo);
			IsLastPage = jobj.getInt("IsLastPage");
			pageIndex++; // =pageIndex+pageIndex*pageNumber;
			String result = jobj.get("result").toString();
			// result=ju.gbk2utf8(result);
			result = result.substring(1, result.length() - 1);
			JSONArray jArray = ju.str2JsonArry(result);
			// 寮�瀛樻暟鎹�
			MysqTools mt = new MysqTools();
			try {
				mmCount += jArray.size();
				returnNum = mt.siveClientGroup(jArray);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		MysqTools mt1 = new MysqTools();
		try {
			mt1.SetLoginfo("机构信息定时更新操作成功!");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("mmCount:" + mmCount);
		System.out.println(" All works is cover ");

		return returnNum;//
	}

	// 设备信息表关联信息定时更新操作
	public int doLoadAndSive5() throws IOException {
		int returnNum = 0;
		MysqTools mt = new MysqTools();
		try {
			returnNum = mt.SetUserGroupId();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		MysqTools mt1 = new MysqTools();
		try {
			mt1.SetLoginfo("用户信息表关联信息定时更新操作成功!");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(" All works is cover ");

		return returnNum;//
	}

	// 设备信息表关联信息定时更新操作
	public int doLoadAndSive6() throws IOException {
		int returnNum = 0;
		MysqTools mt = new MysqTools();
		try {
			returnNum = mt.SetDeviceGroup();
			returnNum = mt.SetDevicehistoryGroup();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		MysqTools mt1 = new MysqTools();
		try {
			mt1.SetLoginfo("设备信息表关联信息定时更新操作成功!");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println(" All works is cover ");

		return returnNum;//
	}

	// 设备基本表定时到做
	public int doLoadLastNewDateAndSive() throws IOException {

		System.out.println("LastNewDateAndSive-properFile:" + properFile);
		Properties prop = new SystemUtils().getProperties(properFile);

		String webserviceUser = prop.getProperty("webserviceUser");
		String webservicePassword = prop.getProperty("webservicePassword");
		int pageNumber = Integer.parseInt(prop.getProperty("oneTimeLoadPageSize"));
		String wsdlAddress = prop.getProperty("webserviceAddress");
		int iNetType = Integer.parseInt(prop.getProperty("isLAN"));
		int pageIndex = 1;
		int IsLastPage = 1;// 0 means its the end of page
		int returnNum = 0;
		JsonUtils ju = new JsonUtils(wsdlAddress);
		ju.setProperFile(this.properFile);
		String token = ju.login(webserviceUser, webservicePassword);
		while (IsLastPage != 0) {

			String deviceInfo = ju.loadJson(token, pageIndex, pageNumber);

			JSONObject jobj = ju.str2json(deviceInfo);
			IsLastPage = jobj.getInt("IsLastPage");
			pageIndex++; // =pageIndex+pageIndex*pageNumber;
			String result = jobj.get("result").toString();
			// result=ju.gbk2utf8(result);
			result = result.substring(1, result.length() - 1);
			JSONArray jArray = ju.str2JsonArry(result);
			MysqTools mt = new MysqTools();
			try {
				String netType = "";
				if (iNetType == 1)
					netType = netType + "内网";
				else
					netType = netType + "外网";
				returnNum = mt.siveDeviceBaseInfo(jArray, netType);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		MysqTools mt1 = new MysqTools();
		try {
			mt1.SetLoginfo("基本设备信息更新成功!");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(" All works is cover ");

		return returnNum;//
	}
	public int doLoadLastNewDateAndSiveByPerson() throws IOException {

		System.out.println("LastNewDateAndSive-properFile:" + properFile);
		Properties prop = new SystemUtils().getProperties(properFile);

		String webserviceUser = prop.getProperty("webserviceUser");
		String webservicePassword = prop.getProperty("webservicePassword");
		int pageNumber = Integer.parseInt(prop
				.getProperty("oneTimeLoadPageSize"));
		String wsdlAddress = prop.getProperty("webserviceAddress");
		int iNetType = Integer.parseInt(prop.getProperty("isLAN"));
		int pageIndex = 1;
		int IsLastPage = 1;// 0 means its the end of page
		int returnNum = 0;
		JsonUtils ju = new JsonUtils(wsdlAddress);
		ju.setProperFile(this.properFile);
		String token = ju.login(webserviceUser, webservicePassword);
		while (IsLastPage != 0) {

			String deviceInfo = ju.loadJson(token, pageIndex, pageNumber);

			JSONObject jobj = ju.str2json(deviceInfo);
			IsLastPage = jobj.getInt("IsLastPage");
			pageIndex++; // =pageIndex+pageIndex*pageNumber;
			String result = jobj.get("result").toString();
			// result=ju.gbk2utf8(result);
			// result = result.substring(1, result.length() - 1);
			JSONArray jArray = ju.str2JsonArry(result);
			MysqTools mt = new MysqTools();
			try {
				String netType = "";
				if (iNetType == 1)
					netType = netType + "内网";
				else
					netType = netType + "外网";
				returnNum = mt.siveDeviceBaseInfo(jArray, "");
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		MysqTools mt1 = new MysqTools();
		try {
			mt1.SetLoginfo("基本设备信息更新成功!");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(" All works is cover ");

		return returnNum;//
	}
}
