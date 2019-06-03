package com.company9.Impl;

import com.company9.Animal;

public class Cat implements Animal {
    @Override
    public void voice() {
        System.out.println("Cat run and cry:miao!miao!miao!");
    }
}
