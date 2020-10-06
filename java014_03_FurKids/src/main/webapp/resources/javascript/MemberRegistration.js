/*-- Show and Hide Password Start------------------------------->*/

function eyeFunction(){
  var x = document.getElementById("eyeInput");
  if(x.type==="password"){
    x.type = "text";
    document.getElementById('eyeHide').style.display="inline-block";
    document.getElementById('eyeShow').style.display="none";
  }
  else{
    x.type="password";
    document.getElementById('eyeHide').style.display="none";
    document.getElementById('eyeShow').style.display="inline-block";
  }
}
function eyeFunctionCheck(){
  var x = document.getElementById("eyeInputCheck");
  if(x.type==="password"){
    x.type = "text";
    document.getElementById('eyeHideCheck').style.display="inline-block";
    document.getElementById('eyeShowCheck').style.display="none";
  }
  else{
    x.type="password";
    document.getElementById('eyeHideCheck').style.display="none";
    document.getElementById('eyeShowCheck').style.display="inline-block";
  }
}


/*-- Show and Hide Password End--------------------------->*/


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

