<template>
  <div id="app">
    <!--img src="./assets/logo.png"-->
    <header id="header" class="header-container header-fixed bg-white" >
      <div class="logo"></div>
      <div class="top-nav">
        <ul class="nav-left list-unstyled">

        </ul>
        <ul class="nav-right list-unstyled">

        </ul>
      </div>
    </header>
    <div class="main-container">
      <aside v-if="requireAuthentication" id="nav-container" class="nav-container nav-fixed bg-dark">
        <div class="nav-wrapper">
          <div class="slimScrollDiv" style="position: relative; overflow: hidden; width: auto; height: 100%;">
            <ul id="nav" class="nav" style="overflow: hidden; width: auto; height: 100%;">
              <li class="nav-title">Navegacion</li>
              <li><a class="md-button md-ink-ripple" href="#"><i class="material-icons"></i>Dashboard</a></li>
              <li><a class="md-button md-ink-ripple" href="#"><i class="material-icons"></i>Pacientes</a></li>
              <li><a class="md-button md-ink-ripple" href="#"><i class="material-icons"></i>Impresion</a></li>
            </ul>
          </div>
        </div>
      </aside>
      <router-view/>
    </div>
  </div>
</template>

<script>
export default {
  name: 'App',
  data () {
    return {
      requireAuthentication: true,
        backendUrl: 'http://127.0.0.1:8080/',
    }
  }
}
</script>

<style>
#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
</style>
