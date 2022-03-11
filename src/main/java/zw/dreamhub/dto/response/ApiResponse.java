package zw.dreamhub.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author Marlvin Chihota
 * Email marlvinchihota@gmail.com
 * Created on 11/3/2022
 */

@Data
@AllArgsConstructor
public class ApiResponse {
    private boolean successful;
    private Object data;
}
