Open Flash Chart for Vaadin

# Introduction #
Open Flash Chart for Vaadin is a simple jar library, which enables you to use Open Flash Chart in Vaadin. Please check out the demo projects for details.
# Features #
  * Only a jar file. There is no GWT code which requires a GWT compilation.
  * Support any kind of Java library to generate Open Flash Chart JSON string. You can choose what you like.
# Demo #
http://ofcvaadin.appspot.com/
# Install Guideline #
Please follow the steps to setup the ofcvaadin env.
  * Download ofcvaadin.jar
  * Put ofcvaadin.jar to your classpath (In folder war/WEB-INF/lib/)
  * Download [open-flash-chart.swf](http://teethgrinder.co.uk/open-flash-chart-2/)
  * Put open-flash-chart.swf to war/ofc/open-flash-chart.swf
Please don't use root path (/) as your vaadin application path. It will make the path "ofc/open-flash-chart.swf" not accessable to external user. Please use a path like "/myapp/". The following is the config in Demo application:
```
  <servlet>
  	<servlet-name>Demo Application</servlet-name>
  	<servlet-class>com.vaadin.terminal.gwt.server.ApplicationServlet</servlet-class>
  	<init-param>
  		<description>Vaadin application class to start</description>
  		<param-name>application</param-name>
  		<param-value>org.ofcvaadin.demo.DemoApplication</param-value>
  	</init-param>
  </servlet>
  <servlet-mapping>
  	<servlet-name>Demo Application</servlet-name>
  	<url-pattern>/demo/*</url-pattern>
  </servlet-mapping>
```
# Sample Usage with jofc2 #
For the details of jofc2, please see http://code.google.com/p/jofc2/. The following is the sample code to create OpenFlashChart in vaadin.
```
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
```

# Ofcvaadin V2 #
If you want to refresh your chart frequently, ofcvaadin V2 has better performance.
### How to use ofcvaadin V2 ###
You can easily replace OpenFlashChart with OpenFlashChartV2. The two classes have same API.
### When to use ofcvaadin V1 ###
Most of the time, I suggest you to use V1, because V1 implementation is much simple. You will meet less problems when using V1.
If you just display a chart on page without frequent refresh, V1 is the best choice.

# FAQ #
A discussion thread on vaadin forum. http://vaadin.com/forum/-/message_boards/message/150320
# History #
  * 2010-04-30: Version 1.0 released.
  * 2010-04-29: Experiment ofcvaadin V2 with OFC javascript API to improve performance.
  * 2010-04-20: ofcvaadin initial release made available.