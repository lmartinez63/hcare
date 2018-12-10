import Vue from 'vue'
import App from './App.vue'
import router from './router'
import Vuelidate from 'vuelidate'

Vue.config.productionTip = false
Vue.use(Vuelidate)

Vue.mixin({
  methods: {
    forceUppercase(e, o, prop) {
      const start = e.target.selectionStart;
      e.target.value = e.target.value.toUpperCase();
      this.$set(o, prop, e.target.value);
      e.target.setSelectionRange(start, start);
    },
  }
})


new Vue({
  render: h => h(App),
    router,
}).$mount('#app')
