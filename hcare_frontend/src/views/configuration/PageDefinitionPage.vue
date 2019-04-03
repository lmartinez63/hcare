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
            <div v-if="page.sectionMap.pageInfo && page.sectionMap.pageInfo.visible && page.sectionMap.pageInfo.fieldDefinitionMap" class="subTitle">
              {{$parent.getLabelValue(page.sectionMap.pageInfo.label)}}
            </div>
            <div v-if="page.sectionMap.pageInfo && page.sectionMap.pageInfo.visible && page.sectionMap.pageInfo.fieldDefinitionMap" class="twoCol">
              <div v-if="page.sectionMap.pageInfo.fieldDefinitionMap.pageCode && page.sectionMap.pageInfo.fieldDefinitionMap.pageCode.visible" class="group">
                <div class="form-group mdl-textfield mdl-js-textfield mdl-textfield--floating-label" :class="{ 'form-group--error': $v.pageData.pageCode.$error }">
                  <input class="form__input mdl-textfield__input" type="text" name="page-pageCode" id="page-pageCode" v-model.trim="$v.pageData.pageCode.$model" />
                  <label class="form__label labelText" for="page-pageCode">{{$parent.getLabelValue( page.sectionMap.pageInfo.fieldDefinitionMap.pageCode.label)}}</label>
                </div>
                <div class="error" v-if="!$v.pageData.pageCode.required">Codigo de Pagina requerido</div>
              </div>
              <div v-if="page.sectionMap.pageInfo.fieldDefinitionMap.visibleRuleExp && page.sectionMap.pageInfo.fieldDefinitionMap.visibleRuleExp.visible" class="group">
                <div class="form-group mdl-textfield mdl-js-textfield mdl-textfield--floating-label" :class="{ 'form-group--error': $v.pageData.visibleRuleExp.$error }">
                  <input class="form__input mdl-textfield__input" type="text" name="page-visibleRuleExp" id="page-visibleRuleExp" v-model.trim="$v.pageData.visibleRuleExp.$model" />
                  <label class="form__label labelText" for="page-visibleRuleExp">{{$parent.getLabelValue( page.sectionMap.pageInfo.fieldDefinitionMap.visibleRuleExp.label)}}</label>
                </div>
                <div class="error" v-if="!$v.pageData.visibleRuleExp.required">Expresion de visibilidad requerida</div>
              </div>
              <div v-if="pageData.label" class="groupFull">
                <label class="typo__label">Label</label>
                <multiselect v-model="pageData.label" :options="labelList" :custom-label="customLabel" placeholder="Select one Label" label="labelCode" track-by="labelCode"></multiselect>
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
  name: 'PageDefinitionPage',
  data() {
    return {
      requestPage: 'pageInfo',
      label: null
    }
  },
  validations: {
    pageData: {
      pageCode: {
        required,
      },
      visibleRuleExp: {
        required,
      },
    }
  },
  components: {
    Datepicker,
    Multiselect
  },
  computed: {
    pageData() {
      return this.$store.state.config.data;
    },
    labelList() {
      return this.$store.state.config.labelList;
    },
    page() {
      return this.$store.state.config.metadata.page;
    }
  },
  created: function() {
    console.log("PageDefinitionPage - created - begin");
    const dataContent = {
      "page": {
        "id": this.$route.params.pageId
      },
      "getLabels": "true"
    }
    const {
      requestPage
    } = this;
    const {
      dispatch
    } = this.$store;
    dispatch('config/getPageById', {
      requestPage: requestPage,
      processName: 'RetrievePageInfo',
      dataContent: dataContent
    });
    console.log("PageDefinitionPage - created - end");
  },
  methods: {
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
    customLabel({
      labelCode,
      labelValueEsEs
    }) {
      return `Codigo: ${labelCode} - Traduccion: ${labelValueEsEs}`
    },
    saveObjectState: function() {
      console.log("PageDefinitionPage - method - saveObjectState - begin");
      const dataContent = {
        "page": this.pageData
      }
      const {
        requestPage
      } = this;
      const {
        dispatch
      } = this.$store;
      this.$v.$touch()
      if (this.$v.$invalid) {
        dispatch('alert/warning', "Por favor complete los campos requeridos");
      } else {
        dispatch('config/savePageEntity', {
          vm: this,
          requestPage: requestPage,
          processName: 'SavePage',
          dataContent: dataContent,
//          returnRoute: returnRoute,
        });
      }
      console.log("PageDefinitionPage - method - saveObjectState - end");
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
