<template>
<!--img src="./assets/logo.png"-->
<div>
  <header v-if="user" id="header" class="header-container header-fixed bg-white">
    <div class="mainMenu" onClick="openMenuMain()"><i class="fas fa-bars"></i></div>
    <div class="logo">
      <img src="/static/img/logo.png">
    </div>
    <div class="top-nav">
      <div v-if="user.userProfile" class="username">
        Usuario: <div v-if="user.userProfile.firstName != undefined"> {{user.userProfile.firstName}}</div>
        <div class="btn btn-logout" v-on:click="logout"><div class="iconLogout"></div>Logout</div>
      </div>
    </div>
  </header>
  <div class="main-container">
    <loading :active.sync="isLoading" :can-cancel="true" :on-cancel="onCancel" :is-full-page="fullPage"></loading>
    <flash-message></flash-message>
    <div v-show="alertVar.message && alertVar.type =='warning' " :class="`alert ${alertVar.type}`">{{alertVar.message}}</div>
    <aside v-if="user" id="nav-container" class="nav-container nav-fixed bg-dark">
      <div class="nav-wrapper">
        <div class="slimScrollDiv" style="position: relative; overflow: hidden; width: auto; height: 100%;">
          <div class="contenedor-menu">
            <ul class="menu" v-if="user.userProfile">
              <li>
                <router-link :to="{ name: 'DashboardPage'}"><i class="icono izquierda fa fa-home"></i>Tablero Principal</router-link>
              </li>
              <li v-if="user.userProfile.pageSectionMap.patientInfo != undefined" class="activado">
                <a><i class="icono izquierda fa fa-star"></i>Administration de Pacientes<i class="icono derecha fa fa-chevron-down"></i></a>
                <ul class="activado" style="display: block;">
                  <li style="display: block;">
                    <router-link :to="{ name: 'BrowsePage', params: { browseName: 'allPatients' }}">Listado de Pacientes</router-link>
                  </li>
                  <li style="display: block;">
                    <router-link :to="{ name: 'PatientPage', params: { patientId: 'null' }}">Registrar Paciente</router-link>
                  </li>
                </ul>
              </li>
              <li v-if="user.userProfile.pageSectionMap.medicalHistoryInfo != undefined">
                <a><i class="icono izquierda fa fa-share-alt"></i>Administracion de Historias Medicas<i class="icono derecha fa fa-chevron-down"></i></a>
                <ul class="activado" style="display: block;">
                  <li style="display: block;">
                    <router-link :to="{ name: 'BrowsePage', params: { browseName: 'allMedicalHistories', entityId: 'null' }}">Listado de Historias Medicas</router-link>
                  </li>
                </ul>
              </li>
              <li v-if="user.userProfile.pageSectionMap.medicalAppointmentInfo != undefined">
                <a><i class="icono izquierda fa fa-share-alt"></i>Administracion de Citas<i class="icono derecha fa fa-chevron-down"></i></a>
                <ul class="activado" style="display: block;">
                  <li style="display: block;">
                    <router-link :to="{ name: 'BrowsePage', params: { browseName: 'medicalAppointmentsToday' }}">Listado de Citas de Hoy</router-link>
                  </li>
                  <li style="display: block;">
                    <router-link :to="{ name: 'MedicalAppointmentPage', params: { medicalAppointmentId: 'null' }}">Nueva Cita</router-link>
                  </li>
                  <li style="display: block;">
                    <router-link :to="{ name: 'BrowsePage', params: { browseName: 'allMedAppHeaderView' }}">Listado de Citas</router-link>
                  </li>
                </ul>
              </li>
              <li v-if="user.userProfile.pageSectionMap.medicalAreaInfo != undefined || user.userProfile.pageSectionMap.userProfileInfo != undefined">
                <a><i class="icono izquierda fa fa-share-alt"></i>Configuraciones<i class="icono derecha fa fa-chevron-down"></i></a>
                <ul class="activado" style="display: block;">
                  <li v-if="user.userProfile.pageSectionMap.medicalAreaInfo != undefined" style="display: block;">
                    <router-link :to="{ name: 'BrowsePage', params: { browseName: 'allMedicalAreas' }}">Listado de Especialidades</router-link>
                  </li>
                  <li v-if="user.userProfile.pageSectionMap.userProfileInfo != undefined" style="display: block;">
                    <router-link :to="{ name: 'BrowsePage', params: { browseName: 'allUserProfiles' }}">Listado de Usuarios</router-link>
                  </li>
                  <li v-if="user.userProfile.pageSectionMap.pageInfo != undefined" style="display: block;">
                    <router-link :to="{ name: 'BrowsePage', params: { browseName: 'allPages' }}">Listado de Paginas</router-link>
                  </li>
                  <li v-if="user.userProfile.pageSectionMap.pageInfo != undefined" style="display: block;">
                    <router-link :to="{ name: 'PageDefinitionPage', params: { pageId: 'null' }}">Nueva Pagina</router-link>
                  </li>
                  <li v-if="user.userProfile.pageSectionMap.rolePageSectionFieldInfo != undefined" style="display: block;">
                    <router-link :to="{ name: 'BrowsePage', params: { browseName: 'allRolePageSectionFieldPage' }}">Listado de Asignacion de Campos por Roles</router-link>
                  </li>
                  <li v-if="user.userProfile.pageSectionMap.pageInfo != undefined" style="display: block;">
                    <router-link :to="{ name: 'RolePageSectionFieldPage', params: { roleId: 'null',pageId: 'null',sectionId: 'null',fieldDefinitionId: 'null' }}">Asignacion de Campo a un Rol</router-link>
                  </li>
                </ul>
              </li>
            </ul>
          </div>
        </div>
      </div>
    </aside>
    <router-view :key="$route.fullPath" />
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
import Loading from 'vue-loading-overlay';
// Import stylesheet
import 'vue-loading-overlay/dist/vue-loading.css';
import 'vue-flash-message/dist/vue-flash-message.min.css';
import 'vuetify/dist/vuetify.min.css'

