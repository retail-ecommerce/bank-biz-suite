import { get,postForm,PREFIX,joinParameters,joinPostParameters } from '../../axios/tools'


const view = (targetObjectId) => {
  return get({
    url: `${PREFIX}platformManager/view/${targetObjectId}/`,
  })
}



const load = (targetObjectId, parameters) => {
  const parametersExpr = joinParameters(parameters)
  return get({
    url: `${PREFIX}platformManager/loadPlatform/${targetObjectId}/${parametersExpr}/`,
  })
}







const addChangeRequest = (targetObjectId, parameters) => {
  const url = `${PREFIX}platformManager/addChangeRequest/platformId/name/tokensExpr/`
  const platformId = targetObjectId
  const requestParameters = { ...parameters, platformId, tokensExpr: 'none' }
  return postForm({ url,requestParameters})
}

const updateChangeRequest = (targetObjectId, parameters) => {
  const url = `${PREFIX}platformManager/updateChangeRequestProperties/platformId/id/name/tokensExpr/`
  const platformId = targetObjectId
  const requestParameters = { ...parameters, platformId, tokensExpr: 'none' }
  return postForm({ url,requestParameters})
}

const removeChangeRequestList = (targetObjectId, parameters) => {
  const url = `${PREFIX}platformManager/removeChangeRequestList/platformId/changeRequestIds/tokensExpr/`
  const requestParameters = { ...parameters, platformId: targetObjectId, tokensExpr: 'none' }
  return postForm({ url,requestParameters})
}



const addAccount = (targetObjectId, parameters) => {
  const url = `${PREFIX}platformManager/addAccount/platformId/name/balance/tokensExpr/`
  const platformId = targetObjectId
  const requestParameters = { ...parameters, platformId, tokensExpr: 'none' }
  return postForm({ url,requestParameters})
}

const updateAccount = (targetObjectId, parameters) => {
  const url = `${PREFIX}platformManager/updateAccountProperties/platformId/id/name/balance/tokensExpr/`
  const platformId = targetObjectId
  const requestParameters = { ...parameters, platformId, tokensExpr: 'none' }
  return postForm({ url,requestParameters})
}

const removeAccountList = (targetObjectId, parameters) => {
  const url = `${PREFIX}platformManager/removeAccountList/platformId/accountIds/tokensExpr/`
  const requestParameters = { ...parameters, platformId: targetObjectId, tokensExpr: 'none' }
  return postForm({ url,requestParameters})
}


const PlatformService = { view,
  load,
  addChangeRequest,
  addAccount,
  updateChangeRequest,
  updateAccount,
  removeChangeRequestList,
  removeAccountList }
export default PlatformService

