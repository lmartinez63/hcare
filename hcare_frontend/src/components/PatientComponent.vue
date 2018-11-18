<template>
<div class="content-container">
  <section data-ui-view="" class="view-container animate-fade-up">
    <section class="page">
      <div class="titleForm">Paciente {{patient.patientCode}}</div>
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
              <div v-on:click="viewMedicalHistory(patient.id)" class="link">
                <div class="icon"><i class="fas fa-save"></i></div>
                <div class="text">Historial medico</div>
              </div>
            </div>
          </div>
        </div>
        <div class="formBox">
          <div class="headerFacNew">
            <div class="subTitle">Datos del Paciente</div>
            <div class="twoCol">
              <div class="group">
                <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
                  <input class="mdl-textfield__input" type="text" name="patient-patientCode" id="patient-patientCode" v-model="patient.patientCode" />
                  <label class="labelText" for="patient-patientCode">Codigo del Paciente</label>
                </div>
              </div>
              <div class="group">
                <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
                  <input class="mdl-textfield__input" type="text" name="patient-firstName" id="patient-firstName" v-model="patient.firstName" />
                  <label class="labelText" for="patient-firstName">Nombres</label>
                </div>
              </div>
              <div class="group">
                <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
                  <input class="mdl-textfield__input" type="text" name="patient-lastName" id="patient-lastName" v-model="patient.lastName" />
                  <label class="labelText" for="patient-lastName">Apellidos</label>
                </div>
              </div>
              <div class="group">
                <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
                  <input class="mdl-textfield__input" type="text" name="patient-birthday" id="patient-birthday" v-model="patient.birthday" />
                  <label class="labelText" for="patient-birthday">Fecha de Nacimiento</label>
                </div>
              </div>
              <div class="groupFull">
                <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
                  <input class="mdl-textfield__input" type="text" name="patient-emailAddress" id="patient-emailAddress" v-model="patient.emailAddress" />
                  <label class="labelText" for="patient-emailAddress">Correo Electronico</label>
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

let installed = false

export default {
  name: 'PatientComponent',
  data() {
    return {
      patient: {},
    }
  },
  created: function() {

    if (this.$route.params.id !== 'null') {
      const url = this.$parent.backendUrl + 'patients/' + this.$route.params.id
      axios.get(url)
        .then(response => {
          this.patient = response.data
          this.patient.birthday = this.frontEndDateFormat(this.patient.birthday)
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
      const url = this.$parent.backendUrl + 'patients'
      this.patient.birthday = this.backEndDateFormat(this.patient.birthday)
      axios.post(url, this.patient)
        .then(response => {
          this.patient = response.data
          this.patient.birthday = this.frontEndDateFormat(this.patient.birthday)
        })
        .catch(error => {
          this.patient.birthday = this.frontEndDateFormat(this.patient.birthday)
          console.log(error)
        })
      // this.$router.push({ name: '/'})
    },
    viewMedicalHistory: function(patientId) {
      this.$router.push({
        name: 'MedicalHistoryComponent',
        params: {
          patientId
        }
      })
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
