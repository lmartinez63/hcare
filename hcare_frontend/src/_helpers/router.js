import Vue from 'vue';
import Router from 'vue-router';

import HomePage from '../views/home/HomePage'
import LoginPage from '../views/login/LoginPage'
import MedicalAppointmentPage from '../views/medicalAppointment/MedicalAppointmentPage'
import PatientPage from '../views/patient/PatientPage'
import BrowsePage from '../views/browse/BrowsePage'
import UserProfilePage from '../views/userProfile/UserProfilePage'
import MedicalAreaPage from '../views/medicalArea/MedicalAreaPage'
import MedicalHistoryPage from '../views/medicalHistory/MedicalHistoryPage'
import RolePageSectionFieldPage from '../views/configuration/RolePageSectionFieldPage'
import PageDefinitionPage from '../views/configuration/PageDefinitionPage'
import SectionPage from '../views/configuration/SectionPage'
import FieldDefinitionPage from '../views/configuration/FieldDefinitionPage'
import LabelPage from '../views/configuration/LabelPage'
import AttachmentPage from '../views/general/AttachmentPage'
import PageNotFoundPage from '../views/general/PageNotFoundPage'
import DashboardPage from '../views/general/DashboardPage'

Vue.use(Router);

export const router = new Router({
  mode: 'history',
  routes: [
    { path: '/', component: HomePage },
    { path: '/login', component: LoginPage },
    { path: '/dashboard', name:'DashboardPage', component: DashboardPage },
    { path: '/medicalAppointment/:medicalAppointmentId', name:'MedicalAppointmentPage', component: MedicalAppointmentPage },
    { path: '/patient/:patientId', name:'PatientPage', component: PatientPage },
    { path: '/medicalArea/:medicalAreaId', name:'MedicalAreaPage', component: MedicalAreaPage },
    { path: '/medicalHistory/:historyCode', name:'MedicalHistoryPage', component: MedicalHistoryPage },
    { path: '/userProfile/:userProfileId', name:'UserProfilePage', component: UserProfilePage },
    { path: '/browse/:browseName', name: 'BrowsePage', component: BrowsePage},
    { path: '/rolePageSectionField/:roleId/:pageId/:sectionId/:fieldDefinitionId', name: 'RolePageSectionFieldPage', component: RolePageSectionFieldPage},
    { path: '/page/:pageId', name: 'PageDefinitionPage', component: PageDefinitionPage},
    { path: '/section/:sectionId', name: 'SectionPage', component: SectionPage},
    { path: '/fieldDefinition/:fieldDefinitionId', name: 'FieldDefinitionPage', component: FieldDefinitionPage},
    { path: '/label/:labelId', name: 'LabelPage', component: LabelPage},
    { path: '/uploadAttachment/:entity/:entityId/:entityCode', name: 'AttachmentPage', component: AttachmentPage },
    // otherwise redirect to home
    { path: '*', redirect: '/' }
  ]
});

router.beforeEach((to, from, next) => {
  // redirect to login page if not logged in and trying to access a restricted page
  console.log('router - call');
  const publicPages = ['/login'];
  const authRequired = !publicPages.includes(to.path);
  const loggedIn = localStorage.getItem('user');

  if (authRequired && !loggedIn) {
    return next('/login');
  }

  next();
})
