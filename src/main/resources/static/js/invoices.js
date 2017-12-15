$(function (){
	$('#show-clients-list').hide();
	
	$('#create-invoice').click(function(e) {
		e.preventDefault();
		$('#show-invoice-list').hide();
		$('#show-clients-list').show();
	})
	
	
	
})

