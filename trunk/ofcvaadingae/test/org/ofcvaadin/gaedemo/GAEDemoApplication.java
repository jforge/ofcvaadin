package org.ofcvaadin.gaedemo;

import java.util.ArrayList;
import java.util.List;

import org.ofcvaadin.ui.OpenFlashChart.ChartDataGenerator;
import org.openflashchart.chart.Chart;
import org.openflashchart.component.Labels;
import org.openflashchart.component.Title;
import org.openflashchart.component.ToolTip;
import org.openflashchart.component.X_Axis;
import org.openflashchart.component.Y_Axis;
import org.openflashchart.elements.Area_Hollow;
import org.openflashchart.elements.Bar;
import org.openflashchart.elements.Bar_3d;
import org.openflashchart.elements.Bar_Fade;
import org.openflashchart.elements.Bar_Glass;
import org.openflashchart.elements.Bar_Sketch;
import org.openflashchart.elements.Bar_Stack;
import org.openflashchart.elements.Filled_Bar;
import org.openflashchart.elements.HBar;
import org.openflashchart.elements.Line;
import org.openflashchart.elements.Line_Dot;
import org.openflashchart.elements.Line_Hollow;

import com.vaadin.Application;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.Window;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;

@SuppressWarnings("serial")
public class GAEDemoApplication extends Application {
	@Override
	public void init() {
		Window mainWindow = new Window("Open Flash Chart for Vaadin");
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
		
		setMainWindow(mainWindow);
		
		showAllDemoWindows();
	}
	
	private void showAllDemoWindows(){
		getMainWindow().addWindow(createLineChartDemo());
		getMainWindow().addWindow(createLineDotChartDemo());
		getMainWindow().addWindow(createLineHollowChartDemo());
		getMainWindow().addWindow(createAreaHollowChartDemo());
		getMainWindow().addWindow(createBar3dChartDemo());
		getMainWindow().addWindow(createFilledBarChartDemo());
		getMainWindow().addWindow(createBarGlassChartDemo());
		getMainWindow().addWindow(createHBarChartDemo());
		getMainWindow().addWindow(createBarSketchChartDemo());
		getMainWindow().addWindow(createBarStackChartDemo());
		getMainWindow().addWindow(createBarChartDemo());
		getMainWindow().addWindow(createBarFadeChartDemo());
	}
	
	private Window createLineChartDemo(){
		OneChartWindow chartWindow = new OneChartWindow(this, "Line Chart");
		chartWindow.setChartDataGenerator(new ChartDataGenerator(){
			private static final long serialVersionUID = -3681042720033802269L;

			@Override
			public String getJson() {
				Chart chart = new Chart();
				Title title = new Title();
				title.setText("My name is Title");
				title.setStyle("{color: #736AFF;font-size: 30px;}");

				X_Axis x_axis = new X_Axis();
				x_axis.setColour("#FF0000");
				List<String> x_axisLabels = new ArrayList<String>();
				Labels labels = new Labels();
				x_axisLabels.add("a");
				x_axisLabels.add("b");
				x_axisLabels.add("c");
				x_axisLabels.add("d");
				x_axisLabels.add("e");
				x_axisLabels.add("f");
				x_axisLabels.add("g");
				x_axisLabels.add("h");
				x_axisLabels.add("i");

				labels.setLabels(x_axisLabels);

				x_axis.setLabels(labels);

				Y_Axis y_axis = new Y_Axis();
				y_axis.setColour("#00FF00");
				y_axis.setMax(20);
				y_axis.setSteps(2);

				Line line = new Line("My name is Line");
				line.setColour("#CCDDEE");
				line.setDot__size(10);
				line.setFont__size(12);
				line.setWidth(4);
				line.setValues(new Object[] { 4, 5, 7, 11, 16, 17, 17.3, 18, 18.5 });

				chart.setTitle(title);
				chart.setLine(line);
				chart.setX_Axis(x_axis);
				chart.setY_Axis(y_axis);
				return chart.createChart();
			}
		});
		
		return chartWindow;
	}
	
