package com.doublechain.bank;
import com.terapico.caf.DateTime;
import com.terapico.uccaf.BaseUserContext;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.text.MessageFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

public class BankCheckerManager extends BaseManagerImpl {
	protected BankObjectChecker checkerOf(BankUserContext ctx) {
		return ctx.getChecker();
	}
	private static class AsyncManagerJob extends Thread {
		protected Object me;
		protected Object proxy;
		protected Method method;
		protected Object[] args;
		protected MethodProxy methodProxy;

		public AsyncManagerJob(Object me, Object proxy, Method method, Object[] args, MethodProxy methodProxy) {
			super();
			this.me = me;
			this.proxy = proxy;
			this.method = method;
			this.args = args;
			this.methodProxy = methodProxy;
		}

		@Override
		public void run() {
			try {
				method.setAccessible(true);
				method.invoke(me, args);
			} catch (Throwable e) {
				e.printStackTrace();
			}
		}
	}
	
	public static final Map<String, Object> EO = new HashMap<>();
	protected Object asyncProxy = null;
	protected Object getAsyncProxy() {
		if (asyncProxy != null) {
			return asyncProxy;
		}
		
		Object me = this;
		MethodInterceptor proxy = new MethodInterceptor() {

			@Override
			public Object intercept(Object proxyObj, Method method, Object[] args, MethodProxy methodProxy)
					throws Throwable {
				new AsyncManagerJob(me, proxyObj, method, args, methodProxy).start();
				return null;
			}
		};
		Enhancer enhancer = new Enhancer();
		enhancer.setSuperclass(me.getClass());
		enhancer.setCallback(proxy);
		return asyncProxy = enhancer.create();
	}
	
	protected void cacheVerifyCode(BankUserContext ctx, String mobile, String verifyCode) {
		String cacheKey = "verifyCode:"+mobile;
		ctx.putToCache(cacheKey, verifyCode, BankBaseConstants.DEFAULT_CACHE_TIME_FOR_VCODE);
	}

	protected String getVerifyCodeFromCache(BankUserContext ctx, String mobile) {
		String cacheKey = "verifyCode:"+mobile;
		return (String) ctx.getCachedObject(cacheKey, String.class);
	}
	protected void checkVerifyCode(BankUserContext ctx, String inputVerifyCode, String mobile) throws Exception {
		String cachedVerifyCode = getVerifyCodeFromCache(ctx, mobile);
		if (cachedVerifyCode == null) {
			throw new Exception("请先获取验证码");
		}
		if (!cachedVerifyCode.equals(inputVerifyCode)) {
			throw new Exception("验证码不正确");
		}
	}
	
