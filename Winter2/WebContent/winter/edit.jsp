<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

<br>
<br>
<br>

<table border="0">
<c:if test="${!empty dhpTaisyosyaList and  fn:length(dhpTaisyosyaList) != 0 }" >
<c:forEach items="${dhpTaisyosyaList }" var="bean">
<tr> 
<td>${bean.kanyuNo2 }</td>
<td>${bean.fuyouNo2 }</td>
<td>${bean.kigou }</td>
<td>${bean.bangou }</td>
<td>${bean.zokugaraNm }</td>
<td>${bean.kojinNm }</td>
<td>${bean.sex }</td>
<td>${bean.age }</td>
<td>${bean.org }</td>
<td>${bean.note }</td>
</tr>
</c:forEach>
</c:if>
<c:if test="${fn:length(dhpTaisyosyaList) ==0}">
<tr><td>nodata</td></tr>
</c:if>

</table>
</body>
</html>

	