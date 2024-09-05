const calendar = document.querySelector(".calendar"),
  date = document.querySelector(".date"),
  daysContainer = document.querySelector(".days"),
  prev = document.querySelector(".prev"),
  next = document.querySelector(".next"),
  todayBtn = document.querySelector(".today-btn"),
  gotoBtn = document.querySelector(".goto-btn"),
  dateInput = document.querySelector(".date-input"),
  eventDay = document.querySelector(".event-day"),
  eventDate = document.querySelector(".event-date"),
  eventsContainer = document.querySelector(".events");

let today = new Date();
let activeDay;
let month = today.getMonth();
let year = today.getFullYear();
/*
const courses=[
  [],
  [],
  [
      {
          "id": 3,
          "name": "Computer Organization",
          "averageRates": 5.0,
          "professor": "John John",
          "totalHours": 30.0,
          "weekDay": "Tuesday",
          "startTime": "08:00",
          "endTime": "12:00",
          "registeredStudents": 0,
          "userRegistered": true
      }
  ],
  [
      {
          "id": 5,
          "name": "Computer Networks",
          "averageRates": 0.0,
          "professor": "John John",
          "totalHours": 30.0,
          "weekDay": "Wednesday",
          "startTime": "08:00",
          "endTime": "12:00",
          "registeredStudents": 1,
          "userRegistered": true
      },
      {
          "id": 6,
          "name": "Software Engineering",
          "averageRates": 0.0,
          "professor": "John John",
          "totalHours": 30.0,
          "weekDay": "Wednesday",
          "startTime": "14:00",
          "endTime": "17:00",
          "registeredStudents": 1,
          "userRegistered": true
      }
  ],
  [
      {
          "id": 8,
          "name": "Computer Graphics",
          "averageRates": 0.0,
          "professor": "John John",
          "totalHours": 30.0,
          "weekDay": "Thursday",
          "startTime": "14:00",
          "endTime": "17:00",
          "registeredStudents": 2,
          "userRegistered": true
      }
  ],
  [],
  []
];
*/

var eventsArr=[];
const months = [
  "January",
  "February",
  "March",
  "April",
  "May",
  "June",
  "July",
  "August",
  "September",
  "October",
  "November",
  "December",
];

// const eventsArr = [
//   {
//     day: 13,
//     month: 11,
//     year: 2022,
//     events: [
//       {
//         title: "Event 1 lorem ipsun dolar sit genfa tersd dsad ",
//         time: "10:00 AM",
//       },
//       {
//         title: "Event 2",
//         time: "11:00 AM",
//       },
//     ],
//   },
// ];

getCourses()
function getCourses(){
  var user= JSON.parse(localStorage.getItem("logedInUser"));
  $.ajax({
    type:"GET",
    url:"http://localhost:8080/courses/dataForCalendar/"+user.id, 
    success: function(data){
        start(data);
    },
    error: function(err){
      console.log(err);
    }

  });
}

function start(courses){
  var dateX;
  var dayOfWeek;
  for(var m=9; m<=13 && m!=2; m++){
    if(m==13) m=1;
    var y;
    if(m==1) y=2024; else y=2023;

    for(var d=1; d<=31; d++){
      dateX = new Date(y+'-'+ m +"-"+d); 
      if (isNaN(dateX.getTime())) break;

      dayOfWeek = dateX.getDay();
      var todayCourses=[];

      if(courses[dayOfWeek].length!=0){
        for(var i=0;i<courses[dayOfWeek].length;i++){
          
          todayCourses.push({
            title: courses[dayOfWeek][i].name,
            time: courses[dayOfWeek][i].startTime+" - "+courses[dayOfWeek][i].endTime,
         });
        }
        var eventOfTheDay={
          day: d,
          month: m,
          year: y,
          events:todayCourses,
        }
        eventsArr.push(eventOfTheDay);
      }
    }
  }
  initCalendar();
}
function initCalendar() {
  const firstDay = new Date(year, month, 1);
  const lastDay = new Date(year, month + 1, 0);
  const prevLastDay = new Date(year, month, 0);
  const prevDays = prevLastDay.getDate();
  const lastDate = lastDay.getDate();
  const day = firstDay.getDay();
  const nextDays = 7 - lastDay.getDay() - 1;

  date.innerHTML = months[month] + " " + year;

  let days = "";

  for (let x = day; x > 0; x--) {
    days += `<div class="day prev-date">${prevDays - x + 1}</div>`;
  }

  for (let i = 1; i <= lastDate; i++) {
    //check if event is present on that day
    let event = false;
    eventsArr.forEach((eventObj) => {
      if (
        eventObj.day === i &&
        eventObj.month === month + 1 &&
        eventObj.year === year
      ) {
        event = true;
      }
    });
    if (
      i === new Date().getDate() &&
      year === new Date().getFullYear() &&
      month === new Date().getMonth()
    ) {
      activeDay = i;
      getActiveDay(i);
      updateEvents(i);
      if (event) {
        days += `<div class="day today active event">${i}</div>`;
      } else {
        days += `<div class="day today active">${i}</div>`;
      }
    } else {
      if (event) {
        days += `<div class="day event">${i}</div>`;
      } else {
        days += `<div class="day ">${i}</div>`;
      }
    }
  }

  for (let j = 1; j <= nextDays; j++) {
    days += `<div class="day next-date">${j}</div>`;
  }
  daysContainer.innerHTML = days;
  addListner();
}

