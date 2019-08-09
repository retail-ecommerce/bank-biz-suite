import { get,postForm,PREFIX,joinParameters,joinPostParameters } from '../../axios/tools'


const view = (targetObjectId) => {
  return get({
    url: `${PREFIX}accountChangeManager/view/${targetObjectId}/`,
  })
}



const load = (targetObjectId, parameters) => {
  const parametersExpr = joinParameters(parameters)
  return get({
    url: `${PREFIX}accountChangeManager/loadAccountChange/${targetObjectId}/${parametersExpr}/`,
  })
}



const requestCandidateAccount = (ownerClass, id, filterKey, pageNo) => {
 
  const url = `${PREFIX}accountChangeManager/requestCandidateAccount/ownerClass/id/filterKey/pageNo/`
  const requestParameters = {id, ownerClass,filterKey, pageNo}
  return postForm({url,requestParameters})
}	

const transferToAnotherAccount = (id, parameters) => {
  //const parametersExpr = joinParameters(parameters)
  const url = `${PREFIX}accountChangeManager/transferToAnotherAccount/id/anotherAccountId/`
  const requestParameters = {id, ...parameters}
  return postForm({url,requestParameters})
}



const requestCandidateChangeRequest = (ownerClass, id, filterKey, pageNo) => {
 
  const url = `${PREFIX}accountChangeManager/requestCandidateChangeRequest/ownerClass/id/filterKey/pageNo/`
  const requestParameters = {id, ownerClass,filterKey, pageNo}
  return postForm({url,requestParameters})
}	

const transferToAnotherChangeRequest = (id, parameters) => {
  //const parametersExpr = joinParameters(parameters)
  const url = `${PREFIX}accountChangeManager/transferToAnotherChangeRequest/id/anotherChangeRequestId/`
  const requestParameters = {id, ...parameters}
  return postForm({url,requestParameters})
}






const AccountChangeService = { view,
  load,
  requestCandidateAccount,
  requestCandidateChangeRequest,
  transferToAnotherAccount,
  transferToAnotherChangeRequest }
export default AccountChangeService

