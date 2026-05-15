package com.msa4spring.errors;

import com.msa4spring.responses.ResponseDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLException;
import java.util.List;

// ExceptionHandler 클래스 생성(클래스명은 아무거나 상관없다.)
// @RestControllerAdvice 어노테이션 : 글로벌 excetion을 받는다.
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    // 예외 처리를 실행할 메소드 정의 (메소드명 자유)

    // 유효성 검사 실패 예외 처리 (@Valid, @Validated)
    @ExceptionHandler(MethodArgumentNotValidException.class)
// ---- ResponseDTO<String> 문자열로 출력시
//    public ResponseEntity<ResponseDTO<String>> validationHandle(MethodArgumentNotValidException e) {
//        String errorMsg = e.getBindingResult()
//                .getAllErrors()
//                .stream()
//                .map(ObjectError::getDefaultMessage)
//                .collect(Collectors.joining(", "));
//
//        ResponseDTO<String> responseDTO = ResponseDTO.<String>builder()
//                .code("E01")
//                .msg("유효성 검사 실패" + errorMsg)
//                .build();
// ---- ResponseDTO<List<String>> 리스트로 출력시
    public ResponseEntity<ResponseDTO<List<String>>> validationHandle(MethodArgumentNotValidException e) {
        List<String> errorMsgList = e.getBindingResult()
                .getAllErrors()
                .stream()
                .map(ObjectError::getDefaultMessage)
                .toList();

        // ---- stream 을 안쓴 경우
        // List<ObjectError> test2 = e.getBindingResult().getAllErrors();
        // List<String> list = new ArrayList<>();
        // for(ObjectError item : test2) {
        //    list.add(item.getDefaultMessage());
        // }
        // ----

        ResponseDTO<List<String>> responseDTO = ResponseDTO.<List<String>>builder()
                .code("E01")
                .msg("유효성 검사 실패")
                .data(errorMsgList)
                .build();

        return ResponseEntity.status(400).body(responseDTO);
    }

    // DB 관련 예외 핸들러
    @ExceptionHandler(SQLException.class)
    public ResponseEntity<ResponseDTO<String>> sqlExceptionHandle(SQLException e) {
        log.error(e.getMessage());

        ResponseDTO<String> responseDTO = ResponseDTO.<String>builder()
                .code("E80")
                .msg("DB 에러 발생")
                .data("현재 서비스 이용 불가합니다. \n잠시후 다시 시도해 주십시오.")
                .build();
        return ResponseEntity.status(500).body(responseDTO);
    }

    // 나머지 예외들 처리
    // Exception.class 최상위 Exception 객체 예외 경우
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseDTO<String>> othersHandle(Exception e) {
        log.error(e.getMessage());

        ResponseDTO<String> responseDTO = ResponseDTO.<String>builder()
                .code("E99")
                .msg("서버 에러 발생")
                .data("현재 서비스 이용 불가합니다. \n잠시후 다시 시도해 주십시오.")
                .build();
        return ResponseEntity.status(500).body(responseDTO);
    }
}
