package shared.model.board;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;



/**
 * 
 * @author jordanJohnson
 *
 */
import shared.locations.VertexLocation;

@SuppressWarnings("serial")
@JsonTypeInfo(use=JsonTypeInfo.Id.CLASS, include=JsonTypeInfo.As.PROPERTY, property="type")
@JsonSubTypes({
	@Type(value = Settlement.class, name = "settlement"),
	@Type(value = City.class, name = "city")
})
public class Vertex implements Serializable{
	private PlayerID owner;
	private VertexLocation location;

	public Vertex() {
		owner = null;
		location = null;
	}
	
	public Vertex(PlayerID owner, VertexLocation location) {
		this.owner = owner;
		this.location = location;
	}
	public PlayerID getOwner() {
		return owner;
	}
	public void setOwner(PlayerID owner) {
		this.owner = owner;
	}
	public VertexLocation getLocation() {
		return location;
	}
	public void setLocation(VertexLocation location) {
		this.location = location;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((location == null) ? 0 : location.hashCode());
		result = prime * result + ((owner == null) ? 0 : owner.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Vertex other = (Vertex) obj;
		if (location == null) {
			if (other.location != null)
				return false;
		} else if (!location.equals(other.location))
			return false;
		if (owner == null) {
			if (other.owner != null)
				return false;
		} else if (!owner.equals(other.owner))
			return false;
		return true;
	}
}
