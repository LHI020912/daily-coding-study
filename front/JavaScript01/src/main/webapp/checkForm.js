// 성명: 필수입력
window.onload = function() {
	//submit 이벤트 발생시 유효성 검사
	document.getElementById("joinForm").onsubmit =()=>{
		let name = document.getElementById('name');
		if(name.value==""){
			alert("이름을 입력해주세요.");
			name.focus();
			//submit이벤트는 처리함수를 실행하고 서버요청을 진행
			//서버요청이 진행되지 않도록
			return false;
		};
		//(동일한이름의 name속성이있다면 불가능 그래서 메서드를 사용함)
		let id = document.getElementById('id')
		if(id.value.length<6||id.value.length>10){
			alert("id는 6~10자로 입력하세요.");
			id.value="";//기존 입력값 지우고 글자수만
			id.focus();
			return false;
		};
		//비밀번호 확인
		let pass = document.getElementById('password');
		let passChk = document.getElementById('passwordCheck');
		
		if(pass.value != passChk.value){
			alert("비밀번호가 일치하지 않습니다.");
			passChk.value="";
			passChk.focus();
			return false;
		};
		//직업선택을 하지 않은 경우
		let job = document.getElementById('job');
		if(job.selectedIndex==0){ // job.value=""로 조건검사해도 같음
			alert("직업을 선택하세요.");
			return false;
		};
		//라디오버튼의 그룹참조를 위해 name 속성활용한 참조
		//라디오버튼의 name속성이 같은 태그가 여러개 이므로 배열과 유사한 형태로 반환
		let chk = false;//하나도 체크하지 않았다는 걸로 시작
		for(i=0;i<joinFrom.emailRcv.length;i++){
			if(joinFrom.emailRcv[i].checked==true)
				chk=true;
		};
		//체크여부확인
		if(chk==false){
			alert("이메일 수신여부를 선책하세요.");
			return false;
		};
		//모두 동의해야 회원가입 가능
		let agree1 = document.getElementById('agree1');
		let agree2 = document.getElementById('agree2');
		
		if(agree1.checked==false||agree2.checked==false){
			alert("모두 동의해주셔야 합니다.(불법임)");
			return false;
		};
		
	};//onsubmit끝
		
};//onload끝