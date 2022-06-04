package mm.com.mytel.redisson.controller;

import lombok.extern.slf4j.Slf4j;
import mm.com.mytel.redisson.payload.TestModel;
import org.redisson.Redisson;
import org.redisson.api.RBucket;
import org.redisson.api.RKeys;
import org.redisson.api.RTopic;
import org.redisson.api.RedissonClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
@Slf4j
public class TestController {

    @GetMapping("")
    public Iterable<String> first(){
        RedissonClient client = Redisson.create();
        RKeys keys = client.getKeys();
        Iterable<String> allkeys=keys.getKeys();
        return allkeys;
    }

    @GetMapping("/setData")
    public String setData(){
        RedissonClient client = Redisson.create();
        RBucket<String> bucket = client.getBucket("stringObject");
        bucket.set("abcd",20, TimeUnit.SECONDS);
        bucket.expire(Duration.ofMinutes(60));
//        rediss htl thwr kyi yin a shae mhr Ouu kyg kyg character twy htae htr tl but thu br thr pyn pee call yin a mhan hwet tl;
        return bucket.get();

//        RMap<string, string="String"> map = redisson.getMap("theMap");
//        map.put("mapKey", "Risa is map value");
//        String objValue = bucket.get();
//        System.out.println("The object value is: " + objValue);
//        String mapValue = map.get("mapKey");
//        System.out.println("The map value is: " + mapValue);
//        redisson.shutdown();
    }


    @GetMapping("/testObject")
    public TestModel testObject(){
        RedissonClient client = Redisson.create();
        log.info("here 1");
        RBucket<TestModel> bucket = client.getBucket("testModel");
        log.info("here 2");
        TestModel testModel = new TestModel();
        log.info("here 1");

        testModel.setId("1111Id");
        testModel.setName("PM");
        log.info("here 3");

        bucket.set(testModel);
        log.info("here 4");

        log.error(testModel.toString());
        return bucket.get();
    }

}
