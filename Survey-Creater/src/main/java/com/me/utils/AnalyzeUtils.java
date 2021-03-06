package com.me.utils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.me.service.SurveysService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @ClassName AnalyzeUtils
 * @Description TODO
 * @Author xufeng
 * @Data 2019/3/5 16:08
 * @Version 1.0
 **/
public class AnalyzeUtils {

    @Autowired
    private SurveysService surveysService;

    public static Boolean filterQuestions(String type){
        switch (type){
            case "radiogroup":
            case "dropdown":
            case "imagepicker":
            case "text":
            case "checkbox":
            case "rating":
            case "boolean":
            case "matrix":
            case "matrixdynamic":
                return true;
            case "matrixdropdown":
            case "sortablelist":
            case "multipletext":
            case "panel":
            case "paneldynamic":
            case "comment":
            case "html":
            case "expression":
            case "file":
                return false;
             default:
                 return false;
        }
    }

    public static String typeAnalyze(String type){
        switch (type){
            case "radiogroup":
            case "imagepicker":
                return "bar";
            case "boolean":
                return "pie";
            case "rating":
                return "rpie";
            case "dropdown":
                return "dpie";
            case "checkbox":
                return "cpie";
            case "matrix":
                return "line";
            case "matrixdynamic":
                return "3DMap";
            case "sortablelist":
                return "3DSMap";
            case "text":
                return "text";
            case "matrixdropdown":
            case "multipletext":
            case "panel":
            case "paneldynamic":
            case "comment":
            case "html":
            case "expression":
            case "file":
                return type;
            default:
                return type;
        }
    }

    public static List<JSONObject> parsePage(String page) {
        List<JSONObject> alist = new ArrayList<>();
        JSONObject jsonObject= JSONObject.parseObject(page);
        JSONArray jsonArray = JSONArray.parseArray(jsonObject.getString("pages"));
        Iterator iterator = jsonArray.iterator();
        while (iterator.hasNext()){
            JSONObject jObject = (JSONObject) iterator.next();
            JSONArray jArray = jObject.getJSONArray("elements");
            Iterator jter = jArray.iterator();
            while(jter.hasNext()){
                alist.add((JSONObject) jter.next());
            }
        }
        return alist;
    }
}
