import { generalService } from '../_services';

const properties = JSON.parse(localStorage.getItem('properties'));
const doctors = JSON.parse(localStorage.getItem('doctors'));
const medicalAreas = JSON.parse(localStorage.getItem('medicalAreas'));

const initialStateProperties = properties ? { properties } : { properties: null };
const initialStateDoctors = doctors ? { doctors } : { doctors: null };
const initialStateMedicalAreas = medicalAreas ? { medicalAreas } : { medicalAreas: null };

export const general = {
    namespaced: true,
    state: {
        properties: initialStateProperties,
        doctors: initialStateDoctors,
        medicalAreas: initialStateMedicalAreas
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
      getDoctors({ dispatch, commit }) {
          commit('getDoctorsRequest');
          generalService.getDoctors()
              .then(
                  doctors => commit('getDoctorsSuccess', doctors),
                  error => commit('getDoctorsFailure', error)
              );
      },
      getMedicalAreas({ dispatch, commit }) {
          commit('getMedicalAreasRequest');
          generalService.getMedicalAreas()
              .then(
                  medicalAreas => commit('getMedicalAreasSuccess', medicalAreas),
                  error => commit('getMedicalAreasFailure', error)
              );
      }
    },
    mutations: {
        loadPropertiesRequest(state) {
            console.log('generalStore - mutations - loadPropertiesRequest');
            state.properties = { loading: true };
        },
        getMedicalAreasRequest(state) {
            console.log('generalStore - mutations - getMedicalAreasRequest');
            state.medicalAreas = { loading: true };
        },
        getDoctorsRequest(state) {
            console.log('generalStore - mutations - getDoctorsRequest');
            state.doctors = { loading: true };
        },
        loadPropertiesSuccess(state,properties) {
            console.log('generalStore - mutations - loadPropertiesSuccess');
            state.properties = { items: properties };
        },
        getDoctorsSuccess(state,doctors) {
            console.log('generalStore - mutations - getDoctorsSuccess');
            state.doctors = { items: doctors };
        },
        getMedicalAreasSuccess(state,medicalAreas) {
            console.log('generalStore - mutations - getDoctorsSuccess');
            state.medicalAreas = { items: medicalAreas };
        },
        loadPropertiesFailure(state, error) {
            console.log('generalStore - mutations - loadPropertiesFailure');
            state.properties = { error };
        },
        getDoctorsFailure(state, error) {
            console.log('generalStore - mutations - getDoctorsFailure');
            state.doctors = { error };
        },
        getMedicalAreasFailure(state, error) {
            console.log('generalStore - mutations - getMedicalAreasFailure');
            state.medicalAreas = { error };
        }

    }
}
