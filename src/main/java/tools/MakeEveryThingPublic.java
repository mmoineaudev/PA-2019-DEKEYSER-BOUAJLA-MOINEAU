package tools;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;

public class MakeEveryThingPublic {
    //https://stackoverflow.com/questions/34571/how-do-i-test-a-private-function-or-a-class-that-has-private-methods-fields-or
    //on peut par cette méthode récupérer une classe sur laquelle on peut
    //Method method = TargetClass.getDeclaredMethod(methodName, argClasses);
    //method.invoke(targetObject, argObjects);
    public static Class getClassWithPublicAccess(String classFullName) throws ClassNotFoundException {
        Class c = Class.forName(classFullName);
        Method[] methodList = c.getDeclaredMethods();
        for(Method method : methodList){
            method.setAccessible(true);
        }
        Field[] fields = c.getDeclaredFields();
        for(Field f : fields) {
            f.setAccessible(true);
        }
        return c;
    }
}
