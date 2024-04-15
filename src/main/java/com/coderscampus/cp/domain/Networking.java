package com.coderscampus.cp.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Networking {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String meetups;
  private String conferences;

  private String contacts;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getMeetups() {
    return meetups;
  }

  public void setMeetups(String meetups) {
    this.meetups = meetups;
  }

  public String getConferences() {
    return conferences;
  }

  public void setConferences(String conferences) {
    this.conferences = conferences;
  }

  public String getContacts() {
    return contacts;
  }

  public void setContacts(String contacts) {
    this.contacts = contacts;
  }

  @Override
  public String toString() {
    return "Networking{"
        + "id="
        + id
        + ", meetups='"
        + meetups
        + '\''
        + ", conferences='"
        + conferences
        + '\''
        + ", contacts='"
        + contacts
        + '\''
        + '}';
  }
}
