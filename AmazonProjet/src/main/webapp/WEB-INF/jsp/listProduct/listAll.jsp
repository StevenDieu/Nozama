<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:include page="../templates/header.jsp" />
<div class="panel panel-default">
	<div class="panel-heading">
		<h3 class="panel-title">Filtre :</h3>
	</div>
	<div class="panel-body">

		<form action="/liste-tous-les-produits" class="formAll" method="get">

			<div class="blocFiltre">
				Année :
				<select id="years" name="years">
					<option value="AllYears">Toutes les années</option>
					<option value="2010" <c:if test="${years == '2010' }">selected</c:if>>Année 2010</option>
					<option value="2000" <c:if test="${years == '2000' }">selected</c:if>>Année 2000</option>
					<option value="1990" <c:if test="${years == '1990' }">selected</c:if>>Année 90</option>
					<option value="1980" <c:if test="${years == '1980' }">selected</c:if>>Année 80</option>
					<option value="1970" <c:if test="${years == '1970' }">selected</c:if>>Année 70</option>
					<option value="1960" <c:if test="${years == '1960' }">selected</c:if>>Année 60</option>
					<option value="1950" <c:if test="${years == '1950' }">selected</c:if>>Année 50</option>
					<option value="1940" <c:if test="${years == '1940' }">selected</c:if>>Année 40</option>
					<option value="1939" <c:if test="${years == '1939' }">selected</c:if>>Antérieur</option>
				</select>
			</div>

			<div class="blocFiltre">
				<input type="submit" class="btn btn-primary" value="Filtrer" />
			</div>
		</form>
	</div>
</div>

<div class="clearBoth"></div>



	<jsp:include page="listProduct.jsp" />



<div class="clearBoth"></div>
<div class="paginationAll center100"></div>

<script>
	var varNumberPage = ${numberPage};
	var varStartPage = ${startPage};
</script>

<script src="/resources/js/jquery.twbsPagination.min.js"></script>
<script src="/resources/js/listeProduct.js"></script>


<jsp:include page="../templates/footer.jsp" />