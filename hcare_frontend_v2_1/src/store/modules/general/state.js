const properties = JSON.parse(localStorage.getItem('properties'))
const doctors = JSON.parse(localStorage.getItem('doctors'))
const medicalAreas = JSON.parse(localStorage.getItem('medicalAreas'))
const roles = JSON.parse(localStorage.getItem('roles'))

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
const initialStateMedicalAreas = medicalAreas ? {
  medicalAreas
} : {
  medicalAreas: null
}
const initialStateRoles = roles ? {
  roles
} : {
  roles: null
}

export default {
  properties: initialStateProperties,
  doctors: initialStateDoctors,
  medicalAreas: initialStateMedicalAreas,
  roles: initialStateRoles,
  loading: false
}
