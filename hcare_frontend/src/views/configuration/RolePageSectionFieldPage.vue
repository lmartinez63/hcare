<template>
<div class="content-container">
  <section data-ui-view="" class="view-container animate-fade-up">
    <section v-if="page && page.sectionMap" class="page">
      <div class="titleForm">{{$parent.getLabelValue(page.label)}}</div>
      <div class="row ui-section contentMain">
        <div class="btnMoreActions" onClick="openThreePoint()">
          <div class="btnMore">
            <i class="fas fa-ellipsis-v"></i>
          </div>
          <div class="moreOptions" id="moreOptions">
            <div class="menuContent">
              <div v-for="button in page.pageButtons" v-if="button.visible" v-on:click="executeAction(button)" class="link">
                <div class="icon"><i class="button.icon"></i></div>
                <div class="text">{{$parent.getLabelValue(button.label)}}</div>
              </div>
            </div>
          </div>
        </div>
        <div class="formBox">
          <div class="headerFacNew">
            <div v-if="page.sectionMap.rolePageSectionFieldInfo && page.sectionMap.rolePageSectionFieldInfo.visible && page.sectionMap.rolePageSectionFieldInfo.fieldDefinitionMap" class="subTitle">
              {{$parent.getLabelValue(page.sectionMap.rolePageSectionFieldInfo.label)}}
            </div>
            <div v-if="page.sectionMap.rolePageSectionFieldInfo && page.sectionMap.rolePageSectionFieldInfo.visible && page.sectionMap.rolePageSectionFieldInfo.fieldDefinitionMap" class="twoCol">
              <div class="groupFull">
                <label class="typo__label" for="role-search">Rol</label>
                <multiselect :disabled="isDisabled" v-model="rolePageSectionField.role" id="role-search" :custom-label="customRoleLabel" track-by="name" placeholder="Buscar un rol" open-direction="bottom" :options="roleList" :searchable="true" :loading="isSearchLoading" :internal-search="true"
                  :clear-on-select="false" :close-on-select="true" :max-height="600" :show-no-results="false" :hide-selected="false" @search-change="asyncRoleFind">
                </multiselect>
              </div>
              <div class="groupFull">
                <label class="typo__label" for="page-search">Pagina</label>
                <multiselect :disabled="isDisabled" v-model="rolePageSectionField.page" id="page-search" :custom-label="customPageLabel" track-by="pageCode" placeholder="Buscar un pagina" open-direction="bottom" :options="pageList" :searchable="true" :loading="isSearchLoading" :internal-search="true"
                  :clear-on-select="false" :close-on-select="true" :max-height="600" :show-no-results="false" :hide-selected="false" @search-change="asyncPageFind">
                </multiselect>
              </div>
              <div class="groupFull">
                <label class="typo__label" for="section-search">Seccion</label>
                <multiselect :disabled="isDisabled" v-model="rolePageSectionField.section" id="section-search" :custom-label="customSectionLabel" track-by="sectionCode" placeholder="Buscar un seccion" open-direction="bottom" :options="sectionList" :searchable="true" :loading="isSearchLoading" :internal-search="true"
                  :clear-on-select="false" :close-on-select="true" :max-height="600" :show-no-results="false" :hide-selected="false" @search-change="asyncSectionFind">
                </multiselect>
              </div>
              <div class="groupFull">
                <label class="typo__label" for="fieldDefinition-search">Campo</label>
                <multiselect :disabled="isDisabled" v-model="rolePageSectionField.fieldDefinition" id="fieldDefinition-search" :custom-label="customFieldDefinitionLabel" track-by="fieldDefinitionCode" placeholder="Buscar un campo" open-direction="bottom" :options="fieldDefinitionList" :searchable="true" :loading="isSearchLoading" :internal-search="true"
                  :clear-on-select="false" :close-on-select="true" :max-height="600" :show-no-results="false" :hide-selected="false" @search-change="asyncFieldDefinitionFind">
                </multiselect>
              </div>
            </div>
          </div>
        </div>
      </div>
    </section>
  </section>
</div>
</template>
<!--script src="https://unpkg.com/element-ui/lib/index.js"></script-->
<script>
import axios from 'axios'
import moment from 'moment'
import round from 'vue-round-filter'
import Datepicker from 'vuejs-datepicker';
import Multiselect from 'vue-multiselect'

import {
  required,
  minLength,
  between,
  email,
  maxLength
} from 'vuelidate/lib/validators'


let installed = false

