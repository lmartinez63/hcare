<template>
<div class="content-container">
  <section data-ui-view="" class="view-container animate-fade-up">
    <section class="page">
      <div class="titleForm">
        Historia Medica {{medicalHistory.historyCode}}
      </div>
      <div class="row ui-section contentMain">
        <div class="btnMoreActions" onClick="openThreePoint()">
          <div class="btnMore">
            <i class="fas fa-ellipsis-v"></i>
          </div>
          <div class="moreOptions" id="moreOptions">
            <div class="menuContent">
              <div v-on:click="saveObjectState()" class="link">
                <div class="icon"><i class="fas fa-save"></i></div>
                <div class="text">Guardar</div>
              </div>
              <!-- specialButtons -->
              <div v-on:click="viewMedicalAppointmets(medicalHistory.historyCode)" class="link">
                <div class="icon"><i class="fas fa-save"></i></div>
                <div class="text">Citas</div>
              </div>
              <div v-on:click="uploadAttachment('medicalHistory', medicalHistory.historyCode, medicalHistory.historyCode)" class="link">
                <div class="icon"><i class="fas fa-save"></i></div>
                <div class="text">Adjuntar Archivo</div>
              </div>
            </div>
          </div>
        </div>
        <div class="formBox">
          <div class="headerFacNew">
            <div class="subTitle">
              Datos de Historia Medica
            </div>
            <div class="twoCol">
              <!--<div class="group">
                <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
                  <input class="mdl-textfield__input" type="text" name="medicalHistory-fileNumber" id="medicalHistory-fileNumber" v-model="medicalHistory.fileNumber" />
                  <label class="labelText" for="medicalHistory-fileNumber">Numero de Archivo</label>
                </div>
              </div>
              -->
              <div class="group">
                <input type="checkbox" name="medicalHistory-historyActivity" id="medicalHistory-historyActivity" v-model="medicalHistory.historyActivity" class="switch-input" />
                <label for="medicalHistory-historyActivity" class="switch-label">Estado
                  <span class="toggle--on">Activa</span>
                  <span class="toggle--off">Pasiva</span>
                </label>
              </div>
              <div class="group">
                <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
                  <label class="mdl-textfield__input labelNoEdit" id="medicalAppointment-patient-fullName">{{medicalHistory.patient.fullName}}</label>
                  <label class="labelText" for="medicalHistory-patient-fullName">Nombre de Paciente</label>
                </div>
              </div>
            </div>
            <div class="subTitle">
              Antecedentes
            </div>
            <div class="twoCol">
              <div class="groupFull">
                <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
                  <input class="mdl-textfield__input" type="text" name="medicalHistory-previousSurgeries" id="medicalHistory-previousSurgeries" v-model="medicalHistory.previousSurgeries" @input="forceUppercase($event, medicalHistory, 'previousSurgeries')" />
                  <label class="labelText" for="medicalHistory-previousSurgeries">Cirugias Previas</label>
                </div>
              </div>
              <div class="groupFull">
                <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
                  <input class="mdl-textfield__input" type="text" name="medicalHistory-previousDiseases" id="medicalHistory-previousDiseases" v-model="medicalHistory.previousDiseases" @input="forceUppercase($event, medicalHistory, 'previousDiseases')" />
                  <label class="labelText" for="medicalHistory-previousDiseases">Enfermedades Previas</label>
                </div>
              </div>
              <div class="groupFull">
                <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
                  <input class="mdl-textfield__input" type="text" name="medicalHistory-hospitalizations" id="medicalHistory-hospitalizations" v-model="medicalHistory.hospitalizations" @input="forceUppercase($event, medicalHistory, 'hospitalizations')" />
                  <label class="labelText" for="medicalHistory-hospitalizations">Hospitalizaciones</label>
                </div>
              </div>
              <div class="groupFull">
                <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
                  <input class="mdl-textfield__input" type="text" name="medicalHistory-medicationAllergies" id="medicalHistory-medicationAllergies" v-model="medicalHistory.medicationAllergies" @input="forceUppercase($event, medicalHistory, 'medicationAllergies')" />
                  <label class="labelText" for="medicalHistory-medicationAllergies">Alergias a Medicamentos</label>
                </div>
              </div>
              <div class="group">
                <input type="checkbox" name="medicalHistory-smoke" id="medicalHistory-smoke" v-model="medicalHistory.smoke" class="switch-input" />
                <label for="medicalHistory-smoke" class="switch-label">Fuma
                  <span class="toggle--on">Si</span>
                  <span class="toggle--off">No</span>
                </label>
              </div>
              <div class="group">
                <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
                  <input class="mdl-textfield__input" type="text" name="medicalHistory-packagesCigarettesPday" id="medicalHistory-packagesCigarettesPday" v-model="medicalHistory.packagesCigarettesPday" />
                  <label class="labelText" for="medicalHistory-packagesCigarettesPday"># de Paquetes por dia</label>
                </div>
              </div>
              <div class="group">
                <input type="checkbox" name="medicalHistory-drink" id="medicalHistory-drink" v-model="medicalHistory.drink" class="switch-input" />
                <label for="medicalHistory-drink" class="switch-label">Bebe
                  <span class="toggle--on">Si</span>
                  <span class="toggle--off">No</span>
                </label>
              </div>
              <div class="group">
                <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
                  <input class="mdl-textfield__input" type="text" name="medicalHistory-howOftenDrink" id="medicalHistory-howOftenDrink" v-model="medicalHistory.howOftenDrink" @input="forceUppercase($event, medicalHistory, 'howOftenDrink')"/>
                  <label class="labelText" for="medicalHistory-howOftenDrink">Con que frequencia?</label>
                </div>
              </div>
              <div class="group">
                <input type="checkbox" name="medicalHistory-hepBResult" id="medicalHistory-hepBResult" v-model="medicalHistory.hepBResult" class="switch-input" />
                <label for="medicalHistory-hepBResult" class="switch-label">Hepatitis B resulado
                  <span class="toggle--on">POSITIVO</span>
                  <span class="toggle--off">NEGATIVO</span>
                </label>
              </div>
              <div class="group">
                <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
                  <input class="mdl-textfield__input" type="text" name="medicalHistory-hepBYear" id="medicalHistory-hepBYear" v-model="medicalHistory.hepBYear" />
                  <label class="labelText" for="medicalHistory-hepBYear">Año de Diagnostico</label>
                </div>
              </div>
              <div class="group">
                <input type="checkbox" name="medicalHistory-hepCResult" id="medicalHistory-hepCResult" v-model="medicalHistory.hepCResult" class="switch-input" />
                <label for="medicalHistory-hepCResult" class="switch-label">Hepatitis C resulado
                  <span class="toggle--on">POSITIVO</span>
                  <span class="toggle--off">NEGATIVO</span>
                </label>
              </div>
              <div class="group">
                <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
                  <input class="mdl-textfield__input" type="text" name="medicalHistory-hepCYear" id="medicalHistory-hepCYear" v-model="medicalHistory.hepCYear" />
                  <label class="labelText" for="medicalHistory-hepCYear">Año de Diagnostico</label>
                </div>
              </div>
              <div class="group">
                <input type="checkbox" name="medicalHistory-vihResult" id="medicalHistory-vihResult" v-model="medicalHistory.vihResult" class="switch-input" />
                <label for="medicalHistory-vihResult" class="switch-label">VIH resulado
                  <span class="toggle--on">POSITIVO</span>
                  <span class="toggle--off">NEGATIVO</span>
                </label>
              </div>
              <div class="group">
                <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
                  <input class="mdl-textfield__input" type="text" name="medicalHistory-vihYear" id="medicalHistory-vihYear" v-model="medicalHistory.vihYear" />
                  <label class="labelText" for="medicalHistory-vihYear">Año de Diagnostico</label>
                </div>
              </div>
              <div class="groupFull">
                <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
                  <input class="mdl-textfield__input" type="text" name="medicalHistory-preferentialDiet" id="medicalHistory-preferentialDiet" v-model="medicalHistory.preferentialDiet"  @input="forceUppercase($event, medicalHistory, 'preferentialDiet')"/>
                  <label class="labelText" for="medicalHistory-preferentialDiet">Dieta preferencial( por ejemplo: vegetariana )</label>
                </div>
              </div>
            </div>
            <div v-if="medicalHistory.attachmentList.length > 0" class="subTitle">
              Documentos Adjuntos
            </div>
            <div v-if="medicalHistory.attachmentList.length > 0" class="twoCol">
              <ul class="boxLinks" id="attachment-list">
                <li v-for="attachment in medicalHistory.attachmentList">
                  <div v-on:click="downloadAttachment(attachment)">
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
  name: 'MedicalHistoryComponent',
  data() {
    return {
      medicalHistory: {
        patient: {},
        attachmentList: [],
      },

    }
  },
  created: function() {
    console.log("MedicalHistoryComponent");
    let selfVue = this
    if (this.$route.params.historyCode !== 'null') {
      var urlMedicalHistoryInfo = this.$parent.backendUrl + 'medicalHistories/' + this.$route.params.historyCode
      axios.get(urlMedicalHistoryInfo)
        .then(response => {
          selfVue.medicalHistory = response.data
        })
        .catch(error => {
          console.log(error)
        })
    }
  },
  methods: {
    goBackBrowse: function() {
      this.$router.push({
        name: 'BrowseComponent'
      })
    },
    saveObjectState: function() {
      const url = this.$parent.backendUrl + 'medicalHistories'
      //Add patientId to medicalHistory
      let selfVue = this
      axios.post(url, this.medicalHistory)
        .then(response => {
          selfVue.medicalHistory = response.data
          selfVue.$parent.sucessMessage()
        })
        .catch(error => {
          console.log(error)
        })
      // this.$router.push({ name: '/'})
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
    uploadAttachment: function(entity, entityId, entityCode) {
      this.$router.push({
        name: 'AttachmentComponent',
        params: {
          entity: entity,
          entityId: entityId,
          entityCode: entityCode,
        }
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
