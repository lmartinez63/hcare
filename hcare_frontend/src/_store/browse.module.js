import { dataResponseService } from '../_services';
import { router } from '../_helpers';

export const browse = {
    namespaced: true,
    state: {
        loading: false,
        data: {},
        browseDataTable: null,
        error: {},
        metadata: {},
    },
    actions: {
        getBrowseData({ dispatch, commit },{ requestPage, processName, dataContent, vueInstance }){
            commit('pendingRequest');
            //TODO need to change  to get Content
            //dataResponseService.getContent( requestPage, processName, dataContent  )
            dataResponseService.getBrowseData( requestPage, processName, dataContent, vueInstance  )
              .then(
                  content => commit('getBrowseDataSuccess', content, vueInstance),
                  error => commit('failureDetected', error)
              );
        },
        reloadBrowseData({ dispatch, commit },{ dataContent}){
            commit('pendingRequest');
            //TODO need to change  to get Content
            //dataResponseService.getContent( requestPage, processName, dataContent  )
            dataResponseService.getBrowseData( '', '', dataContent, null  )
              .then(
                  content => commit('reloadBrowseDataSuccess', content),
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
        reloadBrowseDataSuccess(state, content) {
            state.loading = false;
            console.log('browse - mutations  - reloadBrowseDataSuccess');
            state.data = content.dataBrowse.data;
            state.metadata = content.metaDataBrowse;
            state.browseDataTable.ajax.reload( null, false );
        },
        getBrowseDataSuccess(state, content, vueInstance) {
            state.loading = false;
            console.log('browse - mutations  - getBrowseDataSuccess');
            state.data = content.dataBrowse.data;
            state.metadata = content.metaDataBrowse;

            var columns = [];
            var columnDefinitionArray = [];
            var orderDefinitionArray = [];
            var columnIndex = 0;
            state.metadata.dataColumns.forEach(function(columnDefinition) {
              columns.push({mData: columnDefinition.dataColumnCode, sTitle: columnDefinition.dataColumnName});
              var colType = ''
              if (columnDefinition.columnType != undefined && columnDefinition.columnType !== 'rowButtons') {
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
                case 'rowButtons':
                  jsonObject.defaultContent = "";
                  //jsonObject.data = null;
                  state.metadata.dataRowButtons.forEach(function(rowButton) {
                    //TODO set labelValue as user language
                    var visibility = eval(rowButton.visibilityCondition);
                    if ( visibility == null || visibility == 'true' ) {
                        jsonObject.defaultContent = jsonObject.defaultContent + "<div id='"+rowButton.dataRowButtonCode+"' data-funtion='"+rowButton.dataRowButtonEvent+"' >"+rowButton.label.labelValueEsEs+"</div>";
                    }
                  })
                  break;
              };
              columnDefinitionArray.push(jsonObject);

              if (columnDefinition.columnOrder != undefined) {
                orderDefinitionArray.push(JSON.parse('[' + columnIndex + ',"' + columnDefinition.columnOrder + '"]'))
              }
              columnIndex++
            });

            state.browseDataTable = $('#browseDataTable').DataTable({
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

        },
    }
}
