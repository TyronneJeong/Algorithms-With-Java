package programmers.assignment.kakao2021.utils;

import programmers.assignment.kakao2021.controller.MainController;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public class JSONUtils {
    private static final Logger LOGGER = Logger.getLogger(MainController.class.getName());

    private static JSONUtils jsonUtils = null;
    private static URL joUrl;
    private static URLClassLoader joUcl;
    private static Class<?> joCls;

    private static URL gsonUrl;
    private static URLClassLoader gsonUcl;
    private static Class<?> gsonCls;
    static {
        /* JSON Object */
        try {
            joUrl = new URL("https://repo1.maven.org/maven2/org/json/json/20210307/json-20210307.jar");
            joUcl = new URLClassLoader(new URL[]{joUrl}, JSONUtils.class.getClassLoader());
            joCls = joUcl.loadClass("org.json.JSONObject");
        } catch (MalformedURLException | ClassNotFoundException e) {
            LOGGER.warning("JSON Object 클래스 로드 실패");
            e.printStackTrace();
        }
        /* Gson */
        try {
            gsonUrl = new URL("https://repo1.maven.org/maven2/com/google/code/gson/gson/2.8.6/gson-2.8.6.jar");
            gsonUcl = new URLClassLoader(new URL[]{gsonUrl}, JSONUtils.class.getClassLoader());
            gsonCls = gsonUcl.loadClass("com.google.gson.Gson");
        } catch (MalformedURLException | ClassNotFoundException e) {
            LOGGER.warning("GSON 클래스 로드 실패");
            e.printStackTrace();
        }
    }

    /* declare singletone instance */
    public JSONUtils getInstance() {
        if(jsonUtils == null){
            return new JSONUtils();
        } else {
            return jsonUtils;
        }
    }

    /* Constructor */
    public JSONUtils() {}

    /**
     * mapToJSONString
     * - HashMap 을 인자로 받아 JSON 스트링벨류로 리턴 한다.
     * @param map
     * @return
     */
    public static String mapToJSONString(HashMap<String, Object> map){
        String rtn = null;
        try {
            Constructor<?> constructor = joCls.getConstructor(Map.class);
            constructor.setAccessible(true);
            Object instance = constructor.newInstance(map);
            Method m = joCls.getDeclaredMethod("toString");
            m.setAccessible(true);
            rtn = (String) m.invoke(instance);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        LOGGER.info("JSON Utils return string : " + rtn);
        return rtn;
    }

    /**
     * jsonToMap
     */
    public static HashMap<String, Object> jsonToMap(String jsonStr){
        LOGGER.info("JSON Utils input string : " + jsonStr);
        HashMap<String, Object> retMap = null;
        try {
            Constructor<?> constructor = gsonCls.getConstructor();
            constructor.setAccessible(true);
            Object instance = constructor.newInstance();
            Method m = gsonCls.getDeclaredMethod("fromJson", String.class, Class.class);
            m.setAccessible(true);
            return (HashMap<String, Object>) m.invoke(instance, jsonStr, HashMap.class);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
        return retMap;
    }
}
