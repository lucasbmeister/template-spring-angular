package com.totvs.template.Controllers.Security;

import java.io.UnsupportedEncodingException;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.GsonBuilder;
import com.totvs.template.Annotations.ExcludeAnnotationStrategy;
import com.totvs.template.Domain.Dto.Security.Users.UserDto;
import com.totvs.template.Domain.Entities.Security.User;
import com.totvs.template.Services.Security.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.google.gson.Gson;

@RestController
@PropertySource("classpath:${configfile.path}/template.properties")
@RequestMapping(value="${endpoint.api}",
                consumes = MediaType.APPLICATION_JSON_VALUE,
                produces = MediaType.APPLICATION_JSON_VALUE)
public class SecurityController {

    @Value( "${token.secret}" )
    private String secret;

    @Autowired
    private UserService usersService ;

    @Autowired
    private ModelMapper modelMapper;

    /**
     * Login API
     * @param params Contains username and password
     * @return The user with generated JWT token or 401 error if credentials not valid
     */
    @PostMapping("/login")
    public UserDto login(HttpServletRequest request, HttpServletResponse response, @RequestBody User params) {
        // GET USER
        String username = params.getUsername();
        String password = params.getPassword();

        User user = usersService.login(username, password);
        // GENERATE JWT TOKEN
        if (user != null) {
//            if(user.getRoles() != null) {
//                user.getRoles().forEach(role -> role.set(null));
//            }
            Gson gson = new GsonBuilder().setExclusionStrategies(new ExcludeAnnotationStrategy()).create();
            Algorithm algorithm;
            try {
                algorithm = Algorithm.HMAC256(secret);
                String token = JWT.create()
                        .withClaim("user", gson.toJson(user))
                        .sign(algorithm);

                // Set token in user
                user.setToken(token);
                user.setPassword(null);

            } catch (IllegalArgumentException | UnsupportedEncodingException e) {
                e.printStackTrace();
            }

            return convertToDto(user);
        } else {
            // return 401 error
            response.setStatus(401);
            return null;
        }

    }

    /**
     * Verify JWT token API
     * @param params Contains token
     * @return The user decoded or 401 error if token not valid
     */
    @RequestMapping(value = "verifyToken", method = RequestMethod.POST, headers = "Accept=application/json")
    public UserDto verifyToken(HttpServletRequest request, HttpServletResponse response, @RequestBody User params) {

        String token = params.getToken();
        if(token == null) {
            response.setStatus(401);
            return null;
        }

        token = token.replace("Bearer ", "");


        // Get user from token decode
        try {
            //decode token
            Algorithm algorithm = Algorithm.HMAC256(secret);
            JWTVerifier verifier = JWT.require(algorithm).build();
            DecodedJWT jwt = verifier.verify(token);
            String userJSON = jwt.getClaims().get("user").asString();

            // Set user in Authentication Service
            Gson gson = new GsonBuilder().setExclusionStrategies(new ExcludeAnnotationStrategy()).create();
            Long userId = gson.fromJson(userJSON, User.class).getId();
            CompletableFuture<Optional<User>> verifiedUser = this.usersService.findOne(userId);
            Optional<User> optionalUser = verifiedUser.get();

            if(optionalUser.isPresent()) {
                User user = optionalUser.get();
                user.setPassword(null);
                return convertToDto(user);
            }

        } catch (InterruptedException | ExecutionException |IllegalArgumentException | UnsupportedEncodingException e) {
            response.setStatus(401);
            e.printStackTrace();
        }

        return null;
    }

    /**
     * Change password for current user
     * @param request
     * @param response
     * @param params
     */
    @Secured({"ROLE_PRIVATE_USER" })
    @RequestMapping(value = "/changePassword", method = RequestMethod.POST, headers = "Accept=application/json")
    public boolean changePassword(HttpServletRequest request, HttpServletResponse response, @RequestBody Map<String, String> params) {

        //AuthenticationService auth = (AuthenticationService) SecurityContextHolder.getContext().getAuthentication();

        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        String password = params.get("passwordOld");
        String passwordNew= params.get("passwordNew");

        User user = usersService.login(username, password);
        if (user == null) {
            response.setStatus(500);
            return false;
        }

        try {
            CompletableFuture<Optional<User>> userLogged = usersService.findOne(user.getId());
            Optional<User> optionalUser = userLogged.get();

            if (optionalUser.isPresent()) {
                User updatedUser = optionalUser.get();
                updatedUser.setPassword(passwordNew);
                usersService.insert(updatedUser);
                return true;
            } else {
                response.setStatus(404);
                return false;
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return false;
        }
    }

    private UserDto convertToDto(User user) {
        UserDto userDto = modelMapper.map(user, UserDto.class);
        return userDto;
    }
}