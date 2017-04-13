<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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
    <script type="text/javascript" src="js/login/jquery.js"></script>
	<script type="text/javascript">
	 $(document).ready(function(){
	 
	 $("#get").click(function(){
	  alert("fdqfeq");
	    $.ajax({
			"dataType":"json",
			"type":"post",
			"contentType": "application/json",
			"url":"appUserLogin.do",
			"data":{"username":"1","password":"1"},
			
			"true":function(data){
			alert("1"+data);
			},
			"false":function(data){
			alert("2"+data);
			}
			});
	  });
	 });
	
	</script>
  </head>
  
  <body>
  
    
    <button class="button" type="button" id="get">提交2</button>
  </body>
</html>
