package model;

import static model.SystemDetail.SystemState.unknown;

import java.util.UUID;

/**
 * 
 * @author Gabriele Giunchi
 * 
 * Implementazione di {@link SystemDetail}
 *
 */
public class SystemDetailImpl implements SystemDetail {
	
	// Stato di default in cui si trova il sistema alla sua creazione
	private static final SystemState INITIAL_STATE = unknown;
	
	private final UUID agentId;
	private String id;
	private String name;
	private SystemState state;

	public SystemDetailImpl(final UUID agentId) {
		this.name = "";
		this.agentId = agentId;
		this.id = "";
		this.state = INITIAL_STATE;
	}
	
	
	@Override
	public void setState(final SystemState state) {
		this.state = state;
	}
	
	@Override
	public void setName(final String name) {
		this.name = name;
	}
	
	@Override
	public void setId(final String id) {
		this.id = id;
	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public String getId() {
		return this.id;
	}

	@Override
	public UUID getAgentId() {
		return this.agentId;
	}

	@Override
	public SystemState getState() {
		return this.state;
	}
	
	@Override
	public String toString() {
		final StringBuilder stringBuilder = new StringBuilder(this.id);
		
		if (!this.name.isEmpty()) {
			stringBuilder.append('(')
				.append(name)
				.append(')');
		}
		
		stringBuilder.append(" : ")
			.append(state);
		
		return stringBuilder.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((agentId == null) ? 0 : agentId.hashCode());
		return result;
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		
		final SystemDetailImpl other = (SystemDetailImpl) obj;
		if (agentId == null) {
			if (other.agentId != null) {
				return false;
			}
		} else if (!agentId.equals(other.agentId)) {
			return false;
		}
		
		return true;
	}
}
