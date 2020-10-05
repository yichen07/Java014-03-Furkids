/* sidebar -----------------------------------------------------------------------------> */

//sideHamburger

$(".hamburger").click(function(){
  $(".wrapper").toggleClass("active");
})

//fixed position
$(window).scroll(function () {
  if ($(window).scrollTop() > 364) {
    $('.sidebar_initial').addClass('sideBarToTup')
  } else {
    $('.sidebar_initial').removeClass('sideBarToTup')
  }
});

//sideHamburger