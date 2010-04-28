package org.ofcvaadin.ui;

import java.io.ByteArrayInputStream;
import java.io.Serializable;
import java.net.URL;
import java.util.concurrent.atomic.AtomicInteger;

import com.vaadin.Application;
import com.vaadin.terminal.DownloadStream;
import com.vaadin.terminal.PaintException;
import com.vaadin.terminal.PaintTarget;
import com.vaadin.terminal.URIHandler;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Label;

@SuppressWarnings("serial")
public class OpenFlashChartV2 extends CustomComponent {
	private static String DEFAULT_FLASH_URL = "../ofc/open-flash-chart.swf";
	private static AtomicInteger s_chartIndex = new AtomicInteger(0);
	
	private final Application application;
	private final String flashUrl;
	private final Integer chartIndex;
	private final String dataUrl;
	private final String relativeDataUrl;
	private final String flashObjectId;
	private final ChartURIHandler chartURIHandler;
	private boolean initialized = false;
	private int jsExecutedTimes = 0;
	
	private ChartDataGenerator chartDataGenerator;
	
	public OpenFlashChartV2(Application app) {
		this(app, DEFAULT_FLASH_URL);
	}
	
	public OpenFlashChartV2(Application app, String flashUrl) {
		application = app;
		this.flashUrl = flashUrl;
		
		chartIndex = s_chartIndex.incrementAndGet();
		
		flashObjectId = "ofcvaadin_chart_id_" + chartIndex;
		relativeDataUrl = "ofcvaadindata_v2_" + chartIndex;
		dataUrl = application.getURL() + relativeDataUrl;
		
		chartURIHandler = new ChartURIHandler();
		
		addListener(new ChartRepaintRequestListener());
		addListener(new ChartComponentAttachListener());
		addListener(new ChartComponentDetachListener());
	}
	
	private String getJavascript(){
		return 
		getJsonFuncContent(chartIndex, chartDataGenerator.getJson())
		+ defineFunc("get_ofc_movie_object", getFlashMovieObjectFunc())
		+ defineFunc(getLoadJsonFuncName(chartIndex), getLoadJsonFuncContent(chartIndex))
		+ loadJson(chartIndex);
	}
	
	@Override
    public void paintContent(PaintTarget target) throws PaintException {
		System.out.println("paintContent");
		if(chartDataGenerator == null){
			throw new PaintException ("chartDataGenerator not set. Please call setChartDataGenerator.");
		}
		
		System.out.println("jsExecutedTimes: " + jsExecutedTimes);
		if(jsExecutedTimes == 0){
			application.getMainWindow().executeJavaScript(getJavascript());
		}
		jsExecutedTimes = 0;
		
		if(!initialized){
			System.out.println("initialize");
			Label label = new Label(
"<object classid='clsid:d27cdb6e-ae6d-11cf-96b8-444553540000'\n" +
"  codebase='http://fpdownload.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=8,0,0,0'\n" +
"  id='" + flashObjectId + "' width='500px' height='200px'>\n" +
"	<param name='allowScriptAccess' value='sameDomain' />\n" +
"	<param name='wmode' value='transparent' />\n" +
"	<param name='movie' value='" + flashUrl + "' />\n" +
//"	<param name='flashvars' value='get-data="+ getJsonFuncName(chartIndex) + "' >\n" +
"	<param name='flashvars' value='data-file=" + dataUrl + "?time=" + System.currentTimeMillis() + "' >\n" +
"	<embed name='" + flashObjectId + "'\n" +
"		src='" + flashUrl + "'\n" +
"		quality='high'\n" +
"		bgcolor='#FFFFFF'\n" +
"		wmode='transparent'\n" +
"		width='500px'\n" +
"		height='200px'\n" +
"		name='open-flash-chart'\n" +
//"		flashvars='get-data="+ getJsonFuncName(chartIndex) + "' >\n" +
"		flashvars='data-file=" + dataUrl + "?time=" + System.currentTimeMillis() + "' >\n" +
"		align='middle'\n" +
"		allowScriptAccess='sameDomain'\n" +
"		type='application/x-shockwave-flash'\n" +
"		pluginspage='http://www.macromedia.com/go/getflashplayer'>\n" +
"	</embed>\n" +
"</object>",
					Label.CONTENT_XHTML);
			
			setCompositionRoot(label);
			initialized = true;
		}
		
        super.paintContent(target);
    }
	
