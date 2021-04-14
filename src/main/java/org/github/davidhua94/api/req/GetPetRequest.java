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
public class GetPetRequest implements Request {
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", GetPetRequest.class.getSimpleName() + "[", "]")
                .add("id='" + id + "'")
                .toString();
    }
}
