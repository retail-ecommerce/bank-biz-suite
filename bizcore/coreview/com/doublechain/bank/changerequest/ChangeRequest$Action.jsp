
<%@ page language="java" contentType="text/plain; charset=utf-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sky" tagdir="/tags"%>
<fmt:setLocale value="zh_CN"/>
<c:set var="ignoreListAccessControl" value="${true}"/>


<c:if test="${not empty changeRequest}">

<div class="col-xs-12 col-ms-4 col-md-3 action-section" >
	<b title="A ChangeRequest" style="color: green">${userContext.localeMap['change_request']}</b>
	<hr/>
	<ul>
	
	
	<li><span>${userContext.localeMap['change_request.id']}</span> ${changeRequest.id}</li>
<li><span>${userContext.localeMap['change_request.name']}</span> ${changeRequest.name}</li>
<li><span>${userContext.localeMap['change_request.create_time']}</span> <fmt:formatDate pattern="yyyy-MM-dd" value="${changeRequest.createTime}" /></li>

	
	</ul>
</div>



</c:if>


