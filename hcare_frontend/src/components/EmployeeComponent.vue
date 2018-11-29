<template>
<div class="content-container">
  <section data-ui-view="" class="view-container animate-fade-up">
    <section class="page">
      <div class="titleForm">
        Colaborador {{employee.employeeCode}}
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
              Datos del Colaborador
            </div>
            <div class="twoCol">
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
              <!--
              <div class="group">
                <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
                  <input class="mdl-textfield__input" type="text" name="employee-employeeCode" id="employee-employeeCode" v-model="employee.employeeCode" />
                  <label class="labelText" for="employee-employeeCode">Codigo de Colaborador</label>
                </div>
              </div>
            -->
              <div class="group">
                <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
                  <input class="mdl-textfield__input" type="text" name="employee-emailAddress" id="employee-emailAddress" v-model="employee.emailAddress" />
                  <label class="labelText" for="employee-emailAddress">Email</label>
                </div>
              </div>
              <!--
              <div class="group">
                <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
                  <input class="mdl-textfield__input" type="password" name="employee-password" id="employee-password" v-model="employee.password" />
                  <label class="labelText" for="employee-password">Password</label>
                </div>
              </div>
              -->
              <div class="group">
                <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
                  <input class="mdl-textfield__input" type="text" name="employee-firstName" id="employee-firstName" v-model="employee.firstName" />
                  <label class="labelText" for="employee-firstName">Nombres</label>
                </div>
              </div>
              <div class="group">
                <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
                  <input class="mdl-textfield__input" type="text" name="employee-lastName" id="employee-lastName" v-model="employee.lastName" />
                  <label class="labelText" for="employee-lastName">Apellidos</label>
                </div>
              </div>
              <div class="group">
                <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
                  <input class="mdl-textfield__input" type="text" name="employee-phoneNumber" id="employee-phoneNumber" v-model="employee.phoneNumber" />
                  <label class="labelText" for="employee-phoneNumber">Telefono</label>
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
  name: 'EmployeeComponent',
  data() {
    return {
      employee: {
        title:1,
      },
      titles: [
        {titleId:1,titleName:'Doctor(a)'},
        {titleId:2,titleName:'Terapista'},
        {titleId:3,titleName:'Enfermer(a)'},
        {titleId:4,titleName:'Tecnico(a)'},
        {titleId:5,titleName:'Personal Administrativo'},
        {titleId:6,titleName:'Auxiliar'},
      ],
    }
  },
  components: {
    Datepicker
  },
  created: function() {
    console.log("EmployeeComponent");
    if (this.$route.params.employeeId !== 'null') {
      var urlEmployeeInfo = this.$parent.backendUrl + 'employees/' + this.$route.params.employeeId
      axios.get(urlEmployeeInfo)
        .then(response => {
          this.employee = response.data
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
      const url = this.$parent.backendUrl + 'employees'
      //Add patientId to medicalHistory
      let selfVue = this
      axios.post(url, this.employee)
        .then(response => {
          this.employee = response.data
          selfVue.$parent.sucessMessage()
        })
        .catch(error => {
          console.log(error)
          selfVue.$parent.errorMessage()
        })
      // this.$router.push({ name: '/'})
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
