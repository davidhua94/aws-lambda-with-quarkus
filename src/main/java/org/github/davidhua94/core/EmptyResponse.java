package org.github.davidhua94.core;

import io.quarkus.runtime.annotations.RegisterForReflection;

/**
 * @author David Hua
 * @date 2021/4/8
 * @desc do not return any fields.
 */
@RegisterForReflection
public final class EmptyResponse implements Response {
}
