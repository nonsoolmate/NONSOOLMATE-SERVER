package com.nonsoolmate.nonsoolmateServer.external.redis.repository;


import static com.nonsoolmate.nonsoolmateServer.domain.auth.exception.AuthExceptionType.UNAUTHORIZED_REFRESH_TOKEN;

import com.nonsoolmate.nonsoolmateServer.domain.auth.exception.AuthException;


import com.nonsoolmate.nonsoolmateServer.global.jwt.service.vo.RefreshTokenVO;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RedisTokenRepository extends CrudRepository<RefreshTokenVO, String> {
    Optional<RefreshTokenVO> findByMemberId(String memberId);

    default RefreshTokenVO findByMemberIdOrElseThrowException(String memberId) {
        return findByMemberId(memberId)
                .filter(refreshTokenVO -> !refreshTokenVO.isBlack())
                .orElseThrow(
                () -> new AuthException(UNAUTHORIZED_REFRESH_TOKEN));
    }
}
