package com.example.week1_android

import com.example.week1_android.area.Rectangle
import com.example.week1_android.area.Shape
import com.example.week1_android.area.Square
import com.example.week1_android.area.Triangle

object Driver {

    @JvmStatic
    fun main(args: Array<String>) {


        // challenge -> figure out a way to print salaries of each employee based on
        //  1. Contractor
        // 2. Full Time
        // 3. Intern
        val employee1: Employee = Contractor("John", "Contractor", 123)
        val employee2: Employee = Intern("Smith", "Full Time", 456)
        val employee3: Employee = FullTime("Sara", "Intern", 789)

        // print employee1
        println(employee1)
        println(employee2)
        println(employee3)

        // Homework to create various Shape Areas
        // The Square Area does not obey the Liskov Substitution Principle
        val square: Shape = Square()
        val rectangle: Shape = Rectangle()
        val triangle: Shape = Triangle()

        println(String.format("Area of Square: %f", square.area(5F, 5F)));
        println(String.format("Area of Rectangle: %f", rectangle.area(5F, 3F)));
        println(String.format("Area of Triangle: %f", triangle.area(5F, 3F)));

    }
}