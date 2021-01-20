<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html>
<head>

<meta charset="UTF-8">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	data-integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	data-crossorigin="anonymous">
<style type="text/css">
fieldset {
	border: 1px solid rgb(255, 232, 57);
	width: 400px;
	margin: auto;
}
</style>
<title>Products</title>
<link rel="stylesheet"
	href='../${pageContext.request.contextPath}/css/styles.css'
	type="text/css">
<script src="../js/jquery-3.3.1.min.js"></script>
</head>
<body>
	<section>
		<div class="container">
			<h1 style="text-align: center">新增教練時間</h1>
			<h2 style="text-align: center">教練姓名:${coach.coachName}</h2>
			<h2 style="text-align: center">教練編號:${coach.coachId}</h2>

		</div>
	</section>
	<hr
		style="height: 1px; border: none; color: #333; background-color: #333;">
	<section class="container">
		<!--       三個地方要完全一樣 -->


			<input type='hidden' id='orderStatus' name='_method'> <input
				id="orderStatus" name="orderStatus" type='hidden' value="o"
				class='form:input-large' /> <input type='hidden' id='orderTitle'
				name='_method'> <input id="orderTitle" name="orderTitle"
				type='hidden' value="可預約" class='form:input-large' />

			<div class="form-row">
				<div class="form-group col-md-6">
					<label for="orderDate"> 日期 </label> <input id="orderDate"
						name="orderDate" type="date" class='form:input-large'
						onchange="checkEmptyTime('${coach.coachId}')" />
				</div>
			</div>
			<div class="row">
				<label class="form-control-label">選擇時段</label>
			</div>
			<div class="row mb-3">
				<button type="button" class="btn btn-primary btn-sm" id="allpick_2">全
					選</button>
				<button style="margin-left: 10px" type="button"
					class="btn btn-primary btn-sm" id="allpickn_2">全 不 選</button>
			</div>
			<div class="row form-group" id="pick_time">
				<div class="col-lg-3 custom-control custom-checkbox mb-3">
					<input class="custom-control-input" id="09" name="time"
						type="checkbox" value="09:00:00/10:00:00"> <label
						class="custom-control-label" for="09">09:00 ~ 10:00</label>
				</div>
				<div class="col-lg-3 custom-control custom-checkbox mb-3">
					<input class="custom-control-input" id="10" name="time"
						type="checkbox" value="10:00:00/11:00:00" > <label
						class="custom-control-label" for="10" >10:00 ~ 11:00</label>
				</div>
				<div class="col-lg-3 custom-control custom-checkbox mb-3">
					<input class="custom-control-input" id="11" name="time"
						type="checkbox" value="11:00:00/12:00:00"> <label
						class="custom-control-label" for="11">11:00 ~ 12:00</label>
				</div>
				<div class="col-lg-3 custom-control custom-checkbox mb-3">
					<input class="custom-control-input" id="12" name="time"
						type="checkbox" value="12:00:00/13:00:00"> <label
						class="custom-control-label" for="12">12:00 ~ 13:00</label>
				</div>
				<div class="col-lg-3 custom-control custom-checkbox mb-3">
					<input class="custom-control-input" id="13" name="time"
						type="checkbox" value="13:00:00/14:00:00"> <label
						class="custom-control-label" for="13">13:00 ~ 14:00</label>
				</div>
				<div class="col-lg-3 custom-control custom-checkbox mb-3">
					<input class="custom-control-input" id="14" name="time"
						type="checkbox" value="14:00:00/15:00:00"> <label
						class="custom-control-label" for="14">14:00 ~ 15:00</label>
				</div>
				<div class="col-lg-3 custom-control custom-checkbox mb-3">
					<input class="custom-control-input" id="15" name="time"
						type="checkbox" value="15:00:00/16:00:00"> <label
						class="custom-control-label" for="15">15:00 ~ 16:00</label>
				</div>
				<div class="col-lg-3 custom-control custom-checkbox mb-3">
					<input class="custom-control-input" id="16" name="time"
						type="checkbox" value="16:00:00/17:00:00"> <label
						class="custom-control-label" for="16">16:00 ~ 17:00</label>
				</div>
				<div class="col-lg-3 custom-control custom-checkbox mb-3">
					<input class="custom-control-input" id="17" name="time"
						type="checkbox" value="17:00:00/18:00:00"> <label
						class="custom-control-label" for="17">17:00 ~ 18:00</label>
				</div>
				<div class="col-lg-3 custom-control custom-checkbox mb-3">
					<input class="custom-control-input" id="18" name="time"
						type="checkbox" value="18:00:00/19:00:00"> <label
						class="custom-control-label" for="18">18:00 ~ 19:00</label>
				</div>
				<div class="col-lg-3 custom-control custom-checkbox mb-3">
					<input class="custom-control-input" id="19" name="time"
						type="checkbox" value="19:00:00/20:00:00"> <label
						class="custom-control-label" for="19">19:00 ~ 20:00</label>
				</div>
				<div class="col-lg-3 custom-control custom-checkbox mb-3">
					<input class="custom-control-input" id="20" name="time"
						type="checkbox" value="20:00:00/21:00:00"> <label
						class="custom-control-label" for="20">20:00 ~ 21:00</label>
				</div>
				<div class="col-lg-3 custom-control custom-checkbox mb-3">
					<input class="custom-control-input" id="21" name="time"
						type="checkbox" value="21:00:00/22:00:00"> <label
						class="custom-control-label" for="21">21:00 ~ 22:00</label>
				</div>
				<div class="col-lg-3 custom-control custom-checkbox mb-3">
					<input class="custom-control-input" id="22" name="time"
						type="checkbox" value="22:00:00/23:00:00"> <label
						class="custom-control-label" for="22">22:00 ~ 23:00</label>
				</div>
				<div class="col-lg-3 custom-control custom-checkbox mb-3">
					<input class="custom-control-input" id="23"  name="time"
						type="checkbox" value="23:00:00/24:00:00"> <label
						class="custom-control-label" for="23">23:00 ~ 24:00</label>
				</div>
				<div class="form-row">
					<div class="form-group col-md-6">
						<input id="'coachId'" name="coachId" type='hidden'
							value="${coach.coachId}" class='form:input-large' />
					</div>
				</div>
			</div>
		
	</section>
