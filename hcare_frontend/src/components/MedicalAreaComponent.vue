<template>
<div class="content-container">
  <section data-ui-view="" class="view-container animate-fade-up">
    <section class="page">
      <div class="titleForm">
        Area {{medicalArea.areaName}}
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
        <div class="formBox">
          <div class="headerFacNew">
            <div class="subTitle">
              Datos del Area
            </div>
            <div class="twoCol">
              <div class="group">
                <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
                  <input class="mdl-textfield__input" type="text" name="medicalArea-areaName" id="medicalArea-areaName" v-model="medicalArea.areaName" />
                  <label class="labelText" for="medicalArea-areaName">Nombre del Area</label>
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
import axios from 'axios'
import moment from 'moment'
import round from 'vue-round-filter'
import Datepicker from 'vuejs-datepicker';

let installed = false

export default {
  name: 'MedicalAreaComponent',
  data() {
    return {
      medicalArea: {
      },
    }
  },
  components: {
    Datepicker
  },
  created: function() {
    console.log("MedicalAreaComponent");
    if (this.$route.params.medicalAreaId !== 'null') {
      var urlMedicalAreaInfo = this.$parent.backendUrl + 'medicalAreas/' + this.$route.params.medicalAreaId
      axios.get(urlMedicalAreaInfo)
        .then(response => {
          this.medicalArea = response.data
        })
        .catch(error => {
          console.log(error)
        })
    }
  },
  methods: {
    goBackBrowse: function() {
      this.$router.push({
        name: 'BrowseComponent'
      })
    },
    saveObjectState: function() {
      const url = this.$parent.backendUrl + 'medicalAreas'
      //Add patientId to medicalHistory
      let selfVue = this
      axios.post(url, this.medicalArea)
        .then(response => {
          this.medicalArea = response.data
          selfVue.$parent.sucessMessage()
        })
        .catch(error => {
          console.log(error)
        })
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
