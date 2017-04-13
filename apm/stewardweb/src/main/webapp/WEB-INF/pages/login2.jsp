<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<script type="text/javascript" src="js/login/jquery.js"></script>
<script type="text/javascript">
	$().ready(function(){
		
		$("#btn").click(function(){
			
			var username=$("#username").val();
			var password=$("#password").val();
			var email=$("#email").val();
			alert("username="+username+"   password="+password +"  email"+email);
			if(username==null){
				alert("用户名为空");
			}else{
				$.ajax({  
					 url:"appUserSign.do",
					 contentType:"application/json",
					 data:{username:username,password:password,email:email},
					 success:function(data){  
						 alert("data="+data);
					 }
					 }); 
	//			$.post("getToken.do",{username:username},function(data){
	//				alert(data);
	//			})
			}
		})
	})

</script>
</head>
<body>

用户名:<input type ="text" id ="username" name ="username"/>
password:<input type ="text" id ="password" name ="password"/>
email:<input type ="text" id ="email" name ="email"/>
<input type="button" id ="btn"value="提交">
</body>
</html>