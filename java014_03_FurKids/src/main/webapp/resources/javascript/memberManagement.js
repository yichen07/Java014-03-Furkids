/* sidebar -----------------------------------------------------------------------------> */

//sideHamburger

$(".hamburger").click(function(){
  $(".wrapper").toggleClass("active");
})

//fixed position
$(window).scroll(function () {
  if ($(window).scrollTop() > 364) {
    $('.sidebar-initial').addClass('sideBarToTup')
  } else {
    $('.sidebar-initial').removeClass('sideBarToTup')
  }
});

//sideHamburger