	private Window createLineDotChartDemo(){
		OneChartWindow chartWindow = new OneChartWindow(this, "Line Dot Chart");
		chartWindow.setChartDataGenerator(new ChartDataGenerator(){
			private static final long serialVersionUID = -3681042720033802269L;

			@Override
			public String getJson() {
				Chart chart = new Chart();

				X_Axis x_axis = new X_Axis();
				x_axis.setColour("#FF0000");
				x_axis.setMax(30);
				String[] x_axisLabels = new String[] { "0.00", "0.38", "0.74", "1.07",
						"1.36", "1.60", "1.77", "1.87", "1.90", "1.85", "1.73", "1.54",
						"1.28", "0.98", "0.64", "0.27", "-0.11", "-0.49", "-0.84",
						"-1.16", "-1.44", "-1.66", "-1.81", "-1.89", "-1.89", "-1.82",
						"-1.68", "-1.47", "-1.20", "-0.88" };

				x_axis.setList_labels(x_axisLabels);

				Y_Axis y_axis = new Y_Axis();
				y_axis.setColour("#00FF00");
				y_axis.setMax(3);

				Line_Dot line_dot = new Line_Dot("My name is Line_Dot");
				line_dot.setHalo__size(2);
				line_dot.setDot__size(3);
				List<Double> values = new ArrayList<Double>();
				for (double i = 0; i < 8; i += 0.2) {
					double val = Math.sin(i) + 1.5;
					values.add(val);
				}
				line_dot.setValues(values);

				chart.setLine_Dot(line_dot);
				chart.setX_Axis(x_axis);
				chart.setY_Axis(y_axis);
				return chart.createChart();
			}
		});
		
		return chartWindow;
	}
	
	private Window createLineHollowChartDemo(){
		OneChartWindow chartWindow = new OneChartWindow(this, "Line Hollow Chart");
		chartWindow.setChartDataGenerator(new ChartDataGenerator(){
			private static final long serialVersionUID = -3681042720033802269L;

			@Override
			public String getJson() {
				Chart chart = new Chart();

				X_Axis x_axis = new X_Axis();
				x_axis.setColour("#FF0000");
				x_axis.setMax(30);
				String[] x_axisLabels = new String[] { "0.00", "0.38", "0.74", "1.07",
						"1.36", "1.60", "1.77", "1.87", "1.90", "1.85", "1.73", "1.54",
						"1.28", "0.98", "0.64", "0.27", "-0.11", "-0.49", "-0.84",
						"-1.16", "-1.44", "-1.66", "-1.81", "-1.89", "-1.89", "-1.82",
						"-1.68", "-1.47", "-1.20", "-0.88" };

				x_axis.setList_labels(x_axisLabels);

				Y_Axis y_axis = new Y_Axis();
				y_axis.setColour("#00FF00");
				y_axis.setMax(3);

				Line_Hollow line_hollow = new Line_Hollow("My name is Line_Hollow");
				line_hollow.setDot__size(4);
				line_hollow.setHalo__size(2);
				List<Double> values = new ArrayList<Double>();
				for (double i = 0; i < 8; i += 0.2) {
					double val = Math.sin(i) + 1.5;
					values.add(val);
				}
				line_hollow.setValues(values);

				chart.setLine_Hollow(line_hollow);
				chart.setX_Axis(x_axis);
				chart.setY_Axis(y_axis);
				return chart.createChart();
			}
		});
		
		return chartWindow;
	}
	
