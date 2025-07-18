package org.example.productcatalogservice_june2025_morning;

public class Calculator {

    public Calculator() {}

    public int add(int num1,int num2) {
        return num1+num2;
    }

    public int divide(int num1,int num2) {
        try {
            return num1/num2;
        } catch (ArithmeticException exception) {
            throw exception;
        }
    }
}
