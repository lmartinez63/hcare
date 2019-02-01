var threePoint = true;
var menuMain = true;
$(document).ready(function() {
  $('#table_id').DataTable({
    responsive: true
  });

  $('.menu li:has(ul)').click(function(e) {
    e.preventDefault();
    if ($(this).hasClass('activado')) {
      $(this).removeClass('activado');
      $(this).children('ul').slideUp();
    } else {
      $('.menu li ul').slideUp();
      $('.menu li').removeClass('activado');
      $(this).addClass('activado');
      $(this).children('ul').slideDown();
    }
  });

  $('.btn-menu').click(function() {
    $('.contenedor-menu .menu').slideToggle();
  });

  /*$(window).resize(function(){
      if ($(document).width() > 450){
          $('.contenedor-menu .menu').css({'display' : 'block'});
      }

      if ($(document).width() < 450){
          $('.contenedor-menu .menu').css({'display' : 'none'});
          $('.menu li ul').slideUp();
          $('.menu li').removeClass('activado');
      }
  });*/

  $('.menu li ul li a').click(function() {
    window.location.href = $(this).attr("href");
  });
});

function frontEndDateFormat(date,type,row) {
  return moment(date, 'YYYY-MM-DDTHH:mm:ss.fff Z').format('DD/MM/YYYY')
};

function frontEndDatetimeFormat(date,type,row) {
  return moment(date, 'YYYY-MM-DDTHH:mm:ss.fff Z').format('DD/MM/YYYY hh:mm a')
};
function openThreePoint() {
  if (threePoint) {
    $(".moreOptions").fadeIn();
    $(".moreOptions").css({
      'display': 'flex'
    });
    setTimeout(function() {
      $("body").addClass("closeMoreOptions");
    }, 10);
    threePoint = false;
  }
};

function openMenuMain() {
  if (menuMain) {
    $(".nav-container").fadeIn();
    $(".nav-container").css({
      'display': 'flex'
    });
    setTimeout(function() {
      $("body").addClass("closeMenuMain");
    }, 10);
    menuMain = false;
  }
};

$(document).on("click", ".closeMoreOptions", function() {
  $(".moreOptions").fadeOut();
  $(".closeMoreOptions").attr("class", "");
  threePoint = true;
})
