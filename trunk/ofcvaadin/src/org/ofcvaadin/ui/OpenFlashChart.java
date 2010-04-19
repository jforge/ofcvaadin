package org.ofcvaadin.ui;

import java.io.ByteArrayInputStream;
import java.io.Serializable;
import java.net.URL;
import java.util.concurrent.atomic.AtomicInteger;

import com.vaadin.Application;
import com.vaadin.terminal.DownloadStream;
import com.vaadin.terminal.ExternalResource;
import com.vaadin.terminal.PaintException;
import com.vaadin.terminal.PaintTarget;
import com.vaadin.terminal.URIHandler;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Embedded;

public class OpenFlashChart extends CustomComponent {
	private static final long serialVersionUID = 785869449553376573L;
	private static String DEFAULT_FLASH_URL = "../ofc/open-flash-chart.swf";
	private static AtomicInteger s_chartIndex = new AtomicInteger(0);
	
	private final Application application;
	private final String flashUrl;
	private final String dataUrl;
	private final Integer chartIndex;
	private final String relativeDataUri;
	private final ChartURIHandler chartURIHandler;
	
	private Embedded embedded;
	private ChartDataGenerator chartDataGenerator;
	
	public OpenFlashChart(Application app) {
		this(app, DEFAULT_FLASH_URL);
	}
	
	public OpenFlashChart(Application app, String flashUrl) {
		application = app;
		this.flashUrl = flashUrl;
		
		chartIndex = s_chartIndex.incrementAndGet();
		
		relativeDataUri = "ofcvaadindata" + chartIndex;
		dataUrl = application.getURL() + relativeDataUri;
		
		//System.out.println("dataUrl: " + dataUrl);
		
		chartURIHandler = new ChartURIHandler();
		
		addListener(new ChartComponentAttachListener());
		addListener(new ChartComponentDetachListener());
	}
	
	@Override
    public void paintContent(PaintTarget target) throws PaintException {
		//System.out.println("paintContent");
		if(chartDataGenerator == null){
			throw new PaintException ("chartDataGenerator not set. Please call setChartDataGenerator.");
		}
		
		embedded = new Embedded(null, new ExternalResource(flashUrl));
		embedded.setMimeType("application/x-shockwave-flash");
		embedded.setParameter("movie", flashUrl);
		embedded.setParameter("wmode", "transparent"); //transparent, opaque
		embedded.setParameter("flashvars", "data-file=" + dataUrl +
				"?time=" + System.currentTimeMillis());
		embedded.setWidth("100%");
		embedded.setHeight("100%");
		
		setCompositionRoot(embedded);
		
        super.paintContent(target);
    }
	
	public void setChartDataGenerator(ChartDataGenerator chartDataGenerator){
		this.chartDataGenerator = chartDataGenerator;
	}
	
	public interface ChartDataGenerator extends Serializable {
		String getJson ();
	}
	
	private class ChartComponentAttachListener implements ComponentAttachListener {
		private static final long serialVersionUID = -9128593113679475659L;

		@Override
		public void componentAttachedToContainer(ComponentAttachEvent event) {
			//System.out.printf("Attach: %1$s\t%2$s\n", relativeDataUri, chartURIHandler);
			application.getMainWindow().addURIHandler(chartURIHandler);
		}
	}
	
	private class ChartComponentDetachListener implements ComponentDetachListener {
		private static final long serialVersionUID = -5040511274594312779L;

		@Override
		public void componentDetachedFromContainer(ComponentDetachEvent event) {
			//System.out.printf("Detach: %1$s\t%2$s\n", relativeDataUri, chartURIHandler);
			application.getMainWindow().removeURIHandler(chartURIHandler);
		}
	}
	
	private class ChartURIHandler implements URIHandler {
		private static final long serialVersionUID = 926429899365348740L;

		public DownloadStream handleURI(URL context, String relativeUri) {
			if (! relativeDataUri.equals(relativeUri)) {
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
