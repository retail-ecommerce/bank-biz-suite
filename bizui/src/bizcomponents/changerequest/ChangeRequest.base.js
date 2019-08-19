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


const menuData = {menuName:"变更请求", menuFor: "changeRequest",
  		subItems: [
  {name: 'transactionList', displayName:'事务', icon:'500px',readPermission: false,createPermission: false,deletePermission: false,updatePermission: false,executionPermission: false, viewGroup: '__no_group'},
  {name: 'nameChangeEventList', displayName:'名字更改事件', icon:'exchange-alt',readPermission: false,createPermission: false,deletePermission: false,updatePermission: false,executionPermission: false, viewGroup: '__no_group'},
  {name: 'accountChangeList', displayName:'账户变更', icon:'exchange-alt',readPermission: false,createPermission: false,deletePermission: false,updatePermission: false,executionPermission: false, viewGroup: '__no_group'},
  
  		],
}

const fieldLabels = {
  id: 'ID',
  name: '名称',
  createTime: '创建时间',
  platform: '平台',

}

const displayColumns = [
  { title: fieldLabels.id, debugtype: 'string', dataIndex: 'id', width: '8', render: (text, record)=>renderTextCell(text,record,'changeRequest') , sorter: true },
  { title: fieldLabels.name, debugtype: 'string', dataIndex: 'name', width: '6',render: (text, record)=>renderTextCell(text,record)},
  { title: fieldLabels.createTime, dataIndex: 'createTime', render: (text, record) =>renderDateTimeCell(text,record), sorter: true},
  { title: fieldLabels.platform, dataIndex: 'platform', render: (text, record) => renderReferenceCell(text, record), sorter:true},

]
// refernce to https://ant.design/components/list-cn/
const renderItemOfList=(changeRequest,targetComponent)=>{

  const userContext = null
  return (
    <div key={changeRequest.id}>
	
      <DescriptionList  key={changeRequest.id} size="small" col="4">
        <Description term="ID">{changeRequest.id}</Description> 
        <Description term="名称">{changeRequest.name}</Description> 
        <Description term="创建时间">{ moment(changeRequest.createTime).format('YYYY-MM-DD')}</Description> 
	
        
      </DescriptionList>
      <Divider style={{ height: '2px' }} />
    </div>
	)

}
	



const ChangeRequestBase={menuData,displayColumns,fieldLabels,renderItemOfList}
export default ChangeRequestBase



