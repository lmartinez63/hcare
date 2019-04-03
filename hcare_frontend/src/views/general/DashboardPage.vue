<template>
<v-app>
  <div class="content-container">
    <div class="rowElement">
      <section v-if="$parent.user.userProfile.roles.some( role => role['id'] === 4 )" class="contentGraph fullWidth">
        <!-- <v-chart v-bind:chartData="chartData"></v-chart>-->
        <div id="chart">
          <apexchart :type="chart1.type" :width="chart1.width" :height="chart1.height" :options="chart1.chartOptions" :series="chart1.series" />
        </div>
      </section>
    </div>
    <!-- If is doctor -->
    <div class="rowElement">
      <section v-if="$parent.user.userProfile.roles.some( role => role['id'] === 7 )" class="fullWidth">
        <v-toolbar v-if="dataBrowse" flat color="white">
          <v-toolbar-title>Mis Citas del dia</v-toolbar-title>
          <v-divider class="mx-2" inset vertical></v-divider>
          <v-spacer></v-spacer>
          <!--
      <v-dialog v-model="dialog" max-width="500px">
        <template v-slot:activator="{ on }">
          <v-btn color="primary" dark class="mb-2" v-on="on">New Item</v-btn>
        </template>
        <v-card>
          <v-card-title>
            <span class="headline">{{ formTitle }}</span>
          </v-card-title>s
          <v-card-text>
            <v-container grid-list-md>
              <v-layout wrap>
                <v-flex xs12 sm6 md4>
                  <v-text-field v-model="editedItem.name" label="Dessert name"></v-text-field>
                </v-flex>
                <v-flex xs12 sm6 md4>
                  <v-text-field v-model="editedItem.calories" label="Calories"></v-text-field>
                </v-flex>
                <v-flex xs12 sm6 md4>
                  <v-text-field v-model="editedItem.fat" label="Fat (g)"></v-text-field>
                </v-flex>
                <v-flex xs12 sm6 md4>
                  <v-text-field v-model="editedItem.carbs" label="Carbs (g)"></v-text-field>
                </v-flex>
                <v-flex xs12 sm6 md4>
                  <v-text-field v-model="editedItem.protein" label="Protein (g)"></v-text-field>
                </v-flex>
              </v-layout>
            </v-container>
          </v-card-text>

          <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn color="blue darken-1" flat @click="close">Cancel</v-btn>
            <v-btn color="blue darken-1" flat @click="save">Save</v-btn>
          </v-card-actions>
        </v-card>
      </v-dialog>
      -->
        </v-toolbar>
        <v-data-table :headers="todayMAByCurrentDoctorHeaders" :items="dataBrowse" class="elevation-1">
          <template v-if="dataBrowse" slot="items" slot-scope="props">
            <td class="text-xs-left">{{ props.item.fullName }}</td>
            <td class="text-xs-left">{{ props.item.historyCode }}</td>
            <td class="text-xs-left">{{ props.item.dateAppointment | moment("hh:mm a") }}</td>
            <td class="text-xs-left">{{ props.item.medicalAppointmentType }}</td>
            <td class="text-xs-left">{{ props.item.status }}</td>
            <!--
          <td class="justify-center layout px-0">
            <v-icon small class="mr-2" @click="editItem(props.item)">edit</v-icon>
            <v-icon small @click="deleteItem(props.item)">delete</v-icon>
          </td>
        -->
          </template>
        </v-data-table>
      </section>
    </div>
    <!-- If is secretary -->
    <div class="rowElement">
      <section v-if="$parent.user.userProfile.roles.some( role => role['id'] === 6 )" class="fullWidth">
        <v-toolbar v-if="dataBrowse" flat color="white">
          <v-toolbar-title>Citas del dia</v-toolbar-title>
          <v-divider class="mx-2" inset vertical></v-divider>
          <v-spacer></v-spacer>
          <!--
      <v-dialog v-model="dialog" max-width="500px">
        <template v-slot:activator="{ on }">
          <v-btn color="primary" dark class="mb-2" v-on="on">New Item</v-btn>
        </template>
        <v-card>
          <v-card-title>
            <span class="headline">{{ formTitle }}</span>
          </v-card-title>s
          <v-card-text>
            <v-container grid-list-md>
              <v-layout wrap>
                <v-flex xs12 sm6 md4>
                  <v-text-field v-model="editedItem.name" label="Dessert name"></v-text-field>
                </v-flex>
                <v-flex xs12 sm6 md4>
                  <v-text-field v-model="editedItem.calories" label="Calories"></v-text-field>
                </v-flex>
                <v-flex xs12 sm6 md4>
                  <v-text-field v-model="editedItem.fat" label="Fat (g)"></v-text-field>
                </v-flex>
                <v-flex xs12 sm6 md4>
                  <v-text-field v-model="editedItem.carbs" label="Carbs (g)"></v-text-field>
                </v-flex>
                <v-flex xs12 sm6 md4>
                  <v-text-field v-model="editedItem.protein" label="Protein (g)"></v-text-field>
                </v-flex>
              </v-layout>
            </v-container>
          </v-card-text>

          <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn color="blue darken-1" flat @click="close">Cancel</v-btn>
            <v-btn color="blue darken-1" flat @click="save">Save</v-btn>
          </v-card-actions>
        </v-card>
      </v-dialog>
      -->
        </v-toolbar>
        <v-data-table :headers="headers" :items="dataBrowse" class="elevation-1">
          <template v-if="dataBrowse" slot="items" slot-scope="props">
            <td class="text-xs-left">{{ props.item.fullName }}</td>
            <td class="text-xs-left">{{ props.item.upFullName }}</td>
            <td class="text-xs-left">{{ props.item.historyCode }}</td>
            <td class="text-xs-left">{{ props.item.dateAppointment | moment("hh:mm a") }}</td>
            <td class="text-xs-left">{{ props.item.medicalAppointmentType }}</td>
            <td class="text-xs-left">{{ props.item.status }}</td>
            <!--
          <td class="justify-center layout px-0">
            <v-icon small class="mr-2" @click="editItem(props.item)">edit</v-icon>
            <v-icon small @click="deleteItem(props.item)">delete</v-icon>
          </td>
        -->
          </template>
        </v-data-table>
      </section>
    </div>
    <div class="rowElement">
    <section v-if="$parent.user.userProfile.roles.some( role => role['id'] === 7 )" class="mediumWidth">
      <v-card class="mx-auto" color="#26c6da" dark width="100%">
        <v-card-title>
          <v-icon large left>mdi-twitter</v-icon>
          <span class="title font-weight-light">Nova Clinic</span>
        </v-card-title>
        <v-card-text class="headline font-weight-bold">
          "Estimados Colegas, les presentamos el nuevo sistema Web, cualquier comentario escribirnos a novaclinic@gmail.com"
        </v-card-text>
        <v-card-actions>
          <v-list-tile class="grow">
            <v-list-tile-avatar color="grey darken-3">
              <v-img class="elevation-6" src="https://avataaars.io/"></v-img>
            </v-list-tile-avatar>
            <v-list-tile-content>
              <v-list-tile-title>Jackeline Cruz</v-list-tile-title>
            </v-list-tile-content>
            <v-layout align-center justify-end>
              <v-icon class="mr-1">mdi-heart</v-icon>
              <span class="subheading mr-2">Marzo</span>
              <span class="mr-1">·</span>
              <v-icon class="mr-1">mdi-share-variant</v-icon>
              <span class="subheading">18</span>
            </v-layout>
          </v-list-tile>
        </v-card-actions>
      </v-card>
    </section>
    <section v-if="$parent.user.userProfile.roles.some( role => role['id'] === 7 )" class="contentGraph mediumWidth">
      <div id="chart">
        <apexchart :type="chart2.type" :width="chart2.width" :height="chart2.height" :options="chart2.chartOptions" :series="chart2.series" />
      </div>
    </section>
  </div>
  </div>
  </div>
