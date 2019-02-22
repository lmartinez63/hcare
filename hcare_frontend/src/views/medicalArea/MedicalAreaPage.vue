<template>
<div class="content-container">
  <section data-ui-view="" class="view-container animate-fade-up">
    <section class="page">
      <div class="titleForm">
        Area Medica
      </div>
      <div class="row ui-section contentMain">
        <div class="btnMoreActions" onClick="openThreePoint()">
          <div class="btnMore">
            <i class="fas fa-ellipsis-v"></i>
          </div>
          <div class="moreOptions" id="moreOptions">
            <div class="menuContent">
              <div v-on:click="saveObjectState()" class="link">
                <div class="icon"><i class="fas fa-save"></i></div>
                <div class="text">Guardar</div>
              </div>
              <!-- specialButtons -->
            </div>
          </div>
        </div>
        <div v-if="page && page.sectionMap" class="formBox">
          <div class="headerFacNew">
            <div v-if="page.sectionMap.generalInfo && page.sectionMap.generalInfo.visible && page.sectionMap.generalInfo.fieldDefinitionMap" class="subTitle">
              Datos del Generales
            </div>
            <div v-if="page.sectionMap.generalInfo && page.sectionMap.generalInfo.visible && page.sectionMap.generalInfo.fieldDefinitionMap" class="twoCol">
              <div v-if="page.sectionMap.generalInfo.fieldDefinitionMap.areaName && page.sectionMap.generalInfo.fieldDefinitionMap.areaName.visible" class="group">
                <div class="form-group mdl-textfield mdl-js-textfield mdl-textfield--floating-label" :class="{ 'form-group--error': $v.medicalArea.areaName.$error }">
                  <input class="form__input mdl-textfield__input" type="text" name="medicalArea-areaName" id="medicalArea-areaName" v-model.trim="$v.medicalArea.areaName.$model" @input="forceUppercase($event, medicalArea, 'areaName')" />
                  <label class="form__label labelText" for="medicalArea-areaName">Nombre del Area</label>
                </div>
                <div class="error" v-if="!$v.medicalArea.areaName.required">Nombre del Area requerido</div>
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
import axios from 'axios'
import moment from 'moment'
import round from 'vue-round-filter'
import Datepicker from 'vuejs-datepicker';

import {
  required,
  minLength,
  between,
  email,
  maxLength
} from 'vuelidate/lib/validators'


let installed = false

export default {
  name: 'MedicalAreaPage',
  data() {
    return {
      requestPage: 'medicalAreaInfo',
      defaultMedicalArea: {
        areaName:''
      },
    }
  },
  validations: {
    medicalArea:{
      areaName: {
        required,
      },
    }
  },
  components: {
    Datepicker
  },
  computed: {
    medicalArea() {
      return this.$store.state.medicalArea.data;
    },
    page() {
      return this.$store.state.medicalArea.metadata.page;
    }
  },
  created: function() {
    console.log("MedicalAreaPage - created - begin");
    const dataContent = {
      "medicalArea": {
        "id": this.$route.params.medicalAreaId
      }
    }
    const {
      requestPage
    } = this;
    const {
      dispatch
    } = this.$store;
    if (this.$route.params.medicalAreaId !== 'null') {
      dispatch('medicalArea/getById', {
        requestPage: requestPage,
        processName: 'RetrieveMedicalAreaInfo',
        dataContent: dataContent
      });
    } else {
      this.$store.state.medicalArea.data = this.defaultMedicalArea;
    }
    console.log("MedicalAreaPage - created - end");
  },
  methods: {
    goBackBrowse: function() {
      this.$router.push({
        name: 'BrowseComponent'
      })
    },
    saveObjectState: function() {
      console.log("MedicalAreaPage - method - saveObjectState - begin");
      const dataContent = {
        "medicalArea": this.medicalArea
      }
      const {
        requestPage
      } = this;
      const {
        dispatch
      } = this.$store;
      this.$v.$touch()
      if (this.$v.$invalid) {
        selfVue.$parent.errorMessage("Por favor complete los campos requeridos")
      } else {
        dispatch('medicalArea/saveEntity', {
          requestPage: requestPage,
          processName: 'SaveMedicalArea',
          dataContent: dataContent
        });
      }
      console.log("MedicalAreaPage - method - saveObjectState - end");

      /*
      const url = this.$parent.backendUrl + 'medicalAreas'
      let selfVue = this
      this.$v.$touch()
      if (this.$v.$invalid) {
        selfVue.$parent.errorMessage("Por favor complete los campos requeridos")
      } else {
        axios.post(url, this.medicalArea)
          .then(response => {
            this.medicalArea = response.data
            selfVue.$parent.sucessMessage()
            setTimeout(() => {
              this.$router.push({
                name: 'BrowseComponent',
                params: { browseType: 'allMedicalAreas', entityId: 'null' }
              })
            },1000)
          })
          .catch(error => {
            console.log(error)
          })
      }
      */
      // this.$router.push({ name: '/'})
    },
    frontEndDateFormat: function(date) {
      return moment(date, 'YYYY-MM-DDTHH:mm:ss.fff Z').format('DD/MM/YYYY')
    },
    backEndDateFormat: function(date) {
      return moment(date, 'DD/MM/YYYY').format('YYYY-MM-DDTHH:mm:ss.sssZ')
    }
  },
  filters: {
    capitalize: function(value) {
      if (!value) return ''
      value = value.toString()
      return value.charAt(0).toUpperCase() + value.slice(1)
    },
    round
  }
}
</script>
