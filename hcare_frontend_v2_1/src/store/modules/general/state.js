const properties = JSON.parse(localStorage.getItem('properties'))
const labels = JSON.parse(localStorage.getItem('labels'))
const doctors = JSON.parse(localStorage.getItem('doctors'))
const nurses = JSON.parse(localStorage.getItem('nurses'))
const medicalAreas = JSON.parse(localStorage.getItem('medicalAreas'))
const surgeryTypes = JSON.parse(localStorage.getItem('surgeryTypes'))
const roles = JSON.parse(localStorage.getItem('roles'))
const pages = JSON.parse(localStorage.getItem('pages'))
const sections = JSON.parse(localStorage.getItem('sections'))
const fieldDefinitions = JSON.parse(localStorage.getItem('fieldDefinitions'))

const initialStateProperties = properties ? {
  properties
} : {
  properties: null
}
const initialStateDoctors = doctors ? {
  doctors
} : {
  doctors: null
}
const initialStateNurses = nurses ? {
  nurses
} : {
  nurses: null
}
const initialStateMedicalAreas = medicalAreas ? {
  medicalAreas
} : {
  medicalAreas: null
}
const initialStateSurgeryTypes = surgeryTypes ? {
  surgeryTypes
} : {
  surgeryTypes: null
}
const initialStateRoles = roles ? {
  roles
} : {
  roles: null
}
const initialStatePages = pages ? {
  pages
} : {
  pages: null
}
const initialStateSections = sections ? {
  sections
} : {
  sections: null
}
const initialStateFieldDefinitions = fieldDefinitions ? {
  fieldDefinitions
} : {
  fieldDefinitions: null
}
const initialStateLabels = labels ? {
  labels
} : {
  labels: null
}

export default {
  properties: initialStateProperties,
  doctors: initialStateDoctors,
  nurses: initialStateNurses,
  medicalAreas: initialStateMedicalAreas,
  surgeryTypes: initialStateSurgeryTypes,
  roles: initialStateRoles,
  pages: initialStatePages,
  sections: initialStateSections,
  fieldDefinitions: initialStateFieldDefinitions,
  labels: initialStateLabels,
  loading: false
}
