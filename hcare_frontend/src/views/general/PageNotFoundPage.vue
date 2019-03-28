<template>
<div class="content-container">
  <section data-ui-view="" class="view-container animate-fade-up">
    <section class="page">
      <div class="titleForm">
        Recurso no encontrado
      </div>
      <div class="row ui-section contentMain">
        <div class="btnMoreActions" onClick="openThreePoint()">
          <div class="btnMore">
            <i class="fas fa-ellipsis-v"></i>
          </div>
          <div class="moreOptions" id="moreOptions">
            <div class="menuContent">
              <div v-on:click="back()" class="link">
                <div class="icon"><i class="fas fa-save"></i></div>
                <div class="text">Regresar a la Pagina anterior</div>
              </div>
            </div>
          </div>
        </div>
        <div class="formBox">
          <div class="headerFacNew">
            <div class="twoCol">
              <div class="group">
                Hubo un problema con su pedicion el recurso no esta disponible, por favor regrese a la pagina anterior.
              </div>
            </div>
          </div>
        </div>
      </div>
    </section>
  </section>
</div>
</template>
<!--script src="https://unpkg.com/element-ui/lib/index.js"></script-->
<script>

export default {
  name: 'PageNotFoundPage',
  data() {
    return {
    }
  },
  created: function() {
    console.log("AttachmentPage - created - begin");

  },
  methods: {

  }
}
</script>
