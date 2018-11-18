<template>
    <div class="content-container">
      <section data-ui-view="" class="view-container animate-fade-up">
        <section class="page">
          <div class="titleForm">
            {{title}}
          </div>
          <div class="row ui-section">
            <div class="formBox">
              <div class="btnOptions">
                <div class="boxOpt">
                  <button v-on:click="viewObjectDetails('null')" class="md-button md-primary" type="button" >{{newButtonTitle}}</button>
                </div>
              </div>
              <table id="browseDataTable" class="display responsive nowrap" style="width:100%">
                <thead>
                <tr>
                    <th>Id</th>
                    <th>Codigo del paciente</th>
                    <th>Nombres</th>
                    <th>Apellidos</th>
                    <th>Email</th>
                    <th>Fecha de nacimiento</th>
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
  data () {
    return {
        title: '',
        newButtonTitle: '',
        displayObjects: [],
        detailFunction: '',
        browseurl: '',
    }
  },
  mounted: function () {
    console.log('BrowseComponent')
    var browseDataTable = $('#browseDataTable').DataTable({responsive: true, columnDefs: [{ targets: [ 0 ], visible: false, searchable: false }],})

    switch(this.$route.params.browseType) {
        case 'allPatients':
            this.browseurl = this.$parent.backendUrl+'patients'
            this.detailComponent = 'PatientComponent'
            this.title = 'Listado de Pacientes'
            this.newButtonTitle = 'Añadir Paciente'
            break;
        default:
            break;
    }
    //Assing this vue compnent to self to don't lose reference
    let self = this
    axios.get(this.browseurl).then(response => {
        const objectsModel = response.data
        // Clear datatable
        browseDataTable.clear()
        objectsModel.map(objectItem => {
          self.displayObjects.push(objectItem)
          $('#browseDataTable').dataTable().fnAddData([
            // '<button v-on:click="viewObjectDetails(' + objectItem.pk + ')"> ObjectDetails </button>',
            objectItem.id,
            objectItem.patientCode,
            objectItem.firstName,
            objectItem.lastName,
            objectItem.emailAddress,
            self.frontEndDateFormat(objectItem.birthday),

          ])
        })
        let selfinside = self
        $('#browseDataTable').on('click', 'tr', function (self) {
          var data = browseDataTable.row(this).data()
          //console.log('You clicked on ' + data[0] + '\'s row')
          selfinside.viewObjectDetails(data[0])
        })
      })
      .catch(error => {
        console.log(error)
      })
  },
  methods: {
    viewObjectDetails: function (id) {
      this.$router.push({ name: this.detailComponent, params: { id }})
    },
    frontEndDateFormat: function (date) {
      return moment(date, 'YYYY-MM-DDTHH:mm:ss.fff Z').format('DD/MM/YYYY')
    },
  }
}

</script>
