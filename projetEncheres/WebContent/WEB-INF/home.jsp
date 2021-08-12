<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<body>
	<%@include file="/WEB-INF/layout/navbar.jspf" %>
	<div class="text-center">
		<h1><%= languages.getString("enchere") %></h1>		
	</div>
	
	<c:if test="${not empty requestScope.erreurs}">
		<div>
			<ul class="list-group">
				<c:forEach var="erreur" items="${requestScope.erreurs}">
					<li class="list-group-item d-flex list-group-item-danger">${erreur}</li>
				</c:forEach>
			</ul>
		</div>
	</c:if>
	<div class="container">	
		<div class="w-50 mx-auto">
  			<select class="form-select form-select-padding-x-lg" id="locale">
  				<c:if test="${not empty requestScope.categoriesEncheres }">
  					<c:forEach var="categorie" items="${requestScope.categoriesEncheres }">
  						<option value="${categorie.getLibelle() }">${categorie.getLibelle() }</option>
  					</c:forEach>
  				</c:if>
  			</select>
		</div>
	</div>
</body>

