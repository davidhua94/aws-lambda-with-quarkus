package org.github.davidhua94.api.req;

import io.quarkus.runtime.annotations.RegisterForReflection;
import org.github.davidhua94.core.Request;

import java.util.StringJoiner;

/**
 * @author David Hua
 * @date 2021/4/8
 * @desc
 */
@RegisterForReflection
public class AddPetRequest implements Request {
    private String name;

    private Integer age;

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
        return new StringJoiner(", ", AddPetRequest.class.getSimpleName() + "[", "]")
                .add("name='" + name + "'")
                .add("age=" + age)
                .toString();
    }
}
