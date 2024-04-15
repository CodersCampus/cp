package com.coderscampus.cp.domain;

import jakarta.persistence.*;
import java.util.Set;

@Entity
@Table(name = "spring_project")
public class SpringProject {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  private String title;

  private String description;

  private String imagePath;

  private Set<String> contributors;

  private String url;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getImagePath() {
    return imagePath;
  }

  public void setImagePath(String imagePath) {
    this.imagePath = imagePath;
  }

  public Set<String> getContributors() {
    return contributors;
  }

  public void setContributors(Set<String> contributors) {
    this.contributors = contributors;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }
}
