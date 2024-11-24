package ca.ulaval.glo4003.repUL.api.config.authentication;

import ca.ulaval.glo4003.init.servicelocator.ServiceLocator;
import ca.ulaval.glo4003.repUL.api.config.authentication.annotation.Protected;
import ca.ulaval.glo4003.repUL.api.config.authentication.exception.InvalidCredentialFilterException;
import ca.ulaval.glo4003.repUL.application.auth.AuthService;
import ca.ulaval.glo4003.repUL.application.auth.entity.AuthEntity;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ResourceInfo;
import jakarta.ws.rs.core.HttpHeaders;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;

import static ca.ulaval.glo4003.repUL.application.auth.entity.AuthorizationType.ALL;
import static ca.ulaval.glo4003.repUL.application.auth.entity.AuthorizationType.STUDENT;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

/**
 * Mockito has an issue on these tests, it's impossible to mock reflect.utils from Java
 * because Mockito use it. Here is the issue: <a href="https://github.com/mockito/mockito/issues/2026">...</a>
 */
@Disabled
class AuthenticationFilterTest {
    private ResourceInfo resourceInfo;

    private AuthService authService;

    private AuthenticationFilter authenticationFilter;

    private final static String TOKEN = "42";

    private final static String BEARER = "Bearer " + TOKEN;

    private final static String EMAIL = "test@test.com";

    @BeforeEach()
    void setup() {
        resourceInfo = mock();
        authService = mock();
        ServiceLocator.getInstance().registerService(AuthService.class, authService);
        authenticationFilter = new AuthenticationFilter();
        authenticationFilter.setResourceInfo(resourceInfo);
    }

    @Test
    void givenContainerRequestContext_whenFilter_thenSetSecurityContext() {
        // GIVEN
        ContainerRequestContext containerRequestContext = mock();
        Method mockedMethod = mock();

        when(resourceInfo.getResourceMethod()).thenReturn(mockedMethod).getMock();
        when(mockedMethod.isAnnotationPresent(Protected.class)).thenReturn(true);
        when(containerRequestContext.getHeaderString(HttpHeaders.AUTHORIZATION)).thenReturn(BEARER);
        when(authService.validateToken(any(), ALL))
                .thenReturn(new AuthEntity("test@test.com", STUDENT));

        // WHEN
        authenticationFilter.filter(containerRequestContext);


        // THEN
        JwtSecurityContext jwtSecurityContext = new JwtSecurityContext(
                new AuthenticatedUserPrincipal(EMAIL)
        );

        verify(containerRequestContext, times(1))
                .setSecurityContext(jwtSecurityContext);
    }

    @Test
    void givenContainerRequestContextWithNoBearer_whenFilter_thenThrowInvalidCredentialFilterException() {
        // GIVEN
        ContainerRequestContext containerRequestContext = mock();
        Method mockedMethod = mock();

        when(resourceInfo.getResourceMethod()).thenReturn(mockedMethod).getMock();
        when(mockedMethod.isAnnotationPresent(Protected.class)).thenReturn(true);
        when(containerRequestContext.getHeaderString(HttpHeaders.AUTHORIZATION)).thenReturn(null);

        // WHEN
        assertThatThrownBy(() -> {
            authenticationFilter.filter(containerRequestContext);
        })
                .isInstanceOf(InvalidCredentialFilterException.class)
                .hasMessageContaining("INVALID_CREDENTIALS");
    }

    @Test
    void givenContainerRequestContextWithWrongBearer_whenFilter_thenThrowInvalidCredentialFilterException() {
        // GIVEN
        ContainerRequestContext containerRequestContext = mock();
        Method mockedMethod = mock();

        when(resourceInfo.getResourceMethod()).thenReturn(mockedMethod);
        when(mockedMethod.isAnnotationPresent(Protected.class)).thenReturn(true);
        when(containerRequestContext.getHeaderString(HttpHeaders.AUTHORIZATION)).thenReturn("Wrong bearer");

        // WHEN
        assertThatThrownBy(() -> {
            authenticationFilter.filter(containerRequestContext);
        })
                .isInstanceOf(InvalidCredentialFilterException.class)
                .hasMessageContaining("INVALID_CREDENTIALS");
    }

    @Test
    void givenContainerRequestContextNotProtectedRoute_whenFilter_thenDoNothing() {
        // GIVEN
        ContainerRequestContext containerRequestContext = mock();
        Method mockedMethod = mock();

        when(resourceInfo.getResourceMethod()).thenReturn(mockedMethod).getMock();
        when(mockedMethod.isAnnotationPresent(Protected.class)).thenReturn(false);
        doReturn(Object.class).when(resourceInfo).getResourceClass();

        // WHEN
        authenticationFilter.filter(containerRequestContext);


        // THEN
        verify(containerRequestContext, times(0))
                .setSecurityContext(any());
    }
}