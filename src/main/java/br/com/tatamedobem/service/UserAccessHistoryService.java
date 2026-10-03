package br.com.tatamedobem.service;

import br.com.tatamedobem.domain.AppUser;
import br.com.tatamedobem.domain.UserAccessHistory;
import br.com.tatamedobem.repository.UserAccessHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class UserAccessHistoryService {

    private static final int MAX_IP_ADDRESS_LENGTH = 45;
    private static final int MAX_USER_AGENT_LENGTH = 255;

    private final UserAccessHistoryRepository userAccessHistoryRepository;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void registerAccess(AppUser user, String ipAddress, String userAgent, boolean success) {
        UserAccessHistory history = new UserAccessHistory();
        history.setUser(user);
        history.setAccessAt(LocalDateTime.now());
        history.setIpAddress(limit(ipAddress, MAX_IP_ADDRESS_LENGTH));
        history.setUserAgent(limit(userAgent, MAX_USER_AGENT_LENGTH));
        history.setSuccess(success);

        userAccessHistoryRepository.save(history);
    }

    private String limit(String value, int maxLength) {
        if (value == null || value.length() <= maxLength) {
            return value;
        }
        return value.substring(0, maxLength);
    }
}
