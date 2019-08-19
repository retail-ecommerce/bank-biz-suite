
<%@ page language="java" contentType="text/plain; charset=utf-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sky" tagdir="/tags"%>
<fmt:setLocale value="zh_CN"/>
<c:set var="ignoreListAccessControl" value="${true}"/>


<c:if test="${not empty accountChange}">

<div class="col-xs-12 col-ms-4 col-md-3 action-section" >
	<b title="A AccountChange" style="color: green">${userContext.localeMap['account_change']}</b>
	<hr/>
	<ul>
	
	
	<li><span>${userContext.localeMap['account_change.id']}</span> ${accountChange.id}</li>
<li><span>${userContext.localeMap['account_change.name']}</span> ${accountChange.name}</li>
<li><span>${userContext.localeMap['account_change.previous_balance']}</span> <fmt:formatNumber type="currency"  value="${accountChange.previousBalance}" /></li>
<li><span>${userContext.localeMap['account_change.type']}</span> ${accountChange.type}</li>
<li><span>${userContext.localeMap['account_change.amount']}</span> <fmt:formatNumber type="currency"  value="${accountChange.amount}" /></li>
<li><span>${userContext.localeMap['account_change.current_balance']}</span> <fmt:formatNumber type="currency"  value="${accountChange.currentBalance}" /></li>

	
	</ul>
</div>



</c:if>