export default {
  name: 'RolePageSectionFieldPage',
  data() {
    return {
      requestPage: 'rolePageSectionFieldInfo',
      //TODO Enable when doesn't have enought privs
      isDisabled: false,
      rolePageSectionFieldRequested:{
        id: {
        }
      }
    }
  },
  components: {
    Datepicker,
    Multiselect
  },
  computed: {
    rolePageSectionField() {
      return this.$store.state.config.data;
    },
    roleList() {
      return this.$store.state.config.roleList.items;
    },
    pageList() {
      return this.$store.state.config.pageList.items;
    },
    sectionList() {
      return this.$store.state.config.sectionList.items;
    },
    fieldDefinitionList() {
      return this.$store.state.config.fieldDefinitionList.items;
    },
    isSearchLoading() {
      return this.$store.state.config.fieldDefinitionList.loading;
    },
    page() {
      return this.$store.state.config.metadata.page;
    }
  },
  created: function() {
    console.log("RolePageSectionFieldPage - created - begin");
    this.rolePageSectionFieldRequested = {
      "id": {
        "roleId": this.$route.params.roleId,
        "pageId": this.$route.params.pageId,
        "sectionId": this.$route.params.sectionId,
        "fieldDefinitionId": this.$route.params.fieldDefinitionId,
      }
    };

    const dataContent = {
      "rolePageSectionField": this.rolePageSectionFieldRequested
    }
    const {
      requestPage
    } = this;
    const {
      dispatch
    } = this.$store;
    dispatch('config/getRolePageSectionFieldById', {
      requestPage: requestPage,
      processName: 'RetrieveRolePageSectionFieldInfo',
      dataContent: dataContent
    });
    console.log("RolePageSectionFieldPage - created - end");
  },
  methods: {
    asyncRoleFind(query) {
      const dataContent = {}
      const {
        requestPage
      } = this;
      const {
        dispatch
      } = this.$store;
      dispatch('config/getRoleList', {
        requestPage: requestPage,
        processName: 'RetrieveAllRoles',
        dataContent: dataContent
      });
    },
    asyncPageFind(query) {
      const dataContent = {}
      const {
        requestPage
      } = this;
      const {
        dispatch
      } = this.$store;
      dispatch('config/getPageList', {
        requestPage: requestPage,
        processName: 'RetrieveAllPages',
        dataContent: dataContent
      });
    },
    asyncSectionFind(query) {
      const dataContent = {}
      const {
        requestPage
      } = this;
      const {
        dispatch
      } = this.$store;
      dispatch('config/getSectionList', {
        requestPage: requestPage,
        processName: 'RetrieveAllSections',
        dataContent: dataContent
      });
    },
    asyncFieldDefinitionFind(query) {
      const dataContent = {}
      const {
        requestPage
      } = this;
      const {
        dispatch
      } = this.$store;
      dispatch('config/getFieldDefinitionList', {
        requestPage: requestPage,
        processName: 'RetrieveAllFieldDefinitions',
        dataContent: dataContent
      });
    },
    customRoleLabel({
      name
    }) {
      return name
    },
    customPageLabel({
      label,
      pageCode
    }) {
      return pageCode ? `${pageCode} – ${label.labelValueEsEs}` : ''
    },
    customSectionLabel({
      label,
      sectionCode
    }) {
      return sectionCode ? `${sectionCode} – ${label.labelValueEsEs}` : ''
    },
    customFieldDefinitionLabel({
      label,
      fieldDefinitionCode
    }) {
      return fieldDefinitionCode ? `${fieldDefinitionCode} – ${label.labelValueEsEs}` : ''
    },
    executeAction: function(button) {
      let selfVue = this
      switch (button.buttonType) {
        case 1:
          var routeObject = {};
          var jsonString = button.eventDefinition;
          var eventArray = button.eventDefinition.match(/\${{(.*?)}}/g);
          for (var i = 0, len = eventArray.length; i < len; i++) {
            var dataRouteVariable = eventArray[i];
            jsonString = jsonString.replace(dataRouteVariable, eval(dataRouteVariable.match(/\$\{\{([^)]+)\}\}/)[1]));
          }
          routeObject = JSON.parse(jsonString);
          this.$router.push(routeObject);
          break;
        case 2:
          eval(button.eventDefinition);
          break;
      }

    },
    saveObjectState: function() {
      console.log("RolePageSectionFieldPage - method - saveObjectState - begin");
      const dataContent = {
        "rolePageSectionField": {
            "id":{
              "roleId": this.rolePageSectionField.role.id,
              "pageId": this.rolePageSectionField.page.id,
              "sectionId": this.rolePageSectionField.section.id,
              "fieldDefinitionId": this.rolePageSectionField.fieldDefinition.id,
            }
        },
        "rolePageSectionFieldRequested" : this.rolePageSectionFieldRequested
      }
      const {
        requestPage
      } = this;
      const {
        dispatch
      } = this.$store;
      dispatch('config/saveRolePageSectionFieldEntity', {
          vm: this,
          requestPage: requestPage,
          processName: 'SaveRolePageSectionField',
          dataContent: dataContent,
          //returnRoute: returnRoute,
      });
      console.log("RolePageSectionFieldPage - method - saveObjectState - end");
    },
  },
  filters: {
    capitalize: function(value) {
      if (!value) return ''
      value = value.toString()
      return value.charAt(0).toUpperCase() + value.slice(1)
    },
    round
  }
}
</script>
