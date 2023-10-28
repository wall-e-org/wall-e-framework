package tech.huning.wall.e.util.async.exception;

import tech.huning.wall.e.specs.exception.CommonException;
import tech.huning.wall.e.specs.model.ResultCode;
import tech.huning.wall.e.util.StringUtil;
import tech.huning.wall.e.util.async.core.AsyncFault;
import tech.huning.wall.e.util.async.specs.IAsyncFault;

/**
 * 异步任务处理异常
 *
 * <p>更多内容参看<a href="https://huning.tech" target="_blank"><b>胡宁Tech</b></a>
 * @author huning
 */
public class AsyncException extends CommonException {

    private final IAsyncFault asyncFault;

    public AsyncException(IAsyncFault resultCode) {
        this(resultCode, null);
    }

    public AsyncException(Throwable cause) {
        this(AsyncFault.SERVICE_EXCEPTION, cause);
    }

    public AsyncException(IAsyncFault asyncFault, Throwable cause) {
        super(cause, ResultCode.ASYNC_FAILURE.getCode(),
                StringUtil.isEmpty(asyncFault.getMessage()) ? ResultCode.ASYNC_FAILURE.getMsg() : asyncFault.getMessage());
        this.asyncFault = asyncFault;
    }

    public IAsyncFault getAsyncFault() {
        return asyncFault;
    }

}
