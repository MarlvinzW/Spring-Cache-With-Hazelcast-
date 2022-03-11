package zw.dreamhub.services;

/**
 * @author Marlvin Chihota
 * Email marlvinchihota@gmail.com
 * Created on 11/08/2021
 */

public interface Logger {
    void request(String path, Object payload);
    void payload(Object data);
}
