$(document).ready(function(){
	
	//點了之後顯示改選取的表格
	$('.dropdown-item').click(function(){
		n = $('.dropdown-item').length;
		for(let i = 0; i < n; i++){		
			if(($(this).html()) == ( $('.aaa').eq(i).html() )){
				$('#dv').css('display','');
				$('.ppp').eq(i).css('display','');			
			} else {
				$('.ppp').eq(i).css('display','none');
			}
			
		}
	});
	
	//點新增後選擇取消，隱藏所有新增表格
	$('.cancel').click(function(){
		$('#dv').css('display','none');
	});
	
	
		

	
});