package schedule;

import java.io.Serializable;
import java.time.DateTimeException;
import java.time.LocalTime;

/**
 * 
 * @author Gabriele Giunchi
 * 
 * Periodo di tempo che intercorre tra due orari espressi nella forma hh:mm.
 *
 */
public final class TimePeriod implements Serializable {

	private static final long serialVersionUID = 297318511799292233L;
	
	private final LocalTime startTime;
	private final LocalTime endTime;
	
	private TimePeriod(final LocalTime start, final LocalTime end) throws DateTimeException {
		if (start.compareTo(end) >= 0) {
			throw new DateTimeException("L'orario di fine non può precedere quello di inizio");
		}
		
		this.startTime = start;
		this.endTime = end;
	}
	
	/**
	 * Restituisce un oggetto {@link TimePeriod} 
	 * che rappresenta l'intervallo di tempo tra i due orari dati come parametro.
	 * @param start : ora di inizio
	 * @param end : ora di fine
	 * @return
	 * @throws DateTimeException se la data di fine è precedente a quella di inizio
	 */
	public static TimePeriod between(final LocalTime start, final LocalTime end) throws DateTimeException {
		return new TimePeriod(start, end);
	}

	/**
	 * 
	 * @return l'ora di inizio del periodo
	 */
	public LocalTime getStartTime() {
		return this.startTime;
	}

	/**
	 * 
	 * @return l'ora di fine del periodo
	 */
	public LocalTime getEndTime() {
		return this.endTime;
	}

	@Override
	public String toString() {
		return new StringBuilder(startTime.toString())
				.append(" -> ")
				.append(endTime.toString())
				.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((endTime == null) ? 0 : endTime.hashCode());
		result = prime * result + ((startTime == null) ? 0 : startTime.hashCode());
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
		final TimePeriod other = (TimePeriod) obj;
		if (endTime == null) {
			if (other.endTime != null) {
				return false;
			}
		} else if (!endTime.equals(other.endTime)) {
			return false;
		}
		if (startTime == null) {
			if (other.startTime != null) {
				return false;
			}
		} else if (!startTime.equals(other.startTime)) {
			return false;
		}
		
		return true;
	}
}
