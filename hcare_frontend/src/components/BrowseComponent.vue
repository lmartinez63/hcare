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
              <tr>
                <th v-for="columnDefinition in columnDefinitionNames">{{columnDefinition.columnName}}</th>
              </tr>
            </thead>
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
      title: '',
      entityIdName: '',
      newButtonVisible: false,
      newButtonTitle: '',
      displayObjects: [],
      detailFunction: '',
      browseurl: '',
      entityId: 'null',
      columnDefinitionArray: [],
      columnDefinitionValues: [],
      columnDefinitionNames: [],
      parametersArray: [],
    }
  },
  created: function() {
    console.log('created-' + 'BrowseComponent')
    switch (this.$route.params.browseType) {
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
      case 'allMedicalAreas':
        this.columnDefinitionNames = [{
          columnName: 'Id',
          visible: false
        }, {
          columnName: 'Nombre de la Especialidad',
          visible: true
        }]
        break;
      case 'allPatients':
        this.columnDefinitionNames = [{
          columnName: 'Id',
          visible: false
        }, {
          columnName: 'Codigo del paciente',
          visible: true
        }, {
          columnName: 'Nombres',
          visible: true
        }, {
          columnName: 'Apellidos',
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
            columnName: 'Codigo de Paciente',
            visible: true
          }, {
            columnName: 'Fecha de cita',
            visible: true
          }, {
            columnName: 'Estado',
            visible: true
          }, ]
          break;
      case 'allMedicalAppointments':
        this.columnDefinitionNames = [{
          columnName: 'Id',
          visible: false
        }, {
          columnName: 'Codigo de Paciente',
          visible: true
        }, {
          columnName: 'Fecha de cita',
          visible: true
        }, {
          columnName: 'Estado',
          visible: true
        }, ]
        break;
      case 'allMedicalHistories':
        this.columnDefinitionNames = [{
          columnName: 'Id',
          visible: false
        }, {
          columnName: 'Id Paciente',
          visible: false
        }, {
          columnName: 'Codigo de Paciente',
          visible: true
        }, {
          columnName: 'Codigo de Historia',
          visible: true
        }, {
          columnName: 'Numero de Archivo',
          visible: true
        }, ]
        break;
      case 'medicalAppointmentsByPatient':
        this.columnDefinitionNames = [{
          columnName: 'Id',
          visible: false
        }, {
          columnName: 'Id de Paciente',
          visible: false
        }, {
          columnName: 'Codigo de Paciente',
          visible: true
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
    let self = this
    this.columnDefinitionNames.forEach(function(columnDefinition) {
      if (columnDefinition.visible == false) {
        self.columnDefinitionArray.push(JSON.parse('{"targets": [' + columnIndex + '],"visible": false,"searchable": false}'))
      }
      columnIndex++
    });
    var browseDataTable = $('#browseDataTable').DataTable({
      responsive: true,
      columnDefs: this.columnDefinitionArray,
    })
    this.entityId = this.$route.params.entityId
    switch (this.$route.params.browseType) {
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
        this.columnDefinitionValues = '[objectItem.id, objectItem.employeeCode,objectItem.firstName,objectItem.lastName,objectItem.title,]'
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
        this.columnDefinitionValues = '[objectItem.id, objectItem.patientCode,objectItem.firstName,objectItem.lastName,objectItem.emailAddress,self.frontEndDateFormat(objectItem.birthday),]'
        break;
      case 'allMedicalAreas':
        this.browseurl = this.$parent.backendUrl + 'medicalAreas'
        this.detailComponent = 'MedicalAreaComponent'
        this.title = 'Listado de Especialidades'
        this.newButtonTitle = 'Añadir Especialidad'
        this.newButtonVisible = true
        this.parametersArray = [{
          "key": "medicalAreaId",
          "value": 0,
          "newEntityValue": null
        }, ]
        this.columnDefinitionValues = '[objectItem.id, objectItem.areaName,]'
        break;
      case 'allMedicalHistories':
        this.browseurl = this.$parent.backendUrl + 'medicalHistories'
        this.detailComponent = 'MedicalHistoryComponent'
        this.title = 'Listado de Historias Medicas'
        this.newButtonVisible = false
        this.newButtonTitle = 'Añadir Historia Medica'
        this.parametersArray = [{
          "key": "patientId",
          "value": 1,
          "newEntityValue": null
        }, ]
        this.columnDefinitionValues = '[objectItem.id, objectItem.patientId, objectItem.patientCode, objectItem.medicalHistoryCode, objectItem.fileNumber,]'
        break;
      case 'allMedicalAppointments':
        this.browseurl = this.$parent.backendUrl + 'medicalAppointments'
        this.detailComponent = 'MedicalAppointmentComponent'
        this.title = 'Listado de Citas'
        this.newButtonVisible = false
        this.newButtonTitle = 'Añadir Cita'
        this.parametersArray = [{
          "key": "medicalAppointmentId",
          "value": 0,
          "newEntityValue": null
        }, ]
        this.columnDefinitionValues = '[objectItem.id, objectItem.patientCode,self.frontEndDateFormat(objectItem.dateAppointment),self.$parent.medicalAppointmentStatus[objectItem.status],]'
        break;
      case 'medicalAppointmentsToday':
        this.browseurl = this.$parent.backendUrl + 'medicalAppointmentsToday'
        this.detailComponent = 'MedicalAppointmentComponent'
        this.title = 'Listado de Citas del Dia'
        this.newButtonVisible = false
        this.newButtonTitle = 'Añadir Cita'
        this.parametersArray = [{
          "key": "medicalAppointmentId",
          "value": 0,
          "newEntityValue": null
        }, ]
        this.columnDefinitionValues = '[objectItem.id, objectItem.patientCode,self.frontEndDatetimeFormat(objectItem.dateAppointment),self.$parent.medicalAppointmentStatus[objectItem.status],]'
        break;
        v
      case 'medicalAppointmentsByPatient':
        this.browseurl = this.$parent.backendUrl + 'medicalAppointmentsByPatientId/' + this.entityId
        this.detailComponent = 'MedicalAppointmentComponent'
        this.title = 'Listado de Citas del Paciente ' + this.entityId
        this.newButtonVisible = true
        this.newButtonTitle = 'Añadir Cita'
        this.parametersArray = [{
            "key": "medicalAppointmentId",
            "value": 0,
            "newEntityValue": null
          },
          {
            "key": "patientId",
            "value": 1,
            "newEntityValue": "entityId"
          },
        ]
        this.columnDefinitionValues = '[objectItem.id, objectItem.patientId, objectItem.patientCode,self.frontEndDateFormat(objectItem.dateAppointment),objectItem.status,]'
        break;
      default:
        break;
    }

    axios.get(this.browseurl).then(response => {
        const objectsModel = response.data
        // Clear datatable
        browseDataTable.clear()
        objectsModel.map(objectItem => {
          self.displayObjects.push(objectItem)
          $('#browseDataTable').dataTable().fnAddData(eval(self.columnDefinitionValues))
        })
        let selfinside = self
        $('#browseDataTable').on('click', 'tr', function(self) {
          var data = browseDataTable.row(this).data()
          //console.log('You clicked on ' + data[0] + '\'s row')
          selfinside.viewObjectDetails(data)
        })
      })
      .catch(error => {
        console.log(error)
      })
  },
  methods: {
    viewObjectDetails: function(data) {
      var routeObject = {}
      var jsonString = '{"name":"' + this.detailComponent + '","params":{'
      let selfVue = this
      this.parametersArray.forEach(function(parameter) {
        //Should be other parameter like identificator for example and not use visible
        var dataParameter = null;
        if (data == null) {
          if (parameter.newEntityValue != null) {
            dataParameter = selfVue.$route.params[parameter.newEntityValue]
          }
        } else {
          dataParameter = data[parameter.value]
        }
        jsonString = jsonString + '"' + parameter.key + '":"' + dataParameter + '",'
      });
      jsonString = jsonString.substring(0, jsonString.length - 1);
      jsonString = jsonString + '}}'
      routeObject = JSON.parse(jsonString)
      //routeObject = JSON.parse('{"name":"' + this.detailComponent + '","params":{"' + this.entityIdName + '":"' + id + '"}}')
      this.$router.push(routeObject)
    },
    frontEndDateFormat: function(date) {
      return moment(date, 'YYYY-MM-DDTHH:mm:ss.fff Z').format('DD/MM/YYYY')
    },
    frontEndDatetimeFormat: function(date) {
      return moment(date, 'YYYY-MM-DDTHH:mm:ss.fff Z').format('DD/MM/YYYY HH:mm')
    },
  }
}
</script>
