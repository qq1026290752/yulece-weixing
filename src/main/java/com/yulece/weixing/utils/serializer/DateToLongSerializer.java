package com.yulece.weixing.utils.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.util.Date;

/**
 * @author wangyichao@28ph.cn
 * @Title: DateToLongSerializer
 * @Package com.yulece.weixing.utils.serializer
 * @Description: 时间转Long
 * @Date 2018/4/15/22:02
 **/
public class DateToLongSerializer extends JsonSerializer<Date> {
    @Override
    public void serialize(Date date, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeNumber(date.getTime()/1000);
    }
}
