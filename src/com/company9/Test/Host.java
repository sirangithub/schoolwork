package com.company9.Test;

import com.company9.Animal;
import com.company9.Impl.Cat;
import com.company9.Impl.Chicken;
import com.company9.Impl.Dog;
import com.company9.Impl.Duck;

public class Host {
    public static void main(String [] args){
        Animal dog=new Dog();
        Animal cat=new Cat();
        Animal chicken=new Chicken();
        Animal duck=new Duck();
        System.out.println("The host come and feed,");
        dog.voice();
        cat.voice();
        chicken.voice();
        duck.voice();

    }
}
