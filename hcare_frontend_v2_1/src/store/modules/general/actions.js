import {
  generalService,
  dataResponseService
} from '../../_services'
import router from '../../../router'

export default {
  loadProperties ({ dispatch, commit }) {
    commit('loadPropertiesRequest')
    generalService.loadProperties()
      .then(
        properties => commit('loadPropertiesSuccess', properties),
        error => commit('loadPropertiesFailure', error)
      )
  },
  uploadAttachment ({ dispatch, commit }, { vm, dataContent }) {
    return new Promise((resolve, reject) => {
      commit('setLoading', true)
      dataResponseService.uploadAttachment(dataContent)
        .then(
          content => {
            commit('uploadAttachmentSuccess', content)
            commit('setLoading', false)
            // TODO sucess message should come from a label translated from backend
            dispatch('alert/success', { vm: vm, message: 'Documento ha sido guardado exitosamente' }, { root: true })
            router.go(-1)
            resolve({ status: 200 })
          },
          error => {
            commit('failureDetected', error)
            dispatch('alert/error', error, { root: true })
          }
        )
    })
  },
  downloadAttachment ({ dispatch, commit }, { vm, dataContent }) {
    return new Promise((resolve, reject) => {
      commit('setLoading', true)
      dataResponseService.downloadAttachment(dataContent)
        .then(
          content => {
            commit('downloadAttachmentSuccess', content)
            // Download File directly

            const url = window.URL.createObjectURL(new Blob([content], {
              type: dataContent.attachment.contentType
            }))
            const link = document.createElement('a')
            link.href = url
            link.setAttribute('target', '_blank')
            link.setAttribute('download', dataContent.attachment.fileName)
            document.body.appendChild(link)
            link.click()

            /*
            Reload at same window
            var file = new Blob([content], {type: dataContent.attachment.contentType});
            var objectUrl = URL.createObjectURL(file);
            window.location.assign(objectUrl);
            */
            // TODO sucess message should come from a label translated from backend
            dispatch('alert/success', { vm: vm, message: 'Documento ha sido descargado exitosamente ' }, { root: true })
            resolve({ status: 200 })
          },
          error => {
            commit('failureDetected', error)
            dispatch('alert/error', error, { root: true })
          }
        )
    })
  },
  getLabels ({ dispatch, commit }) {
    commit('getLabelsRequest')
    generalService.getLabels()
      .then(
        labels => commit('getLabelsSuccess', labels),
        error => commit('failureDetected', error)
      )
  },
  getDoctors ({
    dispatch,
    commit
  }) {
    commit('getDoctorsRequest')
    generalService.getDoctors()
      .then(
        doctors => commit('getDoctorsSuccess', doctors),
        error => commit('getDoctorsFailure', error)
      )
  },
  getRoles ({
    dispatch,
    commit
  }) {
    commit('getRolesRequest')
    generalService.getRoles()
      .then(
        doctors => commit('getRolesSuccess', doctors),
        error => commit('getRolesFailure', error)
      )
  },
  getMedicalAreas ({
    dispatch,
    commit
  }) {
    commit('getMedicalAreasRequest')
    generalService.getMedicalAreas()
      .then(
        medicalAreas => commit('getMedicalAreasSuccess', medicalAreas),
        error => commit('getMedicalAreasFailure', error)
      )
  }
}
