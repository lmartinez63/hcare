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
          :title="getDataMapAttribute(dataMap,page.titleDefinition)"
          :text="getDataMapAttribute(dataMap,page.subTitleDefinition)"
        >
          <v-layout
            v-if="page.sectionList.length > 0"
            align-center
          >
            <v-tabs
              v-model="tab"
              grow
              style="width:100%;"
            >
              <v-tab
                v-for="section in orderedSections"
                :key="section.sectionCode"
              >
                {{ $parent.$parent.$parent.getLabelValue(section.label) }}
              </v-tab>
              <v-tab-item
                v-for="section in orderedSections"
                :key="section.sectionCode"
              >
                <v-form>
                  <v-container py-6>
                    <v-layout
                      v-if="section.sectionType === 2 "
                      wrap
                    >
                      <v-card width="100%">
                        <v-sheet
                          class="pa-3 lighten-2"
                          dark
                        >
                          <v-text-field
                            v-model="search"
                            label="Search Company Directory"
                            dark
                            flat
                            solo-inverted
                            hide-details
                            clearable
                            clear-icon="mdi-close-circle-outline"
                          />
                          <v-checkbox
                            v-model="caseSensitive"
                            dark
                            hide-details
                            label="Case sensitive search"
                          />
                        </v-sheet>
                        <v-layout>
                          <v-flex>
                            <v-card-text>
                              <v-treeview
                                v-if="dataMap[page.entity].files"
                                :active.sync="active"
                                :items="treeviewItems"
                                :search="search"
                                :filter="filter"
                                :open.sync="open"
                                activatable
                                item-key="name"
                                open-on-click
                                return-object
                              >
                                <template v-slot:prepend="{ item, open }">
                                  <v-icon v-if="!item.fileType">
                                    {{ open ? 'mdi-folder-open' : 'mdi-folder' }}
                                  </v-icon>
                                  <v-icon v-else>
                                    {{ files[item.fileType] }}
                                  </v-icon>
                                </template>
                              </v-treeview>
                            </v-card-text>
                          </v-flex>
                          <v-divider vertical />
                          <v-flex
                            xs12
                            md6
                          >
                            <v-card>
                              <v-card-text v-if="!selected">
                                <div
                                  class="title font-weight-light grey--text pa-3 text-xs-center"
                                  style="align-self: center;"
                                >
                                  Seleccione una imagen
                                </div>
                              </v-card-text>
                              <v-img
                                v-else
                                :key="selected.id"
                                :src="getImgSrc(selected)"
                                class="grey darken-4"
                              />
                            </v-card>
                          </v-flex>
                        </v-layout>
                      </v-card>
                    </v-layout>
                    <v-layout
                      v-if="section.sectionType === 1 "
                      wrap
                    >
                      <v-flex
                        v-for="fieldDefinition in section.fieldDefinitionList"
                        :key="fieldDefinition.fieldDefinitionCode"
                        xs6
                      >
                        <v-text-field
                          v-if="fieldDefinition.fieldType === 1"
                          v-model="dataMap[section.entity][fieldDefinition.fieldDefinitionCode]"
                          :label="fieldDefinition.label.labelValueEsEs"
                          :disabled="!fieldDefinition.editable"
                          @change="executeFieldChangeEvent(fieldDefinition.onChangeEvent)"
                        />
                        <v-autocomplete
                          v-if="fieldDefinition.fieldType === 2"
                          ref="dataMap[section.entity][fieldDefinition.fieldDefinitionCode]"
                          v-model="dataMap[section.entity][fieldDefinition.fieldDefinitionCode]"
                          :rules="[() => !! dataMap[section.entity][fieldDefinition.fieldDefinitionCode] || 'Este campo es requerido']"
                          :items="arrayItems(fieldDefinition.selectSource)"
                          :label="fieldDefinition.label.labelValueEsEs"
                          placeholder="Seleccione..."
                          :return-object="getReturnObject(fieldDefinition.selectSource)"
                          :item-text="getItemText(fieldDefinition.selectSource)"
                        />
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
                              model="dataMap[section.entity][fieldDefinition.fieldDefinitionCode]"
                              :value="$parent.$parent.$parent.computedDateFormattedMomentjs(dataMap[section.entity][fieldDefinition.fieldDefinitionCode])"
                              :label="fieldDefinition.label.labelValueEsEs"
                              prepend-icon="mdi-calendar"
                              readonly
                              v-on="on"
                            />
                          </template>
                          <v-date-picker
                            v-model="dataMap[section.entity][fieldDefinition.fieldDefinitionCode]"
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
                        <v-datetime-picker
                          v-if="fieldDefinition.fieldType === 5"
                          v-model="dataMap[section.entity][fieldDefinition.fieldDefinitionCode]"
                          :label="fieldDefinition.label.labelValueEsEs"
                        >
                          <template v-slot:dateIcon>
                            <v-icon >
                              mdi-calendar
                            </v-icon>
                          </template>
                          <template v-slot:timeIcon>
                            <v-icon >
                              mdi-clock-outline
                            </v-icon>
                          </template>
                        </v-datetime-picker>
                      </v-flex>
                    </v-layout>
                  </v-container>
                </v-form>
              </v-tab-item>
            </v-tabs>
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
                color="darken-2"
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
                    v-model="fileTitle"
                    label="Titulo del Documento"
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
      tab: null,
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
      fileTitle: '',
      file: '',
      attachment: {
        entityCode: 0,
        entityId: 0,
        entity: ''
      },
      active: [],
      // Testing
      // window: 0,
      open: [1, 2],
      search: null,
      caseSensitive: false,
      // Testing 2
      date: new Date().toISOString().substr(0, 10),
      files: {
        html: 'mdi-language-html5',
        js: 'mdi-nodejs',
        json: 'mdi-json',
        md: 'mdi-markdown',
        pdf: 'mdi-file-pdf',
        png: 'mdi-file-image',
        txt: 'mdi-file-document-outline',
        xls: 'mdi-file-excel'
      },
      tree: []
    }
  },
  computed: {
    filter () {
      return this.caseSensitive
        ? (item, search, textKey) => item[textKey].indexOf(search) > -1
        : undefined
    },
    propertyItems () {
      return this.$store.state.general.properties.items.GENERAL
    },
    dataMap () {
      return this.$store.state.data.dataMap
    },
    page () {
      return this.$store.state.data.metadata.page
    },
    treeviewItems () {
      return this.dataMap[this.page.entity].files
    },
    orderedSections: function () {
      return _.orderBy(this.page.sectionList, 'visualizationOrder')
    },
    selected () {
      if (!this.active.length) return undefined
      return this.active[0]
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
    getReturnObject(selectSource){
      if (selectSource && selectSource != null) {
        var selectSourceJSON = JSON.parse(selectSource)
        if(selectSourceJSON.returnObject && selectSourceJSON.returnObject !== ''){
          return selectSourceJSON.returnObject
        }
      }
      return false
    },
    getItemText(selectSource){
      if (selectSource && selectSource != null) {
        var selectSourceJSON = JSON.parse(selectSource)
        if(selectSourceJSON.itemText && selectSourceJSON.itemText !== ''){
          return selectSourceJSON.itemText
        }
        return "text"
      }
      return "text"
    },
    arrayItems (selectSource) {
      if (selectSource && selectSource != null) {
        var selectSourceJSON = JSON.parse(selectSource)
        var source = selectSourceJSON.source
        var finalSelectSource = this.$store.state
        var selectSourceArray = selectSourceJSON.source.split('.')
        for (var i = 0; i < selectSourceArray.length; i++) {
          finalSelectSource = finalSelectSource[selectSourceArray[i]]
        }
        return finalSelectSource
      }
      return []
    },
    getImgSrc: function (imageObj) {
      return 'data:' + imageObj.contentType + ';base64,' + imageObj.src
    },
    currentEntity: function (currPage, currSection) {
      return (currSection && currSection.entity && currSection.entity !== '') ? currSection.entity : currPage.entity
    },
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
    getValueFromVariable: function (variable) {
      var variableArray = variable.match(/\${{(.*?)}}/g)

      if (variableArray == null || variableArray.length < 1) {
        return variable
      }
      var returnString = variable
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
      formData.append('fileTitle', this.fileTitle)
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
    executeFieldChangeEvent (changeEvent) {
      switch (changeEvent) {
        case 'getPatientInfo':
          this.getPatientInfo()
          break
        default:
          break
      }
    },
    getPatientInfo: function () {
      console.log('DataView - method - getPatientInfo - begin')
      const dataContent = {
        'documentNumber': this.dataMap.medicalAppointment.documentNumber
      }
      const {
        requestPage
      } = this
      const {
        dispatch
      } = this.$store
      dispatch('data/getPatientInfoByDocumentNumberOnMedAppointment', {
        requestPage: requestPage,
        processName: 'GetPatientByDocumentNumber',
        dataContent: dataContent
      })
      console.log('DataView - method - getPatientInfo - end')
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
