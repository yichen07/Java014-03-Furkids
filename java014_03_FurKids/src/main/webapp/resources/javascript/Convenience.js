$(document).ready(function(){

	
		$('.revise').click(function(){
		
			
			display = $('.ccc').css('display');
			
			alert(display);
			
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