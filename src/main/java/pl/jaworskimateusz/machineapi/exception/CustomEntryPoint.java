package pl.jaworskimateusz.machineapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import pl.jaworskimateusz.machineapi.utils.DateUtils;
import pl.jaworskimateusz.machineapi.utils.ObjectToJson;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;

@Component
public class CustomEntryPoint implements AuthenticationEntryPoint {

    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authenticationException) throws IOException {

        String jsonString = ObjectToJson.objectToJson(
                new ErrorResponseTimeString(DateUtils.dateTimeToString(LocalDateTime.now()),
                        authenticationException.getMessage(),
                        HttpStatus.UNAUTHORIZED.value()),
                ErrorResponseTimeString.class);

        response.setContentType("application/json");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.getOutputStream().println(jsonString);

    }
}
