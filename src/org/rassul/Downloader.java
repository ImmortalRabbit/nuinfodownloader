package org.rassul;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.lang.reflect.Type;
import java.text.ParseException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

public class Downloader implements BundleNU{
	private static final String COOKIE = "_ga=GA1.3.391739043.1484594661; JSESSIONID=0000wEOfwH5wu0KN0owREXEuhQF:18ojq97bn; _gat=1; LtpaToken2=+1oWO7azicg5e43g19FwN4kQMFqaSevPeTgnsYvE+pK+TgppAQu+isvebCs6evGWISB43oJ6JBNDom8viQEhq1pkW9r3hU8wsYwbqe9p3cLH77NUMY97WG454HDYeL0SDnCbtRF313kQvxdJmDPpKM9Varw/lvf3M2xa8kiebYWFAKA4yyhrdzJiwr6zVlgb/gmMujAWtFGPy3kNRRYfay8ACOu7HyTlDxbwTT92JbkMNvPGRQVBuE+83KaeEew+ZwRG/bdjAcdXH2h4+Sdrnc5HMGndB+ak0AjBp3sx0bCLLXNvUm6vwJt+LWr8Rd5Z8Dc8E8kZ5pN9PDTSr0JmhX/xvQssjg7ldFpL/yGhiKs+ToW6cpM/xtCyGdHmo2xzUumx4MbTtdB3mi+byZ33DthhRwq/DW0QOgL7PLNLSmzvUCoC+1eAveVMJojD7fFamQ4ffrmGNi8hLV5/ejtcg0ASd6eP65Ufx4jMVwChw5hXbdJzqSL11jcVoxli5FkCy2RE5s2+TphpNjBFg+fgQkS9SNLsJKoULydALNogLDOpntahwgHFX57NTMSpB7BRfgs+S5EFNt0/XP+eqJmoEHB+M3QcmxIRR8eBcJU0WI36aF6AZjnHCxNlFo1LpB/nVsISX3bI6LMvx3C2SJ3haycjHfrVp+9SKJGkV8gwBxWDUEXKjGk6gsjDoAFRUQKQbElzMluO0Ieen0FC3uLSjg==; usersessionid=5e949c425991145da440e0d15e925f26b802984bd2048fda0dd23d38cec4a6f5469a7f3d2d2d42a7563f6b7eb2269674e371948ec1fcb110aca17036ebeecf77df67bee67739eb3779d4e93339d8b02525b7434751c26e0622c7cf619ffddd50bd015a812dec00931b65219c38c8ccbf053818842892a32331227704dfd6146c";

	public static String executePost(String targetURL, String urlParameters) {

	  HttpURLConnection connection = null;
	  try {

	    //Create connection
		URL url = new URL(targetURL);
	    connection = (HttpURLConnection) url.openConnection();
	    connection.setRequestMethod("POST");
	    connection.setRequestProperty("Content-Type",
		"application/x-www-form-urlencoded");
	    connection.setRequestProperty("Cookie",COOKIE);
	    connection.setRequestProperty("X-Requested-With","XMLHttpRequest");
	    connection.setRequestProperty("Referer","http://my.nu.edu.kz/.PhoneBookPortlet/PhoneBookPortletRedirectServlet");
	    connection.setRequestProperty("Content-Length",
		Integer.toString(urlParameters.getBytes().length));
	    connection.setRequestProperty("Content-Language", "en-US");

	    connection.setUseCaches(false);
	    connection.setDoOutput(true);

	    //Send request
	    DataOutputStream wr = new DataOutputStream (
		connection.getOutputStream());
	    wr.writeBytes(urlParameters);
	    wr.close();

	    //Get Response
	    InputStream is = connection.getInputStream();
	    BufferedReader rd = new BufferedReader(new InputStreamReader(is));
	    StringBuilder response = new StringBuilder(); // or StringBuffer if Java version 5+
	    String line;
	    while ((line = rd.readLine()) != null) {
	      response.append(line);
	      response.append('\r');
	    }
	    rd.close();
	    return response.toString();
	  } catch (Exception e) {
	    e.printStackTrace();
	    return null;
	  } finally {
	    if (connection != null) {
	      connection.disconnect();
	    }
	  }
	}

