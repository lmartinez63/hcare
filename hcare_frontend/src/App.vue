<template>
  <div id="app">
    <!--img src="./assets/logo.png"-->
    <header id="header" class="header-container header-fixed bg-white" >
        <div class="logo">
            <img src="/static/img/logo.png">
        </div>
        <div class="top-nav">
            <div class="username">
                Usuario: Admin
            </div>
        </div>
    </header>
    <div class="main-container">
        <aside id="nav-container" class="nav-container nav-fixed bg-dark">
            <div class="nav-wrapper">
                <div class="slimScrollDiv" style="position: relative; overflow: hidden; width: auto; height: 100%;">
                    <div class="contenedor-menu">
                        <ul class="menu">
                            <li><router-link :to="{ name: 'DashboardComponent'}"><i class="icono izquierda fa fa-home"></i>Tablero</router-link></li>
                            <li><a><i class="icono izquierda fa fa-star"></i>Administration de Pacientes<i class="icono derecha fa fa-chevron-down"></i></a>
                                <ul>
                                    <li><router-link :to="{ name: 'BrowseComponent', params: { browseType: 'allPatients', entityId: 'null' }}">Listado de Pacientes</router-link></li>
                                    <li><router-link :to="{ name: 'PatientComponent', params: { id: 'null' }}">Registrar Paciente</router-link></li>
                                </ul>
                            </li>
                            <li><a href="#"><i class="icono izquierda fa fa-share-alt"></i>Administracion de Historias Medicas<i class="icono derecha fa fa-chevron-down"></i></a>
                                <ul>
                                    <li><router-link :to="{ name: 'BrowseComponent', params: { browseType: 'allMedicalHistories', entityId: 'null' }}">Listado de Historias Medicas</router-link></li>
                                </ul>
                            </li>
                            <li><a href="#"><i class="icono izquierda fa fa-share-alt"></i>Administracion de Citas<i class="icono derecha fa fa-chevron-down"></i></a>
                                <ul>
                                    <li><router-link :to="{ name: 'BrowseComponent', params: { browseType: 'allMedicalAppointments', entityId: 'null' }}">Listado de Citas</router-link></li>
                                    <li><router-link :to="{ name: 'BrowseComponent', params: { browseType: 'medicalAppointmentsToday', entityId: 'null' }}">Listado de Citas de Hoy</router-link></li>
                                </ul>
                            </li>
                            <li><a href="#"><i class="icono izquierda fa fa-share-alt"></i>Configuraciones<i class="icono derecha fa fa-chevron-down"></i></a>
                                <ul>
                                    <li><router-link :to="{ name: 'BrowseComponent', params: { browseType: 'allMedicalAreas', entityId: 'null' }}">Listado de Especialidades</router-link></li>
                                    <li><router-link :to="{ name: 'BrowseComponent', params: { browseType: 'allEmployees', entityId: 'null' }}">Listado de Colaboradores</router-link></li>
                                </ul>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
        </aside>
      <router-view :key="$route.fullPath"/>
    </div>
    <div v-show="displayMessage" class="groupMsgBox">
      <div class="msgbox active info">
        <div class="iconMsg">
          <i class="fas fa-info-circle"></i>
        </div>
        <div class="descrip" id="descripMsg">{{message}}</div>
        <div class="iconClose">
          <i class="fas fa-times"></i>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'App',
  data () {
    return {
      requireAuthentication: true,
      message:'',
      displayMessage:false,
      backendUrl: 'http://127.0.0.1:8080/',
      medicalAppointmentStatus: {
        0:'Cita Nueva',
        5:'Agendada',
        10:'En atencion',
        15:'Pendiente Resultados',
        20:'Auxiliar'
      },
      documentTypes: [
        {"key":1,"value":'DNI'},
        {"key":2,"value":'Pasaporte'},
        {"key":3,"value":'Carnet de Extranjeria'}
      ],
      civilStatus: [
        {"key":1,"value":'Soltero'},
        {"key":2,"value":'Casado'},
        {"key":3,"value":'Divorciado'},
        {"key":4,"value":'Prefiero no decir'},
      ],
      navigation: {
      }
    }
  },
  methods: {
    sucessMessage: function() {
      this.message = "Se guardaron los cambios exitosamente"
      this.displayMessage = true
      let selfVue = this
      setTimeout(function(){
            selfVue.displayMessage = false;
      }, 2000);
    },
    errorMessage: function(errormsg) {
      this.message = "Hubo un error porfavor contacte al administrador"
      this.displayMessage = true
      let selfVue = this
      setTimeout(function(){
            selfVue.displayMessage = false;
      }, 2000);
    }
  }
}
</script>

<style>
#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
</style>
