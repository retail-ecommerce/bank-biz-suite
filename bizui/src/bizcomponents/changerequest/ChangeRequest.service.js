import { get,postForm,PREFIX,joinParameters,joinPostParameters } from '../../axios/tools'


const view = (targetObjectId) => {
  return get({
    url: `${PREFIX}changeRequestManager/view/${targetObjectId}/`,
  })
}



const load = (targetObjectId, parameters) => {
  const parametersExpr = joinParameters(parameters)
  return get({
    url: `${PREFIX}changeRequestManager/loadChangeRequest/${targetObjectId}/${parametersExpr}/`,
  })
}



const requestCandidatePlatform = (ownerClass, id, filterKey, pageNo) => {
 
  const url = `${PREFIX}changeRequestManager/requestCandidatePlatform/ownerClass/id/filterKey/pageNo/`
  const requestParameters = {id, ownerClass,filterKey, pageNo}
  return postForm({url,requestParameters})
}	

const transferToAnotherPlatform = (id, parameters) => {
  //const parametersExpr = joinParameters(parameters)
  const url = `${PREFIX}changeRequestManager/transferToAnotherPlatform/id/anotherPlatformId/`
  const requestParameters = {id, ...parameters}
  return postForm({url,requestParameters})
}







const addTransaction = (targetObjectId, parameters) => {
  const url = `${PREFIX}changeRequestManager/addTransaction/changeRequestId/name/fromAccountId/toAccountId/amount/type/tokensExpr/`
  const changeRequestId = targetObjectId
  const requestParameters = { ...parameters, changeRequestId, tokensExpr: 'none' }
  return postForm({ url,requestParameters})
}

const updateTransaction = (targetObjectId, parameters) => {
  const url = `${PREFIX}changeRequestManager/updateTransactionProperties/changeRequestId/id/name/amount/type/tokensExpr/`
  const changeRequestId = targetObjectId
  const requestParameters = { ...parameters, changeRequestId, tokensExpr: 'none' }
  return postForm({ url,requestParameters})
}

const removeTransactionList = (targetObjectId, parameters) => {
  const url = `${PREFIX}changeRequestManager/removeTransactionList/changeRequestId/transactionIds/tokensExpr/`
  const requestParameters = { ...parameters, changeRequestId: targetObjectId, tokensExpr: 'none' }
  return postForm({ url,requestParameters})
}



const addNameChangeEvent = (targetObjectId, parameters) => {
  const url = `${PREFIX}changeRequestManager/addNameChangeEvent/changeRequestId/name/accountId/tokensExpr/`
  const changeRequestId = targetObjectId
  const requestParameters = { ...parameters, changeRequestId, tokensExpr: 'none' }
  return postForm({ url,requestParameters})
}

const updateNameChangeEvent = (targetObjectId, parameters) => {
  const url = `${PREFIX}changeRequestManager/updateNameChangeEventProperties/changeRequestId/id/name/tokensExpr/`
  const changeRequestId = targetObjectId
  const requestParameters = { ...parameters, changeRequestId, tokensExpr: 'none' }
  return postForm({ url,requestParameters})
}

const removeNameChangeEventList = (targetObjectId, parameters) => {
  const url = `${PREFIX}changeRequestManager/removeNameChangeEventList/changeRequestId/nameChangeEventIds/tokensExpr/`
  const requestParameters = { ...parameters, changeRequestId: targetObjectId, tokensExpr: 'none' }
  return postForm({ url,requestParameters})
}



const addAccountChange = (targetObjectId, parameters) => {
  const url = `${PREFIX}changeRequestManager/addAccountChange/changeRequestId/name/accountId/previousBalance/type/amount/currentBalance/tokensExpr/`
  const changeRequestId = targetObjectId
  const requestParameters = { ...parameters, changeRequestId, tokensExpr: 'none' }
  return postForm({ url,requestParameters})
}

const updateAccountChange = (targetObjectId, parameters) => {
  const url = `${PREFIX}changeRequestManager/updateAccountChangeProperties/changeRequestId/id/name/previousBalance/type/amount/currentBalance/tokensExpr/`
  const changeRequestId = targetObjectId
  const requestParameters = { ...parameters, changeRequestId, tokensExpr: 'none' }
  return postForm({ url,requestParameters})
}

const removeAccountChangeList = (targetObjectId, parameters) => {
  const url = `${PREFIX}changeRequestManager/removeAccountChangeList/changeRequestId/accountChangeIds/tokensExpr/`
  const requestParameters = { ...parameters, changeRequestId: targetObjectId, tokensExpr: 'none' }
  return postForm({ url,requestParameters})
}


const ChangeRequestService = { view,
  load,
  addTransaction,
  addNameChangeEvent,
  addAccountChange,
  updateTransaction,
  updateNameChangeEvent,
  updateAccountChange,
  removeTransactionList,
  removeNameChangeEventList,
  removeAccountChangeList,
  requestCandidatePlatform,
  transferToAnotherPlatform }
export default ChangeRequestService