</v-app>
</template>
<script>

</script>
<script>
//TODO Improve dashboard to portlets come database
export default {
  name: 'DashboardPage',
  data() {
    return {
      chart1: {
        height: '300px',
        width: '100%',
        chartOptions: {
          chart: {
            id: 'char1id'
          },
          xaxis: {
            categories: ['Marzo, 27', 'Marzo, 28', 'Marzo, 29', 'Marzo, 30', 'Marzo, 31', 'Abril, 1 ', 'Abril, 2']
          },
          title: {
            text: 'Cantidad citas de la ultima semana',
            align: 'center',
            margin: 10,
            offsetX: 0,
            offsetY: 0,
            floating: false,
            style: {
              fontSize: '16px',
              color: '#263238'
            },
          },
          subtitle: {
            text: 'Marzo, 27 - Abril, 2',
            align: 'center',
            margin: 10,
            offsetX: 0,
            offsetY: 20,
            floating: false,
            style: {
              fontSize: '12px',
              color: '#9699a2'
            },
          },
          legend: {
            show: true,
            position: 'top'
          },
        },
        series: [{
          name: 'Citas Agendadas',
          data: [60, 45, 55, 49, 60, 70, 91]
        }],
        type: 'line'
      },
      chart2: {
        height: '300px',
        width: '100%',
        chartOptions: {
          chart: {
            id: 'char2id'
          },
          labels: ['Masculino', 'Femenino', 'No Especificado'],
          title: {
            text: 'Pacientes por genero',
            align: 'center',
            margin: 10,
            offsetX: 0,
            offsetY: 0,
            floating: false,
            style: {
              fontSize: '16px',
              color: '#263238'
            },
          },
        },
        series: [30, 90, 30],
        dataLabels: {
          enabled: true,
          formatter: function(val) {
            return val + "%"
          }
        },
        type: 'donut'
      },
      dialog: false,
      /*headers: [{
          text: 'Dessert (100g serving)',
          align: 'left',
          sortable: false,
          value: 'name'
        },
        {
          text: 'Calories',
          value: 'calories'
        },
        {
          text: 'Fat (g)',
          value: 'fat'
        },
        {
          text: 'Carbs (g)',
          value: 'carbs'
        },
        {
          text: 'Protein (g)',
          value: 'protein'
        },
        {
          text: 'Actions',
          value: 'name',
          sortable: false
        }
      ],
      */
      todayMAByCurrentDoctorHeaders: [{
          text: 'Paciente',
          align: 'center',
          sortable: true,
          value: 'fullName'
        },
        {
          text: 'Codigo de Historial',
          align: 'center',
          sortable: true,
          value: 'historyCode'
        },
        {
          text: 'Hora de la Cita',
          align: 'center',
          sortable: true,
          value: 'dateAppointment'
        },
        {
          text: 'Tipo de Cita',
          align: 'center',
          sortable: true,
          value: 'medicalAppointmentType'
        },
        {
          text: 'Estado de la Cita',
          align: 'center',
          sortable: true,
          value: 'status'
        },
      ],
      headers: [{
          text: 'Paciente',
          align: 'center',
          sortable: true,
          value: 'fullName'
        },
        {
            text: 'Doctor',
            align: 'center',
            sortable: true,
            value: 'emFullName'
        },
        {
          text: 'Codigo de Historial',
          align: 'center',
          sortable: true,
          value: 'historyCode'
        },
        {
          text: 'Hora de la Cita',
          align: 'center',
          sortable: true,
          value: 'dateAppointment'
        },
        {
          text: 'Tipo de Cita',
          align: 'center',
          sortable: true,
          value: 'medicalAppointmentType'
        },
        {
          text: 'Estado de la Cita',
          align: 'center',
          sortable: true,
          value: 'status'
        },
      ],
      desserts: [],
      data: [],
      editedIndex: -1,
      editedItem: {
        name: '',
        calories: 0,
        fat: 0,
        carbs: 0,
        protein: 0
      },
      defaultItem: {
        name: '',
        calories: 0,
        fat: 0,
        carbs: 0,
        protein: 0
      }
    }
  },
  computed: {
    formTitle() {
      return this.editedIndex === -1 ? 'New Item' : 'Edit Item'
    },
    metadataBrowse() {
      return this.$store.state.browse.metadata;
    },
    dataBrowse() {
      return this.$store.state.browse.data;
    }

  },

  watch: {
    dialog(val) {
      val || this.close()
    }
  },
  created: function() {
    console.log('DashboardPage - created - begin')
    const {
      requestPage
    } = this;
    const {
      dispatch
    } = this.$store;
    //Doctor
    if (this.$parent.user.userProfile.roles.some(role => role['id'] === 7)) {
      const dataContent = {
        "browseName": 'todayMedicalAppointmentsByLoggedDoctor',
        "browseParameters": {
          "doctorId": this.$parent.user.userProfile.id
        }
      }
      dispatch('browse/getBrowseData', {
        requestPage: requestPage,
        processName: '',
        dataContent: dataContent,
        vueInstance: this
      });
      //Secretary
    } else if (this.$parent.user.userProfile.roles.some(role => role['id'] === 6)) {
      const dataContent = {
        "browseName": 'medicalAppointmentsToday',
      }
      dispatch('browse/getBrowseData', {
        requestPage: requestPage,
        processName: '',
        dataContent: dataContent,
        vueInstance: this
      });
    }
    console.log('DashboardPage - created - end')
  },
  mounted: function() {

    //Assing this vue compnent to self to don't lose reference
    console.log('DashboardPage - mounted - end');
  },
  methods: {
    editItem(item) {
      this.editedIndex = this.desserts.indexOf(item)
      this.editedItem = Object.assign({}, item)
      this.dialog = true
    },

    deleteItem(item) {
      const index = this.desserts.indexOf(item)
      confirm('Are you sure you want to delete this item?') && this.desserts.splice(index, 1)
    },

    close() {
      this.dialog = false
      setTimeout(() => {
        this.editedItem = Object.assign({}, this.defaultItem)
        this.editedIndex = -1
      }, 300)
    },

    save() {
      if (this.editedIndex > -1) {
        Object.assign(this.desserts[this.editedIndex], this.editedItem)
      } else {
        this.desserts.push(this.editedItem)
      }
      this.close()
    }
  }
}
</script>
