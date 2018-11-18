import Vue from 'vue'
import Router from 'vue-router'
import BrowseComponent from '@/components/BrowseComponent'
import PatientComponent from '@/components/PatientComponent'
import MedicalHistoryComponent from '@/components/MedicalHistoryComponent'
import AttachmentComponent from '@/components/AttachmentComponent'
Vue.use(Router)

const router = new Router({
  routes: [
    {
      path: '/uploadAttachment/:entity/:entityId/:entityCode',
      name: 'AttachmentComponent',
      component: AttachmentComponent
    },
    {
      path: '/browse/:browseType',
      name: 'BrowseComponent',
      component: BrowseComponent
    },
    {
      path: '/patient/:id',
      name: 'PatientComponent',
      component: PatientComponent
    },
    {
      path: '/MedicalHistory/:patientId',
      name: 'MedicalHistoryComponent',
      component: MedicalHistoryComponent
    }
  ]
})
/*if (this.requreAuthentication) {
  router.beforeEach((to, from, next) => {
    if (sessionStorage.getItem('authToken') !== null || to.path === '/auth') {
      next()
    } else {
      next('/auth')
    }
  })
}*/
export default router
