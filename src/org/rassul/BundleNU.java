package org.rassul;

import java.io.IOException;
import java.util.List;

/**
 * Created by rassul on 6/8/17.
 */
public interface BundleNU {

    void getSST();
    void getSHSS();
    void getSENG();
    void getFoundation();
    void getSchoolOfMedicine();
    void getSchoolOfBusiness();
    void getSchoolOfEducation();
    void getSchoolOfPublicPolicy();
    void processResponse(List<WrapperJson> response, String whoCalled) throws IOException;

}
