import { userService } from '../_services';
import { router } from '../_helpers';
const user = JSON.parse(localStorage.getItem('user'));
const initialState = user
    ? { status: { loggedIn: true }, user }
    : { status: {}, user: null };

export const authentication = {
    namespaced: true,
    state: initialState,
    actions: {
        login({ dispatch, commit }, { username, password }) {
            commit('loginRequest', { username });
            commit('general/setLoading',true,{ root: true });
            userService.login(username, password)
                .then(
                    user => {
                        commit('general/setLoading',false,{ root: true });
                        commit('loginSuccess', user);
                        router.push('/dashboard');
                    },
                    error => {
                        commit('general/setLoading',false,{ root: true });
                        commit('loginFailure', error);
                        dispatch('alert/warning', error, { root: true });
                    }
                );
        },
        logout({ commit }) {
            userService.logout();
            commit('logout');
            router.push('/login');
        }
    },
    mutations: {
        loginRequest(state, user) {
            console.log('authentication - mutations - loginRequest');
            state.loading = true;
            state.status = { loggingIn: true };
            state.user = user;
        },
        loginSuccess(state, user) {
            console.log('authentication - mutations - loginSuccess');
            state.loading = false;
            state.status = { loggedIn: true };
            state.user = user;
        },
        loginFailure(state) {
            console.log('authentication - mutations - loginFailure');
            state.loading = false;
            state.status = {};
            state.user = null;
        },
        logout(state) {
            state.loading = false;
            state.status = {};
            state.user = null;
        }
    }
}