	private Window createAreaHollowChartDemo(){
		OneChartWindow chartWindow = new OneChartWindow(this, "Area Hollow Chart");
		chartWindow.setChartDataGenerator(new ChartDataGenerator(){
			private static final long serialVersionUID = -3681042720033802269L;

			@Override
			public String getJson() {
				Chart chart = new Chart();

				X_Axis x_axis = new X_Axis();
				x_axis.setColour("#FF0000");
				x_axis.setMax(30);
				String[] x_axisLabels = new String[] { "0.00", "0.38", "0.74", "1.07",
						"1.36", "1.60", "1.77", "1.87", "1.90", "1.85", "1.73", "1.54",
						"1.28", "0.98", "0.64", "0.27", "-0.11", "-0.49", "-0.84",
						"-1.16", "-1.44", "-1.66", "-1.81", "-1.89", "-1.89", "-1.82",
						"-1.68", "-1.47", "-1.20", "-0.88" };

				x_axis.setList_labels(x_axisLabels);

				Y_Axis y_axis = new Y_Axis();
				y_axis.setColour("#00FF00");
				y_axis.setMin(-2);
				y_axis.setMax(2);
				// y_axis.setSteps(2);

				Area_Hollow area_Hollow = new Area_Hollow("My name is Area_Hollow");
				area_Hollow.setColour("#FF");
				area_Hollow.setDot__size(3);
				area_Hollow.setFill("#DD3366");
				area_Hollow.setFill__alpha(0.6);
				area_Hollow.setWidth(2);

				Double[] arrayValues = new Double[] { 0.0, 0.37, 0.73, 1.07, 1.36,
						1.59, 1.77, 1.87, 1.89, 1.85, 1.72, 1.53, 1.28, 0.97, 0.63,
						0.26, -0.11, -0.48, -0.84, -1.16, -1.43, -1.65, -1.80, -1.88,
						-1.89, -1.82, -1.67, -1.46, -1.19, -0.88 };
				area_Hollow.setValues(arrayValues);

				chart.setArea_Hollow(area_Hollow);
				chart.setX_Axis(x_axis);
				chart.setY_Axis(y_axis);
				return chart.createChart();
			}
		});
		
		return chartWindow;
	}
	
	private Window createBar3dChartDemo(){
		OneChartWindow chartWindow = new OneChartWindow(this, "Bar 3d Chart");
		chartWindow.setChartDataGenerator(new ChartDataGenerator(){
			private static final long serialVersionUID = -3681042720033802269L;

			@Override
			public String getJson() {
				Chart chart = new Chart();
				X_Axis x_axis = new X_Axis();
				x_axis.setColour("#00EE00");
				x_axis.setSteps(1);
				x_axis.set___3d(6);
				List<Integer> labels = new ArrayList<Integer>();
				labels.add(2);
				labels.add(3);
				labels.add(4);
				labels.add(5);
				x_axis.setList_labels(labels);

				Y_Axis y_axis = new Y_Axis();
				y_axis.setMax(17);

				// ==========3DBar=====================
				Bar_3d bar_3d = new Bar_3d();
				bar_3d.setBar_3d(true);
				bar_3d.setAlpha(0.5);
				bar_3d.setColour("#9933CC");
				bar_3d.setFont__size(10);
				bar_3d.setText("My name is Bar_3d");
				bar_3d.setValues(new Object[] { 7, 9, 6, 11, 8, 4 });

				chart.setBar_3d(bar_3d);
				chart.setX_Axis(x_axis);
				chart.setY_Axis(y_axis);
				chart.setBg_colour("#EEEEEE");
				return chart.createChart();
			}
		});
		
		return chartWindow;
	}
	
	private Window createFilledBarChartDemo(){
		OneChartWindow chartWindow = new OneChartWindow(this, "Filled Bar Chart");
		chartWindow.setChartDataGenerator(new ChartDataGenerator(){
			private static final long serialVersionUID = -3681042720033802269L;

			@Override
			public String getJson() {
				Chart chart = new Chart();
				chart.setBg_colour("#FFFFFF");
				/* Filled_Bar */
				Filled_Bar filled_bar = new Filled_Bar("My name is Filled_Bar");
				filled_bar.setAlpha(0.8);
				filled_bar.setOutline__colour("#577261");
				filled_bar.setColour("#E2D66A");
				filled_bar.setFont__size(10);
				filled_bar.setValues(new Object[] { 9, 8, 7, 6, 5, 4, 3, 2, 1 });

				chart.setFilled_Bar(filled_bar);
				return chart.createChart();
			}
		});
		
		return chartWindow;
	}
	