	@Override
	public void getSST() {
		System.out.println("getSST is called");
		String response = executePost("http://my.nu.edu.kz/.PhoneBookPortlet/UsernameServlet",
				"username=&organisationNumber=KS_ZUP_NU1&organisationName=%D0%9D%D0%B0%D0%B7%D0%B0%D1%80%D0%B1%D0%B0%D0%B5%D0%B2+%D0%A3%D0%BD%D0%B8%D0%B2%D0%B5%D1%80%D1%81%D0%B8%D1%82%D0%B5%D1%82&departments=&linkSelected=&schoolVal=SST&school=School+of+Science+and+Technology&courses=Undergraduate&usertype=Students&maxRows=25");
		if(response == null) {
			System.out.println("Response is null for SST...");
			return;
		}
		System.out.println(response);
		Type wrapperType = new TypeToken<List<WrapperJson>>() {}.getType();
		List<WrapperJson> responseJson = new Gson().fromJson(response, wrapperType);
		processResponse(responseJson,"SST");
	}

	@Override
	public void getSHSS() {
		System.out.println("getSHSSis called");
		String response = executePost("http://my.nu.edu.kz/.PhoneBookPortlet/UsernameServlet",
				"username=&organisationNumber=KS_ZUP_NU1&organisationName=%D0%9D%D0%B0%D0%B7%D0%B0%D1%80%D0%B1%D0%B0%D0%B5%D0%B2+%D0%A3%D0%BD%D0%B8%D0%B2%D0%B5%D1%80%D1%81%D0%B8%D1%82%D0%B5%D1%82&departments=&linkSelected=&schoolVal=SHSS&school=School+of+Humanities+and+Social+Sciences&courses=Undergraduate&usertype=Students&maxRows=25");
		if(response == null) {
			System.out.println("Response is null for SHSS...");
			return;
		}
		System.out.println(response);
		Type wrapperType = new TypeToken<List<WrapperJson>>() {}.getType();
		List<WrapperJson> responseJson = new Gson().fromJson(response, wrapperType);
		processResponse(responseJson,"SHSS");
	}

	@Override
	public void getSENG() {
		System.out.println("getSENGis called");
		String response = executePost("http://my.nu.edu.kz/.PhoneBookPortlet/UsernameServlet",
				"username=&organisationNumber=KS_ZUP_NU1&organisationName=%D0%9D%D0%B0%D0%B7%D0%B0%D1%80%D0%B1%D0%B0%D0%B5%D0%B2+%D0%A3%D0%BD%D0%B8%D0%B2%D0%B5%D1%80%D1%81%D0%B8%D1%82%D0%B5%D1%82&departments=&linkSelected=&schoolVal=SEng&school=School+of+Engineering&courses=Undergraduate&usertype=Students&maxRows=25");
		if(response == null) {
			System.out.println("Response is null for SENG...");
			return;
		}
		System.out.println(response);
		Type wrapperType = new TypeToken<List<WrapperJson>>() {}.getType();
		List<WrapperJson> responseJson = new Gson().fromJson(response, wrapperType);
		processResponse(responseJson,"SENG");
	}

	@Override
	public void getFoundation() {
		System.out.println("getFoundation is called");
		String response = executePost("http://my.nu.edu.kz/.PhoneBookPortlet/UsernameServlet",
				"username=&organisationNumber=KS_ZUP_NU1&organisationName=%D0%9D%D0%B0%D0%B7%D0%B0%D1%80%D0%B1%D0%B0%D0%B5%D0%B2+%D0%A3%D0%BD%D0%B8%D0%B2%D0%B5%D1%80%D1%81%D0%B8%D1%82%D0%B5%D1%82&departments=&linkSelected=&schoolVal=CPS&school=Foundation&courses=1&usertype=Students&maxRows=25");
		if(response == null) {
			System.out.println("Response is null for Foundation...");
			return;
		}
		System.out.println(response);
		Type wrapperType = new TypeToken<List<WrapperJson>>() {}.getType();
		List<WrapperJson> responseJson = new Gson().fromJson(response, wrapperType);
		processResponse(responseJson,"Foundation");
	}

