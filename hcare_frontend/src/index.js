import Vue from 'vue';

import { store } from './_store';
import { router } from './_helpers';
import App from './app/App';

// setup fake backend
//import { configureFakeBackend } from './_helpers';
//configureFakeBackend();
import Vuelidate from 'vuelidate'

Vue.config.productionTip = false
Vue.use(Vuelidate)

new Vue({
    el: '#app',
    router,
    store,
    render: h => h(App)
});