	private class ChartRepaintRequestListener implements RepaintRequestListener {
		private static final long serialVersionUID = -9128593113679475659L;
		
		@Override
		public void repaintRequested(RepaintRequestEvent event) {
			System.out.println("repaintRequested");
			if(initialized){
				System.out.println("jsExecutedTimes: " + jsExecutedTimes);
				if(jsExecutedTimes == 0){
					application.getMainWindow().executeJavaScript(getJavascript());
				}
				jsExecutedTimes ++;
			}
		}
	}
	
	private class ChartComponentAttachListener implements ComponentAttachListener {
		private static final long serialVersionUID = -9128593113679475659L;

		@Override
		public void componentAttachedToContainer(ComponentAttachEvent event) {
			application.getMainWindow().addURIHandler(chartURIHandler);
		}
	}
	
	private class ChartComponentDetachListener implements ComponentDetachListener {
		private static final long serialVersionUID = -5040511274594312779L;

		@Override
		public void componentDetachedFromContainer(ComponentDetachEvent event) {
			application.getMainWindow().removeURIHandler(chartURIHandler);
		}
	}
	
	private String loadJson(int id){
		return
			"setTimeout(\"" + getLoadJsonFuncName(id) + "()\", " + (initialized ? 0 : 150) + ");";
	}
	
	private String defineFunc(String funcName, String func){
		return 
			"if(typeof " + funcName + " != 'function'){" +
			func +
			"}";
	}
	
	private String getLoadJsonFuncContent(int id){
		return 
			"function " + getLoadJsonFuncName(id) + "(){\n" +
			"	var g = get_ofc_movie_object('" + flashObjectId + "');\n" +
			"	if(typeof g == 'undefined' || g == null)return;\n" +
			"	if(typeof g.load == 'function'){\n" +
			"		g.load(" +  getJsonFuncName(id) + "());\n" +
			"	}\n" +
			"}";
	}
	
	private String getJsonFuncContent(int id, String json){
		return 
			"function " + getJsonFuncName(id) + "(){\n" +
			"	return \"" +  getJsonStringify (json) + "\";" +
			"}";
	}
	
	private String getJsonFuncName(int id){
		return "get_ofcvaadin_data_" + id;
	}
	
	private String getLoadJsonFuncName(int id){
		return "load_ofcvaadin_data_" + id;
	}
	
	private String getJsonStringify(String json){
		json = json.replace("\\", "\\\\");
		json = json.replace("\"", "\\\"");
		json = json.replace("\'", "\\\'");
		json = json.replace("\n", "\\n");
		json = json.replace("\r", "\\r");
		return json;
	}
	
	private String getFlashMovieObjectFunc(){
		return 
		"function get_ofc_movie_object(movieName)\n" +
		"{\n" +
		"  if (window.document[movieName]) \n" +
		"  {\n" +
		"      return window.document[movieName];\n" +
		"  }\n" +
		"  if (navigator.appName.indexOf('Microsoft Internet')==-1)\n" +
		"  {\n" +
		"    if (document.embeds && document.embeds[movieName])\n" +
		"      return document.embeds[movieName]; \n" +
		"  }\n" +
		"  else\n" +
		"  {\n" +
		"    return document.getElementById(movieName);\n" +
		"  }\n" +
		"}";
	}
	
	public void setChartDataGenerator(ChartDataGenerator chartDataGenerator){
		this.chartDataGenerator = chartDataGenerator;
	}
	
	public interface ChartDataGenerator extends Serializable {
		String getJson ();
	}
	
	private class ChartURIHandler implements URIHandler {
		private static final long serialVersionUID = 926429899365348740L;

		public DownloadStream handleURI(URL context, String relativeUri) {
			if (! relativeDataUrl.equals(relativeUri)) {
				return null;
			}
			//System.out.println("ChartURIHandler: url=" + relativeUri);
			
			if(chartDataGenerator == null){
				System.err.println("chartDataGenerator not set. Please call setChartDataGenerator.");
			}
			
			String json = chartDataGenerator.getJson();
			//System.out.println("json=" + json);
			ByteArrayInputStream istream = new ByteArrayInputStream(
					json.getBytes());
			return new DownloadStream(istream, null, null);
		}
	}
}
