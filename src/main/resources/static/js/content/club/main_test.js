
getWeather();

function getWeather(){
	document.addEventListener("DOMContentLoaded", getWeather);

		function getWeather() {
			fetch('https://goweather.herokuapp.com/weather/Ulsan')
			.then((response) => response.json())
			.then((data) => {
				document.getElementById("temperature").innerHTML = data['temperature'];
				document.getElementById("description").innerHTML = data['description'];
				
				if (data['description'] === "sunny") {
                document.querySelector('.weather-img').style.backgroundImage = 'url("/image/main/clear.png")';
            	}
            	else if (data['description'] === "Partly cloudy") {
                document.querySelector('.weather-img').style.backgroundImage = 'url("/image/main/clear.png")';
				}
			});
		}
}