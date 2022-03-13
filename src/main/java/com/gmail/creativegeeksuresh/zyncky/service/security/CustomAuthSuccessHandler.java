package com.gmail.creativegeeksuresh.zyncky.service.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gmail.creativegeeksuresh.zyncky.constants.AppRole;
import com.gmail.creativegeeksuresh.zyncky.service.util.CustomUtils;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

@Component
public class CustomAuthSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication auth)
            throws IOException, ServletException {
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>(auth.getAuthorities());

        if (auth instanceof AnonymousAuthenticationToken) {
            response.sendRedirect(request.getContextPath() + "/global/login?access-denied");
        } else {
            final String obtainedRole = authorities.get(0).getAuthority();
            if (obtainedRole.equals(CustomUtils.formatRole(AppRole.ADMIN.name()))) {
                response.sendRedirect(request.getContextPath() + "/admin/dashboard");
            } else if (obtainedRole.equals(CustomUtils.formatRole(AppRole.USER.name()))) {
                response.sendRedirect(request.getContextPath() + "/user/profile");
            } else if (obtainedRole.equals(CustomUtils.formatRole(AppRole.MFA.name()))) {
                response.sendRedirect(request.getContextPath() + "/mfa/secret-code");
            } else {
                response.sendRedirect(request.getContextPath() + "/global/login?unknown-role");
            }
        }

    }

}
