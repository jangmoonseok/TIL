<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
  <title>Bootstrap Example</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
  <script src="https://cdn.jsdelivr.net/npm/jquery@3.6.0/dist/jquery.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js"></script>
<script type="text/javascript">
$(function(){
	// 아이디 중복체크
	$('#idcheck').click(function(){
		var id = $('#id').val().trim();
	
		$.ajax({
			url : '<%= request.getContextPath()%>/IdCheck.do',
			data : {"id" : id},
			type : 'get',
			success : function(res){
				$('#idFlag').html(res.flag).css('color','red');
			},
			error : function(xhr){
				alert("상태 : " + xhr.status)
			},
			dataType : 'json'
		})
	})
		
	// 우편번호 검색 - window.open
	$('#zipsearch').click(function(){
		window.open('zipsearch.html', '우편번호', 'width=600 height=400');
	})
	
	// 우편번호 검색 - modal
	$('#btn1').click(function(){
		var input = $('#dong').val().trim();
		$.ajax({
			url : '/jqueryWeb/ZipSearch.do',
			type : 'get',
			data : {"dong" : input},
			success : function(res){
				str = "<table class='table table-dark table-hover'>";
				str += "<tr><td>우편번호</td>";
				str += "<td>주소</td>";
				str += "<td>번지</td></tr>";
				
				$.each(res, function(i, v){
					var bunji = v.bunji;
					
					if(typeof bunji == "undefined"){
						bunji = "";
					}
					
					str += "<tr class='ziptr'><td>" + v.zipcode + "</td>";
					str += "<td>" + v.sido + v.gugun + v.dong + "</td>";
					str += "<td>" + bunji + "</td></tr>";
				})
				
				str += "</table>"; 
				
				$('#result1').html(str);
			},
			error : function(xhr){
				alert("상태 : " + xhr.status);
			},
			dataType : 'json'
		})
	})
	
	$('#result1').on('click', '.ziptr', function(){
		zipcode = $('td:eq(0)', this).text();
		addr = $('td:eq(1)', this).text();
		
		$('#zip').val(zipcode);
		$('#add1').val(addr);
	
		$('#myModal').modal('hide');
		$('#dong').val("");
		$('#result1').empty();
	})
})
</script>
<style type="text/css">
#dong{
	margin-left: 20px;
}
#zipform{
	margin: 20px;
}
</style>
</head>
<body>

<div class="container">
	<h2>회원가입</h2>
  	<form class="needs-validation" novalidate>
	    <div class="form-group">
	      <label for="uname"  class="mr-sm-2">이름</label>
	      <input type="text" class="form-control col-sm-4"  id="uname" placeholder="Enter username" name="mem_name" required>
	      <div class="valid-feedback">Valid.</div>
	      <div class="invalid-feedback">Please fill out this field.</div>
	    </div>
	    <div class="form-group">
	      <label for="id"  class="mr-sm-2">아이디</label>
	      <button id="idcheck" type="button" class="btn btn-outline-primary btn-sm mb-2">중복검사</button>
	      <br>
	      <input type="text" class="form-control col-sm-4"  id="id" placeholder="Enter userid" name="mem_id" required>
	      <span id="idFlag"></span>
	      <div class="valid-feedback">Valid.</div>
	      <div class="invalid-feedback">Please fill out this field.</div>
	    </div>
	    <div class="form-group">
	      <label for="pwd"  class="mr-sm-2">비밀번호</label>
	      <input type="password" class="form-control col-sm-4"  id="pwd" placeholder="Enter password" name="mem_pass" required>
	      <div class="valid-feedback">Valid.</div>
	      <div class="invalid-feedback">Please fill out this field.</div>
	    </div>
	    <div class="form-group">
	      <label for="birth"  class="mr-sm-2">생년월일</label>
	      <input type="date" class="form-control col-sm-4"  id="birth" name="mem_bir" required>
	      <div class="valid-feedback">Valid.</div>
	      <div class="invalid-feedback">Please fill out this field.</div>
	    </div>
	    <div class="form-group">
	      <label for="tel"  class="mr-sm-2">전화번호</label>
	      <input type="text" class="form-control col-sm-4"  id="tel" placeholder="010-1234-5678" name="mem_hp" required>
	      <div class="valid-feedback">Valid.</div>
	      <div class="invalid-feedback">Please fill out this field.</div>
	    </div>
	    <div class="form-group">
	      <label for="email"  class="mr-sm-2">이메일</label>
	      <input type="text" class="form-control col-sm-4"  id="email" placeholder="abcd@email.com" name="mem_mail" required>
	      <div class="valid-feedback">Valid.</div>
	      <div class="invalid-feedback">Please fill out this field.</div>
	    </div>
	    <div class="form-group">
	      <label for="zip"  class="mr-sm-2">우편번호</label>
	      <button id="zipsearch" type="button" class="btn btn-outline-primary btn-sm mb-2">번호검색</button>
	      <button type="button" class="btn btn-outline-primary btn-sm mb-2" data-toggle="modal" data-target="#myModal">번호검색modal</button>
	      <input type="text" class="form-control col-sm-4"  id="zip" name="mem_zip" required>
	      <div class="valid-feedback">Valid.</div>
	      <div class="invalid-feedback">Please fill out this field.</div>
	    </div>
	    <div class="form-group">
	      <label for="add1"  class="mr-sm-2">주소</label>
	      <input type="text" class="form-control col-sm-5"  id="add1" name="mem_add1" required>
	      <div class="valid-feedback">Valid.</div>
	      <div class="invalid-feedback">Please fill out this field.</div>
	    </div>
	    <div class="form-group">
	      <label for="add2"  class="mr-sm-2">상세주소</label>
	      <input type="text" class="form-control col-sm-5"  id="add2" name="mem_add2" required>
	      <div class="valid-feedback">Valid.</div>
	      <div class="invalid-feedback">Please fill out this field.</div>
	    </div>
	    <div class="form-group form-check">
	      <label class="form-check-label">
	        <input class="form-check-input" type="checkbox" name="remember" required> I agree on blabla.
	        <div class="valid-feedback">Valid.</div>
	        <div class="invalid-feedback">Check this checkbox to continue.</div>
	      </label>
	    </div>
	    <button type="submit" class="btn btn-primary btn-lg">Submit</button>
 	</form>
</div>
<div class="modal" id="myModal">
  <div class="modal-dialog">
    <div class="modal-content">

      <!-- Modal Header -->
      <div class="modal-header">
        <h4 class="modal-title">우편번호 찾기</h4>
        <button type="button" class="close" data-dismiss="modal">&times;</button>
      </div>

      <!-- Modal body -->
      <div class="modal-body">
        <div id="zipform">
	      <label for="dong">우편번호 검색</label>
	      <input type="text" id="dong">
			<input type="button" id="btn1" value="확인">
	    </div>
		<div id="result1"></div>
      </div>

      <!-- Modal footer -->
      <div class="modal-footer">
        <button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
      </div>

    </div>
  </div>
</div>
<script>
// Disable form submissions if there are invalid fields
(function() {
  'use strict';
  window.addEventListener('load', function() {
    // Get the forms we want to add validation styles to
    var forms = document.getElementsByClassName('needs-validation');
    // Loop over them and prevent submission
    var validation = Array.prototype.filter.call(forms, function(form) {
      form.addEventListener('submit', function(event) {
        if (form.checkValidity() === false) {
          event.preventDefault();
          event.stopPropagation();
        }
        form.classList.add('was-validated');
      }, false);
    });
  }, false);
})();
</script>

</body>
</html>
