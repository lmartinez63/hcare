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
        error => commit('failureDetected', error)
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
            commit('failureDetected', error)
            dispatch('alert/error', error, { root: true })
          }
        )
    })
  },
  saveEntity ({ dispatch, commit }, { vm, requestPage, processName, dataContent, returnRoute }) {
    return new Promise((resolve, reject) => {
      commit('general/setLoading', true, { root: true })
      commit('pendingRequest')
      dataResponseService.getContent(requestPage, processName, dataContent, returnRoute)
        .then(
          content => {
            commit('saveEntitySuccess', content)
            commit('general/setLoading', false, { root: true })
            // dispatch('alert/success', { 'vm': vm, message: 'Los datos fueron guardados satisfactoriamente' }, { root: true })
            if (returnRoute) {
              setTimeout(() => {
                router.push(returnRoute)
              }, 5000)
            }
            resolve({ status: 200 })
          },
          error => {
            commit('failureDetected', error)
            dispatch('alert/error', error, { root: true })
          }
        )
    })
  }
}
