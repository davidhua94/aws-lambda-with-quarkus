package org.github.davidhua94.core;

/**
 * @author David Hua
 * @date 2021/3/26
 * @desc
 */
public interface LambdaApi<Req extends Request, Resp extends Response> {
    /**
     * execute a detail action
     * @param request request parameter model
     * @param response response result model
     */
    void execute(Req request, Resp response) throws Exception;
}
