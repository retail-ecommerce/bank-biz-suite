import React from 'react'
import PropTypes from 'prop-types'
import {
  Layout,
  Menu,
  Icon,
  Avatar,
  Dropdown,
  Tag,
  message,
  Spin,
  Breadcrumb,
  AutoComplete,
  Input,Button
} from 'antd'
import DocumentTitle from 'react-document-title'
import { connect } from 'dva'
import { Link, Route, Redirect, Switch } from 'dva/router'
import moment from 'moment'
import groupBy from 'lodash/groupBy'
import { ContainerQuery } from 'react-container-query'
import classNames from 'classnames'
import styles from './Platform.app.less'
import {sessionObject} from '../../utils/utils'

import HeaderSearch from '../../components/HeaderSearch';
import NoticeIcon from '../../components/NoticeIcon';
import GlobalFooter from '../../components/GlobalFooter';


import GlobalComponents from '../../custcomponents';

import PermissionSettingService from '../../permission/PermissionSetting.service'
import appLocaleName from '../../common/Locale.tool'
import BizAppTool from '../../common/BizApp.tool'

const { Header, Sider, Content } = Layout
const { SubMenu } = Menu
const {
  defaultFilteredNoGroupMenuItems,
  defaultFilteredMenuItemsGroup,
  defaultRenderMenuItem,

} = BizAppTool


const filteredNoGroupMenuItems = defaultFilteredNoGroupMenuItems
const filteredMenuItemsGroup = defaultFilteredMenuItemsGroup
const renderMenuItem=defaultRenderMenuItem



const query = {
  'screen-xs': {
    maxWidth: 575,
  },
  'screen-sm': {
    minWidth: 576,
    maxWidth: 767,
  },
  'screen-md': {
    minWidth: 768,
    maxWidth: 991,
  },
  'screen-lg': {
    minWidth: 992,
    maxWidth: 1199,
  },
  'screen-xl': {
    minWidth: 1200,
  },
}




class PlatformBizApp extends React.PureComponent {
  constructor(props) {
    super(props)
     this.state = {
      openKeys: this.getDefaultCollapsedSubMenus(props),
    }
  }

  componentDidMount() {}
  componentWillUnmount() {
    clearTimeout(this.resizeTimeout)
  }
  onCollapse = (collapsed) => {
    this.props.dispatch({
      type: 'global/changeLayoutCollapsed',
      payload: collapsed,
    })
  }

  getDefaultCollapsedSubMenus = (props) => {
    const currentMenuSelectedKeys = [...this.getCurrentMenuSelectedKeys(props)]
    currentMenuSelectedKeys.splice(-1, 1)
    if (currentMenuSelectedKeys.length === 0) {
      return ['/platform/']
    }
    return currentMenuSelectedKeys
  }
  getCurrentMenuSelectedKeys = (props) => {
    const { location: { pathname } } = props || this.props
    const keys = pathname.split('/').slice(1)
    if (keys.length === 1 && keys[0] === '') {
      return [this.menus[0].key]
    }
    return keys
  }
  
  getNavMenuItems = (targetObject) => {
  

    const menuData = sessionObject('menuData')
    const targetApp = sessionObject('targetApp')
	const {objectId}=targetApp;
  	const userContext = null
    return (
      
		  <Menu
             theme="dark"
             mode="inline"
            
             
             onOpenChange={this.handleOpenChange}
            
             defaultOpenKeys={['firstOne']}
             style={{ margin: '16px 0', width: '100%' }}
           >
           

             <Menu.Item key="dashboard">
               <Link to={`/platform/${this.props.platform.id}/dashboard`}><Icon type="dashboard" /><span>{appLocaleName(userContext,"Dashboard")}</span></Link>
             </Menu.Item>
           
        {filteredNoGroupMenuItems(targetObject,this).map((item)=>(renderMenuItem(item)))}  
        {filteredMenuItemsGroup(targetObject,this).map((groupedMenuItem,index)=>{
          return(
    <SubMenu key={`vg${index}`} title={<span><Icon type="folder" /><span>{`${groupedMenuItem.viewGroup}`}</span></span>} >
      {groupedMenuItem.subItems.map((item)=>(renderMenuItem(item)))}  
    </SubMenu>

        )}
        )}

       		<SubMenu key="sub4" title={<span><Icon type="setting" /><span>{appLocaleName(userContext,"Setting")}</span></span>} >
       			<Menu.Item key="profile">
               		<Link to={`/platform/${this.props.platform.id}/permission`}><Icon type="safety-certificate" /><span>{appLocaleName(userContext,"Permission")}</span></Link>
             	</Menu.Item>
             	<Menu.Item key="permission">
               		<Link to={`/platform/${this.props.platform.id}/profile`}><Icon type="cluster" /><span>{appLocaleName(userContext,"Profile")}</span></Link>
             	</Menu.Item> 
      
        	</SubMenu>
        
           </Menu>
    )
  }
  



