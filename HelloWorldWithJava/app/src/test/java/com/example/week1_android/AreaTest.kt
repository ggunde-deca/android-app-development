package com.example.week1_android

import com.example.week1_android.area.Rectangle
import com.example.week1_android.area.Shape
import com.example.week1_android.area.Square
import com.example.week1_android.area.Triangle
import org.junit.Test

import org.junit.Assert.*
import java.lang.ArithmeticException

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class AreaTest {
    @Test
    fun square_area_isCorrect() {
        val square: Shape = Square();

        assertEquals(25F, square.area(5F, 5F));
    }

    @Test
    fun square_area_isInCorrect() {
        val square: Shape = Square();

        assertThrows(
            ArithmeticException::class.java,
        ) { square.area(5F, 3F) }
    }

    @Test
    fun rectangle_area_isCorrect() {
        val rectangle: Shape = Rectangle();

        assertEquals(15F, rectangle.area(5F, 3F));
    }

    @Test
    fun triangle_area_isCorrect() {
        val triangle: Shape = Triangle();

        assertEquals(7.5F, triangle.area(5F, 3F));
    }
}