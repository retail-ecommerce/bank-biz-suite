/*
 * JSP generated by Resin-resin-3.1.15 (built Mon, 13 Oct 2014 03:54:20 PDT)
 */

package _jsp._coreview._com._doublechain._bank._changerequest;
import javax.servlet.*;
import javax.servlet.jsp.*;
import javax.servlet.http.*;

public class _ChangeRequest$Action__jsp extends com.caucho.jsp.JavaPage
{
  private static final java.util.HashMap<String,java.lang.reflect.Method> _jsp_functionMap = new java.util.HashMap<String,java.lang.reflect.Method>();
  private boolean _caucho_isDead;
  
  public void
  _jspService(javax.servlet.http.HttpServletRequest request,
              javax.servlet.http.HttpServletResponse response)
    throws java.io.IOException, javax.servlet.ServletException
  {
    javax.servlet.http.HttpSession session = request.getSession(true);
    com.caucho.server.webapp.WebApp _jsp_application = _caucho_getApplication();
    javax.servlet.ServletContext application = _jsp_application;
    com.caucho.jsp.PageContextImpl pageContext = _jsp_application.getJspApplicationContext().allocatePageContext(this, _jsp_application, request, response, null, session, 8192, true, false);
    javax.servlet.jsp.PageContext _jsp_parentContext = pageContext;
    javax.servlet.jsp.JspWriter out = pageContext.getOut();
    final javax.el.ELContext _jsp_env = pageContext.getELContext();
    javax.servlet.ServletConfig config = getServletConfig();
    javax.servlet.Servlet page = this;
    response.setContentType("text/plain; charset=utf-8");
    request.setCharacterEncoding("UTF-8");
    com.caucho.jstl.rt.SetLocaleTag _jsp_SetLocaleTag_0 = null;
    com.caucho.jstl.rt.FormatDateTag _jsp_FormatDateTag_1 = null;
    try {
      out.write(_jsp_string0, 0, _jsp_string0.length);
      if (_jsp_SetLocaleTag_0 == null) {
        _jsp_SetLocaleTag_0 = new com.caucho.jstl.rt.SetLocaleTag();
        _jsp_SetLocaleTag_0.setPageContext(pageContext);
        _jsp_SetLocaleTag_0.setParent((javax.servlet.jsp.tagext.Tag) null);
        _jsp_SetLocaleTag_0.setValue("zh_CN");
      }

      _jsp_SetLocaleTag_0.doStartTag();
      out.write('\n');
      pageContext.defaultSetOrRemove("ignoreListAccessControl", java.lang.Boolean.TRUE);
      out.write(_jsp_string1, 0, _jsp_string1.length);
      if (_caucho_expr_0.evalBoolean(_jsp_env)) {
        out.write(_jsp_string2, 0, _jsp_string2.length);
        _caucho_expr_1.print(out, _jsp_env, false);
        out.write(_jsp_string3, 0, _jsp_string3.length);
        _caucho_expr_2.print(out, _jsp_env, false);
        out.write(_jsp_string4, 0, _jsp_string4.length);
        _caucho_expr_3.print(out, _jsp_env, false);
        out.write(_jsp_string5, 0, _jsp_string5.length);
        _caucho_expr_4.print(out, _jsp_env, false);
        out.write(_jsp_string4, 0, _jsp_string4.length);
        _caucho_expr_5.print(out, _jsp_env, false);
        out.write(_jsp_string5, 0, _jsp_string5.length);
        _caucho_expr_6.print(out, _jsp_env, false);
        out.write(_jsp_string4, 0, _jsp_string4.length);
        if (_jsp_FormatDateTag_1 == null) {
          _jsp_FormatDateTag_1 = new com.caucho.jstl.rt.FormatDateTag();
          _jsp_FormatDateTag_1.setPageContext(pageContext);
          _jsp_FormatDateTag_1.setParent((javax.servlet.jsp.tagext.Tag) null);
          _jsp_FormatDateTag_1.setPattern("yyyy-MM-dd");
        }

        _jsp_FormatDateTag_1.setValue(_caucho_expr_7.evalObject(_jsp_env));
        _jsp_FormatDateTag_1.doEndTag();
        out.write(_jsp_string6, 0, _jsp_string6.length);
      }
      out.write(_jsp_string1, 0, _jsp_string1.length);
    } catch (java.lang.Throwable _jsp_e) {
      pageContext.handlePageException(_jsp_e);
    } finally {
      if (_jsp_SetLocaleTag_0 != null)
        _jsp_SetLocaleTag_0.release();
      if (_jsp_FormatDateTag_1 != null)
        _jsp_FormatDateTag_1.release();
      _jsp_application.getJspApplicationContext().freePageContext(pageContext);
    }
  }

  private java.util.ArrayList _caucho_depends = new java.util.ArrayList();

  public java.util.ArrayList _caucho_getDependList()
  {
    return _caucho_depends;
  }

  public void _caucho_addDepend(com.caucho.vfs.PersistentDependency depend)
  {
    super._caucho_addDepend(depend);
    com.caucho.jsp.JavaPage.addDepend(_caucho_depends, depend);
  }

  public boolean _caucho_isModified()
  {
    if (_caucho_isDead)
      return true;
    if (com.caucho.server.util.CauchoSystem.getVersionId() != 3802963613949670813L)
      return true;
    for (int i = _caucho_depends.size() - 1; i >= 0; i--) {
      com.caucho.vfs.Dependency depend;
      depend = (com.caucho.vfs.Dependency) _caucho_depends.get(i);
      if (depend.isModified())
        return true;
    }
    return false;
  }

