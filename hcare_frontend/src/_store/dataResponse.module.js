import { dataResponseService } from '../_services';

export const dataResponse = {
    namespaced: true,
    state: {
        loading: false,
        content: {
          dataContent: {
              dataMap: { }
            }
        },
    },
    actions: {
        getMedicalAppointmentByById({ dispatch, commit }, medicalAppointmentId ) {
            commit('getMedicalAppointmentByIdRequest');
            dataResponseService.getMedicalAppointmentByById( medicalAppointmentId )
                .then(
                    medicalAppointment => commit('getMedicalAppointmentByIdSuccess', medicalAppointment),
                    error => commit('getMedicalAppointmentByIdFailure', error)
                );

        },
        getContent({ dispatch, commit },{ requestPage, processName, dataContent }){
          commit('getContentRequest');
          dataResponseService.getContent( requestPage, processName, dataContent  )
              .then(
                  content => commit('getContentSuccess', content),
                  error => commit('getContentFailure', error)
              );
        },
        getPatientInfoByDocumentNumberOnMedAppointment({ dispatch, commit }, { documentNumber } ) {
            commit('getPatientInfoByDocumentNumberOnMedAppointmentRequest');
            dataResponseService.getPatientInfoByDocumentNumberOnMedAppointment( documentNumber )
                .then(
                    patient => commit('getPatientInfoByDocumentNumberOnMedAppointmentSuccess', patient),
                    error => commit('getPatientInfoByDocumentNumberOnMedAppointmentFailure', error)
                );
        }
    },
    mutations: {
        getContentRequest(state) {
            console.log('dataResponse - mutations - getMedicalAppointmentByIdRequest');
            state.loading = true;
        },
        getContentSuccess(state, content) {
            console.log('dataResponse - mutations - getMedicalAppointmentByIdSuccess');
            state.content.dataContent.dataMap = content.dataContent.dataMap;
        },
        getMedicalAppointmentByIdFailure(state, error) {
            console.log('dataResponse - mutations - getMedicalAppointmentByIdFailure'+ error);
            state.content = { error };
        },
        getPatientInfoByDocumentNumberOnMedAppointmentRequest(state) {
            console.log('dataResponse - mutations - getPatientInfoByDocumentNumberOnMedAppointmentRequest');
            state.medicalAppointment.loading = true;
        },
        getPatientInfoByDocumentNumberOnMedAppointmentSuccess(state, patient) {
            console.log('dataResponse - mutations - getPatientInfoByDocumentNumberOnMedAppointmentSuccess');
            if ( patient != null && patient != '' ) {
              state.medicalAppointment.firstName = patient.firstName
              state.medicalAppointment.lastName = patient.lastName
              state.medicalAppointment.celPhoneNumber = patient.celPhoneNumber
              state.medicalAppointment.emailAddress = patient.emailAddress
              state.medicalAppointment.historyCode = patient.historyCode
            }
        },
        getPatientInfoByDocumentNumberOnMedAppointmentFailure(state, error) {
            console.log('dataResponse - mutations - getPatientInfoByDocumentNumberOnMedAppointment' + error);
            //state.medicalAppointment = { error };
        },
    }
}
