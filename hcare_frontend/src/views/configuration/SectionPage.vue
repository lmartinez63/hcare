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
              <div v-for="button in page.pageButtons"  v-if="button.visible" v-on:click="executeAction(button)" class="link">
                <div class="icon"><i class="button.icon"></i></div>
                <div class="text">{{$parent.getLabelValue(button.label)}}</div>
              </div>
            </div>
          </div>
        </div>
        <div class="formBox">
          <div class="headerFacNew">
            <div v-if="page.sectionMap.sectionInfo && page.sectionMap.sectionInfo.visible && page.sectionMap.sectionInfo.fieldDefinitionMap" class="subTitle">
              {{$parent.getLabelValue(page.sectionMap.sectionInfo.label)}}
            </div>
            <div v-if="page.sectionMap.sectionInfo && page.sectionMap.sectionInfo.visible && page.sectionMap.sectionInfo.fieldDefinitionMap" class="twoCol">
              <div v-if="page.sectionMap.sectionInfo.fieldDefinitionMap.sectionCode && page.sectionMap.sectionInfo.fieldDefinitionMap.sectionCode.visible" class="group">
                <div class="form-group mdl-textfield mdl-js-textfield mdl-textfield--floating-label" :class="{ 'form-group--error': $v.section.sectionCode.$error }">
                  <input class="form__input mdl-textfield__input" type="text" name="section-sectionCode" id="section-sectionCode" v-model.trim="$v.section.sectionCode.$model" />
                  <label class="form__label labelText" for="section-sectionCode">{{$parent.getLabelValue( page.sectionMap.sectionInfo.fieldDefinitionMap.sectionCode.label)}}</label>
                </div>
                <div class="error" v-if="!$v.section.sectionCode.required">Codigo de Seccion requerido</div>
              </div>
              <div v-if="page.sectionMap.sectionInfo.fieldDefinitionMap.visibleRuleExp && page.sectionMap.sectionInfo.fieldDefinitionMap.visibleRuleExp.visible" class="group">
                <div class="form-group mdl-textfield mdl-js-textfield mdl-textfield--floating-label" :class="{ 'form-group--error': $v.section.visibleRuleExp.$error }">
                  <input class="form__input mdl-textfield__input" type="text" name="section-visibleRuleExp" id="section-visibleRuleExp" v-model.trim="$v.section.visibleRuleExp.$model" />
                  <label class="form__label labelText" for="section-visibleRuleExp">{{$parent.getLabelValue( page.sectionMap.sectionInfo.fieldDefinitionMap.visibleRuleExp.label)}}</label>
                </div>
                <div class="error" v-if="!$v.section.visibleRuleExp.required">Expresion de visibilidad requerida</div>
              </div>
              <div class="groupFull">
                <label class="typo__label">Label</label>
                <multiselect v-model="section.label" placeholder="Choose a label" label="title" track-by="title" :options="labelList" :option-height="104" :custom-label="customLabel" :show-labels="false">
                  <template slot="singleLabel" slot-scope="props">
                    <!--<img class="option__image" :src="props.option.img" alt="Choose a label">-->
                    <span class="option__desc">
                      <span class="option__title">{{ props.option.labelCode }}</span>
                    </span>
                  </template>
                  <template slot="option" slot-scope="props">
                    <!--<img class="option__image" :src="props.option.img" alt="No Man’s Sky">-->
                    <div class="option__desc">
                      <span class="option__title">{{ props.option.labelCode }}</span>
                      <span class="option__small">{{ props.option.labelValueEsEs }}</span>
                    </div>
                  </template>
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
  name: 'SectionPage',
  data() {
    return {
      requestPage: 'sectionInfo',
      label: null
    }
  },
  validations: {
    section:{
      sectionCode: {
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
    section() {
      return this.$store.state.config.data;
    },
    page() {
      return this.$store.state.config.metadata.page;
    },
    labelList() {
      return this.$store.state.config.labelList;
    },
  },
  created: function() {
    console.log("SectionPage - created - begin");
    const dataContent = {
      "section": {
        "id": this.$route.params.sectionId
      },
      "getLabels": "true"
    }
    const {
      requestPage
    } = this;
    const {
      dispatch
    } = this.$store;
    dispatch('config/getSectionById', {
        requestPage: requestPage,
        processName: 'RetrieveSectionInfo',
        dataContent: dataContent
      });
    console.log("SectionPage - created - end");
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
    customLabel ({ labelCode, labelValueEsEs }) {
      return `${labelCode} – ${labelValueEsEs}`
    },
    saveObjectState: function() {
      console.log("SectionPage - method - saveObjectState - begin");
      const dataContent = {
        "section": this.section
      }
      const {
        requestPage
      } = this;
      const {
        dispatch
      } = this.$store;
      this.$v.$touch()
      if (this.$v.$invalid) {
        selfVue.$parent.errorMessage("Por favor complete los campos requeridos")
      } else {
        dispatch('config/saveSectionEntity', {
          vm: this,
          requestPage: requestPage,
          processName: 'SaveSection',
          dataContent: dataContent,
        });
      }
      console.log("SectionPage - method - saveObjectState - end");
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
