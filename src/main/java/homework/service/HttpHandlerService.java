package homework.service;

import org.springframework.stereotype.Service;

@Service
public interface HttpHandlerService {
    String httpHandle(String url);
}
