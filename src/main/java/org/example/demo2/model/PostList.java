package org.example.demo2.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PostList extends Post {
  private Long id;
  private String authorName;
  private Long authorID;
  private String title;
  private String content;
  private Date created_at;
}
