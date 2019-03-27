export const alert = {
  namespaced: true,
  state: {
    type: null,
    message: '',
    style: null
  },
  actions: {
    success({ commit }, { vm, message }) {
      vm.flash( message, 'success'
    /*  {
        timeout: 10000,
      }*/
    );
      commit('success', message);
    },
    warning({
      commit
    }, message) {
      commit('warning', message);
    },
    error({
      commit
    }, message) {
      commit('error', message);
    },
    clear({
      commit
    }, message) {
      commit('success', message);
    }
  },
  mutations: {
    success(state, message) {
      state.type = 'success';
      state.style = 'alert-success';
      state.message = message;
    },
    warning(state, message) {
      state.type = 'warning';
      state.style = 'alert-warning';
      state.message = message;
    },
    error(state, message) {
      state.type = 'danger';
      state.style = 'alert-danger';
      state.message = message;
    },
    clear(state) {
      state.type = null;
      state.style = null;
      state.message = null;
    }
  }
}
