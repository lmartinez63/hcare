import config from 'config';
import {
  authHeader,
  getTokenAuthHeader
} from '../_helpers';
import { userService,generalService } from '../_services';

export const dataResponseService = {
  getContent,
  getBrowseData
};

function getContent( requestPage, processName, dataContent ) {
  console.log('dataResponseService - getContent');
  var requestData =  {
	   "requestPage":requestPage,
	   "processName":processName,
	   "data":dataContent
  }
  const requestOptions = {
    method: 'POST',
    headers: { 'Authorization':authHeader().Authorization, 'Content-Type': 'application/json' },
    body: JSON.stringify(requestData)
  };
  return fetch(`${config.apiUrl}/getContent/`, requestOptions).then(handleResponse);
}

function getBrowseData( requestPage, processName, dataContent ) {
  console.log('dataResponseService - getBrowseData');
  var requestData =  {
	   "requestPage":requestPage,
	   "processName":processName,
	   "data":dataContent
  }
  const requestOptions = {
    method: 'POST',
    headers: { 'Authorization':authHeader().Authorization, 'Content-Type': 'application/json' },
    body: JSON.stringify(requestData)
  };
  return fetch(`${config.apiUrl}/getBrowseContent/`, requestOptions).then(handleResponse);
}

function handleResponse(response) {
  return response.text().then(text => {
    try {
      const data = text && JSON.parse(text);
      if (!response.ok) {
        if (response.status === 401) {
          // auto logout if 401 response returned from api
          //TODO Pending test this feature
          console.log('dataResponseService - handleResponse - logout');
          userService.logout();
          location.reload(true);
        }

        const error = (data && data.message) || response.statusText;
        return Promise.reject(error);

      }
      return data;
    } catch (error) {
      return Promise.reject(response.statusText);
    }

  });
}
