$(function (){
	let clientId;
	$('#show-clients-list').hide();
	$('#billing-records-list').hide();
	
	$('#create-invoice').click(function(e) {
		e.preventDefault();
		$('#show-invoice-list').hide();
		$('#show-clients-list').show();
		$.get("/api/invoices/clients", function(data){
			for(let i=0; i<data.length; i += 1){
				$('#clients-list')
				.append(
				`<li class="client">
					<a id="${data[i].id}" class="client" href="/invoices/clients/${data[i].id}">${data[i].name}</a>
				</li>
				`
				);
			};
		});
	});
	
	$('#clients-list').on('click', 'a', function(e) {
		e.preventDefault();
		clientId = this.id;
		$('#show-clients-list').hide();
		$('#billing-records-list').show();
		$.get(`api/invoices/clients/${this.id}`, function(data){
			billingRecordList = data;
			for(let i=0; i<data.length; i += 1){
				let appendString = `<tr>
			          <td>
		            <input type="checkbox"
		                   value="${data[i].id}"
		                   name="recordIds" class="record-ids">
		          </td>
		          <td>${data[i].createdBy.username}</td>
		          <td>${data[i].description}</td>
		          <td>${data[i].client.name}</td>`;
				if(data[i].amount != undefined){
					appendString += `<td>$${data[i].amount}</td>`;
				} else {
					appendString += `<td>N/A</td>`;
					}
				if(data[i].rate != undefined){
					appendString += `<td>$${data[i].rate}</td>`;
				}else {
					appendString += `<td>N/A</td>`;
					}
				if(data[i].quantity != undefined){
					appendString += `<td>${data[i].quantity}</td>`;
				}else {
					appendString += `<td>N/A</td>`;
					}
				appendString += `<td>$${data[i].total}</td>`
				$('#bill-records-list').append(appendString);
			};
			
		})
	});
	$('#create-invoice-record').on('click','button', function(e) {
		e.preventDefault();
		console.log(billingRecordList);
		let checkedBoxes = $('.record-ids:checked');
		let recordIds=[];
		for(i=0; i<checkedBoxes.length; i += 1){
			recordIds.push(checkedBoxes[i].value);
		}

		let invoice = {
				invoiceNumber : $('#invoice-number').val(),
				company : {
					id : clientId
				}
		};
		
		
		console.log(JSON.stringify(invoice));
		let headers = {
				'X-CSRF-TOKEN' : $('#create-invoice-csfr').val()
			};
		let settings = {
				headers : headers,
				url : `/api/invoices/clients/${recordIds}`,
				data : JSON.stringify(invoice),
				contentType : 'application/json'
			};
		$.post(settings).done( function(data) {
				$('#invoice-list').append(
						`<tr>
							<td>${data.invoiceNumber}</td>
							<td>${data.company.name}</td>
							<td>${data.createdBy.username}</td>
						</tr>`
				);
				$('#billing-records-list').hide();
				$('#show-invoice-list').show();
				$('#bill-records-list').empty();
				$('#clients-list').empty();
				
	});
	});
	
	
	
});

