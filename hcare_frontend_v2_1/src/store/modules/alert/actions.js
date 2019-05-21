export default {
  success ({ commit }, { vm, message, returnRoute }) {
    commit('success', { vm, message, returnRoute })
  },
  warning ({ commit }, message) {
    commit('warning', message)
  },
  error ({ commit }, message) {
    commit('error', message)
  },
  clear ({ commit }, message) {
    commit('clear', message)
  }
}
