<template>
<div class="content-container">
  <section data-ui-view="" class="view-container animate-fade-up">
    <section class="page">
      <div class="titleForm">
        {{title}}
      </div>
      <div class="row ui-section">
        <div class="formBox">
          <div v-if="newButtonVisible" class="btnOptions">
            <div class="boxOpt">
              <button v-on:click="viewObjectDetails(null)" class="md-button md-primary" type="button">{{newButtonTitle}}</button>
            </div>
          </div>
          <table id="browseDataTable" class="display responsive nowrap" style="width:100%">
            <thead>
            </thead>
            <tbody>
            </tbody>
          </table>
        </div>
      </div>
    </section>
  </section>
</div>
</template>
<script>
import axios from 'axios'
import moment from 'moment'

export default {
  name: 'BrowseComponent',
  data() {
    return {
      requestPage: 'browseInfo',
      title: '',
      entityIdName: '',
      newButtonVisible: false,
      newButtonTitle: '',
      displayObjects: [],
      detailFunction: '',
      browseurl: '',
      //browseName: '',
      entityId: 'null',
      columnDefinitionArray: [],
      orderDefinitionArray: [],
      //columnDefinitionValues: [],
      columnDefinitionNames: [],
      parametersArray: [],
    }
  },
  computed: {
    dataColumns() {
      return this.$store.state.browse.metadata.dataColumns;
    },
  },
  created: function() {
    console.log('created-' + 'BrowseComponent')
    switch (this.$route.params.browseName) {
      case 'allEmployees':
        this.columnDefinitionNames = [{
          columnName: 'Id',
          visible: false
        }, {
          columnName: 'Codigo',
          visible: true
        }, {
          columnName: 'Nombres',
          visible: true
        }, {
          columnName: 'Apellidos',
          visible: true
        }, {
          columnName: 'Cargo',
          visible: true
        }]
        break;
      case 'allPatients':
        this.columnDefinitionNames = [{
          columnName: 'Id',
          visible: false
        }, {
          columnName: '# de HC',
          visible: true
        }, {
          columnName: 'DNI',
          visible: true
        }, {
          columnName: 'Celular',
          visible: true
        }, {
          columnName: 'Nombres y Apellidos',
          visible: true
        }, {
          columnName: 'Email',
          visible: true
        }, {
          columnName: 'Fecha de nacimiento',
          visible: true
        }]
        break;
        case 'medicalAppointmentsToday':
          this.columnDefinitionNames = [{
            columnName: 'Id',
            visible: false
          }, {
            columnName: 'Codigo de Historia',
            visible: true
          }, {
            columnName: 'Nombre y Apellido',
            visible: true
          },{
            columnName: 'Fecha de cita',
            visible: true
          }, {
            columnName: 'Tipo de Cita',
            visible: true
          }, {
            columnName: 'Estado',
            visible: true
          },]
          break;
      case 'allMedicalHistories':
        this.columnDefinitionNames = [
        {
          columnName: 'Codigo de Historia',
          visible: true
        }, {
          columnName: 'Numero de archivo',
          visible: true
        }, {
          columnName: 'Estado de la Historia',
          visible: true
        }]
        break;
      case 'medicalAppointmentsByPatient':
        this.columnDefinitionNames = [{
          columnName: 'Id',
          visible: false
        }, {
          columnName: 'History Code',
          visible: false
        }, {
          columnName: 'Fecha de cita',
          visible: true
        }, {
          columnName: 'Estado',
          visible: true
        }, ]
        break;
      default:
        break;
    }
  },
  mounted: function() {
    console.log('mounted-' + 'BrowseComponent')
    var columnIndex = 0;
    //Assing this vue compnent to self to don't lose reference
    let selfVue = this

    this.entityId = this.$route.params.entityId
    switch (this.$route.params.browseName) {
      case 'allEmployees':
        this.browseurl = this.$parent.backendUrl + 'employees'
        this.detailComponent = 'EmployeeComponent'
        this.title = 'Listado de Colaboradores'
        this.newButtonTitle = 'Añadir Colaborador'
        this.newButtonVisible = true
        this.parametersArray = [{
          "key": "employeeId",
          "value": 0
        }, ]
        this.columnDefinitionValues = '[objectItem.id, objectItem.employeeCode,objectItem.firstName,objectItem.lastName,self.$parent.employeeTitles[objectItem.title],]'
        break;
      case 'allPatients':
        this.browseurl = this.$parent.backendUrl + 'patients'
        this.detailComponent = 'PatientComponent'
        this.title = 'Listado de Pacientes'
        this.newButtonTitle = 'Añadir Paciente'
        this.newButtonVisible = true
        this.parametersArray = [{
          "key": "id",
          "value": 0,
          "newEntityValue": null
        }, ]
        this.columnDefinitionValues = '[objectItem.id, objectItem.historyCode, objectItem.documentNumber, objectItem.phoneNumber, objectItem.fullName,objectItem.emailAddress,self.frontEndDateFormat(objectItem.birthday),]'
        break;
      case 'allMedicalAreas':
        //this.browseurl = this.$parent.backendUrl + 'medicalAreas'
        this.detailComponent = 'MedicalAreaComponent'
        this.title = 'Listado de Especialidades'
        this.newButtonTitle = 'Añadir Especialidad'
        this.newButtonVisible = true
        this.parametersArray = [{
          "key": "medicalAreaId",
          "value": 0,
          "newEntityValue": null
        }, ]
        //this.columnDefinitionValues = '[objectItem.id, objectItem.areaName,]'
        break;
      case 'allMedicalHistories':
        this.browseurl = this.$parent.backendUrl + 'medicalHistories'
        this.detailComponent = 'MedicalHistoryComponent'
        this.title = 'Listado de Historias Medicas'
        this.newButtonVisible = false
        this.newButtonTitle = 'Añadir Historia Medica'
        this.parametersArray = [{
          "key": "historyCode",
          "value": 0,
          "newEntityValue": null
        }, ]
        this.columnDefinitionValues = '[objectItem.historyCode, objectItem.fileNumber, self.$parent.medicalHistoryStatus[objectItem.status],]'
        break;
      case 'allMedAppHeaderView':
        //this.browseName = this.$route.params.browseName
        //this.browseurl = this.$parent.backendUrl + 'medicalAppointments'
        this.detailComponent = 'MedicalAppointmentPage'
        this.title = 'Listado de Citas'
        this.newButtonVisible = true
        this.newButtonTitle = 'Añadir Cita'
        this.parametersArray = [{
          "key": "maId",
          "alias": "medicalAppointmentId",
          "value": 0,
          "newEntityValue": null
        }, ]
        //this.columnDefinitionValues = '[objectItem.maId, objectItem.maHistoryCode,objectItem.maFullName,objectItem.emFullName,self.frontEndDatetimeFormat(objectItem.maDateAppointment),self.$parent.medicalAppointmentTypes[objectItem.maMedAppType],self.$parent.medicalAppointmentStatus[objectItem.maStatus],]'
        break;
      case 'medicalAppointmentsToday':
        this.browseurl = this.$parent.backendUrl + 'medicalAppointmentsToday'
        this.detailComponent = 'MedicalAppointmentComponent'
        this.title = 'Listado de Citas del Dia'
        this.newButtonVisible = true
        this.newButtonTitle = 'Añadir Cita'
        this.parametersArray = [{
          "key": "medicalAppointmentId",
          "value": 0,
          "newEntityValue": null
        }, ]
        this.columnDefinitionValues = '[objectItem.id, objectItem.historyCode,objectItem.fullName,self.frontEndDatetimeFormat(objectItem.dateAppointment),self.$parent.medicalAppointmentTypes[objectItem.medicalAppointmentType],self.$parent.medicalAppointmentStatus[objectItem.status],]'
        break;
        v
      case 'medicalAppointmentsByPatient':
        this.browseurl = this.$parent.backendUrl + 'medicalAppointmentsByPatient/' + this.entityId
        this.detailComponent = 'MedicalAppointmentComponent'
        this.title = 'Listado de Citas del Paciente ' + this.entityId
        this.newButtonVisible = false
        this.newButtonTitle = 'Añadir Cita'
        this.parametersArray = [{
            "key": "medicalAppointmentId",
            "value": 0,
            "newEntityValue": null
          },
          {
            "key": "id",
            "value": 0,
            "newEntityValue": "entityId"
          },
        ]
        this.columnDefinitionValues = '[objectItem.id, objectItem.historyCode, self.frontEndDateFormat(objectItem.dateAppointment),self.$parent.medicalAppointmentStatus[objectItem.status],]'
        break;
      default:
        break;
    }
    const dataContent = {
        "browseName": this.$route.params.browseName
    }
    const {
      requestPage
    } = this;
    const {
      dispatch
    } = this.$store;

    dispatch('browse/getBrowseData', {
        requestPage: requestPage,
        processName: '',
        dataContent: dataContent,
        vueInstance: this
    });

    $('#browseDataTable tbody').on('click', '#open', function(self) {
      //Remove event when the its on first element to dont overwrite more options button
      var dataRoute = this.getAttribute('data-funtion');
      var rowData = $('#browseDataTable').DataTable().row(this).data();
      if(rowData == undefined){
        var $tr = $(this).closest('tr');
        rowData = $('#browseDataTable').DataTable().row($tr).data();
      }
      selfVue.openObjectDetails(rowData,dataRoute);
    })
    $('#browseDataTable tbody').on('click', '#save', function(self) {
      //Remove event when the its on first element to dont overwrite more options button
      ;
      var rowData = $('#browseDataTable').DataTable().row(this).data();
      if(rowData == undefined){
        var $tr = $(this).closest('tr');
        rowData = $('#browseDataTable').DataTable().row($tr).data();
      }
      //console.log('You clicked on ' + data[0] + '\'s row')
      var dataFuntion = this.getAttribute('data-funtion');
      selfVue.saveObjectDetails(rowData,dataFuntion);
    })
  },
  methods: {
    openObjectDetails: function(rowData,dataRoute) {
      var routeObject = {};
      var jsonString = dataRoute;
      dataRoute.match(/\${{(.*?)}}/g).forEach(function(dataRouteVariable) {
        jsonString = jsonString.replace(dataRouteVariable,rowData[dataRouteVariable.match( /\$\{\{([^)]+)\}\}/)[1]]);
      });
      routeObject = JSON.parse(jsonString);
      //routeObject = JSON.parse('{"name":"' + this.detailComponent + '","params":{"' + this.entityIdName + '":"' + id + '"}}')
      this.$router.push(routeObject);
    },
    saveObjectDetails: function(rowData,dataFuntion) {
      var routeObject = {};
      var jsonFuntionString = dataFuntion;
      dataFuntion.match(/\${{(.*?)}}/g).forEach(function(dataFuntionVariable) {
        jsonFuntionString = jsonFuntionString.replace(dataFuntionVariable,rowData[dataFuntionVariable.match( /\$\{\{([^)]+)\}\}/)[1]]);
      });
      var functionJSONObject = JSON.parse(jsonFuntionString);
      const {
        dispatch,commit
      } = this.$store;
      dispatch(functionJSONObject.action, {
        requestPage: functionJSONObject.params.requestPage,
        processName: functionJSONObject.params.processName,
        dataContent: functionJSONObject.params.dataContent
      }).then(response => {
          var dataContent = {
            "browseName": this.$route.params.browseName
          };
          dispatch('browse/reloadBrowseData', {
            requestPage: '',
            processName: '',
            dataContent: dataContent,
            vueInstance: this
          });
        }, error => {
            console.error("Got nothing from server. Prompt user to check internet connection and try again")
        })
      /*
      .then(() => {

        var dataContent = {
          "browseName": this.$route.params.browseName
        };
        dispatch('browse/getBrowseData', {
            requestPage: '',
            processName: '',
            dataContent: dataContent,
            vueInstance: this
        });
      });
      */
    },
  }
}
</script>
