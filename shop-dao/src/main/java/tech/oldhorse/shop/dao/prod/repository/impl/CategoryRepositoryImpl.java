package tech.oldhorse.shop.dao.prod.repository.impl;

import tech.oldhorse.shop.dao.prod.entity.CategoryDO;
import tech.oldhorse.shop.dao.prod.mapper.CategoryMapper;
import tech.oldhorse.shop.dao.prod.repository.CategoryRepository;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 分类 服务实现类
 * </p>
 *
 * @author mika
 * @since 2025-05-30
 */
@Service
public class CategoryRepositoryImpl extends ServiceImpl<CategoryMapper, CategoryDO> implements CategoryRepository {

}
