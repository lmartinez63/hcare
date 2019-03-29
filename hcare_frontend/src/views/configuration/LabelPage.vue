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
            <div v-if="page.sectionMap.labelInfo && page.sectionMap.labelInfo.visible && page.sectionMap.labelInfo.fieldDefinitionMap" class="subTitle">
              {{$parent.getLabelValue(page.sectionMap.labelInfo.label)}}
            </div>
            <div v-if="page.sectionMap.labelInfo && page.sectionMap.labelInfo.visible && page.sectionMap.labelInfo.fieldDefinitionMap" class="twoCol">
              <div v-if="page.sectionMap.labelInfo.fieldDefinitionMap.labelCode && page.sectionMap.labelInfo.fieldDefinitionMap.labelCode.visible" class="group">
                <div class="form-group mdl-textfield mdl-js-textfield mdl-textfield--floating-label" :class="{ 'form-group--error': $v.label.labelCode.$error }">
                  <input class="form__input mdl-textfield__input" type="text" name="label-labelCode" id="label-labelCode" v-model.trim="$v.label.labelCode.$model" />
                  <label class="form__label labelText" for="label-pageCode">{{$parent.getLabelValue( page.sectionMap.labelInfo.fieldDefinitionMap.labelCode.label)}}</label>
                </div>
                <div class="error" v-if="!$v.label.labelCode.required">Codigo de Etiqueta requerido</div>
              </div>
              <div v-if="page.sectionMap.labelInfo.fieldDefinitionMap.module && page.sectionMap.labelInfo.fieldDefinitionMap.module.visible" class="group">
                <div class="form-group mdl-textfield mdl-js-textfield mdl-textfield--floating-label" :class="{ 'form-group--error': $v.label.module.$error }">
                  <input class="form__input mdl-textfield__input" type="text" name="label-module" id="label-module" v-model.trim="$v.label.module.$model" />
                  <label class="form__label labelText" for="label-module">{{$parent.getLabelValue( page.sectionMap.labelInfo.fieldDefinitionMap.module.label)}}</label>
                </div>
                <div class="error" v-if="!$v.label.module.required">Modulo requerido</div>
              </div>
              <div v-if="page.sectionMap.labelInfo.fieldDefinitionMap.subModule && page.sectionMap.labelInfo.fieldDefinitionMap.subModule.visible" class="group">
                <div class="form-group mdl-textfield mdl-js-textfield mdl-textfield--floating-label" :class="{ 'form-group--error': $v.label.subModule.$error }">
                  <input class="form__input mdl-textfield__input" type="text" name="label-subModule" id="label-subModule" v-model.trim="$v.label.subModule.$model" />
                  <label class="form__label labelText" for="label-subModule">{{$parent.getLabelValue( page.sectionMap.labelInfo.fieldDefinitionMap.subModule.label)}}</label>
                </div>
                <div class="error" v-if="!$v.label.subModule.required">Sub Modulo requerido</div>
              </div>
              <div v-if="page.sectionMap.labelInfo.fieldDefinitionMap.labelValue && page.sectionMap.labelInfo.fieldDefinitionMap.labelValue.visible" class="group">
                <div class="form-group mdl-textfield mdl-js-textfield mdl-textfield--floating-label" :class="{ 'form-group--error': $v.label.labelValue.$error }">
                  <input class="form__input mdl-textfield__input" type="text" name="label-labelValue" id="label-labelValue" v-model.trim="$v.label.labelValue.$model" />
                  <label class="form__label labelText" for="label-labelValue">{{$parent.getLabelValue( page.sectionMap.labelInfo.fieldDefinitionMap.labelValue.label)}}</label>
                </div>
                <div class="error" v-if="!$v.label.labelValue.required">Traduccion por defecto requerido</div>
              </div>
              <div v-if="page.sectionMap.labelInfo.fieldDefinitionMap.labelValueEsEs && page.sectionMap.labelInfo.fieldDefinitionMap.labelValueEsEs.visible" class="group">
                <div class="form-group mdl-textfield mdl-js-textfield mdl-textfield--floating-label" :class="{ 'form-group--error': $v.label.labelValueEsEs.$error }">
                  <input class="form__input mdl-textfield__input" type="text" name="label-labelValueEsEs" id="label-labelValueEsEs" v-model.trim="$v.label.labelValueEsEs.$model" />
                  <label class="form__label labelText" for="label-labelValueEsEs">{{$parent.getLabelValue( page.sectionMap.labelInfo.fieldDefinitionMap.labelValueEsEs.label)}}</label>
                </div>
                <div class="error" v-if="!$v.label.labelValueEsEs.required">Traduccion En Español requerida</div>
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

import {
  required,
  minLength,
  between,
  email,
  maxLength
} from 'vuelidate/lib/validators'


let installed = false

export default {
  name: 'LabelPage',
  data() {
    return {
      requestPage: 'labelInfo',
    }
  },
  validations: {
    label:{
      labelCode: {
        required,
      },
      module: {
        required,
      },
      subModule: {
        required,
      },
      labelValue: {
        required,
      },
      labelValueEsEs: {
        required,
      },
    }
  },
  components: {
    Datepicker
  },
  computed: {
    label() {
      return this.$store.state.config.data;
    },
    page() {
      return this.$store.state.config.metadata.page;
    },
    /*languages() {
      return this.$store.state.general.properties.items.GENERAL.LANGUAGE;
    },*/
  },
  created: function() {
    console.log("LabelPage - created - begin");
    const dataContent = {
      "label": {
        "id": this.$route.params.labelId
      }
    }
    const {
      requestPage
    } = this;
    const {
      dispatch
    } = this.$store;
    dispatch('config/getLabelById', {
        requestPage: requestPage,
        processName: 'RetrieveLabelInfo',
        dataContent: dataContent
      });
    console.log("LabelPage - created - end");
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
    saveObjectState: function() {
      console.log("LabelPage - method - saveObjectState - begin");
      const dataContent = {
        "label": this.label
      }
      const {
        requestPage
      } = this;
      const {
        dispatch
      } = this.$store;
      var returnRoute = {
        name: 'BrowsePage',
        params: {
          browseName: 'allLabels',
          entityId: 'null'
        }
      };
      this.$v.$touch()
      if (this.$v.$invalid) {
        selfVue.$parent.errorMessage("Por favor complete los campos requeridos")
      } else {
        dispatch('config/saveLabelEntity', {
          vm: this,
          requestPage: requestPage,
          processName: 'SaveLabel',
          dataContent: dataContent,
          returnRoute: returnRoute,
        });
      }
      console.log("LabelPage - method - saveObjectState - end");
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
