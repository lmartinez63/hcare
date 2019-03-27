import { dataResponseService } from '../_services';
import { router } from '../_helpers';

export const config = {
    namespaced: true,
    state: {
        loading: false,
        data: {},
        roleList: {
          items: [],
          loading: false,
        },
        pageList: {
          items: [],
          loading: false,
        },
        sectionList: {
          items: [],
          loading: false,
        },
        fieldDefinitionList: {
          items: [],
          loading: false,
        },
        labelList: [],
        error: {},
        metadata: {},
    },
    actions: {
      getRoleList({ dispatch, commit }, { requestPage, processName, dataContent }) {
        commit('pendingRoleListRequest');
        dataResponseService.getContent(requestPage, processName, dataContent)
          .then(
            content => {
              commit('getRoleListSuccess', content);
            },
            error => commit('failureDetected', error)
          );
      },
      getPageList({ dispatch, commit }, { requestPage, processName, dataContent }) {
        commit('pendingPageListRequest');
        dataResponseService.getContent(requestPage, processName, dataContent)
          .then(
            content => {
              commit('getPageListSuccess', content);
            },
            error => commit('failureDetected', error)
          );
      },
      getSectionList({ dispatch, commit }, { requestPage, processName, dataContent }) {
        commit('pendingSectionListRequest');
        dataResponseService.getContent(requestPage, processName, dataContent)
          .then(
            content => {
              commit('getSectionListSuccess', content)
            },
            error => commit('failureDetected', error)
          );
      },
      getFieldDefinitionList({ dispatch, commit }, { requestPage, processName, dataContent }) {
        commit('pendingFieldDefinitionListRequest');
        dataResponseService.getContent(requestPage, processName, dataContent)
          .then(
            content => {
              commit('getFieldDefinitionListSuccess', content)
            },
            error => commit('failureDetected', error)
          );
      },
      getRolePageSectionFieldById({ dispatch, commit }, { requestPage, processName, dataContent }) {
        commit('general/setLoading',true,{ root: true });
        commit('pendingRequest');
        dataResponseService.getContent(requestPage, processName, dataContent)
          .then(
            content => {
              commit('getRolePageSectionFieldByIdSuccess', content)
              commit('general/setLoading',false,{ root: true });
            },
            error => {
              dispatch('alert/error', error, { root: true });
              commit('failureDetected', error);
            }
          );
      },
      getPageById({ dispatch, commit }, { requestPage, processName, dataContent }) {
        commit('general/setLoading',true,{ root: true });
        commit('pendingRequest');
        dataResponseService.getContent(requestPage, processName, dataContent)
          .then(
            content => {
              commit('getPageByIdSuccess', content)
              commit('general/setLoading',false,{ root: true });
            },
            error => commit('failureDetected', error)
          );
      },
      getSectionById({ dispatch, commit }, { requestPage, processName, dataContent }) {
        commit('general/setLoading',true,{ root: true });
        commit('pendingRequest');
        dataResponseService.getContent(requestPage, processName, dataContent)
          .then(
            content => {
              commit('getSectionByIdSuccess', content)
              commit('general/setLoading',false,{ root: true });
            },
            error => commit('failureDetected', error)
          );
      },
      getFieldDefinitionById({ dispatch, commit }, { requestPage, processName, dataContent }) {
        commit('general/setLoading',true,{ root: true });
        commit('pendingRequest');
        dataResponseService.getContent(requestPage, processName, dataContent)
          .then(
            content => {
              commit('getFieldDefinitionByIdSuccess', content)
              commit('general/setLoading',false,{ root: true });
            },
            error => commit('failureDetected', error)
          );
      },
      getLabelById({ dispatch, commit }, { requestPage, processName, dataContent }) {
        commit('general/setLoading',true,{ root: true });
        commit('pendingRequest');
        dataResponseService.getContent(requestPage, processName, dataContent)
          .then(
            content => {
              commit('getLabelByIdSuccess', content)
              commit('general/setLoading',false,{ root: true });
            },
            error => commit('failureDetected', error)
          );
      },
      saveRolePageSectionFieldEntity({ dispatch, commit }, { vm, requestPage, processName, dataContent ,returnRoute }) {
        return new Promise((resolve, reject) => {
          commit('general/setLoading',true,{ root: true });
          commit('pendingRequest');
          dataResponseService.getContent(requestPage, processName, dataContent, returnRoute)
            .then(
              content => {

                commit('saveRolePageSectionFieldEntitySuccess', content);
                commit('general/setLoading',false,{ root: true });
                //TODO sucess message should come from a label translated from backend
                dispatch('alert/success', { "vm": vm , "message" : 'Los datos fueron guardados satisfactoriamente' }, { root: true });


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
      savePageEntity({ dispatch, commit }, { vm, requestPage, processName, dataContent ,returnRoute }) {
        return new Promise((resolve, reject) => {
          commit('general/setLoading',true,{ root: true });
          commit('pendingRequest');
          dataResponseService.getContent(requestPage, processName, dataContent, returnRoute)
            .then(
              content => {

                commit('savePageEntitySuccess', content);
                //TODO sucess message should come from a label translated from backend
                dispatch('alert/success', { "vm": vm, "message": 'Los datos fueron guardados satisfactoriamente'}, { root: true });
                commit('general/setLoading',false,{ root: true });
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
      saveSectionEntity({ dispatch, commit }, { vm, requestPage, processName, dataContent ,returnRoute }) {
        return new Promise((resolve, reject) => {
          commit('general/setLoading',true,{ root: true });
          commit('pendingRequest');
          dataResponseService.getContent(requestPage, processName, dataContent, returnRoute)
            .then(
              content => {
                commit('saveSectionEntitySuccess', content);
                //TODO sucess message should come from a label translated from backend
                dispatch('alert/success', { "vm": vm, "message": 'Los datos fueron guardados satisfactoriamente'}, { root: true });
                commit('general/setLoading',false,{ root: true });
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
      saveFieldDefinitionEntity({ dispatch, commit }, { vm, requestPage, processName, dataContent ,returnRoute }) {
        return new Promise((resolve, reject) => {
          commit('general/setLoading',true,{ root: true });
          commit('pendingRequest');
          dataResponseService.getContent(requestPage, processName, dataContent, returnRoute)
            .then(
              content => {
                commit('saveFieldDefinitionEntitySuccess', content);
                //TODO sucess message should come from a label translated from backend
                dispatch('alert/success', { "vm": vm, "message": 'Los datos fueron guardados satisfactoriamente'}, { root: true });
                commit('general/setLoading',false,{ root: true });
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
      saveLabelEntity({ dispatch, commit }, { vm, requestPage, processName, dataContent ,returnRoute }) {
        return new Promise((resolve, reject) => {
          commit('general/setLoading',true,{ root: true });
          commit('pendingRequest');
          dataResponseService.getContent(requestPage, processName, dataContent, returnRoute)
            .then(
              content => {
                commit('saveLabelEntitySuccess', content);
                //TODO sucess message should come from a label translated from backend
                dispatch('alert/success', { "vm": vm, "message": 'Los datos fueron guardados satisfactoriamente'}, { root: true });
                commit('general/setLoading',false,{ root: true });
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
      pendingRoleListRequest(state) {
        console.log('configResponse - mutations - pendingRoleListRequest');
        state.roleList.loading = true;
      },
      getRoleListSuccess(state, content) {
        console.log('configResponse - mutations  - getRoleListSuccess');
        state.roleList.items = content.dataContent.dataMap.roleList;
        state.roleList.loading = false;
      },
      pendingPageListRequest(state) {
        console.log('configResponse - mutations - pendingPageListRequest');
        state.pageList.loading = true;
      },
      getPageListSuccess(state, content) {
        console.log('configResponse - mutations  - getPageListSuccess');
        state.pageList.items = content.dataContent.dataMap.pageList;
        state.pageList.loading = false;
      },
      pendingSectionListRequest(state) {
        console.log('configResponse - mutations - pendingSectionListRequest');
        state.sectionList.loading = true;
      },
      getSectionListSuccess(state, content) {
        console.log('configResponse - mutations  - getSectionListSuccess');
        state.sectionList.items = content.dataContent.dataMap.sectionList;
        state.sectionList.loading = false;
      },
      pendingFieldDefinitionListRequest(state) {
        console.log('configResponse - mutations - pendingFieldDefinitionListRequest');
        state.fieldDefinitionList.loading = true;
      },
      getFieldDefinitionListSuccess(state, content) {
        console.log('configResponse - mutations  - getFieldDefinitionListSuccess');
        state.fieldDefinitionList.items = content.dataContent.dataMap.fieldDefinitionList;
        state.fieldDefinitionList.loading = false;
      },
      getRolePageSectionFieldByIdSuccess(state, content) {
        state.loading = false;
        console.log('configResponse - mutations  - getRolePageSectionFieldByIdSuccess');
        state.data = content.dataContent.dataMap.rolePageSectionField;
        state.metadata = content.metadataContent;
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
      saveRolePageSectionFieldEntitySuccess(state, content){
        state.loading = false;
        console.log('configResponse - mutations  - saveRolePageSectionFieldEntitySuccess');
        state.data = content.dataContent.dataMap.rolePageSectionField;
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
