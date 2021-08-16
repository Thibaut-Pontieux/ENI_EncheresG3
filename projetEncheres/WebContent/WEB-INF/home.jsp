<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<body>
	<%@include file="/WEB-INF/layout/navbar.jspf" %>
	
	<div class="text-center">
		<h1><%= languages.getString("encheres") %></h1>		
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
  				<select class="form-select form-select-padding-x-lg" name="catEnchere">
  					<c:if test="${not empty requestScope.categoriesEncheres }">
  						<c:forEach var="categorie" items="${requestScope.categoriesEncheres }">
  							<option value="${categorie.getLibelle() }">${categorie.getLibelle() }</option>
  						</c:forEach>
  					</c:if>
  				</select>
  				<input type="submit" name="chercher" class="btn btn-success btn-lg" value="<%= languages.getString("chercher") %>"> 
			</div>
		</form>
	</div>
	<table
  		id="table"
  		data-toolbar="#toolbar"
  		data-search="true"
  		data-show-refresh="true"
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
  		data-side-pagination="server"
  		data-response-handler="responseHandler">
  		<tbody>
  			<c:forEach var="enchere" items="${requestScope.ListeEncheres }">
  				<tr>
  					<td></td>
  					<td>${enchere.nomArticle}</td>
  					<td>${enchere.montant}</td>
  					<td>${enchere.dateEnchere}</td>
  					<td>${enchere.utilisateur.pseudo}</td>
  					<td></td>
  				</tr>
  			</c:forEach>
  		</tbody>
	</table>
	
	<script>
		function detailFormatter(index, row) {
			var html = []
			html.push('<p><b><%= languages.getString("vendeur") %> : </b> ' + row.author + '</p>');
			html.push('<p><b><%= languages.getString("date") %> : </b> ' + row.date + '</p>');
			html.push('<p><b><%= languages.getString("article") %> : </b> ' + row.name + '</p>');
			html.push('<p><b><%= languages.getString("prix") %> : </b> ' + row.price + '</p>');
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
		         	 	title: '<%= languages.getString("article") %>',
		        		rowspan: 2,
		        		align: 'center',
		        		valign: 'middle',
		        		sortable: true,
		        		footerFormatter: totalTextFormatter
		        	 },
					  {
		        	  	title: '<%= languages.getString("informations") %>',
		        	  	colspan: 4,
		        	  	align: 'center'
		        	  }],
		        	[
				 	{
		        		field: 'price',
		        	  	title: '<%= languages.getString("prix") %>',
		          		sortable: true,
		         		align: 'center',
		          		footerFormatter: totalPriceFormatter
		         	},
					{
		        		field: 'date',
		        	  	title: '<%= languages.getString("date") %>',
		          		sortable: true,
		         		align: 'center',
		          		footerFormatter: totalPriceFormatter
		         	},
				 	{
		        		field: 'author',
		          		title: '<%= languages.getString("vendeur") %>',
		          		align: 'center',
		          		clickToSelect: false
		         	},
					{
						field: 'operate',
		          		title: '<%= languages.getString("actions") %>',
		          		align: 'center',
		          		clickToSelect: false,
		          		events: window.operateEvents,
		          		formatter: operateFormatter
					}]
		      	],
		    })
		    $table.on('check.bs.table uncheck.bs.table ' +
		      			'check-all.bs.table uncheck-all.bs.table',
		    	function () {
		      		$remove.prop('disabled', !$table.bootstrapTable('getSelections').length)

		      		// save your data, here just save the current page
		      		selections = getIdSelections()
		      		// push or splice the selections if you want to save all data selections
		    })
		    $remove.click(function () {
		      var ids = getIdSelections()
		      $table.bootstrapTable('remove', {
		        field: 'id',
		        values: ids
		      })
		      $remove.prop('disabled', true)
		    })
		  })
	</script>
	<script type="text/javascript" src="resources/table/table.js"></script>
</body>

