import { router } from '../../../router'

export default {
  success (state, { vm, message, returnRoute }) {
    vm.flash(message, 'success', {
      timeout: 3000,
      beforeDestroy () {
        if (returnRoute) {
          router.push(returnRoute)
        }
      }
    })
    state.type = 'success'
    state.style = 'alert-success'
    state.message = message
  },
  warning (state, message) {
    state.type = 'warning'
    state.style = 'alert-warning'
    state.message = message
  },
  error (state, message) {
    state.type = 'danger'
    state.style = 'alert-danger'
    state.message = message
  },
  clear (state) {
    state.type = null
    state.style = null
    state.message = null
  }
}