  getChangeRequestSearch = () => {
    const {ChangeRequestSearch} = GlobalComponents;
    const userContext = null
    return connect(state => ({
      rule: state.rule,
      name: "变更请求",
      role: "changeRequest",
      data: state._platform.changeRequestList,
      metaInfo: state._platform.changeRequestListMetaInfo,
      count: state._platform.changeRequestCount,
      currentPage: state._platform.changeRequestCurrentPageNumber,
      searchFormParameters: state._platform.changeRequestSearchFormParameters,
      searchParameters: {...state._platform.searchParameters},
      expandForm: state._platform.expandForm,
      loading: state._platform.loading,
      partialList: state._platform.partialList,
      owner: { type: '_platform', id: state._platform.id, 
      referenceName: 'platform', 
      listName: 'changeRequestList', ref:state._platform, 
      listDisplayName: appLocaleName(userContext,"List") }, // this is for model namespace and
    }))(ChangeRequestSearch)
  }
  getChangeRequestCreateForm = () => {
   	const {ChangeRequestCreateForm} = GlobalComponents;
   	const userContext = null
    return connect(state => ({
      rule: state.rule,
      role: "changeRequest",
      data: state._platform.changeRequestList,
      metaInfo: state._platform.changeRequestListMetaInfo,
      count: state._platform.changeRequestCount,
      currentPage: state._platform.changeRequestCurrentPageNumber,
      searchFormParameters: state._platform.changeRequestSearchFormParameters,
      loading: state._platform.loading,
      owner: { type: '_platform', id: state._platform.id, referenceName: 'platform', listName: 'changeRequestList', ref:state._platform, listDisplayName: appLocaleName(userContext,"List")}, // this is for model namespace and
    }))(ChangeRequestCreateForm)
  }
  
  getChangeRequestUpdateForm = () => {
    const userContext = null
  	const {ChangeRequestUpdateForm} = GlobalComponents;
    return connect(state => ({
      selectedRows: state._platform.selectedRows,
      role: "changeRequest",
      currentUpdateIndex: state._platform.currentUpdateIndex,
      owner: { type: '_platform', id: state._platform.id, listName: 'changeRequestList', ref:state._platform, listDisplayName: appLocaleName(userContext,"List") }, // this is for model namespace and
    }))(ChangeRequestUpdateForm)
  }

  getAccountSearch = () => {
    const {AccountSearch} = GlobalComponents;
    const userContext = null
    return connect(state => ({
      rule: state.rule,
      name: "账户",
      role: "account",
      data: state._platform.accountList,
      metaInfo: state._platform.accountListMetaInfo,
      count: state._platform.accountCount,
      currentPage: state._platform.accountCurrentPageNumber,
      searchFormParameters: state._platform.accountSearchFormParameters,
      searchParameters: {...state._platform.searchParameters},
      expandForm: state._platform.expandForm,
      loading: state._platform.loading,
      partialList: state._platform.partialList,
      owner: { type: '_platform', id: state._platform.id, 
      referenceName: 'platform', 
      listName: 'accountList', ref:state._platform, 
      listDisplayName: appLocaleName(userContext,"List") }, // this is for model namespace and
    }))(AccountSearch)
  }
  getAccountCreateForm = () => {
   	const {AccountCreateForm} = GlobalComponents;
   	const userContext = null
    return connect(state => ({
      rule: state.rule,
      role: "account",
      data: state._platform.accountList,
      metaInfo: state._platform.accountListMetaInfo,
      count: state._platform.accountCount,
      currentPage: state._platform.accountCurrentPageNumber,
      searchFormParameters: state._platform.accountSearchFormParameters,
      loading: state._platform.loading,
      owner: { type: '_platform', id: state._platform.id, referenceName: 'platform', listName: 'accountList', ref:state._platform, listDisplayName: appLocaleName(userContext,"List")}, // this is for model namespace and
    }))(AccountCreateForm)
  }
  
