
<%@ page language="java" contentType="text/plain; charset=utf-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sky" tagdir="/tags"%>
<fmt:setLocale value="zh_CN"/>
<c:set var="ignoreListAccessControl" value="${true}"/>


<c:if test="${not empty accountChange}">
<div class="col-xs-12 col-ms-6 col-md-4 section">
	
	<div class="inner-section">
	
	<b title="A AccountChange">${userContext.localeMap['account_change']} ${referName}</b><a href="#"><i class="fa fa-refresh" aria-hidden="true"></i></a>
	<hr/>
	<ul>
	
	
	<li><span>ID</span><a class="link-action-removed" href="./accountChangeManager/view/${accountChange.id}/"> ${accountChange.id}</a></li>
<li><span>${userContext.localeMap['account_change.name']}</span> ${accountChange.name}</li>
<li><span>${userContext.localeMap['account_change.previous_balance']}</span> <fmt:formatNumber type="currency"  value="${accountChange.previousBalance}" /></li>
<li><span>${userContext.localeMap['account_change.type']}</span> ${accountChange.type}</li>
<li><span>${userContext.localeMap['account_change.amount']}</span> <fmt:formatNumber type="currency"  value="${accountChange.amount}" /></li>
<li><span>${userContext.localeMap['account_change.current_balance']}</span> <fmt:formatNumber type="currency"  value="${accountChange.currentBalance}" /></li>
<li><span>${userContext.localeMap['account_change.current_status']}</span> ${accountChange.currentStatus}</li>

	
	</ul>
	
	</div><!-- end of inner-section -->
	
</div>

</c:if>




