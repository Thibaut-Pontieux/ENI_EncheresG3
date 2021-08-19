<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@include file="/WEB-INF/layout/navbar.jspf"%>

<head>
	<meta charset="utf-8" />
	<title><%=languages.getString("accueil")%></title>
</head>

<body>


	<div class="text-center">
		<h1><%=languages.getString("encheres")%></h1>
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
		<form action="${pageContext.request.contextPath }/encheres" method="POST" class="mx-auto">
			<div class="input-group w-50 mx-auto">
				<select class="form-select form-select-padding-x-lg"
					name="catEnchere">
					<c:if test="${not empty requestScope.categoriesEncheres}">
						<option value="Tout" selected><%=languages.getString("toutesCat")%></option>
						<c:forEach var="categorie" items="${requestScope.categoriesEncheres}">
							<c:if
								test="${categorie.getLibelle().equals(requestScope.selectedEnchere)}">
								<option value="${categorie.getLibelle()}" selected>${categorie.getLibelle()}</option>
							</c:if>
							<c:if
								test="${! categorie.getLibelle().equals(requestScope.selectedEnchere)}">
								<option value="${categorie.getLibelle()}">${categorie.getLibelle()}</option>
							</c:if>
						</c:forEach>
					</c:if>
				</select> <input class="form-control mr-sm-2" name="search" type="search"
					placeholder="<%= languages.getString("rechercher") %>"
					aria-label="Search" value="${requestScope.search}"> 
					
					<input type="submit" onclick="document.location.reload(false)"
						name="chercher" class="btn btn-primary btn-lg"
						value="<%=languages.getString("chercher")%>">
						
				<button id="refresh" class="btn" onclick="document.location.reload(false)">
					<i class="fa fa-sync"></i>
				</button>
			</div>
		</form>
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
  			<c:if test="${not empty requestScope.ListeEncheres}">
  				<c:forEach var="enchere" items="${requestScope.ListeEncheres }">
  					<tr>	
  					  <td></td>
                      <td>${enchere.nomArticle}</td>
                      <td>${enchere.prixInitial} <img alt="points" src="resources/coin.png" width="15px" height="15px"></td>
                      <td><fmt:formatDate value="${enchere.dateDebutEnchere}" pattern="dd/MM/yyyy" /></td>
                      <td>${enchere.utilisateur.pseudo}</td>
                      <td><button class="btn btn-primary"><span class="fa fa-file-alt"></span> <%=languages.getString("plusInformations")%></button></td>
  					</tr>
  				</c:forEach>
  			</c:if>
  		</tbody>
	</table>

	<script>
		function detailFormatter(index, row) {
			var html = []
			html.push('<p><b><%=languages.getString("article")%> : </b> ' + row.name + '</p>');
			html.push('<p><b><%=languages.getString("prix")%> : </b> ' + row.price + '</p>');
			html.push('<p><b><%=languages.getString("date")%> : </b> ' + row.date + '</p>');
			html.push('<p><b><%=languages.getString("vendeur")%> : </b> ' + row.author + '</p>');
	    	return html.join('')
		}
		
		$(function() {
			$table.bootstrapTable('destroy').bootstrapTable({
		    	height: 550,
		    	columns: [
		    		[{
		        		field: 'state',
		        		rowspan: 2,
		        		align: 'center',
		        		valign: 'middle',
						visible: false
		        	},
					 {
		        	 	field: 'name',
		         	 	title: '<%=languages.getString("article")%>',
		        		rowspan: 2,
		        		align: 'center',
		        		valign: 'middle',
		        		footerFormatter: totalTextFormatter
		        	 },
					  {
		        	  	title: '<%=languages.getString("informations")%>',
		        	  	colspan: 4,
		        	  	align: 'center'
		        	  }],
		        	[
				 	{
		        		field: 'price',
		        	  	title: '<%=languages.getString("prix")%>',
		         		align: 'center',
		          		footerFormatter: totalPriceFormatter
		         	},
					{
		        		field: 'date',
		        	  	title: '<%=languages.getString("date")%>',
		         		align: 'center',
		          		footerFormatter: totalPriceFormatter
		         	},
				 	{
		        		field: 'author',
		          		title: '<%=languages.getString("vendeur")%>',
		          		align: 'center',
		          		clickToSelect: false
		         	},
					{
						field: 'operate',
		          		title: '<%=languages.getString("actions")%>',
						align : 'center',
						clickToSelect : false
					}]],
			})
			$table.on('check.bs.table uncheck.bs.table '
					+ 'check-all.bs.table uncheck-all.bs.table', function() {
				$remove.prop('disabled', !$table
						.bootstrapTable('getSelections').length)

				// save your data, here just save the current page
				selections = getIdSelections()
				// push or splice the selections if you want to save all data selections
			})
			$remove.click(function() {
				var ids = getIdSelections()
				$table.bootstrapTable('remove', {
					field : 'id',
					values : ids
				})
				$remove.prop('disabled', true)
			})
		})
	</script>
	<script type="text/javascript" src="resources/table/table.js"></script>
</body>

