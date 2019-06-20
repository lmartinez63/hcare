import { userService } from '../../_services'
import router from '../../../router'

export default {
  login ({ dispatch, commit }, { username, password }) {
    commit('loginRequest', { username })
    commit('general/setLoading', true, { root: true })
    userService.login(username, password)
      .then(
        user => {
          console.log('Authenticated')
          commit('general/setLoading', false, { root: true })
          commit('loginSuccess', user)
          dispatch('general/loadProperties', null, { root: true })
          dispatch('general/getMedicalAreas', null, { root: true })
          dispatch('general/getDoctors', null, { root: true })
          dispatch('general/getRoles', null, { root: true })
          dispatch('general/getLabels', null, { root: true })
          router.push('/dashboard')
        },
        error => {
          commit('general/setLoading', false, { root: true })
          commit('loginFailure', error)
          dispatch('alert/warning', error, { root: true })
        }
      )
  },
  logout ({ commit }) {
    userService.logout()
    commit('logout')
    router.push('/login')
  }
}
