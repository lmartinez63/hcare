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
            <div v-if="page.sectionMap.fieldDefinitionInfo && page.sectionMap.fieldDefinitionInfo.visible && page.sectionMap.fieldDefinitionInfo.fieldDefinitionMap" class="subTitle">
              {{$parent.getLabelValue(page.sectionMap.fieldDefinitionInfo.label)}}
            </div>
            <div v-if="page.sectionMap.fieldDefinitionInfo && page.sectionMap.fieldDefinitionInfo.visible && page.sectionMap.fieldDefinitionInfo.fieldDefinitionMap" class="twoCol">
              <div v-if="page.sectionMap.fieldDefinitionInfo.fieldDefinitionMap.fieldDefinitionCode && page.sectionMap.fieldDefinitionInfo.fieldDefinitionMap.fieldDefinitionCode.visible" class="group">
                <div class="form-group mdl-textfield mdl-js-textfield mdl-textfield--floating-label" :class="{ 'form-group--error': $v.fieldDefinition.fieldDefinitionCode.$error }">
                  <input class="form__input mdl-textfield__input" type="text" name="fieldDefinition-fieldDefinitionCode" id="fieldDefinition-fieldDefinitionCode" v-model.trim="$v.fieldDefinition.fieldDefinitionCode.$model" />
                  <label class="form__label labelText" for="fieldDefinition-fieldDefinitionCode">{{$parent.getLabelValue( page.sectionMap.fieldDefinitionInfo.fieldDefinitionMap.fieldDefinitionCode.label)}}</label>
                </div>
                <div class="error" v-if="!$v.fieldDefinition.fieldDefinitionCode.required">Codigo de Campo requerido</div>
              </div>
              <div v-if="page.sectionMap.fieldDefinitionInfo.fieldDefinitionMap.visibleRuleExp && page.sectionMap.fieldDefinitionInfo.fieldDefinitionMap.visibleRuleExp.visible" class="group">
                <div class="form-group mdl-textfield mdl-js-textfield mdl-textfield--floating-label" :class="{ 'form-group--error': $v.fieldDefinition.visibleRuleExp.$error }">
                  <input class="form__input mdl-textfield__input" type="text" name="fieldDefinition-visibleRuleExp" id="fieldDefinition-visibleRuleExp" v-model.trim="$v.fieldDefinition.visibleRuleExp.$model" />
                  <label class="form__label labelText" for="fieldDefinition-visibleRuleExp">{{$parent.getLabelValue( page.sectionMap.fieldDefinitionInfo.fieldDefinitionMap.visibleRuleExp.label)}}</label>
                </div>
                <div class="error" v-if="!$v.fieldDefinition.visibleRuleExp.required">Expresion de visibilidad requerida</div>
              </div>
              <div v-if="page.sectionMap.fieldDefinitionInfo.fieldDefinitionMap.editRuleExp && page.sectionMap.fieldDefinitionInfo.fieldDefinitionMap.editRuleExp.visible" class="group">
                <div class="form-group mdl-textfield mdl-js-textfield mdl-textfield--floating-label" :class="{ 'form-group--error': $v.fieldDefinition.editRuleExp.$error }">
                  <input class="form__input mdl-textfield__input" type="text" name="fieldDefinition-editRuleExp" id="fieldDefinition-editRuleExp" v-model.trim="$v.fieldDefinition.editRuleExp.$model" />
                  <label class="form__label labelText" for="fieldDefinition-editRuleExp">{{$parent.getLabelValue( page.sectionMap.fieldDefinitionInfo.fieldDefinitionMap.editRuleExp.label)}}</label>
                </div>
                <div class="error" v-if="!$v.fieldDefinition.editRuleExp.required">Expresion de edicion requerida</div>
              </div>
              <div v-if="page.sectionMap.fieldDefinitionInfo.fieldDefinitionMap.fieldType && page.sectionMap.fieldDefinitionInfo.fieldDefinitionMap.fieldType.visible" class="group">
                <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label form-group">
                  <select v-model="fieldDefinition.fieldType">
                    <option v-for="(value, key, index) in fieldTypes" v-bind:value="key">
                      {{ value }}
                    </option>
                  </select>
                  <label class="labelText form__label" for="fieldDefinition-fieldType">{{$parent.getLabelValue( page.sectionMap.fieldDefinitionInfo.fieldDefinitionMap.fieldType.label)}}</label>
                </div>
              </div>
              <div class="groupFull">
                <label class="typo__label">Label</label>
                <multiselect v-model="fieldDefinition.label" placeholder="Choose a label" label="title" track-by="title" :options="labelList" :option-height="104" :custom-label="customLabel" :show-labels="false">
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
  name: 'FieldDefinitionPage',
  data() {
    return {
      requestPage: 'fieldDefinitionInfo',
      label: null
    }
  },
  validations: {
    fieldDefinition: {
      fieldDefinitionCode: {
        required,
      },
      visibleRuleExp: {
        required,
      },
      editRuleExp: {
        required,
      },
    }
  },
  components: {
    Datepicker,
    Multiselect
  },
  computed: {
    fieldTypes() {
      return this.$store.state.general.properties.items.GENERAL.FIELD_TYPE;
    },
    labelList() {
      return this.$store.state.config.labelList;
    },
    fieldDefinition() {
      return this.$store.state.config.data;
    },
    page() {
      return this.$store.state.config.metadata.page;
    }
  },
  created: function() {
    console.log("FieldDefinitionPage - created - begin");
    const dataContent = {
      "fieldDefinition": {
        "id": this.$route.params.fieldDefinitionId
      },
      "getLabels": "true"
    }
    const {
      requestPage
    } = this;
    const {
      dispatch
    } = this.$store;
    dispatch('config/getFieldDefinitionById', {
      requestPage: requestPage,
      processName: 'RetrieveFieldDefinitionInfo',
      dataContent: dataContent
    });
    console.log("FieldDefinitionPage - created - end");
  },
  methods: {
    customLabel ({ labelCode, labelValueEsEs }) {
      return `${labelCode} – ${labelValueEsEs}`
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
      console.log("FieldDefinitionPage - method - saveObjectState - begin");
      const dataContent = {
        "fieldDefinition": this.fieldDefinition
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
        dispatch('config/saveFieldDefinitionEntity', {
          requestPage: requestPage,
          processName: 'SaveFieldDefinition',
          dataContent: dataContent,
          //returnRoute: returnRoute,
        });
      }
      console.log("FieldDefinitionPage - method - saveObjectState - end");
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
