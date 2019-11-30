package schedule;

import java.io.Serializable;
import java.time.DayOfWeek;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 
 * @author Gabriele Giunchi
 *
 */
public final class AlarmSchedulePlan implements Serializable {
	
	private static final long serialVersionUID = 4394233611764549773L;
	
	private final String systemId;
	private final Set<ScheduleAction> actions;
	private final DayOfWeek day;
	
	private AlarmSchedulePlan(final String systemId, final DayOfWeek day, final Set<ScheduleAction> actions) {
		this.systemId = systemId;
		this.actions = actions;
		this.day = day;
	}
	
	/**
	 * Restituisce un oggetto {@link AlarmSchedulePlan} 
	 * che rappresenta un periodo di attivazione di un sistema.
	 * 
	 * @param systemId : id univoco del sistema
	 * @param day : giorno in cui applicare lo schedule
	 * @param period : periodo di tempo in cui il sistema deve rimanere attivo
	 * @return
	 */
	public static AlarmSchedulePlan activationPeriod(final String systemId, final DayOfWeek day, final TimePeriod period) {
		final ScheduleAction turnOnAction = ScheduleAction.turnOnAction(period.getStartTime());
		final ScheduleAction turnOffAction = ScheduleAction.turnOffAction(period.getEndTime());
		final Set<ScheduleAction> actions = new HashSet<>(Arrays.asList(turnOnAction, turnOffAction));
		return new AlarmSchedulePlan(systemId, day, actions);
	}
	
	/**
	 * Restituisce un oggetto {@link AlarmSchedulePlan} che rappresenta 
	 * un'azione di attivazione o disattivazionedi un sistema.
	 * 
	 * @param systemId : id univoco del sistema
	 * @param day : giorno in cui applicare lo schedule
	 * @param action : azione dello schedule
	 */
	public static AlarmSchedulePlan singleAction(final String systemId, final DayOfWeek day, final ScheduleAction action) {
		final HashSet<ScheduleAction> set = new HashSet<>(Arrays.asList(action));
		return new AlarmSchedulePlan(systemId, day, set);
	}
	
	/**
	 * 
	 * @return id univo del sistema associato allo schedule
	 */
	public String getSystemId() {
		return this.systemId;
	}
	
	/**
	 * 
	 * @return giorno in cui è applicato lo schedule
	 */
	public DayOfWeek getDay() {
		return this.day;
	}
	
	/**
	 * 
	 * @return azioni associate allo schedule. 
	 * Le azioni possono essere singole o in coppia attivazione-disattivazione
	 */
	public Set<ScheduleAction> getActions() {
		return this.actions;
	}
	
	@Override
	public String toString() {
		return new StringBuilder()
				.append(this.systemId)
				.append(" : ")
				.append(this.actions.stream().map(e -> e.toString()))
				.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((actions == null) ? 0 : actions.hashCode());
		result = prime * result + ((day == null) ? 0 : day.hashCode());
		result = prime * result + ((systemId == null) ? 0 : systemId.hashCode());
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
		
		final AlarmSchedulePlan other = (AlarmSchedulePlan) obj;
		if (actions == null) {
			if (other.actions != null) {
				return false;
			}
		} else if (!actions.equals(other.actions)) {
			return false;
		}
		if (day != other.day) {
			return false;
		}
		if (systemId == null) {
			if (other.systemId != null) {
				return false;
			}
		} else if (!systemId.equals(other.systemId)) {
			return false;
		}
		
		return true;
	}
}
