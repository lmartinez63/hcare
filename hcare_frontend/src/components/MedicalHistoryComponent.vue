<template>
<div class="content-container">
  <section data-ui-view="" class="view-container animate-fade-up">
    <section class="page">
      <div class="titleForm">
        Historia Medica {{medicalHistory.medicalHistoryCode}}
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
              <div v-on:click="viewMedicalAppointmets(medicalHistory.id)" class="link">
                <div class="icon"><i class="fas fa-save"></i></div>
                <div class="text">Citas</div>
              </div>
              <div v-on:click="uploadAttachment('medicalHistory', medicalHistory.id, medicalHistory.medicalHistoryCode)" class="link">
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
              <div class="group">
                <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
                  <input class="mdl-textfield__input" type="text" name="medicalHistory-medicalHistoryCode" id="medicalHistory-medicalHistoryCode" v-model="medicalHistory.medicalHistoryCode" />
                  <label class="labelText" for="medicalHistory-medicalHistoryCode">Codigo del Historial medico</label>
                </div>
              </div>
              <div class="group">
                <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
                  <input class="mdl-textfield__input" type="text" name="patient-patientCode" id="medicalHistory-patient-patientCode" v-model="medicalHistory.patient.patientCode" />
                  <label class="labelText" for="medicalHistory-patient-patientCode">Codigo del Paciente</label>
                </div>
              </div>
              <div class="group">
                <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
                  <input class="mdl-textfield__input" type="text" name="patient-firstName" id="medicalHistory-patient-firstName" v-model="medicalHistory.patient.firstName" />
                  <label class="labelText" for="medicalHistory-patient-firstName">Nombre de Paciente</label>
                </div>
              </div>
              <div class="group">
                <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
                  <input class="mdl-textfield__input" type="text" name="medicalHistory-fileNumber" id="medicalHistory-fileNumber" v-model="medicalHistory.fileNumber" />
                  <label class="labelText" for="medicalHistory-fileNumber">Numero de Archivo</label>
                </div>
              </div>
            </div>
            <div class="subTitle">
              Antecedentes
            </div>
            <div class="twoCol">
              <div class="groupFull">
                <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
                  <input class="mdl-textfield__input" type="text" name="medicalHistory-previousSurgeries" id="medicalHistory-previousSurgeries" v-model="medicalHistory.previousSurgeries" />
                  <label class="labelText" for="medicalHistory-previousSurgeries">Cirugias Previas</label>
                </div>
              </div>
              <div class="groupFull">
                <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
                  <input class="mdl-textfield__input" type="text" name="medicalHistory-previousDiseases" id="medicalHistory-previousDiseases" v-model="medicalHistory.previousDiseases" />
                  <label class="labelText" for="medicalHistory-previousDiseases">Enfermedades Previas</label>
                </div>
              </div>
              <div class="groupFull">
                <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
                  <input class="mdl-textfield__input" type="text" name="medicalHistory-hospitalizations" id="medicalHistory-hospitalizations" v-model="medicalHistory.hospitalizations" />
                  <label class="labelText" for="medicalHistory-hospitalizations">Hospitalizaciones</label>
                </div>
              </div>
              <div class="groupFull">
                <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
                  <input class="mdl-textfield__input" type="text" name="medicalHistory-medicationAllergies" id="medicalHistory-medicationAllergies" v-model="medicalHistory.medicationAllergies" />
                  <label class="labelText" for="medicalHistory-medicationAllergies">Alergias a Medicamentos</label>
                </div>
              </div>
              <div class="group">
                <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
                  <input class="mdl-textfield__input" type="text" name="medicalHistory-smoke" id="medicalHistory-smoke" v-model="medicalHistory.smoke" />
                  <label class="labelText" for="medicalHistory-smoke">Fuma S/N</label>
                </div>
              </div>
              <div class="group">
                <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
                  <input class="mdl-textfield__input" type="text" name="medicalHistory-packagesCigarettesPday" id="medicalHistory-packagesCigarettesPday" v-model="medicalHistory.packagesCigarettesPday" />
                  <label class="labelText" for="medicalHistory-packagesCigarettesPday"># de Paquetes por dia</label>
                </div>
              </div>
              <div class="group">
                <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
                  <input class="mdl-textfield__input" type="text" name="medicalHistory-hepBResult" id="medicalHistory-hepBResult" v-model="medicalHistory.hepBResult" />
                  <label class="labelText" for="medicalHistory-hepBResult">Hepatitis B resulado</label>
                </div>
              </div>
              <div class="group">
                <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
                  <input class="mdl-textfield__input" type="text" name="medicalHistory-hepBYear" id="medicalHistory-hepBYear" v-model="medicalHistory.hepBYear" />
                  <label class="labelText" for="medicalHistory-hepBYear">Año</label>
                </div>
              </div>
              <div class="group">
                <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
                  <input class="mdl-textfield__input" type="text" name="medicalHistory-hepCResult" id="medicalHistory-hepCResult" v-model="medicalHistory.hepCResult" />
                  <label class="labelText" for="medicalHistory-hepBResult">Hepatitis C resulado</label>
                </div>
              </div>
              <div class="group">
                <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
                  <input class="mdl-textfield__input" type="text" name="medicalHistory-hepCYear" id="medicalHistory-hepCYear" v-model="medicalHistory.hepCYear" />
                  <label class="labelText" for="medicalHistory-hepCYear">Año</label>
                </div>
              </div>
              <div class="group">
                <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
                  <input class="mdl-textfield__input" type="text" name="medicalHistory-vihResult" id="medicalHistory-vihResult" v-model="medicalHistory.vihResult" />
                  <label class="labelText" for="medicalHistory-vihResult">VIH resulado</label>
                </div>
              </div>
              <div class="group">
                <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
                  <input class="mdl-textfield__input" type="text" name="medicalHistory-vihYear" id="medicalHistory-vihYear" v-model="medicalHistory.vihYear" />
                  <label class="labelText" for="medicalHistory-vihYear">Año</label>
                </div>
              </div>


            </div>
            <div class="subTitle">
              Documentos Adjuntos
            </div>
            <div class="twoCol">
              <ul id="attachment-list">
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
    if (this.$route.params.patientId !== 'null') {
      var urlMedicalHistoryInfo = this.$parent.backendUrl + 'medicalHistory/' + this.$route.params.patientId
      axios.get(urlMedicalHistoryInfo)
        .then(response => {
          this.medicalHistory = response.data
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
      axios.post(url, this.medicalHistory)
        .then(response => {
          this.medicalHistory = response.data
        })
        .catch(error => {
          console.log(error)
        })
      // this.$router.push({ name: '/'})
    },
    downloadAttachment: function(attachment) {
      axios.get(this.$parent.backendUrl + 'downloadAttachment/' + attachment.id, {responseType: 'blob',}).then(response => {
          //Download File directly
          const url = window.URL.createObjectURL(new Blob([response.data], {type: attachment.contentType}))
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
        name: 'MedicalHistoryComponent',
        params: {
          patientId
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
