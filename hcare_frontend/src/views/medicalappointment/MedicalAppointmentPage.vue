<template>
<div class="content-container">
  <section data-ui-view="" class="view-container animate-fade-up">
    <section v-if="page && page.sectionMap" class="page">
      <div class="titleForm">
        {{$parent.getLabelValue(page.label)}}
      </div>
      <div class="row ui-section contentMain">
        <div class="btnMoreActions" onClick="openThreePoint()">
          <div class="btnMore">
            <i class="fas fa-ellipsis-v"></i>
          </div>
          <div class="moreOptions" id="moreOptions">
            <div class="menuContent">
              <div v-for="button in page.pageButtons" v-if="button.visible" v-on:click="executeAction(button)" class="link">
                <div class="icon"><i class="button.icon"></i></div>
                <div class="text">{{$parent.getLabelValue(button.label)}}</div>
              </div>
            </div>
          </div>
        </div>
        <div v-if="page && page.sectionMap" class="formBox">
          <div class="headerFacNew">
            <div v-if="page.sectionMap.patientInfo && page.sectionMap.patientInfo.visible && page.sectionMap.patientInfo.fieldDefinitionMap" class="subTitle">
              {{$parent.getLabelValue(page.sectionMap.patientInfo.label)}}
            </div>
            <div v-if="page.sectionMap.patientInfo && page.sectionMap.patientInfo.visible && page.sectionMap.patientInfo.fieldDefinitionMap" class="twoCol">
              <div v-if="page.sectionMap.medicalAppointmentInfo.fieldDefinitionMap.medicalAppointmentType && page.sectionMap.medicalAppointmentInfo.fieldDefinitionMap.medicalAppointmentType.visible" class="group">
                <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label form-group">
                  <select v-model="medicalAppointment.medicalAppointmentType" :disabled="!page.sectionMap.medicalAppointmentInfo.fieldDefinitionMap.medicalAppointmentType.editable" >
                    <option v-for="(value, key, index) in medicalAppointmentTypes" v-bind:value="key">
                      {{ value }}
                    </option>
                  </select>
                  <label class="labelText form__label" for="medicalAppointment-medicalAppointmentType">{{$parent.getLabelValue( page.sectionMap.medicalAppointmentInfo.fieldDefinitionMap.medicalAppointmentType.label)}}</label>
                </div>
              </div>
              <div v-if="page.sectionMap.patientInfo.fieldDefinitionMap.medicalAppointmentType && page.sectionMap.patientInfo.fieldDefinitionMap.medicalAppointmentType.visible" class="group">
              </div>
              <!--
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
            -->
              <div v-if="page.sectionMap.patientInfo.fieldDefinitionMap.documentNumber && page.sectionMap.patientInfo.fieldDefinitionMap.documentNumber.visible" class="group">
                <div class="form-group mdl-textfield mdl-js-textfield mdl-textfield--floating-label" :class="{ 'form-group--error': $v.medicalAppointment.documentNumber.$error }">
                  <input :disabled="!page.sectionMap.patientInfo.fieldDefinitionMap.documentNumber.editable"  class="form__input mdl-textfield__input" type="text" name="medicalAppointment-documentNumber" id="medicalAppointment-documentNumber" v-model.trim="$v.medicalAppointment.documentNumber.$model" @change="getPatientInfo()" />
                  <label class="form__label labelText" for="medicalAppointment-documentNumber">{{$parent.getLabelValue( page.sectionMap.patientInfo.fieldDefinitionMap.documentNumber.label)}}</label>
                </div>
                <div class="error" v-if="!$v.medicalAppointment.documentNumber.required">Numero de Documento requerido</div>
              </div>
              <div v-if="page.sectionMap.patientInfo.fieldDefinitionMap.firstName && page.sectionMap.patientInfo.fieldDefinitionMap.firstName.visible" class="group">
                <div class="form-group mdl-textfield mdl-js-textfield mdl-textfield--floating-label" :class="{ 'form-group--error': $v.medicalAppointment.firstName.$error }">
                  <input :disabled="!page.sectionMap.patientInfo.fieldDefinitionMap.firstName.editable" class="form__input mdl-textfield__input" type="text" name="medicalAppointment-firstName" id="medicalAppointment-firstName" v-model.trim="$v.medicalAppointment.firstName.$model" @input="forceUppercase($event, medicalAppointment, 'firstName')" />
                  <label class="form__label labelText" for="medicalAppointment-firstName">{{$parent.getLabelValue( page.sectionMap.patientInfo.fieldDefinitionMap.firstName.label)}}</label>
                </div>
                <div class="error" v-if="!$v.medicalAppointment.firstName.required">Nombres requeridos</div>
              </div>
              <div v-if="page.sectionMap.patientInfo.fieldDefinitionMap.lastName && page.sectionMap.patientInfo.fieldDefinitionMap.lastName.visible" class="group">
                <div class="form-group mdl-textfield mdl-js-textfield mdl-textfield--floating-label" :class="{ 'form-group--error': $v.medicalAppointment.lastName.$error }">
                  <input :disabled="!page.sectionMap.patientInfo.fieldDefinitionMap.lastName.editable" class="form__input mdl-textfield__input" type="text" name="medicalAppointment-lastName" id="medicalAppointment-lastName" v-model.trim="$v.medicalAppointment.lastName.$model" @input="forceUppercase($event, medicalAppointment, 'lastName')" />
                  <label class="form__label labelText" for="medicalAppointment-lastName">{{$parent.getLabelValue( page.sectionMap.patientInfo.fieldDefinitionMap.lastName.label)}}</label>
                </div>
                <div class="error" v-if="!$v.medicalAppointment.lastName.required">Apellidos requeridos</div>
              </div>
              <div v-if="page.sectionMap.patientInfo.fieldDefinitionMap.celPhoneNumber && page.sectionMap.patientInfo.fieldDefinitionMap.celPhoneNumber.visible" class="group">
                <div class="form-group mdl-textfield mdl-js-textfield mdl-textfield--floating-label" :class="{ 'form-group--error': $v.medicalAppointment.celPhoneNumber.$error }">
                  <input :disabled="!page.sectionMap.patientInfo.fieldDefinitionMap.celPhoneNumber.editable" class="form__input mdl-textfield__input" type="text" name="medicalAppointment-celPhoneNumber" id="medicalAppointment-celPhoneNumber" v-model="$v.medicalAppointment.celPhoneNumber.$model" />
                  <label class="form__label labelText" for="medicalAppointment-celPhoneNumber">{{$parent.getLabelValue( page.sectionMap.patientInfo.fieldDefinitionMap.celPhoneNumber.label)}}</label>
                </div>
                <div class="error" v-if="!$v.medicalAppointment.celPhoneNumber.required">Numero Celular requerido</div>
              </div>
              <div v-if="page.sectionMap.patientInfo.fieldDefinitionMap.emailAddress && page.sectionMap.patientInfo.fieldDefinitionMap.emailAddress.visible" class="group">
                <div class="form-group mdl-textfield mdl-js-textfield mdl-textfield--floating-label" :class="{ 'form-group--error': $v.medicalAppointment.emailAddress.$error }">
                  <input :disabled="!page.sectionMap.patientInfo.fieldDefinitionMap.emailAddress.editable" class="form__input mdl-textfield__input" type="text" name="medicalAppointment-emailAddress" id="medicalAppointment-emailAddress" v-model.trim="$v.medicalAppointment.emailAddress.$model" />
                  <label class="form__label labelText" for="medicalAppointment-emailAddress">{{$parent.getLabelValue( page.sectionMap.patientInfo.fieldDefinitionMap.emailAddress.label)}}</label>
                </div>
                <div class="error" v-if="!$v.medicalAppointment.emailAddress.required">Email requerido</div>
                <div class="error" v-if="!$v.medicalAppointment.emailAddress.email">Tiene que ser un correo de electronico valido</div>
              </div>
            </div>
            <div v-if="page.sectionMap.medicalAppointmentInfo && page.sectionMap.medicalAppointmentInfo.visible && page.sectionMap.medicalAppointmentInfo.fieldDefinitionMap" class="subTitle">
              {{$parent.getLabelValue(page.sectionMap.medicalAppointmentInfo.label)}}
            </div>
            <div class="twoCol">
              <div v-if="page.sectionMap.medicalAppointmentInfo.fieldDefinitionMap.dateAppointment && page.sectionMap.medicalAppointmentInfo.fieldDefinitionMap.dateAppointment.visible" class="group">
                <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
                  <!--<datepicker v-model="medicalAppointment.dateAppointment"></datepicker>-->
                  <datetime :disabled="!page.sectionMap.medicalAppointmentInfo.fieldDefinitionMap.dateAppointment.editable" type="datetime" :minute-step="30" v-model="medicalAppointment.dateAppointment"></datetime>
                  <label class="labelText" for="medicalAppointment-dateAppointment">{{$parent.getLabelValue( page.sectionMap.medicalAppointmentInfo.fieldDefinitionMap.dateAppointment.label)}}</label>
                </div>
              </div>
              <!--
              <div class="group">
                <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
                  <label class="mdl-textfield__input labelNoEdit" id="medicalAppointment-status">{{$parent.medicalAppointmentStatus[medicalAppointment.status]}}</label>
                  <label class="labelText" for="medicalAppointment-status">Estado de la Cita</label>
                </div>
              </div>
