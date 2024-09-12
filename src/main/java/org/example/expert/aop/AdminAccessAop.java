package org.example.expert.aop;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Slf4j
@Aspect
@Component
@RequiredArgsConstructor
public class AdminAccessAop {

    private final HttpServletRequest request;

    // 메서드를 타겟으로 지정
    @Pointcut("within(org.example.expert.domain.comment.controller.CommentAdminController) || " +
            "within(org.example.expert.domain.user.controller.UserAdminController)")
    public void adminControllerMethods() {
    }

    // 메서드 실행 전에 로그를 남기도록 설정
    @Before("adminControllerMethods()")
    public void logAdminAccess() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userId = authentication != null ? authentication.getName() : "사용자가 없습니다.";
        String requestUrl = request.getRequestURI();
        LocalDateTime requestTime = LocalDateTime.now();

        log.info("Admin Access Log - User Id: {}, URL: {}, Time: {}", userId, requestUrl, requestTime);
    }
}