	private Window createBarGlassChartDemo(){
		OneChartWindow chartWindow = new OneChartWindow(this, "Bar Glass Chart");
		chartWindow.setChartDataGenerator(new ChartDataGenerator(){
			private static final long serialVersionUID = -3681042720033802269L;

			@Override
			public String getJson() {
				Chart chart = new Chart();
				X_Axis x_axis = new X_Axis();
				x_axis.setColour("#00EE00");
				Y_Axis y_axis = new Y_Axis();
				y_axis.setMax(15);

				Bar_Glass bar_glass = new Bar_Glass();
				bar_glass.setColour("#3333CC");
				bar_glass.setFont__size(10);
				bar_glass.setText("My name is Bar_Glass");

				List<Object> bar_glassValues = new ArrayList<Object>();
				bar_glassValues.add(7);
				bar_glassValues.add(9);
				bar_glassValues.add(6);
				bar_glassValues.add(10);
				bar_glassValues.add(2);
				bar_glassValues.add(4);
				bar_glass.setValues(bar_glassValues);
				chart.setBar_Glass(bar_glass);
				chart.setX_Axis(x_axis);
				chart.setY_Axis(y_axis);
				chart.setBg_colour("#EEEEEE");
				return chart.createChart();
			}
		});
		
		return chartWindow;
	}
	
	private Window createHBarChartDemo(){
		OneChartWindow chartWindow = new OneChartWindow(this, "HBar Chart");
		chartWindow.setChartDataGenerator(new ChartDataGenerator(){
			private static final long serialVersionUID = -3681042720033802269L;

			@Override
			public String getJson() {
				Chart chart = new Chart();
				X_Axis x_axis = new X_Axis();
				x_axis.setColour("#00EE00");
				x_axis.setSteps(1);
				x_axis.setList_labels(new Object[] { 2, 3, 4, 5 });
				Y_Axis y_axis = new Y_Axis();
				y_axis.setMin(-1);
				y_axis.setMax(5);

				HBar hbar = new HBar();
				hbar.setText("My name is hbar");
				hbar.setColour("#9933CC");
				hbar.setFont__size(10);
				hbar.setValues(new Object[] { "{right:5}", "{right:9}",
						"{left:13,right:16}" });
				chart.setHBar(hbar);
				chart.setX_Axis(x_axis);
				chart.setY_Axis(y_axis);

				chart.setBg_colour("#EEEEEE");
				return chart.createChart();
			}
		});
		
		return chartWindow;
	}
	
	private Window createBarSketchChartDemo(){
		OneChartWindow chartWindow = new OneChartWindow(this, "Bar Sketch Chart");
		chartWindow.setChartDataGenerator(new ChartDataGenerator(){
			private static final long serialVersionUID = -3681042720033802269L;

			@Override
			public String getJson() {
				Chart chart = new Chart();
				X_Axis x_axis = new X_Axis();
				x_axis.setColour("#00EE00");
				x_axis.setSteps(1);
				x_axis.setList_labels(new Object[] { 2, 3, 4, 5 });

				Y_Axis y_axis = new Y_Axis();
				y_axis.setMin(-1);
				y_axis.setMax(15);

				Bar_Sketch bar_sketch = new Bar_Sketch();
				bar_sketch.setColour("#3333CC");
				bar_sketch.setFont__size(10);
				bar_sketch.setText("My name is Bar_Sketch");
				bar_sketch.setValues(new Object[] { 7, 9, 6, 10, 0, 4 });

				chart.setBar_Sketch(bar_sketch);
				chart.setX_Axis(x_axis);
				chart.setY_Axis(y_axis);

				chart.setBg_colour("#EEEEEE");
				return chart.createChart();
			}
		});
		
		return chartWindow;
	}
	
