package com.example.week1_android.area;

public class Triangle implements Shape {

    @Override
    public float area(float x, float y) {
        return (x * y) / 2;
    }
}
