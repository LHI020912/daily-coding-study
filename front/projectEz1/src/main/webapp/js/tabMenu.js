/**
 * tabMenu.js
 */
$(function(){
	//정적으로 서버로부터 받아옴(전역변수)
	let $tabContent = $('#tabContent div');
	
	$('#tabMenu li:first-child').addClass('selectedItem');
	//각 li에 대해서 클릭이벤트 연결
	$('#tabMenu li').on('click',function(){
		let index = $(this).index();//index()현재 참조하는 객체의 idx반환
		//모든 탭메뉴 li에 대해 적용되었던 클래스를 
		$('#tabMenu li').removeClass('selectedItem');
		//클릭한 li에 대해서만 class add
		$(this).addClass('selectedItem');
		//선택된 메뉴에 대해 관련 컨텐츠 표현(이미지표현)
		$tabContent.css('display','none');
		//클릭한 탭 메뉴(idx)에 해당되는 div만 보이게함
		$tabContent.eq(index).css('display','block');
	})
})