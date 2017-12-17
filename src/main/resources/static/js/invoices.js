$(function (){
	//declare variable to store the client ID.  needed to fetch billing records
	let clientId;
	//set up display
	$('#show-clients-list').hide();
	$('#billing-records-list').hide();
	if($('#invoice-list').has('tr').length == 0){
		$('#invoice-list-table').hide();
		$('#no-invoices-message').show();
	} else{
		$('#no-invoices-message').hide();
	};
	
	//click event handler to create a new invoice
	$('#create-invoice').click(function(e) {
		e.preventDefault();
		//get call to fetch and display the list of clients that have unassigned billing records
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
		//set up view
		$('#show-invoice-list').hide();
		$('#show-clients-list').show();
	});
	
	//click event handler when a client is selected
	$('#clients-list').on('click', 'a', function(e) {
		e.preventDefault();
		//set the client ID
		clientId = this.id;
		//setup display
		$('#show-clients-list').hide();
		$('#billing-records-list').show();
		//get mapping to fetch and display the available billing records for the client
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
	
	//click event handler to handle creation of the new invoice
	$('#create-invoice-record').on('click','button', function(e) {
		e.preventDefault();
		//store the boxes that are checked on the form
		let checkedBoxes = $('.record-ids:checked');
		//store the recordIds for the checked boxes
		let recordIds=[];
		for(i=0; i<checkedBoxes.length; i += 1){
			recordIds.push(checkedBoxes[i].value);
		}

		//create invoice to send through the request body
		let invoice = {
				invoiceNumber : $('#invoice-number').val(),
				company : {
					id : clientId
				}
		};
		
		//Create security headers
		let headers = {
				'X-CSRF-TOKEN' : $('#create-invoice-csfr').val()
			};
		//create settings
		let settings = {
				headers : headers,
				//add recordIds array to the path
				url : `/api/invoices/clients/${recordIds}`,
				data : JSON.stringify(invoice),
				contentType : 'application/json'
			};
		//post the data
		$.post(settings).done( function(data) {
				$('#invoice-list').append(
						`<tr>
							<td>${data.invoiceNumber}</td>
							<td>${data.company.name}</td>
							<td>${data.createdBy.username}</td>
						</tr>`
				);
				//cleanup stuff for display
				$('#billing-records-list').hide();
				$('#show-invoice-list').show();
				$('#bill-records-list').empty();
				$('#clients-list').empty();
				$('#invoice-list-table').show();
				$('#no-invoices-message').hide();
				$('#invoice-number').val('');
				
	});
	});
	
	
	
});

