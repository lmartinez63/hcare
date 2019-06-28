import moment from 'moment'

export default {
  pendingRequest (state) {
    console.log('dataResponse - mutations - pendingRequest')
    state.loading = true
    state.dataMap = {}
    state.metadata = {}
  },
  failureDetected (state, error) {
    state.loading = false
    console.log('dataResponse - mutations - failureDetected - ' + error)
    state.error = error
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
