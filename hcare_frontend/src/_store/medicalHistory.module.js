import { dataResponseService } from '../_services';
import { router } from '../_helpers';

export const medicalHistory = {
  namespaced: true,
  state: {
    loading: false,
    data: {},
    error: {},
    metadata: {},
  },
  actions: {
    getHistoryCode({ dispatch, commit }, { requestPage, processName, dataContent }) {
      commit('general/setLoading',true,{ root: true });
      commit('pendingRequest');
      dataResponseService.getContent(requestPage, processName, dataContent)
        .then(
          content => {
            commit('getHistoryCodeSuccess', content);
            commit('general/setLoading',false,{ root: true });
          },
          error => {
            commit('failureDetected', error)
            dispatch('alert/error', error, { root: true });
          }
        );
    },
    saveEntity({ dispatch, commit }, { vm, requestPage, processName, dataContent }) {
      return new Promise((resolve, reject) => {
        commit('general/setLoading',true,{ root: true });
        commit('pendingRequest');
        dataResponseService.getContent(requestPage, processName, dataContent)
          .then(
            content => {
              commit('saveEntitySuccess', content);
              dispatch('alert/success', {"vm": vm, "message":'Los datos fueron guardados satisfactoriamente'}, { root: true });
              commit('general/setLoading',false,{ root: true });
              /*
              router.push({
                name: 'BrowseComponent',
                params: {
                  browseName: 'allMedAppHeaderView',
                  entityId: 'null'
                }
              })*/
              resolve({status:200});
            },
            error => {
              commit('failureDetected', error),
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
      commit('pendingRequest');
      dataResponseService.getContent(requestPage, processName, dataContent)
        .then(
          content => commit('getPatientInfoByDocumentNumberOnMedAppointmentSuccess', content),
          error => commit('failureDetected', error)
        );
    }
  },
  mutations: {
    pendingRequest(state) {
      console.log('medicalHistoryResponse - mutations - pendingRequest');
      state.loading = true;
    },
    failureDetected(state, error) {
      state.loading = false;
      console.log('medicalHistoryResponse - mutations - failureDetected - ' + error);
      state.error = error;
    },
    getHistoryCodeSuccess(state, content) {
      state.loading = false;
      console.log('medicalHistoryResponse - mutations  - getHistoryCodeSuccess');
      state.data = content.dataContent.dataMap.medicalHistory;
      state.metadata = content.metadataContent;
    },
    saveEntitySuccess(state, content) {
      state.loading = false;
      console.log('medicalHistoryResponse - mutations  - getHistoryCodeSuccess');
      state.data = content.dataContent.dataMap.medicalHistory;
      state.metadata = content.metadataContent;
    },
    getPatientInfoByDocumentNumberOnMedAppointmentSuccess(state, content) {
      state.loading = false;
      console.log('medicalHistoryResponse - mutations - getPatientInfoByDocumentNumberOnMedAppointmentSuccess');
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
