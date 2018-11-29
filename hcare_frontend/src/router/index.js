import Vue from 'vue'
import Router from 'vue-router'
import BrowseComponent from '@/components/BrowseComponent'
import PatientComponent from '@/components/PatientComponent'
import MedicalHistoryComponent from '@/components/MedicalHistoryComponent'
import MedicalAppointmentComponent from '@/components/MedicalAppointmentComponent'
import MedicalAreaComponent from '@/components/MedicalAreaComponent'
import EmployeeComponent from '@/components/EmployeeComponent'
import AttachmentComponent from '@/components/AttachmentComponent'
import DashboardComponent from '@/components/DashboardComponent'

Vue.use(Router)

const router = new Router({
  routes: [
    {
      path: '/uploadAttachment/:entity/:entityId/:entityCode',
      name: 'AttachmentComponent',
      component: AttachmentComponent
    },
    {
      path: '/browse/:browseType/:entityId',
      name: 'BrowseComponent',
      component: BrowseComponent
    },
    {
      path: '/patient/:id',
      name: 'PatientComponent',
      component: PatientComponent
    },
    {
      path: '/medicalHistory/:historyCode',
      name: 'MedicalHistoryComponent',
      component: MedicalHistoryComponent
    },
    {
      path: '/medicalAppointment/:medicalAppointmentId/:historyCode',
      name: 'MedicalAppointmentComponent',
      component: MedicalAppointmentComponent
    },
    {
      path: '/medicalArea/:medicalAreaId',
      name: 'MedicalAreaComponent',
      component: MedicalAreaComponent
    },
    {
      path: '/employee/:employeeId',
      name: 'EmployeeComponent',
      component: EmployeeComponent
    },
    {
      path: '/',
      name: 'DashboardComponent',
      component: DashboardComponent
    }
  ]
})
//Temporal to redirect to Secretary view
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
