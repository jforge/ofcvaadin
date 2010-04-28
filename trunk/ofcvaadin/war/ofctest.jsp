<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Test OFC</title>
</head>
<body>
Hello World! <br/>

<object classid='clsid:d27cdb6e-ae6d-11cf-96b8-444553540000'
  codebase='http://fpdownload.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=8,0,0,0'
  id='ofcGraph' width='500' height='250'>
	<param name='allowScriptAccess' value='sameDomain' >
	<param name='movie' value='ofc/open-flash-chart.swf' >
	<param name='flashvars' value='data-file=datafile/bar.txt' >
	<embed swliveconnect='true' name='ofcGraph'
		src='ofc/open-flash-chart.swf'
		quality='high'
		bgcolor='#FFFFFF'
		width='500'
		height='250'
		flashvars='data-file=datafile/bar.txt'
		type='application/x-shockwave-flash'>
	</embed>
</object>

<a href='javascript:refresh()'>refresh</a>

<script type="text/javascript">
function getFlashMovieObject(movieName)
{
  if (window.document[movieName]) 
  {
      return window.document[movieName];
  }
  if (navigator.appName.indexOf("Microsoft Internet")==-1)
  {
    if (document.embeds && document.embeds[movieName])
      return document.embeds[movieName]; 
  }
  else
  {
    return document.getElementById(movieName);
  }
}

function refresh(){
	if(typeof getFlashMovieObjectVXX != 'function'){
		function getFlashMovieObjectVXX(movieName)
		{
		  if (window.document[movieName]) 
		  {
		      return window.document[movieName];
		  }
		  if (navigator.appName.indexOf("Microsoft Internet")==-1)
		  {
		    if (document.embeds && document.embeds[movieName])
		      return document.embeds[movieName]; 
		  }
		  else
		  {
		    return document.getElementById(movieName);
		  }
		}
	}
	var g = getFlashMovieObjectVXX('ofcGraph');
	alert(g);
	//alert(typeof xxx == 'function');
	g.reload('datafile/bar2.txt');
}
</script>
</body>
</html>
