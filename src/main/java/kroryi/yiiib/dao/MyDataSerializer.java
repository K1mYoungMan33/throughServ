package kroryi.yiiib.dao;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;
import java.util.Map;

public class MyDataSerializer implements JsonSerializer<MyData> {

    @Override
    public JsonElement serialize(MyData myData, Type type, JsonSerializationContext context) {
//        JsonObject jsonObject = new JsonObject();

        // 여기서 MyData의 필드들을 JsonObject에 추가
//        jsonObject.addProperty("fieldName1", myData.getFieldName1());
//        jsonObject.addProperty("fieldName2", myData.getFieldName2());
        // ...

        // Map에 대한 처리 (예: key1:value1, key2:value2, ...)
        JsonObject mapObject = new JsonObject();
        for (Map.Entry<String, Map<String, Object>> entry : myData.map.entrySet()) {

            for (Map.Entry<String, Object> inEntry : entry.getValue().entrySet()) {

                mapObject.addProperty(entry.getKey(), inEntry.getValue().toString() );
                break;
            }
        }
//        jsonObject.add("map", mapObject);

        return mapObject;
    }


}
