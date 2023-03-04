package vti.studentproject.Controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import vti.studentproject.Config.JWT.JwtTokenUtils;
import vti.studentproject.Exception.AppException;
import vti.studentproject.Exception.ErrorResponseBase;
import vti.studentproject.Model.DTO.LoginDTO;
import vti.studentproject.Model.Entity.Account;
import vti.studentproject.Model.Request.LoginRequest;
import vti.studentproject.Repository.AccountRepository;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.Optional;

@RestController
@RequestMapping("api/")
@CrossOrigin("*")
@Validated
@Component
public class AuthController {
    @Autowired
    JwtTokenUtils jwtTokenUtils;
    @Autowired
    private AccountRepository repository;
    @Autowired
    private BCryptPasswordEncoder encoder;
    @Autowired
    private HttpServletRequest httpServletRequest;

    @PostMapping("/login1")
    public LoginDTO loginDTO(Principal principal) {
        String username = principal.getName();
        Optional<Account> optionalAccount = repository.findByUsername(username);
        if (optionalAccount.isEmpty()) {
            throw new AppException(ErrorResponseBase.LOGIN_ERR);
        }
        LoginDTO logindto = new LoginDTO();
        BeanUtils.copyProperties(optionalAccount.get(), logindto);
        return logindto;
    }

    @PostMapping("/login")
    public LoginDTO loginJWT(@RequestBody LoginRequest request) {
        Optional<Account> accountOptional = repository.findByUsername(request.getUsername());
        if (accountOptional.isEmpty()) {
            throw new AppException(ErrorResponseBase.Login_username);

        }
        if (!encoder.matches(request.getPassword(), accountOptional.get().getPassword())) {
            throw new AppException(ErrorResponseBase.Login_password);
        }
        LoginDTO loginDTO = new LoginDTO();
        BeanUtils.copyProperties(accountOptional.get(), loginDTO);
        loginDTO.setUserAgent(httpServletRequest.getHeader("User-Agent"));
        String token = jwtTokenUtils.createAccessToken(loginDTO);
        loginDTO.setToken(token);
        return loginDTO;
    }

}
