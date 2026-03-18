/**
 * index.js
 */
$(function(){
	//브라우저 scroll이벤트
	$(window).on('scroll',function(){
		//스크롤 되는 문서의 스크롤top이
		//#headerBox의 height이상이면 그림자표시
		if($(document).scrollTop() >= $('#headerBox').height())
			$('#mainMenuBox').addClass('mainMenuFixed mainMenuShadow');
		else
			$('#mainMenuBox').removeClass();
	});
	//Top이동
	$('#moveToTop').on('click',function(){
		$('html,body').animate({scrollTop:0}, 500);
	});
});