import {
  generalService,
  dataResponseService
} from '../_services';

import { router } from '../_helpers';

const properties = JSON.parse(localStorage.getItem('properties'));
const doctors = JSON.parse(localStorage.getItem('doctors'));
const medicalAreas = JSON.parse(localStorage.getItem('medicalAreas'));

const initialStateProperties = properties ? {
  properties
} : {
  properties: null
};
const initialStateDoctors = doctors ? {
  doctors
} : {
  doctors: null
};
const initialStateMedicalAreas = medicalAreas ? {
  medicalAreas
} : {
  medicalAreas: null
};

export const general = {
  namespaced: true,
  state: {
    properties: initialStateProperties,
    doctors: initialStateDoctors,
    medicalAreas: initialStateMedicalAreas,
    loading: false
  },
  actions: {
    loadProperties({ dispatch, commit }) {
      commit('loadPropertiesRequest');
      generalService.loadProperties()
        .then(
          properties => commit('loadPropertiesSuccess', properties),
          error => commit('loadPropertiesFailure', error)
        );
    },
    uploadAttachment({ dispatch, commit }, { vm, dataContent }) {
      return new Promise((resolve, reject) => {
        commit('setLoading',true);
        dataResponseService.uploadAttachment(dataContent)
          .then(
            content => {
              commit('uploadAttachmentSuccess', content);
              commit('setLoading',false);
              //TODO sucess message should come from a label translated from backend
              dispatch('alert/success', {vm: vm, message: 'Documento ha sido guardado exitosamente'}, { root: true });
              router.go(-1);
              resolve({status:200});
            },
            error => {
                commit('failureDetected', error);
                dispatch('alert/error', error, { root: true });
            }
          );
      })
    },
    downloadAttachment({ dispatch, commit }, { vm, dataContent }) {
      return new Promise((resolve, reject) => {
        commit('setLoading',true);
        dataResponseService.downloadAttachment( dataContent )
          .then(
            content => {
              commit('downloadAttachmentSuccess', content);
              //Download File directly

              const url = window.URL.createObjectURL(new Blob([content], {
                type: dataContent.attachment.contentType
              }))
              const link = document.createElement('a')
              link.href = url
              link.setAttribute('target', '_blank');
              link.setAttribute('download', dataContent.attachment.fileName)
              document.body.appendChild(link);
              link.click();

              /*
              Reload at same window
              var file = new Blob([content], {type: dataContent.attachment.contentType});
              var objectUrl = URL.createObjectURL(file);
              window.location.assign(objectUrl);
              */
              //TODO sucess message should come from a label translated from backend
              dispatch('alert/success', {vm: vm, message: 'Documento ha sido descargado exitosamente '}, { root: true });
              resolve({status:200});
            },
            error => {
                commit('failureDetected', error);
                dispatch('alert/error', error, { root: true });
            }
          );
      })
    },
    getDoctors({
      dispatch,
      commit
    }) {
      commit('getDoctorsRequest');
      generalService.getDoctors()
        .then(
          doctors => commit('getDoctorsSuccess', doctors),
          error => commit('getDoctorsFailure', error)
        );
    },
    getMedicalAreas({
      dispatch,
      commit
    }) {
      commit('getMedicalAreasRequest');
      generalService.getMedicalAreas()
        .then(
          medicalAreas => commit('getMedicalAreasSuccess', medicalAreas),
          error => commit('getMedicalAreasFailure', error)
        );
    }
  },
  mutations: {
    setLoading(state, loadingValue) {
      console.log('generalStore - mutations - setLoading ' + loadingValue);
      state.loading = loadingValue;
    },
    downloadAttachmentSuccess(state, content){
      console.log('generalStore - mutations - downloadAttachmentSuccess ');
      state.loading = false;
    },
    failureDetected(state, error) {
      state.loading = false;
      console.log('generalStore - mutations - failureDetected - ' + error);
      state.error = error;
    },
    loadPropertiesRequest(state) {
      state.loading = true;
      console.log('generalStore - mutations - loadPropertiesRequest');
      state.properties = {
        loading: true
      };
    },
    getMedicalAreasRequest(state) {
      state.loading = true;
      console.log('generalStore - mutations - getMedicalAreasRequest');
      state.medicalAreas = {
        loading: true
      };
    },
    getDoctorsRequest(state) {
      state.loading = true;
      console.log('generalStore - mutations - getDoctorsRequest');
      state.doctors = {
        loading: true
      };
    },
    loadPropertiesSuccess(state, properties) {
      state.loading = false;
      console.log('generalStore - mutations - loadPropertiesSuccess');
      state.properties = {
        items: properties
      };
    },
    getDoctorsSuccess(state, doctors) {
      state.loading = false;
      console.log('generalStore - mutations - getDoctorsSuccess');
      state.doctors = {
        items: doctors
      };
    },
    getMedicalAreasSuccess(state, medicalAreas) {
      state.loading = false;
      console.log('generalStore - mutations - getDoctorsSuccess');
      state.medicalAreas = {
        items: medicalAreas
      };
    },
    loadPropertiesFailure(state, error) {
      state.loading = false;
      console.log('generalStore - mutations - loadPropertiesFailure');
      state.properties = {
        error
      };
    },
    getDoctorsFailure(state, error) {
      state.loading = false;
      console.log('generalStore - mutations - getDoctorsFailure');
      state.doctors = {
        error
      };
    },
    getMedicalAreasFailure(state, error) {
      state.loading = false;
      console.log('generalStore - mutations - getMedicalAreasFailure');
      state.medicalAreas = {
        error
      };
    }

  }
}
