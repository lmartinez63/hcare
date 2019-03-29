<template>
<v-app>
<div class="content-container">
  <section class="contPortlet">
  </section>
  <section class="contentGraph">
    <v-chart v-bind:chartData="chartData"></v-chart>
  </section>
  <section>
    <v-card class="mx-auto" color="#26c6da" dark max-width="400">
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
            <v-img
              class="elevation-6"
              src="https://avataaars.io/"
            ></v-img>
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
  <section>
    <v-toolbar flat color="white">
      <v-toolbar-title>Mi Citas del dia</v-toolbar-title>
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
    <v-data-table :headers="headers" :items="desserts" class="elevation-1">
      <template slot="items" slot-scope="props">
        <td>{{ props.item.name }}</td>
        <td class="text-xs-right">{{ props.item.calories }}</td>
        <td class="text-xs-right">{{ props.item.fat }}</td>
        <td class="text-xs-right">{{ props.item.carbs }}</td>
        <td class="text-xs-right">{{ props.item.protein }}</td>
        <td class="justify-center layout px-0">
          <v-icon small class="mr-2" @click="editItem(props.item)">edit</v-icon>
          <v-icon small @click="deleteItem(props.item)">delete</v-icon>
        </td>
      </template>
    </v-data-table>
  </section>
  <section class="contentGraph">
    <v-chart v-bind:chartData="chartData1"></v-chart>
  </section>
  <section class="contentGraph">
    <v-chart v-bind:chartData="chartData2"></v-chart>
  </section>

</div>
</div>
</v-app>
</template>
<script>

