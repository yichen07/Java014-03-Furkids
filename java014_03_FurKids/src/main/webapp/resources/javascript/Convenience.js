$(document).ready(function(){
	
	// $('.food').click(function(){
	// 	document.getElementById('dv').style.display='';
	// });
	
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
	
	$('.cancel').click(function(){
		$('#dv').css('display','none');
	});
});