$(function (){
	$('#show-clients-list').hide();
	$('#billing-records-list').hide();
	
	$('#create-invoice').click(function(e) {
		e.preventDefault();
		$('#show-invoice-list').hide();
		$('#show-clients-list').show();
		$.get("/api/invoices/clients", function(data){
			console.log(data);
			for(let i=0; i<data.length; i += 1){
				$('#clients-list')
				.append(
				`<li>
					<a href="/invoices/clients/${data[i].id}">${data[i].name}</a>
				</li>
				`		
				);
				console.log(data[i].name);
				console.log(data[i].id);
			}
		})
	})
	
	$('#clients-list').click(function(e) {
		e.preventDefault();
		$('#show-clients-list').hide();
		$('#billing-records-list').show();
	})
	
	
	
})

