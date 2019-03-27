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
              <!--
              <div v-on:click="viewMedicalAppointmets(medicalHistory.historyCode)" class="link">
                <div class="icon"><i class="fas fa-save"></i></div>
                <div class="text">Citas</div>
              </div>
              <div v-on:click="uploadAttachment('medicalHistory', medicalHistory.historyCode, medicalHistory.historyCode)" class="link">
                <div class="icon"><i class="fas fa-save"></i></div>
                <div class="text">Adjuntar Archivo</div>
              </div>
              -->
              <div v-for="button in page.pageButtons"  v-if="button.visible" v-on:click="executeAction(button)" class="link">
                <div class="icon"><i class="button.icon"></i></div>
                <div class="text">{{$parent.getLabelValue(button.label)}}</div>
              </div>
            </div>
          </div>
        </div>
        <div v-if="page && page.sectionMap" class="formBox">
          <div class="headerFacNew">
            <div v-if="page.sectionMap.medicalHistoryInfo && page.sectionMap.medicalHistoryInfo.visible && page.sectionMap.medicalHistoryInfo.fieldDefinitionMap" class="subTitle">
              {{$parent.getLabelValue(page.sectionMap.medicalHistoryInfo.label)}}
            </div>
            <!--<div v-if="page.sectionMap.patientInfo && page.sectionMap.patientInfo.visible && page.sectionMap.patientInfo.fieldDefinitionMap" class="twoCol">-->
            <div v-if="page.sectionMap.medicalHistoryInfo && page.sectionMap.medicalHistoryInfo.visible && page.sectionMap.medicalHistoryInfo.fieldDefinitionMap" class="twoCol">
              <!--<div class="group">
                <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
                  <input class="mdl-textfield__input" type="text" name="medicalHistory-fileNumber" id="medicalHistory-fileNumber" v-model="medicalHistory.fileNumber" />
                  <label class="labelText" for="medicalHistory-fileNumber">Numero de Archivo</label>
                </div>
              </div>
              -->
              <div v-if="page.sectionMap.medicalHistoryInfo.fieldDefinitionMap.historyActivity && page.sectionMap.medicalHistoryInfo.fieldDefinitionMap.historyActivity.visible" class="group">
                <input type="checkbox" name="medicalHistory-historyActivity" id="medicalHistory-historyActivity" v-model="medicalHistory.historyActivity" class="switch-input" />
                <label for="medicalHistory-historyActivity" class="switch-label">{{$parent.getLabelValue( page.sectionMap.medicalHistoryInfo.fieldDefinitionMap.historyActivity.label)}}
                  <span class="toggle--on">Activa</span>
                  <span class="toggle--off">Pasiva</span>
                </label>
              </div>
              <div v-if="page.sectionMap.medicalHistoryInfo.fieldDefinitionMap.fullName && page.sectionMap.medicalHistoryInfo.fieldDefinitionMap.fullName.visible" class="group">
                <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
                  <label class="mdl-textfield__input labelNoEdit" id="medicalAppointment-patient-fullName">{{medicalHistory.patient.fullName}}</label>
                  <label class="labelText" for="medicalHistory-patient-fullName">{{$parent.getLabelValue( page.sectionMap.medicalHistoryInfo.fieldDefinitionMap.fullName.label)}}</label>
                </div>
              </div>
            </div>
            <div v-if="page.sectionMap.medicalHistoryBackgroundInfo && page.sectionMap.medicalHistoryBackgroundInfo.visible && page.sectionMap.medicalHistoryBackgroundInfo.fieldDefinitionMap" class="subTitle">
              {{$parent.getLabelValue(page.sectionMap.medicalHistoryBackgroundInfo.label)}}
            </div>
            <div v-if="page.sectionMap.medicalHistoryBackgroundInfo && page.sectionMap.medicalHistoryBackgroundInfo.visible && page.sectionMap.medicalHistoryBackgroundInfo.fieldDefinitionMap" class="twoCol">
              <div v-if="page.sectionMap.medicalHistoryBackgroundInfo.fieldDefinitionMap.previousSurgeries && page.sectionMap.medicalHistoryBackgroundInfo.fieldDefinitionMap.previousSurgeries.visible" class="groupFull">
                <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
                  <input class="mdl-textfield__input" type="text" name="medicalHistory-previousSurgeries" id="medicalHistory-previousSurgeries" v-model="medicalHistory.previousSurgeries" @input="$parent.forceUppercase($event, medicalHistory, 'previousSurgeries')" />
                  <label class="labelText" for="medicalHistory-previousSurgeries">{{$parent.getLabelValue( page.sectionMap.medicalHistoryBackgroundInfo.fieldDefinitionMap.previousSurgeries.label)}}</label>
                </div>
              </div>
              <div v-if="page.sectionMap.medicalHistoryBackgroundInfo.fieldDefinitionMap.previousDiseases && page.sectionMap.medicalHistoryBackgroundInfo.fieldDefinitionMap.previousDiseases.visible" class="groupFull">
                <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
                  <input class="mdl-textfield__input" type="text" name="medicalHistory-previousDiseases" id="medicalHistory-previousDiseases" v-model="medicalHistory.previousDiseases" @input="$parent.forceUppercase($event, medicalHistory, 'previousDiseases')" />
                  <label class="labelText" for="medicalHistory-previousDiseases">{{$parent.getLabelValue( page.sectionMap.medicalHistoryBackgroundInfo.fieldDefinitionMap.previousDiseases.label)}}</label>
                </div>
              </div>
              <div v-if="page.sectionMap.medicalHistoryBackgroundInfo.fieldDefinitionMap.hospitalizations && page.sectionMap.medicalHistoryBackgroundInfo.fieldDefinitionMap.hospitalizations.visible" class="groupFull">
                <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
                  <input class="mdl-textfield__input" type="text" name="medicalHistory-hospitalizations" id="medicalHistory-hospitalizations" v-model="medicalHistory.hospitalizations" @input="$parent.forceUppercase($event, medicalHistory, 'hospitalizations')" />
                  <label class="labelText" for="medicalHistory-hospitalizations">{{$parent.getLabelValue( page.sectionMap.medicalHistoryBackgroundInfo.fieldDefinitionMap.hospitalizations.label)}}</label>
                </div>
              </div>

              <div v-if="page.sectionMap.medicalHistoryBackgroundInfo.fieldDefinitionMap.medicationAllergies && page.sectionMap.medicalHistoryBackgroundInfo.fieldDefinitionMap.medicationAllergies.visible" class="groupFull">
                <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
                  <input class="mdl-textfield__input" type="text" name="medicalHistory-medicationAllergies" id="medicalHistory-medicationAllergies" v-model="medicalHistory.medicationAllergies" @input="$parent.forceUppercase($event, medicalHistory, 'medicationAllergies')" />
                  <label class="labelText" for="medicalHistory-medicationAllergies">{{$parent.getLabelValue( page.sectionMap.medicalHistoryBackgroundInfo.fieldDefinitionMap.medicationAllergies.label)}}</label>
                </div>
              </div>
              <div v-if="page.sectionMap.medicalHistoryBackgroundInfo.fieldDefinitionMap.smoke && page.sectionMap.medicalHistoryBackgroundInfo.fieldDefinitionMap.smoke.visible" class="group">
                <input type="checkbox" name="medicalHistory-smoke" id="medicalHistory-smoke" v-model="medicalHistory.smoke" class="switch-input" />
                <label for="medicalHistory-smoke" class="switch-label">{{$parent.getLabelValue( page.sectionMap.medicalHistoryBackgroundInfo.fieldDefinitionMap.smoke.label)}}
                  <span class="toggle--on">Si</span>
                  <span class="toggle--off">No</span>
                </label>
              </div>
              <div v-if="page.sectionMap.medicalHistoryBackgroundInfo.fieldDefinitionMap.packagesCigarettesPday && page.sectionMap.medicalHistoryBackgroundInfo.fieldDefinitionMap.packagesCigarettesPday.visible" class="group">
                <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
                  <input class="mdl-textfield__input" type="text" name="medicalHistory-packagesCigarettesPday" id="medicalHistory-packagesCigarettesPday" v-model="medicalHistory.packagesCigarettesPday" />
                  <label class="labelText" for="medicalHistory-packagesCigarettesPday">{{$parent.getLabelValue( page.sectionMap.medicalHistoryBackgroundInfo.fieldDefinitionMap.packagesCigarettesPday.label)}}</label>
                </div>
              </div>
              <div v-if="page.sectionMap.medicalHistoryBackgroundInfo.fieldDefinitionMap.drink && page.sectionMap.medicalHistoryBackgroundInfo.fieldDefinitionMap.drink.visible" class="group">
                <input type="checkbox" name="medicalHistory-drink" id="medicalHistory-drink" v-model="medicalHistory.drink" class="switch-input" />
                <label for="medicalHistory-drink" class="switch-label">{{$parent.getLabelValue( page.sectionMap.medicalHistoryBackgroundInfo.fieldDefinitionMap.drink.label)}}
                  <span class="toggle--on">Si</span>
                  <span class="toggle--off">No</span>
                </label>
              </div>
              <div v-if="page.sectionMap.medicalHistoryBackgroundInfo.fieldDefinitionMap.howOftenDrink && page.sectionMap.medicalHistoryBackgroundInfo.fieldDefinitionMap.howOftenDrink.visible" class="group">
                <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
                  <input class="mdl-textfield__input" type="text" name="medicalHistory-howOftenDrink" id="medicalHistory-howOftenDrink" v-model="medicalHistory.howOftenDrink" @input="forceUppercase($event, medicalHistory, 'howOftenDrink')"/>
                  <label class="labelText" for="medicalHistory-howOftenDrink">{{$parent.getLabelValue( page.sectionMap.medicalHistoryBackgroundInfo.fieldDefinitionMap.howOftenDrink.label)}}</label>
                </div>
              </div>
              <div v-if="page.sectionMap.medicalHistoryBackgroundInfo.fieldDefinitionMap.hepBResult && page.sectionMap.medicalHistoryBackgroundInfo.fieldDefinitionMap.hepBResult.visible" class="group">
                <input type="checkbox" name="medicalHistory-hepBResult" id="medicalHistory-hepBResult" v-model="medicalHistory.hepBResult" class="switch-input" />
                <label for="medicalHistory-hepBResult" class="switch-label">{{$parent.getLabelValue( page.sectionMap.medicalHistoryBackgroundInfo.fieldDefinitionMap.hepBResult.label)}}
                  <span class="toggle--on">POSITIVO</span>
                  <span class="toggle--off">NEGATIVO</span>
                </label>
              </div>
              <div v-if="page.sectionMap.medicalHistoryBackgroundInfo.fieldDefinitionMap.hepBYear && page.sectionMap.medicalHistoryBackgroundInfo.fieldDefinitionMap.hepBYear.visible" class="group">
                <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
                  <input class="mdl-textfield__input" type="text" name="medicalHistory-hepBYear" id="medicalHistory-hepBYear" v-model="medicalHistory.hepBYear" />
                  <label class="labelText" for="medicalHistory-hepBYear">{{$parent.getLabelValue( page.sectionMap.medicalHistoryBackgroundInfo.fieldDefinitionMap.hepBYear.label)}}</label>
                </div>
              </div>
              <div v-if="page.sectionMap.medicalHistoryBackgroundInfo.fieldDefinitionMap.hepCResult && page.sectionMap.medicalHistoryBackgroundInfo.fieldDefinitionMap.hepCResult.visible" class="group">
                <input type="checkbox" name="medicalHistory-hepCResult" id="medicalHistory-hepCResult" v-model="medicalHistory.hepCResult" class="switch-input" />
                <label for="medicalHistory-hepCResult" class="switch-label">{{$parent.getLabelValue( page.sectionMap.medicalHistoryBackgroundInfo.fieldDefinitionMap.hepCResult.label)}}
                  <span class="toggle--on">POSITIVO</span>
                  <span class="toggle--off">NEGATIVO</span>
                </label>
              </div>
              <div v-if="page.sectionMap.medicalHistoryBackgroundInfo.fieldDefinitionMap.hepCYear && page.sectionMap.medicalHistoryBackgroundInfo.fieldDefinitionMap.hepCYear.visible" class="group">
                <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
                  <input class="mdl-textfield__input" type="text" name="medicalHistory-hepCYear" id="medicalHistory-hepCYear" v-model="medicalHistory.hepCYear" />
                  <label class="labelText" for="medicalHistory-hepCYear">{{$parent.getLabelValue( page.sectionMap.medicalHistoryBackgroundInfo.fieldDefinitionMap.hepCYear.label)}}</label>
                </div>
              </div>
              <div v-if="page.sectionMap.medicalHistoryBackgroundInfo.fieldDefinitionMap.vihResult && page.sectionMap.medicalHistoryBackgroundInfo.fieldDefinitionMap.vihResult.visible" class="group">
                <input type="checkbox" name="medicalHistory-vihResult" id="medicalHistory-vihResult" v-model="medicalHistory.vihResult" class="switch-input" />
                <label for="medicalHistory-vihResult" class="switch-label">{{$parent.getLabelValue( page.sectionMap.medicalHistoryBackgroundInfo.fieldDefinitionMap.vihResult.label)}}
                  <span class="toggle--on">POSITIVO</span>
                  <span class="toggle--off">NEGATIVO</span>
                </label>
              </div>
              <div v-if="page.sectionMap.medicalHistoryBackgroundInfo.fieldDefinitionMap.vihYear && page.sectionMap.medicalHistoryBackgroundInfo.fieldDefinitionMap.vihYear.visible" class="group">
                <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
                  <input class="mdl-textfield__input" type="text" name="medicalHistory-vihYear" id="medicalHistory-vihYear" v-model="medicalHistory.vihYear" />
                  <label class="labelText" for="medicalHistory-vihYear">{{$parent.getLabelValue( page.sectionMap.medicalHistoryBackgroundInfo.fieldDefinitionMap.vihYear.label)}}</label>
                </div>
              </div>
              <div v-if="page.sectionMap.medicalHistoryBackgroundInfo.fieldDefinitionMap.preferentialDiet && page.sectionMap.medicalHistoryBackgroundInfo.fieldDefinitionMap.preferentialDiet.visible"class="groupFull">
                <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
                  <input class="mdl-textfield__input" type="text" name="medicalHistory-preferentialDiet" id="medicalHistory-preferentialDiet" v-model="medicalHistory.preferentialDiet"  @input="forceUppercase($event, medicalHistory, 'preferentialDiet')"/>
                  <label class="labelText" for="medicalHistory-preferentialDiet">{{$parent.getLabelValue( page.sectionMap.medicalHistoryBackgroundInfo.fieldDefinitionMap.preferentialDiet.label)}}</label>
                </div>
              </div>
            </div>

            <div v-if="page && page.sectionMap && page.sectionMap.attachmentInfo && page.sectionMap.attachmentInfo.visible && medicalHistory.attachmentList.length > 0" class="subTitle">
              {{$parent.getLabelValue(page.sectionMap.attachmentInfo.label)}}
            </div>
            <div v-if="page && page.sectionMap && page.sectionMap.attachmentInfo && page.sectionMap.attachmentInfo.visible && medicalHistory.attachmentList.length > 0"  class="twoCol">
              <ul v-if="page.sectionMap.attachmentInfo.fieldDefinitionMap.attachmentList && page.sectionMap.attachmentInfo.fieldDefinitionMap.attachmentList.visible"  class="boxLinks" id="attachment-list">
                <li v-for="attachment in medicalHistory.attachmentList">
                  <div v-on:click="$parent.downloadAttachment(attachment)">
                    <span>{{ attachment.fileName }}</span>
                    <!--<i class="fas fa-file-download"></i>-->
                    <i class="fas fa-save"></i>
                  </div>
                </li>
              </ul>
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

