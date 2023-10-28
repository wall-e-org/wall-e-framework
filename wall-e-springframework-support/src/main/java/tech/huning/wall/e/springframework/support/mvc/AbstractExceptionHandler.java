package tech.huning.wall.e.springframework.support.mvc;

import com.alibaba.fastjson.JSON;
import tech.huning.wall.e.specs.exception.*;
import tech.huning.wall.e.specs.model.ResponseModel;
import tech.huning.wall.e.specs.model.ResultCode;
import tech.huning.wall.e.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

/**
 * 全局异常处理
 *
 * <p>更多内容参看<a href="https://huning.tech" target="_blank"><b>胡宁Tech</b></a>
 * @author huning
 */
@SuppressWarnings("rawtypes")
public abstract class AbstractExceptionHandler {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@ExceptionHandler(value = CommonException.class)
	@ResponseStatus(HttpStatus.OK)
	public ResponseModel commonException(CommonException e) {
		logger.error("commonException:{}", e.getMessage(), e);
		ResponseModel responseModel = ResponseUtil.failure(e);
		logger.debug("commonException-responseModel:{}", JSON.toJSONString(responseModel));
		return responseModel;
	}

	@ExceptionHandler(value = ServiceException.class)
	@ResponseStatus(HttpStatus.OK)
	public ResponseModel serviceException(ServiceException e) {
		logger.error("serviceException:{}", e.getMessage(), e);
		ResponseModel responseModel = ResponseUtil.failure(e);
		logger.debug("serviceException-responseModel:{}", JSON.toJSONString(responseModel));
		return responseModel;
	}

	@ExceptionHandler(value = DaoException.class)
	@ResponseStatus(HttpStatus.OK)
	public ResponseModel daoException(DaoException e) {
		logger.error("daoException:{}", e.getMessage(), e);
		ResponseModel responseModel = ResponseUtil.failure(e);
		logger.debug("daoException-responseModel:{}", JSON.toJSONString(responseModel));
		return responseModel;
	}

	@ExceptionHandler(value = SystemException.class)
	@ResponseStatus(HttpStatus.OK)
	public ResponseModel systemException(SystemException e) {
		logger.error("systemException:{}", e.getMessage(), e);
		ResponseModel responseModel = ResponseUtil.failure(e);
		logger.debug("systemException-responseModel:{}", JSON.toJSONString(responseModel));
		return responseModel;
	}

	@ExceptionHandler(value = CheckException.class)
	@ResponseStatus(HttpStatus.OK)
	public ResponseModel checkException(CheckException e) {
		logger.error("checkException:{}", e.getMessage(), e);
		ResponseModel responseModel = ResponseUtil.failure(e);
		logger.debug("checkException-responseModel:{}", JSON.toJSONString(responseModel));
		return responseModel;
	}

	@ExceptionHandler(value = BindException.class)
	@ResponseStatus(HttpStatus.OK)
	public ResponseModel bindException(BindException e){
		logger.error("bindException:{}", e.getMessage(), e);
		BindingResult bindingResult = e.getBindingResult();
		ResponseModel responseModel = ResponseModel.failure(ResultCode.PARAMETER_CHECK_FAILURE);
		if (bindingResult.hasErrors()) {
			List<FieldError> fieldErrors = bindingResult.getFieldErrors();
			if(fieldErrors.size() > 0) {
				responseModel.setMsg(fieldErrors.get(0).getDefaultMessage());
			}
		}
		logger.debug("methodArgumentNotValidException-responseModel:{}", JSON.toJSONString(responseModel));
		return responseModel;
	}

	@ExceptionHandler(value = Exception.class)
	@ResponseStatus(HttpStatus.OK)
	public ResponseModel exception(Exception e) {
		logger.error("exception:{}", e.getMessage(), e);
		return ResponseUtil.failure();
	}

}