package org.rassul;

/**
 * Created by rassul on 6/8/17.
 */
public class Main {
    public static void main(String[] args){
        Downloader downloader = new Downloader();
        downloader.getFoundation();
        downloader.getSST();
        downloader.getSHSS();
        downloader.getSENG();
        downloader.getSchoolOfBusiness();
        downloader.getSchoolOfEducation();
        downloader.getSchoolOfPublicPolicy();
        downloader.getSchoolOfMedicine();

    }
}
