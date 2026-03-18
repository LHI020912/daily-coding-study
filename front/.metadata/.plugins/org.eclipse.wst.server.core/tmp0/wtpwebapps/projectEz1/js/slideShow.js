/**
 * slideShow.js
 */

$(function(){
	//현재 보여지는 img index관리
	//변수 값 변경은 prev/next 버튼 클릭시 변경됨
	let moveIndex = 0;
	//슬라이드 패널을 움직이는 함수(왼쪽여백 조정)
	//슬라이드패너의 기준위치는 왼쪽여백 0인 위치-왼쪽여백을0~- 여백까지 조정
	function moveSlide(index){
		//함수호출시점: prev/next버튼 클릭시, 보여질 img index값이 인수로 전달
		//현재 보고있는 이미지index 갱신
		moveIndex=index;//명시적으로 저장받았다는 뜻(잇없상관x)
		//슬라이드 이동(왼쪽여백 계산)
		let moveLeft = -(index*1280);
		$('#slidePanel').animate({'left':moveLeft},'slow');	
		}
	
	$('#prevButton').on('click',function(){
		if(moveIndex!=0)//첫번째 이미지가 아니라면
			moveIndex-=1;
		moveSlide(moveIndex);
	});//click끝
	
	$('#nextButton').on('click',function(){
		if(moveIndex !=4)//마지막 이미지가 아니라면
			moveIndex+=1;//다음!
		moveSlide(moveIndex);
	});//click끝
	/*초기 슬라이더 위치 랜덤하게 지정하는 코드(랜덤메소드)*/
	let moveRandom = Math.floor(Math.random()*5);
	moveSlide(moveRandom);
	
	//각 컨트롤 버튼에 대해
	$('.controlButton').each(function(idx){
		$(this).hover(
			function(){$(this).attr('src','image/controlButton2.png')},
			function(){$(this).attr('src','image/controlButton1.png')}
			);
		//클릭했을 때 인덱스 값을 moveSlide()함수에게 전달
		$(this).on('click',function(){
			moveSlide(idx);
		})
	});
});