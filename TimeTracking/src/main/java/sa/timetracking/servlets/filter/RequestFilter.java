package sa.timetracking.servlets.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("/*")
public class RequestFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest)servletRequest;
        String url = request.getRequestURL().toString();

        if (url.endsWith(".jsp")) {
            String newUrl = url.substring(0, url.length() - 4);
            ((HttpServletResponse) servletResponse).sendRedirect(newUrl);
        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }
}
