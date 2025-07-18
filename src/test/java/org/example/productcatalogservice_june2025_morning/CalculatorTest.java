package org.example.productcatalogservice_june2025_morning;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CalculatorTest {

    @Test
    public void testAdd_2Integers_RunSuccessfully() {
        //arrange
        Calculator calculator = new Calculator();

        //act
        int result = calculator.add(1,10);

        //assert
        assertEquals(11,result);
    }

    @Test
    public void test_DivideByZero_ResultsInArithmeticException() {
        //arrange
        Calculator calculator = new Calculator();

//        calculator.divide(1,0);

        assertThrows(ArithmeticException.class,
                ()-> calculator.divide(1,0));
    }

}