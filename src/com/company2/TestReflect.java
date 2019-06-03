package com.company2;
        import java.lang.reflect.Constructor;
public class TestReflect {
    public static void main(String[] args)throws Exception {
        Class<?>class1=null;
        class1=Class.forName("com.company2.Point");
        Constructor<?>[ ] allConstructors=class1.getDeclaredConstructors();
        //Constructor<?>[ ] publicConstrutors=class1.getConstructors();
        for(int i=0;i<allConstructors.length;i++){
            System.out.println(allConstructors[i]);
            //ystem.out.println(publicConstrutors[i]);
        }
    }
}
