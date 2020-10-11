/*-- Top Change "+-" Icon Start------------------------------------------------------>*/

const accordion = document.getElementsByClassName('contentBx');

for (i = 0; i < accordion.length; i++) {
    accordion[i].addEventListener('click', function () {
        this.classList.toggle('active')
    })
}

/*-- Top Change "+-" Icon End-------------------------------------------------------->*/

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
