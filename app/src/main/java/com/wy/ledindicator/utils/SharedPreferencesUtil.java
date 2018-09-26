package com.wy.ledindicator.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import com.wy.ledindicator.MyApplication;
import java.io.IOException;
import java.io.Serializable;
import java.util.Set;


public class SharedPreferencesUtil {

    /**
     * 写数据
     * @param spName   SharedPreferences下定义的sp
     * @param key
     * @param value
     * @return
     * @throws IOException
     * @throws ClassCastException
     */
    public static boolean write(String spName, String key, Object value){
        Context context = MyApplication.getInstance();
        SharedPreferences preferences = context.getSharedPreferences(spName, Context.MODE_PRIVATE);//创建仅允许本应用使用的SharedPreferences
        Editor editor = preferences.edit();
        if (value instanceof String) {
            editor.putString(key, (String) value);
        } else if (value instanceof Integer) {
            editor.putInt(key, (int) value);
        } else if (value instanceof Float) {
            editor.putFloat(key, (float) value);
        } else if (value instanceof Boolean) {
            editor.putBoolean(key, (boolean) value);
        } else if (value instanceof Set) {
            editor.putStringSet(key, (Set<String>) value);
        } else if (value instanceof Long) {
            editor.putLong(key, (long) value);
        } else { //写对象
            try{
                Serializable serialObi = (Serializable)value;
                String base64Str = Base64.encodeObject(serialObi);//通过Spring 4 android将这个value对象转为字符串
                editor.putString(key, base64Str);
            }catch (IOException ioe){
//                LogUtil.e("存储对象为base64码时出现异常");
                ioe.printStackTrace();
                return false;
            }catch (Exception caste){
               // Log.i(Constant.LogTag.SHAREDPREFERENCES_LOG, "存储对象为base64码时出现异常,参数Object value必须为一个Serializable对象");
                caste.printStackTrace();
                return false;
            }
        }
        return editor.commit();
    }

    /**
     * 读数据
     * @param spName cn.com.quickpark.ssp.Constant.SharedPreferences下定义的sp
     * @param key
     * @param type
     * @param defaultVal
     * @param <T>
     */
    public static <T> T read(String spName, String key, Class<T> type, T defaultVal) {
        Context context = MyApplication.getInstance();
        SharedPreferences preferences = context.getSharedPreferences(spName, Context.MODE_PRIVATE);//创建仅允许本应用使用的SharedPreferences
        if (String.class.equals(type)) {
            return (T) preferences.getString(key, defaultVal != null ? ((String) defaultVal) : null);
        } else if (Integer.class.equals(type)) {
            return (T) new Integer(preferences.getInt(key, defaultVal != null ? ((Integer) defaultVal) : null));
        } else if (Float.class.equals(type)) {
            return (T) new Float(preferences.getFloat(key, defaultVal != null ? ((Float) defaultVal) : null));
        } else if (Boolean.class.equals(type)) {
            return (T) new Boolean(preferences.getBoolean(key, defaultVal != null ? ((Boolean) defaultVal) : null));
        } else if (Set.class.equals(type)) {
            return (T) preferences.getStringSet(key, defaultVal != null ? ((Set) defaultVal) : null);
        } else if (Long.class.equals(type)) {
            return (T) new Long(preferences.getLong(key, defaultVal != null ? ((Long) defaultVal) : null));
        } else { //读对象
            try {
                String objStr = preferences.getString(key,"");
                if(!StringUtils.hasText(objStr)){
                    return  null;
                }
                return (T) Base64.decodeToObject(objStr);
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            } catch (ClassNotFoundException cfe) {
//                LogUtil.e("读取base64码对象时出现异常");
               // Log.i(Constant.LogTag.SHAREDPREFERENCES_LOG, "读取base64码对象时出现异常");
                cfe.printStackTrace();
                return null;
            }
        }
    }

    public static final String FILE_NAME = "share_data";
    /**保存数据的方法，我们需要拿到保存数据的具体类型，然后根据类型调用不同的保存方法*/
    public static void put(Context context, String key, Object object) {
        SharedPreferences sp = context.getSharedPreferences(FILE_NAME,Context.MODE_PRIVATE);
        Editor editor = sp.edit();
        if (object instanceof String) {
            editor.putString(key, (String) object);
        } else if (object instanceof Integer) {
            editor.putInt(key, (Integer) object);
        } else if (object instanceof Boolean) {
            editor.putBoolean(key, (Boolean) object);
        } else if (object instanceof Float) {
            editor.putFloat(key, (Float) object);
        } else if (object instanceof Long) {
            editor.putLong(key, (Long) object);
        } else {
            editor.putString(key, object.toString());
        }
        editor.commit();
    }
    /**得到保存数据的方法，我们根据默认值得到保存的数据的具体类型，然后调用相对于的方法获取值*/
    public static Object get(Context context, String key, Object defaultObject) {
        SharedPreferences sp = context.getSharedPreferences(FILE_NAME,Context.MODE_PRIVATE);
        if (defaultObject instanceof String) {
            return sp.getString(key, (String) defaultObject);
        } else if (defaultObject instanceof Integer) {
            return sp.getInt(key, (Integer) defaultObject);
        } else if (defaultObject instanceof Boolean) {
            return sp.getBoolean(key, (Boolean) defaultObject);
        } else if (defaultObject instanceof Float) {
            return sp.getFloat(key, (Float) defaultObject);
        } else if (defaultObject instanceof Long) {
            return sp.getLong(key, (Long) defaultObject);
        }
        return null;
    }
}
