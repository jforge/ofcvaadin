package org.ofcvaadin.demo;

import java.util.ArrayList;

import jofc2.OFC;
import jofc2.model.Chart;
import jofc2.model.axis.XAxis;
import jofc2.model.axis.YAxis;
import jofc2.model.elements.BarChart;
import jofc2.model.elements.HorizontalBarChart;
import jofc2.model.elements.LineChart;
import jofc2.model.elements.PieChart;

import org.ofcvaadin.ui.OpenFlashChart;
import org.ofcvaadin.ui.OpenFlashChartV2;
import org.ofcvaadin.ui.OpenFlashChart.ChartDataGenerator;

import com.vaadin.Application;
import com.vaadin.ui.*;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;

public class DemoApplication extends Application {
	private static final long serialVersionUID = 4422477701385760785L;

	@Override
	public void init() {
		Window mainWindow = new Window("Open Flash Chart for Vaadin");
		setMainWindow(mainWindow);
		
		Label label = new Label("Open Flash Chart for Vaadin demo. More cases are coming." +
				"Please visit page for more details:" +
				"<a href='http://code.google.com/p/ofcvaadin/'>ofcvaadin</a>",
				Label.CONTENT_XHTML);
		mainWindow.addComponent(label);

		Button showDemos = new Button("Show All Demos");
		showDemos.addListener(new ClickListener(){
			private static final long serialVersionUID = -89669779516839869L;

			@Override
			public void buttonClick(ClickEvent event) {
				showAllDemoWindows();
			}
		});
		mainWindow.addComponent(showDemos);
		
		//embedOpenFlashChart(mainWindow);
		embedOpenFlashChartV2(mainWindow);
	}
	
	private void showAllDemoWindows(){
		getMainWindow().addWindow(createLineChartDemo());
		getMainWindow().addWindow(createBarChartDemo());
		getMainWindow().addWindow(createHorizontalBarChartDemo());
		getMainWindow().addWindow(createPieChartDemo());
	}
	
	@SuppressWarnings("serial")
	private void embedOpenFlashChartV2(Window mainWindow){
		final OpenFlashChartV2 chartVaadin = new OpenFlashChartV2(this);
		chartVaadin.setWidth("500px");
		chartVaadin.setHeight("200px");
		mainWindow.addComponent(chartVaadin);
		
		chartVaadin.setChartDataGenerator(new OpenFlashChartV2.ChartDataGenerator(){
			@Override
			public String getJson() {
				LineChart lc = new LineChart();
				lc.setText("test_1");

				ArrayList<Number> lst = new ArrayList<Number>();
				for (int i = 0; i < 10; i++) {
					lst.add((int) (Math.random() * 20));
				}
				lc.addValues(lst);

				YAxis ya = new YAxis();
				ya.setMax(22);
				ya.setSteps(2);

				Chart c = new Chart("Line Chart");
				c.addElements(lc);
				c.setYAxis(ya);

				String s = OFC.getInstance().prettyPrint(c, 4);
				return s;
			}
		});
		
		Button refresh = new Button("Refresh Chart");
		refresh.addListener(new ClickListener(){
			private static final long serialVersionUID = -89669779516839869L;

			@Override
			public void buttonClick(ClickEvent event) {
				chartVaadin.requestRepaint();
			}
		});
		mainWindow.addComponent(refresh);
	}
	
	@SuppressWarnings("serial")
	private void embedOpenFlashChart(Window mainWindow){
		OpenFlashChart chartVaadin = new OpenFlashChart(this);
		chartVaadin.setWidth("500px");
		chartVaadin.setHeight("200px");
		mainWindow.addComponent(chartVaadin);
		
		chartVaadin.setChartDataGenerator(new ChartDataGenerator(){
			@Override
			public String getJson() {
				LineChart lc = new LineChart();
				lc.setText("test_1");

				ArrayList<Number> lst = new ArrayList<Number>();
				for (int i = 0; i < 10; i++) {
					lst.add((int) (Math.random() * 20));
				}
				lc.addValues(lst);

				YAxis ya = new YAxis();
				ya.setMax(22);
				ya.setSteps(2);

				Chart c = new Chart("Line Chart");
				c.addElements(lc);
				c.setYAxis(ya);

				String s = OFC.getInstance().prettyPrint(c, 4);
				return s;
			}
		});
	}
	
