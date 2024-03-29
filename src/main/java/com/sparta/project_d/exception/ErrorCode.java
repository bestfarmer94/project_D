package com.sparta.project_d.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
@Getter
public enum ErrorCode {
    DuplicatedCategory(HttpStatus.BAD_REQUEST, "이미 존재하는 카테고리 입니다."),
    NotFoundCategory(HttpStatus.NOT_FOUND, "카테고리가 존재하지 않습니다."),
    ExistsSupplyInCategory(HttpStatus.BAD_REQUEST, "카테고리에 비품이 존재하여 삭제할 수 없습니다."),

    DuplicatedDepartment(HttpStatus.BAD_REQUEST, "이미 존재하는 부서입니다."),
    NotFoundDepartment(HttpStatus.NOT_FOUND, "해당 부서가 존재하지 않습니다."),
    ExistsUserInDepartment(HttpStatus.BAD_REQUEST, "부서에 사원이 존재하여 삭제할 수 없습니다."),
    AtLeastOneNeedDept(HttpStatus.BAD_REQUEST, "최소 한개의 부서는 등록해야 합니다."),

    DuplicatedPartners(HttpStatus.BAD_REQUEST, "이미 등록된 협력업체 입니다."),
    NotFoundPartners(HttpStatus.NOT_FOUND, "해당 업체가 존재하지 않습니다."),
    NotFoundUsers(HttpStatus.NOT_FOUND, "해당 사용자가 존재하지 않습니다."),

    NotFoundRequest(HttpStatus.NOT_FOUND, "삭제된 요청입니다."),
    NotFoundImages(HttpStatus.NOT_FOUND, "해당 요청의 이미지를 찾을 수 없습니다."),
    NotFoundFileInS3(HttpStatus.NOT_FOUND, "해당 파일을 S3에서 찾을 수 없습니다."),
    NotMatchedAmountImages(HttpStatus.BAD_REQUEST, "누락된 이미지가 존재합니다."),
    InvalidBase64(HttpStatus.BAD_REQUEST, "Base64 파일이 잘못되었습니다."),

    NotFoundNotification(HttpStatus.NOT_FOUND, "삭제된 알림입니다."),
    NotAllowedMethod(HttpStatus.METHOD_NOT_ALLOWED, "잘못된 요청입니다."),
    NoPermission(HttpStatus.BAD_REQUEST, "해당 요청에 대한 권한이 없습니다."),
    NotFoundSupply(HttpStatus.NOT_FOUND, "해당 비품이 존재하지 않습니다."),
    NullComment(HttpStatus.BAD_REQUEST, "거절 사유를 작성해 주세요."),
    NotStockSupply(HttpStatus.BAD_REQUEST, "사용 중인 비품입니다."),
    isProcessingRequest(HttpStatus.BAD_REQUEST, "처리 중인 요청이 있습니다."),
    NullImageList(HttpStatus.BAD_REQUEST, "저장된 이미지가 없습니다."),
    NotUnProcessedRequest(HttpStatus.BAD_REQUEST, "처리 전 요청만 수정/삭제가 가능합니다."),
    InValidRequest(HttpStatus.BAD_REQUEST, "해당 키워드의 이미지를 검색할 수 없습니다."),
    ProcessedRequest(HttpStatus.BAD_REQUEST, "이미 처리된 요청입니다."),
    InValidTimePattern(HttpStatus.BAD_REQUEST, "날짜형식을 맞춰주세요. yyyy-MM-dd"),
    ExcelAmountLessThanTwo(HttpStatus.BAD_REQUEST, "최소 2개 이상의 엑셀 데이터만 저장 가능합니다."),
    DuplicateSerialNum(HttpStatus.BAD_REQUEST, "해당 시리얼넘버의 비품이 이미 등록되어 있습니다."),

    JsonConvertError(HttpStatus.BAD_REQUEST, "Json 형식으로 변환에 실패했습니다."),
    UnAuthorized(HttpStatus.UNAUTHORIZED, "로그인을 해주세요."),
    NotFoundUser(HttpStatus.BAD_REQUEST, "해당 사원이 존재하지 않습니다."),
    NotMatchPassword(HttpStatus.BAD_REQUEST, "비밀번호가 일치하지 않습니다."),
    NotAllowSamePassword(HttpStatus.BAD_REQUEST, "이전과 동일한 비밀번호입니다."),
    NotMatchAdminPassword(HttpStatus.BAD_REQUEST, "관리자 암호가 일치하지 않습니다."),
    DuplicateUsername(HttpStatus.BAD_REQUEST, "이미 존재하는 아이디 입니다."),
    InValidException(HttpStatus.BAD_REQUEST, "값이 잘못되었습니다."),
    FailedRevokeGoogleAccessToken(HttpStatus.BAD_REQUEST, "구글 액세스토큰 취소가 실패했습니다."),
    TokenSecurityExceptionOrMalformedJwtException(HttpStatus.BAD_REQUEST, "Invalid JWT signature, 유효하지 않는 JWT 서명 입니다."),
    TokenExpiredJwtException(HttpStatus.BAD_REQUEST, "Expired JWT token, 만료된 JWT token 입니다."),
    TokenUnsupportedJwtException(HttpStatus.BAD_REQUEST, "Unsupported JWT token, 지원되지 않는 JWT 토큰 입니다."),
    TokenIllegalArgumentException(HttpStatus.BAD_REQUEST, "JWT claims is empty, 잘못된 JWT 토큰 입니다."),
    RefreshTokenValidException(HttpStatus.BAD_REQUEST, "refreshToken이 유효하지 않습니다."),
    NotFoundRefreshToken(HttpStatus.SEE_OTHER, "refreshToken이 존재하지 않습니다."),
    NotMatchedIp(HttpStatus.SEE_OTHER, "로그인 시점과 Ip가 일치하지 않습니다. 재로그인 해주세요."),
    TokenNeedReIssue(HttpStatus.SEE_OTHER, "액세스 토큰이 만료되었습니다. 재발급 해주세요."),
    InValidAccessToken(HttpStatus.BAD_REQUEST, "액세스 토큰이 유효하지 않습니다.");

    private final HttpStatus httpStatus;
    private final String message;
}