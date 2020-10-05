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


/*-- 日期選取器 Start------------------------------>*/

$(function () {
  $('input[name="datetimes"]').daterangepicker({
    timePicker: true,
    startDate: moment().startOf('hour'),
    endDate: moment().startOf('hour').add(32, 'hour'),
    locale: {
      format: 'M/DD hh:mm A'
    }
  });
});


$("input.dateRange").daterangepicker({
  "alwaysShowCalendars": true,

  opens: "left",
  timePicker: true,
  timePickerIncrement: 30, // 以 30 分鐘為一個選取單位
  locale: {
    format: "YYYY-MM-DD",
    separator: " ~ ",
    applyLabel: "確定",
    cancelLabel: "清除",
    fromLabel: "開始日期",
    toLabel: "結束日期",
    customRangeLabel: "自訂日期區間",
    daysOfWeek: ["日", "一", "二", "三", "四", "五", "六"],
    monthNames: ["1月", "2月", "3月", "4月", "5月", "6月",
      "7月", "8月", "9月", "10月", "11月", "12月"
    ],

    firstDay: 1
  }
});
$("input.dateRange").on("cancel.daterangepicker", function (ev, picker) {
  $(this).val("");
});

/*-- 日期選取器 End ------------------------------->*/

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



/*-- Taiwan Adress Start--------------------------------------------------->*/

$("#twzipcode").twzipcode();

$("#twzipcode_ADV").twzipcode({
    zipcodeIntoDistrict: true, // 郵遞區號自動顯示在地區
    css: ["city form-control", "town form-control"], // 自訂 "城市"、"地區" class 名稱
    countyName: "city", // 自訂城市 select 標籤的 name 值
    districtName: "town" // 自訂地區 select 標籤的 name 值
});

/*-- Taiwan Adress End----------------------------------------------------->*/




