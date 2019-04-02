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
            <div v-if="page.sectionMap.userProfileInfo && page.sectionMap.userProfileInfo.visible && page.sectionMap.userProfileInfo.fieldDefinitionMap" class="subTitle">
              {{$parent.getLabelValue(page.sectionMap.userProfileInfo.label)}}
            </div>
            <div v-if="page.sectionMap.userProfileInfo && page.sectionMap.userProfileInfo.visible && page.sectionMap.userProfileInfo.fieldDefinitionMap" class="twoCol">
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
              <div v-if="page.sectionMap.userProfileInfo.fieldDefinitionMap.username && page.sectionMap.userProfileInfo.fieldDefinitionMap.username.visible" class="group">
                <div class="form-group mdl-textfield mdl-js-textfield mdl-textfield--floating-label" :class="{ 'form-group--error': $v.userProfile.username.$error }">
                  <!-- TODO implement toUpperCase
                  <input class="mdl-textfield__input" type="text" name="userProfile-firstName" id="userProfile-firstName" v-model.trim="$v.userProfile.firstName.$model" @input="forceUppercase($event, userProfile, 'firstName')" />-->
                  <input class="mdl-textfield__input" type="text" name="userProfile-username" id="userProfile-firstName" v-model.trim="$v.userProfile.username.$model" />
                  <label class="labelText" for="userProfile-username">{{$parent.getLabelValue( page.sectionMap.userProfileInfo.fieldDefinitionMap.username.label)}}</label>
                </div>
                <div class="error" v-if="!$v.userProfile.username.required">Nombre de usuario requerido</div>
              </div>
              <!--DELETE
            <div class="group">
              <div class="form-group mdl-textfield mdl-js-textfield mdl-textfield--floating-label" :class="{ 'form-group--error': $v.medicalAppointment.documentNumber.$error }">
                <input class="form__input mdl-textfield__input" type="text" name="medicalAppointment-documentNumber" id="medicalAppointment-documentNumber" v-model.trim="$v.medicalAppointment.documentNumber.$model" @change="getPatientInfo()" />
                <label class="form__label labelText" for="medicalAppointment-documentNumber">Numero de Documento</label>
              </div>
              <div class="error" v-if="!$v.medicalAppointment.documentNumber.required">Numero de Documento requerido</div>
            </div>
          -->
              <div v-if="page.sectionMap.userProfileInfo.fieldDefinitionMap.emailAddress && page.sectionMap.userProfileInfo.fieldDefinitionMap.emailAddress.visible" class="group">
                <div class="form-group mdl-textfield mdl-js-textfield mdl-textfield--floating-label" :class="{ 'form-group--error': $v.userProfile.emailAddress.$error }">
                  <input class="form__input mdl-textfield__input" type="text" name="userProfile-emailAddress" id="userProfile-emailAddress" v-model="$v.userProfile.emailAddress.$model" />
                  <label class="form__label labelText" for="userProfile-emailAddress">{{$parent.getLabelValue( page.sectionMap.userProfileInfo.fieldDefinitionMap.emailAddress.label)}}</label>
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
              <div v-if="page.sectionMap.userProfileInfo.fieldDefinitionMap.firstName && page.sectionMap.userProfileInfo.fieldDefinitionMap.firstName.visible" class="group">
                <div class="form-group mdl-textfield mdl-js-textfield mdl-textfield--floating-label" :class="{ 'form-group--error': $v.userProfile.firstName.$error }">
                  <!-- TODO implement toUpperCase
                  <input class="mdl-textfield__input" type="text" name="userProfile-firstName" id="userProfile-firstName" v-model.trim="$v.userProfile.firstName.$model" @input="forceUppercase($event, userProfile, 'firstName')" />-->
                  <input class="mdl-textfield__input" type="text" name="userProfile-firstName" id="userProfile-firstName" v-model.trim="$v.userProfile.firstName.$model" />
                  <label class="labelText" for="userProfile-firstName">{{$parent.getLabelValue( page.sectionMap.userProfileInfo.fieldDefinitionMap.firstName.label)}}</label>
                </div>
                <div class="error" v-if="!$v.userProfile.firstName.required">Nombres requeridos</div>
              </div>
              <div v-if="page.sectionMap.userProfileInfo.fieldDefinitionMap.lastName && page.sectionMap.userProfileInfo.fieldDefinitionMap.lastName.visible" class="group">
                <div class="form-group mdl-textfield mdl-js-textfield mdl-textfield--floating-label" :class="{ 'form-group--error': $v.userProfile.lastName.$error }">
                  <!-- TODO implement toUpperCase
                  <input class="form__input mdl-textfield__input" type="text" name="userProfile-lastName" id="userProfile-lastName" v-model.trim="$v.userProfile.lastName.$model" @input="forceUppercase($event, userProfile, 'lastName')" />-->
                  <input class="form__input mdl-textfield__input" type="text" name="userProfile-lastName" id="userProfile-lastName" v-model.trim="$v.userProfile.lastName.$model" />
                  <label class="form__label labelText" for="userProfile-lastName">{{$parent.getLabelValue( page.sectionMap.userProfileInfo.fieldDefinitionMap.lastName.label)}}</label>
                </div>
                <div class="error" v-if="!$v.userProfile.lastName.required">Apellidos requeridos</div>
              </div>
              <div v-if="page.sectionMap.userProfileInfo.fieldDefinitionMap.phoneNumber && page.sectionMap.userProfileInfo.fieldDefinitionMap.phoneNumber.visible" class="group">
                <div class="form-group mdl-textfield mdl-js-textfield mdl-textfield--floating-label" :class="{ 'form-group--error': $v.userProfile.phoneNumber.$error }">
                  <input class="form__input mdl-textfield__input" type="text" name="userProfile-phoneNumber" id="userProfile-phoneNumber" v-model.trim="$v.userProfile.phoneNumber.$model" />
                  <label class="form__label labelText" for="userProfile-phoneNumber">{{$parent.getLabelValue( page.sectionMap.userProfileInfo.fieldDefinitionMap.phoneNumber.label)}}</label>
                </div>
                <div class="error" v-if="!$v.userProfile.phoneNumber.required">Celular requerido</div>
              </div>
            </div>
            <!-- <div v-if="page.sectionMap.userProfileRolesInfo && page.sectionMap.userProfileRolesInfo.visible && page.sectionMap.userProfileRolesInfo.fieldDefinitionMap" class="twoCol"> -->
              <!-- <div v-if="page.sectionMap.userProfileRolesInfo.fieldDefinitionMap.phoneNumber && page.sectionMap.userProfileRolesInfo.fieldDefinitionMap.phoneNumber.visible" class="group"> -->
            <div v-if="roles && userProfile.selectedRoles">
              <div>
                <v-list-tile v-for="(role, index) in roles" :key="role.id">
                  <v-list-tile-content>
                    <v-checkbox :value="role.id" :key="role.name" :label="role.name" v-model="userProfile.selectedRoles"></v-checkbox>
                  </v-list-tile-content>
                </v-list-tile>
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
    }
  },
  validations: {
    userProfile: {
      username:{
        required,
      },
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
    roles() {
      return this.$store.state.general.roles.items;
    },
    page() {
      return this.$store.state.userProfile.metadata.page;
    }
  },
  created: function() {
    console.log("UserProfilePage - created - begin");
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
    dispatch('userProfile/getById', {
      vm: this,
      requestPage: requestPage,
      processName: 'RetrieveUserProfileInfo',
      dataContent: dataContent
    });
    console.log("UserProfilePage - created - end");
  },
  methods: {
    goBackBrowse: function() {
      this.$router.push({
        name: 'BrowseComponent'
      })
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
      console.log("UserProfilePage - method - saveObjectState - begin");
      this.userProfile.roles = [];
      let selfVue = this;
      this.roles.forEach(function(role) {
        if(selfVue.userProfile.selectedRoles.includes(role.id)){
            selfVue.userProfile.roles.push(role);
        }
      });
      delete this.userProfile.selectedRoles;
      const dataContent = {
        "userProfile": this.userProfile
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
          browseName: 'allUserProfiles',
          entityId: 'null'
        }
      };
      this.$v.$touch()
      if (this.$v.$invalid) {
        dispatch('alert/warning', "Por favor complete los campos requeridos");
      } else {
        dispatch('userProfile/saveEntity', {
          vm: this,
          requestPage: requestPage,
          processName: 'SaveUserProfile',
          dataContent: dataContent,
          returnRoute: returnRoute
        });
      }
      console.log("UserProfilePage - method - saveObjectState - end");
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