//function to add month and year on prev and next button
function prevMonth() {
  if(month>8 || month==0){
     month--;
    if (month < 0) {
      month = 11;
      year--;
    }
  }
  initCalendar();
}

function nextMonth() {
  if(month<=11 && month!=0){
    month++;
    if (month > 11) {
      month = 0;
      year++;
    }
  } 
  initCalendar();
}

prev.addEventListener("click", prevMonth);
next.addEventListener("click", nextMonth);



//function to add active on day
function addListner() {
  const days = document.querySelectorAll(".day");
  days.forEach((day) => {
    day.addEventListener("click", (e) => {
      getActiveDay(e.target.innerHTML);
      updateEvents(Number(e.target.innerHTML));
      activeDay = Number(e.target.innerHTML);
      //remove active
      days.forEach((day) => {
        day.classList.remove("active");
      });
      //if clicked prev-date or next-date switch to that month
      if (e.target.classList.contains("prev-date")) {
        prevMonth();
        //add active to clicked day afte month is change
        setTimeout(() => {
          //add active where no prev-date or next-date
          const days = document.querySelectorAll(".day");
          days.forEach((day) => {
            if (
              !day.classList.contains("prev-date") &&
              day.innerHTML === e.target.innerHTML
            ) {
              day.classList.add("active");
            }
          });
        }, 100);
      } else if (e.target.classList.contains("next-date")) {
        nextMonth();
        //add active to clicked day afte month is changed
        setTimeout(() => {
          const days = document.querySelectorAll(".day");
          days.forEach((day) => {
            if (
              !day.classList.contains("next-date") &&
              day.innerHTML === e.target.innerHTML
            ) {
              day.classList.add("active");
            }
          });
        }, 100);
      } else {
        e.target.classList.add("active");
      }
    });
  });
}

todayBtn.addEventListener("click", () => {
  today = new Date();
  month = today.getMonth();
  year = today.getFullYear();
  initCalendar();
});

dateInput.addEventListener("input", (e) => {
  dateInput.value = dateInput.value.replace(/[^0-9/]/g, "");
  if (dateInput.value.length === 2) {
    dateInput.value += "/";
  }
  if (dateInput.value.length > 7) {
    dateInput.value = dateInput.value.slice(0, 7);
  }
  if (e.inputType === "deleteContentBackward") {
    if (dateInput.value.length === 3) {
      dateInput.value = dateInput.value.slice(0, 2);
    }
  }
});

gotoBtn.addEventListener("click", gotoDate);

function gotoDate() {
  const dateArr = dateInput.value.split("/");
  if (dateArr.length === 2) {
    if ((dateArr[0] > 8 && dateArr[0] < 13 && dateArr[1]==2023) || 
    (dateArr[0] == 1   && dateArr[1]==2024))  {

      month = dateArr[0] - 1;
      year = dateArr[1];
      initCalendar();
      return;
    }
  }
  Swal.fire({
    title: "Invalid Date",
    icon: "question"
  });
}

//function get active day day name and date and update eventday eventdate
function getActiveDay(date) {
  const day = new Date(year, month, date);
  const dayName = day.toString().split(" ")[0];
  eventDay.innerHTML = dayName;
  eventDate.innerHTML = date + " " + months[month] + " " + year;
}

//function update events when a day is active
function updateEvents(date) {
  let events = "";
  eventsArr.forEach((event) => {
    if (
      date === event.day &&
      month + 1 === event.month &&
      year === event.year
      // variablit date ktheje ne day=> ne eventsArr ruaj day aty krahaso
    ) {
      event.events.forEach((event) => {
        events += `<div class="event">
            <div class="title">
              <i class="fas fa-circle"></i>
              <h3 class="event-title">${event.title}</h3>
            </div>
            <div class="event-time">
              <span class="event-time">${event.time}</span>
            </div>
        </div>`;
      });
    }
  });
  if (events === "") {
    events = `<div class="no-event">
            <h3>No Courses</h3>
        </div>`;
  }
  eventsContainer.innerHTML = events;
}



