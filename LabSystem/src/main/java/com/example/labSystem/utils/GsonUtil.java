package com.example.labSystem.utils;

import com.example.labSystem.common.OpenApiConstant;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.*;

/**
 * 利用gson解析json
 *
 * @author
 * @version
 */
public class GsonUtil {

    public static <T> T jsonToObjectForTimeStamp(String jsonStr, Class<T> clazz) {
        T t = null;
        try {
            GsonBuilder builder = new GsonBuilder();
            builder.registerTypeAdapter(Date.class, new JsonDeserializer<Date>() {
                public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
                    return new Date(json.getAsJsonPrimitive().getAsLong());
                }
            });
            Gson gson = builder.create();
            t = gson.fromJson(jsonStr, clazz);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return t;
    }

    public static <T> T jsonToObject(String jsonStr, Class<T> clazz) {
        T t = null;
        try {
            Gson gson = new Gson();
            t = gson.fromJson(jsonStr, clazz);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return t;
    }

    public static <T> T jsonToObjectDisableHtml(String jsonStr, Class<T> clazz) {
        T t = null;
        try {
            Gson gson = new GsonBuilder().disableHtmlEscaping().create();
            t = gson.fromJson(jsonStr, clazz);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return t;
    }

    public static String ObjectToJson(Object object) {

        Gson gson = new GsonBuilder().setDateFormat(OpenApiConstant.SIMPLEFORMAT24).create();
        String str = gson.toJson(object);
        return str;
    }

    public static String ObjectToJsonDisableHtml(Object object) {

        Gson gson = new GsonBuilder().disableHtmlEscaping().create();
        String str = gson.toJson(object);
        return str;
    }

    public static <T> List<T> jsonToList(String jsonString, Class<T> cls) {
        List<T> list = new ArrayList<T>();
        try {
            Gson gson = new Gson();
            list = gson.fromJson(jsonString, new TypeToken<List<T>>() {
            }.getType());
        } catch (Exception e) {
        }
        return list;
    }

    public static List<Map<String, Object>> jsonTolistKeyMaps(String jsonString) {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        try {
            Gson gson = new Gson();
            list = gson.fromJson(jsonString,
                    new TypeToken<List<Map<String, Object>>>() {
                    }.getType());
        } catch (Exception e) {
            // TODO: handle exception
        }
        return list;
    }

    public static String ObjectToJson1(Object object) {

        final GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(Object.class, object);
        gsonBuilder.setPrettyPrinting();

        final Gson gson = gsonBuilder.create();

        String str = gson.toJson(object);
        return str;
    }

//    //json的key转大写
//    //toUpperCase()换成toLowerCase() 转小写
//    public static JSONObject transtoUpperCaseObject(String json) {
//        JSONObject jSONArray1 = JSONObject.fromObject(json);
//        JSONObject jSONArray2 = new JSONObject();
//        Iterator it = jSONArray1.keys();
//        while (it.hasNext()) {
//            String key = (String) it.next();
//            Object object = jSONArray1.get(key);
//            if (object.getClass().toString().endsWith("JSONObject")) {
//                jSONArray2.accumulate(key.toUpperCase(), transtoUpperCaseObject(object.toString()));
//            } else if (object.getClass().toString().endsWith("JSONArray")) {
//                jSONArray2.accumulate(key.toUpperCase(), transToArray(jSONArray1.getJSONArray(key).toString()));
//            } else {
//                jSONArray2.accumulate(key.toUpperCase(), object);
//            }
//        }
//        return jSONArray2;
//    }
//
//    //json数组的key转大写
//    public static JSONArray transToArray(String jsonArray) {
//        JSONArray jSONArray1 = JSONArray.fromObject(jsonArray);
//        JSONArray jSONArray2 = new JSONArray();
//        for (int i = 0; i < jSONArray1.size(); i++) {
//            Object jArray = jSONArray1.getJSONObject(i);
//            if (jArray.getClass().toString().endsWith("JSONObject")) {
//                jSONArray2.add(transtoUpperCaseObject(jArray.toString()));
//            } else if (jArray.getClass().toString().endsWith("JSONArray")) {
//                jSONArray2.add(transToArray(jArray.toString()));
//            }
//        }
//        return jSONArray2;
//    }
//
//    //json的key转小写
//    //toUpperCase()换成toLowerCase() 转小写
//    public static JSONObject transtoLowerCaseObject(String json) {
//        JSONObject jSONArray1 = JSONObject.fromObject(json);
//        JSONObject jSONArray2 = new JSONObject();
//        Iterator it = jSONArray1.keys();
//        while (it.hasNext()) {
//            String key = (String) it.next();
//            Object object = jSONArray1.get(key);
//            if (object.getClass().toString().endsWith("JSONObject")) {
//                jSONArray2.accumulate(key.toLowerCase(), transtoLowerCaseObject(object.toString()));
//            } else if (object.getClass().toString().endsWith("JSONArray")) {
//                jSONArray2.accumulate(key.toLowerCase(), transToLowerArray(jSONArray1.getJSONArray(key).toString()));
//            } else {
//                jSONArray2.accumulate(key.toLowerCase(), object);
//            }
//        }
//        return jSONArray2;
//    }
//
//    //json数组的key转大写
//    public static JSONArray transToLowerArray(String jsonArray) {
//        JSONArray jSONArray1 = JSONArray.fromObject(jsonArray);
//        JSONArray jSONArray2 = new JSONArray();
//        for (int i = 0; i < jSONArray1.size(); i++) {
//            Object jArray = jSONArray1.getJSONObject(i);
//            if (jArray.getClass().toString().endsWith("JSONObject")) {
//                jSONArray2.add(transtoLowerCaseObject(jArray.toString()));
//            } else if (jArray.getClass().toString().endsWith("JSONArray")) {
//                jSONArray2.add(transToLowerArray(jArray.toString()));
//            }
//        }
//        return jSONArray2;
//    }
}
