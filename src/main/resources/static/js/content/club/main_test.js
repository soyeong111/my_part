
getWeather();

function getWeather(){
	document.addEventListener("DOMContentLoaded", getWeather);

		function getWeather() {
			fetch('https://goweather.herokuapp.com/weather/Ulsan')
			.then((response) => response.json())
			.then((data) => {
				document.getElementById("temperature").innerHTML = data['temperature'];
				document.getElementById("description").innerHTML = data['description'];
				
				if (data['description'] === 'Heavy rain, mist') {
					document.querySelector('.weather-img').style.backgroundImage = 'url("/image/main/sunny.png")';
				}
				else if (data['description'] === 'Cloudy') {
					document.querySelector('.weather-img').style.backgroundImage = 'url("/image/main/cloudy.png")';
				}
				else if (data['description'] === 'Rainy') {
					document.querySelector('.weather-img').style.backgroundImage = 'url("/image/main/rainy.png")';
				}
			});
		}
}