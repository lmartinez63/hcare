import { dataResponseService } from '../_services';
import { router } from '../_helpers';

export const medicalAppointment = {
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
        },
        getPatientInfoByDocumentNumberOnMedAppointment({ dispatch, commit }, { requestPage, processName, dataContent } ) {
            commit('pendingRequest');
            dataResponseService.getContent( requestPage, processName, dataContent )
                .then(
                    content => commit('getPatientInfoByDocumentNumberOnMedAppointmentSuccess', content),
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
            console.log('medicalAppointmentResponse - mutations - failureDetected - '+ error);
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
            console.log('medicalAppointmentResponse - mutations  - getByIdSuccess');
            state.data = content.dataContent.dataMap.medicalAppointment;
            state.metadata = content.metadataContent;
        },
        getPatientInfoByDocumentNumberOnMedAppointmentSuccess(state, content) {
            state.loading = false;
            console.log('medicalAppointmentResponse - mutations - getPatientInfoByDocumentNumberOnMedAppointmentSuccess');
            if ( content && content != null && content != '' ) {
              state.data.firstName = content.dataContent.dataMap.patient.firstName
              state.data.lastName = content.dataContent.dataMap.patient.lastName
              state.data.celPhoneNumber = content.dataContent.dataMap.patient.celPhoneNumber
              state.data.emailAddress = content.dataContent.dataMap.patient.emailAddress
              state.data.historyCode = content.dataContent.dataMap.patient.historyCode
            }
        },
    }
}
