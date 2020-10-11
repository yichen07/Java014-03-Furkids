/*-- Carousel Start
$('#myCarousel').carousel({
  interval: 2000,
  cycle: true
});------------------------------->*/
/*-- Carousel End--------------------------------->*/


/*-- 日期選取器 Start------------------------------>*/
$(function () {
  $('input[name="datetimes"]').daterangepicker({
    startDate: moment().startOf('hour'),
    endDate: moment().startOf('hour').add(32, 'hour'),
    locale: {
      format: 'M/DD hh:mm A'
    }
  });
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

