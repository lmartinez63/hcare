<template>
<div class="content-container">
  <section data-ui-view="" class="view-container animate-fade-up">
    <section class="page">
      <div class="titleForm">
        Cita Medica de Paciente {{medicalAppointment.patient.patientCode}}
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
              Datos de Paciente
            </div>
            <div class="twoCol">
              <div class="group">
                <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
                  <input class="mdl-textfield__input" type="text" name="medicalAppointment-patient-firstName" id="medicalAppointment-patient-firstName" v-model="medicalAppointment.patient.firstName" />
                  <label class="labelText" for="medicalAppointment-patient-firstName">Nombres</label>
                </div>
              </div>
              <div class="group">
                <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
                  <input class="mdl-textfield__input" type="text" name="medicalAppointment-patient-lastName" id="medicalAppointment-patient-lastName" v-model="medicalAppointment.patient.lastName" />
                  <label class="labelText" for="medicalAppointment-patient-lastName">Codigo del Paciente</label>
                </div>
              </div>
            </div>
            <div class="subTitle">
              Datos de la Cita
            </div>
            <div class="twoCol">
              <div class="group">
                <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
                  <!--<datepicker v-model="medicalAppointment.dateAppointment"></datepicker>-->
                  <datetime type="datetime" :minute-step="30" v-model="medicalAppointment.dateAppointment"></datetime>
                  <label class="labelText" for="medicalAppointment-dateAppointment">Fecha de la Cita</label>
                </div>
              </div>
              <div class="group">
                <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
                  <label class="mdl-textfield__input labelNoEdit" id="medicalAppointment-status">{{$parent.medicalAppointmentStatus[medicalAppointment.status]}}</label>
                  <label class="labelText" for="medicalAppointment-status">Estado de la Cita</label>
                </div>
              </div>
              <div class="group">
                <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
                  <select v-model="medicalAppointment.medicalAreaId">
                    <option v-for="medicalArea in medicalAreas" v-bind:value="medicalArea.id">
                      {{ medicalArea.areaName }}
                    </option>
                  </select>
                  <label class="labelText" for="medicalAppointment-medicalAreaId">Especialidad</label>
                </div>
              </div>
              <div class="group">
                <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
                  <select v-model="medicalAppointment.doctorId">
                    <option v-for="doctor in doctors" v-bind:value="doctor.id">
                      {{ doctor.firstName }}
                    </option>
                  </select>
                  <label class="labelText" for="medicalAppointment-doctorId">Especialista</label>
                </div>
              </div>
              <div class="groupFull">
                <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
                  <input class="mdl-textfield__input" type="text" name="medicalAppointment-visitReason" id="medicalAppointment-visitReason" v-model="medicalAppointment.visitReason" />
                  <label class="labelText" for="medicalAppointment-visitReason">Rason de la Visita</label>
                </div>
              </div>
              <div class="groupFull" v-if="medicalAppointment.status > 0">
                <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
                  <input class="mdl-textfield__input" type="text" name="medicalAppointment-diagnostic" id="medicalAppointment-diagnostic" v-model="medicalAppointment.diagnostic" />
                  <label class="labelText" for="medicalAppointment-diagnostic">Diagnostico</label>
                </div>
              </div>
            </div>
            <div class="subTitle">
              Documentos Adjuntos
            </div>
            <div class="twoCol">
              <ul id="attachment-list">
                <li v-for="attachment in medicalAppointment.attachmentList">
                  <div v-on:click="downloadAttachment(attachment)">
                    <span>{{ attachment.fileName }}</span>
                    <!--<i class="fas fa-file-download"></i>-->
                    <i class="fas fa-save"></i>
                  </div>
                </li>
              </ul>
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
import { Datetime } from 'vue-datetime';
import 'vue-datetime/dist/vue-datetime.css'
import { Settings } from 'luxon'

Settings.defaultLocale = 'es'

let installed = false

export default {
  name: 'MedicalAppointmentComponent',
  data() {
    return {
      medicalAppointment: {
        dateAppointment: (new Date()).toISOString(),
        status: 0,
        patient: {},
        attachmentList: [],
      },
      medicalAreas: [
      ],
      doctors:[
      ],

    }
  },
  components: {
    Datepicker,
    datetime: Datetime,
  },
  created: function() {
    console.log("MedicalAppointmentComponent");
    let selfVue = this
    if (this.$route.params.medicalAppointmentId !== 'null') {
      var urlMedicalAppointmentInfo = this.$parent.backendUrl + 'medicalAppointments/' + this.$route.params.medicalAppointmentId
      axios.get(urlMedicalAppointmentInfo)
        .then(response => {
          selfVue.medicalAppointment = response.data
          if (selfVue.medicalAppointment.id == null) {
            selfVue.medicalAppointment.dateAppointment = (new Date()).toISOString()
            selfVue.medicalAppointment.status = 0
          }
        })
        .catch(error => {
          console.log(error)
        })
    } else {
      var urlPatientInfo = this.$parent.backendUrl + 'patients/' + this.$route.params.patientId
      axios.get(urlPatientInfo)
        .then(response => {
          selfVue.medicalAppointment.patient = response.data
          selfVue.medicalAppointment.patientId = response.data.id
          selfVue.medicalAppointment.patientCode = response.data.patientCode
        })
        .catch(error => {
          console.log(error)
        })
    }
    var urlMedicalAreaInfo = this.$parent.backendUrl + 'medicalAreas'
    axios.get(urlMedicalAreaInfo)
      .then(response => {
        this.medicalAreas = response.data
      })
      .catch(error => {
        console.log(error)
      })
    var urlDoctorInfo = this.$parent.backendUrl + 'employeesByTitle/1'
      axios.get(urlDoctorInfo)
        .then(response => {
          this.doctors = response.data
        })
        .catch(error => {
          console.log(error)
        })
  },
  methods: {
    goBackBrowse: function() {
      this.$router.push({
        name: 'BrowseComponent'
      })
    },
    saveObjectState: function() {
      const url = this.$parent.backendUrl + 'medicalAppointments'
      //Add patientId to medicalHistory
      let selfVue = this
      axios.post(url, this.medicalAppointment)
        .then(response => {
          selfVue.medicalAppointment = response.data
          selfVue.$parent.sucessMessage()
        })
        .catch(error => {
          console.log(error)
        })
      // this.$router.push({ name: '/'})
    },
    downloadAttachment: function(attachment) {
      axios.get(this.$parent.backendUrl + 'downloadAttachment/' + attachment.id, {
          responseType: 'blob',
        }).then(response => {
          //Download File directly
          const url = window.URL.createObjectURL(new Blob([response.data], {
            type: attachment.contentType
          }))
          const link = document.createElement('a')
          link.href = url
          link.setAttribute('download', attachment.fileName)
          document.body.appendChild(link);
          link.click();

          //Preview on page
          /*
          var file = new Blob([response.data], {type: 'image/png'});
          var fileURL = URL.createObjectURL(file);
          window.open(fileURL,'_blank');
          */
        })
        .catch(error => {
          console.log(error)
        })
    },
    uploadAttachment: function(entity, entityId, entityCode) {
      this.$router.push({
        name: 'AttachmentComponent',
        params: {
          entity: entity,
          entityId: entityId,
          entityCode: entityCode,
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
