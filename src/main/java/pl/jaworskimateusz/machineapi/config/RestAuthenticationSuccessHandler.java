package pl.jaworskimateusz.machineapi.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import pl.jaworskimateusz.machineapi.model.User;
import pl.jaworskimateusz.machineapi.service.UserService;
import pl.jaworskimateusz.machineapi.utils.ObjectToJson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

@Component
public class RestAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private final long expirationTime;
    private final String secret;

    @Autowired
    private UserService userService;

    public RestAuthenticationSuccessHandler(
            @Value("${jwt.expiration-time}") long expirationTime,
            @Value("${jwt.secret}") String secret) {
        this.expirationTime = expirationTime;
        this.secret = secret;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        UserDetails principal = (UserDetails) authentication.getPrincipal();
        String token = JWT.create()
                .withSubject(principal.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + expirationTime))
                .sign(Algorithm.HMAC256(secret));
        response.addHeader("Authorization", "Bearer " + token);
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");

        PrintWriter out = response.getWriter();
        JSONObject jsonToken = new JSONObject("{\"token\":\"" + token + "\"}");
        JSONObject user = new JSONObject(userService.findByUsername(principal.getUsername()));
//        String js = ObjectToJson.objectToJson(userService.findByUsername(principal.getUsername()), User.class);
        out.print("{" + "\"token\":\"" + token + "\"" + "," + "\"user\":" + user + "}");
        out.flush();
    }
}
