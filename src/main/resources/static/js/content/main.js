
 document.addEventListener('DOMContentLoaded', function() {
    var calendarEl = document.getElementById('calendar');

    var calendar = new FullCalendar.Calendar(calendarEl, {
    	googleCalendarApiKey : '',
        eventSources :[ 
            {
                googleCalendarId : ''
                , color: '#4e68b9'   // an option!
                , textColor: 'white' // an option!
            } 
        ],
     	});
    	
      

    calendar.render();
  });