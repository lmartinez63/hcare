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
      state.dataMap.medicalAppointment.firstName = content.dataContent.dataMap.patient.firstName
      state.dataMap.medicalAppointment.lastName = content.dataContent.dataMap.patient.lastName
      state.dataMap.medicalAppointment.celPhoneNumber = content.dataContent.dataMap.patient.celPhoneNumber
      state.dataMap.medicalAppointment.emailAddress = content.dataContent.dataMap.patient.emailAddress
      state.dataMap.medicalAppointment.historyCode = content.dataContent.dataMap.patient.historyCode
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
