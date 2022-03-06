package com.example.demo.Helpers;

import org.springframework.security.core.userdetails.UserDetails;

public class UserInfoHelper {
    private static int companyID;
    private static String requestPath;
    private static int empID;
    private static UserDetails userDetails;

    public static String getRequestPath() {
        return requestPath;
    }

    public static void setRequestPath(String requestPath) {
        UserInfoHelper.requestPath = requestPath;
    }

    public static UserDetails getUserDetails() {
        return userDetails;
    }

    public static void setUserDetails(UserDetails userDetails) {
        UserInfoHelper.userDetails = userDetails;
    }

    public static int getEmpID() {
        return empID;
    }

    public static void setEmpID(int empID) {
        UserInfoHelper.empID = empID;
    }

    public static int getCompanyID() {
        return companyID;
    }

    public static void setCompanyID(int companyID) {
        UserInfoHelper.companyID = companyID;
    }
}
