
 document.addEventListener('DOMContentLoaded', function() {
    var calendarEl = document.getElementById('calendar');

    var calendar = new FullCalendar.Calendar(calendarEl, {
    	googleCalendarApiKey : "AIzaSyAWw1w8auy0TJQOTl-YHfi8R9CFnL9WegA",
        eventSources :[ 
            {
                googleCalendarId : '69d9f3562fdd3d1d5f6df7da5a903719b34dad458924f4d15dac7b5792319b01@group.calendar.google.com'
                , color: '#4e68b9'   // an option!
                , textColor: 'white' // an option!
            } 
        ],
     	});
    	
      

    calendar.render();
  });