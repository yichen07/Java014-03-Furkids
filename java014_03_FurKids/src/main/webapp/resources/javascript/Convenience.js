$(document).ready(function(){

		//按下修改時
		$('.revise').click(function(){	
			
			display = $('.ccc').css('display');
				
			if(display == 'none'){
				$('.ccc').css('display','block');
				$('.ddd').css('display','none');
				$('.revise').html('取消修改');
			} else if(display == 'block'){	
				$('.ccc').css('display','none');
				$('.ddd').css('display','block');
				$('.revise').html('修改');
			} 
					
			if($('.delete').css('display') == 'block'){
				$('.delete').css('display','none');
			} else if ($('.delete').css('display') == 'none'){
				$('.delete').css('display','block');
			}
					
			//關閉modal前呼叫
			$(".mmm").on("hidden.bs.modal",function(e){
  				$('.ccc').css('display','none');
				$('.ddd').css('display','block');
				$('.delete').css('display','block');
				$('.revise').html('修改');				
			});
			
			
			
		});	
					
});