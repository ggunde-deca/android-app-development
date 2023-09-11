package com.example.week1_android.area;

public class Square implements Shape{

    @Override
    public float area(float x, float y) {
        if (x != y)
            throw new ArithmeticException(String.format("To calculate area of a Square, {%f} and {%f} should be equal", x, y));
        return x * y;
    }
}
