<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@include file="/WEB-INF/layout/navbar.jspf"%>
<body>
	<link href="resources/connexion.css">
	
	<style>
		.container {
    		position: absolute;
    		left: 30%;
    		top: 15%;
    		transfrom: translate(-50%,-50%);
		}
	</style>
	
	
	<c:if test="${not empty requestScope.erreurUser}">
		<div>
			<ul class="list-group">
				<c:forEach var="erreur" items="${requestScope.erreurUser}">
					<li class="list-group-item d-flex list-group-item-danger">${erreur}</li>
				</c:forEach>
			</ul>
		</div>
	</c:if>
	<div class="container">
    	<div class="row">
			<div class="col-md-6 col-md-offset-3">
				<div class="panel panel-login">
					<div class="panel-heading">
						<div class="row">
							<div class="col-xs-6">
								<a href="#" class="active btn btn-lg btn-outline-primary" id="login-form-link"><%= languages.getString("seconnecter") %></a>
								<a href="#" class="btn btn-lg btn-outline-primary" id="register-form-link"><%= languages.getString("inscription") %></a>
							</div>
						</div>
						<hr>
					</div>
					<div class="panel-body">
						<div class="row">
							<div class="col-lg-12">
								<form id="login-form" action="${pageContext.request.contextPath }/connexion" method="post" role="form" style="display: block;">
									<div class="form-group">
										<input type="text" name="username" id="username" tabindex="1" class="form-control" placeholder="<%= languages.getString("pseudo") + " / " + languages.getString("email") %>" value="">
										<br>
									</div>
									<div class="form-group">
										<input type="password" name="password" id="password" tabindex="2" class="form-control" placeholder="<%= languages.getString("mdp") %>">
										<br>
									</div>
									<div class="form-group">
										<div class="row">
											<div class="col-sm-6 col-sm-offset-3">
												<input type="submit" name="login-submit" id="login-submit" tabindex="4" class="form-control btn btn-primary" value="<%= languages.getString("validationConnexion") %>">
											</div>
										</div>
									</div>
								</form>
								<form id="register-form" action="${pageContext.request.contextPath }/signInPage" method="post" role="form" style="display: none;">
									<div class="form-group">
										<input type="text" maxlength="30" class="form-control" name="nom" id="nom" placeholder="<%= languages.getString("nom") %>" required/>
										<br>
									</div>
									<div class="form-group">
										<input type="text" maxlength="30" class="form-control" name="prenom" id="prenom" placeholder="<%= languages.getString("prenom") %>" required/>
										<br>
									</div>
									<div class="form-group">
										<input type="text" maxlength="30" class="form-control" name="pseudo" id="pseudo" placeholder="<%= languages.getString("pseudo") %>" required/>
										<br>
									</div>
									<div class="form-group">
										<input type="email" maxlength="50" class="form-control" name="email" id="email" placeholder="<%= languages.getString("email") %>" required/>
            							<c:if test="${!empty requestScope.error}"><p>Erreur</p></c:if>
										<br>
									</div>
									<div class="form-group">
										<input type="password" maxlength="30" class="form-control" name="mdp" id="mdp" placeholder="<%= languages.getString("mdp") %>" required/>
										<br>
									</div>
									<div class="form-group">
										<input type="tel" maxlength="15" class="form-control" name="phone" id="phone" placeholder="<%= languages.getString("telephone") %>" required/>
           								<c:if test="${!empty requestScope.errorphone}"><p>Wrong Number</p></c:if>
										<br>
									</div>
									<div class="form-group">
										<input type="text" maxlength="30" class="form-control" name="street" id="street" placeholder="<%= languages.getString("adresse") %>" required/>
										<br>
									</div>
									<div class="form-group">
										<input type="text" maxlength="10" class="form-control" name="postalcode" id="postalcode" placeholder="<%= languages.getString("codePostal") %>" required/>
            							<c:if test="${!empty requestScope.errorpostalcode}"><p>Format Invalid</p></c:if>
										<br>
									</div>
									<div class="form-group">
										<input type="text" maxlength="30" class="form-control" name="city" id="city" placeholder="<%= languages.getString("ville") %>" required/>
										<br>
									</div>
									<div class="form-group">
										<div class="row">
											<div class="col-sm-6 col-sm-offset-3">
												<input type="submit" name="register-submit" id="register-submit" tabindex="4" class="form-control btn btn-primary" value="<%= languages.getString("validationInscription") %>">
											</div>
										</div>
									</div>
								</form>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	
	<script src="resources/connexion.js"></script>
	<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
	
</body>