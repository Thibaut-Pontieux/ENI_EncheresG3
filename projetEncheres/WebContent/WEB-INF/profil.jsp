<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@include file="/WEB-INF/layout/navbar.jspf" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <title><%=languages.getString("connexion")%></title>
    </head>
    <body>
		
        <c:if test="${not empty requestScope.erreurs}">
			<div>
				<ul class="list-group">
					<c:forEach var="erreur" items="${requestScope.erreurs}">
						<li class="list-group-item d-flex list-group-item-danger">${erreur}</li>
					</c:forEach>
				</ul>
			</div>
		</c:if>
        
        <form method="post" action="signInPage" class="container">
        
        	<h1 class="mt-2" style="display:flex; justify-content:center">Inscription</h1>
        
        	<div class="form-row">
        	
  			<div class="form-group col-md-6 mx-auto">
            <label for="nom">Nom : </label>
            <input type="text" maxlength="30" class="form-control" name="nom" id="nom" required/>
            </div>
            
            <div class="form-group col-md-6 mx-auto">
            <label for="prenom">Prénom : </label>
            <input type="text" maxlength="30" class="form-control" name="prenom" id="prenom" required/>
            </div>
            
            <div class="form-group col-md-6 mx-auto">
            <label for="pseudo">Pseudo : </label>
            <input type="text" maxlength="30" class="form-control" name="pseudo" id="pseudo" required/>
            </div>
            
            <div class="form-group col-md-6 mx-auto">
            <label for="email">Email : </label>
            <input type="email" maxlength="50" class="form-control" name="email" id="email" required/>
            <c:if test="${!empty requestScope.error}"><p>Erreur</p></c:if>
            </div>
            
            <div class="form-group col-md-6 mx-auto">
            <label for="mdp">Mot de passe : </label>
            <input type="password" maxlength="30" class="form-control" name="mdp" id="mdp" required/>
            </div>
            
            <div class="form-group col-md-6 mx-auto">
            <label for="mdp">Téléphone : </label>
            <input type="tel" maxlength="15" class="form-control" name="phone" id="phone" required/>
            <c:if test="${!empty requestScope.errorphone}"><p>Wrong Number</p></c:if>
            </div>
            
           	<div class="form-group col-md-6 mx-auto">
            <label for="street">Rue : </label>
            <input type="text" maxlength="30" class="form-control" name="street" id="street" required/>
            </div>
            
            <div class="form-group col-md-6 mx-auto">
            <label for="postalcode">Code Postal : </label>
            <input type="text" maxlength="10" class="form-control" name="postalcode" id="postalcode" required/>
            <c:if test="${!empty requestScope.errorpostalcode}"><p>Format Invalid</p></c:if>
            </div>
            
            <div class="form-group col-md-6 mx-auto">
            <label for="city">Ville : </label>
            <input type="text" maxlength="30" class="form-control" name="city" id="city" required/>
            </div>

  			</div>
  			
  			<div class="text-center">
  			<button class="btn btn-primary mt-4" type="submit">Valider</button>
 			</div>
 			
        </form>
        
    </body>
</html>