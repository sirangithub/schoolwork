package com.company9.Impl;

import com.company9.Animal;

public class Chicken implements Animal {
    @Override
    public void voice() {
        System.out.println("Chicken run and crow :ji!ji!ji!");
    }
}
