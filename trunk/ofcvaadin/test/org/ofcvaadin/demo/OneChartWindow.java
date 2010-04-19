package org.ofcvaadin.demo;

import org.ofcvaadin.ui.OpenFlashChart;
import org.ofcvaadin.ui.OpenFlashChart.ChartDataGenerator;

import com.vaadin.Application;
import com.vaadin.ui.Button;
import com.vaadin.ui.Window;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;

public class OneChartWindow extends Window {
	private static final long serialVersionUID = -5162503344338525054L;
	private final Application application;
	private OpenFlashChart chartVaadin;
	
	public OneChartWindow(Application app, String title) {
		super(title);
		application = app;
		
		setWidth("540px");
		setHeight("320px");
		
		chartVaadin = new OpenFlashChart(application);
		chartVaadin.setWidth("500px");
		chartVaadin.setHeight("200px");
		addComponent(chartVaadin);
		
		Button refresh = new Button("Refresh Chart");
		refresh.addListener(new ClickListener(){
			private static final long serialVersionUID = -89669779516839869L;

			@Override
			public void buttonClick(ClickEvent event) {
				chartVaadin.requestRepaint();
			}
		});
		addComponent(refresh);
	}
	
	public void setChartDataGenerator(ChartDataGenerator chartDataGenerator){
		chartVaadin.setChartDataGenerator(chartDataGenerator);
	}
}
