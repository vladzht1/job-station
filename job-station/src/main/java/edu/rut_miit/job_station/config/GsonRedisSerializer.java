package edu.rut_miit.job_station.config;

import java.io.UnsupportedEncodingException;

import org.hibernate.type.SerializationException;
import org.springframework.data.redis.serializer.RedisSerializer;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@SuppressWarnings("rawtypes")
public class GsonRedisSerializer implements RedisSerializer {
    private Gson gson;

    public GsonRedisSerializer(GsonBuilder builder) {
        this.gson = builder.create();
    }

    @Override
    public byte[] serialize(Object o) throws SerializationException {
        return gson.toJson(o).getBytes();
    }

    @Override
    public Object deserialize(byte[] bytes) throws SerializationException {
        try {
            return gson.fromJson(new String(bytes, "UTF-8"), Object.class);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return null;
    }
}
