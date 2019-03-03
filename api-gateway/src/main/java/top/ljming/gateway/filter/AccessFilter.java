package top.ljming.gateway.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;

public class AccessFilter extends ZuulFilter {

    private static Logger logger = LoggerFactory.getLogger(AccessFilter.class);
    @Override
    public String filterType() {  // 决定过滤器在请求的哪个生命周期执行。 pre 表示在请求路前执行
        return "pre";
    }

    @Override
    public int filterOrder() {  // 过滤器执行顺序，当请求一个阶段中存在多个过滤器时，需要根据该方法返回到值来依次执行
        return 0;
    }

    @Override
    public boolean shouldFilter() {  // 判断该过滤器是否需要执行。
        return true;
    }

    @Override
    public Object run() {  // 过滤器的具体逻辑
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();

        logger.info("send {} request to {}", request.getMethod(), request.getRequestURL().toString());
        Object accessToken = request.getParameter("accessToken");

        if (accessToken == null) {
            logger.warn("accessToken is empty");
            ctx.setSendZuulResponse(false);
            ctx.setResponseStatusCode(401);
            return null;
        }

        logger.info("accessToken ok");
        return null;
    }
}
