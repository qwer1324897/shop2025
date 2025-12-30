<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body style = "background:blue">

	<h3>Error error code:A131B</h3>
	<p>비정상적인 에러 발생</p>
	<%
		String msg = (String)request.getAttribute("msg");
	%>

</body>
</html>