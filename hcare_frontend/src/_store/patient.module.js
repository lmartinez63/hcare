import {
  dataResponseService
} from '../_services';
import {
  router
} from '../_helpers';

export const patient = {
  namespaced: true,
  state: {
    loading: false,
    data: {},
    error: {},
    metadata: {},
  },
  actions: {
    getById({ dispatch, commit }, { requestPage, processName, dataContent }) {
      commit('pendingRequest');
      dataResponseService.getContent(requestPage, processName, dataContent)
        .then(
          content => commit('getByIdSuccess', content),
          error => commit('failureDetected', error)
        );
    },
    saveEntity({ dispatch, commit }, { requestPage, processName, dataContent , returnRoute}) {
      return new Promise((resolve, reject) => {
        commit('pendingRequest');
        dataResponseService.getContent(requestPage, processName, dataContent, returnRoute)
          .then(
            content => {
              commit('saveEntitySuccess', content);
              dispatch('alert/success', 'Los datos fueron guardados satisfactoriamente', { root: true });
              if ( returnRoute ) {
                setTimeout(() => {
                  router.push(returnRoute);
                },5000)
              }
              resolve({status:200});
            },
            error => {
                commit('failureDetected', error);
                dispatch('alert/error', error, { root: true });
            }
          );
      })
    }
  },
  mutations: {
    pendingRequest(state) {
      console.log('patientResponse - mutations - pendingRequest');
      state.loading = true;
    },
    failureDetected(state, error) {
      state.loading = false;
      console.log('patientResponse - mutations - failureDetected - ' + error);
      state.error = error;
    },
    getByIdSuccess(state, content) {
      state.loading = false;
      console.log('patientResponse - mutations  - getByIdSuccess');
      state.data = content.dataContent.dataMap.patient;
      state.metadata = content.metadataContent;
    },
    saveEntitySuccess(state, content) {
      state.loading = false;
      console.log('patientResponse - mutations  - saveEntitySuccess');
      state.data = content.dataContent.dataMap.patient;
      state.metadata = content.metadataContent;
    },
  }
}
