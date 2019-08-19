

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
import styles from './NameChangeEvent.profile.less'
import DescriptionList from '../../components/DescriptionList';

import GlobalComponents from '../../custcomponents';
import PermissionSetting from '../../permission/PermissionSetting'
import appLocaleName from '../../common/Locale.tool'
const { Description } = DescriptionList;
const {defaultRenderExtraHeader}= DashboardTool


/////////////////////////////////////////////////////////////////////////////////////////////////////////////////


const internalSummaryOf = (nameChangeEvent,targetComponent) =>{
    const userContext = null
	return (
	<DescriptionList className={styles.headerList} size="small" col="4">
<Description term="ID">{nameChangeEvent.id}</Description> 
<Description term="名称">{nameChangeEvent.name}</Description> 
	
      </DescriptionList>
	)
}


const renderPermissionSetting = nameChangeEvent => {
  const {NameChangeEventBase} = GlobalComponents
  return <PermissionSetting targetObject={nameChangeEvent}  targetObjectMeta={NameChangeEventBase}/>
}

const internalRenderExtraHeader = defaultRenderExtraHeader

class NameChangeEventPermission extends Component {


  componentDidMount() {

  }
  

  render() {
    // eslint-disable-next-line max-len
    const  nameChangeEvent = this.props.nameChangeEvent;
    const { id,displayName,  } = nameChangeEvent
    const cardsData = {cardsName:"名字更改事件",cardsFor: "nameChangeEvent",cardsSource: nameChangeEvent,
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
  nameChangeEvent: state._nameChangeEvent,
}))(Form.create()(NameChangeEventPermission))

