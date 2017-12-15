$(function() {

	$('#create-companies-form').submit(function(e) {
		e.preventDefault();

		let company = {
			name : $('#company-name').val()
		};
		let headers = {
			'X-CSRF-TOKEN' : $('#company-csrf').val()
		}
		let settings = {
			headers : headers,
			url : '/api/admin/companies',
			data : JSON.stringify(company),
			contentType : 'application/json'
		};

		$.post(settings).done(function(data) {
			console.log('data received', data);
			$('#company-list')
				.append(`<li>${data.name}</li>`);
			$('#company-name').val('');
		})

	});

});