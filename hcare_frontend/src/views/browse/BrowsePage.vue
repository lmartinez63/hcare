<template>
<div class="content-container">
  <section data-ui-view="" class="view-container animate-fade-up">
    <section class="page">
      <div class="titleForm">
        {{title}}
      </div>
      <div class="row ui-section">
        <div class="formBox">
          <div v-if="newButtonVisible" class="btnOptions">
            <div class="boxOpt">
              <button v-on:click="viewObjectDetails(null)" class="md-button md-primary" type="button">{{newButtonTitle}}</button>
            </div>
          </div>
          <table id="browseDataTable" class="display responsive nowrap" style="width:100%">
            <thead>
            </thead>
            <tbody>
            </tbody>
          </table>
        </div>
      </div>
    </section>
  </section>
</div>
</template>
<script>
import axios from 'axios'
import moment from 'moment'

export default {
  name: 'BrowsePage',
  data() {
    return {
      requestPage: 'browseInfo',
      title: '',
      entityIdName: '',
      newButtonVisible: false,
      newButtonTitle: '',
      displayObjects: [],
      detailFunction: '',
      browseurl: '',
      //browseName: '',
      entityId: 'null',
      columnDefinitionArray: [],
      orderDefinitionArray: [],
      //columnDefinitionValues: [],
      columnDefinitionNames: [],
      parametersArray: [],
    }
  },
  computed: {
    dataColumns() {
      return this.$store.state.browse.metadata.dataColumns;
    },
  },
  created: function() {
    console.log('created-' + 'BrowseComponent')
  },
  mounted: function() {
    console.log('mounted-' + 'BrowseComponent')
    var columnIndex = 0;
    //Assing this vue compnent to self to don't lose reference
    let selfVue = this
    this.entityId = this.$route.params.entityId
    const dataContent = {
        "browseName": this.$route.params.browseName,
        "browseParameters": this.$route.query
    }
    const {
      requestPage
    } = this;
    const {
      dispatch
    } = this.$store;

    dispatch('browse/getBrowseData', {
        requestPage: requestPage,
        processName: '',
        dataContent: dataContent,
        vueInstance: this
    });

    $('#browseDataTable tbody').on('click', '#open', function(self) {
      //Remove event when the its on first element to dont overwrite more options button
      var dataRoute = this.getAttribute('data-funtion');
      var rowData = $('#browseDataTable').DataTable().row(this).data();
      if(rowData == undefined){
        var $tr = $(this).closest('tr');
        rowData = $('#browseDataTable').DataTable().row($tr).data();
      }
      selfVue.openObjectDetails(rowData,dataRoute);
    })
    $('#browseDataTable tbody').on('click', '#save', function(self) {
      //Remove event when the its on first element to dont overwrite more options button
      ;
      var rowData = $('#browseDataTable').DataTable().row(this).data();
      if(rowData == undefined){
        var $tr = $(this).closest('tr');
        rowData = $('#browseDataTable').DataTable().row($tr).data();
      }
      //console.log('You clicked on ' + data[0] + '\'s row')
      var dataFuntion = this.getAttribute('data-funtion');
      selfVue.saveObjectDetails(rowData,dataFuntion);
    })
  },
  methods: {
    openObjectDetails: function(rowData,dataRoute) {
      var routeObject = {};
      var jsonString = dataRoute;
      dataRoute.match(/\${{(.*?)}}/g).forEach(function(dataRouteVariable) {
        jsonString = jsonString.replace(dataRouteVariable,rowData[dataRouteVariable.match( /\$\{\{([^)]+)\}\}/)[1]]);
      });
      routeObject = JSON.parse(jsonString);
      //routeObject = JSON.parse('{"name":"' + this.detailComponent + '","params":{"' + this.entityIdName + '":"' + id + '"}}')
      this.$router.push(routeObject);
    },
    saveObjectDetails: function(rowData,dataFuntion) {
      var routeObject = {};
      var jsonFuntionString = dataFuntion;
      dataFuntion.match(/\${{(.*?)}}/g).forEach(function(dataFuntionVariable) {
        jsonFuntionString = jsonFuntionString.replace(dataFuntionVariable,rowData[dataFuntionVariable.match( /\$\{\{([^)]+)\}\}/)[1]]);
      });
      var functionJSONObject = JSON.parse(jsonFuntionString);
      const {
        dispatch,commit
      } = this.$store;
      dispatch(functionJSONObject.action, {
        requestPage: functionJSONObject.params.requestPage,
        processName: functionJSONObject.params.processName,
        dataContent: functionJSONObject.params.dataContent
      }).then(response => {
          var dataContent = {
            "browseName": this.$route.params.browseName
          };
          dispatch('browse/reloadBrowseData', {
            requestPage: '',
            processName: '',
            dataContent: dataContent,
            vueInstance: this
          });
        }, error => {
            console.error("Got nothing from server. Prompt user to check internet connection and try again")
        })
      /*
      .then(() => {

        var dataContent = {
          "browseName": this.$route.params.browseName
        };
        dispatch('browse/getBrowseData', {
            requestPage: '',
            processName: '',
            dataContent: dataContent,
            vueInstance: this
        });
      });
      */
    },
  }
}
</script>
