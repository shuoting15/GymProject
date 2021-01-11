<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<!DOCTYPE html>
<html>

<head>
    <meta charset='utf-8' />
    <link href='css/mainclander.css' rel='stylesheet' />
    <script src='js/mainclander.js'></script>
    <script>
    document.addEventListener('DOMContentLoaded', function() {
        var calendarEl = document.getElementById('calendar');

        var calendar = new FullCalendar.Calendar(calendarEl, {
            // 控制日曆最上方的按鈕
            headerToolbar: {
                left: 'prev,next today',
                center: 'title',
                // 預設： 'dayGridMonth,timeGridWeek,timeGridDay,listWeek'
                right: 'timeGridWeek',
            },
            initialDate: '2021-10-10',
            // 預設一開始渲染的View
            initialView: 'timeGridWeek',
            // 語言中文化
            locale: 'zh-tw',
            // 預設是true，改false後可禁止點擊 9/7(週一) 進入日模式
            navLinks: false, // can click day/week names to navigate views
            nowIndicator: true,

            weekNumbers: true,
            weekNumberCalculation: 'ISO',

            editable: false,
            selectable: false,
            dayMaxEvents: true, // allow "more" link when too many events

            // 可控制日曆上只呈現幾點到幾點
            slotMinTime: '09:00:00',
            slotMaxTime: '24:00:00',

            // 是否顯示 all-day 選項
            allDaySlot: false,

            // 控制每週的第一天是.. 0=Sunday 1=Monday ... 6=Saturday
            firstDay: 0,

            // 點擊某事件後...
            eventClick: function(info) {
				var eventDate = info.event.start;
					var startyear = eventDate.getFullYear();
					var startMonth = eventDate.getMonth()+1;
					var startDate = eventDate.getDate();
					var startHours = eventDate.getHours();
					var startMinutes = eventDate.getMinutes();
					var startSeconds = eventDate.getSeconds();
				alert("The current date of the calendar is " +startyear+startMonth );


                // change the border color just for fun
                info.el.style.borderColor = 'red';
            },

            // 日曆上的事件
            events: [
            
                {
                    title: 'Meeting',
                    start: '2020-09-12T10:30:00',
                    end: '2020-09-12T11:30:00'
                },
				                {
                    title: '123',
                    start: '2020-09-13 10:30:00',
                    end: '2020-09-13 11:30:00'
                },
				                {
                    title: 'Meeting',
                    start: '2020-09-14T10:30:00',
                    end: '2020-09-14T11:30:00'
                },
				                {
                    title: 'Meeting',
                    start: '2020-09-15T10:30:00',
                    end: '2020-09-15T11:30:00'
                },
 
            ]
        });

        calendar.render();
    });
    </script>
    <style>
    body {
        margin: 40px 10px;
        padding: 0;
        font-family: Arial, Helvetica Neue, Helvetica, sans-serif;
        font-size: 14px;
    }

    #calendar {
        max-width: 1100px;
        margin: auto;
    }
    </style>
</head>

<body>
    <div id='calendar'></div>
</body>

</html>