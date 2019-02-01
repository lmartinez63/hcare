import { userService } from '../_services';

export const users = {
    namespaced: true,
    state: {
        all: {},
        userProfile: {}
    },
    actions: {
        getAll({ commit }) {
            commit('getAllRequest');

            userService.getAll()
                .then(
                    users => commit('getAllSuccess', users),
                    error => commit('getFailure', error)
                );
        },
        getCurrentUserDetails({ commit }) {
            commit('getCurrentUserDetailsRequest');

            userService.getCurrentUserDetails()
                .then(
                    currentUser => commit('getCurrentUserDetailSuccess', currentUser),
                    error => commit('getFailure', error)
                );
        }
    },
    mutations: {
        getAllRequest(state) {
            state.all = { loading: true };
        },
        getAllSuccess(state, users) {
            state.all = { items: users };
        },
        getCurrentUserDetailSuccess(state, user) {
            state.all = { currentUser: user };
        },
        getFailure(state, error) {
            state.all = { error };
        }
    }
}
