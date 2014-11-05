<script>
var mapjsonurl = "/assets/temp/map.json";
var dataset = "";
// Note: This example requires that you consent to location sharing when
// prompted by your browser. If you see a blank space instead of the map, this
// is probably because you have denied permission for location sharing.

var testjson = {"Locations": [{"lat":41.877849999999995,"lng":-87.64101670000002,"content":"Event: Words" }]};
var myLatlng = new google.maps.LatLng(testjson["Locations"][0]["lat"],testjson["Locations"][0]["lng"]);

var map;

function initialize() {
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
  $.getJSON( mapjsonurl, {
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
				title: 'Hello World!',
				content: item.content
			});

			var infowindow = new google.maps.InfoWindow({
				content: "This is the marker for a location. This location is: " + marker.position + 
						 "<br />" + "My name is: " + item.name + " of Type: " + item.type +
						 "<br />" + item.content
			});

			$("#locs").append('<br /> <a  obj="'+index+'" href="#">' + item.name + '</a>');

			google.maps.event.addListener(marker, 'click', function() {
				infowindow.open(map,marker);
			});
    	});
	});
})();
</script>
