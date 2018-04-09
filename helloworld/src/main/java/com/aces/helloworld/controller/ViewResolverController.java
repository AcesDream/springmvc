package com.aces.helloworld.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 视图解析流程源代码分析
 * 请求处理方法执行完成后，最终返回一个 ModelAndView对象。对于那些返回 String，View 或 ModeMap 等类型的
 * 处理方法，Spring MVC 也会在内部将它们装配成一个ModelAndView 对象，它包含了逻辑名和模型对象的视图
 *
 * Spring MVC 借助视图解析器（ViewResolver）得到最终的视图对象（View），最终的视图可以是 JSP ，也可能是
 * Excel、JFreeChart 等各种表现形式的视图
 *
 * 对于最终究竟采取何种视图对象对模型数据进行渲染，处理器并不关心，处理器工作重点聚焦在生产模型数据的工
 * 作上，从而实现 MVC 的充分解耦
 *
 * 视图的作用是渲染模型数据，将模型里的数据以某种形式呈现给客户。
 *
 * 为了实现视图模型和具体实现技术的解耦，Spring 在org.springframework.web.servlet 包中定义了一个高度抽象的 View接口：
 *
 * 视图对象由视图解析器负责实例化。由于视图是无状态的，所以他们不会有线程安全的问题
 *
 */
@Controller
public class ViewResolverController {


    private Logger logger = LoggerFactory.getLogger(ViewResolverController.class);

    /**
     * 视图解析流程：
     * DispatcherServlet.java
     *
     * protected void doDispatch(HttpServletRequest request, HttpServletResponse response) throws Exception {
     *     ……
     *     //（1）
     *     mv = ha.handle(processedRequest, response, mappedHandler.getHandler());
     *     ……
     *     //（2）
     *     processDispatchResult(processedRequest, response, mappedHandler, mv, dispatchException);
     *     ……
     * }
     * private void processDispatchResult(HttpServletRequest request, HttpServletResponse response,
     * 			HandlerExecutionChain mappedHandler, ModelAndView mv, Exception exception) throws Exception {
     *     ……
     *     //（3）
     *     render(mv, request, response);
     *     ……
     * }
     * protected void render(ModelAndView mv, HttpServletRequest request, HttpServletResponse response) throws Exception {
     *     ……
     *     //（4）
     *     view = resolveViewName(mv.getViewName(), mv.getModelInternal(), locale, request);
     *     ……
     *     //（5）
     *     view.render(mv.getModelInternal(), request, response);
     *
     * }
     * protected View resolveViewName(String viewName, Map<String, Object> model, Locale locale,
     * 			HttpServletRequest request) throws Exception {
     *
     * 		for (ViewResolver viewResolver : this.viewResolvers) {
     * 		    //（4-1）InternalResourceViewResolver
     * 			View view = viewResolver.resolveViewName(viewName, locale);
     * 			if (view != null) {
     * 				return view;
     *                        }        * 		}
     * 		return null;
     *    }
     * AbstractView.java
     *
     * @Override
     * 	public void render(Map<String, ?> model, HttpServletRequest request, HttpServletResponse response) throws Exception {
     * 		if (logger.isTraceEnabled()) {
     * 			logger.trace("Rendering view with name '" + this.beanName + "' with model " + model +
     * 				" and static attributes " + this.staticAttributes);
     * 		}
     *
     * 		Map<String, Object> mergedModel = createMergedOutputModel(model, request, response);
     * 		prepareResponse(request, response);
     * 		//（5-1）
     * 		renderMergedOutputModel(mergedModel, getRequestToExpose(request), response);
     * 	}
     * InternalResourceView.java
     *
     * protected void renderMergedOutputModel(
     * 			Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) throws Exception {
     *
     * 		// Expose the model object as request attributes.
     * 		exposeModelAsRequestAttributes(model, request);
     *
     * 		// Expose helpers as request attributes, if any.
     * 		exposeHelpers(request);
     *
     * 		// Determine the path for the request dispatcher.
     * 		String dispatcherPath = prepareForRendering(request, response);
     *
     * 		// Obtain a RequestDispatcher for the target resource (typically a JSP).
     * 		//（5-2）
     * 		RequestDispatcher rd = getRequestDispatcher(request, dispatcherPath);
     * 		if (rd == null) {
     * 			throw new ServletException("Could not get RequestDispatcher for [" + getUrl() +
     * 					"]: Check that the corresponding file exists within your web application archive!");
     * 		}
     *
     * 		// If already included or response already committed, perform include, else forward.
     * 		if (useInclude(request, response)) {
     * 			response.setContentType(getContentType());
     * 			if (logger.isDebugEnabled()) {
     * 				logger.debug("Including resource [" + getUrl() + "] in InternalResourceView '" + getBeanName() + "'");
     * 			}
     * 			rd.include(request, response);
     * 		}
     *
     * 		else {
     * 			// Note: The forwarded resource is supposed to determine the content type itself.
     * 			if (logger.isDebugEnabled()) {
     * 				logger.debug("Forwarding to resource [" + getUrl() + "] in InternalResourceView '" + getBeanName() + "'");
     * 			}
     * 			//（5-3）
     * 			rd.forward(request, response);
     * 		}
     * 	}
     *
     * @return 成功页面
     */
    @RequestMapping("/testViewAndViewResolver")
    public String testViewAndViewResolver () {
        logger.info("i'm spring mvc hello world");
        return "success";
    }
}
