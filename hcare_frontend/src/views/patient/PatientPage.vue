<template>
<div class="content-container">
  <section data-ui-view="" class="view-container animate-fade-up">
    <section v-if="page && page.sectionMap" class="page">
      <div class="titleForm">{{$parent.getLabelValue(page.label)}}</div>
      <div class="row ui-section contentMain">
        <div class="btnMoreActions" onClick="openThreePoint()">
          <div class="btnMore">
            <i class="fas fa-ellipsis-v"></i>
          </div>
          <div class="moreOptions" id="moreOptions">
            <div class="menuContent">
            
              <div v-for="button in page.pageButtons"  v-if="button.visible" v-on:click="executeAction(button)" class="link">
                <div class="icon"><i class="button.icon"></i></div>
                <div class="text">{{$parent.getLabelValue(button.label)}}</div>
              </div>
            </div>
          </div>
        </div>
        <div class="formBox">
          <div class="headerFacNew">
            <div v-if="page.sectionMap.patientInfo && page.sectionMap.patientInfo.visible && page.sectionMap.patientInfo.fieldDefinitionMap" class="subTitle">{{$parent.getLabelValue(page.sectionMap.patientInfo.label)}}</div>
            <div v-if="page.sectionMap.patientInfo && page.sectionMap.patientInfo.visible && page.sectionMap.patientInfo.fieldDefinitionMap" class="twoCol">
              <div v-if="page.sectionMap.patientInfo.fieldDefinitionMap.documentType && page.sectionMap.patientInfo.fieldDefinitionMap.documentType.visible" class="group">
                <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
                  <select v-model="patient.documentType">
                    <option v-for="(value, key, index) in documentTypes" v-bind:value="key">
                      {{ value }}
                    </option>
                  </select>
                  <label class="labelText" for="patient-documentType">{{$parent.getLabelValue( page.sectionMap.patientInfo.fieldDefinitionMap.documentType.label)}}</label>
                </div>
              </div>
              <div v-if="page.sectionMap.patientInfo.fieldDefinitionMap.documentNumber && page.sectionMap.patientInfo.fieldDefinitionMap.documentNumber.visible" class="group">
                <div class="form-group mdl-textfield mdl-js-textfield mdl-textfield--floating-label" :class="{ 'form-group--error': $v.patient.documentNumber.$error }">
                  <input class="form__input mdl-textfield__input" type="text" name="patient-documentNumber" id="patient-documentNumber" v-model.trim="$v.patient.documentNumber.$model" @change="getPatientInfo()" />
                  <label class="form__label labelText" for="patient-documentNumber">{{$parent.getLabelValue( page.sectionMap.patientInfo.fieldDefinitionMap.documentNumber.label)}}</label>
                </div>
                <div class="error" v-if="!$v.patient.documentNumber.required">Numero de Documento requerido</div>
              </div>
              <div v-if="page.sectionMap.patientInfo.fieldDefinitionMap.firstName && page.sectionMap.patientInfo.fieldDefinitionMap.firstName.visible" class="group">
                <div class="form-group mdl-textfield mdl-js-textfield mdl-textfield--floating-label" :class="{ 'form-group--error': $v.patient.firstName.$error }">
                  <input class="form__input mdl-textfield__input" type="text" name="patient-firstName" id="patient-firstName" v-model="$v.patient.firstName.$model" />
                  <label class="form__label labelText" for="patient-firstName">{{$parent.getLabelValue( page.sectionMap.patientInfo.fieldDefinitionMap.firstName.label)}}</label>
                </div>
                <div class="error" v-if="!$v.patient.firstName.required">Nombres requeridos</div>
              </div>
              <div v-if="page.sectionMap.patientInfo.fieldDefinitionMap.lastName && page.sectionMap.patientInfo.fieldDefinitionMap.lastName.visible" class="group">
                <div class="form-group mdl-textfield mdl-js-textfield mdl-textfield--floating-label" :class="{ 'form-group--error': $v.patient.lastName.$error }">
                  <input class="form__input mdl-textfield__input" type="text" name="patient-lastName" id="patient-lastName" v-model="$v.patient.lastName.$model" />
                  <label class="form__label labelText" for="patient-lastName">{{$parent.getLabelValue( page.sectionMap.patientInfo.fieldDefinitionMap.lastName.label)}}</label>
                </div>
                <div class="error" v-if="!$v.patient.lastName.required">Apellidos requerido</div>
              </div>
              <div v-if="page.sectionMap.patientInfo.fieldDefinitionMap.birthday && page.sectionMap.patientInfo.fieldDefinitionMap.birthday.visible" class="group">
                <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
                  <datepicker v-model="patient.birthday"></datepicker>
                  <label class="labelText" for="patient-birthday">{{$parent.getLabelValue( page.sectionMap.patientInfo.fieldDefinitionMap.birthday.label)}}</label>
                </div>
              </div>
              <div v-if="page.sectionMap.patientInfo.fieldDefinitionMap.civilStatus && page.sectionMap.patientInfo.fieldDefinitionMap.civilStatus.visible" class="group">
                <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
                  <select v-model="patient.civilStatus">
                    <option v-for="(value, key, index) in civilStatus" v-bind:value="key">
                      {{ value }}
                    </option>
                  </select>
                  <label class="labelText" for="patient-civilStatus">{{$parent.getLabelValue( page.sectionMap.patientInfo.fieldDefinitionMap.civilStatus.label)}}</label>
                </div>
              </div>
              <div v-if="page.sectionMap.patientInfo.fieldDefinitionMap.phoneNumber && page.sectionMap.patientInfo.fieldDefinitionMap.phoneNumber.visible" class="group">
                <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
                  <input class="mdl-textfield__input" type="text" name="patient-phoneNumber" id="patient-phoneNumber" v-model="patient.phoneNumber" />
                  <label class="labelText" for="patient-phoneNumber">{{$parent.getLabelValue( page.sectionMap.patientInfo.fieldDefinitionMap.phoneNumber.label)}}</label>
                </div>
              </div>
              <div v-if="page.sectionMap.patientInfo.fieldDefinitionMap.celPhoneNumber && page.sectionMap.patientInfo.fieldDefinitionMap.celPhoneNumber.visible" class="group">
                <div class="form-group mdl-textfield mdl-js-textfield mdl-textfield--floating-label" :class="{ 'form-group--error': $v.patient.celPhoneNumber.$error }">
                  <input class="form__input mdl-textfield__input" type="text" name="patient-celPhoneNumber" id="patient-celPhoneNumber" v-model.trim="$v.patient.celPhoneNumber.$model" />
                  <label class="form__label labelText" for="patient-celPhoneNumber">{{$parent.getLabelValue( page.sectionMap.patientInfo.fieldDefinitionMap.celPhoneNumber.label)}}</label>
                </div>
                <div class="error" v-if="!$v.patient.celPhoneNumber.required">Numero de Celular requerido</div>
              </div>
              <div v-if="page.sectionMap.patientInfo.fieldDefinitionMap.emailAddress && page.sectionMap.patientInfo.fieldDefinitionMap.emailAddress.visible" class="group">
                <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
                  <input class="mdl-textfield__input" type="text" name="patient-emailAddress" id="patient-emailAddress" v-model="patient.emailAddress" />
                  <label class="labelText" for="patient-emailAddress">{{$parent.getLabelValue( page.sectionMap.patientInfo.fieldDefinitionMap.emailAddress.label)}}</label>
                </div>
              </div>
              <div v-if="page.sectionMap.patientInfo.fieldDefinitionMap.addressDistrict && page.sectionMap.patientInfo.fieldDefinitionMap.addressDistrict.visible" class="group">
                <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
                  <select v-model="patient.addressDistrict">
                    <option v-for="(value, key, index) in districts" v-bind:value="key">
                      {{ value }}
                    </option>
                  </select>
                  <label class="labelText" for="patient-addressDistrict">{{$parent.getLabelValue( page.sectionMap.patientInfo.fieldDefinitionMap.addressDistrict.label)}}</label>
                </div>
              </div>
              <div v-if="page.sectionMap.patientInfo.fieldDefinitionMap.address && page.sectionMap.patientInfo.fieldDefinitionMap.address.visible" class="groupFull">
                <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
                  <input class="mdl-textfield__input" type="text" name="patient-address" id="patient-address" v-model="patient.address" />
                  <label class="labelText" for="patient-address">{{$parent.getLabelValue( page.sectionMap.patientInfo.fieldDefinitionMap.address.label)}}</label>
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
  name: 'PatientPage',
  data() {
    return {
      requestPage: 'patientInfo',
    }
  },
  validations: {
    patient: {
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
  computed: {
    documentTypes() {
      return this.$store.state.general.properties.items.GENERAL.DOCUMENT_TYPE;
    },
    civilStatus() {
      return this.$store.state.general.properties.items.GENERAL.CIVIL_STATUS;
    },
    districts() {
      return this.$store.state.general.properties.items.GENERAL.DISTRICT;
    },
    patient() {
      return this.$store.state.patient.data;
    },
    page() {
      return this.$store.state.patient.metadata.page;
    }
  },
  created: function() {
    console.log("PatientPage - created - begin");
    const dataContent = {
      "patient": {
        "id": this.$route.params.patientId
      }
    }
    const {
      requestPage
    } = this;
    const {
      dispatch
    } = this.$store;
    var processName = '';
    dispatch('patient/getById', {
      requestPage: requestPage,
      processName: 'RetrievePatientInfo',
      dataContent: dataContent
    });
    console.log("PatientPage - created - end");
  },
  methods: {
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
              params: {
                browseType: 'allPatients',
                entityId: 'null'
              }
            })
          }, 1000)
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
          if (response.data != null && response.data !== "") {
            selfVue.patient = response.data
          }
          //this.patient.birthday = this.frontEndDateFormat(this.patient.birthday)
        })
        .catch(error => {
          console.log(error)
        })
    },
    saveObjectState: function() {
      console.log("MedicalAppointmentPage - method - saveObjectState - begin");
      const dataContent = {
        "patient": this.patient
      }
      const {
        requestPage
      } = this;
      const {
        dispatch
      } = this.$store;
      var returnRoute = {
        name: 'BrowsePage',
        params: {
          browseName: 'allPatients',
          entityId: 'null'
        }
      };
      this.$v.$touch()
      if (this.$v.$invalid) {
        selfVue.$parent.errorMessage("Por favor complete los campos requeridos")
      } else {
        dispatch('patient/saveEntity', {
          requestPage: requestPage,
          processName: 'SavePatient',
          dataContent: dataContent,
          returnRoute: returnRoute,
        });
      }
      console.log("MedicalAppointmentPage - method - saveObjectState - end");
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
        name: 'BrowsePage',
        params: {
          browseName: 'medicalAppointmentsByPatient',
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
