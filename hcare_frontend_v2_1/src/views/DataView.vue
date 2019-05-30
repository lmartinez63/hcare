<template>
  <v-container
    fill-height
    fluid
    grid-list-xl
  >
    <v-layout
      v-if="page && dataMap"
      justify-center
      wrap
    >
      <v-flex xs12>
        <material-card
          color="green"
          :title="getDataMapAttribute(dataMap,page.titleDefinition)"
          :text="getDataMapAttribute(dataMap,page.subTitleDefinition)"
        >
          <v-layout align-center>
            <v-item-group
              v-model="window"
              class="shrink mr-4"
              mandatory
              tag="v-flex"
            >
              <v-item
                v-for="section in orderedSections"
                :key="section.sectionCode"
              >
                <div slot-scope="{ active, toggle }">
                  <v-btn
                    :input-value="active"
                    icon
                    @click="toggle"
                  >
                    <v-icon>mdi-record</v-icon>
                  </v-btn>
                </div>
              </v-item>
            </v-item-group>
            <v-flex>
              <v-window
                v-if="page.sectionList.length > 0"
                v-model="window"
                class="elevation-1"
                vertical
              >
                <v-window-item
                  v-for="section in orderedSections"
                  :key="section.sectionCode"
                >
                  <v-layout
                    align-center
                    mb-3
                  >
                    <v-flex
                      xs12
                      sm6
                      md1
                    />
                    <strong class="title">{{ $parent.$parent.$parent.getLabelValue(section.label) }}</strong>
                    <v-spacer />
                  </v-layout>
                  <v-form>
                    <v-container py-0>
                      <v-layout wrap>
                        <v-flex
                          v-for="fieldDefinition in section.fieldDefinitionList"
                          :key="fieldDefinition.fieldDefinitionCode"
                        >
                          <!-- TextField -->
                          <v-text-field
                            v-if="fieldDefinition.fieldType === 1"
                            v-model="dataMap[section.entity][fieldDefinition.fieldDefinitionCode]"
                            :label="fieldDefinition.label.labelValueEsEs"
                            :disabled="!fieldDefinition.editable"
                          />
                          <!-- Selects -->
                          <v-autocomplete
                            v-if="fieldDefinition.fieldType === 2"
                            ref="dataMap[section.entity][fieldDefinition.fieldDefinitionCode]"
                            v-model="dataMap[section.entity][fieldDefinition.fieldDefinitionCode]"
                            :rules="[() => !! dataMap[section.entity][fieldDefinition.fieldDefinitionCode] || 'Este campo es requerido']"
                            :items="propertyItems[fieldDefinition.selectSource]"
                            :label="fieldDefinition.label.labelValueEsEs"
                            placeholder="Seleccione..."
                          />
                          <!-- ComboBox -->
                          <v-switch
                            v-if="fieldDefinition.fieldType === 3"
                            v-model="dataMap[section.entity][fieldDefinition.fieldDefinitionCode]"
                            :label="fieldDefinition.label.labelValueEsEs"
                          />
                          <v-dialog
                            v-if="fieldDefinition.fieldType === 4"
                            ref="dialog"
                            v-model="fieldDefinition.modal"
                            :return-value.sync="dataMap[section.entity][fieldDefinition.fieldDefinitionCode]"
                            persistent
                            lazy
                            full-width
                            width="290px"
                          >
                            <template v-slot:activator="{ on }">
                              <v-text-field
                                model="section.entity[fieldDefinition.fieldDefinitionCode]"
                                :value="$parent.$parent.$parent.computedDateFormattedMomentjs(dataMap[section.entity][fieldDefinition.fieldDefinitionCode])"
                                :label="fieldDefinition.label.labelValueEsEs"
                                prepend-icon="mdi-calendar"
                                readonly
                                v-on="on"
                              />
                            </template>
                            <v-date-picker
                              v-model="new Date(dataMap[section.entity][fieldDefinition.fieldDefinitionCode]).toJSON()"
                              scrollable
                              locale="es"
                            >
                              <v-spacer />
                              <v-btn
                                flat
                                color="primary"
                                @click="fieldDefinition.modal = false"
                              >
                                Cancelar
                              </v-btn>
                              <v-btn
                                flat
                                color="primary"
                                @click="$refs.dialog[0].save(dataMap[section.entity][fieldDefinition.fieldDefinitionCode])"
                              >
                                OK
                              </v-btn>
                            </v-date-picker>
                          </v-dialog>
                        </v-flex>
                        <!--
                        <v-flex
                          xs12
                          text-xs-right
                        >
                          <v-btn
                            class="mx-0 font-weight-light"
                            color="success"
                            @click="saveObjectState()"
                          >
                            Update Profile
                          </v-btn>
                        </v-flex>
                        -->
                      </v-layout>
                    </v-container>
                  </v-form>
                </v-window-item>
              </v-window>
            </v-flex>
          </v-layout>
          <v-speed-dial
            v-model="fab"
            :top="optionButtonTop"
            :bottom="optionButtonBottom"
            :right="optionButtonRight"
            :left="optionButtonLeft"
            :direction="optionButtonDirection"
            :open-on-hover="optionButtonHover"
            :transition="optionButtonTransition"
          >
            <template v-slot:activator>
              <v-btn
                v-model="fab"
                color="blue darken-2"
                dark
                fab
              >
                <v-icon>mdi-dots-vertical</v-icon>
                <v-icon>mdi-close</v-icon>
              </v-btn>
            </template>
            <v-btn
              v-for="pageButton in page.pageButtons"
              v-if="pageButton.visible"
              :key="pageButton.buttonCode"
              fab
              dark
              small
              color="green"
              @click="executeAction(pageButton)"
            >
              <v-icon>{{ pageButton.icon }}</v-icon>
            </v-btn>
          </v-speed-dial>
        </material-card>
      </v-flex>
      <!-- Upload Attachment -->
      <v-dialog
        v-if="createUploadFileDialog"
        v-model="uploadFileDialog"
        persistent
        max-width="600px"
      >
        <v-card>
          <v-card-title>
            <span class="headline">Subir Documento</span>
          </v-card-title>
          <v-card-text>
            <v-container grid-list-md>
              <v-layout wrap>
                <v-flex
                  xs12
                  sm6
                  md4
                >
                  <v-text-field
                    v-model="fileName"
                    label="Nombre del Documento"
                  />
                </v-flex>
                <v-flex
                  xs12
                  sm6
                >
                  <v-autocomplete
                    :items="['Historial Medico', 'Informacion del Paciente']"
                    label="Categoria"
                  />
                </v-flex>
                <v-flex
                  xs12
                  sm6
                >
                  <input
                    id="file"
                    ref="file"
                    type="file"
                    @change="handleFileUpload()"
                  >
                </v-flex>
              </v-layout>
            </v-container>
          </v-card-text>
          <v-card-actions>
            <v-spacer />
            <v-btn
              color="blue darken-1"
              flat
              @click="uploadFileDialog = false"
            >
              Close
            </v-btn>
            <v-btn
              color="blue darken-1"
              flat
              @click="submitFile"
            >
              Save
            </v-btn>
          </v-card-actions>
        </v-card>
      </v-dialog>
    </v-layout>
  </v-container>
