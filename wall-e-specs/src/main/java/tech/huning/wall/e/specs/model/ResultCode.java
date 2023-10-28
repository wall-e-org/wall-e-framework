package tech.huning.wall.e.specs.model;

/**
 * 全局结果码
 *
 * <p>更多内容参看<a href="https://huning.tech" target="_blank"><b>胡宁Tech</b></a>
 * @author huning
 */
public interface ResultCode {

	ResultCode SUCCESS = new ResultCode() {
		@Override
		public String getCode() {
			return "success";
		}

		@Override
		public String getMsg() {
			return "成功";
		}

	};

	ResultCode FAILURE = new ResultCode() {
		@Override
		public String getCode() {
			return "failure";
		}

		@Override
		public String getMsg() {
			return "系统异常,请稍后再试!";
		}
	};

	ResultCode PARAMETER_CHECK_FAILURE = new ResultCode() {
		@Override
		public String getCode() {
			return "parameter_check_failure";
		}

		@Override
		public String getMsg() {
			return "参数校验失败!";
		}
	};

	ResultCode ASYNC_FAILURE = new ResultCode() {
		@Override
		public String getCode() {
			return "async_failure";
		}

		@Override
		public String getMsg() {
			return "异步执行异常,请稍后再试!";
		}
	};

	String getCode();

	String getMsg();

}
