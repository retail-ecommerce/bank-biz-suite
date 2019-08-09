import { get,postForm,PREFIX,joinParameters,joinPostParameters } from '../../axios/tools'


const view = (targetObjectId) => {
  return get({
    url: `${PREFIX}transactionManager/view/${targetObjectId}/`,
  })
}



const load = (targetObjectId, parameters) => {
  const parametersExpr = joinParameters(parameters)
  return get({
    url: `${PREFIX}transactionManager/loadTransaction/${targetObjectId}/${parametersExpr}/`,
  })
}



const requestCandidateFromAccount = (ownerClass, id, filterKey, pageNo) => {
 
  const url = `${PREFIX}transactionManager/requestCandidateFromAccount/ownerClass/id/filterKey/pageNo/`
  const requestParameters = {id, ownerClass,filterKey, pageNo}
  return postForm({url,requestParameters})
}	

const transferToAnotherFromAccount = (id, parameters) => {
  //const parametersExpr = joinParameters(parameters)
  const url = `${PREFIX}transactionManager/transferToAnotherFromAccount/id/anotherFromAccountId/`
  const requestParameters = {id, ...parameters}
  return postForm({url,requestParameters})
}



const requestCandidateToAccount = (ownerClass, id, filterKey, pageNo) => {
 
  const url = `${PREFIX}transactionManager/requestCandidateToAccount/ownerClass/id/filterKey/pageNo/`
  const requestParameters = {id, ownerClass,filterKey, pageNo}
  return postForm({url,requestParameters})
}	

const transferToAnotherToAccount = (id, parameters) => {
  //const parametersExpr = joinParameters(parameters)
  const url = `${PREFIX}transactionManager/transferToAnotherToAccount/id/anotherToAccountId/`
  const requestParameters = {id, ...parameters}
  return postForm({url,requestParameters})
}



const requestCandidateChangeRequest = (ownerClass, id, filterKey, pageNo) => {
 
  const url = `${PREFIX}transactionManager/requestCandidateChangeRequest/ownerClass/id/filterKey/pageNo/`
  const requestParameters = {id, ownerClass,filterKey, pageNo}
  return postForm({url,requestParameters})
}	

const transferToAnotherChangeRequest = (id, parameters) => {
  //const parametersExpr = joinParameters(parameters)
  const url = `${PREFIX}transactionManager/transferToAnotherChangeRequest/id/anotherChangeRequestId/`
  const requestParameters = {id, ...parameters}
  return postForm({url,requestParameters})
}






const TransactionService = { view,
  load,
  requestCandidateFromAccount,
  requestCandidateToAccount,
  requestCandidateChangeRequest,
  transferToAnotherFromAccount,
  transferToAnotherToAccount,
  transferToAnotherChangeRequest }
export default TransactionService

