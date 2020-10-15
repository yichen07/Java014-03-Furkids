/*-- 文字浮動 Start --------------------------->*/
const inputs = document.querySelectorAll(".inputClass");

function focusFunc() {
  let parent = this.parentNode;
  parent.classList.add("focus");
}

function blurFunc() {
  let parent = this.parentNode;
  if (this.value == "") {
    parent.classList.remove("focus");
  }
}

inputs.forEach((input) => {
  input.addEventListener("focus", focusFunc);
  input.addEventListener("blur", blurFunc);
});

/*-- 文字浮動 End------------------------------>*/

/*-- 貓熱量計算 Start------------------------------>*/

let catWeight = document.getElementById("inputCatWeight");
catWeight.addEventListener('change', totalKCal);

let catValue = document.getElementById("selectCat");
catValue.addEventListener('change', totalKCal)

let catDayCount=document.getElementById("catEatCount");
catDayCount.addEventListener('change',totalKCal)
let unit = document.getElementById("catCalUnite");

function totalKCal() {
  let rer = Math.round(Math.pow(parseFloat(catWeight.value), 0.75)) * 70 || 0;
  let eatCount = parseFloat(catDayCount.value) || 1;

  let catValueSelect = parseFloat(catValue.options[catValue.selectedIndex].value) || 0;

  let mer = rer * catValueSelect;
  totalCatCal.innerText = Math.round(mer / eatCount*10)/10;
  unit.innerText = "(kcal/餐)";
}
/*-- 貓熱量計算 End------------------------------>*/
/*-- 狗熱量計算 Start------------------------------>*/

let dogWeight = document.getElementById('inputDogWeight');
dogWeight.addEventListener('change', totalDogKCal);

let dogValue = document.getElementById("selectDog");
dogValue.addEventListener('change', totalDogKCal);

let dogDayCount = document.getElementById('DogEatCount');
dogDayCount.addEventListener('change',totalDogKCal);

let dogUnit = document.getElementById("dogCalUnite");


function totalDogKCal() {
  let rer = Math.round(Math.pow(parseFloat(dogWeight.value), 0.75)) * 70 || 0;
  
  let eatCount = parseFloat(dogDayCount.value) || 1;

  let dogValueSelect = parseFloat(dogValue.options[dogValue.selectedIndex].value) || 0;

  let mer = rer * dogValueSelect;
  totalDogCal.innerText = Math.round(mer / eatCount*10)/10;
  dogUnit.innerText = "(kcal/餐)";
}
/*-- 狗熱量計算 End------------------------------>*/
