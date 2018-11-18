<template>
  <div id="app">
    <!--img src="./assets/logo.png"-->
    <header id="header" class="header-container header-fixed bg-white" >
        <div class="logo">
            <img src="/static/img/logo.png">
        </div>
        <div class="top-nav">
            <div class="username">
                Usuario: Admin
            </div>
        </div>
    </header>
    <div class="main-container">
        <aside id="nav-container" class="nav-container nav-fixed bg-dark">
            <div class="nav-wrapper">
                <div class="slimScrollDiv" style="position: relative; overflow: hidden; width: auto; height: 100%;">
                    <div class="contenedor-menu">
                        <ul class="menu">
                            <li><a href="#"><i class="icono izquierda fa fa-home"></i>Dashboard</a></li>
                            <li><a><i class="icono izquierda fa fa-star"></i>Administration de Pacientes<i class="icono derecha fa fa-chevron-down"></i></a>
                                <ul>
                                    <li>
                                      <router-link :to="{ name: 'BrowseComponent', params: { browseType: 'allPatients' }}">Listado de Pacientes</router-link>
                                    </li>
                                    <li><a href="#">Registrar Paciente</a></li>
                                    <li><a href="#">Item 3</a></li>
                                    <li><a href="#">Item 4</a></li>
                                </ul>
                            </li>
                            <li><a href="#"><i class="icono izquierda fa fa-share-alt"></i>Administracion de Historias Medicas<i class="icono derecha fa fa-chevron-down"></i></a>
                                <ul>
                                    <li><a href="#">Listado de Historias Medicas</a></li>
                                </ul>
                            </li>
                            <li><a href="#"><i class="icono izquierda fa fa-share-alt"></i>Administracion de Citas<i class="icono derecha fa fa-chevron-down"></i></a>
                                <ul>
                                    <li><a href="#">Listado de Citas</a></li>
                                    <li><a href="#">Listado de Citas de Hoy</a></li>
                                    <li><a href="#">Listado de Citas Vencidas</a></li>
                                </ul>
                            </li>
                            <li><a href="#"><i class="icono izquierda fa fa-envelope"></i>Contactanos</a></li>
                        </ul>
                    </div>
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
      navigation: {
        
      }
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