-->
              <div v-if="page.sectionMap.medicalAppointmentInfo.fieldDefinitionMap.medicalAreaId && page.sectionMap.medicalAppointmentInfo.fieldDefinitionMap.medicalAreaId.visible" class="group">
                <div class="form-group mdl-textfield mdl-js-textfield mdl-textfield--floating-label" :class="{ 'form-group--error': $v.medicalAppointment.medicalAreaId.$error }">
                  <select :disabled="!page.sectionMap.medicalAppointmentInfo.fieldDefinitionMap.medicalAreaId.editable" v-model="$v.medicalAppointment.medicalAreaId.$model">
                    <option v-for="medicalArea in medicalAreas.items" v-bind:value="medicalArea.id">
                      {{ medicalArea.areaName }}
                    </option>
                  </select>
                  <label class="form__label labelText" for="medicalAppointment-medicalAreaId">{{$parent.getLabelValue( page.sectionMap.medicalAppointmentInfo.fieldDefinitionMap.medicalAreaId.label)}}</label>
                </div>
                <div class="error" v-if="!$v.medicalAppointment.medicalAreaId.required">Especialidad requerida</div>
              </div>
              <div v-if="page.sectionMap.medicalAppointmentInfo.fieldDefinitionMap.doctorId && page.sectionMap.medicalAppointmentInfo.fieldDefinitionMap.doctorId.visible" class="group">
                <div class="form-group mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
                  <select :disabled="!page.sectionMap.medicalAppointmentInfo.fieldDefinitionMap.medicalAreaId.editable" v-model="$v.medicalAppointment.doctorId.$model">
                    <option v-for="doctor in doctors.items" v-bind:value="doctor.id">
                      {{ doctor.fullName }}
                    </option>
                  </select>
                  <label class="form__label labelText" for="medicalAppointment-doctorId">{{$parent.getLabelValue( page.sectionMap.medicalAppointmentInfo.fieldDefinitionMap.doctorId.label)}}</label>
                </div>
                <div class="error" v-if="!$v.medicalAppointment.doctorId.required">Especialidad requerida</div>
              </div>
              <div  v-if="page.sectionMap.medicalAppointmentInfo.fieldDefinitionMap.notes && page.sectionMap.medicalAppointmentInfo.fieldDefinitionMap.notes.visible" class="groupFull">
                <div class="form-group mdl-textfield mdl-js-textfield mdl-textfield--floating-label" :class="{ 'form-group--error': $v.medicalAppointment.notes.$error }">
                  <input :disabled="!page.sectionMap.medicalAppointmentInfo.fieldDefinitionMap.notes.editable" class="mdl-textfield__input" type="text" name="medicalAppointment-notes" id="medicalAppointment-notes" v-model="$v.medicalAppointment.notes.$model" @input="forceUppercase($event, medicalAppointment, 'notes')" />
                  <label class="labelText" for="medicalAppointment-notes">{{$parent.getLabelValue( page.sectionMap.medicalAppointmentInfo.fieldDefinitionMap.notes.label)}}</label>
                </div>
                <div class="error" v-if="!$v.medicalAppointment.notes.maxLength">Cantidad de caracteres excedido</div>
              </div>
              <!-- medicalAppointment in attend -->
              <div v-if="page.sectionMap.medicalAppointmentInfo.fieldDefinitionMap.preferentialDiagnostic && page.sectionMap.medicalAppointmentInfo.fieldDefinitionMap.preferentialDiagnostic.visible" class="groupFull">
                <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
                  <select :disabled="!page.sectionMap.medicalAppointmentInfo.fieldDefinitionMap.preferentialDiagnostic.editable" v-model="medicalAppointment.preferentialDiagnostic">
                    <option v-for="(value, key, index) in preferentialDiagnostics" v-bind:value="key">
                      {{ value }}
                    </option>
                  </select>
                  <label class="labelText" for="medicalAppointment-visitReason">{{$parent.getLabelValue( page.sectionMap.medicalAppointmentInfo.fieldDefinitionMap.preferentialDiagnostic.label)}}</label>
                </div>
              </div>
              <div v-if="page.sectionMap.medicalAppointmentInfo.fieldDefinitionMap.diagnostic && page.sectionMap.medicalAppointmentInfo.fieldDefinitionMap.diagnostic.visible" class="groupFull">
                <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
                  <input :disabled="!page.sectionMap.medicalAppointmentInfo.fieldDefinitionMap.diagnostic.editable" class="mdl-textfield__input" type="text" name="medicalAppointment-diagnostic" id="medicalAppointment-diagnostic" v-model="medicalAppointment.diagnostic" @input="forceUppercase($event, medicalAppointment, 'diagnostic')" />
                  <label class="labelText" for="medicalAppointment-diagnostic">{{$parent.getLabelValue( page.sectionMap.medicalAppointmentInfo.fieldDefinitionMap.diagnostic.label)}}</label>
                </div>
              </div>
            </div>
            <div v-if="page && page.sectionMap && page.sectionMap.attachmentInfo && page.sectionMap.attachmentInfo.visible" class="subTitle">
              {{$parent.getLabelValue(page.sectionMap.attachmentInfo.label)}}
            </div>
            <div v-if="page && page.sectionMap && page.sectionMap.attachmentInfo && page.sectionMap.attachmentInfo.visible" class="twoCol">
              <ul v-if="page.sectionMap.attachmentInfo.fieldDefinitionMap.attachmentList && page.sectionMap.attachmentInfo.fieldDefinitionMap.attachmentList.visible"  id="attachment-list">
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
  name: 'MedicalAppointmentPage',
  data() {
    return {
      requestPage: 'medicalAppointmentInfo',
    }
  },
  validations: {
    medicalAppointment: {
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
  computed: {
    doctors() {
      return this.$store.state.general.doctors;
    },
    medicalAreas() {
      return this.$store.state.general.medicalAreas;
    },
    medicalAppointmentTypes() {
      return this.$store.state.general.properties.items.MEDICAL_APPOINTMENT.TYPE;
    },
    preferentialDiagnostics() {
      return this.$store.state.general.properties.items.MEDICAL_APPOINTMENT.PREFERENTIAL_DIAGNOSTIC;
    },
    medicalAppointment() {
      return this.$store.state.medicalAppointment.data;
    },
    page() {
      return this.$store.state.medicalAppointment.metadata.page;
    }
  },
  created: function() {
    console.log("MedicalAppointmentPage - created - begin");
    const dataContent = {
      "medicalAppointment": {
        "id": this.$route.params.medicalAppointmentId
      }
    }
    const {
      requestPage
    } = this;
    const {
      dispatch
    } = this.$store;
    dispatch('medicalAppointment/getById', {
        requestPage: requestPage,
        processName: 'RetrieveMedicalAppointmentInfo',
        dataContent: dataContent
    });
    console.log("MedicalAppointmentPage - created - end");
  },
  mounted: function() {
    console.log("MedicalAppointmentPage - mounted - begin");
    let selfVue = this
    console.log("MedicalAppointmentPage - mounted - end");
  },
  methods: {
    getPatientInfo: function() {
      console.log("MedicalAppointmentPage - method - getPatientInfo - begin");
      const dataContent = {
        "documentNumber": this.medicalAppointment.documentNumber
      }
      const {
        requestPage
      } = this;
      const {
        dispatch
      } = this.$store;
      dispatch('medicalAppointment/getPatientInfoByDocumentNumberOnMedAppointment', {
        requestPage: requestPage,
        processName: 'GetPatientByDocumentNumber',
        dataContent: dataContent
      });
      console.log("MedicalAppointmentPage - method - getPatientInfo - end");
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
    executeAction: function(button) {
      let selfVue = this
      switch (button.buttonType) {
        case 1:
          var routeObject = {};
          var jsonString = button.eventDefinition;
          var eventArray = button.eventDefinition.match(/\${{(.*?)}}/g);
          for (var i = 0, len = eventArray.length; i < len; i++) {
            var dataRouteVariable = eventArray[i];
            jsonString = jsonString.replace(dataRouteVariable, eval(dataRouteVariable.match(/\$\{\{([^)]+)\}\}/)[1]));
          }
          routeObject = JSON.parse(jsonString);
          this.$router.push(routeObject);
          break;
        case 2:
          eval(button.eventDefinition);
          break;
      }

    },
    saveObjectState: function() {
      console.log("MedicalAppointmentPage - method - saveObjectState - begin");
      const dataContent = {
        "medicalAppointment": this.medicalAppointment
      }
      const {
        requestPage
      } = this;
      const {
        dispatch
      } = this.$store;
      var returnRoute = { name: 'BrowsePage', params: { browseName: 'allMedAppHeaderView', entityId: 'null' } };
      this.$v.$touch()
      if (this.$v.$invalid) {
        selfVue.$parent.errorMessage("Por favor complete los campos requeridos")
      } else {
        dispatch('medicalAppointment/saveEntity', {
          vm: this,
          requestPage: requestPage,
          processName: 'RegisterMedicalAppointment',
          dataContent: dataContent,
          returnRoute: returnRoute
        });
      }
      console.log("MedicalAppointmentPage - method - saveObjectState - end");
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