export default {
  name: 'app',
  components: {
    Loading
  },
  data() {
    return {
      fullPage: true,
      backendUrl: 'http://127.0.0.1:8080/',
    }
  },
  computed: {
    alertVar() {
      return this.$store.state.alert
    },
    alertVarType() {
      return this.$store.state.alert.type
    },
    user() {
      return this.$store.state.authentication.user;
    },
    properties() {
      return this.$store.state.general.properties;
    },
    isLoading() {
      return this.$store.state.general.loading;
    }
    /*show() {
      return this.$store.state.authentication.loading;
    },*/
  },
  created() {
    console.log('App - Create - begin')

    if (this.user) {
      this.$store.dispatch('general/loadProperties');
      this.$store.dispatch('general/getMedicalAreas');
      this.$store.dispatch('general/getDoctors');
      this.$store.dispatch('general/getRoles');
    }
    console.log('App - Create - end')
  },
  updated () {
    //TOREMOVE need to be removed it's updated each time DOM is updated
    var threePoint = true;
    var menuMain = true;
    $(document).ready(function() {
      $('#table_id').DataTable({
        responsive: true
      });

      $('.menu li:has(ul)').click(function(e) {
        e.preventDefault();
        return;
        if ($(this).hasClass('activado')) {
          $(this).removeClass('activado');
          $(this).children('ul').slideUp();
        } else {
          $('.menu li ul').slideUp();
          $('.menu li').removeClass('activado');
          $(this).addClass('activado');
          $(this).children('ul').slideDown();
        }
      });

      $('.btn-menu').click(function() {
        $('.contenedor-menu .menu').slideToggle();
      });

      $('.menu li ul li a').click(function() {
        window.location.href = $(this).attr("href");
      });
    });

    function frontEndDateFormat(date,type,row) {
      return moment(date, 'YYYY-MM-DDTHH:mm:ss.fff Z').format('DD/MM/YYYY')
    };

    function frontEndDatetimeFormat(date,type,row) {
      return moment(date, 'YYYY-MM-DDTHH:mm:ss.fff Z').format('DD/MM/YYYY hh:mm a')
    };
    function openThreePoint() {
      if (threePoint) {
        $(".moreOptions").fadeIn();
        $(".moreOptions").css({
          'display': 'flex'
        });
        setTimeout(function() {
          $("body").addClass("closeMoreOptions");
        }, 10);
        threePoint = false;
      }
    };
    function openMenuMain() {
      if (menuMain) {
        $(".nav-container").fadeIn();
        $(".nav-container").css({
          'display': 'flex'
        });
        setTimeout(function() {
          $("body").addClass("closeMenuMain");
        }, 10);
        menuMain = false;
      }
    };
    $(document).on("click", ".closeMoreOptions", function() {
      $(".moreOptions").fadeOut();
      $(".closeMoreOptions").attr("class", "");
      threePoint = true;
    });
  },
  methods: {
    getLabelValue: function(label) {
      var lValue = '';
      if (label) {
        var language = this.$store.state.authentication.user.userProfile.language;
        switch (language) {
          case 'ES_ES':
            lValue = label.labelValueEsEs;
            break;
          default:
            break;
        }

      }
      return lValue;
    },
    logout: function() {
      this.submitted = true;
      const { dispatch } = this.$store;
      dispatch('authentication/logout');
    },
    downloadAttachment: function(attachment) {
      const dataContent = {
        "attachment": attachment
      };
      const {
        dispatch
      } = this.$store;
      dispatch('general/downloadAttachment', {
          vm: this,
          dataContent: dataContent,
          //returnRoute: returnRoute,
      });
    },
    onCancel() {
      console.log('User cancelled the loader.')
    }
  },
  watch: {
    $route(to, from) {
      // clear alert on location change
      this.$store.dispatch('alert/clear');
    },
    alertVarType(val) {
      console.log("Alert changed " + val);
      //TODO setup labels
      var self = this;
      if (val == 'danger')
        this.$swal({
          title: "Un error occurrio!",
          text: "Le gustaria reportar el error!",
          icon: "warning",
          dangerMode: true,
          buttons: ["Retornar a la pagina anterior!", "Enviar Reporte"],
          closeOnEsc: false,
          closeOnClickOutside: false
        }).then((sendAlert) => {
          if (sendAlert) {
            //TODO Send Alert to an email or something
            this.$swal({
              text: "Reporte enviado! Estaremos arreglando el error lo mas pronto posible, perdón los inconvenientes",
              icon: "success",
              closeOnEsc: false,
              closeOnClickOutside: false
            }).then(value => {
              self.$router.go(-1);
            });
          } else {
            this.$swal({
              text: "Retornando a la pagina anterior!",
              closeOnEsc: false,
              closeOnClickOutside: false
            }).then(value => {
              self.$router.go(-1);
            });;
          }
        });

    },
    isLoading(val) {
      console.log("Loading changed " + val);
    }
  }
};
</script>
