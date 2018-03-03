package com.course.example.pizzaview;


import java.io.Serializable;

public class Pizza implements Serializable{
    private String programmer;
    private String day;
    private int pizzas;

    public String getDay() {
        return day;
    }

    public int getPizzas() {
        return pizzas;
    }

    public String getProgrammer() {
        return programmer;
    }

    public void setProgrammer(String programmer) {
        this.programmer = programmer;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public void setPizzas(int pizzas) {
        this.pizzas = pizzas;
    }

    public Pizza(String programmer, String day, int pizzas) {
        this.programmer = programmer;
        this.day = day;
        this.pizzas = pizzas;
    }

    @Override
    public String toString() {
        return "Pizza{" +
                "programmer='" + programmer + '\'' +
                ", day='" + day + '\'' +
                ", pizzas=" + pizzas +
                '}';
    }
}