</body>
<script type="text/javascript">
function checkEmptyTime(coachId){
	var orderDate = $('#orderDate').val();
	$.ajax({
			url:  "<c:url value='/checkEmptyTime'/>",
			type : "POST",
			dataType : "JSON",
			data : {"coachId":coachId,"orderDate":orderDate},
			success : function (data) {
				var json = $.parseJSON(JSON.stringify(data));
				console.log(json);
		        $('#pick_time').children('div').each(function() {
		            $(this).children(':checkbox').each(function() {
		                $(this).prop('disabled', false);
		            });
		        });
		        
		        $('#pick_time').children('div').each(function() {
		            $(this).children(':checkbox').each(function() {
		                $(this).prop('checked', false);
		            });
		        });
		        
				for (let key in json) {
					var orderStartTime = json[key]['orderStartTime'];
					console.log(orderStartTime);
					var orderStartTimeArray = new Array();
					　var orderStartTimerray = orderStartTime.split(" ");
					var orderStartTimerrayID =  orderStartTimerray[1].split(":")
					console.log(orderStartTimerrayID[0]);

					$("#"+orderStartTimerrayID[0]).prop('disabled', true);
				}
				
		}		
	})		

}










document.addEventListener("DOMContentLoaded", function() {


	
	
	
    // 全選、全不選的js
    $('#allpick_2').on('click', function() {
        $('#pick_time').children('div').each(function() {
            $(this).children(':checkbox').not("[disabled]").each(function() {
            	
                $(this).prop('checked', true);
               
            });
        });
    });
    $('#allpickn_2').on('click', function() {
        $('#pick_time').children('div').each(function() {
            $(this).children(':checkbox').each(function() {
                $(this).prop('checked', false);
            });
        });
    });
});
</script>
</html>