  public long _caucho_lastModified()
  {
    return 0;
  }

  public java.util.HashMap<String,java.lang.reflect.Method> _caucho_getFunctionMap()
  {
    return _jsp_functionMap;
  }

  public void init(ServletConfig config)
    throws ServletException
  {
    com.caucho.server.webapp.WebApp webApp
      = (com.caucho.server.webapp.WebApp) config.getServletContext();
    super.init(config);
    com.caucho.jsp.TaglibManager manager = webApp.getJspApplicationContext().getTaglibManager();
    manager.addTaglibFunctions(_jsp_functionMap, "c", "http://java.sun.com/jsp/jstl/core");
    manager.addTaglibFunctions(_jsp_functionMap, "fmt", "http://java.sun.com/jsp/jstl/fmt");
    manager.addTaglibFunctions(_jsp_functionMap, "fn", "http://java.sun.com/jsp/jstl/functions");
    com.caucho.jsp.PageContextImpl pageContext = new com.caucho.jsp.PageContextImpl(webApp, this);
    _caucho_expr_0 = com.caucho.jsp.JspUtil.createExpr(pageContext.getELContext(), "${not empty changeRequest}");
    _caucho_expr_1 = com.caucho.jsp.JspUtil.createExpr(pageContext.getELContext(), "${userContext.localeMap['change_request']}");
    _caucho_expr_2 = com.caucho.jsp.JspUtil.createExpr(pageContext.getELContext(), "${userContext.localeMap['change_request.id']}");
    _caucho_expr_3 = com.caucho.jsp.JspUtil.createExpr(pageContext.getELContext(), "${changeRequest.id}");
    _caucho_expr_4 = com.caucho.jsp.JspUtil.createExpr(pageContext.getELContext(), "${userContext.localeMap['change_request.name']}");
    _caucho_expr_5 = com.caucho.jsp.JspUtil.createExpr(pageContext.getELContext(), "${changeRequest.name}");
    _caucho_expr_6 = com.caucho.jsp.JspUtil.createExpr(pageContext.getELContext(), "${userContext.localeMap['change_request.create_time']}");
    _caucho_expr_7 = com.caucho.jsp.JspUtil.createExpr(pageContext.getELContext(), "${changeRequest.createTime}");
  }

  public void destroy()
  {
      _caucho_isDead = true;
      super.destroy();
  }

  public void init(com.caucho.vfs.Path appDir)
    throws javax.servlet.ServletException
  {
    com.caucho.vfs.Path resinHome = com.caucho.server.util.CauchoSystem.getResinHome();
    com.caucho.vfs.MergePath mergePath = new com.caucho.vfs.MergePath();
    mergePath.addMergePath(appDir);
    mergePath.addMergePath(resinHome);
    com.caucho.loader.DynamicClassLoader loader;
    loader = (com.caucho.loader.DynamicClassLoader) getClass().getClassLoader();
    String resourcePath = loader.getResourcePathSpecificFirst();
    mergePath.addClassPath(resourcePath);
    com.caucho.vfs.Depend depend;
    depend = new com.caucho.vfs.Depend(appDir.lookup("coreview/com/doublechain/bank/changerequest/ChangeRequest$Action.jsp"), -972667849557351146L, false);
    com.caucho.jsp.JavaPage.addDepend(_caucho_depends, depend);
    depend = new com.caucho.vfs.Depend(mergePath.lookup("jar:file:/opt/resin-3.1.12/lib/resin.jar!/com/caucho/jstl/fmt.tld"), 8594578285720415164L, false);
    com.caucho.jsp.JavaPage.addDepend(_caucho_depends, depend);
    com.caucho.jsp.JavaPage.addDepend(_caucho_depends, new com.caucho.make.ClassDependency(com.caucho.jstl.rt.SetLocaleTag.class, -5807438068488217094L));
    com.caucho.jsp.JavaPage.addDepend(_caucho_depends, new com.caucho.make.ClassDependency(com.caucho.jstl.rt.FormatDateTag.class, -4037758247139937686L));
  }

  static {
    try {
    } catch (Exception e) {
      e.printStackTrace();
      throw new RuntimeException(e);
    }
  }
  private static com.caucho.el.Expr _caucho_expr_0;
  private static com.caucho.el.Expr _caucho_expr_1;
  private static com.caucho.el.Expr _caucho_expr_2;
  private static com.caucho.el.Expr _caucho_expr_3;
  private static com.caucho.el.Expr _caucho_expr_4;
  private static com.caucho.el.Expr _caucho_expr_5;
  private static com.caucho.el.Expr _caucho_expr_6;
  private static com.caucho.el.Expr _caucho_expr_7;

  private final static char []_jsp_string0;
  private final static char []_jsp_string1;
  private final static char []_jsp_string2;
  private final static char []_jsp_string3;
  private final static char []_jsp_string5;
  private final static char []_jsp_string6;
  private final static char []_jsp_string4;
  static {
    _jsp_string0 = "\n\n\n\n\n\n\n".toCharArray();
    _jsp_string1 = "\n\n\n".toCharArray();
    _jsp_string2 = "\n\n<div class=\"col-xs-12 col-ms-4 col-md-3 action-section\" >\n	<b title=\"A ChangeRequest\" style=\"color: green\">".toCharArray();
    _jsp_string3 = "</b>\n	<hr/>\n	<ul>\n	\n	\n	<li><span>".toCharArray();
    _jsp_string5 = "</li>\n<li><span>".toCharArray();
    _jsp_string6 = "</li>\n\n	\n	</ul>\n</div>\n\n\n\n".toCharArray();
    _jsp_string4 = "</span> ".toCharArray();
  }
}
