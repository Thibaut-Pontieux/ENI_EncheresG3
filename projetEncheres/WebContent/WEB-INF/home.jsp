<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<body>
	<%@include file="/WEB-INF/layout/navbar.jspf" %>
	<script type="text/javascript" src="resources/table/table.js"></script>
	<script type="text/javascript">
	var enchere = [];
	<c:forEach items="${requestScope.ListeEncheres }" var="enchere">
		enchere.push({id: ${enchere.idArticle}, price: ${enchere.montant} })
	</c:forEach>
	getEncheres(enchere);
	</script>
	

	<div class="text-center">
		<h1>Enchères</h1>		
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
	<table
  		id="table"
  		data-toolbar="#toolbar"
  		data-search="true"
  		data-show-refresh="true"
  		data-show-toggle="true"
  		data-show-columns="true"
  		data-show-columns-toggle-all="true"
  		data-detail-view="true"
 		data-show-export="true"
 	 	data-click-to-select="true"
  		data-detail-formatter="detailFormatter"
  		data-minimum-count-columns="2"
  		data-show-pagination-switch="true"
  		data-pagination="true"
  		data-id-field="id"
  		data-page-list="[10, 25, 50, 100, all]"
		data-url="resources/table/data.js"
  		data-side-pagination="server"

  		data-response-handler="responseHandler">
	</table>
	

	
	
</body>

