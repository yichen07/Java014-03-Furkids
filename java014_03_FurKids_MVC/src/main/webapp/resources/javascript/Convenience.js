$(document).ready(function(){
		
		//如果已上架服務<8並且還有未上架分店 就顯示新增框	
		if( $('.insertcon').length < 8 && $('.notInsertCon').length != 0){
			$('#insert').css('display','block');
		}
	
});