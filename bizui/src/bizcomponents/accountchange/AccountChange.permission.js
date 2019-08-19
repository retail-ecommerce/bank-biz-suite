

import React, { Component } from 'react'
import FontAwesome from 'react-fontawesome';
import { connect } from 'dva'
import moment from 'moment'
import BooleanOption from '../../components/BooleanOption';
import { Row, Col, Icon, Card, Tabs, Table, Radio, DatePicker, Tooltip, Menu, Dropdown,Badge, Switch,Select,Form,AutoComplete,Modal } from 'antd'
import { Link, Route, Redirect} from 'dva/router'
import numeral from 'numeral'

import DashboardTool from '../../common/Dashboard.tool'
import PageHeaderLayout from '../../layouts/PageHeaderLayout'
import styles from './AccountChange.profile.less'
import DescriptionList from '../../components/DescriptionList';

import GlobalComponents from '../../custcomponents';
import PermissionSetting from '../../permission/PermissionSetting'
import appLocaleName from '../../common/Locale.tool'
const { Description } = DescriptionList;
const {defaultRenderExtraHeader}= DashboardTool


/////////////////////////////////////////////////////////////////////////////////////////////////////////////////


const internalSummaryOf = (accountChange,targetComponent) =>{
    const userContext = null
	return (
	<DescriptionList className={styles.headerList} size="small" col="4">
<Description term="ID">{accountChange.id}</Description> 
<Description term="名称">{accountChange.name}</Description> 
<Description term="初期余额">{accountChange.previousBalance}</Description> 
<Description term="类型">{accountChange.type}</Description> 
<Description term="金额">{accountChange.amount}</Description> 
<Description term="当前余额">{accountChange.currentBalance}</Description> 
	
      </DescriptionList>
	)
}


const renderPermissionSetting = accountChange => {
  const {AccountChangeBase} = GlobalComponents
  return <PermissionSetting targetObject={accountChange}  targetObjectMeta={AccountChangeBase}/>
}

const internalRenderExtraHeader = defaultRenderExtraHeader

class AccountChangePermission extends Component {


  componentDidMount() {

  }
  

  render() {
    // eslint-disable-next-line max-len
    const  accountChange = this.props.accountChange;
    const { id,displayName,  } = accountChange
    const cardsData = {cardsName:"账户变更",cardsFor: "accountChange",cardsSource: accountChange,
  		subItems: [
    
      	],
  	};
    const renderExtraHeader = this.props.renderExtraHeader || internalRenderExtraHeader
    const summaryOf = this.props.summaryOf || internalSummaryOf
   
    return (

      <PageHeaderLayout
        title={`${cardsData.cardsName}: ${displayName}`}
        content={summaryOf(cardsData.cardsSource,this)}
        wrapperClassName={styles.advancedForm}
      >
      {renderExtraHeader(cardsData.cardsSource)}
      {renderPermissionSetting(cardsData.cardsSource)}
      
      </PageHeaderLayout>
    )
  }
}

export default connect(state => ({
  accountChange: state._accountChange,
}))(Form.create()(AccountChangePermission))