	public com.doublechain.bank.platform.PlatformManager platformManagerOf(BankUserContext userContext){
		return userContext.getManagerGroup().getPlatformManager();
	}
	public com.doublechain.bank.platform.PlatformDAO platformDaoOf(BankUserContext userContext){
		return userContext.getDAOGroup().getPlatformDAO();
	}
	public com.doublechain.bank.changerequest.ChangeRequestManager changeRequestManagerOf(BankUserContext userContext){
		return userContext.getManagerGroup().getChangeRequestManager();
	}
	public com.doublechain.bank.changerequest.ChangeRequestDAO changeRequestDaoOf(BankUserContext userContext){
		return userContext.getDAOGroup().getChangeRequestDAO();
	}
	public com.doublechain.bank.transaction.TransactionManager transactionManagerOf(BankUserContext userContext){
		return userContext.getManagerGroup().getTransactionManager();
	}
	public com.doublechain.bank.transaction.TransactionDAO transactionDaoOf(BankUserContext userContext){
		return userContext.getDAOGroup().getTransactionDAO();
	}
	public com.doublechain.bank.namechangeevent.NameChangeEventManager nameChangeEventManagerOf(BankUserContext userContext){
		return userContext.getManagerGroup().getNameChangeEventManager();
	}
	public com.doublechain.bank.namechangeevent.NameChangeEventDAO nameChangeEventDaoOf(BankUserContext userContext){
		return userContext.getDAOGroup().getNameChangeEventDAO();
	}
	public com.doublechain.bank.account.AccountManager accountManagerOf(BankUserContext userContext){
		return userContext.getManagerGroup().getAccountManager();
	}
	public com.doublechain.bank.account.AccountDAO accountDaoOf(BankUserContext userContext){
		return userContext.getDAOGroup().getAccountDAO();
	}
	public com.doublechain.bank.accountchange.AccountChangeManager accountChangeManagerOf(BankUserContext userContext){
		return userContext.getManagerGroup().getAccountChangeManager();
	}
	public com.doublechain.bank.accountchange.AccountChangeDAO accountChangeDaoOf(BankUserContext userContext){
		return userContext.getDAOGroup().getAccountChangeDAO();
	}
	public com.doublechain.bank.userdomain.UserDomainManager userDomainManagerOf(BankUserContext userContext){
		return userContext.getManagerGroup().getUserDomainManager();
	}
	public com.doublechain.bank.userdomain.UserDomainDAO userDomainDaoOf(BankUserContext userContext){
		return userContext.getDAOGroup().getUserDomainDAO();
	}
	public com.doublechain.bank.userwhitelist.UserWhiteListManager userWhiteListManagerOf(BankUserContext userContext){
		return userContext.getManagerGroup().getUserWhiteListManager();
	}
	public com.doublechain.bank.userwhitelist.UserWhiteListDAO userWhiteListDaoOf(BankUserContext userContext){
		return userContext.getDAOGroup().getUserWhiteListDAO();
	}
	public com.doublechain.bank.secuser.SecUserManager secUserManagerOf(BankUserContext userContext){
		return userContext.getManagerGroup().getSecUserManager();
	}
	public com.doublechain.bank.secuser.SecUserDAO secUserDaoOf(BankUserContext userContext){
		return userContext.getDAOGroup().getSecUserDAO();
	}
	public com.doublechain.bank.secuserblocking.SecUserBlockingManager secUserBlockingManagerOf(BankUserContext userContext){
		return userContext.getManagerGroup().getSecUserBlockingManager();
	}
	public com.doublechain.bank.secuserblocking.SecUserBlockingDAO secUserBlockingDaoOf(BankUserContext userContext){
		return userContext.getDAOGroup().getSecUserBlockingDAO();
	}
	public com.doublechain.bank.userapp.UserAppManager userAppManagerOf(BankUserContext userContext){
		return userContext.getManagerGroup().getUserAppManager();
	}
	public com.doublechain.bank.userapp.UserAppDAO userAppDaoOf(BankUserContext userContext){
		return userContext.getDAOGroup().getUserAppDAO();
	}
	public com.doublechain.bank.listaccess.ListAccessManager listAccessManagerOf(BankUserContext userContext){
		return userContext.getManagerGroup().getListAccessManager();
	}
	public com.doublechain.bank.listaccess.ListAccessDAO listAccessDaoOf(BankUserContext userContext){
		return userContext.getDAOGroup().getListAccessDAO();
	}
	public com.doublechain.bank.objectaccess.ObjectAccessManager objectAccessManagerOf(BankUserContext userContext){
		return userContext.getManagerGroup().getObjectAccessManager();
	}
	public com.doublechain.bank.objectaccess.ObjectAccessDAO objectAccessDaoOf(BankUserContext userContext){
		return userContext.getDAOGroup().getObjectAccessDAO();
	}
	public com.doublechain.bank.loginhistory.LoginHistoryManager loginHistoryManagerOf(BankUserContext userContext){
		return userContext.getManagerGroup().getLoginHistoryManager();
	}
	public com.doublechain.bank.loginhistory.LoginHistoryDAO loginHistoryDaoOf(BankUserContext userContext){
		return userContext.getDAOGroup().getLoginHistoryDAO();
	}
	public com.doublechain.bank.genericform.GenericFormManager genericFormManagerOf(BankUserContext userContext){
		return userContext.getManagerGroup().getGenericFormManager();
	}
	public com.doublechain.bank.genericform.GenericFormDAO genericFormDaoOf(BankUserContext userContext){
		return userContext.getDAOGroup().getGenericFormDAO();
	}
	public com.doublechain.bank.formmessage.FormMessageManager formMessageManagerOf(BankUserContext userContext){
		return userContext.getManagerGroup().getFormMessageManager();
	}
	public com.doublechain.bank.formmessage.FormMessageDAO formMessageDaoOf(BankUserContext userContext){
		return userContext.getDAOGroup().getFormMessageDAO();
	}
	public com.doublechain.bank.formfieldmessage.FormFieldMessageManager formFieldMessageManagerOf(BankUserContext userContext){
		return userContext.getManagerGroup().getFormFieldMessageManager();
	}
	public com.doublechain.bank.formfieldmessage.FormFieldMessageDAO formFieldMessageDaoOf(BankUserContext userContext){
		return userContext.getDAOGroup().getFormFieldMessageDAO();
	}
	public com.doublechain.bank.formfield.FormFieldManager formFieldManagerOf(BankUserContext userContext){
		return userContext.getManagerGroup().getFormFieldManager();
	}
	public com.doublechain.bank.formfield.FormFieldDAO formFieldDaoOf(BankUserContext userContext){
		return userContext.getDAOGroup().getFormFieldDAO();
	}
	public com.doublechain.bank.formaction.FormActionManager formActionManagerOf(BankUserContext userContext){
		return userContext.getManagerGroup().getFormActionManager();
	}
	public com.doublechain.bank.formaction.FormActionDAO formActionDaoOf(BankUserContext userContext){
		return userContext.getDAOGroup().getFormActionDAO();
	}
	public com.doublechain.bank.candidatecontainer.CandidateContainerManager candidateContainerManagerOf(BankUserContext userContext){
		return userContext.getManagerGroup().getCandidateContainerManager();
	}
	public com.doublechain.bank.candidatecontainer.CandidateContainerDAO candidateContainerDaoOf(BankUserContext userContext){
		return userContext.getDAOGroup().getCandidateContainerDAO();
	}
	public com.doublechain.bank.candidateelement.CandidateElementManager candidateElementManagerOf(BankUserContext userContext){
		return userContext.getManagerGroup().getCandidateElementManager();
	}
	public com.doublechain.bank.candidateelement.CandidateElementDAO candidateElementDaoOf(BankUserContext userContext){
		return userContext.getDAOGroup().getCandidateElementDAO();
	}
	
	
	
	

	protected void checkGender(String gender, int i, int j,String targetFieldName, List<Message> messageList) {
		
		
	}
	
	//for stub only
	protected void checkDateNow(Date likeTime, int i, Object now,
			String targetFieldName, BankException exception) {
		
		
	}


	protected Object now() {

		return null;
	}
	
	protected boolean isValidIdentifier(String value){
		return hasVisualChar(value);
		
	}
	
	protected boolean hasVisualChar(String value){
		if(value==null){
			return false;
		}
		if(value.isEmpty()){
			return false;
		}
		if(value.trim().isEmpty()){
			return false;
		}
		return true;
		
	}
	protected void checkBigDecimalRange(BigDecimal projectArea, double i, double j, String projectAreaOfProject,
			List<Message> messageList) {
		
	}
    
}






