<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<script type="text/javascript">

function deleteItems(){
	
	document.itemsForm.action="${pageContext.request.contextPath }/items/deleteItems.action"
	document.itemsForm.submit();
}

function qureyItems(){
	
	document.itemsForm.action="${pageContext.request.contextPath }/items/queryItems.action"
	document.itemsForm.submit();
}
</script>
<title>查询商品列表</title>
</head>
<body>
<form name="itemsForm" action="${pageContext.request.contextPath }/items/queryItems.action" method="post">
查询条件：
<table width="100%" border="1">
<tr>
<!-- 查询条件输入 -->
<td><input name="itemsCustom.itemName"/></td>
<td><input type ="button" value="查询" onclick="qureyItems();"/></td>
<td><input type ="button" value="批量删除" onclick="deleteItems();"/></td>
<td><td><a href="${pageContext.request.contextPath}/items/editqueryItems.action">批量修改</a></td></td>
</tr>
</table>

商品列表：
<table width="100%" border="1">
<tr>

<td>选择</td>
<td>商品名称</td>
<td>商品价格</td>
<td>商品描述</td>
<td>操作</td>
</tr>
<c:forEach items="${itemsList }" var="item">
<tr>
<td><input type="checkbox" name="items_ids" value="${item.id}"/></td>
<td>${item.itemName }</td>
<td>${item.itemPrice }</td>

<td>${item.itemDetail }</td>
<td><a href="${pageContext.request.contextPath}/items/editItems.action?id=${item.id}">修改</a></td>
</tr>
</c:forEach>

</table>
</form>

</body>
</html>