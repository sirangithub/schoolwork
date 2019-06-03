package com.company9.Impl;

import com.company9.Animal;

public class Duck implements Animal {
    @Override
    public void voice() {
        System.out.println("Duck swing and quack:ga!ga!ga!");
    }
}
