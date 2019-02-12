import { dataResponseService } from '../_services';
import { router } from '../_helpers';

export const browse = {
    namespaced: true,
    state: {
        loading: false,
        data: {},
        error: {},
        metadata: {},
    },
    actions: {
        getBrowseData({ dispatch, commit },{ requestPage, processName, dataContent }){
          commit('pendingRequest');
          //TODO need to change  to get Content
          //dataResponseService.getContent( requestPage, processName, dataContent  )
          dataResponseService.getBrowseData( requestPage, processName, dataContent  )
              .then(
                  content => commit('getBrowseDataSuccess', content),
                  error => commit('failureDetected', error)
              );
        },
    },
    mutations: {
        pendingRequest(state) {
            console.log('browse - mutations - pendingRequest');
            state.loading = true;
        },
        failureDetected(state, error) {
            state.loading = false;
            console.log('browse - mutations - failureDetected - '+ error);
            state.error = error;
        },
        getBrowseDataSuccess(state, content) {
            state.loading = false;
            console.log('browse - mutations  - getBrowseDataSuccess');
            state.data = content.dataBrowse.data;
            state.metadata = content.metaDataBrowse;

            var columns = [];
            var columnDefinitionArray = [];
            var orderDefinitionArray = [];
            var columnIndex = 0;
            state.metadata.dataColumns.forEach(function(columnDefinition) {
              columns.push({mData: columnDefinition.fieldDefinitionCode, sTitle: columnDefinition.dataColumnName});
              var colType = ''
              if (columnDefinition.columnType != undefined) {
                colType = ',"type": "'+columnDefinition.columnType+'"'
              }
              var visibility = ''
              if (columnDefinition.frontEndVisibility === "false") {
                visibility = ',"visible": false, "searchable": false'

              } else {
                visibility = ',"visible": true'
              }
              var jsonObject=JSON.parse('{"targets": [' + columnIndex + ']'+visibility+colType+'}')
              switch(columnDefinition.columnType){
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
              columnDefinitionArray.push(jsonObject)

              if (columnDefinition.columnOrder != undefined) {
                orderDefinitionArray.push(JSON.parse('[' + columnIndex + ',"' + columnDefinition.columnOrder + '"]'))
              }
              columnIndex++
            });

            var browseDataTable = $('#browseDataTable').DataTable({
              responsive: true,
              columnDefs: columnDefinitionArray,
              dom: 'Bfrtip',
              buttons: [
                  'copy', 'csv', 'excel', 'pdf', 'print'
              ],
              order: orderDefinitionArray,
              //data: selfVue.dataOfDatatables,
              "ajax": function (data, callback, settings) {
                  callback( { data: state.data } );
              },
              "columns": columns
            })
            $('#browseDataTable tbody').on('click', '#openMedicalAppointment', function(self) {
              //Remove event when the its on first element to dont overwrite more options button
              var data = browseDataTable.row(this).data()
              //console.log('You clicked on ' + data[0] + '\'s row')
              selfVue.viewObjectDetails(data)
            })
            //TODO needs to be changed to be dinamic and refresh target data and build directly and not in browsepage
            //var browseDataTable = $('#browseDataTable').DataTable();
            //browseDataTable.ajax.reload();
        }
    }
}
