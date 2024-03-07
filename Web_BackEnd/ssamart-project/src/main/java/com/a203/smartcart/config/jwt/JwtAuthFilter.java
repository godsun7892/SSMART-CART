    package com.a203.smartcart.config.jwt;

    import com.a203.smartcart.config.auth.CustomUserDetailsService;
    import jakarta.servlet.FilterChain;
    import jakarta.servlet.ServletException;
    import jakarta.servlet.http.HttpServletRequest;
    import jakarta.servlet.http.HttpServletResponse;
    import lombok.RequiredArgsConstructor;
    import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
    import org.springframework.security.core.context.SecurityContextHolder;
    import org.springframework.security.core.userdetails.UserDetails;
    import org.springframework.web.filter.OncePerRequestFilter;

    import java.io.IOException;


    // 인증 필터
    // login 요청이 오면 UsernamePasswordAuthenticationFilter가 낚아챔
    @RequiredArgsConstructor
    public class JwtAuthFilter extends OncePerRequestFilter {

        private final CustomUserDetailsService customUserDetailsService;
        private final JwtUtil jwtUtil;

        // Authentication 객체 만들어서 리턴 => 의존 : AuthenticationManager
        // JWT 토큰 검증 필터 수행
        @Override
        protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
            String authorizationHeader = request.getHeader("Authorization");

            // JWT가 헤드에 있는 경우
            if(authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
                String token= authorizationHeader.substring(7);
                System.out.println("아니 너 헤드에 토큰을 가지고 왔자나");
                //JWT 유효성 검증
                if(jwtUtil.validateToken(token)) {
                    String userId = jwtUtil.getUserId(token);

                    // 여기서 확인
                    // 유저와 토큰 일치 시 userDetails 생성
                    UserDetails userDetails = customUserDetailsService.loadUserByUsername(userId);
                    System.out.println("너 우리 회원이구나");
                    if(userDetails != null) {
                        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                                new UsernamePasswordAuthenticationToken(
                                        userDetails,
                                        null,
                                        userDetails.getAuthorities()
                                );
                        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);

                    }

                }

            }
            System.out.println("인증 완료 최종");
            filterChain.doFilter(request, response);


        }
    }
