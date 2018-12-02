<template>
<div class="content-container">
  <section data-ui-view="" class="view-container animate-fade-up">
    <section class="page">
      <div class="titleForm">Paciente {{patient.historyCode}}</div>
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
              <div v-if="patient.historyCode != null "v-on:click="viewMedicalHistory(patient.historyCode)" class="link">
                <div class="icon"><i class="fas fa-save"></i></div>
                <div class="text">Historial medico</div>
              </div>
              <div v-on:click="viewMedicalAppointmentHistory(patient.documentNumber)" class="link">
                <div class="icon"><i class="fas fa-save"></i></div>
                <div class="text">Historial de Citas</div>
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
                  <select v-model="patient.documentType">
                    <option v-for="documentType in $parent.documentTypes" v-bind:value="documentType.key">
                      {{ documentType.value }}
                    </option>
                  </select>
                  <label class="labelText" for="patient-documentType">Tipo de Documento</label>
                </div>
              </div>
              <div class="group">
                <div class="form-group mdl-textfield mdl-js-textfield mdl-textfield--floating-label" :class="{ 'form-group--error': $v.patient.documentNumber.$error }">
                  <input class="form__input mdl-textfield__input" type="text" name="patient-documentNumber" id="patient-documentNumber" v-model.trim="$v.patient.documentNumber.$model"  @change="getPatientInfo()" />
                  <label class="form__label labelText" for="patient-documentNumber"> Numero de Documento</label>
                </div>
                <div class="error" v-if="!$v.patient.documentNumber.required">Numero de Documento requerido</div>
              </div>
              <div class="group">
                <div class="form-group mdl-textfield mdl-js-textfield mdl-textfield--floating-label" :class="{ 'form-group--error': $v.patient.firstName.$error }">
                  <input class="form__input mdl-textfield__input" type="text" name="patient-firstName" id="patient-firstName" v-model="$v.patient.firstName.$model" />
                  <label class="form__label labelText" for="patient-firstName">Nombres</label>
                </div>
                <div class="error" v-if="!$v.patient.firstName.required">Nombres requeridos</div>
              </div>
              <div class="group">
                <div class="form-group mdl-textfield mdl-js-textfield mdl-textfield--floating-label" :class="{ 'form-group--error': $v.patient.lastName.$error }">
                  <input class="form__input mdl-textfield__input" type="text" name="patient-lastName" id="patient-lastName" v-model="$v.patient.lastName.$model" />
                  <label class="form__label labelText" for="patient-lastName">Apellidos</label>
                </div>
                <div class="error" v-if="!$v.patient.lastName.required">Apellidos requerido</div>
              </div>
              <div class="group">
                <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
                  <datepicker v-model="patient.birthday"></datepicker>
                  <label class="labelText" for="patient-birthday">Fecha de Nacimiento</label>
                </div>
              </div>
              <div class="group">
                <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
                  <select v-model="patient.civilStatus">
                    <option v-for="civilStatus in $parent.civilStatus" v-bind:value="civilStatus.key">
                      {{ civilStatus.value }}
                    </option>
                  </select>
                  <label class="labelText" for="patient-civilStatus">Estado Civil</label>
                </div>
              </div>
              <div class="group">
                <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
                  <input class="mdl-textfield__input" type="text" name="patient-phoneNumber" id="patient-phoneNumber" v-model="patient.phoneNumber" />
                  <label class="labelText" for="patient-phoneNumber">Numero Telefonico</label>
                </div>
              </div>
              <div class="group">
                <div class="form-group mdl-textfield mdl-js-textfield mdl-textfield--floating-label" :class="{ 'form-group--error': $v.patient.celPhoneNumber.$error }">
                  <input class="form__input mdl-textfield__input" type="text" name="patient-celPhoneNumber" id="patient-celPhoneNumber" v-model.trim="$v.patient.celPhoneNumber.$model" />
                  <label class="form__label labelText" for="patient-celPhoneNumber">Numero de Celular</label>
                </div>
                <div class="error" v-if="!$v.patient.celPhoneNumber.required">Numero de Celular requerido</div>
              </div>
              <div class="group">
                <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
                  <input class="mdl-textfield__input" type="text" name="patient-emailAddress" id="patient-emailAddress" v-model="patient.emailAddress" />
                  <label class="labelText" for="patient-emailAddress">Correo Electronico</label>
                </div>
              </div>
              <div class="group">
                <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
                  <select v-model="patient.addressDistrict">
                    <option v-for="district in $parent.districts" v-bind:value="district.key">
                      {{ district.value }}
                    </option>
                  </select>
                  <label class="labelText" for="patient-addressDistrict">Distrito</label>
                </div>
              </div>
              <div class="groupFull">
                <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
                  <input class="mdl-textfield__input" type="text" name="patient-address" id="patient-address" v-model="patient.address" />
                  <label class="labelText" for="patient-address">Direccion</label>
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

