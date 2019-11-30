package schedule;

import java.io.Serializable;
import java.time.LocalTime;

/**
 * 
 * @author Gabriele Giunchi
 *
 */
public final class ScheduleAction implements Serializable {
	
	private static final long serialVersionUID = -9129747122217844198L;
	
	private final ScheduleActionType type;
	private final LocalTime time;
	
	private ScheduleAction(final ScheduleActionType type, final LocalTime time) {
		this.type = type;
		this.time = time;
	}
	
	/**
	 * Restituisce un oggetto {@link ScheduleAction} 
	 * che rappresenta un'azione di attivazione del sistema.
	 * @param time : ora e minuto in cui l'azione deve essere eseguita
	 * @return
	 */
	public static ScheduleAction turnOnAction(final LocalTime time) {
		return new ScheduleAction(ScheduleActionType.turn_on, time);
	}
	
	/**
	 * Restituisce un oggetto {@link ScheduleAction} 
	 * che rappresenta un'azione di disattivazione del sistema.
	 * @param time : ora e minuto in cui l'azione deve essere eseguita
	 * @return
	 */
	public static ScheduleAction turnOffAction(final LocalTime time) {
		return new ScheduleAction(ScheduleActionType.turn_off, time);
	}

	/**
	 * 
	 * @return tipo dell'action identificato dall'enum {@link ScheduleActionType}
	 */
	public ScheduleActionType getType() {
		return this.type;
	}

	/**
	 *
	 * @return ora in cui l'azione deve essere eseguita
	 */
	public LocalTime getTime() {
		return time;
	}
	
	@Override
	public String toString() {
		return new StringBuilder(this.time.toString()).append(' ').append(this.type.toString()).toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((time == null) ? 0 : time.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
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
		
		final ScheduleAction other = (ScheduleAction) obj;
		if (time == null) {
			if (other.time != null) {
				return false;
			}
		} else if (!time.equals(other.time)) {
			return false;
		}
		if (type != other.type) {
			return false;
		}
		return true;
	}
}
