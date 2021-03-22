package com.udacity.jwdnd.course1.cloudstorage.controller;

import java.lang.reflect.Method;
import java.util.HashMap;

public class BaseController {
    protected Class<?> mainServiceClass;
    protected Class<?> mainModelClass;
    protected String insertMethodName = "insert";
    protected String updateMethodName = "update";
    protected String retrieveMethodName = "getById";

    protected HashMap<String, Object> createModelViewData(String errorMessage, boolean success) {
        HashMap<String, Object> viewData = new HashMap<>();
        viewData.put("noteErrorMessage", errorMessage);
        viewData.put("noteSuccess", success);
        return viewData;
    }

    protected String getDataMethodName(boolean isUpdated) {
        String methodName = this.insertMethodName;
        if (isUpdated) {
            methodName = this.updateMethodName;
        }
        return methodName;
    }

    protected HashMap<String, Object> getDataMethod(boolean isUpdated) {
        try {
            HashMap<String, Object> methodInfo = new HashMap<>();
            String methodName = this.getDataMethodName(isUpdated);
            Method method = this.mainServiceClass.getMethod(methodName, this.mainModelClass);
            methodInfo.put("method", method);
            methodInfo.put("methodName", methodName);
            return methodInfo;
        } catch (NoSuchMethodException exception) {
            return null;
        }
    }

    protected boolean hasMethod(HashMap<String, Object> methodInfo) {
        return null != methodInfo.get("method");
    }

    protected boolean isMethodAnUpdate(HashMap<String, Object> methodInfo) {
        return this.updateMethodName.equals(methodInfo.get("methodName"));
    }
}