</template>

<script>

import _ from 'lodash'

let installed = false

export default {
  name: 'DataView',
  data () {
    return {
      modal: false,
      dateElements: 0,
      window: 0,
      optionButtonDirection: 'bottom',
      fab: false,
      optionButtonHover: false,
      optionButtonTop: true,
      optionButtonRight: true,
      optionButtonBottom: false,
      optionButtonLeft: false,
      optionButtonTransition: 'slide-y-reverse-transition',
      requestPage: '',
      createUploadFileDialog: false,
      uploadFileDialog: true,
      fileName: '',
      file: '',
      attachment: {
        entityCode: 0,
        entityId: 0,
        entity: '',
      },
    }
  },
  computed: {
    propertyItems () {
      return this.$store.state.general.properties.items.GENERAL
    },
    dataMap () {
      return this.$store.state.data.dataMap
    },
    page () {
      return this.$store.state.data.metadata.page
    },
    orderedSections: function () {
      return _.orderBy(this.page.sectionList, 'visualizationOrder')
    }
  },
  created: function () {
    console.log('DataPage - created - begin')
    const { processName } = this.$route.params
    const { requestPage } = this.$route.params
    this.requestPage = requestPage
    const queryParams = this.$route.query
    const { dispatch } = this.$store
    dispatch('data/getData', {
      requestPage: requestPage,
      processName: processName,
      dataContent: JSON.parse(JSON.stringify(queryParams))
    })
    console.log('DataPage - created - end')
  },
  mounted: function () {
    console.log('DataPage - mounted - begin')
    console.log('DataPage - mounted - end')
  },
  methods: {
    getDataMapAttribute: function (dataMap, attribute) {
      if (attribute && attribute !== null) {
        var attributeArray = attribute.split('.')
        if (attributeArray.length > 0) {
          var finalAttribute = dataMap[attributeArray[0]]
          for (var i = 1; i < attributeArray.length; i++) {
            finalAttribute = finalAttribute[attributeArray[i]]
          }
        }
        return finalAttribute
      }
      return ''
    },
    getValueFromVariable: function(variable) {
      var variableArray = variable.match(/\${{(.*?)}}/g)

      if ( variableArray == null || variableArray.length < 1 ){
        return variable;
      }
      var returnString = variable;
      for (var i = 0, len = variableArray.length; i < len; i++) {
        var dataVariable = variableArray[i]
        returnString = returnString.replace(dataVariable, eval(dataVariable.match(/\$\{\{([^)]+)\}\}/)[1]))
      }
      return returnString
    },
    submitFile () {
      // Initialize the form data
      let formData = new FormData()
      // Add the form data we need to submit
      formData.append('file', this.file)
      formData.append('fileName', this.fileName)
      formData.append('entity', this.getValueFromVariable(this.attachment.entity))
      formData.append('entityId', this.getValueFromVariable(this.attachment.entityId))
      formData.append('entityCode', this.getValueFromVariable(this.attachment.entityCode))

      const {
        dispatch
      } = this.$store
      // this.$v.$touch()
      /* if (this.$v.$invalid) {
        dispatch('alert/warning', 'Por favor complete los campos requeridos')
      } else {
      */
      dispatch('data/uploadFile', {
        vm: this,
        formData: formData
      }).then(function (response) {
        console.log('sucessUpload')
        console.log(response)
      })
        .catch(function (response) {
        // handle error
          console.log('errorUpload')
          console.log(response)
        })
      this.uploadFileDialog = false
    },
    // Handles a change on the file upload
    handleFileUpload () {
      this.file = this.$refs.file.files[0]
    },
    executeAction: function (button) {
      let selfVue = this
      switch (button.buttonType) {
        case 1:
          var routeObject = {}
          var jsonString = button.eventDefinition
          var eventArray = button.eventDefinition.match(/\${{(.*?)}}/g)
          for (var i = 0, len = eventArray.length; i < len; i++) {
            var dataRouteVariable = eventArray[i]
            jsonString = jsonString.replace(dataRouteVariable, eval(dataRouteVariable.match(/\$\{\{([^)]+)\}\}/)[1]))
          }
          routeObject = JSON.parse(jsonString)
          this.$router.push(routeObject)
          break
        case 2:
          // TOREMOVE
          eval(button.eventDefinition)
          break
      }
    },
    goBackBrowse: function () {
      this.$router.push({
        name: 'BrowseComponent'
      })
    },
    /*
    validateDocumentNumber: function () {
      axios.post(url, this.patient)
        .then(response => {
          selfVue.patient = response.data
          selfVue.$parent.sucessMessage()
          // this.patient.birthday = this.frontEndDateFormat(this.patient.birthday)
          setTimeout(() => {
            this.$router.push({
              name: 'BrowseComponent',
              params: {
                browseType: 'allPatients',
                entityId: 'null'
              }
            })
          }, 1000)
        })
        .catch(error => {
          // this.patient.birthday = this.frontEndDateFormat(this.patient.birthday)
          console.log(error)
        })
    },
    */
    getPatientInfo: function () {
      let selfVue = this
      /*
      axios.get(this.$parent.backendUrl + 'retrievePatientByDocumentNumber/' + this.patient.documentNumber)
        .then(response => {
          if (response.data != null && response.data !== '') {
            selfVue.patient = response.data
          }
          // this.patient.birthday = this.frontEndDateFormat(this.patient.birthday)
        })
        .catch(error => {
          console.log(error)
        })
        */
    },
    saveObjectState: function (sAttributeArray, processName) {
      console.log('DataView - method - saveObjectState - begin')
      var attributeArray = sAttributeArray.split(',')
      var dataContent = {}
      for (var i = 0; i < attributeArray.length; i++) {
        Object.defineProperty(dataContent, attributeArray[i], { value: this.dataMap[attributeArray[i]], writable: true, enumerable: true, configurable: true })
      }
      const {
        requestPage
      } = this
      const {
        dispatch
      } = this.$store
      // this.$v.$touch()
      /* if (this.$v.$invalid) {
        dispatch('alert/warning', 'Por favor complete los campos requeridos')
      } else {
      */
      dispatch('data/saveEntity', {
        vm: this,
        requestPage: requestPage,
        processName: processName,
        dataContent: dataContent
        //        returnRoute: returnRoute
      })
      // }
      console.log('MedicalAppointmentPage - method - saveObjectState - end')
    },
    viewMedicalHistory: function (historyCode) {
      this.$router.push({
        name: 'MedicalHistoryComponent',
        params: {
          historyCode: historyCode
        }
      })
    },
    viewMedicalAppointmentHistory: function (documentNumber) {
      this.$router.push({
        name: 'BrowsePage',
        params: {
          browseName: 'medicalAppointmentsByPatient',
          entityId: documentNumber
        }
      })
    }
  }
}
</script>
