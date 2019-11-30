package schedule;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Gabriele Giunchi
 *
 */
public final class AlarmScheduleManager {
	
	private static final AlarmScheduleManager SINGLETON = new AlarmScheduleManager();
	
	private final Map<String, List<AlarmSchedulePlan>> map;
	
	private AlarmScheduleManager() {
		this.map = new ConcurrentHashMap<>();
	}
	
	/**
	 * @return unica istanza di {@link AlarmScheduleManager}
	 */
	public static AlarmScheduleManager getInstance() {
		return SINGLETON;
	}
	
	/**
	 * Salva uno schedule.
	 * @param schedule : oggetto {@link AlarmSchedulePlan} da salvare
	 */
	public void addSchedule(final AlarmSchedulePlan schedule) {
		if (!this.map.containsKey(schedule.getSystemId())) {
			this.map.put(schedule.getSystemId(), new ArrayList<>());
		}
		
		this.map.get(schedule.getSystemId()).add(schedule);
	}
	
	/**
	 * @return tutti gli schedule salvati
	 */
	public List<AlarmSchedulePlan> getAllSchedules() {
		final List<AlarmSchedulePlan> list = new ArrayList<>();
		this.map.values().forEach(l -> list.addAll(l));
		return list;
	}
	
	/**
	 * Restituisce tutti gli schedule che sono fissati nel giorno dato.
	 * @param day giorno della settimana da usare come filtro della ricerca
	 * @return lista di schedule filtrati per il giorno dato
	 */
	public List<AlarmSchedulePlan> getSchedulesForDay(final DayOfWeek day) {
		final List<AlarmSchedulePlan> list = new ArrayList<>();
		this.map.values().stream().forEach(l -> {
			l.stream()
			.filter(s -> s.getDay().equals(day))
			.forEach(s1 -> {
				list.add(s1);
			});
		});
		
		return list;
	}
	
	/**
	 * Restituisce tutti gli schedule associati ad un sistema.
	 * @param systemId : id univoco del sistema
	 * @return lista di schedule filtrata per il sistema
	 */
	public List<AlarmSchedulePlan> getSchedulesForSystem(final String systemId) {
		if (!this.map.containsKey(systemId)) {
			this.map.put(systemId, new ArrayList<>());
		}
		
		return this.map.get(systemId);
	}
	
	/**
	 * Rimuove tutti gli schedule associati ad un sistema.
	 * @param systemId : id univoco del sistema
	 * @return true se erano presenti schedule associati al sistema, false altrimenti
	 */
	public boolean removeSchedulesOfSystem(final String systemId) {
		return this.map.remove(systemId) != null;
	}
}
