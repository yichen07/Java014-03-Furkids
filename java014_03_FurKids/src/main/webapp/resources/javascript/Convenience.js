$(document).ready(function(){

	
		$('.revise').click(function(){		
			display = $('.ccc').css('display');
		
			if(display == 'none'){
				$('.ccc').css('display','block');
				$('.ddd').css('display','none');
			}
					
			if(display == 'block'){	
				$('.ccc').css('display','none');
				$('.ddd').css('display','block');
			} 
			
		});	
					
});