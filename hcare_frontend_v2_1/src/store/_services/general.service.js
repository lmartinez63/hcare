
import config from 'config'
import { authHeader } from '../_helpers'
import { userService } from '../_services'

export const generalService = {
  loadProperties,
  getDoctors,
  getRoles,
  getMedicalAreas
}

function loadProperties () {
  console.log('generalService - loadProperties')
  const requestOptions = {
    method: 'GET',
    headers: authHeader()
  }
  return fetch(`${config.apiUrl}/loadProperties`, requestOptions).then(handleResponse)
    .then(properties => {
      localStorage.setItem('properties', JSON.stringify(properties))
      return properties
    })
}
function getDoctors () {
  console.log('generalService - getDoctors')
  const requestOptions = {
    method: 'GET',
    headers: authHeader()
  }
  return fetch(`${config.apiUrl}/getDoctors`, requestOptions).then(handleResponse)
    .then(doctors => {
      localStorage.setItem('doctors', JSON.stringify(doctors))
      return doctors
    })
}
function getRoles () {
  console.log('generalService - getRoles')
  const requestOptions = {
    method: 'GET',
    headers: authHeader()
  }
  return fetch(`${config.apiUrl}/getRoles`, requestOptions).then(handleResponse)
    .then(doctors => {
      localStorage.setItem('roles', JSON.stringify(doctors))
      return doctors
    })
}
function getMedicalAreas () {
  console.log('generalService - getMedicalAreas')
  const requestOptions = {
    method: 'GET',
    headers: authHeader()
  }
  return fetch(`${config.apiUrl}/getMedicalAreas`, requestOptions).then(handleResponse)
    .then(medicalAreas => {
      localStorage.setItem('medicalAreas', JSON.stringify(medicalAreas))
      return medicalAreas
    })
}

function handleResponse (response) {
  return response.text().then(text => {
    try {
      const data = text && JSON.parse(text)
      if (!response.ok) {
        if (response.status === 401) {
          // auto logout if 401 response returned from api
          // TODO Pending test this feature
          userService.logout()
          location.reload(true)
        }

        const error = (data && data.message) || response.statusText
        return Promise.reject(error)
      }
      return data
    } catch (error) {
      return Promise.reject(response.statusText)
    }
  })
}
