import Vue from 'vue';
import Vuex from 'vuex';
import VueFlashMessage from 'vue-flash-message';
import VueSwal from 'vue-swal';

import { alert } from './alert.module';
import { authentication } from './authentication.module';
import { users } from './users.module';
import { general } from './general.module';
import { medicalAppointment } from './medicalAppointment.module';
import { userProfile } from './userProfile.module';
import { medicalArea } from './medicalArea.module';
import { browse } from './browse.module';
import { patient } from './patient.module';
import { medicalHistory } from './medicalHistory.module';
import { config } from './config.module';
//D3Chart
import Chart from 'v-chart-plugin';
import Vuetify from 'vuetify'
import VueApexCharts from 'vue-apexcharts'

Vue.component('apexchart', VueApexCharts)

Vue.use(Vuex);
Vue.use(VueSwal);
Vue.use(VueFlashMessage);
Vue.use(Chart);
Vue.use(Vuetify)
Vue.use(require('vue-moment'));


export const store = new Vuex.Store({
    modules: {
        alert,
        authentication,
        users,
        general,
        medicalAppointment,
        medicalHistory,
        medicalArea,
        browse,
        userProfile,
        patient,
        config
    }
});
