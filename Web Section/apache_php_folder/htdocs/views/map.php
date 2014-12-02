<script>

var webServiceURL = 'http://54.69.0.233:8080/iGoWeb/HappyPathClientService?WSDL';
/* var getRestaurants = '<?xml version="1.0" encoding="UTF-8"?><S:Envelope xmlns:S="http://schemas.xmlsoap.org/soap/envelope/" xmlns:SOAP-ENV="http://schemas.xmlsoap.org/soap/envelope/"> \
    <SOAP-ENV:Header/> \
    <S:Body> \
        <ns2:getYelpData xmlns:ns2="http://local/"> \
            <arg0>seeRestaurants</arg0> \
            <arg1>1</arg1> \
            <arg2>0</arg2> \
        </ns2:getYelpData> \
    </S:Body> \
</S:Envelope>';
*/

function CallService()
{
	/*
    $.ajax({
        url: webServiceURL, 
        type: "POST",
        dataType: "json", 
        processData: false,
        data: getRestaurants, 
        success: OnSuccess, 
        error: OnError
    }); */
	$.soap({
		url: webServiceURL,
		method: 'getYelpData',

		data: {
			arg0: 'seeRestaurants',
			arg1: '1',
			arg2: '0'
		},
		
		success: function (soapResponse) {
    		alert(soapResponse.toJSON());
			// do stuff with soapResponse
			// if you want to have the response as JSON use soapResponse.toJSON();
			// or soapResponse.toString() to get XML string
			// or soapResponse.toXML() to get XML DOM
		},
		error: function (SOAPResponse) {
			debugger;
			alert(SOAPResponse);
		}
	});
    return false;
}

var mapjsonurl = "/assets/temp/map.json";
var dataset = "";
// Note: This example requires that you consent to location sharing when
// prompted by your browser. If you see a blank space instead of the map, this
// is probably because you have denied permission for location sharing.

var testjson = {"Locations": [{"lat":41.877849999999995,"lng":-87.64101670000002,"content":"Event: Words","Location_Id":5 }]};
var myLatlng = new google.maps.LatLng(testjson["Locations"][0]["lat"],testjson["Locations"][0]["lng"]);

var map;

function initialize() {
	CallService();
	var mapOptions = {
	zoom: 6
	};
	map = new google.maps.Map(document.getElementById('map-canvas'),
	  mapOptions);

	// Try HTML5 geolocation
	if(navigator.geolocation) {
	navigator.geolocation.getCurrentPosition(function(position) {
	  var pos = new google.maps.LatLng(position.coords.latitude,
	                                   position.coords.longitude);

	  var infowindow = new google.maps.InfoWindow({
	    map: map,
	    position: pos,
	    content: 'Location found using HTML5: ' + pos.lat() + ' by ' + pos.lng()
	  });

	  map.setCenter(pos);
	}, function() {
	  handleNoGeolocation(true);
	});
	} else {
	// Browser doesn't support Geolocation
	handleNoGeolocation(false);
	}
}

function handleNoGeolocation(errorFlag) {
  if (errorFlag) {
    var content = 'Error: The Geolocation service failed.';
  } else {
    var content = 'Error: Your browser doesn\'t support geolocation.';
  }

  var options = {
    map: map,
    position: new google.maps.LatLng(60, 105),
    content: content
  };

  var infowindow = new google.maps.InfoWindow(options);
  map.setCenter(options.position);
}

  function locationclick() {
  	console.log($(this));
  	console.log(dataset);
  	// console.log($(this).attr("obj"));
  	// window.alert(dataset["Locations"][this.attr("obj")]);
  };

google.maps.event.addDomListener(window, 'load', initialize);
</script>
    <div id="map-canvas"></div>


<div id="locs"></div>
 
<script>
(function() {

  $.getJSON( mapjsonurl /* + '?username=<?php echo $_SESSION['user_name']; ?>&Coordinate_Lng='+position.coords.latitude+'&Coordinate_Lat='+position.coords.latitude+'&size=500&IncludeEvents=true' */, { // This needs to be edited to call the right POST page with the right arguments
    format: "json"
  })
    .done(function( data ) {
    	dataset = data;
    	data["Locations"].forEach( function(item, index) {
    		console.log(item.content)
    		console.log(data.Locations);


			var marker = new google.maps.Marker({
				position: item, // myLatlng
				map: map,
				title: item.name,
				content: item.content
			});

			// To be altered into a SOAP request later
			var addaddress = 'Desired_Visit?Username=<?php echo $_SESSION["user_name"]; ?>&Location_Id=' + item.Location_Id;

			var infowindow = new google.maps.InfoWindow({
				content: "This is the marker for a location. This location is: " + marker.position + 
						 "<br />" + "My name is: " + item.name + " of Type: " + item.type +
						 "<br />" + item.content +
						 "<br />" + '<a href="'+ addaddress +'">Link</a>'
			});

			$("#locs").append('<br /> <a  obj="'+index+'" href="#">' + item.name + '</a>');

			google.maps.event.addListener(marker, 'click', function() {
				infowindow.open(map,marker);
			});
    	});
	});
})();
</script>
