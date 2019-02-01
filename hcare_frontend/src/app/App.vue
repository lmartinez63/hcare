<template>
  <!--img src="./assets/logo.png"-->
  <div>
  <header v-if="user" id="header" class="header-container header-fixed bg-white" >
      <div class="mainMenu" onClick="openMenuMain()"><i class="fas fa-bars"></i></div>
      <div class="logo">
          <img src="/static/img/logo.png">
      </div>
      <div class="top-nav">
          <div v-if="user.userProfile" class="username">
              Usuario: <div v-if="user.userProfile.firstName != undefined"> {{user.userProfile.firstName}}</div>
              <router-link to="/login">Logout</router-link>
          </div>
      </div>
  </header>
    <!--
    <div class="jumbotron">
        <div class="container">
            <div class="row">
                <div class="col-sm-6 offset-sm-3">
                    <div v-if="alert.message" :class="`alert ${alert.type}`">{{alert.message}}</div>
                    <router-view></router-view>
                </div>
            </div>
        </div>
    </div>
  -->
    <div class="main-container">
        <aside v-if="user" id="nav-container" class="nav-container nav-fixed bg-dark">
            <div class="nav-wrapper">
                <div class="slimScrollDiv" style="position: relative; overflow: hidden; width: auto; height: 100%;">
                    <div class="contenedor-menu">
                        <ul class="menu" v-if="user.userProfile" >
                            <li><router-link :to="{ name: 'DashboardComponent'}"><i class="icono izquierda fa fa-home"></i>Tablero</router-link></li>
                            <li v-if="user.userProfile.pageSectionMap.patientInfo != undefined">
                              <a><i class="icono izquierda fa fa-star"></i>Administration de Pacientes<i class="icono derecha fa fa-chevron-down"></i></a>
                                <ul>
                                    <li><router-link :to="{ name: 'BrowseComponent', params: { browseType: 'allPatients', entityId: 'null' }}">Listado de Pacientes</router-link></li>
                                    <li><router-link :to="{ name: 'PatientComponent', params: { id: 'null' }}">Registrar Paciente</router-link></li>
                                </ul>
                            </li>
                            <li v-if="user.userProfile.pageSectionMap.medicalHistoryInfo != undefined" >
                              <a><i class="icono izquierda fa fa-share-alt"></i>Administracion de Historias Medicas<i class="icono derecha fa fa-chevron-down"></i></a>
                                <ul>
                                    <li><router-link :to="{ name: 'BrowseComponent', params: { browseType: 'allMedicalHistories', entityId: 'null' }}">Listado de Historias Medicas</router-link></li>
                                </ul>
                            </li>
                            <li v-if="user.userProfile.pageSectionMap.medicalAppointmentInfo != undefined" >
                              <a><i class="icono izquierda fa fa-share-alt"></i>Administracion de Citas<i class="icono derecha fa fa-chevron-down"></i></a>
                                <ul>
                                    <li><router-link :to="{ name: 'MedicalAppointmentPage', params: { medicalAppointmentId: 'null' }}">Nueva Cita</router-link></li>
                                    <li><router-link :to="{ name: 'BrowseComponent', params: { browseType: 'allMedicalAppointments', entityId: 'null' }}">Listado de Citas</router-link></li>
                                    <li><router-link :to="{ name: 'BrowseComponent', params: { browseType: 'medicalAppointmentsToday', entityId: 'null' }}">Listado de Citas de Hoy</router-link></li>
                                </ul>
                            </li>
                            <li v-if="user.userProfile.pageSectionMap.medicalAreaInfo != undefined || user.userProfile.pageSectionMap.userProfileInfo != undefined" >
                              <a><i class="icono izquierda fa fa-share-alt"></i>Configuraciones<i class="icono derecha fa fa-chevron-down"></i></a>
                                <ul>
                                    <li v-if="user.userProfile.pageSectionMap.medicalAreaInfo != undefined" ><router-link :to="{ name: 'BrowseComponent', params: { browseType: 'allMedicalAreas', entityId: 'null' }}">Listado de Especialidades</router-link></li>
                                    <li v-if="user.userProfile.pageSectionMap.userProfileInfo != undefined" ><router-link :to="{ name: 'BrowseComponent', params: { browseType: 'allEmployees', entityId: 'null' }}">Listado de Colaboradores</router-link></li>
                                </ul>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
        </aside>
        <div v-if="alert.message" :class="`alert ${alert.type}`">{{alert.message}}</div>
      <router-view :key="$route.fullPath"/>
    </div>
    <!--
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
    -->
  </div>
</template>

<script>
export default {
    name: 'app',
    computed: {
        alert () {
            return this.$store.state.alert
        },
        user () {
            return this.$store.state.authentication.user;
        },
        properties () {
            return this.$store.state.general.properties;
        }
    },
    created () {
        console.log('App - Create - begin')

        if(this.user){
            this.$store.dispatch('general/loadProperties');
            this.$store.dispatch('general/getMedicalAreas');
            this.$store.dispatch('general/getDoctors');
        }
        console.log('App - Create - end')
    },
    watch:{
        $route (to, from){
            // clear alert on location change
            this.$store.dispatch('alert/clear');
        }
    }
};
</script>
