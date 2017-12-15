$(function() {
	$('#flat-fee-record-create').submit(function(e) {
		e.preventDefault();
		let billingRecord = {
			description : $('#flat-fee-description').val(),
			amount : $('#flat-fee-amount').val(),
			client : {
				id: $('#flat-fee-client-id').val()
			}
		};
		console.log(JSON.stringify(billingRecord));
		let headers = {
			'X-CSRF-TOKEN' : $('#flat-fee-record-csrf').val()
		};
		let settings = {
			headers : headers,
			url : '/api/flatfees',
			data : JSON.stringify(billingRecord),
			contentType : 'application/json'
		};
		$.post(settings).done( function(data){
			console.log(data);
			$('#table-body')
			.append(`<tr>
			<td>${data.createdBy.username}</td>
			<td>${data.description}</td>
			<td>${data.client.name}</td>	
			<td>${data.amount}</td>	
			<td>-</td>	
			<td>-</td>
			<td>${data.total}</td>				
			</tr>`	
			);
			$('#flat-fee-description').val('');
			$('#flat-fee-amount').val('');
			$('#flat-fee-client-id').val('');
			
		});

	});
	$('#rate-based-record-create').submit(function(e) {
		e.preventDefault();
		let billingRecord = {
			description : $('#rate-based-description').val(),
			rate : $('#rate').val(),
			quantity : $('#quantity').val(),
			client : {
				id: $('#rate-based-client-id').val()
			}
		};
		console.log(JSON.stringify(billingRecord));
		let headers = {
			'X-CSRF-TOKEN' : $('#rate-based-record-csrf').val()
		};
		let settings = {
			headers : headers,
			url : '/api/ratefees',
			data : JSON.stringify(billingRecord),
			contentType : 'application/json'
		};
		$.post(settings).done( function(data){
			console.log(data);
			$('#table-body')
			.append(`<tr>
			<td>${data.createdBy.username}</td>
			<td>${data.description}</td>
			<td>${data.client.name}</td>	
			<td>-</td>	
			<td>${data.rate}</td>	
			<td>${data.quantity}</td>
			<td>${data.total}</td>				
			</tr>`	
			);
			$('#rate-based-description').val('');
			$('#rate').val('');
			$('#rate-based-client-id').val('');
			$('#quantity').val('');
			
		});

	});

})
