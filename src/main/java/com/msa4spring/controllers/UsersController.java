package com.msa4spring.controllers;

import com.msa4spring.requests.PostFilterRequest;
import com.msa4spring.requests.UsersPaginationRequest;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api")
public class UsersController {
    @GetMapping("/users")
    public String index(
            // @RequestParam(value = "pa") String page 유저가 보내온 파라메터명 pa인 경우
            // @RequestParam(required = false) String page 유저가 보내도 되고 안보내되는 경우
            // @RequestParam(required = false, defaultValue = "1")  String page 유저가 전달하지 않으면 기본값 1
            @RequestParam(required = false, defaultValue = "1") String page
            , @RequestParam String limit
    ) {
        // Query parameter 획득 방법 : @RequestParam 어노테이션을 통해 획득
        return "GET Users: " + page + ", " + limit;
    }

    // @GetMapping("/users/{id}") id는 유저가 보내온 명으로 보통 컬럼명과 동일하게 사용한다
    @GetMapping("/users/{id}")
    public String show(
            @PathVariable String id
    ) {
        // Segment parameter 획득 방법 : @PathVariable 어노테이션을 통해 획득
        return "GET users show" + id;
    }

    @PostMapping("/users")
    public String store() {
        return "POST Users";
    }
    // -------------------------
    // DTO를 활용하여 파라미터 획득
    // -------------------------
    @GetMapping("/users/dto-param")
    public String dtoParam(
            UsersPaginationRequest usersPaginationRequest
    ) {
        return String.format("GET dtoParam: %d %d"
                , usersPaginationRequest.page()
                , usersPaginationRequest.limit()
            );
    }

    // ---- 세그먼트 파라미터나 또는 Form Data를 DTO로 획득할 때 @ModelAttribute 어노테이션 사용
    @GetMapping("/posts/{id}/filter/{categoryId}")
    public String postFilter(
            @ModelAttribute PostFilterRequest postFilterRequest
            ) {
        return String.format("postFiler: %d, %d", postFilterRequest.id(), postFilterRequest.categoryId());
    }

    // ---- JSON 데이터를 DTO로 획득 : @RequestBody 어노테이션 사용
    @GetMapping("/posts/json")
    public String postJson(
            @RequestBody PostFilterRequest postFilterRequest
    ) {
        return String.format("postJson: %d, %d", postFilterRequest.id(),postFilterRequest.categoryId());
    }


}
