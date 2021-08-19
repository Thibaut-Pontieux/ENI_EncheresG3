<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/layout/navbar.jspf" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title><%= languages.getString("ajouterE") %></title>
</head>
<body>
	<div class="text-center">
		<h1><%= languages.getString("ajouterE") %></h1>		
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
	<form method="POST" action="${pageContext.request.contextPath}/ajoutEncheres">
  		<div class="form-group mb-3 w-25 mx-auto">
    		<label class="control-label" for="catEnchere"><%= languages.getString("categorie") %></label>
    		<select class="form-select" name="catEnchere">
  			<c:if test="${not empty requestScope.categoriesEncheres}">
  				<c:forEach var="categorie" items="${requestScope.categoriesEncheres}">
  					<option value="${categorie.getIdCategorie()}">${categorie.getLibelle()}</option>
  				</c:forEach>
  			</c:if>
  			</select>
    	</div>
		<div class="form-group mb-3 w-25 mx-auto">
    		<label class="control-label" for="nomArticle"><%= languages.getString("article") %></label>
    		<input type="text" class="form-control" id="nomArticle" name="nomArticle" maxlength="30" placeholder="<%= languages.getString("articlePH") %>" required>
    	</div>
    	<div class="form-group mb-3 w-25 mx-auto">
    		<label class="control-label" for="descriptionArticle"><%= languages.getString("descArticle") %></label>
    		<textarea class="form-control" id="descriptionArticle" name="descriptionArticle" maxlength="300" placeholder="<%= languages.getString("descArticlePH") %>" required></textarea>
    	</div>  
    	<div class="form-group mb-3 w-25 mx-auto">
    		<label class="control-label" for="prixArticle"><%= languages.getString("prixArticle") %></label>
    		<input type="number" class="form-control" id="prixArticle" name="prixArticle" min="1" max="10000000000" required>
    	</div>  
    	<div class="row mb-3 w-25 mx-auto">
    		<div class="col form-group w-50">
    			<label class="control-label" for="dateDebVente"><%= languages.getString("dateDebVente") %></label>
    			<input type="date" class="form-control" id="dateDebVente" name="dateDebVente" required>
    		</div>
    		<div class="col form-group w-50">
    			<label class="control-label" for="heureDebVente"><%= languages.getString("heureDebVente") %></label>
    			<input type="time" class="form-control" id="heureDebVente" name="heureDebVente" required>
    		</div>
    	</div> 
    	<div class="row mb-3 w-25 mx-auto">
    		<div class="col form-group w-50">
    			<label class="control-label" for="dateFinVente"><%= languages.getString("dateFinVente") %></label>
    			<input type="date" class="form-control" id="dateFinVente" name="dateFinVente" required>
    		</div>
    		<div class="col form-group w-50">
    			<label class="control-label" for="heureFinVente"><%= languages.getString("heureFinVente") %></label>
    			<input type="time" class="form-control" id="heureFinVente" name="heureFinVente" required>
    		</div>
    	</div> 
    	<div class="form-group mb-3 w-25 mx-auto">
    		<label class="control-label" for="adresseRetrait"><%= languages.getString("adresseRetrait") %></label>
    		<input type="text" class="form-control" id="adresseRetrait" name="adresseRetrait" value="${requestScope.infosUtilisateur.getVille() }" readonly>
    	</div>
    	<div class="d-grid gap-2 w-25 mx-auto">
  			<button class="btn btn-success btn-lg" type="submit"><%= languages.getString("validerNvEnchere") %></button>
		</div>
	</form>
</body>
<script type="text/javascript">
	dateDebVente.min = new Date().toISOString().split("T")[0];
	dateFinVente.min = new Date().toISOString().split("T")[0];
	
	$("#dateDebVente").on("change", function(){
		if($(this).val() == new Date().toISOString().split("T")[0]) {
			heureDebVente.min = new Date().getHours()+":"+new Date().getMinutes();
		}
		else{
			heureDebVente.min = null;
		}
		if($(this).val() > $("#dateFinVente").val()) {
			$("#dateFinVente").val('');
		}
		dateFinVente.min = new Date($(this).val()).toISOString().split("T")[0];
	})
	$("#heureDebVente").on("change", function() {
		console.log($(this).val());
		
		if($("#dateDebVente").val() == $("#dateFinVente").val()) {
			heureFinVente.min = $(this).val();
		}
	})
</script>
<style>
.control-label:after {
  content:"*";
  color:red;
}
</style>
</html>