	@SuppressWarnings("serial")
	private Window createLineChartDemo(){
		final String title = "Line Chart";
		OneChartWindow chartWindow = new OneChartWindow(this, title);
		chartWindow.setChartDataGenerator(new ChartDataGenerator(){
			@Override
			public String getJson() {
				LineChart lc = new LineChart();
				lc.setText("test_1");

				ArrayList<Number> lst = new ArrayList<Number>();
				for (int i = 0; i < 10; i++) {
					lst.add((int) (Math.random() * 20));
				}
				lc.addValues(lst);

				YAxis ya = new YAxis();
				ya.setMax(22);
				ya.setSteps(2);

				Chart c = new Chart(title);
				c.addElements(lc);
				c.setYAxis(ya);

				String s = OFC.getInstance().prettyPrint(c, 4);
				return s;
			}
		});
		
		return chartWindow;
	}
	
	private Window createBarChartDemo(){
		final String title = "Bar Chart";
		OneChartWindow chartWindow = new OneChartWindow(this, title);
		chartWindow.setChartDataGenerator(new ChartDataGenerator(){
			private static final long serialVersionUID = 3164548364271914786L;

			@Override
			public String getJson() {
				BarChart lc = new BarChart();
				lc.setText("test_1");

				ArrayList<Number> lst = new ArrayList<Number>();
				for (int i = 0; i < 10; i++) {
					lst.add((int) (Math.random() * 20));
				}
				lc.addValues(lst);

				YAxis ya = new YAxis();
				ya.setMax(22);
				ya.setSteps(2);

				Chart c = new Chart(title);
				c.addElements(lc);
				c.setYAxis(ya);

				String s = OFC.getInstance().prettyPrint(c, 4);
				return s;
			}
		});
		
		return chartWindow;
	}
	
	private Window createHorizontalBarChartDemo(){
		final String title = "Horizontal Bar Chart";
		OneChartWindow chartWindow = new OneChartWindow(this, title);
		chartWindow.setChartDataGenerator(new ChartDataGenerator(){
			private static final long serialVersionUID = -4139782544095092771L;

			@Override
			public String getJson() {
				HorizontalBarChart lc = new HorizontalBarChart();
				lc.setText("test_1");

				ArrayList<Number> lst = new ArrayList<Number>();
				for (int i = 0; i < 10; i++) {
					lst.add((int) (Math.random() * 20));
				}
				lc.addValues(lst);

				XAxis xa = new XAxis();
				xa.setMax(22);
				xa.setSteps(2);

				Chart c = new Chart(title);
				c.addElements(lc);
				c.setXAxis(xa);

				String s = OFC.getInstance().prettyPrint(c, 4);
				return s;
			}
		});
		
		return chartWindow;
	}
	
	private Window createPieChartDemo(){
		final String title = "Pie Chart";
		OneChartWindow chartWindow = new OneChartWindow(this, title);
		chartWindow.setChartDataGenerator(new ChartDataGenerator(){
			private static final long serialVersionUID = 5732132570890663242L;

			@Override
			public String getJson() {
				PieChart lc = new PieChart();
				lc.setText("test_1");

				ArrayList<Number> lst = new ArrayList<Number>();
				for (int i = 0; i < 10; i++) {
					lst.add((int) (Math.random() * 20));
				}
				lc.addValues(lst);

				YAxis ya = new YAxis();
				ya.setMax(22);
				ya.setSteps(2);

				Chart c = new Chart(title);
				c.addElements(lc);
				c.setYAxis(ya);

				String s = OFC.getInstance().prettyPrint(c, 4);
				return s;
			}
		});
		
		return chartWindow;
	}
}
