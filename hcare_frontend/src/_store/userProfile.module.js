import { dataResponseService } from '../_services';
import { router } from '../_helpers';

export const userProfile = {
    namespaced: true,
    state: {
        loading: false,
        data: {},
        error: {},
        metadata: {},
    },
    actions: {
        getById({ dispatch, commit }, { vm, requestPage, processName, dataContent }) {
          commit('general/setLoading',true,{ root: true });
          commit('pendingRequest');
          dataResponseService.getContent(requestPage, processName, dataContent)
            .then(
              content => {
                commit('getByIdSuccess', { vm ,content } );
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
                  dispatch('alert/success', { vm: vm, message: 'Los datos fueron guardados satisfactoriamente', returnRoute: returnRoute }, { root: true });
                  resolve({status:200});
                },
                error => {
                    commit('failureDetected', error);
                    dispatch('alert/error', error, { root: true });
                }
              );
          })
        },
    },
    mutations: {
        pendingRequest(state) {
            console.log('userProfileResponse - mutations - pendingRequest');
            state.loading = true;
        },
        failureDetected(state, error) {
            state.loading = false;
            console.log('userProfileResponse - mutations - failureDetected - '+ error);
            state.error = error;
        },
        getByIdSuccess(state, { vm, content }) {
            state.loading = false;
            console.log('userProfileResponse - mutations  - getByIdSuccess');
            state.data = content.dataContent.dataMap.userProfile;
            state.data.selectedRoles = [];
            for(var upr in state.data.roles){
              state.data.selectedRoles.push(state.data.roles[upr].id);
            }

            state.metadata = content.metadataContent;
        },
        saveEntitySuccess(state, content) {
            state.loading = false;
            console.log('userProfileResponse - mutations  - getByIdSuccess');
            state.data = content.dataContent.dataMap.medicalAppointment;
            state.metadata = content.metadataContent;
        },
    }
}
