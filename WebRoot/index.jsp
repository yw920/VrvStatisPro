<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'index.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
  </head>
  
  <body>
    <!-- <form method="post" action="vrvRegisteredTerminalAction">
			IsDom: <input type="text" name="IsDom" /> 
			<input type="submit" name="submit" value="Submit" />
		</form> -->
		<form method="post" action="vrvActiveStateAction">
			iFunc: <input type="text" name="iFunc" /> 
			strStartTime: <input type="text" name="strStartTime" /> 
			strEndTime: <input type="text" name="strEndTime" />
			deviceId: <input type="text" name="DeviceId" />
			strOfficeName: <input type="text" name="strOfficeName" />
			<input type="submit" name="submit" value="Submit" />
		</form>
		<span class="tree-indent">123456</span>
		<span class="tree-indent">234567</span>
		<span class="tree-indent">345678</span>
<script>
	function delSpanTree(){
  	var nodelist = document.getElementsByClassName("tree-indent");
  	var node = nodelist[0];
  	for(var i=nodelist.length-1;i>=0;i--)
  	{
  		node = nodelist[i];
  		node.parentNode.removeChild(node);
  	}
  }
  delSpanTree();
</script>
  </body>
</html>
