package org.example.expert.config;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PasswordEncoderTest {

    @Mock
    private PasswordEncoder passwordEncoder; // PasswordEncoder의 목 객체

    @InjectMocks
    private PasswordEncoderTestService passwordEncoderTestService; // 실제 테스트할 서비스

    @Test
    void matches_메서드가_정상적으로_동작한다() {
        // given
        String rawPassword = "testPassword";
        String encodedPassword = "encodedPassword";

        // Mocking matches methods
        when(passwordEncoder.matches(rawPassword, encodedPassword)).thenReturn(true);

        // when
        boolean matches = passwordEncoderTestService.checkPassword(rawPassword, encodedPassword);

        // then
        assertTrue(matches);
    }

    // 비즈니스 로직을 수행할 서비스를 설정
    static class PasswordEncoderTestService {
        private final PasswordEncoder passwordEncoder;

        PasswordEncoderTestService(PasswordEncoder passwordEncoder) {
            this.passwordEncoder = passwordEncoder;
        }

        boolean checkPassword(String rawPassword, String encodedPassword) {
            return passwordEncoder.matches(rawPassword, encodedPassword);
        }
    }
}
