<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
function init(){
	
 document.getElementsByName("hah")[0].value="${hah}"
 document.getElementsByName("hah1")[0].value="${hah1}"
 document.getElementsByName("hah2")[0].value="${hah2}"
 document.getElementsByName("hah3")[0].value="${hah3}"
	
}


</script>
</head>

<body onload="init();">


<form action="/Winter/reParam.do" method="post">
<input type="text" name="hah"/><br>
<input type="text" name="hah1"/><br>
<input type="text" name="hah2"/><br>
<input type="text" name="hah3"/>


<input type="submit" value="submit">

</form>

</body>
</html>