<template>
  <div class="content-container">
      <section data-ui-view="" class="view-container animate-fade-up">
        <section class="page">
          <div class="titleForm">
            Detalles de Paciente
          </div>
          <div class="row ui-section">
            <div class="formBox">
              <div class="headerFacNew">
                <div class="twoCol">
                  <div class="group">
                    <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
                      <input class="mdl-textfield__input" type="text" name="displayObject-patientCode" id="displayObject-patientCode" v-model="displayObject.patientCode" />
                      <label class="labelText" for="displayObject-patientCode">Codigo Paciente</label>
                    </div>
                  </div>
                  <div class="group">
                    <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
                      <input class="mdl-textfield__input" type="text" name="displayObject-firstName" id="displayObject-firstName" v-model="displayObject.firstName" />
                      <label class="labelText" for="displayObject-firstName">Nombres</label>
                    </div>
                  </div>
                  <div class="group">
                    <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
                      <input class="mdl-textfield__input" type="text" name="displayObject-lastName" id="displayObject-lastName" v-model="displayObject.lastName" />
                      <label class="labelText" for="displayObject-lastName">Apellidos</label>
                    </div>
                  </div>     
                  <div class="group">
                    <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
                      <input class="mdl-textfield__input" type="text" name="displayObject-birthday" id="displayObject-birthday" v-model="displayObject.birthday" />
                      <label class="labelText" for="displayObject-birthday">Fecha de Nacimiento</label>
                    </div>
                  </div>
                  <div class="groupFull">
                    <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
                      <input class="mdl-textfield__input" type="text" name="displayObject-emailAddress" id="displayObject-emailAddress" v-model="displayObject.emailAddress" />
                      <label class="labelText" for="displayObject-emailAddress">Correo Electronico</label>
                    </div>
                  </div>
                </div>
              </div>
                <div class="btnOptions">
                <div class="boxOpt">
                  <button v-on:click="saveObjectState()" class="md-button md-accent" type="button" >Guardar</button>
                  <button v-on:click="goBackBrowse()" class="md-button md-warn" type="button" >Cancelar</button>
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
  data () {
    return {
        displayObject:{},
    }
  },
  created: function () {
    
    if (this.$route.params.id !== 'null') {
        const url = this.$parent.backendUrl+'patients/' + this.$route.params.id  
      axios.get(url)
        .then(response => {
          this.displayObject = response.data
          this.displayObject.birthday = this.frontEndDateFormat(this.displayObject.birthday)
        })
        .catch(error => {
          console.log(error)
        })
    }
  },
  methods: {
    goBackBrowse: function () {
      this.$router.push({ name: 'BrowseComponent'})
    },
    saveObjectState: function () {
      
    const url = this.$parent.backendUrl+'patients'
      this.displayObject.birthday = this.backEndDateFormat(this.displayObject.birthday)
      axios.post(url, this.displayObject)
        .then(response => {
          this.displayObject = response.data
          this.displayObject.birthday = this.frontEndDateFormat(this.displayObject.birthday)
        })
        .catch(error => {
          this.displayObject.birthday = this.frontEndDateFormat(this.displayObject.birthday)
          console.log(error)
        })
      // this.$router.push({ name: '/'})
    },
    frontEndDateFormat: function (date) {
      return moment(date, 'YYYY-MM-DDTHH:mm:ss.fff Z').format('DD/MM/YYYY')
    },
    backEndDateFormat: function (date) {
      return moment(date, 'DD/MM/YYYY').format('YYYY-MM-DDTHH:mm:ss.sssZ')
    }
  },
  filters: {
    capitalize: function (value) {
      if (!value) return ''
      value = value.toString()
      return value.charAt(0).toUpperCase() + value.slice(1)
    },
    round
  }
}
</script>
