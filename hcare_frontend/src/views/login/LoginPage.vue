<template>
<form @submit.prevent="handleSubmit">
  <div class="boxLoginPortlet">
    <div class="boxLogin">
      <div class="body-inner">
        <div class="card bg-white">
          <div class="titleLogin">
            HCARE
          </div>
          <div class="twoCol">
            <div class="group">
              <div class="form-group mdl-textfield mdl-js-textfield mdl-textfield--floating-label" :class="{ 'form-group--error': $v.username.$error }">
                <input class="form__input mdl-textfield__input" type="text" name="username" id="username" v-model="$v.username.$model" />
                <label class="form__label labelText" for="username">Usuario</label>
              </div>
              <div class="error" v-if="!$v.username.required">Usuario requerido</div>
            </div>
            <div class="group">
              <div class="form-group mdl-textfield mdl-js-textfield mdl-textfield--floating-label" :class="{ 'form-group--error': $v.password.$error }">
                <input class="form__input mdl-textfield__input" type="password" name="password" id="password" v-model="$v.password.$model" />
                <label class="form__label labelText" for="password">Password</label>
              </div>
              <div class="error" v-if="!$v.password.required">Password requerido</div>
            </div>
          </div>
          <div class="form-group">
            <button class="btn btn-primary" :disabled="loggingIn">Login</button>
            <img v-show="loggingIn" src="data:image/gif;base64,R0lGODlhEAAQAPIAAP///wAAAMLCwkJCQgAAAGJiYoKCgpKSkiH/C05FVFNDQVBFMi4wAwEAAAAh/hpDcmVhdGVkIHdpdGggYWpheGxvYWQuaW5mbwAh+QQJCgAAACwAAAAAEAAQAAADMwi63P4wyklrE2MIOggZnAdOmGYJRbExwroUmcG2LmDEwnHQLVsYOd2mBzkYDAdKa+dIAAAh+QQJCgAAACwAAAAAEAAQAAADNAi63P5OjCEgG4QMu7DmikRxQlFUYDEZIGBMRVsaqHwctXXf7WEYB4Ag1xjihkMZsiUkKhIAIfkECQoAAAAsAAAAABAAEAAAAzYIujIjK8pByJDMlFYvBoVjHA70GU7xSUJhmKtwHPAKzLO9HMaoKwJZ7Rf8AYPDDzKpZBqfvwQAIfkECQoAAAAsAAAAABAAEAAAAzMIumIlK8oyhpHsnFZfhYumCYUhDAQxRIdhHBGqRoKw0R8DYlJd8z0fMDgsGo/IpHI5TAAAIfkECQoAAAAsAAAAABAAEAAAAzIIunInK0rnZBTwGPNMgQwmdsNgXGJUlIWEuR5oWUIpz8pAEAMe6TwfwyYsGo/IpFKSAAAh+QQJCgAAACwAAAAAEAAQAAADMwi6IMKQORfjdOe82p4wGccc4CEuQradylesojEMBgsUc2G7sDX3lQGBMLAJibufbSlKAAAh+QQJCgAAACwAAAAAEAAQAAADMgi63P7wCRHZnFVdmgHu2nFwlWCI3WGc3TSWhUFGxTAUkGCbtgENBMJAEJsxgMLWzpEAACH5BAkKAAAALAAAAAAQABAAAAMyCLrc/jDKSatlQtScKdceCAjDII7HcQ4EMTCpyrCuUBjCYRgHVtqlAiB1YhiCnlsRkAAAOwAAAAAAAAAAAA==" />
          </div>
        </div>
        <div class="additional-info">
        </div>
      </div>
    </div>
  </div>
</form>
</template>

<script>
//Validations
import {
  required,
  minLength,
  between,
  email,
  maxLength
} from 'vuelidate/lib/validators'

export default {
  data() {
    return {
      username: '',
      password: '',
      submitted: false
    }
  },
  validations: {
    username: {
      required,
    },
    password: {
      required,
    },
  },
  computed: {
    loggingIn() {
      return this.$store.state.authentication.status.loggingIn;
    }
  },
  created() {
    console.log('LoginPage - Created - begin');
    // reset login status
    this.$store.dispatch('authentication/logout');
    console.log('LoginPage - Created - end');
  },
  methods: {
    handleSubmit(e) {
      this.submitted = true;
      const { dispatch } = this.$store;
      const { username, password } = this;
      this.$v.$touch()
      if (this.$v.$invalid) {
        //selfVue.$parent.errorMessage("Por favor complete los campos requeridos")
        dispatch('alert/error', "Por favor complete los campos requeridos", { root: true });
      } else {
        dispatch('authentication/login', {
          username,
          password
        });
      }
    }
  }
};
</script>
