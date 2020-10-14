/*-- Top Change "+-" Icon Start------------------------------------------------------>*/

const accordion = document.getElementsByClassName('contentBx');
for (i = 0; i < accordion.length; i++) {
    accordion[i].addEventListener('click', function () {
        this.classList.toggle('active')
    })
}

/*-- Top Change "+-" Icon End-------------------------------------------------------->*/

