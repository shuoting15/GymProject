<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
            <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

                <html>

                <head>
                    <meta charset="UTF-8">
                    <meta name="viewport" content="width=device-width, initial-scale=1.0">
                    <meta http-equiv="X-UA-Compatible" content="ie=edge">

                    <link href="https://fonts.googleapis.com/css?family=Rajdhani:300,400,500,600,700&display=swap"
                        rel="stylesheet">
                    <link rel="stylesheet" href="http://cdn.bootstrapmb.com/bootstrap/4.3.1/css/bootstrap.min.css">
                    <link rel="stylesheet" href="css/all.min.css">
                    <link rel="stylesheet" href="css/animate.css">
                    <link rel="stylesheet" href="css/lightcase.css">
                    <link rel="stylesheet" href="css/flaticon.css">
                    <link rel="stylesheet" href="css/swiper.min.css">

                    <link rel="shortcut icon" href="images/trainers/favicon.png" type="image/x-icon">

                    <link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css">
                    <script src="https://code.jquery.com/jquery-3.5.1.js"></script>
                    <link rel="stylesheet"
                        href="https://cdnjs.cloudflare.com/ajax/libs/slick-carousel/1.9.0/slick.css" />
                    <link rel="stylesheet"
                        href="https://cdnjs.cloudflare.com/ajax/libs/slick-carousel/1.9.0/slick-theme.css" />
                    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@10"></script>
                    <link rel="stylesheet" href="css/stylecoach.css">
                    <title>Courses</title>
                    <%-- <link rel="stylesheet" href='${pageContext.request.contextPath}/css/styles.css'
                        type="text/css"> --%>
                        <style>
                            .slick-center .slide-h3 {
                                color: #FFF;
                            }

                            .slider {
                                width: 900px;
                                height: 40px;
                                margin: 20px auto;
                                text-align: center;
                            }

                            .slide-h3 {
                                margin: 10% 0 10% 0;
                                padding: 40% 20%;
                                background: #008ed6;
                            }

                            .slider div {
                                margin-right: 5px;
                            }

                            .slick-slide {
                                opacity: .6;
                            }

                            .slick-center {
                                display: block;
                                max-width: 10% !important;
                                max-height: 20% !important;
                                opacity: 1;
                            }

                            .slick-prev:before {
                                color: black;
                            }

                            .slick-next:before {
                                color: black;
                            }

                            a {
                                font-size: 30px;
                            }
                        </style>

                        <script type="text/javascript">





                            $(document).ready(function () {

                                    var selectText = "請選擇";
                                    var selectVal = "--select--";
                                    $("#cdate").append("<option value=" + selectVal + ">" + selectText + "</option>");


                                    $("#re").click(function () {
                                        var keyword = $("#keyword").val();
                                        // 	console.log(keyword);
                                        var cate = $("#cate").find("option:selected").text();
                                        var cdate = $("#cdate").find("option:selected").text();

                                        $.ajax({
                                            url: "CourseSearchByKC",
                                            type: "POST",
                                            async: false,
                                            data: {
                                                Keyword: keyword,
                                                Cate: cate,
                                                Cdate: cdate,
                                            },
                                            success: function (list_search) {
                                                $(".style-feature").html("");
                                                for (var i = 0; i < list_search.length; i++) {
                                                    var courses = list_search[i];

                                                    var tbd = `
                	<div class="feature-item">
                    <div class="feature-thumb">
                        <a href="<c:url value='/course?id=\${courses.courseId}'/>"><img src="<c:url value='/getCPicture/\${courses.courseId}'/>" alt="feature"></a>
                    </div>
                    <div class="feature-content">
                        <div class="feature-header">
                            <h4 class="title"><a href="<c:url value='/course?id=\${courses.courseId}'/>"  onmouseover="this.style.color='orange'" onmouseout="this.style.color='black'" target="_blank">\${courses.title}</a></h4>
                            <p> \${courses.date} \${courses.starttime}~\${courses.endtime}</p>
                            <p>教室: \${courses.location}</p>
                            <p>人數: \${courses.selected} / \${courses.max}</p>
                            <p>價格: \${courses.price} 點</p>
                        </div>
                        <div class="feature-footer d-flex flex-wrap align-items-center justify-content-between">
                            <div class="d-flex flex-wrap">
                            	<div class="feature-author-content">
                            		<h6 class="sub-title">\${courses.courseCoachBean.name}</h6>
                        		</div>
                            </div>
                            
                            <button name="a\${courses.max - courses.selected}" class="btn btn-dark" style="width:100px" onclick="bookfunction(\${courses.price}, \${courses.courseId})">立即預約</button>                             
                        </div>
                    </div>
                </div>`;

                                                    $(".style-feature").append(tbd);
                                                    $("button[name='a0']").each(function () {
                                                        $(this).attr('disabled', 'true').attr("class", "btn btn-secondary").text("已額滿");
                                                    })
                                                    //                 $("#a0").attr('disabled', 'true').attr("class","btn btn-secondary").text("已額滿");
                                                }
                                            }
                                        });
                                    })
                                    $.ajax({
                                        url: "CourseSearchShowDate",
                                        type: "POST",
                                        async: false,
                                        success: function (list) {
                                            for (var i = 0; i < list.length; i++) {
                                                var date = list[i];
                                                //                  console.log(date);
                                                var optionDate = "<option value=" + date + ">" + date + "</option>";
                                                $("#cdate").append(optionDate);
                                            }
                                            $("#re").trigger("click");

                                        }
                                    })

                            })


                        </script>
                        <style type="text/css">
                            #kw {
                                align: center;
                                width: 50px;
                                line-height: 30px;
                                padding: 10px;
                                margin-right: 150px;
                                float: left;
                            }

                            #cli {
                                align: center;
                                width: 150px;
                                line-height: 20px;
                                padding: 9px;
                                float: left;
                            }
                        </style>
                </head>

                <body>
                    <!-- Preloader -->
                    <!--     <div class="preloader"> -->
                    <!--         <div class="preloader-wrapper"> -->
                    <!--             <img src="css/ajax-loader.gif" alt="preloader"> -->
                    <!--         </div> -->
                    <!--     </div> -->
                    <!-- Preloader -->
                    <!-- 引入共同的頁首 -->
                    <jsp:include page="/fragment/top.jsp" />
                    <section class="page-header bg_img" data-background="images/banner.jpg">
                        <div class="container">
                            <h3 class="title">
                                <span class="shape-wrapper"><span class="shape"></span>Courses<span
                                        class="shape"></span></span>
                            </h3>
                        </div>
                    </section>
                    <section>
                        <div>
                            <div class="container" style="text-align: center">
                                <!--                 <h1>課程列表</h1> -->
                                <h3 style="color: red;">${messages}</h3>
                            </div>
                        </div>
                    </section>
                    <div align="center" style="margin-left:300px;margin-bottom:10px; width:1000px; height:60px;">
                        <div id="kw">
                            <input id="keyword" type="text" placeholder="關鍵字"
                                style="border: 1px gray solid;height:42px;width:180px">
                        </div>
                        <div id="kw">

                            <select id="cate" style="width:180px;height:42px;border: 1px gray solid;appearance:none;
  -moz-appearance:none;
  -webkit-appearance:none;">
                                <option style="color: gray" value="--">不限種類</option>
                                <option style="color: gray" value="瑜珈 & 皮拉提斯">瑜珈 & 皮拉提斯</option>
                                <option style="color: gray" value="拳擊">拳擊</option>
                                <option style="color: gray" value="舞蹈">舞蹈</option>
                                <option style="color: gray" value="飛輪">飛輪</option>
                                <option style="color: gray" value="肌力訓練">肌力訓練</option>
                            </select>
                        </div>
                        <div id="kw">
                            <select id="cdate" style="width:180px;height:42px;border: 1px gray solid;appearance:none;
  -moz-appearance:none;
  -webkit-appearance:none;">
                            </select>
                        </div>

                        <div id="cli"><a style="font-size: 20px;color: white;" class="btn btn-primary btn-lg" id="re">
                                顯示結果</a>
                        </div>
                        <div id="cli"><a style="font-size: 20px;" href="<c:url value='/courses'/>"
                                class="btn btn-secondary btn-lg">
                                重置條件</a>
                        </div>
                    </div>

                    <script src="https://cdnjs.cloudflare.com/ajax/libs/slick-carousel/1.9.0/slick.js"></script>

                    <section id="slick-content">
                        <div class="slider" data-slick='{"slidesToShow": 7, "slidesToScroll": 1}'>
                        </div>
                    </section>

                    <hr style="height:1px;border:none;color:#333;background-color:#333;">
                    <script>
                        function classChangeDate(d) {

                            var cdate = d;
                            //             console.log(cdate);
                            //             $("#re").click(function () {
                            var keyword = $("#keyword").val();
                            //             	console.log(keyword);
                            var cate = $("#cate").find("option:selected").text();
                            //             	var cdate = $("#cdate").find("option:selected").text();

                            $.ajax({
                                url: "CourseSearchByKC",
                                type: "POST",
                                async: false,
                                data: {
                                    Keyword: keyword,
                                    Cate: cate,
                                    Cdate: cdate,
                                },
                                success: function (list_search) {
                                    $(".style-feature").html("");
                                    for (var i = 0; i < list_search.length; i++) {
                                        var courses = list_search[i];

                                        var tbd = `
                            	<div class="feature-item">
                                <div class="feature-thumb">
                                    <a href="<c:url value='/course?id=\${courses.courseId}'/>"><img src="<c:url value='/getCPicture/\${courses.courseId}'/>" alt="feature"></a>
                                </div>
                                <div class="feature-content">
                                    <div class="feature-header">
                                        <h4 class="title"><a href="<c:url value='/course?id=\${courses.courseId}'/>" onmouseover="this.style.color='orange'" onmouseout="this.style.color='black'" target="_blank">\${courses.title}</a></h4>
                                        <p> \${courses.date} \${courses.starttime}~\${courses.endtime}</p>
                                        <p>教室: \${courses.location}</p>
                                        <p>人數: \${courses.selected} / \${courses.max}</p>
                                        <p>價格: \${courses.price} 點</p>
                                    </div>
                                    <div class="feature-footer d-flex flex-wrap align-items-center justify-content-between">
                                        <div class="d-flex flex-wrap">
                                            <div class="feature-author-content">
                                                <h6 class="sub-title">\${courses.courseCoachBean.name}</h6>
                                            </div>
                                        </div>
                                        <button name="a\${courses.max - courses.selected}" class="btn btn-dark" style="width:100px" onclick="bookfunction(\${courses.price}, \${courses.courseId})">立即預約</button>  
                                    </div>
                                </div>
                            </div>`;

                                        $(".style-feature").append(tbd);
                                        $("button[name='a0']").each(function () {
                                            $(this).attr('disabled', 'true').attr("class", "btn btn-secondary").text("已額滿");
                                        })
                                        //                             $("#a0").attr('disabled', 'true').attr("class","btn btn-secondary").text("已額滿");
                                    }
                                }
                            });
                            //             	})

                        }
                        function getDays(day1, day2) {
                            // 獲取入參字串形式日期的Date型日期
                            var st = day1.getDate();
                            var et = day2.getDate();

                            // 定義返回的陣列
                            var retArr = [];

                            // 迴圈，啟動日期不等於結束日期時，進行迴圈
                            while (st.getTime() != et.getTime()) {

                                // 將啟動日期的字串形式的日期存放進陣列
                                retArr.push(st.getYMD());

                                // 取得開始日期的天
                                var tempDate = st.getDate();

                                // 將開始日期st指向構造出的新的日期
                                // 新的日期較之前的日期多加一天
                                st = new Date(st.getFullYear(), st.getMonth(), st.getDate() + 1);
                            }
                            // 將結束日期的天放進陣列
                            retArr.push(et.getYMD());

                            // console.log(retArr); // 或可換為return ret;
                            for (i in retArr) {
                                // console.log(retArr[i]);
                                $(".slider").append("<li id='da'><a onClick=\"classChangeDate('" + retArr[i] + "\')\" id='d" + i + "'  onmouseover=\"this.style.color='orange';\" onmouseout=\"this.style.color='007bff';\"   >" + retArr[i].substring(5, 10) + "</a></li>");
                            }
                        }

                        // 給Date對象添加getYMD方法，獲取字符串形式的年月日
                        Date.prototype.getYMD = function () {

                            // 將結果放在數組中，使用數組的join方法返回連接起來的字符串，並給不足兩位的天和月十位上補零
                            return [this.getFullYear(), fz(this.getMonth() + 1), fz(this.getDate())].join("-");
                        }

                        // 給String物件新增getDate方法，使字串形式的日期返回為Date型的日期
                        String.prototype.getDate = function () {
                            var strArr = this.split('-');
                            var date = new Date(strArr[0], strArr[1] - 1, strArr[2]);
                            return date;
                        }

                        function fz(num) {
                            if (num < 10) {
                                num = "0" + num;
                            }
                            return num
                        }

                        var date1 = new Date();
                        var date2 = new Date(date1);
                        date2.setDate(date1.getDate() + 20);
                        var a = date1.getFullYear() + "-" + (date1.getMonth() + 1) + "-" + date1.getDate();
                        var b = date2.getFullYear() + "-" + (date2.getMonth() + 1) + "-" + date2.getDate();

                        getDays(a, b);
                    </script>
                    <script>
                        $('.slider').slick({
                            // centerMode:true,
                            centerPadding: '100px',
                            slidesToShow: 1,
                            slidesToScroll: 1,
                            // autoplaySpeed: 2000,  
                            // dots: true,
                            arrows: true,
                            infinite: true,
                            accessibility: true,
                            // lazyLoad: "progressive",
                        });
                    </script>
                    <section class="container">
                        <div class="row">
                        </div>
                    </section>
                    <!-- Feature Section Starts Here -->
                    <section class="feature-section padding-bottom padding-top">
                        <div class="container">
                            <div class="style-feature">

                            </div>

                        </div>
                    </section>
                    <!-- Feature Section Ends Here -->
                    <script>
                        function bookfunction(price, id) {
                            Swal.fire({
                                type: 'warning', // 彈框類型
                                title: '確定要預約本課程嗎?<br>價格為:' + price + '點', //標題
                                text: "", //顯示內容            

                                confirmButtonColor: '#3085d6',// 確定按鈕的 顏色
                                confirmButtonText: '確定',// 確定按鈕的 文字
                                icon: 'question',
                                showCancelButton: true, // 是否顯示取消按鈕
                                cancelButtonColor: '#d33', // 取消按鈕的 顏色
                                cancelButtonText: "取消", // 取消按鈕的 文字
                                focusCancel: true, // 是否聚焦 取消按鈕

                            }).then((isConfirm) => {

                                try {
                                    //判斷 是否 點擊的 確定按鈕
                                    if (isConfirm.value) {
                                        $.ajax({
                                            url: "<c:url value='/booking/course'/>",

                                            dataType: "JSON",
                                            data: { "id": id },
                                            success: function (data) {
                                                if (data.messages == "餘額不足") {
                                                    Swal.fire("餘額不足", "請購買點數", "error");
                                                } else if (data.messages == "您已預約本課程，請勿重複預約!") {
                                                    Swal.fire("已預約過本課程", "請勿重複預約", "error");
                                                } else {
                                                    Swal.fire("預約成功", "如要取消,請至預約查詢頁面", "success");
                                                }
                                                setTimeout(function(){
                        	            			window.location.reload();//刷新
                        		            		},1500)
                                                
                                            }
                                        })
                                    }
                                    else {
                                        Swal.fire("取消操作", "", "error");

                                    }
                                } catch (e) {
                                    alert(e);
                                }

                            });
                            //      	if(confirm("確定預約本課程嗎? 確認無誤後請按確定!")){  
                            //      		return true;  
                            //      	}  
                            //      	return false;  
                        }
                    </script>
                    <!--     頁尾 -->
                    <jsp:include page="/fragment/footer.jsp" />

                    <!-- JavaScript File Links -->
                    <script src="js/jquery-3.3.1.min.js"></script>
                    <script src="js/modernizr-3.6.0.min.js"></script>
                    <script src="js/plugins.js"></script>
                    <script src="http://cdn.bootstrapmb.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
                    <script src="js/isotope.pkgd.min.js"></script>
                    <script src="js/swiper.min.js"></script>
                    <script src="js/waypoint.js"></script>
                    <script src="js/counterup.min.js"></script>
                    <script src="js/lightcase.js"></script>
                    <script src="js/wow.min.js"></script>
                    <script src="js/maincoach.js"></script>

                </body>

                </html>