<template>
  <v-app>
    <loading
      :active.sync="isLoading"
      :can-cancel="true"
      :on-cancel="onCancel"
      :is-full-page="fullPage"
    />
    <core-filter v-if="user" />

    <core-toolbar v-if="user" />

    <core-drawer v-if="user" />

    <core-view />
  </v-app>
</template>

<style lang="scss">
@import '@/styles/index.scss';
@import '@/styles/hcare.scss';
@import '~vue-loading-overlay/dist/vue-loading.css';
/* Remove in 1.2 */
.v-datatable thead th.column.sortable i {
  vertical-align: unset;
}
</style>
<script>
import Loading from 'vue-loading-overlay'
// Import stylesheet
// import '~vue-loading-overlay/dist/vue-loading.css'
// import 'vue-flash-message/dist/vue-flash-message.min.css'
// import 'vuetify/dist/vuetify.min.css'

export default {
  components: {
    Loading
  },
  data () {
    return {
      fullPage: true
    }
  },
  computed: {
    alertVar () {
      return this.$store.state.alert
    },
    alertVarType () {
      return this.$store.state.alert.type
    },
    user () {
      return this.$store.state.authentication.user
    },
    properties () {
      return this.$store.state.general.properties
    },
    isLoading () {
      return this.$store.state.general.loading
    }
    /* show() {
      return this.$store.state.authentication.loading;
    }, */
  },
  watch: {
    $route (to, from) {
      // clear alert on location change
      this.$store.dispatch('alert/clear')
    },
    alertVarType (val) {
      console.log('Alert changed ' + val)
      // TODO setup labels
      var self = this
      if (val === 'danger') {
        this.$swal({
          title: 'Un error occurrio!',
          text: 'Le gustaria reportar el error!',
          icon: 'warning',
          dangerMode: true,
          buttons: ['Retornar a la pagina anterior!', 'Enviar Reporte'],
          closeOnEsc: false,
          closeOnClickOutside: false
        }).then((sendAlert) => {
          if (sendAlert) {
          // TODO Send Alert to an email or something
            this.$swal({
              text: 'Reporte enviado! Estaremos arreglando el error lo mas pronto posible, perdón los inconvenientes',
              icon: 'success',
              closeOnEsc: false,
              closeOnClickOutside: false
            }).then(value => {
              self.$router.go(-1)
            })
          } else {
            this.$swal({
              text: 'Retornando a la pagina anterior!',
              closeOnEsc: false,
              closeOnClickOutside: false
            }).then(value => {
              self.$router.go(-1)
            })
          }
        })
      }
    },
    isLoading (val) {
      console.log('Loading changed ' + val)
    }
  },
  created () {
    console.log('App - Create - begin')

    if (this.user) {
      this.$store.dispatch('general/loadProperties')
      this.$store.dispatch('general/getLabels')
      this.$store.dispatch('general/getMedicalAreas')
      this.$store.dispatch('general/getDoctors')
      this.$store.dispatch('general/getRoles')
    }
    console.log('App - Create - end')
  },
  methods: {
    computedDateFormattedMomentjs (date) {
      // TODO this property should come from a userProfile locale
      this.$moment.locale('es')
      return date ? this.$moment(date).format('LLLL') : ''
    },
    getLabelValue: function (label) {
      var lValue = ''
      if (label) {
        var language = this.$store.state.authentication.user.userProfile.language
        switch (language) {
          case 'ES_ES':
            lValue = label.labelValueEsEs
            break
          default:
            break
        }
      }
      return lValue
    },
    logout: function () {
      this.submitted = true
      const { dispatch } = this.$store
      dispatch('authentication/logout')
    },
    downloadAttachment: function (attachment) {
      const dataContent = {
        'attachment': attachment
      }
      const {
        dispatch
      } = this.$store
      dispatch('general/downloadAttachment', {
        vm: this,
        dataContent: dataContent
        // returnRoute: returnRoute,
      })
    },
    onCancel () {
      console.log('User cancelled the loader.')
    }
  }
}
</script>
