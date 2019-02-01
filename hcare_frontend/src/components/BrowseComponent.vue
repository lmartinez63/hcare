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
      title: '',
      entityIdName: '',
      newButtonVisible: false,
      newButtonTitle: '',
      displayObjects: [],
      detailFunction: '',
      browseurl: '',
      entityId: 'null',
      columnDefinitionArray: [],
      orderDefinitionArray: [],
      columnDefinitionValues: [],
      columnDefinitionNames: [],
      dataOfDatatables: {},
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
      case 'allMedicalAppointments':
        this.columnDefinitionNames = [{
          columnName: 'Id',
          visible: false
        }, {
          columnName: 'Codigo de Historia',
          visible: true
        }, {
          columnName: 'Paciente',
          visible: true,
          colType: 'string'
        },{
          columnName: 'Doctor(a)',
          visible: true,
          colType: 'string'
        },{
          columnName: 'Fecha Y Hora',
          visible: true,
          order: 'desc',
          colType: 'date'
        }, {
          columnName: 'Tipo ',
          visible: true,
          colType: 'string'
        }, {
          columnName: 'Estado',
          visible: true,
          colType: 'string'
        }, {
          columnName: 'Opciones',
          colType: 'medical_appointment_button',
          visible: true
        },]
        this.columns = [
            { data: "maId" },
            { data: "maHistoryCode" },
            { data: "maFullName" },
            { data: "emFullName" },
            { data: "maDateAppointment"},
            { data: "maMedAppType" },
            { data: "maStatus" },
        ]
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
    this.columnDefinitionNames.forEach(function(columnDefinition) {
      var colType = ''
      if (columnDefinition.colType != undefined) {
        colType = ',"type": "'+columnDefinition.colType+'"'
      }
      var visibility = ''
      if (columnDefinition.visible == false) {
        visibility = ',"visible": false, "searchable": false'

      } else {
        visibility = ',"visible": true'
      }
      var jsonObject=JSON.parse('{"targets": [' + columnIndex + ']'+visibility+colType+'}')
      switch(columnDefinition.colType){
        case 'date':
          jsonObject.render = function(data){return moment(data).format('DD/MM/YYYY hh:mm a');}
          jsonObject.className = 'dt-body-right'
          break;
        case 'string':
          jsonObject.className = 'dt-body-left'
          break;
        case 'medical_appointment_button':
          jsonObject.data = null,
          jsonObject.defaultContent = "<button id='openMedicalAppointment'>Abrir</button><button>Se presento</button><button>No se Presento</button>"
          break;
      }
      selfVue.columnDefinitionArray.push(jsonObject)

      if (columnDefinition.order != undefined) {
        selfVue.orderDefinitionArray.push(JSON.parse('[' + columnIndex + ',"' + columnDefinition.order + '"]'))
      }
      columnIndex++
    });
    this.entityId = this.$route.params.entityId
    this.browseurl = this.$parent.backendUrl + '/getBrowseData/'
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
          "key": "historyCode",
          "value": 0,
          "newEntityValue": null
        }, ]
        this.columnDefinitionValues = '[objectItem.historyCode, objectItem.fileNumber, self.$parent.medicalHistoryStatus[objectItem.status],]'
        break;
      case 'allMedicalAppointments':
        this.browseurl = this.browseurl + 'allMedAppHeaderView'
        //this.browseurl = this.$parent.backendUrl + 'medicalAppointments'
        this.detailComponent = 'MedicalAppointmentComponent'
        this.title = 'Listado de Citas'
        this.newButtonVisible = true
        this.newButtonTitle = 'Añadir Cita'
        this.parametersArray = [{
          "key": "maId",
          "alias": "medicalAppointmentId",
          "value": 0,
          "newEntityValue": null
        }, ]
        this.columnDefinitionValues = '[objectItem.maId, objectItem.maHistoryCode,objectItem.maFullName,objectItem.emFullName,self.frontEndDatetimeFormat(objectItem.maDateAppointment),self.$parent.medicalAppointmentTypes[objectItem.maMedAppType],self.$parent.medicalAppointmentStatus[objectItem.maStatus],]'
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
    axios.get(this.browseurl).then(response => {
        selfVue.dataOfDatatables = response.data.data
        // JS Load Data for datatables
        var browseDataTable = $('#browseDataTable').DataTable({
          responsive: true,
          columnDefs: selfVue.columnDefinitionArray,
          dom: 'Bfrtip',
          buttons: [
              'copy', 'csv', 'excel', 'pdf', 'print'
          ],
          order: selfVue.orderDefinitionArray,
          //data: selfVue.dataOfDatatables,
          "ajax": function (data, callback, settings) {
              callback( { data: selfVue.dataOfDatatables } );
          },
          "columns": selfVue.columns
        })

        $('#browseDataTable tbody').on('click', '#openMedicalAppointment', function(self) {
          //Remove event when the its on first element to dont overwrite more options button
          var data = browseDataTable.row(this).data()
          //console.log('You clicked on ' + data[0] + '\'s row')
          selfVue.viewObjectDetails(data)
        })

      })
      .catch(error => {
        console.log(error)
      })
    /*
    var browseDataTable = $('#browseDataTable').DataTable({
      responsive: true,
      columnDefs: this.columnDefinitionArray,
      dom: 'Bfrtip',
      buttons: [
          'copy', 'csv', 'excel', 'pdf', 'print'
      ],
      order: this.orderDefinitionArray,

//      "processing": true,
      "serverSide": true,
      "ajax": {
            "url": this.browseurl,
            "type": "GET"
      },
      "columns": this.columns
    })
    browseDataTable = $('#browseDataTable').DataTable({
      "serverSide": false
    })
    *./
/*
    axios.get(this.browseurl).then(response => {
        const objectsModel = response.data
        // Clear datatable
        browseDataTable.clear()
        objectsModel.map(objectItem => {
          self.displayObjects.push(objectItem)
          $('#browseDataTable').dataTable().fnAddData(eval(self.columnDefinitionValues))
        })
        let selfinside = self
        $('#browseDataTable tbody').on('click', 'tr', function(self) {
          //Remove event when the its on first element to dont overwrite more options button
          if(self.target.tabIndex != 0 || (self.target.tabIndex == 0 && this.cells.length == 1) ){
            var data = browseDataTable.row(this).data()
            //console.log('You clicked on ' + data[0] + '\'s row')
            selfinside.viewObjectDetails(data)
          }
        })
      })
      .catch(error => {
        console.log(error)
      })
      */
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
          dataParameter = data[parameter.key]
        }
        var sentParameterName = parameter.key
        if (parameter.alias != null) {
          sentParameterName = parameter.alias
        }
        jsonString = jsonString + '"' + sentParameterName + '":"' + dataParameter + '",'
      });
      jsonString = jsonString.substring(0, jsonString.length - 1);
      jsonString = jsonString + '}}'
      routeObject = JSON.parse(jsonString)
      //routeObject = JSON.parse('{"name":"' + this.detailComponent + '","params":{"' + this.entityIdName + '":"' + id + '"}}')
      this.$router.push(routeObject)
    },
  }
}
</script>