  getAccountUpdateForm = () => {
    const userContext = null
  	const {AccountUpdateForm} = GlobalComponents;
    return connect(state => ({
      selectedRows: state._platform.selectedRows,
      role: "account",
      currentUpdateIndex: state._platform.currentUpdateIndex,
      owner: { type: '_platform', id: state._platform.id, listName: 'accountList', ref:state._platform, listDisplayName: appLocaleName(userContext,"List") }, // this is for model namespace and
    }))(AccountUpdateForm)
  }


  
  buildRouters = () =>{
  	const {PlatformDashboard} = GlobalComponents
  	const {PlatformPermission} = GlobalComponents
  	const {PlatformProfile} = GlobalComponents
  	
  	
  	const routers=[
  	{path:"/platform/:id/dashboard", component: PlatformDashboard},
  	{path:"/platform/:id/profile", component: PlatformProfile},
  	{path:"/platform/:id/permission", component: PlatformPermission},
  	
  	
  	
  	{path:"/platform/:id/list/changeRequestList", component: this.getChangeRequestSearch()},
  	{path:"/platform/:id/list/changeRequestCreateForm", component: this.getChangeRequestCreateForm()},
  	{path:"/platform/:id/list/changeRequestUpdateForm", component: this.getChangeRequestUpdateForm()},
   	
  	{path:"/platform/:id/list/accountList", component: this.getAccountSearch()},
  	{path:"/platform/:id/list/accountCreateForm", component: this.getAccountCreateForm()},
  	{path:"/platform/:id/list/accountUpdateForm", component: this.getAccountUpdateForm()},
     	
  	
  	]
  	
  	const {extraRoutesFunc} = this.props;
	const extraRoutes = extraRoutesFunc?extraRoutesFunc():[]
    const finalRoutes = routers.concat(extraRoutes)
    
  	return (<Switch>
             {finalRoutes.map((item)=>(<Route key={item.path} path={item.path} component={item.component} />))}    
  	  	</Switch>)
  	
  
  }
 

  getPageTitle = () => {
    // const { location } = this.props
    // const { pathname } = location
    const title = '银行模拟平台'
    return title
  }
 
  handleOpenChange = (openKeys) => {
    const latestOpenKey = openKeys.find(key => this.state.openKeys.indexOf(key) === -1)
    this.setState({
      openKeys: latestOpenKey ? [latestOpenKey] : [],
    })
  }
   toggle = () => {
     const { collapsed } = this.props
     this.props.dispatch({
       type: 'global/changeLayoutCollapsed',
       payload: !collapsed,
     })
   }
    logout = () => {
   
    console.log("log out called")
    this.props.dispatch({ type: 'launcher/signOut' })
  }
   render() {
     // const { collapsed, fetchingNotices,loading } = this.props
     const { collapsed } = this.props
     
  
     const targetApp = sessionObject('targetApp')
     const currentBreadcrumb =targetApp?sessionObject(targetApp.id):[];
     const userContext = null
     const renderBreadcrumbText=(value)=>{
     	if(value==null){
     		return "..."
     	}
     	if(value.length < 10){
     		return value
     	}
     
     	return value.substring(0,10)+"..."
     	
     	
     }
     const menuProps = collapsed ? {} : {
       openKeys: this.state.openKeys,
     }
     const layout = (
     <Layout>
        <Header>
          
          <div className={styles.left}>
          <img
            src="./favicon.png"
            alt="logo"
            onClick={this.toggle}
            className={styles.logo}
          /><Link key={"__home"} to={"/home"} className={styles.breadcrumbLink}><Icon type="home" />&nbsp;{appLocaleName(userContext,"Home")}</Link>
          {currentBreadcrumb.map((item)=>{
            return (<Link  key={item.link} to={`${item.link}`} className={styles.breadcrumbLink}><Icon type="caret-right" />{renderBreadcrumbText(item.name)}</Link>)

          })}
         </div>
          <div className={styles.right}  >
          <Button type="primary"  icon="logout" onClick={()=>this.logout()}>
          {appLocaleName(userContext,"Exit")}</Button>
          </div>
          
        </Header>
       <Layout>
         <Sider
           trigger={null}
           collapsible
           collapsed={collapsed}
           breakpoint="md"
           onCollapse={()=>this.onCollapse(collapsed)}
           collapsedWidth={56}
           className={styles.sider}
         >

		 {this.getNavMenuItems(this.props.platform)}
		 
         </Sider>
         <Layout>
           <Content style={{ margin: '24px 24px 0', height: '100%' }}>
           
           {this.buildRouters()}
 
             
             
           </Content>
          </Layout>
        </Layout>
      </Layout>
     )
     return (
       <DocumentTitle title={this.getPageTitle()}>
         <ContainerQuery query={query}>
           {params => <div className={classNames(params)}>{layout}</div>}
         </ContainerQuery>
       </DocumentTitle>
     )
   }
}

export default connect(state => ({
  collapsed: state.global.collapsed,
  fetchingNotices: state.global.fetchingNotices,
  notices: state.global.notices,
  platform: state._platform,
  ...state,
}))(PlatformBizApp)



