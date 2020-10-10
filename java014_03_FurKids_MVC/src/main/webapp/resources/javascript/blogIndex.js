/* NavBar -------------------------------------------------------------------------> */

$(document).nvibarTop(function () {
    $('#navbarDropdown').mouseenter(function () {
        $('.dropdown-menu').slideToggle(300, "linear");
    });

    $('.dropdown-menu').mouseleave(function () {
        $(this).slideToggle(300, "linear");
    });
});


// navBarShadow

$(window).scroll(function () {
    if ($(window).scrollTop() > 10) {
        $('#navBar').addClass('floatingNav');
    } else {
        $('#navBar').removeClass('floatingNav');
    }
});

/* NavBar End-------------------------------------------------------------------------> */


/* Banner ----------------------------------------------------------------------------> */

$('#imageStellar').stellar();

/* Banner End ------------------------------------------------------------------------> */

/* Add Column Bar -----------------------------------------------------*/

//fixed position
$(window).scroll(function () {
  if ($(window).scrollTop() > 364) {
    $('.addCol').addClass('addColToTup');
  } else {
    $('.addCol').removeClass('addColToTup');
  }
});

/* Add Column Bar End-----------------------------------------------------*/

