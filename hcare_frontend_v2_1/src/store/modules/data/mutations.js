import moment from 'moment'

export default {
  pendingRequest (state) {
    console.log('dataResponse - mutations - pendingRequest')
    state.loading = true
    state.dataMap = {}
    state.metadata = {}
  },
  pendingDialogRequest (state) {
    console.log('dataResponse - mutations - pendingDialogRequest')
    state.loading = true
    state.dialgoDataMap = {}
    state.dialogMetadata = {}
  },
  failureDetected (state, error) {
    state.loading = false
    console.log('dataResponse - mutations - failureDetected - ' + error)
    state.error = error
  },
  getDialogDataSuccess (state, content) {
    state.loading = false
    console.log('dataResponse - mutations  - getDialogDataSuccess')
    state.dialogDataMap = content.dataContent.dataMap
    // Formats for dates
    if (state.dataMap.patient) {
      state.dataMap.patient.birthday = (new Date(state.dataMap.patient.birthday)).toISOString().substr(0, 10)
    }
    state.dialogMetadata = content.metadataContent
  },
  getDataSuccess (state, content) {
    state.loading = false
    console.log('dataResponse - mutations  - getDataSuccess')
    state.dataMap = content.dataContent.dataMap
    // Formats for dates
    if (state.dataMap.patient) {
      state.dataMap.patient.birthday = (new Date(state.dataMap.patient.birthday)).toISOString().substr(0, 10)
    }
    state.metadata = content.metadataContent
  },
  getPatientInfoByDocumentNumberOnMedAppointmentSuccess (state, content) {
    state.loading = false
    console.log('dataResponse - mutations - getPatientInfoByDocumentNumberOnMedAppointmentSuccess')
    if (content && content != null && content.dataContent.dataMap.patient) {
      state.dataMap.medicalAppointment.patient = content.dataContent.dataMap.patient
    } else {
      state.dataAlert = { message: 'Numero de Documento no encontrado, el paciente sera registrado', type: 'info', display: true }
    }
  },
  saveEntitySuccess (state, content) {
    state.loading = false
    console.log('processResponse - mutations  - getDataSuccess')
    state.dataMap = content.dataContent.dataMap
  },
  uploadFileSuccess (state, content) {
    state.loading = false
    console.log('processResponse - mutations  - uploadFileSuccess')
  }
}
