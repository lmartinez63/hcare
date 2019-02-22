import { dataResponseService } from '../_services';
import { router } from '../_helpers';

export const medicalArea = {
    namespaced: true,
    state: {
        loading: false,
        data: {},
        error: {},
        metadata: {},
    },
    actions: {
        getById({ dispatch, commit },{ requestPage, processName, dataContent }){
          commit('pendingRequest');
          dataResponseService.getContent( requestPage, processName, dataContent  )
              .then(
                  content => commit('getByIdSuccess', content),
                  error => commit('failureDetected', error)
              );
        },
        saveEntity({ dispatch, commit },{ requestPage, processName, dataContent }){
          commit('pendingRequest');
          dataResponseService.getContent( requestPage, processName, dataContent  )
              .then(
                  content => {
                    commit('saveEntitySuccess', content);
                    router.push({
                      name: 'BrowseComponent',
                      params: { browseType: 'allMedicalAppointments', entityId: 'null' }
                    })
                  },
                  error => commit('failureDetected', error)
              );
        }
    },
    mutations: {
        pendingRequest(state) {
            console.log('medicalAreaResponse - mutations - pendingRequest');
            state.loading = true;
        },
        failureDetected(state, error) {
            state.loading = false;
            console.log('medicalAreaResponse - mutations - failureDetected - '+ error);
            state.error = error;
        },
        getByIdSuccess(state, content) {
            state.loading = false;
            console.log('medicalAreaResponse - mutations  - getByIdSuccess');
            state.data = content.dataContent.dataMap.medicalArea;
            state.metadata = content.metadataContent;
        },
        saveEntitySuccess(state, content) {
            state.loading = false;
            console.log('medicalAreaResponse - mutations  - getByIdSuccess');
            state.data = content.dataContent.dataMap.medicalArea;
            state.metadata = content.metadataContent;
        }
    }
}
