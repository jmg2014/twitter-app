package twitter.repository;

import java.time.LocalDateTime;

public class Post implements Comparable<Post>{

	public String getComment() {
		return comment;
	}


	public LocalDateTime getDateTime() {
		return dateTime;
	}


	private String owner;
	private String comment;
	private LocalDateTime dateTime;
	
	
	public Post(String owner,String comment, LocalDateTime dateTime){
		this.owner=owner;
		this.comment=comment;
		this.dateTime=dateTime;
	}


	public String getOwner() {
		return owner;
	}


	@Override
	public int compareTo(Post o) {
		return o.getDateTime().compareTo(this.getDateTime());
	}
	
}
