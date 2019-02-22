import Vue from 'vue';
import Router from 'vue-router';

import HomePage from '../views/home/HomePage'
import LoginPage from '../views/login/LoginPage'
import MedicalAppointmentPage from '../views/medicalAppointment/MedicalAppointmentPage'
import BrowsePage from '../views/browse/BrowsePage'
import UserProfilePage from '../views/userProfile/UserProfilePage'
import MedicalAreaPage from '../views/medicalArea/MedicalAreaPage'

Vue.use(Router);

export const router = new Router({
  mode: 'history',
  routes: [
    { path: '/', component: HomePage },
    { path: '/login', component: LoginPage },
    { path: '/medicalAppointment/:medicalAppointmentId', name:'MedicalAppointmentPage', component: MedicalAppointmentPage },
    { path: '/medicalArea/:medicalAreaId', name:'MedicalAreaPage', component: MedicalAreaPage },
    { path: '/userProfile/:userProfileId', name:'UserProfilePage', component: UserProfilePage },
    { path: '/browse/:browseName/:entityId', name: 'BrowseComponent', component: BrowsePage},
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
