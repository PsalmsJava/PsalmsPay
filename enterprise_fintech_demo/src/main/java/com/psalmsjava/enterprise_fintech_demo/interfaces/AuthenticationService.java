package com.psalmsjava.enterprise_fintech_demo.interfaces;

import com.psalmsjava.enterprise_fintech_demo.dao.requests.SignInRequest;
import com.psalmsjava.enterprise_fintech_demo.dao.requests.SignUpRequest;
import com.psalmsjava.enterprise_fintech_demo.dao.response.JwtAuthenticationResponse;

public interface AuthenticationService {
    JwtAuthenticationResponse signup(SignUpRequest request);

    JwtAuthenticationResponse signin(SignInRequest request);
}
