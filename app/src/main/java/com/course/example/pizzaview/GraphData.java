package com.course.example.pizzaview;

import java.util.ArrayList;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.GridLabelRenderer;
import com.jjoe64.graphview.LegendRenderer;
import com.jjoe64.graphview.ValueDependentColor;
import com.jjoe64.graphview.helper.StaticLabelsFormatter;
import com.jjoe64.graphview.series.BarGraphSeries;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;


public class GraphData extends Activity {
	
	private TextView texted = null;
	private ArrayList<Pizza> pizzalist = new ArrayList<Pizza>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_graph);
		

		//get ArrayList from intent object
		pizzalist = (ArrayList<Pizza>) getIntent().getSerializableExtra("list");

		//set limit on how many columns to display
		int limit = 7;

		String[] programmer = new String[limit];
		int[] pizzas = new int[limit];

		//write data to arrays for later graphing
		for (int i=0; i<limit; i++) {
			Pizza pizza = pizzalist.get(i);
			programmer[i] = pizza.getProgrammer();
			pizzas[i] = pizza.getPizzas();
		}

		//create DataPoint array for plotting
		DataPoint[] points = new DataPoint[limit];
		for (int i=0; i<limit; i++){
			points[i] = new DataPoint(i, pizzas[i]);
		}

		GraphView graph = (GraphView) findViewById(R.id.graph);
		BarGraphSeries<DataPoint> series = new BarGraphSeries<>(points);

		// styling
		series.setValueDependentColor(new ValueDependentColor<DataPoint>() {
			@Override
			public int get(DataPoint data) {
				return Color.rgb((int) data.getX()*255/4, (int) Math.abs(data.getY()*255/6), 100);
			}
		});

		series.setSpacing(50);
		series.setTitle("pizza");
		graph.getLegendRenderer().setVisible(true);
		graph.getLegendRenderer().setAlign(LegendRenderer.LegendAlign.TOP);

		// draw values on top
		series.setDrawValuesOnTop(true);
		series.setValuesOnTopColor(Color.RED);
		series.setValuesOnTopSize(50);

		// label X axis
		StaticLabelsFormatter staticLabelsFormatter = new StaticLabelsFormatter(graph);
		staticLabelsFormatter.setHorizontalLabels(programmer);
		graph.getGridLabelRenderer().setLabelFormatter(staticLabelsFormatter);

		graph.getViewport().setScrollable(true); // enables horizontal scrolling
		graph.getViewport().setScrollableY(true); // enables vertical scrolling

		//graph.getViewport().setScalable(true); // enables horizontal zooming and scrolling
		// graph.getViewport().setScalableY(true); // enables vertical zooming and scrolling

		graph.addSeries(series);



	}

}
