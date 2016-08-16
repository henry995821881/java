<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<script type="text/javascript">

function editAllItems(){
	
	document.itemsForm.action="${pageContext.request.contextPath }/items/editAllSubmit.action"
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
<td><input type ="button" value="提交修改" onclick="editAllItems();"/></td>
</tr>
</table>

商品列表：
<table width="100%" border="1">
<tr>

<td>商品id</td>
<td>商品名称</td>
<td>商品价格</td>
<td>商品描述</td>

<td>生产时间</td>
</tr>
<c:forEach items="${itemsList }" var="item" varStatus="status">


<tr>

<td><input readonly="readonly" name="itemsList[${status.index}].id" value=" ${item.id }"/></td>
<td><input name="itemsList[${status.index}].itemName" value=" ${item.itemName }"/></td>
<td><input name="itemsList[${status.index}].itemPrice" value=" ${item.itemPrice }"/></td>
<td><input name="itemsList[${status.index}].itemDetail" value=" ${item.itemDetail }"/></td>
<td><input name="itemsList[${status.index}].createTime" value="<fmt:formatDate value="${item.createTime }" pattern="yyyy-MM-dd HH:mm:ss" />"/></td>
</tr>
</c:forEach>

</table>
</form>

</body>
</html>