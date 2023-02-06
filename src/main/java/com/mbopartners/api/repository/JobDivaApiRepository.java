package com.mbopartners.api.repository;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "${feign.repository.jobdiva.name}", url = "${feign.repository.jobdiva.url}")
public interface JobDivaApiRepository {

    @GetMapping(value = "${feign.repository.jobdiva.auth.api.getAccessToken.endpoint}", produces = MediaType.APPLICATION_JSON_VALUE)
    String getAccessToken(@RequestParam(value = "clientid") String clientId, @RequestParam(value = "username") String username, @RequestParam(value = "password") String password);

    //todo : call jobdiva to get "ready to invoice" records

}
