package com.gmail.creativegeeksuresh.zyncky.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gmail.creativegeeksuresh.zyncky.service.util.AppConstants;

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

        System.out.println(savedRequest);
        System.out.println(savedRequest.getRedirectUrl());

        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>(auth.getAuthorities());
        System.out.println("Context path: " + request.getContextPath());
        // System.out.println("Context path: "+reques);
        // response.sendRedirect(request.getContextPath() + "/user/view-books");
        if ((!(auth instanceof AnonymousAuthenticationToken))
                && authorities.get(0).getAuthority().equals(AppConstants.ROLE_PREFIX +
                        AppConstants.USER_ROLE)) {
            // response.sendRedirect(request.getContextPath() + "/user/view-books");
            response.sendRedirect(getRedirectUrl(requestCache, savedRequest, requestContextPath, AppConstants.USER_ROLE));

        } else if ((!(auth instanceof AnonymousAuthenticationToken))
                && authorities.get(0).getAuthority().equals(AppConstants.ROLE_PREFIX +
                        AppConstants.ADMIN_ROLE)) {
            // response.sendRedirect(request.getContextPath() + "/admin/dashboard");
            response.sendRedirect(getRedirectUrl(requestCache, savedRequest, requestContextPath, AppConstants.ADMIN_ROLE));

        } else {
            // response.sendRedirect(request.getContextPath() + "/login?access-denied");
            response.sendRedirect(getRedirectUrl(requestCache, savedRequest, requestContextPath, ""));

        }

    }

    private String getRedirectUrl(RequestCache requestCache, SavedRequest savedRequest,
            String requestContextPath, String roleName) {
        try {
            String redirectUrl = requestContextPath.concat("/global/login");

            if (savedRequest != null) {
                redirectUrl = savedRequest.getRedirectUrl();
                // String[] tmpArr = redirectUrl.split("/");
                // if(tmpArr.length>=4){
                //     String tmpUrlPrefix = tmpArr[3];
                //     if(tmpUrlPrefix.equalsIgnoreCase(AppConstants.ADMIN_ROLE)){

                //     }
                // }
            }else{
                switch (roleName) {
                    case AppConstants.ADMIN_ROLE: {
                        redirectUrl = requestContextPath.concat("/admin/dashboard");
                    }
                        break;
                    case AppConstants.USER_ROLE: {
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
