

import React, { Component } from 'react'
import FontAwesome from 'react-fontawesome';
import { connect } from 'dva'
import moment from 'moment'
import BooleanOption from '../../components/BooleanOption';
import { Row, Col, Icon, Card, Tabs, Table, Radio, DatePicker, Tooltip, Menu, Dropdown,Badge, Switch,Select,Form,AutoComplete,Modal } from 'antd'
import { Link, Route, Redirect} from 'dva/router'
import numeral from 'numeral'
import {
  ChartCard, yuan, MiniArea, MiniBar, MiniProgress, Field, Bar, Pie, TimelineChart,
} from '../../components/Charts'
import Trend from '../../components/Trend'
import NumberInfo from '../../components/NumberInfo'
import { getTimeDistance } from '../../utils/utils'
import PageHeaderLayout from '../../layouts/PageHeaderLayout'
import styles from './Transaction.dashboard.less'
import DescriptionList from '../../components/DescriptionList';
import ImagePreview from '../../components/ImagePreview';
import GlobalComponents from '../../custcomponents';
import DashboardTool from '../../common/Dashboard.tool'
import appLocaleName from '../../common/Locale.tool'

const {aggregateDataset,calcKey, defaultHideCloseTrans,
  defaultImageListOf,defaultSettingListOf,defaultBuildTransferModal,
  defaultExecuteTrans,defaultHandleTransferSearch,defaultShowTransferModel,
  defaultRenderExtraHeader,
  defaultSubListsOf,defaultRenderAnalytics,
  defaultRenderExtraFooter,renderForTimeLine,renderForNumbers,
  defaultQuickFunctions, defaultRenderSubjectList,
}= DashboardTool



const { Description } = DescriptionList;
const { TabPane } = Tabs
const { RangePicker } = DatePicker
const { Option } = Select


const imageList =(transaction)=>{return [
	 ]}

const internalImageListOf = (transaction) =>defaultImageListOf(transaction,imageList)

const optionList =(transaction)=>{return [ 
	]}

const buildTransferModal = defaultBuildTransferModal
const showTransferModel = defaultShowTransferModel
const internalRenderSubjectList = defaultRenderSubjectList
const internalSettingListOf = (transaction) =>defaultSettingListOf(transaction, optionList)
const internalLargeTextOf = (transaction) =>{

	return null
	

}


const internalRenderExtraHeader = defaultRenderExtraHeader

const internalRenderExtraFooter = defaultRenderExtraFooter
const internalSubListsOf = defaultSubListsOf


const internalRenderTitle = (cardsData,targetComponent) =>{
  
  
  const linkComp=cardsData.returnURL?<Link to={cardsData.returnURL}> <FontAwesome name="arrow-left"  /> </Link>:null
  return (<div>{linkComp}{cardsData.cardsName}: {cardsData.displayName}</div>)

}


const internalSummaryOf = (transaction,targetComponent) =>{
	
	
	const {TransactionService} = GlobalComponents
	const userContext = null
	return (
	<DescriptionList className={styles.headerList} size="small" col="4">
<Description term="ID">{transaction.id}</Description> 
<Description term="名称">{transaction.name}</Description> 
<Description term="从账户">{transaction.fromAccount==null?appLocaleName(userContext,"NotAssigned"):`${transaction.fromAccount.displayName}(${transaction.fromAccount.id})`}
 <Icon type="swap" onClick={()=>
  showTransferModel(targetComponent,"从账户","account",TransactionService.requestCandidateFromAccount,
	      TransactionService.transferToAnotherFromAccount,"anotherFromAccountId",transaction.fromAccount?transaction.fromAccount.id:"")} 
  style={{fontSize: 20,color:"red"}} />
</Description>
<Description term="承担责任">{transaction.toAccount==null?appLocaleName(userContext,"NotAssigned"):`${transaction.toAccount.displayName}(${transaction.toAccount.id})`}
 <Icon type="swap" onClick={()=>
  showTransferModel(targetComponent,"承担责任","account",TransactionService.requestCandidateToAccount,
	      TransactionService.transferToAnotherToAccount,"anotherToAccountId",transaction.toAccount?transaction.toAccount.id:"")} 
  style={{fontSize: 20,color:"red"}} />
</Description>
<Description term="金额">{transaction.amount}</Description> 
<Description term="类型">{transaction.type}</Description> 
<Description term="变更请求">{transaction.changeRequest==null?appLocaleName(userContext,"NotAssigned"):`${transaction.changeRequest.displayName}(${transaction.changeRequest.id})`}
 <Icon type="swap" onClick={()=>
  showTransferModel(targetComponent,"变更请求","changeRequest",TransactionService.requestCandidateChangeRequest,
	      TransactionService.transferToAnotherChangeRequest,"anotherChangeRequestId",transaction.changeRequest?transaction.changeRequest.id:"")} 
  style={{fontSize: 20,color:"red"}} />
</Description>
	
        {buildTransferModal(transaction,targetComponent)}
      </DescriptionList>
	)

}

const internalQuickFunctions = defaultQuickFunctions

class TransactionDashboard extends Component {

 state = {
    transferModalVisiable: false,
    candidateReferenceList: {},
    candidateServiceName:"",
    candidateObjectType:"city",
    targetLocalName:"",
    transferServiceName:"",
    currentValue:"",
    transferTargetParameterName:"",  
    defaultType: 'transaction'


  }
  componentDidMount() {

  }
  

  render() {
    // eslint-disable-next-line max-len
    const { id,displayName,  } = this.props.transaction
    if(!this.props.transaction.class){
      return null
    }
    const returnURL = this.props.returnURL
    
    const cardsData = {cardsName:"事务",cardsFor: "transaction",
    	cardsSource: this.props.transaction,returnURL,displayName,
  		subItems: [
    
      	],
  	};
    
    const renderExtraHeader = this.props.renderExtraHeader || internalRenderExtraHeader
    const settingListOf = this.props.settingListOf || internalSettingListOf
    const imageListOf = this.props.imageListOf || internalImageListOf
    const subListsOf = this.props.subListsOf || internalSubListsOf
    const largeTextOf = this.props.largeTextOf ||internalLargeTextOf
    const summaryOf = this.props.summaryOf || internalSummaryOf
    const renderTitle = this.props.renderTitle || internalRenderTitle
    const renderExtraFooter = this.props.renderExtraFooter || internalRenderExtraFooter
    const renderAnalytics = this.props.renderAnalytics || defaultRenderAnalytics
    const quickFunctions = this.props.quickFunctions || internalQuickFunctions
    const renderSubjectList = this.props.renderSubjectList || internalRenderSubjectList
    
    return (

      <PageHeaderLayout
        title={renderTitle(cardsData,this)}
        content={summaryOf(cardsData.cardsSource,this)}
        wrapperClassName={styles.advancedForm}
      >
       
        {renderExtraHeader(cardsData.cardsSource)}
        {imageListOf(cardsData.cardsSource)}  
        {quickFunctions(cardsData)} 
        {renderAnalytics(cardsData.cardsSource)}
        {settingListOf(cardsData.cardsSource)}
        {renderSubjectList(cardsData)}       
        {largeTextOf(cardsData.cardsSource)}
        {renderExtraFooter(cardsData.cardsSource)}
  		
      </PageHeaderLayout>
    
    )
  }
}

export default connect(state => ({
  transaction: state._transaction,
  returnURL: state.breadcrumb.returnURL,
  
}))(Form.create()(TransactionDashboard))

