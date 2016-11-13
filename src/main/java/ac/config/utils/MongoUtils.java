package ac.config.utils;

import org.springframework.data.mongodb.core.query.Update;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by tianq on 11/11/16.
 */
public class MongoUtils {
    public static Update updateFromObj(Object obj) throws InvocationTargetException, IllegalAccessException {
        Update update = new Update();
        Field[] fields = obj.getClass().getDeclaredFields();// 获取所有属性
        Method[] methods = obj.getClass().getMethods();// 获取所有的方法
        for (int i = 0; i < fields.length; i++) {
            String fieldName = fields[i].getName();
            for (int j = 0; j < methods.length; j++) {// 为对象赋值
                String methdName = methods[j].getName();
                if (null != fieldName && equalFieldGet(fieldName, methdName)) {
                    Object val = methods[j].invoke(obj);// 得到值
                    if(val != null) {
                        update.set(fieldName, val);
                    }
                }
            }
        }
        return update;
    }

    /*
  * 比较setter方法和属性相等
  */
    private static boolean equalFieldGet(String field, String name) {
        if (name.toLowerCase().matches("get" + field.toLowerCase())) {
            return true;
        } else {
            return false;
        }
    }
}
