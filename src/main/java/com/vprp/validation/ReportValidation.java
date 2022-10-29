package com.vprp.validation;

import com.vprp.user.utils.AppConstant;
import org.springframework.beans.factory.annotation.Autowired;

public class ReportValidation {

    public static Boolean validateReport(String reportName, String loginId){
        Boolean isValidAccess;
        switch(reportName){
            case "GP_Report_Test01":
                isValidAccess=UserValidation.verifyUserGPWithRoleAndApp(loginId, AppConstant.ROLE_VPRP_GP, AppConstant.APP_VPRP);
                if (isValidAccess)
                    return true;
                break;
            case "User_Report":
                // need to validate report
                break;
            case "MGNREGS_SchemeWise":
                // need to validate report
                break;
            case "Entitlement_MGNREGSjobCard":
                // need to validate report
                break;
            default:
                throw new IllegalStateException("Invalid ReportName " + reportName);
        }

        return false;
    }
}
