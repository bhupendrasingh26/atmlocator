$(document).ready(function() {
	$(document).keypress(function(e) {
		if (e.which == 13) {
			$('#search').trigger('click');
		}
	});

	$('#search').click(function() {
		var city = $('#city').val();
		$.ajax({
			type : 'GET',
			url : 'http://localhost:8080/atmlocator/locate?city=' + city,
			dataType : 'json'
		}).then(function(data) {
			renderLocationInfo(data);
		});
	});
});

function renderLocationInfo(data) {
	var table = $('#atmstable');
	table.find('> tbody > tr').remove();
	data.forEach(function(loc) {
		table.find('> tbody:last-child').append(
				'<tr>' + '<td>' + loc.type + '</td>' + '<td>'
						+ loc.address.street + '</td>' + '<td>'
						+ loc.address.housenumber + '</td>' + '<td>'
						+ loc.address.postalcode + '</td>' + '<td>'
						+ loc.address.city + '</td>' + '<td>'
						+ loc.address.geoLocation.lat + '</td>' + '<td>'
						+ loc.address.geoLocation.lng + '</td>' + '<td>'
						+ loc.distance + '</td>' + '</tr>');
	});
}
