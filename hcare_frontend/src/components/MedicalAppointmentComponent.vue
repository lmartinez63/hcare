<template>
<div class="content-container">
  <section data-ui-view="" class="view-container animate-fade-up">
    <section class="page">
      <div class="titleForm">
        Cita Medica
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
              <div v-if="medicalAppointment.status == '5'" v-on:click="updateStatus('10')" class="link">
                <div class="icon"><i class="fas fa-save"></i></div>
                <div class="text">En Atencion</div>
              </div>
              <div v-if="medicalAppointment.status == '5'" v-on:click="updateStatus('90')" class="link">
                <div class="icon"><i class="fas fa-save"></i></div>
                <div class="text">No se presento</div>
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
                <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label form-group">
                  <select v-model="medicalAppointment.medicalAppointmentType">
                    <option v-for="medicalAppointmentType in medicalAppointmentTypes" v-bind:value="medicalAppointmentType.id">
                      {{ medicalAppointmentType.type }}
                    </option>
                  </select>
                  <label class="labelText form__label" for="medicalAppointment-medicalAppointmentType">Tipo de Cita</label>
                </div>
              </div>
              <div class="group">
              </div>
              <div class="group">
                <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
                  <select v-model="medicalAppointment.documentType">
                    <option v-for="documentType in $parent.documentTypes" v-bind:value="documentType.key">
                      {{ documentType.value }}
                    </option>
                  </select>
                  <label class="labelText" for="medicalAppointment-documentType">Tipo de Documento</label>
                </div>
              </div>
              <div class="group">
                <div class="form-group mdl-textfield mdl-js-textfield mdl-textfield--floating-label" :class="{ 'form-group--error': $v.medicalAppointment.documentNumber.$error }">
                  <input class="form__input mdl-textfield__input" type="text" name="medicalAppointment-documentNumber" id="medicalAppointment-documentNumber" v-model.trim="$v.medicalAppointment.documentNumber.$model" @change="getPatientInfo()" />
                  <label class="form__label labelText" for="medicalAppointment-documentNumber">Numero de Documento</label>
                </div>
                <div class="error" v-if="!$v.medicalAppointment.documentNumber.required">Numero de Documento requerido</div>
              </div>
              <div class="group">
                <div class="form-group mdl-textfield mdl-js-textfield mdl-textfield--floating-label" :class="{ 'form-group--error': $v.medicalAppointment.firstName.$error }">
                  <input class="form__input mdl-textfield__input" type="text" name="medicalAppointment-firstName" id="medicalAppointment-firstName" v-model.trim="$v.medicalAppointment.firstName.$model" />
                  <label class="form__label labelText" for="medicalAppointment-firstName">Nombres</label>
                </div>
                <div class="error" v-if="!$v.medicalAppointment.firstName.required">Nombres requeridos</div>
              </div>
              <div class="group">
                <div class="form-group mdl-textfield mdl-js-textfield mdl-textfield--floating-label" :class="{ 'form-group--error': $v.medicalAppointment.lastName.$error }">
                  <input class="form__input mdl-textfield__input" type="text" name="medicalAppointment-lastName" id="medicalAppointment-lastName" v-model.trim="$v.medicalAppointment.lastName.$model" />
                  <label class="form__label labelText" for="medicalAppointment-lastName">Apellidos</label>
                </div>
                <div class="error" v-if="!$v.medicalAppointment.lastName.required">Apellidos requeridos</div>
              </div>
              <div class="group">
                <div class="form-group mdl-textfield mdl-js-textfield mdl-textfield--floating-label" :class="{ 'form-group--error': $v.medicalAppointment.celPhoneNumber.$error }">
                  <input class="form__input mdl-textfield__input" type="text" name="medicalAppointment-celPhoneNumber" id="medicalAppointment-celPhoneNumber" v-model="$v.medicalAppointment.celPhoneNumber.$model" />
                  <label class="form__label labelText" for="medicalAppointment-celPhoneNumber">Numero de Celular</label>
                </div>
                <div class="error" v-if="!$v.medicalAppointment.celPhoneNumber.required">Numero Celular requerido</div>
              </div>
              <div class="group">
                <div class="form-group mdl-textfield mdl-js-textfield mdl-textfield--floating-label" :class="{ 'form-group--error': $v.medicalAppointment.emailAddress.$error }">
                  <input class="form__input mdl-textfield__input" type="text" name="medicalAppointment-emailAddress" id="medicalAppointment-emailAddress" v-model.trim="$v.medicalAppointment.emailAddress.$model" />
                  <label class="form__label labelText" for="medicalAppointment-emailAddress">Correo Electronico</label>
                </div>
                <div class="error" v-if="!$v.medicalAppointment.emailAddress.required">Email requerido</div>
                <div class="error" v-if="!$v.medicalAppointment.emailAddress.email">Tiene que ser un correo de electronico valido</div>
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
                <div class="form-group mdl-textfield mdl-js-textfield mdl-textfield--floating-label" :class="{ 'form-group--error': $v.medicalAppointment.medicalAreaId.$error }">
                  <select v-model="$v.medicalAppointment.medicalAreaId.$model">
                    <option v-for="medicalArea in medicalAreas" v-bind:value="medicalArea.id">
                      {{ medicalArea.areaName }}
                    </option>
                  </select>
                  <label class="form__label labelText" for="medicalAppointment-medicalAreaId">Especialidad</label>
                </div>
                <div class="error" v-if="!$v.medicalAppointment.medicalAreaId.required">Especialidad requerida</div>
              </div>
              <div class="group">
                <div class="form-group mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
                  <select v-model="$v.medicalAppointment.doctorId.$model">
                    <option v-for="doctor in doctors" v-bind:value="doctor.id">
                      {{ doctor.firstName }}
                    </option>
                  </select>
                  <label class="form__label labelText" for="medicalAppointment-doctorId">Especialista</label>
                </div>
                <div class="error" v-if="!$v.medicalAppointment.doctorId.required">Especialidad requerida</div>
              </div>
              <div class="groupFull">
                <div class="form-group mdl-textfield mdl-js-textfield mdl-textfield--floating-label" :class="{ 'form-group--error': $v.medicalAppointment.notes.$error }">
                  <input class="mdl-textfield__input" type="text" name="medicalAppointment-notes" id="medicalAppointment-notes" v-model="$v.medicalAppointment.notes.$model" />
                  <label class="labelText" for="medicalAppointment-notes">Notas</label>
                </div>
                <div class="error" v-if="!$v.medicalAppointment.notes.maxLength">Cantidad de caracteres excedido</div>
              </div>
              <!-- medicalAppointment in attend -->
              <div class="groupFull" v-if="medicalAppointment.status == '10'">
                <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
                  <select v-model="medicalAppointment.preferentialDiagnostic">
                    <option v-for="preferentialDiagnostic in preferentialDiagnostics" v-bind:value="preferentialDiagnostic.key">
                      {{ preferentialDiagnostic.value }}
                    </option>
                  </select>
                  <label class="labelText" for="medicalAppointment-visitReason">Diagnostico Preferencial</label>
                </div>
              </div>
              <div class="groupFull" v-if="medicalAppointment.status == '10'">
                <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
                  <input class="mdl-textfield__input" type="text" name="medicalAppointment-diagnostic" id="medicalAppointment-diagnostic" v-model="medicalAppointment.diagnostic" />
                  <label class="labelText" for="medicalAppointment-diagnostic">Detalle de Diagnostico</label>
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
import {
  Datetime
} from 'vue-datetime';
import 'vue-datetime/dist/vue-datetime.css'
import {
  Settings
} from 'luxon'
//Validations
import {
  required,
  minLength,
  between,
  email,
  maxLength
} from 'vuelidate/lib/validators'


