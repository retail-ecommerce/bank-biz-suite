import React from 'react'
import { Icon,Divider } from 'antd'

import { Link } from 'dva/router'
import moment from 'moment'
import ImagePreview from '../../components/ImagePreview'
import appLocaleName from '../../common/Locale.tool'
import BaseTool from '../../common/Base.tool'
import GlobalComponents from '../../custcomponents'
import DescriptionList from '../../components/DescriptionList'
const { Description } = DescriptionList
const {
	defaultRenderReferenceCell,
	defaultRenderBooleanCell,
	defaultRenderMoneyCell,
	defaultRenderDateTimeCell,
	defaultRenderImageCell,
	defaultRenderDateCell,
	defaultRenderIdentifier,
	defaultRenderTextCell,
} = BaseTool

const renderTextCell=defaultRenderTextCell
const renderIdentifier=defaultRenderIdentifier
const renderDateCell=defaultRenderDateCell
const renderDateTimeCell=defaultRenderDateTimeCell
const renderImageCell=defaultRenderImageCell
const renderMoneyCell=defaultRenderMoneyCell
const renderBooleanCell=defaultRenderBooleanCell
const renderReferenceCell=defaultRenderReferenceCell


const menuData = {menuName:"名字更改事件", menuFor: "nameChangeEvent",
  		subItems: [
  
  		],
}

const fieldLabels = {
  id: 'ID',
  name: '名称',
  account: '账户',
  changeRequest: '变更请求',

}

const displayColumns = [
  { title: fieldLabels.id, debugtype: 'string', dataIndex: 'id', width: '8', render: (text, record)=>renderTextCell(text,record,'nameChangeEvent') , sorter: true },
  { title: fieldLabels.name, debugtype: 'string', dataIndex: 'name', width: '12',render: (text, record)=>renderTextCell(text,record)},
  { title: fieldLabels.account, dataIndex: 'account', render: (text, record) => renderReferenceCell(text, record), sorter:true},
  { title: fieldLabels.changeRequest, dataIndex: 'changeRequest', render: (text, record) => renderReferenceCell(text, record), sorter:true},

]
// refernce to https://ant.design/components/list-cn/
const renderItemOfList=(nameChangeEvent,targetComponent)=>{

  const userContext = null
  return (
    <div key={nameChangeEvent.id}>
	
      <DescriptionList  key={nameChangeEvent.id} size="small" col="4">
        <Description term="ID">{nameChangeEvent.id}</Description> 
        <Description term="名称">{nameChangeEvent.name}</Description> 
        <Description term="账户">{nameChangeEvent.account==null?appLocaleName(userContext,"NotAssigned"):`${nameChangeEvent.account.displayName}(${nameChangeEvent.account.id})`}
        </Description>
        <Description term="变更请求">{nameChangeEvent.changeRequest==null?appLocaleName(userContext,"NotAssigned"):`${nameChangeEvent.changeRequest.displayName}(${nameChangeEvent.changeRequest.id})`}
        </Description>
	
        
      </DescriptionList>
      <Divider style={{ height: '2px' }} />
    </div>
	)

}
	



const NameChangeEventBase={menuData,displayColumns,fieldLabels,renderItemOfList}
export default NameChangeEventBase



