<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>商品修改</title>

</head>
<body>
<c:if test="${allErrors !=null }">
<p style="color: red;">错误提示：</p>
<!-- 显示错误信息 -->
<c:forEach items="${allErrors }" var="error">
 ${error.defaultMessage }
</c:forEach>
<hr/>
</c:if>


<form  id="itemForm" action="${pageContext.request.contextPath}/items/editItemsUpdate.action" method="post" >

<input type="hidden" name="id" value="${item.id }"/>
商品修改：
<table>
<tr>
<td>商品名称</td>
<td><input type="text" name ="itemName" value="${item.itemName}"/></td>
</tr>

<tr>
<td>商品价格</td>
<td><input type="text" name="itemPrice" value="${item.itemPrice }"/></td>

</tr>

<tr>
<td>商品明细</td>
<td><input type="text" name="itemDetail" value="${item.itemDetail}"/></td>


<tr>
<td>生产日期</td>
<td><input type="text" name="createTime"  value="<fmt:formatDate value="${item.createTime }" pattern="yyyy-MM-dd HH:mm:ss" />"/></td>

</tr>
<tr> <input type="submit" value="提交修改"/></tr>
</table>

</form>

</body>
</html>