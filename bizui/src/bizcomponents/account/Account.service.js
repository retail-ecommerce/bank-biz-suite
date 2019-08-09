import { get,postForm,PREFIX,joinParameters,joinPostParameters } from '../../axios/tools'


const view = (targetObjectId) => {
  return get({
    url: `${PREFIX}accountManager/view/${targetObjectId}/`,
  })
}



const load = (targetObjectId, parameters) => {
  const parametersExpr = joinParameters(parameters)
  return get({
    url: `${PREFIX}accountManager/loadAccount/${targetObjectId}/${parametersExpr}/`,
  })
}



const requestCandidatePlatform = (ownerClass, id, filterKey, pageNo) => {
 
  const url = `${PREFIX}accountManager/requestCandidatePlatform/ownerClass/id/filterKey/pageNo/`
  const requestParameters = {id, ownerClass,filterKey, pageNo}
  return postForm({url,requestParameters})
}	

const transferToAnotherPlatform = (id, parameters) => {
  //const parametersExpr = joinParameters(parameters)
  const url = `${PREFIX}accountManager/transferToAnotherPlatform/id/anotherPlatformId/`
  const requestParameters = {id, ...parameters}
  return postForm({url,requestParameters})
}







const addTransactionAsFromAccount = (targetObjectId, parameters) => {
  const url = `${PREFIX}accountManager/addTransactionAsFromAccount/accountId/name/amount/type/changeRequestId/tokensExpr/`
  const accountId = targetObjectId
  const requestParameters = { ...parameters, accountId, tokensExpr: 'none' }
  return postForm({ url,requestParameters})
}

const updateTransactionAsFromAccount = (targetObjectId, parameters) => {
  const url = `${PREFIX}accountManager/updateTransactionAsFromAccountProperties/accountId/id/name/amount/type/tokensExpr/`
  const accountId = targetObjectId
  const requestParameters = { ...parameters, accountId, tokensExpr: 'none' }
  return postForm({ url,requestParameters})
}

const removeTransactionListAsFromAccount = (targetObjectId, parameters) => {
  const url = `${PREFIX}accountManager/removeTransactionListAsFromAccount/accountId/transactionIds/tokensExpr/`
  const requestParameters = { ...parameters, accountId: targetObjectId, tokensExpr: 'none' }
  return postForm({ url,requestParameters})
}



const addTransactionAsToAccount = (targetObjectId, parameters) => {
  const url = `${PREFIX}accountManager/addTransactionAsToAccount/accountId/name/amount/type/changeRequestId/tokensExpr/`
  const accountId = targetObjectId
  const requestParameters = { ...parameters, accountId, tokensExpr: 'none' }
  return postForm({ url,requestParameters})
}

const updateTransactionAsToAccount = (targetObjectId, parameters) => {
  const url = `${PREFIX}accountManager/updateTransactionAsToAccountProperties/accountId/id/name/amount/type/tokensExpr/`
  const accountId = targetObjectId
  const requestParameters = { ...parameters, accountId, tokensExpr: 'none' }
  return postForm({ url,requestParameters})
}

const removeTransactionListAsToAccount = (targetObjectId, parameters) => {
  const url = `${PREFIX}accountManager/removeTransactionListAsToAccount/accountId/transactionIds/tokensExpr/`
  const requestParameters = { ...parameters, accountId: targetObjectId, tokensExpr: 'none' }
  return postForm({ url,requestParameters})
}



const addAccountChange = (targetObjectId, parameters) => {
  const url = `${PREFIX}accountManager/addAccountChange/accountId/name/previousBalance/type/amount/currentBalance/changeRequestId/tokensExpr/`
  const accountId = targetObjectId
  const requestParameters = { ...parameters, accountId, tokensExpr: 'none' }
  return postForm({ url,requestParameters})
}

const updateAccountChange = (targetObjectId, parameters) => {
  const url = `${PREFIX}accountManager/updateAccountChangeProperties/accountId/id/name/previousBalance/type/amount/currentBalance/tokensExpr/`
  const accountId = targetObjectId
  const requestParameters = { ...parameters, accountId, tokensExpr: 'none' }
  return postForm({ url,requestParameters})
}

const removeAccountChangeList = (targetObjectId, parameters) => {
  const url = `${PREFIX}accountManager/removeAccountChangeList/accountId/accountChangeIds/tokensExpr/`
  const requestParameters = { ...parameters, accountId: targetObjectId, tokensExpr: 'none' }
  return postForm({ url,requestParameters})
}


const AccountService = { view,
  load,
  addTransactionAsFromAccount,
  addTransactionAsToAccount,
  addAccountChange,
  updateTransactionAsFromAccount,
  updateTransactionAsToAccount,
  updateAccountChange,
  removeTransactionListAsFromAccount,
  removeTransactionListAsToAccount,
  removeAccountChangeList,
  requestCandidatePlatform,
  transferToAnotherPlatform }
export default AccountService

