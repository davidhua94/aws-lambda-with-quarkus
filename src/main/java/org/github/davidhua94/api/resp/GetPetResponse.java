package org.github.davidhua94.api.resp;

import io.quarkus.runtime.annotations.RegisterForReflection;
import org.github.davidhua94.core.Response;

import java.util.StringJoiner;

/**
 * @author David Hua
 * @date 2021/4/13
 * @desc
 */
@RegisterForReflection
public class GetPetResponse implements Response {
    private String id;

    private String name;

    private Integer age;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", GetPetResponse.class.getSimpleName() + "[", "]")
                .add("id='" + id + "'")
                .add("name='" + name + "'")
                .add("age=" + age)
                .toString();
    }
}
