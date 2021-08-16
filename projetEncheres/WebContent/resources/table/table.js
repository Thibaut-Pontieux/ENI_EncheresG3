var $table = $('#table')
var $remove = $('#remove')
var selections = []

function getIdSelections() {
	return $.map($table.bootstrapTable('getSelections'), function (row) {
    				return row.id
    })
}

function responseHandler(res) {
	$.each(res.rows, function (i, row) {
      		row.state = $.inArray(row.id, selections) !== -1
    })
    return res
}
function operateFormatter(value, row, index) {
	return [
    	'<a class="like" href="javascript:void(0)" title="Like">',
    	'<i class="fa fa-heart"></i>',
    	'</a>  ',
    	'<a class="remove" href="javascript:void(0)" title="Remove">',
    	'<i class="fa fa-trash"></i>',
    	'</a>'
    ].join('')
}

window.operateEvents = {
	'click .like': function (e, value, row, index) {
	alert('You click like action, row: ' + JSON.stringify(row))
	},
    
	'click .remove': function (e, value, row, index) {
    	$table.bootstrapTable('remove', {
        	field: 'id',
        	values: [row.id]
      	})
    }
 }

function totalTextFormatter(data) {
	return 'Total'
}

function totalNameFormatter(data) {
	return data.length
}

function totalPriceFormatter(data) {
	var field = this.field
	return '$' + data.map(function (row) {
    	return +row[field].substring(1)
    }).reduce(function (sum, i) {
    	return sum + i
	}, 0)
}
