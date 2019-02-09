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
            state.data = content.data;
            //TODO needs to be changed to be dinamic and refresh target data and build directly and not in browsepage
            var browseDataTable = $('#browseDataTable').DataTable();
            browseDataTable.ajax.reload();
        }
    }
}
