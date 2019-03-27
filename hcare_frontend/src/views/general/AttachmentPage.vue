<template>
<div class="content-container">
  <section data-ui-view="" class="view-container animate-fade-up">
    <section class="page">
      <div class="titleForm">
        {{title}}
      </div>
      <div class="row ui-section contentMain">
        <div class="btnMoreActions" onClick="openThreePoint()">
          <div class="btnMore">
            <i class="fas fa-ellipsis-v"></i>
          </div>
          <div class="moreOptions" id="moreOptions">
            <div class="menuContent">
              <div v-on:click="uploadAttachment()" class="link">
                <div class="icon"><i class="fas fa-save"></i></div>
                <div class="text">Guardar</div>
              </div>
            </div>
          </div>
        </div>
        <div class="formBox">
          <div class="headerFacNew">
            <div class="subTitle">
              Subir Documento
            </div>
            <div class="twoCol">
              <div class="group">
                <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
                  <input class="mdl-textfield__input" type="text" name="filename" id="filename" v-model="fileName" />
                  <label class="labelText" for="filename">Titulo de Documento</label>
                </div>
              </div>
              <div class="group">
                <div>
                  <input type="file" id="file" ref="file" v-on:change="handleFileUpload()" />
                </div>
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
  name: 'AttachmentPage',
  data() {
    return {
      title: '',
      fileName: '',
      file: '',
      attachment: {
        entityCode: 0,
        entityId: 0,
        entity: '',
      },
    }
  },
  created: function() {
    console.log("AttachmentPage - created - begin");
    this.attachment.entityCode = this.$route.params.entityCode
    this.attachment.entityId = this.$route.params.entityId
    this.attachment.entity = this.$route.params.entity
    switch (this.attachment.entity) {
      case 'medicalHistory':
        this.title = 'Historia Medica ' + this.attachment.entityCode
        break;
      default:
        break;
    }
    console.log("AttachmentPage - created - end");
  },
  methods: {
    uploadAttachment: function() {
      console.log("uploadAttachment - method - saveObjectState - begin");
      let formData = new FormData();
      //Add the form data we need to submit
      formData.append('file', this.file);
      formData.append('fileName', this.fileName);
      formData.append('entity', this.attachment.entity);
      formData.append('entityId', this.attachment.entityId);
      
      const {
        dispatch
      } = this.$store;
      dispatch('general/uploadAttachment', {
          vm: this,
          dataContent: formData,
          //returnRoute: returnRoute,
      });
      console.log("uploadAttachment - method - saveObjectState - end");
    },
    //Handles a change on the file upload
    handleFileUpload() {
      this.file = this.$refs.file.files[0];
    }
  }
}
</script>
