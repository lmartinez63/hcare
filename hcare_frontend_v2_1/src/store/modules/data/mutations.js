import moment from 'moment'

export default {
  pendingRequest (state) {
    console.log('patientResponse - mutations - pendingRequest')
    state.loading = true
    state.dataMap = {}
    state.metadata = {}
  },
  failureDetected (state, error) {
    state.loading = false
    console.log('patientResponse - mutations - failureDetected - ' + error)
    state.error = error
  },
  getDataSuccess (state, content) {
    state.loading = false
    console.log('processResponse - mutations  - getDataSuccess')
    state.dataMap = content.dataContent.dataMap
    state.metadata = content.metadataContent
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
