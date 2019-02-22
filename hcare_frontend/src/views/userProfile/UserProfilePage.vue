<template>
<div class="content-container">
  <section data-ui-view="" class="view-container animate-fade-up">
    <section class="page">
      <div class="titleForm">
        Coloborador
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
              <!-- TODO
              <div class="group">
                <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
                  <select v-model="employee.title">
                    <option v-for="title in titles" v-bind:value="title.titleId">
                      {{ title.titleName }}
                    </option>
                  </select>
                  <label class="labelText" for="employee-title">Especialidad</label>
                </div>
              </div>
              -->
              <!--
              <div class="group">
                <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
                  <input class="mdl-textfield__input" type="text" name="employee-employeeCode" id="employee-employeeCode" v-model="employee.employeeCode" />
                  <label class="labelText" for="employee-employeeCode">Codigo de Colaborador</label>
                </div>
              </div>
            -->
              <!--DELETE
            <div class="group">
              <div class="form-group mdl-textfield mdl-js-textfield mdl-textfield--floating-label" :class="{ 'form-group--error': $v.medicalAppointment.documentNumber.$error }">
                <input class="form__input mdl-textfield__input" type="text" name="medicalAppointment-documentNumber" id="medicalAppointment-documentNumber" v-model.trim="$v.medicalAppointment.documentNumber.$model" @change="getPatientInfo()" />
                <label class="form__label labelText" for="medicalAppointment-documentNumber">Numero de Documento</label>
              </div>
              <div class="error" v-if="!$v.medicalAppointment.documentNumber.required">Numero de Documento requerido</div>
            </div>
          -->
              <div v-if="page.sectionMap.generalInfo.fieldDefinitionMap.emailAddress && page.sectionMap.generalInfo.fieldDefinitionMap.emailAddress.visible" class="group">
                <div class="form-group mdl-textfield mdl-js-textfield mdl-textfield--floating-label" :class="{ 'form-group--error': $v.userProfile.emailAddress.$error }">
                  <input class="form__input mdl-textfield__input" type="text" name="userProfile-emailAddress" id="userProfile-emailAddress" v-model="$v.userProfile.emailAddress.$model" />
                  <label class="form__label labelText" for="userProfile-emailAddress">Email</label>
                </div>
                <div class="error" v-if="!$v.userProfile.emailAddress.required">Email requerido</div>
                <div class="error" v-if="!$v.userProfile.emailAddress.email">Tiene que ser un correo de electronico valido</div>
              </div>
              <!--
              <div class="group">
                <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
                  <input class="mdl-textfield__input" type="password" name="employee-password" id="employee-password" v-model="employee.password" />
                  <label class="labelText" for="employee-password">Password</label>
                </div>
              </div>
              -->
              <div v-if="page.sectionMap.generalInfo.fieldDefinitionMap.firstName && page.sectionMap.generalInfo.fieldDefinitionMap.firstName.visible" class="group">
                <div class="form-group mdl-textfield mdl-js-textfield mdl-textfield--floating-label" :class="{ 'form-group--error': $v.userProfile.firstName.$error }">
                  <!-- TODO implement toUpperCase
                  <input class="mdl-textfield__input" type="text" name="userProfile-firstName" id="userProfile-firstName" v-model.trim="$v.userProfile.firstName.$model" @input="forceUppercase($event, userProfile, 'firstName')" />-->
                  <input class="mdl-textfield__input" type="text" name="userProfile-firstName" id="userProfile-firstName" v-model.trim="$v.userProfile.firstName.$model" />
                  <label class="labelText" for="userProfile-firstName">Nombres</label>
                </div>
                <div class="error" v-if="!$v.userProfile.firstName.required">Nombres requeridos</div>
              </div>
              <div v-if="page.sectionMap.generalInfo.fieldDefinitionMap.lastName && page.sectionMap.generalInfo.fieldDefinitionMap.lastName.visible" class="group">
                <div class="form-group mdl-textfield mdl-js-textfield mdl-textfield--floating-label" :class="{ 'form-group--error': $v.userProfile.lastName.$error }">
                  <!-- TODO implement toUpperCase
                  <input class="form__input mdl-textfield__input" type="text" name="userProfile-lastName" id="userProfile-lastName" v-model.trim="$v.userProfile.lastName.$model" @input="forceUppercase($event, userProfile, 'lastName')" />-->
                  <input class="form__input mdl-textfield__input" type="text" name="userProfile-lastName" id="userProfile-lastName" v-model.trim="$v.userProfile.lastName.$model" />
                  <label class="form__label labelText" for="userProfile-lastName">Apellidos</label>
                </div>
                <div class="error" v-if="!$v.userProfile.lastName.required">Apellidos requeridos</div>
              </div>
              <div v-if="page.sectionMap.generalInfo.fieldDefinitionMap.phoneNumber && page.sectionMap.generalInfo.fieldDefinitionMap.phoneNumber.visible" class="group">
                <div class="form-group mdl-textfield mdl-js-textfield mdl-textfield--floating-label" :class="{ 'form-group--error': $v.userProfile.phoneNumber.$error }">
                  <input class="form__input mdl-textfield__input" type="text" name="userProfile-phoneNumber" id="userProfile-phoneNumber" v-model.trim="$v.userProfile.phoneNumber.$model" />
                  <label class="form__label labelText" for="userProfile-phoneNumber">Celular</label>
                </div>
                <div class="error" v-if="!$v.userProfile.phoneNumber.required">Celular requerido</div>
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
//Validations
import {
  required,
  minLength,
  between,
  email,
  maxLength
} from 'vuelidate/lib/validators'