let installed = false

export default {
  name: 'MedicalHistoryPage',
  data() {
    return {
      requestPage: 'medicalHistoryInfo',
      defaultMedicalHistory: {
        patient: {},
        attachmentList: [],
      },

    }
  },
  validations: {
    medicalHistory: {
    },
  },
  computed: {
    medicalHistory() {
      return this.$store.state.medicalHistory.data;
    },
    page() {
      return this.$store.state.medicalHistory.metadata.page;
    }
  },
  created: function() {
    console.log("MedicalHistoryPage - created - begin");
    const dataContent = {
      "medicalHistory": {
        "historyCode": this.$route.params.historyCode
      }
    }
    const {
      requestPage
    } = this;
    const {
      dispatch
    } = this.$store;
    let selfVue = this
    if (this.$route.params.historyCode !== 'null') {
      dispatch('medicalHistory/getHistoryCode', {
        requestPage: requestPage,
        processName: 'RetrieveMedicalHistoryInfo',
        dataContent: dataContent
      });
    } else {
      dispatch('alert/error', 'Historial Medico no disponible');
    }
    console.log("MedicalHistoryPage - created - end");
  },
  methods: {
    goBackBrowse: function() {
      this.$router.push({
        name: 'BrowseComponent'
      })
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
      console.log("MedicalHistoryPage - method - saveObjectState - begin");
      const dataContent = {
        "medicalHistory": this.medicalHistory
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
        dispatch('medicalHistory/saveEntity', {
          vm: this,
          requestPage: requestPage,
          processName: 'SaveMedicalHistory',
          dataContent: dataContent,
        });
      }
      console.log("MedicalHistoryPage - method - saveObjectState - end");
    },
    downloadAttachment: function(attachment) {
      axios.get(this.$parent.backendUrl + 'downloadAttachment/' + attachment.id, {
          responseType: 'blob',
        }).then(response => {
          //Download File directly
          const url = window.URL.createObjectURL(new Blob([response.data], {
            type: attachment.contentType
          }))
          const link = document.createElement('a')
          link.href = url
          link.setAttribute('download', attachment.fileName)
          document.body.appendChild(link);
          link.click();

          //Preview on page
          /*
          var file = new Blob([response.data], {type: 'image/png'});
          var fileURL = URL.createObjectURL(file);
          window.open(fileURL,'_blank');
          */
        })
        .catch(error => {
          console.log(error)
        })
    },
    viewMedicalAppointmets: function() {
      this.$router.push({
        name: 'BrowseComponent',
        params: {
          browseType: 'medicalAppointmentsByPatient',
          entityId: this.medicalHistory.patient.documentNumber,
        }
      })
    },
    frontEndDateFormat: function(date) {
      return moment(date, 'YYYY-MM-DDTHH:mm:ss.fff Z').format('DD/MM/YYYY')
    },
    backEndDateFormat: function(date) {
      return moment(date, 'DD/MM/YYYY').format('YYYY-MM-DDTHH:mm:ss.sssZ')
    }
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
