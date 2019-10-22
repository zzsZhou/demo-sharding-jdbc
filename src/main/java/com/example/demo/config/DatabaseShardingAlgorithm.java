package com.example.demo.config;

import io.shardingjdbc.core.api.algorithm.sharding.PreciseShardingValue;
import io.shardingjdbc.core.api.algorithm.sharding.standard.PreciseShardingAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Collection;

/**
 * @author zhouq
 * @version 1.0
 * @date 2019/10/22
 */
@Component
public class DatabaseShardingAlgorithm implements PreciseShardingAlgorithm<Integer> {


    @Override
    public String doSharding(Collection<String> collection, PreciseShardingValue<Integer> preciseShardingValue) {

        if(Integer.parseInt(preciseShardingValue.getValue().toString()) <=50){
            return (String) collection.toArray()[0];
        }else {
            return (String) collection.toArray()[1];
        }
        //throw new IllegalArgumentException();
    }
}
