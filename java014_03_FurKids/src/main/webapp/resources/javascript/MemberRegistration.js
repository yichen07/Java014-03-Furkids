/*-- Show and Hide Password Start------------------------------->*/

// var state = false;
// function eyeFunction() {
//   if (state) {
//     document.getElementById("password").setAttribute("type", "password");
//     document.getElementById("eyeShow").style.color = '#7a797e';
//     state = false;
//   }
//   else {
//     document.getElementById("password").setAttribute("type", "text");
//     document.getElementById("eyeShow").style.color = '#5887ef';
//     state = true;
//   }
// }


// function eyeFunction(){
//   var x = $("#eyeInput");
//   if(x.type==="password"){
//     x.type="text";
//     $("eyeHide").MerchantRegistration.display="inline-block";
//     $("eyeShow").MerchantRegistration.display="none";
//   }
//   else{
//    x.type = "password"; 
//    $("eyeHide").MerchantRegistration.display="none";
//    $("eyeShow").MerchantRegistration.display="inline-block";
//   }
// }

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


/*-- Show and Hide Password Start End--------------------------->*/

/*-- Taiwan Adress Start--------------------------------------------------->*/

$("#twzipcode").twzipcode();

// $("#twzipcode_ADV").twzipcode({
//     zipcodeIntoDistrict: true, // 郵遞區號自動顯示在地區
//     css: ["city form-control", "town form-control"], // 自訂 "城市"、"地區" class 名稱 
//     countyName: "city", // 自訂城市 select 標籤的 name 值
//     districtName: "town" // 自訂地區 select 標籤的 name 值
// });

$("#zipcode").twzipcode({
  "zipcodeIntoDistrict": true,
  "css": ["city form-control", "town form-control"],
  "countyName": "city", // 指定城市 select name
  "districtName": "town" // 指定地區 select name
  });

/*-- Taiwan Adress End----------------------------------------------------->*/

/*-- +-Button Start------------------------------>*/
function isNegative() {
  var $rooms = $("#noOfRoom");
  var c = $rooms.val();

  if (c >= 0) {
    b--;
    $rooms.val(b);
  }
  else {
    $("#subs").prop("disabled", true);
    $rooms.val(0);
    alert('請輸入整數');
  }
}

$('#adds').click(function add() {
  var $rooms = $("#noOfRoom");
  var a = $rooms.val();

  a++;
  $("#subs").prop("disabled", !a);
  $rooms.val(a);

  $("#noOfRoom").trigger(isNegative());
});

$("#subs").prop("disabled", !$("#noOfRoom").val());

$('#subs').click(function subst() {
  var $rooms = $("#noOfRoom");
  var b = $rooms.val();
  if (b >= 2) {
    b--;
    $rooms.val(b);
  }
  else {

    $("#subs").prop("disabled", true);
  }
});
/*-- +-Button End--------------------------------->*/

