

function onGeoOk(position) {
    const lat = position.coords.latitude;
    const lon = position.coords.longitude;
    console.log(`현재 위치는 ${lat}, ${lon}`)
}

function onGeoError() {
    alert("날씨를 제공할 위치를 찾을 수 없습니다.")
}

navigator.geolocation.getCurrentPosition(onGeoOk, onGeoError);