	@Override
	public void getSchoolOfMedicine() {
		System.out.println("getSchoolOfMedicine is called");
		String response = executePost("http://my.nu.edu.kz/.PhoneBookPortlet/UsernameServlet",
				"username=&organisationNumber=KS_ZUP_NU1&organisationName=%D0%9D%D0%B0%D0%B7%D0%B0%D1%80%D0%B1%D0%B0%D0%B5%D0%B2+%D0%A3%D0%BD%D0%B8%D0%B2%D0%B5%D1%80%D1%81%D0%B8%D1%82%D0%B5%D1%82&departments=&linkSelected=&schoolVal=SOM(Medicine)&school=School+of+Medicine&courses=Doctorate&usertype=Students&maxRows=25");
		if(response == null) {
			System.out.println("Response is null for School Of Medicine...");
			return;
		}
		System.out.println(response);
		Type wrapperType = new TypeToken<List<WrapperJson>>() {}.getType();
		List<WrapperJson> responseJson = new Gson().fromJson(response, wrapperType);
		processResponse(responseJson,"SOM");

	}

	@Override
	public void getSchoolOfBusiness() {
		System.out.println("getSchoolOfBusiness is called");
		String response = executePost("http://my.nu.edu.kz/.PhoneBookPortlet/UsernameServlet",
				"username=&organisationNumber=KS_ZUP_NU1&organisationName=%D0%9D%D0%B0%D0%B7%D0%B0%D1%80%D0%B1%D0%B0%D0%B5%D0%B2+%D0%A3%D0%BD%D0%B8%D0%B2%D0%B5%D1%80%D1%81%D0%B8%D1%82%D0%B5%D1%82&departments=&linkSelected=&schoolVal=GSB&school=Graduate+School+of+Business&courses=Graduate&usertype=Students&maxRows=25");
		if(response == null) {
			System.out.println("Response is null for School Of Business...");
			return;
		}
		System.out.println(response);
		Type wrapperType = new TypeToken<List<WrapperJson>>() {}.getType();
		List<WrapperJson> responseJson = new Gson().fromJson(response, wrapperType);
		processResponse(responseJson, "SOB");

	}

	@Override
	public void getSchoolOfEducation() {
		System.out.println("getSchoolOfEducation is called");
		String response = executePost("http://my.nu.edu.kz/.PhoneBookPortlet/UsernameServlet",
				"username=&organisationNumber=KS_ZUP_NU1&organisationName=%D0%9D%D0%B0%D0%B7%D0%B0%D1%80%D0%B1%D0%B0%D0%B5%D0%B2+%D0%A3%D0%BD%D0%B8%D0%B2%D0%B5%D1%80%D1%81%D0%B8%D1%82%D0%B5%D1%82&departments=&linkSelected=&schoolVal=GSE&school=Graduate+School+of+Education&courses=Master%E2%80%99s&usertype=Students&maxRows=25");
		if(response == null) {
			System.out.println("Response is null for School Of Education...");
			return;
		}
		System.out.println(response);
		Type wrapperType = new TypeToken<List<WrapperJson>>() {}.getType();
		List<WrapperJson> responseJson = new Gson().fromJson(response, wrapperType);
		processResponse(responseJson, "SOE");
	}

	@Override
	public void getSchoolOfPublicPolicy() {
		System.out.println("getSchoolOfEducation is called");
		String response = executePost("http://my.nu.edu.kz/.PhoneBookPortlet/UsernameServlet",
				"username=&organisationNumber=KS_ZUP_NU1&organisationName=%D0%9D%D0%B0%D0%B7%D0%B0%D1%80%D0%B1%D0%B0%D0%B5%D0%B2+%D0%A3%D0%BD%D0%B8%D0%B2%D0%B5%D1%80%D1%81%D0%B8%D1%82%D0%B5%D1%82&departments=&linkSelected=&schoolVal=GSPP&school=Graduate+School+of+Public+Policy&courses=Graduate&usertype=Students&maxRows=25");
		if(response == null) {
			System.out.println("Response is null for School Of Education...");
			return;
		}
		System.out.println(response);
		Type wrapperType = new TypeToken<List<WrapperJson>>() {}.getType();
		List<WrapperJson> responseJson = new Gson().fromJson(response, wrapperType);
		processResponse(responseJson,"SOPP");
	}

