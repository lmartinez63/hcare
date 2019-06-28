import { dataResponseService } from '../../_services'
import router from '../../../router'

export default {
  getData ({ dispatch, commit }, { requestPage, processName, dataContent }) {
    commit('general/setLoading', true, { root: true })
    commit('pendingRequest')
    dataResponseService.getContent(requestPage, processName, dataContent)
      .then(
        content => {
          commit('getDataSuccess', content)
          commit('general/setLoading', false, { root: true })
        },
        error => {
          commit('general/setLoading', false, { root: true })
          commit('failureDetected', error)
          dispatch('alert/error', error.message, { root: true })
        }
      )
  },
  getPatientInfoByDocumentNumberOnMedAppointment ({ dispatch, commit }, { requestPage, processName, dataContent }) {
    commit('general/setLoading', true, { root: true })
    dataResponseService.getContent(requestPage, processName, dataContent)
      .then(
        content => {
          commit('getPatientInfoByDocumentNumberOnMedAppointmentSuccess', content)
          commit('general/setLoading', false, { root: true })
        },
        error => {
          commit('general/setLoading', false, { root: true })
          commit('failureDetected', error)
          dispatch('alert/error', error.message, { root: true })
        }
      )
  },
  uploadFile ({ dispatch, commit }, { vm, formData }) {
    return new Promise((resolve, reject) => {
      commit('general/setLoading', true, { root: true })
      commit('pendingRequest')
      dataResponseService.uploadAttachment(formData)
        .then(
          content => {
            commit('uploadFileSuccess', content)
            commit('general/setLoading', false, { root: true })
            resolve({ status: 200 })
          },
          error => {
            commit('general/setLoading', false, { root: true })
            commit('failureDetected', error)
            dispatch('alert/error', error.message, { root: true })
          }
        )
    })
  },
  saveEntity ({ dispatch, commit }, { vm, requestPage, processName, dataContent, returnRoute, additionalActions }) {
    return new Promise((resolve, reject) => {
      console.log('data-actions-saveEntity')
      commit('general/setLoading', true, { root: true })
      commit('pendingRequest')
      dataResponseService.getContent(requestPage, processName, dataContent, returnRoute, additionalActions)
        .then(
          content => {
            commit('general/setLoading', false, { root: true })
            commit('saveEntitySuccess', content)
            dispatch('alert/success', 'Los datos fueron guardados satisfactoriamente', { root: true })
            if (additionalActions && Array.isArray(additionalActions)) {
              for (var i = 0; i < additionalActions.length; i++) {
                console.log('executingAdditionalActions: ' + additionalActions[i])
                eval(additionalActions[i])
              }
            }
            if (returnRoute && returnRoute !== '') {
              if (returnRoute === 'back') {
                router.go(-1)
              } else if (returnRoute === 'reloadPage') {
                router.go()
              } else {
                setTimeout(() => {
                  router.push(returnRoute)
                }, 5000)
              }
            }

            resolve({ status: 200 })
          },
          error => {
            commit('general/setLoading', false, { root: true })
            commit('failureDetected', error)
            dispatch('alert/error', error.message, { root: true })
          }
        )
    })
  }
}
