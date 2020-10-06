/*-- Top Change "+-" Icon Start------------------------------------------------------>*/

const accordion = document.getElementsByClassName('contentBx');
for (i = 0; i < accordion.length; i++) {
    accordion[i].addEventListener('click', function () {
        this.classList.toggle('active')
    })
}

/*-- Top Change "+-" Icon End-------------------------------------------------------->*/

/*-- Taiwan Adress Start--------------------------------------------------->*/

$("#twzipcode").twzipcode();

$("#twzipcode_ADV").twzipcode({
    zipcodeIntoDistrict: true, // 郵遞區號自動顯示在地區
    css: ["city form-control", "town form-control"], // 自訂 "城市"、"地區" class 名稱 
    countyName: "city", // 自訂城市 select 標籤的 name 值
    districtName: "town" // 自訂地區 select 標籤的 name 值
});

/*-- Taiwan Adress End----------------------------------------------------->*/

/*-- Input Effect Start---------------------------------------------------->*/

const inputs = document.querySelectorAll(".input");


function addcl(){
	let parent = this.parentNode.parentNode;
	parent.classList.add("focus");
}

function remcl(){
	let parent = this.parentNode.parentNode;
	if(this.value == ""){
		parent.classList.remove("focus");
	}
}


inputs.forEach(input => {
	input.addEventListener("focus", addcl);
	input.addEventListener("blur", remcl);
});
/*-- Input Effect End---------------------------------------------------->*/