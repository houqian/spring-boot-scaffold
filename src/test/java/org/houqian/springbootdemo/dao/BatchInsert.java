package org.houqian.springbootdemo.dao;

import cn.hutool.core.collection.CollUtil;
import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.houqian.springbootdemo.dao.master.CityMapper;
import org.houqian.springbootdemo.dto.City;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiConsumer;

/**
 * @author houqian
 * @date 2020/9/24
 * @since 1.0
 */
@Slf4j
public class BatchInsert {
	@Resource
	private SqlSessionFactory sqlSessionFactory;

	@Test
	public void test() {
		List<City> targets = mockCities();
		this.batchInsert(targets, CityMapper.class, CityMapper::insertOne);
	}

	private List<City> mockCities() {
		return Arrays.asList(City.builder().country("beijing").name("北京").state("中国").build(),
						City.builder().country("shanghai").name("上海").state("中国").build()
		);
	}

	public <M, O> void batchInsert(List<O> targets, Class<M> mapperClazz, BiConsumer<M, O> biConsumer) {
		Preconditions.checkArgument(CollUtil.isNotEmpty(targets));
		try (SqlSession sqlSession = sqlSessionFactory.openSession(ExecutorType.BATCH)) {
			M mapper = sqlSession.getMapper(mapperClazz);

			List<List<O>> partition = Lists.partition(targets, 500);
			int cnt = 0;
			for (List<O> part : partition) {
				for (O target : part) {
					biConsumer.accept(mapper, target);
				}

				// 通知DB执行预编译的批量SQL
				sqlSession.flushStatements();
				log.info("批量更新，共 {} 批，执行第 {} 批完毕. 每批 {} 行记录 ", partition.size(), ++cnt, part.size());
			}
		}
	}
}
