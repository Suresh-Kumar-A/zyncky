package com.gmail.creativegeeksuresh.zyncky.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gmail.creativegeeksuresh.zyncky.service.util.AppConstants;
import com.gmail.creativegeeksuresh.zyncky.service.util.AppConstants.UserRole;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.stereotype.Component;

@Component
public class CustomAuthSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication auth)
            throws IOException, ServletException {
        RequestCache requestCache = new HttpSessionRequestCache();
        SavedRequest savedRequest = requestCache.getRequest(request, response);
        final String requestContextPath = request.getContextPath();

        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>(auth.getAuthorities());
        if ((!(auth instanceof AnonymousAuthenticationToken))
                && authorities.get(0).getAuthority().equals(AppConstants.ROLE_PREFIX +
                        UserRole.USER.toString())) {
            // response.sendRedirect(request.getContextPath() + "/user/view-books");
            response.sendRedirect(
                getRedirectUrl(requestCache, savedRequest, requestContextPath, UserRole.USER));

        } else if ((!(auth instanceof AnonymousAuthenticationToken))
                && authorities.get(0).getAuthority().equals(AppConstants.ROLE_PREFIX +
                UserRole.ADMIN.toString())) {
            // response.sendRedirect(request.getContextPath() + "/admin/dashboard");
            response.sendRedirect(
                getRedirectUrl(requestCache, savedRequest, requestContextPath, UserRole.ADMIN));

        } else {
            // response.sendRedirect(request.getContextPath() + "/login?access-denied");
            response.sendRedirect(getRedirectUrl(requestCache, savedRequest, requestContextPath, UserRole.ANONYMOUS));

        }

    }

    private String getRedirectUrl(RequestCache requestCache, SavedRequest savedRequest,
            String requestContextPath, UserRole userRole) {
        try {
            String redirectUrl = requestContextPath.concat("/global/login");

            if (savedRequest != null) {
                redirectUrl = savedRequest.getRedirectUrl();
            }else{
                switch (userRole) {
                    case ADMIN: {
                        redirectUrl = requestContextPath.concat("/admin/dashboard");
                    }
                        break;
                    case USER: {
                        redirectUrl = requestContextPath.concat("/user/dashboard");
                    }
                        break;
                    default: {
                        redirectUrl = requestContextPath.concat("/global/login?access-denied");
                    }
                        break;
                }
            }

            return redirectUrl;
        } catch (Exception e) {
            e.printStackTrace();
            return requestContextPath.concat("/global/login");
        }
    }

}
