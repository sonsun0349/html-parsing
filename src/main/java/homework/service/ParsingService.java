package homework.service;

import homework.dto.ValueDto;
import org.springframework.stereotype.Service;

@Service
public interface ParsingService {
    ValueDto parsing(String url,String type,int num);
}
