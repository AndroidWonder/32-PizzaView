package com.course.example.pizzaview;
/*
    This application will read data from a MySQL database and plot the data using GraphView.
    It does this using one activity to set up the background thread and read the data.
    A second activity plots the data using a Column Chart.
 */
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private Thread t = null;
    private ArrayList<Pizza> list = new ArrayList<Pizza>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //start thread
        t = new Thread(background);
        t.start();

    }

    private Runnable background = new Runnable() {
        public void run(){
            String URL = "jdbc:mysql://frodo.bentley.edu:3306/test";
            String username = "harry";
            String password = "harry";


            Statement stmt = null;

            try (Connection con = DriverManager.getConnection (
                        URL,
                        username,
                        password);) {
                stmt = con.createStatement();

                //get pizza data
                ResultSet result = stmt.executeQuery("select * from cs280data;");

                //read result set, write data to ArrayList and Log
                while (result.next()) {
                    String programmer = result.getString("programmer");
                    String day = result.getString("day");
                    int pizzas = result.getInt("pizzas");
                    Pizza pizza = new Pizza(programmer, day, pizzas);
                    String str = String.format("%10s         %5s       %d", programmer, day, pizzas );
                    list.add(pizza);
                    Log.e("JDBC",str );
                }

                //Create intent, place ArrayList on intent object,
                //   request another Activity be started to use the data
                Intent intent = new Intent(MainActivity.this, GraphData.class);
                intent.putExtra("list", list);
                startActivity(intent);

                //clean up
                t = null;


            } catch (SQLException e) {
                Log.e("JDBC","problems with SQL sent to "+URL+
                        ": "+e.getMessage());
            }

            };


    };

}

