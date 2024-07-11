package com.mannazo.admin.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;


@FeignClient(name = "post-service")
public interface PostServiceClient {
    @GetMapping("/count")
    int getNumberOfPosts();
}
