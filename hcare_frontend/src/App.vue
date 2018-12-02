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
      medicalHistoryStatus: {
        '0':'Encontrada',
        '99':'Perdida',
      },
      medicalHistoryActivities: {
        'true':'Activa',
        'false':'Pasiva',
      },medicalAppointmentTypes: {
        1:'Nueva Consulta',
        2:'Control',
        3:'Analisis de Laboratorio',
        4:'Riego Quirurgico',
        5:'Ecografia',
        6:'Eco Dopler',
        7:'Cirugia Ambulatoria',
        8:'Cirugia con Hospitalacion',
        9:'Ambulancia'
      },
      medicalAppointmentStatus: {
        0:'Cita Nueva',
        5:'Agendada',
        10:'En atencion',
        15:'Pendiente Resultados',
        20:'Auxiliar',
        90:'No se presento',
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
      districts: [
        {"key":1,"value":'Alto Selva Alegre'},
        {"key":2,"value":'Arequipa'},
        {"key":3,"value":'Cayma'},
        {"key":4,"value":'Cerro Colorado'},
        {"key":5,"value":'Characato'},
        {"key":6,"value":'Chiguata'},
        {"key":7,"value":'Jacabo Hunter'},
        {"key":8,"value":'Jose Luis Bustamante y Rivero'},
        {"key":9,"value":'La Joya'},
        {"key":10,"value":'Mariano Melgar'},
        {"key":11,"value":'Miraflores'},
        {"key":12,"value":'Moyebaya'},
        {"key":13,"value":'Paucarpata'},
        {"key":14,"value":'Pocsi'},
        {"key":15,"value":'Polobaya'},
        {"key":16,"value":'Quequeña'},
        {"key":17,"value":'Sabandia'},
        {"key":18,"value":'Sachaca'},
        {"key":19,"value":'San Juan de Tarucani'},
        {"key":20,"value":'Santa Isabel de Siguas'},
        {"key":21,"value":'Santa Rita de Siguas'},
        {"key":22,"value":'San Juan de Siguas'},
        {"key":23,"value":'Socabaya'},
        {"key":24,"value":'Tiabaya'},
        {"key":25,"value":'Vítor'},
        {"key":26,"value":'Yanahuara'},
        {"key":27,"value":'Yarabamba'},
        {"key":28,"value":'Yura'},
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
      this.message = errormsg
      this.displayMessage = true
      let selfVue = this
      setTimeout(function(){
            selfVue.displayMessage = false;
      }, 3000);
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
