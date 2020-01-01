package org.houqian.springbootdemo.commons.sqlwithcollection;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Builder
@Data
public class User {

  private String username;
  private String password;
  private String fullname;
  private Role   role;

  public User(String username, String password, String fullname, Role role) {
    super();
    this.username = username;
    this.password = password;
    this.fullname = fullname;
    this.role = role;
  }

//  public static final Attribute<User, String> FULL_NAME = new SimpleAttribute<User, String>("fullname") {
//    @Override
//    public String getValue(User user, QueryOptions queryOptions) {
//      return user.fullname;
//    }
//
//  };
//
//  public static final Attribute<User, String> USERNAME = new SimpleAttribute<User, String>("username") {
//    @Override
//    public String getValue(User user, QueryOptions queryOptions) { return user.username; }
//  };
}