let installed = false

export default {
  name: 'UserProfilePage',
  data() {
    return {
      requestPage: 'userProfileInfo',
      defaultUserProfile: {
        title: 1,
        emailAddress: '',
        lastName: '',
        firstName: '',
        phoneNumber: '',
      },
      titles: [{
          titleId: 1,
          titleName: 'DOCTOR(A)'
        },
        {
          titleId: 2,
          titleName: 'TERAPISTA'
        },
        {
          titleId: 3,
          titleName: 'ENFERMERO(A)'
        },
        {
          titleId: 4,
          titleName: 'TECNICO(A)'
        },
        {
          titleId: 5,
          titleName: 'PERSONAL ADMINISTRATIVO'
        },
        {
          titleId: 6,
          titleName: 'AUXILIAR'
        },
      ],
    }
  },
  validations: {
    userProfile: {
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
      phoneNumber: {
        required,
      },
    }
  },
  components: {
    Datepicker
  },
  computed: {
    userProfile() {
      return this.$store.state.userProfile.data;
    },
    page() {
      return this.$store.state.userProfile.metadata.page;
    }
  },
  created: function() {
    console.log("MedicalAppointmentPage - created - begin");
    const dataContent = {
      "userProfile": {
        "id": this.$route.params.userProfileId
      }
    }
    const {
      requestPage
    } = this;
    const {
      dispatch
    } = this.$store;
    if (this.$route.params.userProfileId !== 'null') {
      dispatch('userProfile/getById', {
        requestPage: requestPage,
        processName: 'RetrieveUserProfileInfo',
        dataContent: dataContent
      });
    } else {
      this.$store.state.userProfile.data = this.defaultUserProfile;
    }
    console.log("MedicalAppointmentPage - created - end");
  },
  methods: {
    goBackBrowse: function() {
      this.$router.push({
        name: 'BrowseComponent'
      })
    },
    saveObjectState: function() {
      const url = this.$parent.backendUrl + 'employees'
      let selfVue = this
      this.$v.$touch()
      if (this.$v.$invalid) {
        selfVue.$parent.errorMessage("Por favor complete los campos requeridos")
      } else {
        axios.post(url, this.employee)
          .then(response => {
            this.employee = response.data
            selfVue.$parent.sucessMessage()
            setTimeout(() => {
              this.$router.push({
                name: 'BrowseComponent',
                params: { browseType: 'allEmployees', entityId: 'null' }
              })
            },1000)
          })
          .catch(error => {
            console.log(error)
            selfVue.$parent.errorMessage()
          })
      }
    },
    frontEndDateFormat: function(date) {
      return moment(date, 'YYYY-MM-DDTHH:mm:ss.fff Z').format('DD/MM/YYYY')
    },
    backEndDateFormat: function(date) {
      return moment(date, 'DD/MM/YYYY').format('YYYY-MM-DDTHH:mm:ss.sssZ')
    },

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