	private Window createBarStackChartDemo(){
		OneChartWindow chartWindow = new OneChartWindow(this, "Bar Stack Chart");
		chartWindow.setChartDataGenerator(new ChartDataGenerator(){
			private static final long serialVersionUID = -3681042720033802269L;

			@Override
			public String getJson() {
				Chart chart = new Chart();
				X_Axis x_axis = new X_Axis();
				x_axis.setColour("#00EE00");
				x_axis.setList_labels(new Object[] { 2, 3, 4, 5 });

				Y_Axis y_axis = new Y_Axis();
				y_axis.setMin(0);
				y_axis.setMax(15);

				Bar_Stack bar_stack = new Bar_Stack();
				bar_stack.setColour("#3333CC");
				bar_stack.setFont__size(10);
				bar_stack.setText("My name is Bar_Stack");
				bar_stack.setValues(new Object[] { new Object[] { 2.5, 5 },
						new Object[] { 7.5 },
						new Object[] { 5, "{val:5,colour:#ff0000}" },
						new Object[] { 2, 2, 2, 2, "{val:2,colour:#ff00ff}" } });

				chart.setBar_Stack(bar_stack);
				chart.setX_Axis(x_axis);
				chart.setY_Axis(y_axis);

				chart.setBg_colour("#EEEEEE");
				return chart.createChart();
			}
		});
		
		return chartWindow;
	}
	
	private Window createBarChartDemo(){
		OneChartWindow chartWindow = new OneChartWindow(this, "Bar Chart");
		chartWindow.setChartDataGenerator(new ChartDataGenerator(){
			private static final long serialVersionUID = -3681042720033802269L;

			@Override
			public String getJson() {
				Chart chart = new Chart();
				X_Axis x_axis = new X_Axis();
				x_axis.setColour("#00EE00");

				Y_Axis y_axis = new Y_Axis();
				y_axis.setMax(15);

				// ==========Bar=====================
				Bar bar = new Bar();
				bar.setBar_3d(true);
				bar.setAlpha(0.9);
				bar.setColour("#9933CC");
				bar.setFont__size(10);
				bar.setText("My name is bar");
				bar.setValues(new Object[] { 7, 9, 6, 11, null, 4 });
				bar.setTip("Bar 1<br> val = #val#");

				Bar bar2 = new Bar();
				bar2.setBar_3d(true);
				bar2.setAlpha(0.9);
				bar2.setColour("#00AADD");
				bar2.setFont__size(10);
				bar2.setText("My name is bar");
				bar2.setValues(new Object[] { 7, 9, 6, 11, null, 4 });
				bar2.setTip("Bar 2 val = #val#");

				ToolTip toolTip = new ToolTip();
				toolTip.setShadow(false);
				toolTip.setStroke(5);
				toolTip
						.setBody("{font-size: 10px; font-weight: bold; color: #000000;}");
				toolTip.setBackground("#BDB396");
				toolTip.setTitle("{font-size: 14px; color: #CC2A43;}");
				chart.setBar(bar);
				chart.setBar(bar2);
				chart.setToolTip(toolTip);
				chart.setX_Axis(x_axis);
				chart.setY_Axis(y_axis);
				chart.setBg_colour("#EEEEEE");
				return chart.createChart();
			}
		});
		
		return chartWindow;
	}
	
	private Window createBarFadeChartDemo(){
		OneChartWindow chartWindow = new OneChartWindow(this, "Bar Fade Chart");
		chartWindow.setChartDataGenerator(new ChartDataGenerator(){
			private static final long serialVersionUID = -3681042720033802269L;

			@Override
			public String getJson() {
				Chart chart = new Chart();
				X_Axis x_axis = new X_Axis();
				x_axis.setColour("#00EE00");
				x_axis.setSteps(1);
				x_axis.setList_labels(new Object[] { 2, 3, 4, 5 });
				Y_Axis y_axis = new Y_Axis();
				y_axis.setMax(15);
				// ==========Bar=====================
				Bar_Fade bar_fade = new Bar_Fade();
				bar_fade.setBar_3d(true);
				bar_fade.setAlpha(0.9);
				bar_fade.setColour("#9933CC");
				bar_fade.setFont__size(10);
				bar_fade.setText("My name is Bar_Fade");
				bar_fade.setValues(new Object[] { 7, 9, 6, 11, 8, 4 });

				chart.setBar_Fade(bar_fade);
				chart.setX_Axis(x_axis);
				chart.setY_Axis(y_axis);
				chart.setBg_colour("#EEEEEE");
				return chart.createChart();
			}
		});
		
		return chartWindow;
	}
}