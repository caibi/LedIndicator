package com.wy.ledindicator.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Color;
import android.text.TextUtils;

import com.wy.ledindicator.Constants;
import com.wy.ledindicator.MyApplication;
import com.wy.ledindicator.entity.Params;

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

    /**
     * @return      获取配置参数
     */
    public static Params getParams(){
        String txt = read(Constants.ShareprefrencesParams.ALL_SP_NAME,Constants.ShareprefrencesParams.TEXT,String.class,"默认文字");
        int direction = read(Constants.ShareprefrencesParams.ALL_SP_NAME,Constants.ShareprefrencesParams.DIRECTION,Integer.class,2);
        int size = read(Constants.ShareprefrencesParams.ALL_SP_NAME,Constants.ShareprefrencesParams.TEXT_SIZE,Integer.class,50);
        String font = read(Constants.ShareprefrencesParams.ALL_SP_NAME,Constants.ShareprefrencesParams.TEXT_font,String.class,"wryh.ttf");
        int textColor = read(Constants.ShareprefrencesParams.ALL_SP_NAME,Constants.ShareprefrencesParams.TEXT_COLOR,Integer.class,Color.BLACK);
        int bgType = read(Constants.ShareprefrencesParams.ALL_SP_NAME,Constants.ShareprefrencesParams.MAIN_BG_TYPE,Integer.class,1);
        int bgColor = read(Constants.ShareprefrencesParams.ALL_SP_NAME,Constants.ShareprefrencesParams.MAIN_COLOR_BG,Integer.class,Color.WHITE);
        String picPath = read(Constants.ShareprefrencesParams.ALL_SP_NAME,Constants.ShareprefrencesParams.MAIN_PIC_BG,String.class,"");
        int speed = read(Constants.ShareprefrencesParams.ALL_SP_NAME,Constants.ShareprefrencesParams.SPEED,Integer.class,20);

        Params params = new Params(txt,direction,size,font,textColor,speed,bgType,bgColor,picPath);
        return params;
    }

    /**
     * @param params    存储配置参数
     * @return
     */
    public static void putParams(Params params){
        if(!TextUtils.isEmpty(params.getText()))
        write(Constants.ShareprefrencesParams.ALL_SP_NAME,Constants.ShareprefrencesParams.TEXT,params.getText());
        if(params.getDirection()!=0)
        write(Constants.ShareprefrencesParams.ALL_SP_NAME,Constants.ShareprefrencesParams.DIRECTION,params.getDirection());
        if(params.getSize()!=0)
        write(Constants.ShareprefrencesParams.ALL_SP_NAME,Constants.ShareprefrencesParams.TEXT_SIZE,params.getSize());
        if(!TextUtils.isEmpty(params.getFont()))
        write(Constants.ShareprefrencesParams.ALL_SP_NAME,Constants.ShareprefrencesParams.TEXT_font,params.getFont());
        if(params.getTextColor()!=0)
        write(Constants.ShareprefrencesParams.ALL_SP_NAME,Constants.ShareprefrencesParams.TEXT_COLOR,params.getTextColor());

        write(Constants.ShareprefrencesParams.ALL_SP_NAME,Constants.ShareprefrencesParams.MAIN_BG_TYPE,params.getType());
        if(params.getBgColor()!=0)
        write(Constants.ShareprefrencesParams.ALL_SP_NAME,Constants.ShareprefrencesParams.MAIN_COLOR_BG,params.getBgColor());
        if(!TextUtils.isEmpty(params.getPicPath()))
        write(Constants.ShareprefrencesParams.ALL_SP_NAME,Constants.ShareprefrencesParams.MAIN_PIC_BG,params.getPicPath());

        write(Constants.ShareprefrencesParams.ALL_SP_NAME,Constants.ShareprefrencesParams.SPEED,params.getSpeed());
    }

}