	@Override
	public void processResponse(List<WrapperJson> responseJson, String whoCalled){
		if(responseJson == null && responseJson.isEmpty()){
			System.out.println("List is empty");
		} else {

			List<Row> rows = responseJson.get(0).getRows();
			System.out.println(rows.get(rows.size()-1).getUserName());
			addInformation(rows);
			Gson gson = new GsonBuilder().create();
			FileWriter writer = null;
			try {
				writer = new FileWriter(whoCalled + ".json");
				String json = gson.toJson(rows);
				writer.write(json);
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					writer.flush();
					writer.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		}
	}

	private void addInformation(List<Row> rows){
		int total = rows.size();
		int counter = 1;
		for(Row row : rows){
			System.out.println("In progress " + counter + "/" + total);
			htmlParser(row);
			System.out.println("Done " + counter + "/" + total);
			counter++;
		}
	}

	private void htmlParser(Row row){
		try {
			//This will get you the response.
			Connection.Response res = Jsoup
					.connect("http://my.nu.edu.kz/wps/portal/hidden/login/!ut/p/b1/04_Sj9CPykssy0xPLMnMz0vMAfGjzOKd3R09TMx9DAwsfNxNDTwdPUKDLAONjQ1czYEKIoEKDHAARwNC-sP1o_AqCTSBKsBjhZ9Hfm6qfkFuhEGWiaMiAFWLnI8!/dl4/d5/L2dBISEvZ0FBIS9nQSEh/pw/Z7_2OQEAB1A087S30AK3TO5I630A6/act/id=0/358273364786/-/")
					.data("login", "rassul.rakhimzhan", "password", "MiMingomong5665","loginSubmit","Login")
					.method(Connection.Method.GET)
					.execute();


			//This will get you cookies
			Map<String, String> loginCookies = res.cookies();

			String url = "http://my.nu.edu.kz/.AccountPage/StudentCard?uid=" + row.getUserid();
			Connection.Response response = Jsoup.connect(url).followRedirects(true).execute();
			Document document = Jsoup.connect(url)
					.cookies(loginCookies)
					.get();

			Elements elements = document.select(".infoClass > ul");
			String unparsedInfo = elements.text();
			//Split Fullname
			String[] parts = unparsedInfo.split(" School: ");
			String fullname = parts[0];

			//Split School
			parts = unparsedInfo.split("School: ");
			String school = "";
			if(parts.length>1){
				parts = parts[1].split(" Major:");
				school = parts[0];
			}


			//Split Major
			parts = unparsedInfo.split("Major: ");
			String major= "";
			if(parts.length>1){
				parts = parts[1].split(" Year");
				major = parts[0];
			}


			//Split Year Of Study
			parts = unparsedInfo.split("Year of study: ");
			String yearOfStudy= "";
			if(parts.length>1){
				parts = parts[1].split(" Sex");
				yearOfStudy = parts[0];
			}

			//Get Sex
			parts = unparsedInfo.split("Sex: ");
			String sex = "";
			if(parts.length>1){
				parts = parts[1].split(" ");
				sex = parts[0];
			}

			//Get Email
			parts = unparsedInfo.split("Personal email address: ");
			String email = "";
			if(parts.length>1){
				parts = parts[1].split(" ");
				email = parts[0];
			}

			//Get Phone number if Exists
			parts = unparsedInfo.split("Mobile Phone number: ");
			String phoneNumber = "";
			if(parts.length>1){
				parts = parts[1].split(" ");
				phoneNumber = parts[0];
			}

			/****
			System.out.println(unparsedInfo);
			System.out.println("Fullname: " + fullname);
			System.out.println("School: " + school);
			System.out.println("Major: " + major);
			System.out.println("Year of Study: " + yearOfStudy);
			System.out.println("Sex: " + sex);
			System.out.println("Email: " + email);
			System.out.println("Phone Number: " + phoneNumber);
			****/

			row.setSchool(school);
			row.setMajor(major);
			row.setYear(yearOfStudy);
			row.setSex(sex);
			row.setEmail(email);
			row.setMobilePhone(phoneNumber);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}


}
