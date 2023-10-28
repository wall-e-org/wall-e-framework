package tech.huning.wall.e.specs.pagination;

import tech.huning.wall.e.specs.model.pagination.PagedOutModel;
import org.junit.Assert;
import org.junit.Test;

import java.util.Collections;
import java.util.UUID;

/**
 * 分页查询出参模型测试
 *
 * <p>更多内容参看<a href="https://huning.tech" target="_blank"><b>胡宁Tech</b></a>
 * @author huning
 */
public class PagedOutModelTest {

    @Test
    public void test(){
        PagedOutModel<String> pagedOutModel = new PagedOutModel<>();

        pagedOutModel.setData(Collections.singletonList(UUID.randomUUID().toString()))
                     .setPageNo(1)
                     .setPageSize(10)
                     .setTotalCount(101L);

        Assert.assertEquals(Long.valueOf(11), pagedOutModel.getTotalPage());
    }

}
