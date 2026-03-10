package microproject.user.service;

import lombok.RequiredArgsConstructor;
import microproject.user.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public void register(){

    }
}
