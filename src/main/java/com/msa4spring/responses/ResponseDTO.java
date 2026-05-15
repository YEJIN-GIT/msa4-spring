package com.msa4spring.responses;

import lombok.Builder;
import lombok.Getter;

// @Getter 어노데이션 : 클래스 레벨이나 필드 레벨에 선언시, 클래스의 모든 필드에 대한 Getter메서드를 자동으로 생성
@Getter
@Builder
public class ResponseDTO<T> {
    private String code;
    private String msg;
    private T data;
}
