package zw.dreamhub.services.impl;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import zw.dreamhub.services.Logger;

/**
 * @author Marlvin Chihota
 * Email marlvinchihota@gmail.com
 * Created on 11/08/2021
 */

@Service
@Slf4j
public class LoggerImpl implements Logger {

    Gson gson = new GsonBuilder().serializeNulls().setPrettyPrinting().create();

    @Override
    public void request(String path, Object payload) {
        log.info("{} : {}", path, gson.toJson(payload));
    }

    @Override
    public void payload(Object data) {
        log.info(gson.toJson(data));
    }

}
