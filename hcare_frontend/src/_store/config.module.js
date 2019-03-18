import { dataResponseService } from '../_services';
import { router } from '../_helpers';

export const config = {
    namespaced: true,
    state: {
        loading: false,
        data: {},
        labelList: [],
        error: {},
        metadata: {},
    },
    actions: {
      getPageById({ dispatch, commit }, { requestPage, processName, dataContent }) {
        commit('pendingRequest');
        dataResponseService.getContent(requestPage, processName, dataContent)
          .then(
            content => commit('getPageByIdSuccess', content),
            error => commit('failureDetected', error)
          );
      },
      getSectionById({ dispatch, commit }, { requestPage, processName, dataContent }) {
        commit('pendingRequest');
        dataResponseService.getContent(requestPage, processName, dataContent)
          .then(
            content => commit('getSectionByIdSuccess', content),
            error => commit('failureDetected', error)
          );
      },
      getFieldDefinitionById({ dispatch, commit }, { requestPage, processName, dataContent }) {
        commit('pendingRequest');
        dataResponseService.getContent(requestPage, processName, dataContent)
          .then(
            content => commit('getFieldDefinitionByIdSuccess', content),
            error => commit('failureDetected', error)
          );
      },
      getLabelById({ dispatch, commit }, { requestPage, processName, dataContent }) {
        commit('pendingRequest');
        dataResponseService.getContent(requestPage, processName, dataContent)
          .then(
            content => commit('getLabelByIdSuccess', content),
            error => commit('failureDetected', error)
          );
      },
      savePageEntity({ dispatch, commit }, { requestPage, processName, dataContent ,returnRoute }) {
        return new Promise((resolve, reject) => {
          commit('pendingRequest');
          dataResponseService.getContent(requestPage, processName, dataContent, returnRoute)
            .then(
              content => {
                commit('savePageEntitySuccess', content);
                //TODO sucess message should come from a label translated from backend
                dispatch('alert/success', 'Los datos fueron guardados satisfactoriamente', { root: true });
                //router.go(-1);
                if ( returnRoute  ) {
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
      saveSectionEntity({ dispatch, commit }, { requestPage, processName, dataContent ,returnRoute }) {
        return new Promise((resolve, reject) => {
          commit('pendingRequest');
          dataResponseService.getContent(requestPage, processName, dataContent, returnRoute)
            .then(
              content => {
                commit('saveSectionEntitySuccess', content);
                //TODO sucess message should come from a label translated from backend
                dispatch('alert/success', 'Los datos fueron guardados satisfactoriamente', { root: true });
                //router.go(-1);
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
      saveFieldDefinitionEntity({ dispatch, commit }, { requestPage, processName, dataContent ,returnRoute }) {
        return new Promise((resolve, reject) => {
          commit('pendingRequest');
          dataResponseService.getContent(requestPage, processName, dataContent, returnRoute)
            .then(
              content => {
                commit('saveFieldDefinitionEntitySuccess', content);
                //TODO sucess message should come from a label translated from backend
                dispatch('alert/success', 'Los datos fueron guardados satisfactoriamente', { root: true });
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
      saveLabelEntity({ dispatch, commit }, { requestPage, processName, dataContent ,returnRoute }) {
        return new Promise((resolve, reject) => {
          commit('pendingRequest');
          dataResponseService.getContent(requestPage, processName, dataContent, returnRoute)
            .then(
              content => {
                commit('saveLabelEntitySuccess', content);
                //TODO sucess message should come from a label translated from backend
                dispatch('alert/success', 'Los datos fueron guardados satisfactoriamente', { root: true });
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
    },
    mutations: {
      pendingRequest(state) {
        console.log('configResponse - mutations - pendingRequest');
        state.loading = true;
      },
      failureDetected(state, error) {
        state.loading = false;
        console.log('configResponse - mutations - failureDetected - ' + error);
        state.error = error;
      },
      getPageByIdSuccess(state, content) {
        state.loading = false;
        console.log('configResponse - mutations  - getPageByIdSuccess');
        state.data = content.dataContent.dataMap.page;
        state.labelList = content.dataContent.dataMap.labelList;
        state.metadata = content.metadataContent;
      },
      getSectionByIdSuccess(state, content) {
        state.loading = false;
        console.log('configResponse - mutations  - getSectionByIdSuccesss');
        state.data = content.dataContent.dataMap.section;
        state.labelList = content.dataContent.dataMap.labelList;
        state.metadata = content.metadataContent;
      },
      getFieldDefinitionByIdSuccess(state, content) {
        state.loading = false;
        console.log('configResponse - mutations  - getFieldDefinitionByIdSuccess');
        state.data = content.dataContent.dataMap.fieldDefinition;
        state.labelList = content.dataContent.dataMap.labelList;
        state.metadata = content.metadataContent;
      },
      getLabelByIdSuccess(state, content) {
        state.loading = false;
        console.log('configResponse - mutations  - getLabelByIdSuccess');
        state.data = content.dataContent.dataMap.label;
        state.metadata = content.metadataContent;
      },
      savePageEntitySuccess(state, content) {
        state.loading = false;
        console.log('configResponse - mutations  - savePageEntitySuccess');
        state.data = content.dataContent.dataMap.pageData;
        state.metadata = content.metadataContent;
      },
      saveSectionEntitySuccess(state, content) {
        state.loading = false;
        console.log('configResponse - mutations  - saveSectionEntitySuccess');
        state.data = content.dataContent.dataMap.section;
        state.metadata = content.metadataContent;
      },
      saveFieldDefinitionEntitySuccess(state, content) {
        state.loading = false;
        console.log('configResponse - mutations  - saveFieldDefinitionEntitySuccess');
        state.data = content.dataContent.dataMap.fieldDefinition;
        state.metadata = content.metadataContent;
      },
      saveLabelEntitySuccess(state, content) {
        state.loading = false;
        console.log('configResponse - mutations  - saveLabelEntitySuccess');
        state.data = content.dataContent.dataMap.label;
        state.metadata = content.metadataContent;
      },
    }
}
