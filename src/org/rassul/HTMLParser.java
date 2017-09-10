package org.rassul;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by rassul on 6/9/17.
 */
public class HTMLParser {

    private static final String COOKIE = "_ga=GA1.3.391739043.1484594661; JSESSIONID=0000wEOfwH5wu0KN0owREXEuhQF:18ojq97bn; _gat=1; LtpaToken2=+1oWO7azicg5e43g19FwN4kQMFqaSevPeTgnsYvE+pK+TgppAQu+isvebCs6evGWISB43oJ6JBNDom8viQEhq1pkW9r3hU8wsYwbqe9p3cLH77NUMY97WG454HDYeL0SDnCbtRF313kQvxdJmDPpKM9Varw/lvf3M2xa8kiebYWFAKA4yyhrdzJiwr6zVlgb/gmMujAWtFGPy3kNRRYfay8ACOu7HyTlDxbwTT92JbkMNvPGRQVBuE+83KaeEew+ZwRG/bdjAcdXH2h4+Sdrnc5HMGndB+ak0AjBp3sx0bCLLXNvUm6vwJt+LWr8Rd5Z8Dc8E8kZ5pN9PDTSr0JmhX/xvQssjg7ldFpL/yGhiKs+ToW6cpM/xtCyGdHmo2xzUumx4MbTtdB3mi+byZ33DthhRwq/DW0QOgL7PLNLSmzvUCoC+1eAveVMJojD7fFamQ4ffrmGNi8hLV5/ejtcg0ASd6eP65Ufx4jMVwChw5hXbdJzqSL11jcVoxli5FkCy2RE5s2+TphpNjBFg+fgQkS9SNLsJKoULydALNogLDOpntahwgHFX57NTMSpB7BRfgs+S5EFNt0/XP+eqJmoEHB+M3QcmxIRR8eBcJU0WI36aF6AZjnHCxNlFo1LpB/nVsISX3bI6LMvx3C2SJ3haycjHfrVp+9SKJGkV8gwBxWDUEXKjGk6gsjDoAFRUQKQbElzMluO0Ieen0FC3uLSjg==; usersessionid=5e949c425991145da440e0d15e925f26b802984bd2048fda0dd23d38cec4a6f5469a7f3d2d2d42a7563f6b7eb2269674e371948ec1fcb110aca17036ebeecf77df67bee67739eb3779d4e93339d8b02525b7434751c26e0622c7cf619ffddd50bd015a812dec00931b65219c38c8ccbf053818842892a32331227704dfd6146c";

    public static void main(String[] args){
        try {
            //This will get you the response.
            Connection.Response res = Jsoup
                    .connect("http://my.nu.edu.kz/wps/portal/hidden/login/!ut/p/b1/04_Sj9CPykssy0xPLMnMz0vMAfGjzOKd3R09TMx9DAwsfNxNDTwdPUKDLAONjQ1czYEKIoEKDHAARwNC-sP1o_AqCTSBKsBjhZ9Hfm6qfkFuhEGWiaMiAFWLnI8!/dl4/d5/L2dBISEvZ0FBIS9nQSEh/pw/Z7_2OQEAB1A087S30AK3TO5I630A6/act/id=0/358273364786/-/")
                    .data("login", "rassul.rakhimzhan", "password", "MiMingomong5665","loginSubmit","Login")
                    .method(Connection.Method.GET)
                    .execute();


            //This will get you cookies
            Map<String, String> loginCookies = res.cookies();

            String url = "http://my.nu.edu.kz/.AccountPage/StudentCard?uid=201395684";
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
                parts = parts[1].split(" Major");
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
            System.out.println(unparsedInfo);
            System.out.println("Fullname: " + fullname);
            System.out.println("School: " + school);
            System.out.println("Major: " + major);
            System.out.println("Year of Study: " + yearOfStudy);
            System.out.println("Sex: " + sex);
            System.out.println("Email: " + email);
            System.out.println("Phone Number: " + phoneNumber);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void meth(List<Row> listOfString){
        Row row = new Row();
        row.setUserName("Added item");
        listOfString.add(row);
    }
}