import {
  required,
  minLength,
  between,
  email,
  maxLength
} from 'vuelidate/lib/validators'

let installed = false

export default {
  name: 'PatientComponent',
  data() {
    return {
      patient: {
        firstName: '',
        lastName: '',
        celPhoneNumber:'',
        documentNumber:'',
        documentType: 1,
      },
    }
  },
  validations: {
    patient:{
      firstName: {
        required,
      },
      lastName: {
        required,
      },
      celPhoneNumber: {
        required,
      },
      documentNumber: {
        required,
      },
    }
  },
  components: {
    Datepicker,
  },
  created: function() {
    console.log("PatientComponent");
    if (this.$route.params.id !== 'null') {
      const url = this.$parent.backendUrl + 'patients/' + this.$route.params.id
      let selfVue = this
      axios.get(url)
        .then(response => {
          selfVue.patient = response.data
          //this.patient.birthday = this.frontEndDateFormat(this.patient.birthday)
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
    validateDocumentNumber: function() {
      axios.post(url, this.patient)
        .then(response => {
          selfVue.patient = response.data
          selfVue.$parent.sucessMessage()
          //this.patient.birthday = this.frontEndDateFormat(this.patient.birthday)
          setTimeout(() => {
            this.$router.push({
              name: 'BrowseComponent',
              params: { browseType: 'allPatients', entityId: 'null' }
            })
          },1000)
        })
        .catch(error => {
          //this.patient.birthday = this.frontEndDateFormat(this.patient.birthday)
          console.log(error)
        })
    },
    getPatientInfo: function() {
      let selfVue = this
      axios.get(this.$parent.backendUrl + 'retrievePatientByDocumentNumber/' + this.patient.documentNumber)
        .then(response => {
          if (response.data != null) {
            selfVue.patient= response.data
          }
          //this.patient.birthday = this.frontEndDateFormat(this.patient.birthday)
        })
        .catch(error => {
          console.log(error)
        })
    },
    saveObjectState: function() {
      const url = this.$parent.backendUrl + 'patients'
      //this.patient.birthday = this.backEndDateFormat(this.patient.birthday)
      if (this.patient.emailAddress == '') {
        this.patient.emailAddress = 'novaclinicarequipa@gmail.com'
      }
      let selfVue = this
      this.$v.$touch()
      if (this.$v.$invalid) {
        selfVue.$parent.errorMessage("Por favor complete los campos requeridos")
      } else {
        axios.post(url, this.patient)
          .then(response => {
            selfVue.patient = response.data
            selfVue.$parent.sucessMessage()
            //this.patient.birthday = this.frontEndDateFormat(this.patient.birthday)
            setTimeout(() => {
              this.$router.push({
                name: 'BrowseComponent',
                params: { browseType: 'allPatients', entityId: 'null' }
              })
            },1000)
          })
          .catch(error => {
            //this.patient.birthday = this.frontEndDateFormat(this.patient.birthday)
            console.log(error)
          })
      }
    },
    viewMedicalHistory: function(historyCode) {
      this.$router.push({
        name: 'MedicalHistoryComponent',
        params: {
          historyCode: historyCode
        }
      })
    },
    viewMedicalAppointmentHistory: function(documentNumber) {
      this.$router.push({
        name: 'BrowseComponent',
        params: {
          browseType: 'medicalAppointmentsByPatient',
          entityId: documentNumber,
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
