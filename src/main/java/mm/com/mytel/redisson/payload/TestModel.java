package mm.com.mytel.redisson.payload;

import lombok.Data;

import java.io.Serializable;

@Data
public class TestModel implements Serializable {
    private String id;
    private String name;
    private String phoneNo;
}
