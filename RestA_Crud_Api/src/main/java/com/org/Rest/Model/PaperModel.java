package com.org.Rest.Model;


import javax.persistence.*;

@Entity
@Table(name = "papers")
public class PaperModel {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@Column(name = "title")
	private String title;
	@Column(name = "description")
	private String description;
	@Column(name = "published")
	private boolean published;
	
	public PaperModel() {
	}
	
	public PaperModel(String title, String description, boolean published) {
		this.title = title;
		this.description = description;
		this.published = published;
	}
	public long getId() {
		return id;
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
	public boolean isPublished() {
		return published;
	}
	public void setPublished(boolean isPublished) {
		this.published = isPublished;
	}
	@Override
	public String toString() {
		return "PaperModel [id=" + id + ", title=" + title + ", desc=" + description + ", published=" + published + "]";
	}
}