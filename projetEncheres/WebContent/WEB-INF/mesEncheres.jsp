<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/layout/navbar.jspf" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title><%= languages.getString("mesEncheres") %></title>
</head>
<body>
	<div class="text-center">
		<h1><%= languages.getString("mesEncheres") %></h1>		
	</div>
	<div class="container-fluid d-grid gap-2 w-25">
		<a href="${pageContext.request.contextPath}/ajoutEncheres" class="btn btn-primary btn-block btn-lg" role="button"><%= languages.getString("ajouterE") %></a>
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
	
	<table
  		id="table"
  		data-toolbar="#toolbar"
  		data-detail-view="true"
  		data-detail-formatter="detailFormatter"
  		data-minimum-count-columns="2"
  		data-id-field="id">
  		<tbody>
  			<c:if test="${not empty requestScope.ListeEncheres}">
  				<c:forEach var="enchere" items="${requestScope.ListeEncheres }">
  					<tr>	
  					  <td></td>
                      <td>${enchere.nomArticle}</td>
                      <td>${enchere.prixInitial} <img alt="points" src="resources/coin.png" width="15px" height="15px"></td>
                      <td><fmt:formatDate value="${enchere.dateDebutEnchere}" pattern="dd/MM/yyyy" /></td>
                      <td></td>
  					</tr>
  				</c:forEach>
  			</c:if>
  		</tbody>
	</table>
	
	<script>
		function detailFormatter(index, row) {
			var html = []
			html.push('<p><b><%= languages.getString("article") %> : </b> ' + row.name + '</p>');
			html.push('<p><b><%= languages.getString("prix") %> : </b> ' + row.price + '</p>');
			html.push('<p><b><%= languages.getString("date") %> : </b> ' + row.date + '</p>');		
	    	return html.join('')
		}
		
		$(function() {
			$('#table').bootstrapTable('destroy').bootstrapTable({
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
		         	 	title: '<%= languages.getString("article") %>',
		        		rowspan: 2,
		        		align: 'center',
		        		valign: 'middle',
		        		sortable: true
		        	 },
					  {
		        	  	title: '<%= languages.getString("informations") %>',
		        	  	colspan: 3,
		        	  	align: 'center'
		        	  }],
		        	[
				 	{
		        		field: 'price',
		        	  	title: '<%= languages.getString("prix") %>',
		          		sortable: true,
		         		align: 'center'
		         	},
					{
		        		field: 'date',
		        	  	title: '<%= languages.getString("date") %>',
		          		sortable: true,
		         		align: 'center'
		         	},
					{
						field: 'operate',
		          		title: '<%= languages.getString("actions") %>',
		          		align: 'center',
		          		clickToSelect: false
					}]
		      	],
		    })
		  })
	</script>
</body>
</html>