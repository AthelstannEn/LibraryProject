<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="/WEB-INF/custom.tld" prefix="custom"%>

<style>
	.black-background {background-color:#f6f6f6;}
	.white {color:#fff;}
</style>

<nav class="navbar navbar-default navbar-fixed-top"
	style="margin-bottom: 20px">
	<div class="container-fluid">
		<div class="navbar-header">
			
			
		</div>
		<div class="collapse navbar-collapse" id="one">
			<div class="header">
				<ul class="nav navbar-nav">
					<li class="dropdown"><a href="" role="button"
						class="dropdown-toggle" data-toggle="dropdown"
						aria-haspopup="true" aria-expanded="false"> PRODUCTS<span
							class="caret"></span>
					</a>
						<ul class="dropdown-menu">
							<c:forEach items="${types}" var="type">
								<li><a href="/type/${type.id}"><h5>${type.type}</h5></a></li>
								<li role="separator" class="divider"></li>
							</c:forEach>
						</ul>
					<li role="separator" class="divider"></li>


					
					
					<li class="dropdown" style="border-left: 1px solid #e2e2e2"><a
						href="" role="button" class="dropdown-toggle"
						data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
							SUPPORT<span class="caret"></span>
					</a>
						<ul class="dropdown-menu">
							<li><a href="/supportArea">SUPPORT AREA</a></li>
							<li role="separator" class="divider"></li>
							<li><a href="/warrantyAndUpgrade">Be sure to read</a></li>
							<li role="separator" class="divider"></li>
							<li><a href="/peoples">REST people test</a></li>
						</ul></li>

					<form:form class="navbar-form navbar-left form-inline"
						action="/user/searching" method="GET" modelAttribute="filter"
						style="padding-left:220px">
						<custom:hiddenInputs excludeParams="search" />
					
						<div class="form-group">
							<form:input path="search" class="form-control"
								placeholder="I'm looking for..." />
						</div>
						<button class="btn btn-primary black-background white"
							type="submit">FAST SEARCH</button>
						<!-- 							<a class="btn btn-default" href="/user/searching"><span class="glyphicon glyphicon-search" aria-hidden="true"></span></a> -->
					</form:form>

				</ul>
				<ul class="nav navbar-nav navbar-right" style="margin-right: 10px;">

					<sec:authorize access="isAuthenticated()">
						<li
							style="border-left: 1px solid #e2e2e2; border-right: 1px solid #e2e2e2; margin-right: 5px;"><a
							href="/cart" target="_blank"><span class="glyphicon glyphicon-shopping-cart"
								aria-hidden="true"></span> CART</a></li>
					</sec:authorize>

					<sec:authorize access="isAuthenticated()">
						<sec:authorize access="hasRole('ROLE_ADMIN')">
							<li><a href="/admin/goodModels">ADMIN</a></li>
						</sec:authorize>

						<li style="margin-top: 4px; margin-right: 5px;"><h4>
								<b>Hello:</b> ${username}
							</h4></li>

						<li style="margin-top: 7px; margin-right: 5px;"><form:form
								action="/logout" method="POST">
								<button type="submit" class="btn btn-danger">Logout</button>
							</form:form></li>
					</sec:authorize>

					<sec:authorize access="!isAuthenticated()">
						<li style="border-left: 1px solid #e2e2e2"><a href="/login"><span
								class="glyphicon glyphicon-arrow-right" aria-hidden="true"></span>
								SING IN</a></li>
						<li style="border-left: 1px solid #e2e2e2"><a href="/RegForm"><span
								class="glyphicon glyphicon-pencil" aria-hidden="true"></span>
								SING UP</a></li>
					</sec:authorize>

				</ul>
			</div>
		</div>
	</div>
</nav>