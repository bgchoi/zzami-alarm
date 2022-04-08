package com.zzami.alarm.core.dto.result;

public enum ResultStatus {

  OK(200, "성공"),
  CREATED(201, "등록성공"),
  BAD_REQUEST(400, "잘못된 요청입니다."),
  UNAUTHORIZED(401, "인증 실패입니다."),
  FORBIDDEN(403,"인증은 성공했지만 권한체크 실패입니다."),
  NOT_FOUND(404,"존재하지 않은 파일입니다."),
  METHOD_NOT_ALLOWED(405,"사용되지 않는 메소드입니다."),
  CONFLICT(409, "요청을 수행중에 충돌이 발생했습니다."),
  INTERNAL_SERVER_ERROR(500, "알 수 없는 오류가 발생했습니다.")
  ;
  
  private final int code;
  private final String message;
  
  private ResultStatus(int code, String message) { 
    this.code = code;
    this.message = message;
  }
  
  public int code() {
    return this.code;
  }
  
  public String message() {
    return this.message;
  }
  
}