Settings.defaultLocale = 'es'

let installed = false

export default {
  name: 'MedicalAppointmentComponent',
  data() {
    return {
      medicalAppointment: {
        dateAppointment: (new Date()).toISOString(),
        status: 0,
        emailAddress: '',
        firstName: '',
        lastName: '',
        documentNumber: '',
        documentType: '1',
        celPhoneNumber: '',
        doctorId: '',
        medicalAreaId: '',
        medicalAppointmentType: '1',
        notes: '',
        patient: {},
        attachmentList: [],
      },
      medicalAreas: [],
      doctors: [],
      medicalAppointmentTypes: [{
          id: 1,
          type: 'Nueva Consulta'
        },
        {
          id: 2,
          type: 'Control'
        },
        {
          id: 3,
          type: 'Analisis de Laboratorio'
        },
        {
          id: 4,
          type: 'Riego Quirurgico'
        },
        {
          id: 5,
          type: 'Ecografia'
        },
        {
          id: 6,
          type: 'Eco Dopler'
        },
        {
          id: 7,
          type: 'Cirugia Ambulatoria'
        },
        {
          id: 8,
          type: 'Cirugia con Hospitalacion'
        },
        {
          id: 8,
          type: 'Ambulancia'
        },
      ],
      preferentialDiagnostics: [{
          key: 1,
          value: 'Cirugia de Rinoplastia'
        },
        {
          key: 2,
          value: 'Cirugia de Liposuccion'
        },
        {
          key: 3,
          value: 'Otros'
        },
      ],
    }
  },
  validations: {
    medicalAppointment:{
      emailAddress: {
        required,
        email,
      },
      lastName: {
        required,
      },
      firstName: {
        required,
      },
      documentNumber: {
        required,
      },
      celPhoneNumber: {
        required,
      },
      medicalAreaId: {
        required,
      },
      doctorId: {
        required,
      },
      notes: {
        maxLength: maxLength(4000)
      }
    },
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
    getPatientInfo: function() {
      let selfVue = this
      axios.get(this.$parent.backendUrl + 'retrievePatientByDocumentNumber/' + this.medicalAppointment.documentNumber)
        .then(response => {
          if (response.data != null) {
            selfVue.medicalAppointment.firstName = response.data.firstName
            selfVue.medicalAppointment.lastName = response.data.lastName
            selfVue.medicalAppointment.celPhoneNumber = response.data.celPhoneNumber
            selfVue.medicalAppointment.emailAddress = response.data.emailAddress
            selfVue.medicalAppointment.historyCode = response.data.historyCode
          }
          //this.patient.birthday = this.frontEndDateFormat(this.patient.birthday)
        })
        .catch(error => {
          console.log(error)
        })
    },
    goBackBrowse: function() {
      this.$router.push({
        name: 'BrowseComponent'
      })
    },
    updateStatus: function(newStatus) {
      this.medicalAppointment.status = newStatus
      this.saveObjectState();
      // this.$router.push({ name: '/'})
    },
    saveObjectState: function() {
      const url = this.$parent.backendUrl + 'medicalAppointments'
      //Add patientId to medicalHistory
      if (this.medicalAppointment.emailAddress == '') {
        this.medicalAppointment.emailAddress = 'novaclinicarequipa@gmail.com'
      }
      let selfVue = this
      this.$v.$touch()
      if (this.$v.$invalid) {
        selfVue.$parent.errorMessage("Por favor complete los campos requeridos")
      } else {
        // do your submit logic here
        axios.post(url, this.medicalAppointment)
          .then(response => {
            selfVue.medicalAppointment = response.data
            selfVue.$parent.sucessMessage()
            setTimeout(() => {
              this.$router.push({
                name: 'BrowseComponent',
                params: { browseType: 'allMedicalAppointments', entityId: 'null' }
              })
            },1000)
          })
          .catch(error => {
            console.log(error)
          })
      }

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
