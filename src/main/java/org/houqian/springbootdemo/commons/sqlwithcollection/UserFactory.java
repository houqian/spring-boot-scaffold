package org.houqian.springbootdemo.commons.sqlwithcollection;

import com.google.common.base.Preconditions;

import java.util.ArrayList;
import java.util.List;

import static com.google.common.base.Preconditions.checkArgument;

/**
 * @author : houqian
 * @version : 1.0
 * @since : 2018/6/20
 */
public class UserFactory {
  public static List<User> createCollectionOfUsers(int count) {
    checkArgument(count > 0, "数量应该大于0");
    List<User> result = new ArrayList<>(count);
    for (int i = 0; i < count; i++) {
      result.add(User.builder().fullname("张三" + i)
              .password("111111" + i)
              .role(Role.builder().name("李四" + i).build())
              .build());
    }
    return result;
  }
}
