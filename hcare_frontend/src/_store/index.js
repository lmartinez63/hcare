import Vue from 'vue';
import Vuex from 'vuex';

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

Vue.use(Vuex);

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
