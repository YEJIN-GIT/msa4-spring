package com.msa4spring.requests;

public record UsersPaginationRequest(
        Integer page
        , Integer limit
) {
    // 생성자를 통해 초기값 세팅 하려면
    public UsersPaginationRequest(Integer page, Integer limit) {
        this.page = (page == null) ? 1 : page;
        this.limit = (limit == null) ? 10 : limit;
    }
}