</script>
<script>
//TODO Improve dashboard to portlets come database
const totalOfPatientsPerDay = [{
    date: '14/03/2019',
    totalOfPatients: 98,
    attendedPatients: 95
  },
  {
    date: '15/03/2019',
    totalOfPatients: 76,
    attendedPatients: 71
  },
  {
    date: '16/03/2019',
    totalOfPatients: 85,
    attendedPatients: 84
  },
  {
    date: '17/03/2019',
    totalOfPatients: 65,
    attendedPatients: 64
  },
  {
    date: '18/03/2019',
    totalOfPatients: 55,
    attendedPatients: 51
  },
  {
    date: '19/03/2019',
    totalOfPatients: 62,
    attendedPatients: 60
  },
  {
    date: '20/03/2019',
    totalOfPatients: 52,
    attendedPatients: 40
  },
  {
    date: '20/03/2019',
    totalOfPatients: 55,
    attendedPatients: 49
  },
  {
    date: '21/03/2019',
    totalOfPatients: 32,
    attendedPatients: 30
  },
  {
    date: '22/03/2019',
    totalOfPatients: 62,
    attendedPatients: 60
  },
  {
    date: '23/03/2019',
    totalOfPatients: 67,
    attendedPatients: 55
  },
  {
    date: '24/03/2019',
    totalOfPatients: 67,
    attendedPatients: 55
  },
  {
    date: '25/03/2019',
    totalOfPatients: 67,
    attendedPatients: 55
  },
  {
    date: '26/03/2019',
    totalOfPatients: 57,
    attendedPatients: 55
  },
  {
    date: '27/03/2019',
    totalOfPatients: 59,
    attendedPatients: 50
  },
  {
    date: '28/03/2019',
    totalOfPatients: 47,
    attendedPatients: 35
  },
];
const grades1 = [{
    Name: 'Mary',
    s1Grade: 98,
    s2Grade: 95
  },
  {
    Name: 'Sam',
    s1Grade: 76,
    s2Grade: 81
  },
  {
    Name: 'Steve',
    s1Grade: 85,
    s2Grade: 84
  },
  {
    Name: 'Jill',
    s1Grade: 87,
    s2Grade: 90
  },
  {
    Name: 'Ben',
    s1Grade: 63,
    s2Grade: 75
  },
  {
    Name: 'Brian',
    s1Grade: 65,
    s2Grade: 62
  },
  {
    Name: 'Isabel',
    s1Grade: 90,
    s2Grade: 85
  }
];
const typeMedicalAppointmentByDoctor = [{
    type: 'Nueva Cita',
    countType: 98
  },
  {
    type: 'Control',
    countType: 62
  },
  {
    type: 'Ecografia',
    countType: 24
  },
  {
    type: 'Cirugia con Hospitalizacion',
    countType: 87
  },
];
export default {
  name: 'DashboardPage',
  data() {
    return {
      dashboardInfo: {
        qtyMedAppPerDay: 0,
        qtyMedAppAttendedPerDay: 0,
        qtyMedAppNotAttendedPerDay: 0,
      },
      chartData: {
        chartType: 'vBarChart',
        selector: 'chart',
        title: 'Numero de pacientes durante las ultimas 2 semanas',
        subtitle: '14 de Marzo - 28 de Marzo',
        width: 500,
        height: 400,
        metric: ['totalOfPatients', 'attendedPatients'],
        dim: 'date',
        data: totalOfPatientsPerDay,
        legends: {
          enabled: true,
        },
        overrides: {
          palette: {
            fill: ['#34495E', '#4fc08d'],
            stroke: '#41B883'
          },
          x: {
            ticks: 20
          },
          y: {
            axisWidth: 40,
          }
        }
      },
      chartData1: {
        chartType: 'vBarChart',
        selector: 'chart1',
        title: 'Student Grades1',
        subtitle: '2018 - 2019',
        width: 500,
        height: 400,
        metric: ['s1Grade', 's2Grade'],
        dim: 'Name',
        data: grades1,
        legends: {
          enabled: true,
        },
        overrides: {
          palette: {
            fill: ['#34495E', '#4fc08d'],
            stroke: '#41B883'
          },
          x: {
            ticks: 20
          },
          y: {
            axisWidth: 40,
          }
        }
      },
      chartData2: {
        chartType: 'pieChart',
        selector: 'chart2',
        title: 'Tipos de las Consultas',
        subtitle: 'Masculino/Femeninos',
        width: 500,
        height: 400,
        metric: ['countType'],
        dim: 'type',
        data: typeMedicalAppointmentByDoctor,
      },
      dialog: false,
    headers: [
      {
        text: 'Dessert (100g serving)',
        align: 'left',
        sortable: false,
        value: 'name'
      },
      { text: 'Calories', value: 'calories' },
      { text: 'Fat (g)', value: 'fat' },
      { text: 'Carbs (g)', value: 'carbs' },
      { text: 'Protein (g)', value: 'protein' },
      { text: 'Actions', value: 'name', sortable: false }
    ],
    desserts: [],
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
    formTitle () {
      return this.editedIndex === -1 ? 'New Item' : 'Edit Item'
    }
  },

  watch: {
    dialog (val) {
      val || this.close()
    }
  },
  created: function() {
    console.log('DashboardPage - created - begin')
    this.initialize();
    console.log('DashboardPage - created - end')
  },
  mounted: function() {
    console.log('DashboardPage - mounted - end')
    //Assing this vue compnent to self to don't lose reference
    let self = this

  },
  methods: {
    initialize () {
      this.desserts = [
        {
          name: 'Frozen Yogurt',
          calories: 159,
          fat: 6.0,
          carbs: 24,
          protein: 4.0
        },
        {
          name: 'Ice cream sandwich',
          calories: 237,
          fat: 9.0,
          carbs: 37,
          protein: 4.3
        },
        {
          name: 'Eclair',
          calories: 262,
          fat: 16.0,
          carbs: 23,
          protein: 6.0
        },
        {
          name: 'Cupcake',
          calories: 305,
          fat: 3.7,
          carbs: 67,
          protein: 4.3
        },
        {
          name: 'Gingerbread',
          calories: 356,
          fat: 16.0,
          carbs: 49,
          protein: 3.9
        },
        {
          name: 'Jelly bean',
          calories: 375,
          fat: 0.0,
          carbs: 94,
          protein: 0.0
        },
        {
          name: 'Lollipop',
          calories: 392,
          fat: 0.2,
          carbs: 98,
          protein: 0
        },
        {
          name: 'Honeycomb',
          calories: 408,
          fat: 3.2,
          carbs: 87,
          protein: 6.5
        },
        {
          name: 'Donut',
          calories: 452,
          fat: 25.0,
          carbs: 51,
          protein: 4.9
        },
        {
          name: 'KitKat',
          calories: 518,
          fat: 26.0,
          carbs: 65,
          protein: 7
        }
      ]
    },

    editItem (item) {
      this.editedIndex = this.desserts.indexOf(item)
      this.editedItem = Object.assign({}, item)
      this.dialog = true
    },

    deleteItem (item) {
      const index = this.desserts.indexOf(item)
      confirm('Are you sure you want to delete this item?') && this.desserts.splice(index, 1)
    },

    close () {
      this.dialog = false
      setTimeout(() => {
        this.editedItem = Object.assign({}, this.defaultItem)
        this.editedIndex = -1
      }, 300)
    },

    save () {
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
