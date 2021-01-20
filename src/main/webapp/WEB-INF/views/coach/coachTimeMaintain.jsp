<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>

<head>
<meta charset='utf-8' />
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<link href='css/mainclander.css' rel='stylesheet' />
<link rel="stylesheet" href="css/demo.css">
<script src='js/mainclander.js'></script>
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@10"></script>
<script>
//建立今天日期
var fullDate = new Date();
var yyyy = fullDate.getFullYear();
var MM = (fullDate.getMonth() + 1) >= 10 ? (fullDate.getMonth() + 1) : ("0" + (fullDate.getMonth() + 1));
var dd = fullDate.getDate() < 10 ? ("0"+fullDate.getDate()) : fullDate.getDate();
var today = yyyy + "-" + MM + "-" + dd;

//抓取星期
var Week  = new Date().getDay()

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
    //預設日期
    initialDate: today,
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
    // 顯示範圍
	validRange: {
			start: today,
			   },
            // 控制每週的第一天是.. 0=Sunday 1=Monday ... 6=Saturday
            firstDay: Week,
			//滑鼠移入變色
// 			var bgColor = null;
			eventMouseEnter: function(info) {
				bgColor = info.el.style.borderColor;
				info.el.style.backgroundColor = 'red';
										},
			eventMouseLeave: function(info) {
				 info.el.style.backgroundColor = bgColor;
			},
            // 點擊某事件後...
            eventClick: function(info) {
            	if(info.event.title=="可預約"){
            		Swal.fire({
            			  title: '確定刪除此時段?'+'<br>'+info.event.start.toLocaleString(),
            			  text: "",
            			  icon: 'warning',
            			  showCancelButton: true,
            			  confirmButtonColor: '#3085d6',
            			  cancelButtonColor: '#d33',
            			  confirmButtonText: '確定刪除',
            			  cancelButtonText:'取消刪除'
            			}).then((result) => {
            			  if (result.isConfirmed) {
            	             	$.ajax({
                		url : "<c:url value='/coachTimeDelete'/>",
                		type : "POST",
                		dataType : "JSON",
                		data : {"orderId":info.event.id,"coachId":${coach.coachId}},
                		success : function (data) {
                		}
                		})              		               		       				           				              				  
            				  
            	            		Swal.fire('Delete Success','刪除成功','success');
            	             		calendar.refetchEvents();
            			    
            			  }
            			})
            	
            	}
            	else{Swal.fire('Opps!','此時段已被預約無法刪除','error'); }
            	
   
            },

            // 日曆上的事件
            events: 'http://localhost:8080/GymProject/findall/${coach.coachId}.html'
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
	<h1 align="center">教練時間管理: ${coach.coachName}</h1>
	<div align="center" style="margin-top: 10px">
		<input class="btn btn-primary" style="width: 200px;" type="button"
			value="新增教練時間"
			onclick="window.location.href='<c:url value="/addCoachTime/${coach.coachId}" />';" />
		<!-- Button trigger modal -->
		<button type="button" class="btn btn-primary" data-toggle="modal"
			data-target="#exampleModal">Launch demo modal</button>

		<!-- Modal -->
		<div class="modal fade" id="exampleModal" tabindex="-1"
			aria-labelledby="exampleModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<h5 class="modal-title" id="exampleModalLabel">Modal title</h5>
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
					</div>
					<div class="modal-body">
					
					<jsp:include page="addCoachTime.jsp"></jsp:include>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-secondary"
							data-dismiss="modal">Close</button>
						<button type="button" class="btn btn-primary">Save
							changes</button>
					</div>
				</div>
			</div>
		</div>
	<!-- Modal -->
	</div>
	<h1 align="center" style="margin-top: 50px">點擊刪除可預約時段</h1>
	<div id='calendar'></div>
	<div class="form-group" align="center" style="margin-top: 10px">
		<div class='col-lg-offset-2 col-lg-10' align="center">
			<input id="backCoachsMaintain" name="backCoachsMaintain"
				type='button' class='btn btn-primary' value="回教練管理頁面"
				onclick="window.location.href='http://localhost:8080/GymProject/coachMaintain';" />
		</div>
	</div>
	<!-- JavaScript File Links -->
	<script src="js/jquery-3.3.1.min.js"></script>
	<script src="js/modernizr-3.6.0.min.js"></script>
	<script src="js/plugins.js"></script>
</body>

</html>