import {
  dataResponseService
} from '../_services';
import {
  router
} from '../_helpers';

export const medicalAppointment = {
  namespaced: true,
  state: {
    loading: false,
    data: {},
    error: {},
    metadata: {},
  },
  actions: {
    getById({ dispatch, commit }, { requestPage, processName, dataContent }) {
      commit('general/setLoading',true,{ root: true });
      commit('pendingRequest');
      dataResponseService.getContent(requestPage, processName, dataContent)
        .then(
          content => {
            commit('getByIdSuccess', content);
            commit('general/setLoading',false,{ root: true });
          },
          error => commit('failureDetected', error)
        );
    },
    saveEntity({ dispatch, commit }, { vm, requestPage, processName, dataContent ,returnRoute }) {
      return new Promise((resolve, reject) => {
        commit('general/setLoading',true,{ root: true });
        commit('pendingRequest');
        dataResponseService.getContent(requestPage, processName, dataContent, returnRoute)
          .then(
            content => {
              commit('saveEntitySuccess', content);
              commit('general/setLoading',false,{ root: true });
              //TODO sucess message should come from a label translated from backend
              dispatch('alert/success', {vm: vm,message: 'Los datos fueron guardados satisfactoriamente'}, { root: true });
              if ( returnRoute ) {
                  router.push(returnRoute);
              }
              resolve({status:200});
            },
            error => {
                commit('failureDetected', error);
                dispatch('alert/error', error, { root: true });
            }
          );
      })
    },
    getPatientInfoByDocumentNumberOnMedAppointment({
      dispatch,
      commit
    }, {
      requestPage,
      processName,
      dataContent
    }) {
      commit('general/setLoading',true,{ root: true });
      commit('pendingRequest');
      dataResponseService.getContent(requestPage, processName, dataContent)
        .then(
          content => {
            commit('getPatientInfoByDocumentNumberOnMedAppointmentSuccess', content)
            commit('general/setLoading',false,{ root: true });
          },
          error => commit('failureDetected', error)
        );
    }
  },
  mutations: {
    pendingRequest(state) {
      console.log('medicalAppointmentResponse - mutations - pendingRequest');
      state.loading = true;
    },
    failureDetected(state, error) {
      state.loading = false;
      console.log('medicalAppointmentResponse - mutations - failureDetected - ' + error);
      state.error = error;
    },
    getByIdSuccess(state, content) {
      state.loading = false;
      console.log('medicalAppointmentResponse - mutations  - getByIdSuccess');
      state.data = content.dataContent.dataMap.medicalAppointment;
      state.metadata = content.metadataContent;
    },
    saveEntitySuccess(state, content) {
      state.loading = false;
      console.log('medicalAppointmentResponse - mutations  - saveEntitySuccess');
      state.data = content.dataContent.dataMap.medicalAppointment;
      state.metadata = content.metadataContent;
    },
    getPatientInfoByDocumentNumberOnMedAppointmentSuccess(state, content) {
      state.loading = false;
      console.log('medicalAppointmentResponse - mutations - getPatientInfoByDocumentNumberOnMedAppointmentSuccess');
      if (content && content != null && content != '') {
        state.data.firstName = content.dataContent.dataMap.patient.firstName
        state.data.lastName = content.dataContent.dataMap.patient.lastName
        state.data.celPhoneNumber = content.dataContent.dataMap.patient.celPhoneNumber
        state.data.emailAddress = content.dataContent.dataMap.patient.emailAddress
        state.data.historyCode = content.dataContent.dataMap.patient.historyCode
      }
    },
  }
}
