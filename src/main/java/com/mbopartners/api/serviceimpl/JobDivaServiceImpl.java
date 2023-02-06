package com.mbopartners.api.serviceimpl;

import com.mbopartners.api.repository.JobDivaApiRepository;
import com.mbopartners.api.service.JobDivaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class JobDivaServiceImpl implements JobDivaService  {

    @Autowired
    private JobDivaApiRepository jobDivaApiRepository;

    @Value("${api.jobdiva.auth.username}")
    private String username;

    @Value("${api.jobdiva.auth.password}")
    private String password;

    @Value("${api.jobdiva.auth.clientid}")
    private String clientId;

    @Override
    public String getAccessToken() {
        return jobDivaApiRepository.getAccessToken(clientId, username, password);
    }
}
