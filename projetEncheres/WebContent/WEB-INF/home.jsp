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
  			<button id="buttonR" class="btn btn-secondary">refresh</button>
  			<input type="text" id="buttonS" maxlength="30">
		</div>
	</div>

	<table
  		id="table"
  		data-toolbar="#toolbar"
  		data-show-columns-toggle-all="true"
  		data-detail-view="true"
  		data-detail-formatter="detailFormatter"
  		data-minimum-count-columns="2"
  		data-id-field="id"
  		data-page-list="[10, 25, 50, 100, all]"
  		data-side-pagination="server"
  		data-response-handler="responseHandler">
  		<tbody>
  			<c:forEach var="enchere" items="${requestScope.ListeEncheres }">
  				<tr>	
  					<td></td>
  					<td>${enchere.idArticle}</td>
  					<td>${enchere.montant}</td>
  					<td>${enchere.utilisateur.pseudo}</td>
  					<td>${enchere.dateEnchere}</td>
  					<td></td>
  				</tr>
  			</c:forEach>
  		</tbody>
	</table>
	

	
	<script type="text/javascript" src="resources/table/table.js"></script>
	
	<script>
  var $table = $('#table')
  var $button = $('#button')

  $(function() {
    $buttonR.click(function () {
      $table.bootstrapTable('refresh')
    })
    
    $buttonS.click(function ()) {
    	$table.bootstrapTable('search')
    }
    
  })
</script>

</body>

