package tech.huning.wall.e.util;

import tech.huning.wall.e.specs.constant.CommonConstant;
import tech.huning.wall.e.specs.exception.SystemException;
import tech.huning.wall.e.specs.model.ResultCode;
import tech.huning.wall.e.specs.model.dto.CommonDTO;
import tech.huning.wall.e.specs.model.form.CommonForm;
import tech.huning.wall.e.specs.model.po.CommonPO;
import tech.huning.wall.e.specs.model.session.CommonSession;
import tech.huning.wall.e.specs.model.vo.CommonVO;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 * Bean操作工具类
 *
 * <p>更多内容参看<a href="https://huning.tech" target="_blank"><b>胡宁Tech</b></a>
 * @author huning
 */
public class BeanUtil {

    private BeanUtil(){}

    /**
     * 依托Form数据模型创建DTO，并复制必要的数据
     * @param form  form数据
     * @param clazz 类型
     * @return dto
     * @param <T> 类型
     * @throws SystemException 系统异常
     */
    public static <T extends CommonDTO> T createDtoFromForm(CommonForm form, Class<T> clazz) throws SystemException {
        return createBeanFromInstance(form, clazz);
    }

    /**
     * 依托Form数据模型创建DTO，并复制必要的数据
     * @param dto dto数据
     * @param clazz 类型
     * @return session数据
     * @param <T> 类型
     * @throws SystemException 系统异常
     */
    public static <T extends Serializable> T createSessionFromDto(CommonDTO dto, Class<T> clazz) throws SystemException {
        return createBeanFromInstance(dto, clazz);
    }

    /**
     * 依托DTO数据模型创建VO，并复制必要的数据
     * @param dto   dto数据
     * @param clazz 类型
     * @return dto数据
     * @param <T> 类型
     * @throws SystemException 系统异常
     */
    public static <T extends CommonVO> T createVoFromDto(CommonDTO dto, Class<T> clazz) throws SystemException {
        return createBeanFromInstance(dto, clazz);
    }

    /**
     * 通过session创建vo
     * @param session session
     * @param clazz 类型
     * @return vo数据
     * @param <T> 类型
     * @throws SystemException 系统异常
     */
    public static <T extends CommonVO> T createVoFromSession(CommonSession session, Class<T> clazz) throws SystemException {
        return createBeanFromInstance(session, clazz);
    }

    /**
     * 依托DTO数据模型创建PO，并复制必要的数据
     * @param dto dto
     * @param clazz 类型
     * @return po
     * @param <T> 类型
     * @throws SystemException 系统异常
     */
    public static <T extends CommonPO> T createPoFromDto(CommonDTO dto, Class<T> clazz) throws SystemException {
        return createBeanFromInstance(dto, clazz);
    }

    /**
     * 依托Form数据模型创建PO，并复制必要的数据
     * @param form form
     * @param clazz 类型
     * @return po
     * @param <T> 类型
     * @throws SystemException 系统异常
     */
    public static <T extends CommonPO> T createPoFromForm(CommonForm form, Class<T> clazz) throws SystemException {
        return createBeanFromInstance(form, clazz);
    }

    /**
     * 创建标记删除的PO
     * @param id 编码
     * @param clazz 类型
     * @return po
     * @param <T> 类型
     * @throws SystemException 系统异常
     */
    public static <T extends CommonPO> T createPoOfMarkDelete(Long id, Class<T> clazz) throws SystemException {
        try {
            T bean = clazz.newInstance();
            bean.setId(id);
            bean.setIsDeleted(CommonConstant.IS_DELETED_NO);
            return bean;
        } catch (InstantiationException e) {
            throw new SystemException(e, ResultCode.FAILURE);
        } catch (IllegalAccessException e) {
            throw new SystemException(e, ResultCode.FAILURE);
        }
    }

    /**
     * 依托PO数据模型创建DTO，并复制必要的数据
     * @param po po数据
     * @param clazz 类型
     * @return dto
     * @param <T> 类型
     * @throws SystemException 系统异常
     */
    public static <T extends CommonDTO> T createDtoFromPo(CommonPO po, Class<T> clazz) throws SystemException {
        return createBeanFromInstance(po, clazz);
    }

    /**
     * 从instance创建bean
     * @param instance 实例
     * @param clazz 类型
     * @return bean
     * @param <T> 类型
     * @throws SystemException 系统异常
     */
    private static <T> T createBeanFromInstance(Object instance, Class<T> clazz) throws SystemException {
        try {
            T bean = clazz.newInstance();
            if(Objects.nonNull(instance)) {
                BeanUtils.copyProperties(instance, bean);
            }
            if(CommonPO.class.isAssignableFrom(clazz)) {
                CommonPO po = (CommonPO)bean;
                if(Objects.isNull(po.getCreatedAt())) {
                    po.setCreatedAt(new Date());
                }
                if(Objects.isNull(po.getUpdatedAt())) {
                    po.setUpdatedAt(new Date());
                }
                if(StringUtil.isEmpty(po.getCreatedBy())) {
                    po.setCreatedBy(CommonConstant.CREATED_BY_DEFAULT_VALUE);
                }
                if(StringUtil.isEmpty(po.getUpdatedBy())) {
                    po.setUpdatedBy(CommonConstant.UPDATED_BY_DEFAULT_VALUE);
                }
                return clazz.cast(po);
            }
            return bean;
        } catch (InstantiationException | IllegalAccessException e) {
            throw new SystemException(e, ResultCode.FAILURE);
        }
    }

}
