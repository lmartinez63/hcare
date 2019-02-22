<template>
</template>

<script>
export default {
    computed: {
        /*users () {
            return this.$store.state.users.all;
        }*/
    },
    created () {
      console.log('HomePage - created - begin');
//TODO need to be moved to their own component where is used
      $('#table_id').DataTable({
        responsive: true
      });
//TODO need to be moved to app.vue as it funtion
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
//TODO need to be moved to app.vue as it funtion
      $('.btn-menu').click(function() {
        $('.contenedor-menu .menu').slideToggle();
      });
//TODO need to be moved to app.vue as it funtion
      $('.menu li ul li a').click(function() {
        window.location.href = $(this).attr("href");
      });
        //this.$store.dispatch('users/getAll');
      console.log('HomePage - created - end');
    }
};
</script>
