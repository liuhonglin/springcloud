package com.lhl.test.springcloud.provider.controller;

import com.lhl.test.springcloud.provider.exceptions.BaseBizException;
import com.lhl.test.springcloud.service.common.CommonResponse;
import com.lhl.test.springcloud.service.dto.UserDto;
import org.springframework.http.HttpRequest;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@RestController
@RequestMapping("/api")
public class IndexController {

    @GetMapping("/")
    public CommonResponse index0() {
        return new CommonResponse("200", "success", "hello");
    }

    @PostMapping("/index")
    public CommonResponse index(UserDto userDto) {

        return new CommonResponse("200", "success", userDto);
    }

    @PostMapping("/index2")
    public CommonResponse indexJson(@RequestBody UserDto userDto) {

        return new CommonResponse("200", "success", userDto);
    }

    @PostMapping("/index3")
    public CommonResponse indexStream(HttpServletRequest request) {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            InputStreamReader isr = new InputStreamReader(request.getInputStream());
            BufferedReader br = new BufferedReader(isr);

            String line = null;
            while ((line = (br.readLine())) != null) {
                stringBuilder.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new CommonResponse("200", "success", stringBuilder);
    }

    @PostMapping("/index4")
    public CommonResponse indexValid(@Validated(UserDto.Update.class) @RequestBody UserDto userDto) {

        return new CommonResponse("200", "maybeSuccess");
    }

    @PostMapping("/index5")
    public CommonResponse indexValidError(@Validated(UserDto.Update.class) @RequestBody UserDto userDto, Error errors) {

        if (errors.getCause() != null) {
            throw new BaseBizException(errors.getMessage(), errors.getCause());
        }

        return new CommonResponse("200", "maybeSuccess");
    }
}
